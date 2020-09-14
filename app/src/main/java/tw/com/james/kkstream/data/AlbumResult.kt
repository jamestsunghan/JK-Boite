package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlbumResult (
    val data: List<Album>,
    val paging: Paging,
    val summary: Summary,
    val error: String? = null
): Parcelable
