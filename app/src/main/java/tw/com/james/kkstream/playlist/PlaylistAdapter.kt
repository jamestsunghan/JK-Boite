package tw.com.james.kkstream.playlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.data.Track
import tw.com.james.kkstream.databinding.ItemPlaylistHeaderBinding
import tw.com.james.kkstream.databinding.ItemPlaylistTrackBinding

class PlaylistAdapter : ListAdapter<TrackListItem, RecyclerView.ViewHolder>(DiffCallback) {
    class CoverViewHolder(private val binding: ItemPlaylistHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.coverUrl = url
            binding.executePendingBindings()
        }
    }

    class TrackViewHolder(private val binding: ItemPlaylistTrackBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(track: Track) {
            binding.track = track
            binding.detail = track.album?.artist?.name + "@" + track.album?.releaseDate
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<TrackListItem>() {
        override fun areItemsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: TrackListItem, newItem: TrackListItem): Boolean {
            return oldItem == newItem
        }

        private const val TRACK_HEADER_VIEW_TYPE = 0x11
        private const val TRACK_ITEM_VIEW_TYPE = 0x12

    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is TrackListItem.CoverItem -> TRACK_HEADER_VIEW_TYPE
            is TrackListItem.TrackItem -> TRACK_ITEM_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TRACK_HEADER_VIEW_TYPE -> CoverViewHolder(
                ItemPlaylistHeaderBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
            TRACK_ITEM_VIEW_TYPE -> TrackViewHolder(
                ItemPlaylistTrackBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw ClassCastException("Unknown ViewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CoverViewHolder -> holder.bind((getItem(position) as TrackListItem.CoverItem).url)
            is TrackViewHolder -> holder.bind((getItem(position) as TrackListItem.TrackItem).track)
        }
    }
}

