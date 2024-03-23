package com.vyapp.pizzaapp.app.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.vyapp.pizzaapp.R
import com.vyapp.pizzaapp.app.util.showToastLong
import com.vyapp.pizzaapp.app.view.config.BottomNavConfig.navItemClickListeners
import com.vyapp.pizzaapp.app.view.config.TabLayoutConfig
import com.vyapp.pizzaapp.app.view.config.TabLayoutConfig.tabClickListeners
import com.vyapp.pizzaapp.app.view.config.TabLayoutConfig.tabLayoutConfig
import com.vyapp.pizzaapp.app.view.custom.BannerView
import com.vyapp.pizzaapp.app.view.rv.DishAdapter
import com.vyapp.pizzaapp.databinding.FragmentDashboardBinding
import com.vyapp.pizzaapp.domain.model.DishModel
import kotlinx.coroutines.flow.collectLatest

class DashboardFragment : Fragment() {

    private val binding: FragmentDashboardBinding by lazy {
        FragmentDashboardBinding.inflate(layoutInflater)
    }

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity())[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabLayoutConfig(binding.tabLayout)
        tabClickListeners(binding.tabLayout, mainViewModel, requireContext())
        navItemClickListeners(binding.bottomNav,requireContext())
        lifecycleScope.launchWhenStarted {
            mainViewModel.bannerData.collectLatest { bannerList ->
                for (_banner in bannerList) {
                    val banner: BannerView = BannerView(requireContext(), _banner)
                    binding.bannerPanel.addView(banner)
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            mainViewModel.dishData.collectLatest {
                when (it) {
                    is UIState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is UIState.Success<List<DishModel>> -> {
                        binding.progress.visibility = View.GONE
                        binding.dishList.adapter = DishAdapter(it.data)
                    }
                    is UIState.Error -> {
                        binding.progress.visibility = View.GONE
                        it.e.message.toString().showToastLong(requireContext())
                    }
                }
            }
        }

        binding.dishList.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}