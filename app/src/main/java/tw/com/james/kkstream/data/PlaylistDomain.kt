package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class PlaylistDomain(val text: String, var id: String, var cover: String, var album: Album?) :
    Parcelable {
    ALBUM("albums", "", "", null),
    FEATURED("featured-playlists", "", "", null),
    CHART("charts", "", "", null)
}