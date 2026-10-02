package com.example.data.model

data class UserSession(
    val username: String = "Sushinaute",
    val email: String = "user@sushilanguage.com",
    val isLoggedIn: Boolean = false,
    val streakDays: Int = 3,
    val sushiCoins: Int = 150,
    val hearts: Int = 5,
    val completedLessons: Set<String> = setOf("fra_1", "jap_1")
)

data class CategoryCard(
    val id: String,
    val title: String,
    val subtitle: String,
    val countLabel: String,
    val iconEmoji: String,
    val color: Long,
    val shadowColor: Long
)

data class LanguageQuestion(
    val id: String,
    val prompt: String,
    val targetWord: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class LanguageItem(
    val id: String,
    val name: String,
    val nativeGreeting: String,
    val flagEmoji: String,
    val level: String,
    val progress: Float,
    val completedLessons: Int,
    val totalLessons: Int,
    val description: String,
    val questions: List<LanguageQuestion>
)

data class GameItem(
    val id: String,
    val name: String,
    val iconEmoji: String,
    val description: String,
    val tag: String,
    val difficulty: String,
    val color: Long
)

data class SubjectQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class SubjectItem(
    val id: String,
    val name: String,
    val iconEmoji: String,
    val description: String,
    val badge: String,
    val color: Long,
    val questions: List<SubjectQuestion>
)

sealed interface Screen {
    data object Auth : Screen
    data object Perso : Screen
    data object Languages : Screen
    data class LanguageLesson(val languageId: String) : Screen
    data object Games : Screen
    data class ChessGame(val puzzleMode: Boolean = true) : Screen
    data object CheckersGame : Screen
    data object Extra : Screen
    data class SubjectQuiz(val subjectId: String) : Screen
}
