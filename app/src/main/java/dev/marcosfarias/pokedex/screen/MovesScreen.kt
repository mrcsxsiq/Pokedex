package dev.marcosfarias.pokedex.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import dev.marcosfarias.pokedex.R
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

object MovesScreen : KScreen<MovesScreen>() {

    override val layoutId: Int? = null
    override val viewClass: Class<*>? = null

    val pokedex = KTextView { withText("Pokedex") }


    val recyclerView = KRecyclerView(
        builder = { withId(R.id.recyclerView) },
        itemTypeBuilder = { itemType(::RvItem) }
    )

    class RvItem(matcher: Matcher<View>) : KRecyclerItem<RvItem>(matcher) {
        val textViewName = KTextView(matcher) { withId(R.id.textViewName) }
        val imageView = KImageView(matcher) { withId(R.id.imageView) }
        val textViewTitle = KTextView(matcher) { withId(R.id.textViewTitle) }
    }

    val settings = KButton { withId(R.id.sd_main_fab) }
    val allGen = KButton {
        withId(R.id.sd_label)
        withText("All Gen")
    }

    val generation = KTextView { withText("Generation") }
}

