package tw.com.james.kkstream.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentPlaylistBinding

import tw.com.james.kkstream.ext.getVMFactory

class PlaylistFragment : Fragment() {

    private val viewModel: PlaylistViewModel by viewModels {
        getVMFactory(
            PlaylistFragmentArgs.fromBundle(requireArguments()).tracksKey
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentPlaylistBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_playlist, container, false)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        binding.recyclerPlaylist.adapter = PlaylistAdapter(PlaylistAdapter.OnClickListener{track->
            findNavController().navigate(
                PlaylistFragmentDirections.actionAlbumFragmentToSongFragment(track.url)
            )
        })

        return binding.root
    }

}
