package tw.com.james.kkstream.release.paging

import android.util.Log
import androidx.paging.PagingSource
import retrofit2.HttpException
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
                    val albums = KKApi.retrofitService.getIndieMusic(token)
                    listOf(Release.AlbumItem(albums = albums.data)) + items.data.map {
                        Release.PlayListItem(it)
                    }
                } else {
                    items.data.map { Release.PlayListItem(it) }
                },
                prevKey = if (initKey <= 0) null else initKey - 10,
                nextKey = if(items.data.size < 10) null else initKey + 10
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }
}