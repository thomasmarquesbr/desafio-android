package com.thomas.datalayer

import com.thomas.archtecture_framework.fakes.FakeResultWrapper
import com.thomas.datalayer.features.users.remote.UsersAPIDataSource
import com.thomas.datalayer.features.users.UsersRepositoryImpl
import com.thomas.datalayer.features.users.local.UsersLocalDataSource
import com.thomas.domainlayer.features.users.model.UserModel
import com.thomas.fakes.FakeUserDTO
import com.thomas.fakes.FakeUserEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersRepositoryImplTest {

    @MockK
    lateinit var usersAPIDataSource: UsersAPIDataSource

    @MockK
    lateinit var usersLocalDataSource: UsersLocalDataSource

    private fun instantiateRepository() = UsersRepositoryImpl(
        usersAPIDataSource,
        usersLocalDataSource
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers SHOULD call correct functions from usersAPIDataSource WHEN cache is true`() = runBlocking {
        val fakeList = listOf(
            FakeUserEntity.mock(),
            FakeUserEntity.mock(),
            FakeUserEntity.mock()
        )

        coEvery {
            usersLocalDataSource.getUsers()
        } returns FakeResultWrapper.mockSuccess(
            success = fakeList
        )

        val result = instantiateRepository().getUsers(fromCache = true)

        coVerify {
            usersLocalDataSource.getUsers()
        }

        coVerify(exactly = 0) {
            usersAPIDataSource.getUsers()
            usersLocalDataSource.saveUsers(any())
        }

        Assert.assertNull(result.error)
        Assert.assertNotNull(result.success)
        Assert.assertTrue(result.success is List<UserModel>)
    }

    @Test
    fun `getUsers SHOULD call correct functions from usersAPIDataSource WHEN cache is false`() = runBlocking {
         val fakeList = listOf(
             FakeUserDTO.mock(),
             FakeUserDTO.mock(),
             FakeUserDTO.mock()
         )

        coEvery {
            usersAPIDataSource.getUsers()
        } returns FakeResultWrapper.mockSuccess(
            success = fakeList
        )

        every {
            usersLocalDataSource.saveUsers(any())
        } returns Unit

        val result = instantiateRepository().getUsers(fromCache = false)

        coVerify {
            usersAPIDataSource.getUsers()
            usersLocalDataSource.saveUsers(any())
        }

        coVerify(exactly = 0) {
            usersLocalDataSource.getUsers()
        }

        Assert.assertNull(result.error)
        Assert.assertNotNull(result.success)
        Assert.assertTrue(result.success is List<UserModel>)
    }

}
