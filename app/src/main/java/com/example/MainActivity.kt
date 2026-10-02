package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.Screen
import com.example.ui.screens.AuthScreen
import com.example.ui.screens.CheckersGameScreen
import com.example.ui.screens.ChessGameScreen
import com.example.ui.screens.ExtraScreen
import com.example.ui.screens.GamesScreen
import com.example.ui.screens.LanguageLessonScreen
import com.example.ui.screens.LanguagesScreen
import com.example.ui.screens.PersoScreen
import com.example.ui.screens.SubjectQuizScreen
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SushiLanguageTheme
import com.example.ui.viewmodel.SushiViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SushiLanguageTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = RiceCream
                ) {
                    SushiLanguageApp()
                }
            }
        }
    }
}

@Composable
fun SushiLanguageApp(viewModel: SushiViewModel = viewModel()) {
    val activeScreen by viewModel.activeScreen.collectAsState()
    val userSession by viewModel.userSession.collectAsState()
    val categories by viewModel.categories.collectAsState()
    val languages by viewModel.languages.collectAsState()

    when (val screen = activeScreen) {
        is Screen.Auth -> {
            AuthScreen(
                onLoginSuccess = { user, pass ->
                    viewModel.login(user, pass)
                },
                onGuestLogin = {
                    viewModel.continueAsGuest()
                }
            )
        }

        is Screen.Perso -> {
            PersoScreen(
                user = userSession,
                categories = categories,
                onCategoryClick = { categoryId ->
                    when (categoryId) {
                        "languages" -> viewModel.navigateTo(Screen.Languages)
                        "games" -> viewModel.navigateTo(Screen.Games)
                        "extra" -> viewModel.navigateTo(Screen.Extra)
                        else -> {
                            // Custom newly added category defaults to Extra subjects
                            viewModel.navigateTo(Screen.Extra)
                        }
                    }
                },
                onAddCategory = { title, subtitle, countLabel, icon ->
                    viewModel.addNewCategory(title, subtitle, countLabel, icon)
                },
                onLogout = {
                    viewModel.logout()
                }
            )
        }

        is Screen.Languages -> {
            LanguagesScreen(
                user = userSession,
                languages = languages,
                onLanguageClick = { langId ->
                    viewModel.navigateTo(Screen.LanguageLesson(langId))
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.LanguageLesson -> {
            val language = languages.find { it.id == screen.languageId } ?: languages.first()
            LanguageLessonScreen(
                language = language,
                user = userSession,
                onCompleteLesson = { score ->
                    viewModel.completeLanguageLesson(language.id, score)
                },
                onLoseHeart = {
                    viewModel.loseHeart()
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.Games -> {
            GamesScreen(
                user = userSession,
                games = viewModel.games,
                onGameSelect = { gameId ->
                    when (gameId) {
                        "chess" -> viewModel.navigateTo(Screen.ChessGame())
                        "checkers" -> viewModel.navigateTo(Screen.CheckersGame)
                    }
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.ChessGame -> {
            ChessGameScreen(
                user = userSession,
                onWinPuzzle = { coins ->
                    viewModel.completeSubjectQuiz("chess", 5)
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.CheckersGame -> {
            CheckersGameScreen(
                user = userSession,
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.Extra -> {
            ExtraScreen(
                user = userSession,
                subjects = viewModel.subjects,
                onSubjectClick = { subjectId ->
                    viewModel.navigateTo(Screen.SubjectQuiz(subjectId))
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }

        is Screen.SubjectQuiz -> {
            val subject = viewModel.subjects.find { it.id == screen.subjectId } ?: viewModel.subjects.first()
            SubjectQuizScreen(
                subject = subject,
                user = userSession,
                onCompleteQuiz = { score ->
                    viewModel.completeSubjectQuiz(subject.id, score)
                },
                onLoseHeart = {
                    viewModel.loseHeart()
                },
                onBackClick = {
                    viewModel.goBack()
                }
            )
        }
    }
}
