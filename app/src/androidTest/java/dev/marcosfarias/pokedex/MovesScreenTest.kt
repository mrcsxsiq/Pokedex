package dev.marcosfarias.pokedex

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.rule.GrantPermissionRule
import com.kaspersky.components.alluresupport.withForcedAllureSupport
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import dev.marcosfarias.pokedex.screen.MainScreen
import dev.marcosfarias.pokedex.screen.MovesScreen
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

class MovesScreenTest : TestCase(kaspressoBuilder = Kaspresso.Builder.withForcedAllureSupport()) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
        android.Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun movesScreenTest() {
        run {
            MainScreen.recyclerViewMenu.childAt<MainScreen.RvItemScreen>(1) {
                click()
            }

            MovesScreen {
                pokedex.isDisplayed()

                step("Check menu elements content") {
                    recyclerView {
                        childAt<MovesScreen.RvItem>(0) {
                            textViewName.isDisplayed()
                            textViewName.hasText("Bulbasaur")
                        }
                        childAt<MovesScreen.RvItem>(1) {
                            textViewName.isDisplayed()
                            textViewName.hasText("Ivysaur")
                        }
                        childAt<MovesScreen.RvItem>(2) {
                            textViewName.isDisplayed()
                            textViewName.hasText("Venusaur")
                        }
                    }
                }

                step("Swipe") {

                    recyclerView.isDisplayed()

                    val height = device.uiDevice.displayHeight
                    val width = device.uiDevice.displayWidth
                    device.uiDevice.swipe(width / 2, height / 2, width / 2, 0, 200)


                    recyclerView {
                        device.uiDevice.waitForIdle()
                        childAt<MovesScreen.RvItem>(20) {
                            textViewName {
                                isDisplayed()
                                hasAnyText()
                                hasNoText("Bulbasaur")
                                hasNoText("Ivysaur")
                                hasNoText("Venusaur")
                                hasNoText("Charmander")
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun checkingAllGenTest() {
        run {
            MainScreen.recyclerViewMenu.childAt<MainScreen.RvItemScreen>(1) {
                click()
            }

            MovesScreen {
                pokedex.isDisplayed()
                settings.click()
                allGen.click()

                step("Check menu count") {
                    Assert.assertEquals(8, recyclerView.getSize())
                }

                step("Check menu elements visibility") {
                    recyclerView {
                        children<MovesScreen.RvItem> {
                            textViewTitle {
                                isDisplayed()
                                hasAnyText()
                            }
                        }
                    }
                }

                step("Check menu elements content") {
                    recyclerView {
                        childAt<MovesScreen.RvItem>(0) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation I")
                        }
                        childAt<MovesScreen.RvItem>(1) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation II")
                        }
                        childAt<MovesScreen.RvItem>(2) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation III")
                        }
                        childAt<MovesScreen.RvItem>(3) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation IV")
                        }
                        childAt<MovesScreen.RvItem>(4) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation V")
                        }
                        childAt<MovesScreen.RvItem>(5) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation VI")
                        }
                        childAt<MovesScreen.RvItem>(6) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation VII")
                        }
                        childAt<MovesScreen.RvItem>(7) {
                            textViewTitle.isDisplayed()
                            textViewTitle.hasText("Generation VIII")
                        }
                    }
                }
            }
        }
    }
}