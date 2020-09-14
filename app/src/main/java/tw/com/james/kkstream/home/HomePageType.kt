package tw.com.james.kkstream.home

import androidx.fragment.app.Fragment
import tw.com.james.kkstream.R
import tw.com.james.kkstream.Util.getString
import tw.com.james.kkstream.ranking.RankingFragment
import tw.com.james.kkstream.release.ReleaseFragment

enum class HomePageType(val title: String, val fragment: Fragment) {
    NEW_RELEASE( getString(R.string.new_release_title), ReleaseFragment()),
    RANKING( getString(R.string.chart_title), RankingFragment())
}