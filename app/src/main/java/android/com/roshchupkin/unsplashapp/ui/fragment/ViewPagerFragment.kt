package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.ui.adapters.ViewPagerAdapter
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_viewpager.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewpager.adapter = ViewPagerAdapter(
            requireActivity().supportFragmentManager,
            lifecycle)

        TabLayoutMediator(
            requireActivity().tab_layout, view.viewpager
        ) { tab, position ->

            when (position) {
                0 -> {
                    tab.text = "Random Photo"
                }
                1 -> {
                    tab.text = "Collection"
                }
            }
        }.attach()

    }



}
