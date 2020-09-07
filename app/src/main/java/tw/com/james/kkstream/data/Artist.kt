package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist (
    val id: String,
    val name: String,
    val url: String,
    val images: List<ImageVariant>
): Parcelable
