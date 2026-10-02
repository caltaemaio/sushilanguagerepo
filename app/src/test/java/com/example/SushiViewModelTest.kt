package com.example

import com.example.data.model.Screen
import com.example.ui.viewmodel.SushiViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SushiViewModelTest {

    private lateinit var viewModel: SushiViewModel

    @Before
    fun setup() {
        viewModel = SushiViewModel()
    }

    @Test
    fun testInitialState() {
        assertEquals(Screen.Auth, viewModel.activeScreen.value)
        assertFalse(viewModel.userSession.value.isLoggedIn)
        assertEquals(3, viewModel.categories.value.size)
        assertEquals(9, viewModel.languages.value.size)
    }

    @Test
    fun testLoginAndGuest() {
        assertTrue(viewModel.login("SushiMaster", "secret123"))
        assertEquals("SushiMaster", viewModel.userSession.value.username)
        assertTrue(viewModel.userSession.value.isLoggedIn)
        assertEquals(Screen.Perso, viewModel.activeScreen.value)
    }

    @Test
    fun testNavigationStack() {
        viewModel.continueAsGuest()
        assertEquals(Screen.Perso, viewModel.activeScreen.value)

        viewModel.navigateTo(Screen.Languages)
        assertEquals(Screen.Languages, viewModel.activeScreen.value)

        assertTrue(viewModel.goBack())
        assertEquals(Screen.Perso, viewModel.activeScreen.value)
    }

    @Test
    fun testExtensibleCategoryCreation() {
        val initialCount = viewModel.categories.value.size
        viewModel.addNewCategory("Histoire", "Grandes époques", "3 époques", "🏛️")
        assertEquals(initialCount + 1, viewModel.categories.value.size)
        assertEquals("Histoire", viewModel.categories.value.last().title)
    }

    @Test
    fun testLessonProgressAndSushiCoins() {
        val initialCoins = viewModel.userSession.value.sushiCoins
        viewModel.completeLanguageLesson("it", 3)
        assertEquals(initialCoins + 30, viewModel.userSession.value.sushiCoins)
    }
}
