package tw.com.james.kkstream.home

import androidx.fragment.app.Fragment
import tw.com.james.kkstream.ranking.RankingFragment
import tw.com.james.kkstream.release.ReleaseFragment

enum class HomePageType(val pos: Int, val title: String, val fragment: Fragment) {
    NEW_RELEASE(0, "新發行", ReleaseFragment()),
    RANKING(1, "排行榜", RankingFragment())
}