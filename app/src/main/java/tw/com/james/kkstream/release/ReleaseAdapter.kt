package tw.com.james.kkstream.release

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.databinding.ItemReleaseBinding
import tw.com.james.kkstream.databinding.ItemReleaseHeaderBinding

class ReleaseAdapter: ListAdapter<Release, RecyclerView.ViewHolder>(DiffCallback) {
    class HeaderViewHolder(val binding: ItemReleaseHeaderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(albums: Release.AlbumItem){
            binding.albumItem = albums
            binding.recyclerAlbum.adapter = AlbumAdapter()
            binding.executePendingBindings()
        }
    }
    class ReleaseViewHolder(val binding: ItemReleaseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(release: Chart){
            binding.chart = release
            binding.detail = release.owner.name + "@" + release.updatedAt.take(10)
            binding.executePendingBindings()

        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<Release>(){
        override fun areItemsTheSame(oldItem: Release, newItem: Release): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Release, newItem: Release): Boolean {
            return oldItem == newItem
        }

        private const val ITEM_VIEW_TYPE_HEADER  = 0x00
        private const val ITEM_VIEW_TYPE_RELEASE = 0x01
    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is Release.AlbumItem    -> ITEM_VIEW_TYPE_HEADER
            is Release.PlayListItem -> ITEM_VIEW_TYPE_RELEASE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder(ItemReleaseHeaderBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            ITEM_VIEW_TYPE_RELEASE -> ReleaseViewHolder(ItemReleaseBinding
                .inflate(LayoutInflater.from(parent.context), parent, false))
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is HeaderViewHolder -> holder.bind(getItem(position) as Release.AlbumItem)
            is ReleaseViewHolder -> holder.bind((getItem(position) as Release.PlayListItem).play)
        }
    }
}