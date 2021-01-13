package android.com.roshchupkin.unsplashapp.ui

import android.com.roshchupkin.unsplashapp.ui.fragment.DetailImageFragment
import android.com.roshchupkin.unsplashapp.ui.fragment.RandomImagesFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.paging.ExperimentalPagingApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class MainFragmentFactory
    @Inject
    constructor(
    ):FragmentFactory() {

        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return when (className) {
                RandomImagesFragment::class.java.name -> {
                    val fragment = RandomImagesFragment()
                    fragment
                }
               DetailImageFragment::class.java.name -> {
                    val fragment = DetailImageFragment()
                    fragment
                }

                else -> super.instantiate(classLoader, className)
            }
        }
    }
