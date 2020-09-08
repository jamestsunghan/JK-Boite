package tw.com.james.kkstream

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.ranking.RankingAdapter
import tw.com.james.kkstream.release.AlbumAdapter
import tw.com.james.kkstream.release.Release
import tw.com.james.kkstream.release.ReleaseAdapter

@BindingAdapter("chartList")
fun submitChartList(recyclerView: RecyclerView, list: List<Chart>?){
    list?.let{
        when(val adapter = recyclerView.adapter){
            is RankingAdapter -> adapter.submitList(it)
        }
    }
}

@BindingAdapter("releaseList")
fun submitReleaseList(recyclerView: RecyclerView, list: List<Release>?){
    list?.let{
        when(val adapter = recyclerView.adapter){
            is ReleaseAdapter -> adapter.submitList(it)
        }
    }
}

@BindingAdapter("albumList")
fun submitAlbumList(recyclerView: RecyclerView, list: List<Album>?){
    list?.let{
        when(val adapter = recyclerView.adapter){
            is AlbumAdapter -> adapter.submitList(it)
        }
    }
}

@BindingAdapter("imageUrl")
fun gildeImage(imgView: ImageView, url: String?){
    url?.let{
        val uri = it.toUri().buildUpon().build()

        Glide.with(imgView.context).load(uri).apply(
            RequestOptions()
                .placeholder(R.drawable.app_icon)
                .error(R.drawable.app_icon)
        ).into(imgView)
    }
}