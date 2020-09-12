package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.R
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.network.KKBOXAccountApiService
import tw.com.james.kkstream.network.KKBOXOpenApiService
import java.lang.Exception

class StreamRemoteDataSource(
    private val accountService: KKBOXAccountApiService,
    private val retrofitService: KKBOXOpenApiService,
    private val util: Util
) : StreamDataSource {

    override suspend fun getToken(): Result<TokenResult> {
        if (!util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = accountService.getToken()
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
        if (!util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = retrofitService.getChartPlaylists(token)
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getFeaturedPlaylists(token: String): Result<ChartResult> {
        if (!util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = retrofitService.getFeaturedPlaylists(token)
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getNewestAlbumMixed(token: String): Result<AlbumResult> {
        if (!util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = retrofitService.getNewestAlbumMixed(token)
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getTracks(
        token: String,
        domain: PlaylistDomain
    ): Result<PlaylistTracksResult> {
        if (!util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }
        return try {
            val result = retrofitService.getPlaylistTracks(
                token = token,
                domain = domain.text,
                id = domain.id
            )
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}