package dev.marcosfarias.pokedex

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.marcosfarias.pokedex.database.dao.PokemonDAO
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.ui.dashboard.DashboardViewModel
import io.mockk.*
import junit.framework.TestCase.assertEquals
import org.junit.*

class DashboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dao: PokemonDAO = mockk(relaxed = true)
    private lateinit var viewModel: DashboardViewModel

    @Before
    fun before() {
        viewModel = DashboardViewModel(pokemonDAO = dao)
    }

    @Test
    fun `GIVEN id WHEN request pokemon THEN get pokemon as result and call only 1 request`() {
        // GIVEN
        val input = "123"
        val expected: LiveData<Pokemon> = mockk(relaxed = true)
        every { dao.getById(input) } returns expected

        // WHEN
        val result = viewModel.getPokemonById(id = input)

        // THEN
        verify(exactly = 1) { dao.getById(input) }
        confirmVerified(dao)
        Assert.assertEquals(expected.value, result.value)
    }

    @Test
    fun `GIVEN a list of ids WHEN request pokemon evolutions THEN get a list of pokemons and call only 1 request`() {
        // GIVEN
        val input = listOf("123", "321")
        val pikachu = Pokemon().apply { name = "Pikachu" }
        val squirtle = Pokemon().apply { name = "Squirtle" }
        val expected = MutableLiveData(listOf(pikachu, squirtle))
        every { dao.getEvolutionsByIds(input) } returns expected

        // WHEN
        val result = viewModel.getPokemonEvolutionsByIds(ids = input)

        // THEN
        verify(exactly = 1) { dao.getEvolutionsByIds(input) }
        confirmVerified(dao)
        Assert.assertEquals(expected, result)
    }


    @Test
    fun `check return Pokemon from DAO`() {
        // Given
        val pokemonId = "1"
        val expectedPokemon = Pokemon(id = pokemonId, name = "Bulbasaur")
        every { dao.getById(pokemonId) } returns MutableLiveData(expectedPokemon)

        // When
        val result = viewModel.getPokemonById(pokemonId)

        // Then
        assertEquals(expectedPokemon, result.value)
    }

    @Test
    fun `check return Pokemon list from DAO`() {
        // Given
        val pokemonIds = listOf("1", "2", "3")
        val expectedPokemonList = listOf(
            Pokemon(id = "1", name = "Bulbasaur"),
            Pokemon(id = "2", name = "Ivysaur"),
            Pokemon(id = "3", name = "Venusaur")
        )
        every { dao.getEvolutionsByIds(pokemonIds) } returns MutableLiveData(
            expectedPokemonList
        )

        // When
        val result = viewModel.getPokemonEvolutionsByIds(pokemonIds)

        // Then
        assertEquals(expectedPokemonList, result.value)
    }

    companion object {
        @JvmStatic
        @AfterClass
        fun tearDown() {
            unmockkAll()
        }
    }
}
