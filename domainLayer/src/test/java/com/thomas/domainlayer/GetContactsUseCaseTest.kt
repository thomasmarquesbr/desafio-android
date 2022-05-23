package com.thomas.domainlayer

import com.thomas.archtecture_framework.fakes.FakeResultWrapper
import com.thomas.archtecture_framework.wrapper.ErrorWrapper
import com.thomas.domainlayer.base.ErrorDetailModel
import com.thomas.domainlayer.features.users.GetContactsFailureFactory
import com.thomas.domainlayer.features.users.GetContactsUseCase
import com.thomas.domainlayer.features.users.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetContactsUseCaseTest {

    @MockK
    lateinit var usersRepository: UsersRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @Test
    fun `usecase SHOULD call correct functions WHEN runAsync is called`() = runBlocking {
        val fakeList = listOf(
            FakeUserModel.mock(),
            FakeUserModel.mock(),
            FakeUserModel.mock()
        )

        coEvery {
            usersRepository.getUsers()
        } returns FakeResultWrapper.mockSuccess(
            success = fakeList
        )
        val result = GetContactsUseCase(
            usersRepository
        ).runAsync()

        coVerifySequence {
            usersRepository.getUsers()
        }

        Assert.assertNull(result.error)
        Assert.assertEquals(fakeList, result.success)
    }

    @Test
    fun `should return base failure when repository return failure with a message`() =
        runBlocking {
            val fakeError = ErrorWrapper(
                errorBody = ErrorDetailModel(
                    message = "message",
                )
            )

            coEvery {
                usersRepository.getUsers()
            } returns FakeResultWrapper.mockError(
                error = fakeError
            )

            val result = GetContactsUseCase(
                usersRepository
            ).runAsync()

            coVerifySequence {
                usersRepository.getUsers()
            }

            Assert.assertNull(result.success)
            Assert.assertTrue(result.error is GetContactsFailureFactory.BaseFailure)
        }

    @Test
    fun `should return failure when repository return failure with custom error 1`() =
        runBlocking {
            val fakeError = ErrorWrapper(
                errorBody = ErrorDetailModel(
                    message = "message",
                    status = "1001"
                ),
            )

            coEvery {
                usersRepository.getUsers()
            } returns FakeResultWrapper.mockError(
                error = fakeError
            )

            val result = GetContactsUseCase(
                usersRepository
            ).runAsync()

            coVerifySequence {
                usersRepository.getUsers()
            }

            Assert.assertNull(result.success)
            Assert.assertTrue(result.error is GetContactsFailureFactory.CustomError1Failure)
        }

    @Test
    fun `should return failure when repository return failure with custom error 2`() =
        runBlocking {
            val fakeError = ErrorWrapper(
                errorBody = ErrorDetailModel(
                    message = "message",
                    status = "1002"
                ),
            )

            coEvery {
                usersRepository.getUsers()
            } returns FakeResultWrapper.mockError(
                error = fakeError
            )

            val result = GetContactsUseCase(
                usersRepository
            ).runAsync()

            coVerifySequence {
                usersRepository.getUsers()
            }

            Assert.assertNull(result.success)
            Assert.assertTrue(result.error is GetContactsFailureFactory.CustomError2Failure)
        }

    @Test
    fun `should return failure when repository return failure with custom error 3`() =
        runBlocking {
            val fakeError = ErrorWrapper(
                errorBody = ErrorDetailModel(
                    message = "message",
                    status = "1003"
                ),
            )

            coEvery {
                usersRepository.getUsers()
            } returns FakeResultWrapper.mockError(
                error = fakeError
            )

            val result = GetContactsUseCase(
                usersRepository
            ).runAsync()

            coVerifySequence {
                usersRepository.getUsers()
            }

            Assert.assertNull(result.success)
            Assert.assertTrue(result.error is GetContactsFailureFactory.CustomError3Failure)
        }

    @Test
    fun `should return generic failure when repository return failure`() = runBlocking {
        val fakeError = ErrorWrapper<ErrorDetailModel>()

        coEvery {
            usersRepository.getUsers()
        } returns FakeResultWrapper.mockError(
            error = fakeError
        )

        val result = GetContactsUseCase(
            usersRepository
        ).runAsync()

        coVerifySequence {
            usersRepository.getUsers()
        }

        Assert.assertNull(result.success)
        Assert.assertTrue(result.error is GetContactsFailureFactory.GenericFailure)
    }
}
