package tw.com.james.kkstream.data.source

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
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
import java.lang.Exception


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DefaultRepositoryTest {

    @Mock
    lateinit var openApi: KKBOXOpenApi

    @Mock
    lateinit var remote: StreamDataSource

    lateinit var repo: StreamRepository


    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repo = DefaultStreamRepository(remote, openApi)
    }

    @Test
    fun getToken_success() = runBlockingTest {
        val expected = TokenResult("happy", "type", 100)
        `when`(remote.getToken()).thenReturn(Result.Success(expected))
        val executed = repo.getToken()
        assertEquals("happy", (executed as Result.Success).data.token)
    }

    @Test
    fun getToken_fail() = runBlockingTest {
        val expected = TokenResult(error = "nothing")
        `when`(remote.getToken()).thenReturn(Result.Fail(expected.error as String))
        val executed = repo.getToken()
        assertEquals("nothing", (executed as Result.Fail).error)
    }

    @Test
    fun getToken_error() = runBlockingTest {
        val expected = Exception("Erroring")
        `when`(remote.getToken()).thenReturn(Result.Error(expected))
        val executed = repo.getToken()
        assertEquals(expected, (executed as Result.Error).exception)
    }

    @Test
    fun getChartPlaylists_success() = runBlockingTest {
        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0))
        `when`(remote.getChartPlaylists("")).thenReturn(Result.Success(expected))
        val executed = repo.getChartPlaylists("")
        assertEquals(0, (executed as Result.Success).data.data.size)
    }

    @Test
    fun getFeaturedPlaylists_fail() = runBlockingTest {
        val expected = ChartResult(listOf(), Paging(0, 0, "", ""), Summary(0), "nothing")
        `when`(remote.getChartPlaylists("")).thenReturn(Result.Fail(expected.error as String))
        val executed = repo.getChartPlaylists("")
        assertEquals("nothing", (executed as Result.Fail).error)
    }

    @Test
    fun getIndieMusic_success() = runBlockingTest {
        val expected = AlbumResult(listOf(), Paging(0, 0, "", ""), Summary(0))
        `when`(remote.getNewestAlbumMixed("")).thenReturn(Result.Success(expected))
        val executed = repo.getNewestAlbumMixed("")
        assertEquals(0, (executed as Result.Success).data.data.size)
    }

    @Test
    fun getIndieMusic_error() = runBlockingTest {
        val expected = Exception("Erroring")
        `when`(remote.getNewestAlbumMixed("")).thenReturn(Result.Error(expected))
        val executed = repo.getNewestAlbumMixed("")
        assertEquals(expected, (executed as Result.Error).exception)
    }

    @Test
    fun getTracks_success() = runBlockingTest {
        val expected = PlaylistTracksResult(listOf(), Paging(0, 0, "", ""), Summary(0))
        `when`(remote.getTracks("", PlaylistDomain.ALBUM)).thenReturn(Result.Success(expected))
        val executed = repo.getTracks("", PlaylistDomain.ALBUM)
        assertEquals(0, (executed as Result.Success).data.data.size)
    }
}