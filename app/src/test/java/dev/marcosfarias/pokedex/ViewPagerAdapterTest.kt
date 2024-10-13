package dev.marcosfarias.pokedex

import android.content.Context
import androidx.fragment.app.FragmentManager
import dev.marcosfarias.pokedex.ui.dashboard.ViewPagerAdapter
import dev.marcosfarias.pokedex.ui.dashboard.moves.MovesFragment
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import kotlin.random.Random

class ViewPagerAdapterTest {

    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var fragmentManager: FragmentManager
    private val mockContext: Context = mockk(relaxed = true)
    private val pokemonId = Random.nextInt(5)

    @Before
    fun setUp() {
        fragmentManager = mockk(relaxed = true)
        viewPagerAdapter = ViewPagerAdapter(fragmentManager, mockContext, pokemonId.toString())
    }

    @Test
    fun `check correct number of pages`() {
        val expectedCount = 4
        assertEquals(expectedCount, viewPagerAdapter.count)
    }

    @Test
    fun `check correct fragment for MovesFragment`() {
        val fragment = viewPagerAdapter.getItem(3)
        assert(fragment is MovesFragment) // Проверяем, что это именно MovesFragment
    }
}
