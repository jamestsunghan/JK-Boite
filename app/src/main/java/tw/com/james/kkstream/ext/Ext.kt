package tw.com.james.kkstream.ext

import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.release.Release

fun List<Chart>.toReleaseItem(albums: List<Album>): List<Release> {
    return if (this.isEmpty()) {
        listOf(Release.AlbumItem(albums))
    } else {
        listOf(Release.AlbumItem(albums)) + this.map { chart ->
            Release.PlayListItem(chart)
        }
    }
}

fun List<Album>.addToReleaseItem(charts: List<Chart>): List<Release> {
    return listOf(Release.AlbumItem(this)) + charts.map { chart ->
        Release.PlayListItem(chart)
    }
}