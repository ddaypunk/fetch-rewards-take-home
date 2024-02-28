package com.ddaypunk.fetchrewardsexercise.hiring.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNotNull
import com.ddaypunk.fetchrewardsexercise.hiring.core.fake.FakeApiClient
import com.ddaypunk.fetchrewardsexercise.hiring.data.model.HiringDataModel
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


class HiringDataRepositoryTest {

    @Nested
    @DisplayName("Given a fake api client returning valid data")
    inner class GivenFakeApiClientWithData {
        private val subject = HiringDataRepository(FakeApiClient())

        @Test
        fun `when invoking retrieve, then the received data has correctly sorted unique keys`() =
            runTest {
                val actual = subject.retrieve().data?.keys

                assertThat(actual).isEqualTo(setOf("1", "2", "3"))
            }

        @Test
        fun `when invoking retrieve, then received data contains no blank names`() = runTest {
            val actual = subject.retrieve().data?.get("1")

            assertThat(actual).isNotNull().isEqualTo(
                listOf(
                    HiringDataModel(
                        id = 2,
                        listId = 1,
                        name = "Hello"
                    )
                )
            )
        }

        @Test
        fun `when invoking retrieve, then received data contains no null names`() = runTest {
            val actual = subject.retrieve().data?.get("2")

            assertThat(actual).isNotNull().isEqualTo(
                listOf(
                    HiringDataModel(
                        id = 6,
                        listId = 2,
                        name = "Dude"
                    )
                )
            )
        }

        @Test
        fun `when invoking retrieve, then received data is ordered by name`() = runTest {
            val actual = subject.retrieve().data?.get("3")

            assertThat(actual).isNotNull().isEqualTo(
                listOf(
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
        }
    }
}