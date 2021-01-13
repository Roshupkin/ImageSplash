package android.com.roshchupkin.unsplashapp.ui.adapters

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.ItemCollectionImageBinding
import android.com.roshchupkin.unsplashapp.model.CollectionDomain
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class CollectionAdapter(private val interaction: Interaction? = null) :
    PagingDataAdapter<CollectionDomain, CollectionAdapter.CollectionViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CollectionDomain>() {

            override fun areItemsTheSame(
                oldItem: CollectionDomain,
                newItem: CollectionDomain
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: CollectionDomain,
                newItem: CollectionDomain
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionViewHolder {
        val binding =
            ItemCollectionImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CollectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CollectionViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(
                currentItem,
                interaction
            )
        }

    }


    inner class CollectionViewHolder(
        private val binding: ItemCollectionImageBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {

                }
            }
        }

        fun bind(item: CollectionDomain, interaction: Interaction?) {
            binding.apply {
                Glide.with(itemView)
                    .load(item.coverPhoto?.urls?.regular)
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
                textTotalPhotos.text = item.totalPhotos.toString()
                textTitleCollection.text = item.title


                binding.root.setOnClickListener {
                    interaction?.onItemSelected(absoluteAdapterPosition, item)
                }
            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: CollectionDomain)
    }


}