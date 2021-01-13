package android.com.roshchupkin.unsplashapp.ui.adapters


import android.com.roshchupkin.unsplashapp.ui.fragment.CollectionFragment
import android.com.roshchupkin.unsplashapp.ui.fragment.RandomImagesFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.paging.ExperimentalPagingApi
import androidx.viewpager2.adapter.FragmentStateAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class ViewPagerAdapter(fragmentManager: FragmentManager, ifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, ifecycle) {

    override fun getItemCount(): Int {
        return 2
    }


    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return RandomImagesFragment()
            1 -> return CollectionFragment()
            else -> return throw Throwable("Invalid position $position")
        }
    }

}