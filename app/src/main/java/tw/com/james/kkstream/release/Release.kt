package tw.com.james.kkstream.release

import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart

sealed class Release{
    data class AlbumItem(val albums: List<Album>): Release()
    data class PlayListItem(val play: Chart): Release()
}