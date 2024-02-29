package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.ddaypunk.fetchrewardsexercise.hiring.presentation.component.ListCardState
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainScreenUITest {
    @get:Rule
    val composeTestRule = createComposeRule()

    val cardStates = listOf(
        ListCardState(
            title = "1",
            isExpanded = true,
            entries = listOf(
                "hello 1",
                "there 10"
            ),
            onClick = {}
        ),
        ListCardState(
            title = "2",
            isExpanded = false,
            entries = listOf(
                "Dude 2",
                "Sweet 200"
            ),
            onClick = {}
        ),
        ListCardState(
            title = "3",
            isExpanded = false,
            entries = listOf(
                "Higher 30",
                "Ground 300"
            ),
            onClick = {}
        )
    )

    @Test
    fun verifyMainScreenDisplays() {

        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ddaypunk.fetchrewardsexercise", appContext.packageName)

        with(composeTestRule) {
            setContent {
                MainScreenReady(cardStates = cardStates)
            }

            onNodeWithText("hello 1").assertIsDisplayed()
            onNodeWithText("there 10").assertIsDisplayed()
            onNodeWithText("Dude 2").assertDoesNotExist()
            onNodeWithText("Higher 30").assertDoesNotExist()
        }
    }
}