package com.ddaypunk.fetchrewardsexercise.hiring.presentation.screen

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.ddaypunk.fetchrewardsexercise.hiring.core.ViewModelTest
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import com.ddaypunk.fetchrewardsexercise.hiring.data.repository.HiringDataRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

class MainScreenViewModelTest : ViewModelTest() {
    val mockRepository = mockk<HiringDataRepository>()
    val mockData = mapOf(
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

    @Test
    fun firstViewModelTest() = runTest {
        coEvery { mockRepository.retrieve() } returns mockData
        val subject = MainScreenViewModel(mockRepository)
        assertThat(subject.uiState.value).isEqualTo(UiState.Loading)
    }
}