package tw.com.james.kkstream.release

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository

class ReleaseViewModel(private val repo: StreamRepository) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _tracksDomain = MutableLiveData<PlaylistDomain>()
    val tracksDomain: LiveData<PlaylistDomain>
        get() = _tracksDomain

    var releaseFlow : Flow<PagingData<Release>> = flowOf()

    init {
        if (token.value == null) {
            getToken()
        }
    }

    private fun pagingRelease(token: String){

        Log.d("JJJ","token $token")

        releaseFlow = repo.getPagingChartPlaylists(token)

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

    fun navigationComplete() {
        _tracksDomain.value = null
    }

}
