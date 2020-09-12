package tw.com.james.kkstream.data.source

import tw.com.james.kkstream.R
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.network.KKBOXOpenApi
import java.lang.Exception

class StreamRemoteDataSource(private val KKApi: KKBOXOpenApi) : StreamDataSource {

    override suspend fun getToken(): Result<TokenResult> {
        if (!Util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = KKApi.accountService.getToken()
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getChartPlaylists(token: String): Result<ChartResult> {
        if (!Util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = KKApi.retrofitService.getChartPlaylists(token)
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getFeaturedPlaylists(token: String): Result<ChartResult> {
        if (!Util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = KKApi.retrofitService.getFeaturedPlaylists(token)
            result.error?.let {
                return Result.Fail(it)
            }

            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun getNewestAlbumMixed(token: String): Result<AlbumResult> {
        if (!Util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }

        return try {
            val result = KKApi.retrofitService.getNewestAlbumMixed(token)
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
        if (!Util.isInternetConnected()) {
            return Result.Fail(StreamApp.instance.getString(R.string.no_internet))
        }
        return try {
            val result = KKApi.retrofitService.getPlaylistTracks(
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