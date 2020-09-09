package tw.com.james.kkstream.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tw.com.james.kkstream.data.source.StreamRepository
import tw.com.james.kkstream.song.SongViewModel

@Suppress("UNCHECKED_CAST")
class SongViewModelFactory(
    private val repo: StreamRepository,
    private val songUrl: String): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            when{
                isAssignableFrom(SongViewModel::class.java) ->
                    SongViewModel(repo, songUrl)
                else -> throw IllegalArgumentException("Unknown ViewModel Class $modelClass")
            }
        } as T
}