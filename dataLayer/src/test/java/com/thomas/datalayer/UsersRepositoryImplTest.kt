package com.thomas.datalayer

import com.thomas.archtecture_framework.fakes.FakeResultWrapper
import com.thomas.datalayer.features.users.UsersAPIDataSource
import com.thomas.datalayer.features.users.UsersRepositoryImpl
import com.thomas.domainlayer.features.users.model.UserModel
import com.thomas.fakes.FakeUserDTO
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersRepositoryImplTest {

    @MockK
    lateinit var usersAPIDataSource: UsersAPIDataSource

    private fun instantiateRepository() = UsersRepositoryImpl(
        usersAPIDataSource
    )

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers SHOULD call correct functions from usersAPIDataSource`() = runBlocking {
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

        val result = instantiateRepository().getUsers()

        coVerify {
            usersAPIDataSource.getUsers()
        }

        Assert.assertNull(result.error)
        Assert.assertNotNull(result.success)
        Assert.assertTrue(result.success is List<UserModel>)
    }

}
