package com.thomas.datalayer

import com.thomas.datalayer.features.users.UsersAPI
import com.thomas.datalayer.features.users.UsersAPIDataSource
import com.thomas.datalayer.features.users.model.UserDTO
import com.thomas.fakes.FakeUserDTO
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class UsersAPIDataSourceTest {

    @MockK
    lateinit var usersAPI: UsersAPI

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers SHOULD call correct functions from usersAPI service`() = runBlocking {
        val fakeList = listOf(
            FakeUserDTO.mock(),
            FakeUserDTO.mock(),
            FakeUserDTO.mock()
        )

        coEvery {
            usersAPI.getUsers()
        } returns Response.success(fakeList)

        val result = UsersAPIDataSource(usersAPI).getUsers()

        coVerify {
            usersAPI.getUsers()
        }

        Assert.assertNull(result.error)
        Assert.assertNotNull(result.success)
        Assert.assertTrue(result.success is List<UserDTO>)
    }
}
