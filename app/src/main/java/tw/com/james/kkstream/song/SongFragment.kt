package tw.com.james.kkstream.song

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentSongBinding
import tw.com.james.kkstream.ext.getVMFactory

class SongFragment : Fragment() {

//    private val viewModel: SongViewModel by viewModels{ getVMFactory() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSongBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_song, container, false)

        binding.lifecycleOwner = this

//        binding.viewModel = viewModel

        binding.songWeb.loadUrl(
            SongFragmentArgs.fromBundle(requireArguments()).songUrlKey
        )

        return binding.root
    }

}
