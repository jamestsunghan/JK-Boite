package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReleaseResult (
    val id: String,
    val title: String,
    val albums: AlbumResult,
    val error: String? = null
): Parcelable