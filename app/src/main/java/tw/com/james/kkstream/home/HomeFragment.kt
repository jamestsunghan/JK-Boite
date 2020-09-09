package tw.com.james.kkstream.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator

import tw.com.james.kkstream.R
import tw.com.james.kkstream.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var mediator: TabLayoutMediator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding = DataBindingUtil
            .inflate(inflater,R.layout.fragment_home, container, false)
        binding.lifecycleOwner = this

        binding.viewpagerList.adapter = HomePagerAdapter(requireActivity())

        mediator = TabLayoutMediator(binding.tabsList, binding.viewpagerList,
            TabLayoutMediator.TabConfigurationStrategy{ tab, position ->
                tab.text = HomePageType.values()[position].title
            }
        )

        mediator.attach()
        
        return binding.root
    }
}
