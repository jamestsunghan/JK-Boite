package tw.com.james.kkstream.ext

import androidx.fragment.app.Fragment
import tw.com.james.kkstream.StreamApp
import tw.com.james.kkstream.factory.ViewModelFactory

fun Fragment.getVMFactory(): ViewModelFactory{
    val repo = (requireContext().applicationContext as StreamApp).repo
    return ViewModelFactory(repo)
}