package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageVariant (
    val height: Int,
    val width: Int,
    val url: String
): Parcelable
