package android.com.roshchupkin.unsplashapp.ui

import android.com.roshchupkin.unsplashapp.ui.fragment.FragmentTest
import android.com.roshchupkin.unsplashapp.ui.fragment.RandomImageFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class ViewPagerAdapter(fragmentActivity: FragmentActivity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
       when(position){
           0-> return RandomImageFragment()
           else->return FragmentTest()
       }
    }
}