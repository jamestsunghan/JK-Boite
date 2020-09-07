package tw.com.james.kkstream.album

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentAlbumBinding
import tw.com.james.kkstream.ext.getVMFactory

class AlbumFragment : Fragment() {

    private val viewModel: AlbumViewModel by viewModels{ getVMFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAlbumBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_album, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}
