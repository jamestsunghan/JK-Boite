package tw.com.james.kkstream.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.com.james.kkstream.Util.token
import tw.com.james.kkstream.data.LoadStatus
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository

class AlbumViewModel(private val repo: StreamRepository, private val domain: PlaylistDomain) : ViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _status = MutableLiveData<LoadStatus>()
    val status: LiveData<LoadStatus>
        get() = _status

    init{
        getTracks(domain)
    }

    fun getTracks(domain: PlaylistDomain){
        viewModelScope.launch {
            val result = repo.getTracks(token as String, domain).handleResultWith(_error, _status)

        }
    }
}
