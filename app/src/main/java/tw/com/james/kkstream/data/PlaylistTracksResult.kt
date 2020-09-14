package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlaylistTracksResult (
    val data: List<Track> = listOf(),
    val paging: Paging = Paging(0,10),
    val summary: Summary = Summary(0),
    val error: String? = null
): Parcelable