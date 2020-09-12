package tw.com.james.kkstream.data.source

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.network.KKBOXAccountApiService
import tw.com.james.kkstream.network.KKBOXOpenApi
import tw.com.james.kkstream.network.KKBOXOpenApiService

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StreamRemoteDataSourceTest {

    @Mock
    lateinit var retrofitService: KKBOXOpenApiService

    @Mock
    lateinit var accountService: KKBOXAccountApiService

    @Mock
    lateinit var util: Util

    lateinit var remoteDataSource: StreamRemoteDataSource

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        `when`(util.isInternetConnected()).thenReturn(true)
        remoteDataSource = StreamRemoteDataSource(accountService, retrofitService, util)
    }


    @Test
    fun getChartPlaylists_success() = runBlocking {

        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0))

        `when`(retrofitService.getChartPlaylists("")).thenReturn(expected)

        assertEquals(0, (remoteDataSource.getChartPlaylists("") as Result.Success).data.data.size)
    }

    @Test
    fun getChartPlaylists_fail() = runBlocking {

        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0), "error")

        `when`(retrofitService.getChartPlaylists("")).thenReturn(expected)

        assertEquals("error", (remoteDataSource.getChartPlaylists("") as Result.Fail).error)
    }

    @Test
    fun getFeaturedPlaylists_success() = runBlocking {

        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0))

        `when`(retrofitService.getFeaturedPlaylists("")).thenReturn(expected)

        assertEquals(expected, (remoteDataSource.getFeaturedPlaylists("") as Result.Success).data)
    }

    @Test
    fun getFeaturedPlaylists_fail() = runBlocking {
        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0), "error")
        `when`(retrofitService.getFeaturedPlaylists("")).thenReturn(expected)
        val executed = remoteDataSource.getFeaturedPlaylists("")
        assertEquals("error", (executed as Result.Fail).error)
    }

    @Test
    fun getNewestAlbumMixed_success() = runBlocking {
        val expected = AlbumResult(listOf(), Paging(0, 0, "", ""), Summary(0))
        `when`(retrofitService.getNewestAlbumMixed("")).thenReturn(expected)
        val executed = remoteDataSource.getNewestAlbumMixed("")
        assertEquals(0, (executed as Result.Success).data.data.size)
    }

    @Test
    fun getNewestAlbumMixed_fail() = runBlocking {
        val expected = AlbumResult(listOf(), Paging(0, 0, "", ""), Summary(0), "error")
        `when`(retrofitService.getNewestAlbumMixed("")).thenReturn(expected)
        val executed = remoteDataSource.getNewestAlbumMixed("")
        assertEquals("error", (executed as Result.Fail).error)
    }

    @Test
    fun getTracks_success() = runBlocking {
        val expected = PlaylistTracksResult()
        `when`(retrofitService.getPlaylistTracks("", "charts", "")).thenReturn(expected)
        val executed = remoteDataSource.getTracks("", PlaylistDomain.CHART)
        assertEquals(0, (executed as Result.Success).data.data.size)
    }

    @Test
    fun getTracks_fail() = runBlocking {
        val expected = PlaylistTracksResult(error = "error")
        `when`(retrofitService.getPlaylistTracks("", "charts", "")).thenReturn(expected)
        val executed = remoteDataSource.getTracks("", PlaylistDomain.CHART)
        assertEquals("error", (executed as Result.Fail).error)
    }
}