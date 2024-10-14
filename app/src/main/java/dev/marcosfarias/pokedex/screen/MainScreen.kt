package dev.marcosfarias.pokedex.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import dev.marcosfarias.pokedex.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MainScreen: KScreen<MainScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val search_text = KTextView { withId(R.id.search_text) }

    val recyclerViewMenu = KRecyclerView(
        builder = { withId(R.id.recyclerViewMenu) },
        itemTypeBuilder = { itemType(::RvItemScreen) }
    )

    class RvItemScreen(matcher: Matcher<View>) : KRecyclerItem<RvItemScreen>(matcher) {
        val textViewName = KTextView(matcher) { withId(R.id.textViewName) }
    }

    val recyclerViewNews = KRecyclerView(
        builder = { withId(R.id.recyclerViewNews) },
        itemTypeBuilder = { itemType(::RvItemNews) }
    )

    class RvItemNews(matcher: Matcher<View>) : KRecyclerItem<RvItemNews>(matcher) {
        val textViewNews = KTextView(matcher) { withId(R.id.textViewName) }
        val imageViewNews = KImageView(matcher) { withId(R.id.imageView) }
    }
}