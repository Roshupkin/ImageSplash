package android.com.roshchupkin.unsplashapp.ui.adapters

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.database.entity.RandomImageCacheEntity
import android.com.roshchupkin.unsplashapp.databinding.ItemImageBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class RandomImageAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<RandomImageCacheEntity, RandomImageAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RandomImageCacheEntity>() {

            override fun areItemsTheSame(
                oldItem: RandomImageCacheEntity,
                newItem: RandomImageCacheEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RandomImageCacheEntity,
                newItem: RandomImageCacheEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(
                currentItem,
                interaction
            )
        }

    }


    inner class PhotoViewHolder(
        private val binding: ItemImageBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }

        fun bind(image: RandomImageCacheEntity, interaction: Interaction?) {
            binding.apply {
                Glide.with(itemView)
                    .load(image.urlsImageRegular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewPhoto)

                Glide.with(itemView)
                    .load(image.profileImageSmall)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewUserProfile)

                textViewUserName.text = image.username
                textPositionOnAdapter.text = absoluteAdapterPosition.toString()

                binding.root.setOnClickListener {
                    interaction?.onItemSelected(absoluteAdapterPosition, image)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: RandomImageCacheEntity)
    }


}
