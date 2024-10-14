package dev.marcosfarias.pokedex

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.marcosfarias.pokedex.screen.MainScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MainScreenTest : TestCase(kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport()) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun checkingMainScreen() {
        run {
            MainScreen {
                search_text {
                    isDisplayed()
                }

                step("Check menu count") {
                    Assert.assertEquals(6, recyclerViewMenu.getSize())
                }

                step("Check menu elements visibility") {
                    recyclerViewMenu {
                        children<MainScreen.RvItemScreen> {
                            textViewName {
                                isDisplayed()
                                hasAnyText()
                            }
                        }
                    }
                }

                step("Check menu elements content") {
                    recyclerViewMenu {
                        childAt<MainScreen.RvItemScreen>(0) {
                            textViewName.hasText("Pokedex")
                        }
                        childAt<MainScreen.RvItemScreen>(1) {
                            textViewName.hasText("Moves")
                        }
                        childAt<MainScreen.RvItemScreen>(2) {
                            textViewName.hasText("Abilities")
                        }
                        childAt<MainScreen.RvItemScreen>(3) {
                            textViewName.hasText("Items")
                        }
                        childAt<MainScreen.RvItemScreen>(4) {
                            textViewName.hasText("Locations")
                        }
                        childAt<MainScreen.RvItemScreen>(5) {
                            textViewName.hasText("Type Charts")
                        }
                    }
                }

                step("Check news elements content") {
                    recyclerViewNews {
                        childAt<MainScreen.RvItemNews>(0) {
                            textViewNews.isDisplayed()
                            textViewNews.hasText("Pokémon Rumble Rush Arrives Soon")
                            imageViewNews.isDisplayed()
                        }
                        childAt<MainScreen.RvItemNews>(1) {
                            textViewNews.isDisplayed()
                            textViewNews.hasText("Pokémon Rumble Rush Arrives Soon")
                            imageViewNews.isDisplayed()
                        }
                    }
                }
            }
        }
    }
}