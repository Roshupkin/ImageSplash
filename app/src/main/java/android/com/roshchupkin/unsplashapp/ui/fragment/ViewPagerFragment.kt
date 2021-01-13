package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.ui.adapters.ViewPagerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.ExperimentalPagingApi
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_viewpager.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalPagingApi
class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.viewpager.adapter = ViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )



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

        requireActivity().search_view.setOnQueryTextFocusChangeListener { v, hasFocus ->
            when (hasFocus) {
                false -> {
                    v.search_view.setQuery("", false)
                }

            }
        }
        requireActivity().search_view.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    requireActivity().search_view.clearFocus()
                    val bundle = bundleOf("queryString" to query)
                    findNavController().navigate(R.id.action_viewPagerFragment_to_searchImageFragment,bundle)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

    }

    override fun onResume() {
        super.onResume()
        requireActivity().appbar_layout.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().appbar_layout.visibility = View.GONE

    }


}
