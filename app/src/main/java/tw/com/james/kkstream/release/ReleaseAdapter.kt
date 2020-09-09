package tw.com.james.kkstream.release

import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.databinding.ItemReleaseBinding
import tw.com.james.kkstream.databinding.ItemReleaseHeaderBinding

class ReleaseAdapter(val onClickListener: OnClickListener, val viewModel: ReleaseViewModel)
    : ListAdapter<Release, RecyclerView.ViewHolder>(DiffCallback) {
    class HeaderViewHolder(val binding: ItemReleaseHeaderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(albums: Release.AlbumItem, viewModel: ReleaseViewModel){
            binding.albumItem = albums
            binding.recyclerAlbum.adapter = AlbumAdapter(AlbumAdapter.OnClickListener {album->
                viewModel.watchTracks(PlaylistDomain.ALBUM.apply {
                    id = album.id
                    cover = album.images.last().url
                    this.album = album
                })
            })

            binding.recyclerAlbum.addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    val canScrollHZ = rv.canScrollHorizontally(RecyclerView.FOCUS_FORWARD)

                    if(canScrollHZ){
                        when(e.action){
                            MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                        }
                    } else {
                        when(e.action){
                            MotionEvent.ACTION_MOVE -> {
//                                if(e.x < e.getHistoricalX(1)){
//                                    rv.parent.requestDisallowInterceptTouchEvent(false)
//                                }
                            }
                        }

                    }


                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
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
            is HeaderViewHolder -> holder.bind(getItem(position) as Release.AlbumItem, viewModel)
            is ReleaseViewHolder -> {
                val chart = (getItem(position) as Release.PlayListItem).play
                holder.bind(chart)
                holder.itemView.setOnClickListener {
                    onClickListener.onClick(chart)
                }
            }
        }
    }

    class OnClickListener(private val clicklistener: (chart: Chart) -> Unit){
        fun onClick(chart: Chart) = clicklistener(chart)
    }
}