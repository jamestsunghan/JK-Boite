package tw.com.james.kkstream.release

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.databinding.ItemReleaseAlbumBinding

class AlbumAdapter(val onClickListener: OnClickListener): ListAdapter<Album, AlbumAdapter.AlbumViewHolder>(DiffCallback) {

    class AlbumViewHolder(private val binding: ItemReleaseAlbumBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(album: Album){
            binding.album = album
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Album>(){
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(ItemReleaseAlbumBinding
            .inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener.onClick(getItem(position))
        }
    }

    class OnClickListener(val clickListener: (album: Album)-> Unit){
        fun onClick(album: Album) = clickListener(album)
    }
}