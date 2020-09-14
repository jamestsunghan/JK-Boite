package tw.com.james.kkstream.song

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentSongBinding

class SongFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSongBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_song, container, false)

        binding.lifecycleOwner = this

        binding.songWeb.loadUrl(
            SongFragmentArgs.fromBundle(requireArguments()).songUrlKey
        )

        return binding.root
    }

}
