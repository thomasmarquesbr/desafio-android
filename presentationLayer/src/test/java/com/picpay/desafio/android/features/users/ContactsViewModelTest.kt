package com.picpay.desafio.android.features.users

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import com.thomas.archtecture_framework.fakes.FakeResultWrapper
import com.thomas.archtecture_framework.state.ViewState
import com.thomas.domainlayer.FakeUserModel
import com.thomas.domainlayer.features.users.GetContactsFailureFactory
import com.thomas.domainlayer.features.users.GetContactsUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

class ContactsViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getContactsUseCase: GetContactsUseCase

    private lateinit var viewModel: ContactsViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)

        viewModel = spyk(instantiateViewModel())
    }

    @ExperimentalCoroutinesApi
    private fun instantiateViewModel() = ContactsViewModel(
        dispatcherIO = testDispatcher,
        getContactsUseCase = getContactsUseCase
    )

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `loadDetails SHOULD set live data WHEN request success`() {
        val fakeList = listOf(
            FakeUserModel.mock(),
            FakeUserModel.mock(),
            FakeUserModel.mock()
        )
        coEvery {
            getContactsUseCase.runAsync(any())
        } returns FakeResultWrapper.mockSuccess(
            success = fakeList
        )

        val observer = viewModel.getContactsState.test()

        viewModel.loadDetails()

        coVerify {
            getContactsUseCase.runAsync(any())
        }

        observer.assertHasValue()
        observer.assertHistorySize(2)
        Assert.assertTrue((observer.valueHistory()[0] is ViewState.Loading))
        Assert.assertTrue((observer.valueHistory()[1] is ViewState.Success))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `loadDetails SHOULD set live data WHEN request failure`() {
        coEvery {
            getContactsUseCase.runAsync(any())
        } returns FakeResultWrapper.mockError(
            error = GetContactsFailureFactory.BaseFailure()
        )

        val observer = viewModel.getContactsState.test()

        viewModel.loadDetails()

        coVerify {
            getContactsUseCase.runAsync(any())
        }

        observer.assertHasValue()
        observer.assertHistorySize(2)
        Assert.assertTrue((observer.valueHistory()[0] is ViewState.Loading))
        Assert.assertTrue((observer.valueHistory()[1] is ViewState.Error))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `loadDetails SHOULD set live data WHEN request returns emptyList`() {
        coEvery {
            getContactsUseCase.runAsync(any())
        } returns FakeResultWrapper.mockSuccess(
            success = listOf()
        )

        val observer = viewModel.getContactsState.test()

        viewModel.loadDetails()

        coVerify {
            getContactsUseCase.runAsync(any())
        }

        observer.assertHasValue()
        observer.assertHistorySize(2)
        Assert.assertTrue((observer.valueHistory()[0] is ViewState.Loading))
        Assert.assertTrue((observer.valueHistory()[1] is ViewState.Empty))
    }
}
