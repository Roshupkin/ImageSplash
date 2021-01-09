package android.com.roshchupkin.unsplashapp.ui

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalPagingApi
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainNavHostFragment : NavHostFragment() {


    @Inject
    lateinit var mainFragmentFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = mainFragmentFactory
    }

}
