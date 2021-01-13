package android.com.roshchupkin.unsplashapp.ui.adapters

import android.com.roshchupkin.unsplashapp.databinding.ImageLoadStateFooterBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class ImageLoadStateAdapter (private val retry: () -> Unit) :
   LoadStateAdapter<ImageLoadStateAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(
        holder: ImageLoadStateAdapter.LoadStateViewHolder,
        loadState: LoadState
    ) {
       holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): ImageLoadStateAdapter.LoadStateViewHolder {
        val binding = ImageLoadStateFooterBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LoadStateViewHolder(binding)
    }

    inner class LoadStateViewHolder(private val binding: ImageLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
                buttonRetry.isVisible = loadState !is LoadState.Loading
                textViewSystemMessage.isVisible = loadState !is LoadState.Loading
            }
        }
    }

}