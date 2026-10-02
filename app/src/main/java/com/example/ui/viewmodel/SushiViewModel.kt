package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.data.SushiData
import com.example.data.model.CategoryCard
import com.example.data.model.GameItem
import com.example.data.model.LanguageItem
import com.example.data.model.Screen
import com.example.data.model.SubjectItem
import com.example.data.model.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SushiViewModel : ViewModel() {

    private val _userSession = MutableStateFlow(UserSession())
    val userSession: StateFlow<UserSession> = _userSession.asStateFlow()

    private val _screenStack = MutableStateFlow<List<Screen>>(listOf(Screen.Auth))
    val currentScreen: StateFlow<Screen> = MutableStateFlow(Screen.Auth)

    private val _categories = MutableStateFlow(SushiData.mainCategories)
    val categories: StateFlow<List<CategoryCard>> = _categories.asStateFlow()

    private val _languages = MutableStateFlow(SushiData.languages)
    val languages: StateFlow<List<LanguageItem>> = _languages.asStateFlow()

    val games: List<GameItem> = SushiData.games
    val subjects: List<SubjectItem> = SushiData.subjects

    // Active screen derived from stack
    private val _activeScreen = MutableStateFlow<Screen>(Screen.Auth)
    val activeScreen: StateFlow<Screen> = _activeScreen.asStateFlow()

    fun login(usernameInput: String, passwordInput: String): Boolean {
        if (usernameInput.isBlank()) return false
        _userSession.update {
            it.copy(
                username = usernameInput.trim(),
                email = if (usernameInput.contains("@")) usernameInput.trim() else "${usernameInput.trim().lowercase()}@sushilanguage.com",
                isLoggedIn = true
            )
        }
        _screenStack.value = listOf(Screen.Perso)
        _activeScreen.value = Screen.Perso
        return true
    }

    fun continueAsGuest() {
        _userSession.update {
            it.copy(
                username = "Sushi-Master",
                email = "invite@sushilanguage.com",
                isLoggedIn = true
            )
        }
        _screenStack.value = listOf(Screen.Perso)
        _activeScreen.value = Screen.Perso
    }

    fun logout() {
        _userSession.update { it.copy(isLoggedIn = false) }
        _screenStack.value = listOf(Screen.Auth)
        _activeScreen.value = Screen.Auth
    }

    fun navigateTo(screen: Screen) {
        _screenStack.update { it + screen }
        _activeScreen.value = screen
    }

    fun goBack(): Boolean {
        val current = _screenStack.value
        if (current.size > 1) {
            val updated = current.dropLast(1)
            _screenStack.value = updated
            _activeScreen.value = updated.last()
            return true
        }
        return false
    }

    fun completeLanguageLesson(langId: String, scoreEarned: Int) {
        _userSession.update { user ->
            user.copy(
                sushiCoins = user.sushiCoins + (scoreEarned * 10),
                completedLessons = user.completedLessons + langId
            )
        }
        _languages.update { list ->
            list.map { lang ->
                if (lang.id == langId) {
                    val newCompleted = (lang.completedLessons + 1).coerceAtMost(lang.totalLessons)
                    val newProgress = newCompleted.toFloat() / lang.totalLessons.toFloat()
                    lang.copy(completedLessons = newCompleted, progress = newProgress)
                } else lang
            }
        }
    }

    fun completeSubjectQuiz(subjectId: String, scoreEarned: Int) {
        _userSession.update { user ->
            user.copy(
                sushiCoins = user.sushiCoins + (scoreEarned * 15)
            )
        }
    }

    fun loseHeart() {
        _userSession.update { user ->
            user.copy(hearts = (user.hearts - 1).coerceAtLeast(0))
        }
    }

    fun refillHearts() {
        _userSession.update { user ->
            user.copy(hearts = 5)
        }
    }

    // Extensibility demo: Add a custom category
    fun addNewCategory(title: String, subtitle: String, countLabel: String, icon: String) {
        val newCard = CategoryCard(
            id = "custom_${System.currentTimeMillis()}",
            title = title,
            subtitle = subtitle,
            countLabel = countLabel,
            iconEmoji = icon,
            color = 0xFF9E40DA,
            shadowColor = 0xFF7A2AA8
        )
        _categories.update { it + newCard }
    }
}
