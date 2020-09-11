package tw.com.james.kkstream.ranking

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.*
import tw.com.james.kkstream.data.source.StreamRepository
import java.lang.Exception


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
internal class RankingViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: StreamRepository

    lateinit var viewModel: RankingViewModel

    @Mock
    lateinit var errorObserver: Observer<String>

    @Mock
    lateinit var chartListObserver: Observer<List<Chart>>

    @Mock
    lateinit var tokenObserver: Observer<String>

    val testDispatcher = TestCoroutineDispatcher()
    val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = RankingViewModel(repository)
        viewModel.error.observeForever(errorObserver)
        viewModel.chartList.observeForever(chartListObserver)
        token.observeForever(tokenObserver)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun getChartList_success() = testDispatcher.runBlockingTest {
        val expected = ChartResult(listOf(), Paging(0,0,"",""), Summary(0))
        `when`(repository.getChartPlaylists("")).thenReturn(Result.Success(expected))
        viewModel.getChart("")
        verify(chartListObserver, times(1)).onChanged(ArgumentMatchers.isNotNull())
    }

    @Test
    fun getChartList_fail() = testDispatcher.runBlockingTest {
        val expected = "error"
        `when`(repository.getChartPlaylists("")).thenReturn(Result.Fail(expected))
        viewModel.getChart("")
        verify(errorObserver, times(1)).onChanged(ArgumentMatchers.isNotNull())
    }

    @Test
    fun getToken_success() = testDispatcher.runBlockingTest {
        val expected = TokenResult("","type",100)
        `when`(repository.getToken()).thenReturn(Result.Success(expected))
        viewModel.getToken()
        verify(tokenObserver, times(1)).onChanged(ArgumentMatchers.isNotNull())
    }

    @Test
    fun getToken_error() = testDispatcher.runBlockingTest {
        val expected = "error"
        `when`(repository.getToken()).thenReturn(Result.Error(Exception(expected)))
        viewModel.getToken()
        verify(errorObserver, times(1)).onChanged(ArgumentMatchers.isNotNull())
    }
}