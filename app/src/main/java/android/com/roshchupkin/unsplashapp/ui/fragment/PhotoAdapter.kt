package android.com.roshchupkin.unsplashapp.ui.fragment

import android.com.roshchupkin.unsplashapp.R
import android.com.roshchupkin.unsplashapp.databinding.ItemRandomPhotoBinding
import android.com.roshchupkin.unsplashapp.network.entity.RandomPhotoListResponse
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_random_photo.view.*

class PhotoAdapter(private val listener: OnItemClickListener) :
    PagingDataAdapter<RandomPhotoListResponse, PhotoAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RandomPhotoListResponse>() {

            override fun areItemsTheSame(
                oldItem: RandomPhotoListResponse,
                newItem: RandomPhotoListResponse
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RandomPhotoListResponse,
                newItem: RandomPhotoListResponse
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
            holder.bind(currentItem)
        }
    }


    inner class PhotoViewHolder(private val binding: ItemRandomPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        listener.onItemClick(item)
                    }
                }
            }
        }

        fun bind(photo: RandomPhotoListResponse) {
            binding.apply {
                Glide.with(itemView)
                    .load(photo.urls.regular)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_error_loading_24)
                    .into(imageViewPhoto)

                textViewUserName.text = photo.user.username
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(photo: RandomPhotoListResponse)
    }


}
