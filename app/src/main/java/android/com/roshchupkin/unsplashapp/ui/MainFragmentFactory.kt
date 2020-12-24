package android.com.roshchupkin.unsplashapp.ui

import android.com.roshchupkin.unsplashapp.ui.fragment.RandomPhotoFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class MainFragmentFactory
    @Inject
    constructor(
    ):FragmentFactory() {

        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return when (className) {

                RandomPhotoFragment::class.java.name -> {
                    val fragment = RandomPhotoFragment()
                    fragment
                }

                else -> super.instantiate(classLoader, className)
            }
        }
    }
