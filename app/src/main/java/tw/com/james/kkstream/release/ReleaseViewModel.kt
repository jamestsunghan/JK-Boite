package tw.com.james.kkstream.release

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository
import tw.com.james.kkstream.ext.addToReleaseItem
import tw.com.james.kkstream.ext.toReleaseItem
import tw.com.james.kkstream.network.KKBOXOpenApi
import tw.com.james.kkstream.release.paging.ReleasedPagingSource

class ReleaseViewModel(private val repo: StreamRepository) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _chartList = MutableLiveData<List<Chart>>()
    val chartList: LiveData<List<Chart>>
        get() = _chartList

    private val _fPlayList = MutableLiveData<List<Album>>()
    val fPlayList: LiveData<List<Album>>
        get() = _fPlayList

    private val _tracksDomain = MutableLiveData<PlaylistDomain>()
    val tracksDomain: LiveData<PlaylistDomain>
        get() = _tracksDomain

    private val _albumSelected = MutableLiveData<Album>()
    val albumSelected: LiveData<Album>
        get() = _albumSelected

    var releaseFlow : Flow<PagingData<Release>> = flowOf()

    val releaseList = MediatorLiveData<List<Release>>().apply {
        addSource(chartList) {
            it?.let { charts ->
                value = charts.toReleaseItem(fPlayList.value ?: listOf())
            }
        }
        addSource(fPlayList) {
            it?.let { albums ->
                value = albums.addToReleaseItem(chartList.value ?: listOf())
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        releaseList.removeSource(chartList)
        releaseList.removeSource(fPlayList)
    }

    init {
        if (token.value == null) {
            getToken()
        }
    }

    private fun pagingRelease(token: String){

        Log.d("JJJ","token $token")

        releaseFlow = Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 11
            )
        ){
            ReleasedPagingSource(KKBOXOpenApi, token)
        }.flow
        Util.token.value = token
    }

    private fun getToken() {
        viewModelScope.launch {

            val result = repo.getToken().handleResultWith(_error, _status)

            result?.let {

                pagingRelease(result.type + " " + result.token)

            }
        }
    }

    fun watchTracks(domain: PlaylistDomain) {
        _tracksDomain.value = domain
    }

}
