package tw.com.james.kkstream.release

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.Album
import tw.com.james.kkstream.data.Chart
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository
import tw.com.james.kkstream.ext.addToReleaseItem
import tw.com.james.kkstream.ext.toReleaseItem

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
        if (token == null) {
            getToken()
        } else {
            getIndieMusic(token as String)
            getFeaturedPlaylists(token as String)
        }
    }

    private fun getFeaturedPlaylists(token: String) {
        viewModelScope.launch {
            val result = repo.getFeaturedPlaylists(token).handleResultWith(_error, _status)

            Log.d("JJ", "feature list ${result?.data}")

            result?.let {
                _chartList.value = result.data
            }
            Log.d("JJ", "${result?.data}")

        }
    }

    fun getIndieMusic(token: String) {
        viewModelScope.launch {
            val result = repo.getIndieMusic(token).handleResultWith(_error, _status)

            Log.d("JJ", "indie ${result?.data}")

            result?.let {
                _fPlayList.value = result.data
            }
        }
    }

    private fun getToken() {
        viewModelScope.launch {

            val result = repo.getToken().handleResultWith(_error, _status)

            result?.let {
                token = result.type + " " + result.token
                getIndieMusic(token as String)
                getFeaturedPlaylists(token as String)
            }
        }
    }

    fun watchTracks(domain: PlaylistDomain) {
        _tracksDomain.value = domain
    }

}
