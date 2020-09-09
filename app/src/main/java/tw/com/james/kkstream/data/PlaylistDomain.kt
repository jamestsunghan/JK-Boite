package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class PlaylistDomain(val text: String, var id: String): Parcelable {
    ALBUM("album", ""),
    FEATURED("featured-playlists", ""),
    CHART("charts", "");
    fun setPlayId(id: String){
        this.id = id
    }
}