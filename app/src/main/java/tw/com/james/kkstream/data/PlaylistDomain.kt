package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class PlaylistDomain(val text: String, var id: String, var cover: String): Parcelable {
    ALBUM("album", "", ""),
    FEATURED("featured-playlists", "", ""),
    CHART("charts", "", "");
}