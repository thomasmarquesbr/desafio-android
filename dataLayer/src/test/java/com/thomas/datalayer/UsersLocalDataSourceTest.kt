package com.thomas.datalayer

import com.thomas.datalayer.di.AppDatabase
import com.thomas.datalayer.features.users.local.UsersLocalDataSource
import com.thomas.datalayer.features.users.local.model.UserEntity
import com.thomas.fakes.FakeUserEntity
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class UsersLocalDataSourceTest {

    @MockK
    lateinit var appDatabase: AppDatabase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers SHOULD call correct getAll from usersDAO in database`() = runBlocking {
        val fakeList = listOf(
            FakeUserEntity.mock(),
            FakeUserEntity.mock(),
            FakeUserEntity.mock()
        )

        coEvery {
            appDatabase.userDao().getAll()
        } returns fakeList

        val result = UsersLocalDataSource(appDatabase).getUsers()

        coVerify {
            appDatabase.userDao().getAll()
        }

        Assert.assertNull(result.error)
        Assert.assertNotNull(result.success)
        Assert.assertTrue(result.success is List<UserEntity>)
    }

    @Test
    fun `getUsers SHOULD call correct deleteAll and insertAll from usersDAO in database`() = runBlocking {
        val fakeList = listOf(
            FakeUserEntity.mock(),
            FakeUserEntity.mock(),
            FakeUserEntity.mock()
        )

        coEvery {
            appDatabase.userDao().deleteAll()
        } returns Unit

        coEvery {
            appDatabase.userDao().insertAll(any())
        } returns Unit

        val result = UsersLocalDataSource(appDatabase).saveUsers(fakeList)

        coVerify {
            appDatabase.userDao().deleteAll()
            appDatabase.userDao().insertAll(any())
        }
    }

}
