package android.com.roshchupkin.unsplashapp.ui

import android.com.roshchupkin.unsplashapp.R
import android.content.Context
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
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
