package tw.com.james.kkstream.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.Track
import tw.com.james.kkstream.data.source.StreamRepository

class PlaylistViewModel(private val repo: StreamRepository, private val domain: PlaylistDomain) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    private val _trackList = MutableLiveData<List<TrackListItem>>()
    val trackList: LiveData<List<TrackListItem>>
        get() = _trackList

    init{
        getTracks(domain)
    }

    private fun getTracks(domain: PlaylistDomain){
        viewModelScope.launch {
            val result = repo.getTracks(token as String, domain).handleResultWith(_error, _status)
            result?.let{
                _trackList.value = listOf(TrackListItem.CoverItem(domain.cover)) + it.data.map{track->
                    TrackListItem.TrackItem(track)
                }
            }
        }
    }
}
