package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import assertk.assertAll
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import com.ddaypunk.fetchrewardsexercise.core.client.ApiResponse
import com.ddaypunk.fetchrewardsexercise.hiring.core.ViewModelTest
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.hiring.data.repository.HiringDataRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MainScreenViewModelTest : ViewModelTest() {
    val mockRepository = mockk<HiringDataRepository>()
    val mockData = ApiResponse.Success(
        data = mapOf(
            "1" to listOf(
                HiringDataModel(
                    id = 2,
                    listId = 1,
                    name = "Hello"
                )
            ),
            "2" to listOf(
                HiringDataModel(
                    id = 6,
                    listId = 2,
                    name = "Dude"
                )
            ),
            "3" to listOf(
                HiringDataModel(
                    id = 5,
                    listId = 3,
                    name = "Sup"
                ),
                HiringDataModel(
                    id = 4,
                    listId = 3,
                    name = "there"
                )
            )
        )
    )

    @Nested
    @DisplayName("Given valid viewmodel with uiState at creation")
    inner class GivenValidViewModelWithUiStateAtCreation {
        @Test
        fun `when UiState is initially retrieved, then Loading state should be returned`() =
            runTest {
                val subject = MainScreenViewModel(mockRepository)
                coEvery { mockRepository.retrieve() } returns mockData
                assertThat(subject.uiState.first()).isInstanceOf(UiState.Loading::class)
            }
    }

    @Nested
    @DisplayName("Given valid viewmodel with uiState after initialization")
    inner class GivenValidViewModelWithUiStateAfterInitialization {
        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        fun `when UiState is retrieved after complete initialization, then Ready state should be returned with expected data`() =
            runTest {
                coEvery { mockRepository.retrieve() } returns mockData
                val subject = MainScreenViewModel(mockRepository)

                advanceUntilIdle()
                val states = (subject.uiState.value as UiState.Ready).cardStates
                states.forEachIndexed { index, state ->
                    when (index) {
                        0 -> assertAll {
                            with(state) {
                                assertThat(title).isEqualTo("1")
                                assertThat(isExpanded).isFalse()
                                assertThat(entries).isEqualTo(listOf("Hello"))
                            }
                        }

                        1 -> assertAll {
                            with(state) {
                                assertThat(title).isEqualTo("2")
                                assertThat(isExpanded).isFalse()
                                assertThat(entries).isEqualTo(listOf("Dude"))
                            }
                        }

                        2 -> assertAll {
                            with(state) {
                                assertThat(title).isEqualTo("3")
                                assertThat(isExpanded).isFalse()
                                assertThat(entries).isEqualTo(listOf("Sup", "there"))
                            }
                        }
                    }
                }
            }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        fun `and UiState is retrieved after complete initialization, when expanding a card, then Ready state should be returned with expected data`() =
            runTest {
                val subject = MainScreenViewModel(mockRepository)
                coEvery { mockRepository.retrieve() } returns mockData

                advanceUntilIdle()
                assertThat(subject.uiState.value).isInstanceOf(UiState.Ready::class)
                var cardOne = (subject.uiState.value as UiState.Ready).cardStates[0]
                cardOne.onClick.invoke()

                advanceUntilIdle()
                cardOne = (subject.uiState.value as UiState.Ready).cardStates[0]
                assertThat(cardOne.isExpanded).isTrue()
            }
    }
}