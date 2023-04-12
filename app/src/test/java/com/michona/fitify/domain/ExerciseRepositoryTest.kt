package com.michona.fitify.domain

import com.michona.fitify.domain.data.dtos.ExerciseDetail
import com.michona.fitify.domain.data.dtos.ExerciseDetailContainer
import com.michona.fitify.domain.data.dtos.ExercisePack
import com.michona.fitify.domain.data.dtos.ExerciseToolSet
import com.michona.fitify.domain.local.api.ExercisesDao
import com.michona.fitify.domain.remote.api.ExerciseDetailApi
import com.michona.fitify.domain.remote.api.ExercisePackApi
import com.michona.fitify.domain.repository.ExerciseRepository
import com.michona.fitify.domain.repository.InstructionRepository
import com.michona.fitify.domain.usecase.BuildExerciseModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ExerciseRepositoryTest {

    @MockK
    private lateinit var packApi: ExercisePackApi

    @MockK
    private lateinit var exerciseDetailApi: ExerciseDetailApi

    @MockK
    private lateinit var exercisesDao: ExercisesDao

    @MockK
    private lateinit var buildExerciseModel: BuildExerciseModel

    @MockK
    private lateinit var instructionRepository: InstructionRepository

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check if fetch calls apis and local dao`() = runTest(UnconfinedTestDispatcher()) {
        val repo = ExerciseRepository(packApi, exerciseDetailApi, exercisesDao, buildExerciseModel, instructionRepository, UnconfinedTestDispatcher())

        coEvery { packApi.getPacks() } returns Response.success(
            ExerciseToolSet(
                tools = listOf(
                    ExercisePack(code = "pack_id", count = 0, size = 10),
                    ExercisePack(code = "pack_id", count = 0, size = 10),
                ),
            ),
        )
        coEvery { exerciseDetailApi.getExercisesFrom(any()) } returns Response.success(
            ExerciseDetailContainer(
                exercises = listOf(
                    ExerciseDetail(id = "ex_id", title = "title", instructions = null),
                    ExerciseDetail(id = "ex_id_2", title = "title", instructions = null),
                ),
            ),
        )

        coEvery { exercisesDao.upsertAll(any()) } just runs

        repo.fetch()

        coVerify(exactly = 1) { packApi.getPacks() }
        coVerify(exactly = 2) { exerciseDetailApi.getExercisesFrom("pack_id") }
        coVerify(exactly = 1) { exercisesDao.upsertAll(*anyVararg()) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `check state if network call fails`() = runTest(UnconfinedTestDispatcher()) {
        val repo = ExerciseRepository(packApi, exerciseDetailApi, exercisesDao, buildExerciseModel, instructionRepository, UnconfinedTestDispatcher())

        coEvery { packApi.getPacks() } returns Response.error(400, "".toResponseBody())

        repo.fetch()

        coVerify(exactly = 1) { packApi.getPacks() }
        coVerify(exactly = 0) { exerciseDetailApi.getExercisesFrom(any()) }
        coVerify(exactly = 0) { exercisesDao.upsertAll(*anyVararg()) }
    }
}
