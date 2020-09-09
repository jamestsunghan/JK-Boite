package tw.com.james.kkstream.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import tw.com.james.kkstream.playlist.PlaylistViewModel
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.data.source.StreamRepository

@Suppress("UNCHECKED_CAST")
class AlbumViewModelFactory(
    private val repo: StreamRepository,
    private val domain: PlaylistDomain
): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass){
            when{
                isAssignableFrom(PlaylistViewModel::class.java) ->
                    PlaylistViewModel(repo, domain)
                else -> throw IllegalArgumentException("Unknown ViewModel Class ${modelClass.name}")
            }
        } as T
}