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
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.network.KKBOXOpenApi
import tw.com.james.kkstream.network.KKBOXOpenApiService

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StreamRemoteDataSourceTest {

    @Mock
    lateinit var kkApi: KKBOXOpenApi

    lateinit var remoteDataSource: StreamRemoteDataSource

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        remoteDataSource = StreamRemoteDataSource(kkApi)
    }

    @Test
    fun getToken_success() = runBlocking{
    }

    @Test
    fun getChartPlaylists_success() = runBlocking{
        val expected = ChartResult(listOf(), Paging(0,0,"",""), Summary(0))
        `when`(kkApi.retrofitService.getChartPlaylists("")).thenReturn(expected)
        val executed = remoteDataSource.getChartPlaylists("")
        assertEquals(0, (executed as Result.Success).data.data.size)
    }

    @Test
    fun getFeaturedPlaylists() {
    }

    @Test
    fun getNewestAlbumMixed() {
    }

    @Test
    fun getTracks() {
    }
}