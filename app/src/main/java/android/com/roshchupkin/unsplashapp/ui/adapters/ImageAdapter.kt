package android.com.roshchupkin.unsplashapp.ui.adapters

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.ItemImageBinding
import android.com.roshchupkin.unsplashapp.model.ImageDomain
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class ImageAdapter(private val interaction: Interaction? = null) :
PagingDataAdapter<ImageDomain, ImageAdapter.ImageViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ImageDomain>() {

            override fun areItemsTheSame(
                oldItem: ImageDomain,
                newItem: ImageDomain
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageDomain,
                newItem: ImageDomain
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(
                currentItem,
                interaction
            )
        }

    }


    inner class ImageViewHolder(
        private val binding: ItemImageBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ImageDomain, interaction: Interaction?) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.urls?.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewPhoto)

                Glide.with(itemView)
                    .load(item.user?.profile_image?.small)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewUserProfile)

                textViewName.text = item.user?.name



                binding.root.setOnClickListener {
                    interaction?.onItemSelected(absoluteAdapterPosition, item)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: ImageDomain)
    }


}