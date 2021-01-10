package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.FragmentDetailImageBinding
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.com.roshchupkin.unsplashapp.ui.viewmodule.DetailImageViewModule
import android.com.roshchupkin.unsplashapp.utill.DataState
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailImageFragment
@Inject
constructor() : Fragment(R.layout.fragment_detail_image) {
    private val viewModel: DetailImageViewModule by viewModels()

    private var _binding: FragmentDetailImageBinding? = null
    private val binding get() = _binding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribe()
        val itemId = this.arguments?.getString("itemID").toString()
        _binding = FragmentDetailImageBinding.bind(view)

        viewModel.getImage(itemId)




    }

    private fun subscribe() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<ImageDomain> -> {
                    displayProgressBar(false)
                    binding?.apply {
                        Glide.with(this@DetailImageFragment)
                            .load(dataState.data.urls?.regular)
                            .centerCrop()
                            .transition(DrawableTransitionOptions.withCrossFade())
                            .error(R.drawable.ic_error_loading_24)
                            .into(imageView)
                        textSetHeight.text = dataState.data.height.toString()
                        textSetWidth.text = dataState.data.width.toString()
                        textSetDescription.text = dataState.data.description
                        textSetLink.text = dataState.data.urls?.regular
                    }
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
                is DataState.HttpError -> {
                    displayProgressBar(false)
                    displayError(dataState.httpException.toString())
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    displayError(dataState.exceptionMessage)
                }
            }
        })
    }

    private fun displayError(message: String?) {

        if (message != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()

        } else
            Toast.makeText(context, "Unknown error", Toast.LENGTH_SHORT).show()

    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding?.apply {
            if (isDisplayed) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

}