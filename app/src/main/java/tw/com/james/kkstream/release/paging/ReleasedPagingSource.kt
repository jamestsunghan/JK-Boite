package tw.com.james.kkstream.release.paging

import android.util.Log
import androidx.paging.PagingSource
import retrofit2.HttpException
import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.network.KKBOXOpenApi
import tw.com.james.kkstream.release.Release
import java.io.IOException

class ReleasedPagingSource(
    private val KKApi: KKBOXOpenApi,
    private val token: String
) : PagingSource<Int, Release>() {


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Release> {
        Log.d("JJ", "paging source loading")
        return try {
            val initKey = if (params is LoadParams.Append) params.key else 0

            val items = KKApi.retrofitService.getFeaturedPlaylists(
                token = token,
                offset = initKey
            )

            Log.d("JJJ", "items $items")
            LoadResult.Page(
                data = if (initKey == 0) {

                    listOf(Release.AlbumItem(albums = getTenAlbums())) + items.data.map {
                        Release.PlayListItem(it)
                    }
                } else {
                    items.data.map { Release.PlayListItem(it) }
                },
                prevKey = if (initKey <= 0) null else initKey - PLAYLIST_PAGE_SIZE,
                nextKey = if (items.data.size < PLAYLIST_PAGE_SIZE) null else initKey + PLAYLIST_PAGE_SIZE
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private suspend fun getTenAlbums(): List<Album>{
        val albums = KKApi.retrofitService.getNewestAlbumMixed(token)
        var list = albums.data
        var offset = 0
        while (list.size < ALBUM_LIST_SIZE) {
            offset = if (list.isNullOrEmpty()) offset + ALBUM_LIST_SIZE else ALBUM_LIST_SIZE - list.size
            val add = KKApi.retrofitService.getNewestAlbumMixed(token, offset = offset)
            list = add.data
        }
        return list
    }

    companion object{
        private const val ALBUM_LIST_SIZE    = 10
        private const val PLAYLIST_PAGE_SIZE = 10
    }
}