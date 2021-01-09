package android.com.roshchupkin.unsplashapp.ui

import android.com.roshchupkin.unsplashapp.ui.fragment.RandomImageFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
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
                RandomImageFragment::class.java.name -> {
                    val fragment = RandomImageFragment()
                    fragment
                }

                else -> super.instantiate(classLoader, className)
            }
        }
    }
