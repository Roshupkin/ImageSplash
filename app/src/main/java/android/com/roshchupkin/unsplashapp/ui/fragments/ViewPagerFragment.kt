package android.com.roshchupkin.unsplashapp.ui.fragments

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentImageListBinding
import android.com.roshchupkin.unsplashapp.databinding.FragmentViewpagerBinding
import android.com.roshchupkin.unsplashapp.ui.adapters.ViewPagerAdapter
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
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

    
    private var _binding: FragmentViewpagerBinding? = null
    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentViewpagerBinding.bind(view)

        binding.viewpager.adapter = ViewPagerAdapter(
            childFragmentManager,
            lifecycle
        )

        TabLayoutMediator(
            requireActivity().tabs, binding.viewpager
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
                    findNavController().navigate(
                        R.id.action_containerFragment_to_searchImageFragment,
                        bundle
                    )
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
        requireActivity().tabs.isVisible = true
        requireActivity().toolbar.isVisible = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        requireActivity().tabs.isVisible = false
        requireActivity().toolbar.isVisible = false
    }


}
