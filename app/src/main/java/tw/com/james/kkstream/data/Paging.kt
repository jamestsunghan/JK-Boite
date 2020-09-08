package tw.com.james.kkstream.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Paging (
    val offset: Int,
    val limit: Int,
    val previous: String?,
    val next: String?
): Parcelable
