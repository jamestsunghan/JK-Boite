package tw.com.james.kkstream.ext

import androidx.fragment.app.Fragment
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.data.PlaylistDomain
import tw.com.james.kkstream.factory.AlbumViewModelFactory
import tw.com.james.kkstream.factory.ViewModelFactory

fun Fragment.getVMFactory(): ViewModelFactory{
    val repo = (requireContext().applicationContext as StreamApp).repo
    return ViewModelFactory(repo)
}

fun Fragment.getVMFactory(domain: PlaylistDomain): AlbumViewModelFactory {
    val repo = (requireContext().applicationContext as StreamApp).repo
    return AlbumViewModelFactory(repo, domain)
}