package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentImageBinding
import android.com.roshchupkin.unsplashapp.model.Image.ImageDomain
import android.com.roshchupkin.unsplashapp.ui.adapters.ImageAdapter
import android.com.roshchupkin.unsplashapp.ui.viewmodel.SearchImageViewModel
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class SearchImageFragment : Fragment(R.layout.fragment_image), ImageAdapter.Interaction {

    private val searchImageViewModel: SearchImageViewModel by viewModels()
    lateinit var imageAdapter: ImageAdapter

    private var _binding: FragmentImageBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentImageBinding.bind(view)

        val queryString: String = this.arguments?.getString("queryString").toString()

        binding.apply {
            recyclerView.apply {
                imageAdapter = ImageAdapter(this@SearchImageFragment)
                adapter = imageAdapter
                scrollToPosition(0)
            }
            searchImageViewModel.getImageList(queryString).observe(viewLifecycleOwner) {
                imageAdapter.submitData(viewLifecycleOwner.lifecycle, it)
            }
        }




    }

    override fun onItemSelected(position: Int, item: ImageDomain) {
        val bundle = bundleOf("itemID" to item.id)
        findNavController().navigate(R.id.action_imageFragment_to_detailImageFragment, bundle)
    }
}