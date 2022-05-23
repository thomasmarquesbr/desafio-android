package com.picpay.desafio.android.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavDirections
import com.jraska.livedata.test
import io.mockk.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class BaseViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private lateinit var baseViewModel: BaseViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {

        MockKAnnotations.init(this)

        Dispatchers.setMain(testDispatcher)

        startKoin(koinApplication = KoinApplication.init())

        baseViewModel = spyk(BaseViewModel(testDispatcher))
    }

    @ExperimentalCoroutinesApi
    @After
    fun after() {
        stopKoin()
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoToTest() {

        val direction = mockk<NavDirections>()

        baseViewModel.goTo(direction)

        verify(exactly = 1) {
            baseViewModel.navigate(NavigationCommand.To(direction))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoNavigationCommand() {

        baseViewModel.goToNavigationCommand(NavigationCommand.Back)

        verify(exactly = 1) {
            baseViewModel.navigate(any())
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoBackTest() {

        baseViewModel.goBack()

        verify(exactly = 1) {
            baseViewModel.navigate(NavigationCommand.Back)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoBackToHomeTest() {

        baseViewModel.goToHome()

        verify(exactly = 1) {
            baseViewModel.navigate(NavigationCommand.GoToHome)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoBackToLoginTest() {

        baseViewModel.goBackToLogin()

        verify(exactly = 1) {
            baseViewModel.navigate(NavigationCommand.BackToLogin)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldNavigateBackToHomeTest() {

        val navigationCommand = NavigationCommand.GoToHome

        val observer = baseViewModel.navigation.test()

        baseViewModel.navigate(navigationCommand)

        observer.assertHasValue()
        observer.assertHistorySize(1)

        val valueHistory = observer.valueHistory()

        assert(valueHistory[0].getContentIfNotHandled() is NavigationCommand.GoToHome)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldNavigateToTest() {

        val direction = mockk<NavDirections>()

        val navigationCommand = NavigationCommand.To(direction)

        val observer = baseViewModel.navigation.test()

        baseViewModel.navigate(navigationCommand)

        observer.assertHasValue()
        observer.assertHistorySize(1)

        val valueHistory = observer.valueHistory()

        val navCommandResult = valueHistory[0].getContentIfNotHandled()

        assert(navCommandResult is NavigationCommand.To)
        assertEquals(direction, (navCommandResult as NavigationCommand.To).directions)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldShowProgressTest() {

        val observer = baseViewModel.showProgress.test()

        baseViewModel.setStatusProgress(true)

        observer.assertHasValue()
        observer.assertHistorySize(2)

        val valueHistory = observer.valueHistory()

        assert(valueHistory[0] is Boolean)
        assert(valueHistory[1] is Boolean)
        assertFalse(valueHistory[0])
        assertTrue(valueHistory[1])
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldNotShowProgressTest() {

        val observer = baseViewModel.showProgress.test()

        baseViewModel.setStatusProgress(false)

        observer.assertHasValue()
        observer.assertHistorySize(2)

        val valueHistory = observer.valueHistory()

        assert(valueHistory[0] is Boolean)
        assert(valueHistory[1] is Boolean)
        assertFalse(valueHistory[0])
        assertFalse(valueHistory[1])
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldShowMessageTest() {

        val observer = baseViewModel.showMessage.test()

        baseViewModel.showMessage("message")

        observer.assertHasValue()
        observer.assertHistorySize(2)

        val valueHistory = observer.valueHistory()

        assertNull(valueHistory[0])
        assert(valueHistory[1] is String)
        assertEquals(valueHistory[1], "message")
    }

    @ExperimentalCoroutinesApi
    @Test
    fun shouldGoBackWhenOnBackPressedTest() {

        baseViewModel.onBackPressed()

        verify(exactly = 1) {
            baseViewModel.goBack()
        }
    }

}
