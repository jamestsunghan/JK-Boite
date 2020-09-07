package tw.com.james.kkstream.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class HomePagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return HomePageType.values().size
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            HomePageType.NEW_RELEASE.pos -> HomePageType.NEW_RELEASE.fragment
            HomePageType.RANKING.pos     -> HomePageType.RANKING.fragment
            else                         -> HomeFragment()
        }
    }
}