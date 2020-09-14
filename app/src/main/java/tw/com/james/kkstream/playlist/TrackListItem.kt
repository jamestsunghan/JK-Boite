package tw.com.james.kkstream.playlist

import tw.com.james.kkstream.data.Track

sealed class TrackListItem {
    data class CoverItem(val url: String) : TrackListItem()
    data class TrackItem(val track: Track) : TrackListItem()
}