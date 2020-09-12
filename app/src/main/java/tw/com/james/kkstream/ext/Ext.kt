package tw.com.james.kkstream.ext

import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.data.PlaylistDomain

fun PlaylistDomain.addInfo(chart: Chart): PlaylistDomain{
    return this.apply {
        id = chart.id
        cover = chart.images.last().url
    }
}

fun PlaylistDomain.addInfo(albumItem: Album): PlaylistDomain{
    return this.apply {
        id = albumItem.id
        cover = albumItem.images.last().url
        album = albumItem
    }
}