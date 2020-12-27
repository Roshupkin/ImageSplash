package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.ItemRandomPhotoBinding
import android.com.roshchupkin.unsplashapp.network.entity.RandomImageNetworkEntity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class PhotoAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<RandomImageNetworkEntity, PhotoAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RandomImageNetworkEntity>() {

            override fun areItemsTheSame(
                oldItem: RandomImageNetworkEntity,
                newItem: RandomImageNetworkEntity
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RandomImageNetworkEntity,
                newItem: RandomImageNetworkEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding =
            ItemRandomPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)

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
        private val binding: ItemRandomPhotoBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }

        fun bind(image: RandomImageNetworkEntity, interaction: Interaction?) {
            binding.apply {
                Glide.with(itemView)
                    .load(image.urlsNetworkEntity.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewPhoto)

                textViewUserName.text = image.userNetworkEntity.username
                textViewUserName.setOnClickListener {
                    interaction?.onItemSelected(absoluteAdapterPosition, image) }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: RandomImageNetworkEntity)
    }


}
