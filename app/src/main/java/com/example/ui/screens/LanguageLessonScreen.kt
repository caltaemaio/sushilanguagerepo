package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LanguageItem
import com.example.data.model.UserSession
import com.example.ui.components.Sushi3DButton
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.NoriMuted
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SalmonCoral
import com.example.ui.theme.SalmonDark
import com.example.ui.theme.SalmonLight
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen
import com.example.ui.theme.WasabiLight

@Composable
fun LanguageLessonScreen(
    language: LanguageItem,
    user: UserSession,
    onCompleteLesson: (score: Int) -> Unit,
    onLoseHeart: () -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    val questions = language.questions
    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerChecked by remember { mutableStateOf(false) }
    var isCorrect by remember { mutableStateOf(false) }
    var isLessonFinished by remember { mutableStateOf(false) }
    var correctAnswersCount by remember { mutableIntStateOf(0) }

    val currentQ = if (questions.isNotEmpty()) questions[currentQuestionIndex.coerceIn(0, questions.size - 1)] else null
    val progress = if (questions.isNotEmpty()) (currentQuestionIndex.toFloat() / questions.size.toFloat()) else 1f

    Scaffold(
        topBar = {
            Surface(
                color = SurfaceWhite,
                shadowElevation = 1.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fermer la leçon",
                            tint = NoriMuted
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    LinearProgressIndicator(
                        progress = { if (isLessonFinished) 1f else progress },
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        color = WasabiGreen,
                        trackColor = Color(0xFFE5E5E5),
                        strokeCap = StrokeCap.Round
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(SalmonLight)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Cœurs",
                            tint = SalmonCoral,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${user.hearts}",
                            fontWeight = FontWeight.Bold,
                            color = SalmonCoral,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        },
        containerColor = RiceCream
    ) { innerPadding ->
        if (isLessonFinished) {
            // Victory Screen
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "🎉", fontSize = 64.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Leçon Terminée !",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    color = NoriDark,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "Vous avez brillé en ${language.name} ${language.flagEmoji} !",
                    fontSize = 15.sp,
                    color = NoriMuted,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 6.dp, bottom = 24.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Réponses correctes", color = NoriMuted, fontWeight = FontWeight.Medium)
                            Text("$correctAnswersCount / ${questions.size}", fontWeight = FontWeight.Bold, color = WasabiDark)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Récompense Sushi", color = NoriMuted, fontWeight = FontWeight.Medium)
                            Text("+30 🍣", fontWeight = FontWeight.Bold, color = Color(0xFFFF9600))
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Série entretenue", color = NoriMuted, fontWeight = FontWeight.Medium)
                            Text("🔥 ${user.streakDays} jours", fontWeight = FontWeight.Bold, color = Color(0xFFFF5964))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Sushi3DButton(
                    text = "CONTINUER 🍣",
                    onClick = onBackClick,
                    backgroundColor = WasabiGreen,
                    shadowColor = WasabiDark,
                    modifier = Modifier.fillMaxWidth(),
                    testTag = "continue_after_lesson_button"
                )
            }
        } else if (currentQ != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    // Question Prompt
                    Text(
                        text = "NOUVEAU MOT",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = WasabiDark,
                        letterSpacing = 1.sp
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = currentQ.prompt,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = NoriDark
                    )

                    Spacer(modifier = Modifier.height(18.dp))

                    // Mascot prompt bubble
                    Surface(
                        color = SurfaceWhite,
                        shape = RoundedCornerShape(18.dp),
                        border = androidx.compose.foundation.BorderStroke(2.dp, CardBorder),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFE5F9DB)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = "🍣", fontSize = 24.sp)
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = "« ${currentQ.targetWord} »",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = WasabiDark
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Options list
                    currentQ.options.forEachIndexed { index, option ->
                        val isSelected = selectedOptionIndex == index
                        val isThisCorrect = isAnswerChecked && index == currentQ.correctIndex
                        val isThisWrong = isAnswerChecked && isSelected && !isCorrect

                        val bgColor = when {
                            isThisCorrect -> WasabiLight
                            isThisWrong -> SalmonLight
                            isSelected -> Color(0xFFE3F6FF)
                            else -> SurfaceWhite
                        }
                        val borderColor = when {
                            isThisCorrect -> WasabiGreen
                            isThisWrong -> SalmonCoral
                            isSelected -> Color(0xFF1CB0F6)
                            else -> CardBorder
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(bgColor)
                                .border(2.dp, borderColor, RoundedCornerShape(16.dp))
                                .clickable(enabled = !isAnswerChecked) {
                                    selectedOptionIndex = index
                                }
                                .padding(16.dp)
                                .testTag("option_$index")
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = option,
                                    fontSize = 17.sp,
                                    fontWeight = if (isSelected || isThisCorrect) FontWeight.Bold else FontWeight.Medium,
                                    color = NoriDark
                                )

                                if (isThisCorrect) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Correct",
                                        tint = WasabiGreen
                                    )
                                } else if (isThisWrong) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Faux",
                                        tint = SalmonCoral
                                    )
                                }
                            }
                        }
                    }
                }

                // Bottom validation feedback & Button
                Column {
                    AnimatedVisibility(
                        visible = isAnswerChecked,
                        enter = fadeIn() + slideInVertically(initialOffsetY = { it / 2 })
                    ) {
                        Surface(
                            color = if (isCorrect) WasabiLight else SalmonLight,
                            shape = RoundedCornerShape(16.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = if (isCorrect) "🎉 C'est parfait !" else "💡 Oups, pas tout à fait !",
                                    fontWeight = FontWeight.Black,
                                    fontSize = 16.sp,
                                    color = if (isCorrect) WasabiDark else SalmonDark
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = currentQ.explanation,
                                    fontSize = 13.sp,
                                    color = NoriDark
                                )
                            }
                        }
                    }

                    if (!isAnswerChecked) {
                        Sushi3DButton(
                            text = "VÉRIFIER",
                            onClick = {
                                if (selectedOptionIndex != null) {
                                    isAnswerChecked = true
                                    val correct = selectedOptionIndex == currentQ.correctIndex
                                    isCorrect = correct
                                    if (correct) {
                                        correctAnswersCount++
                                    } else {
                                        onLoseHeart()
                                    }
                                }
                            },
                            enabled = selectedOptionIndex != null,
                            backgroundColor = WasabiGreen,
                            shadowColor = WasabiDark,
                            modifier = Modifier.fillMaxWidth(),
                            testTag = "verify_answer_button"
                        )
                    } else {
                        Sushi3DButton(
                            text = if (currentQuestionIndex + 1 < questions.size) "QUESTION SUIVANTE ➡️" else "TERMINER LA LEÇON 🎉",
                            onClick = {
                                if (currentQuestionIndex + 1 < questions.size) {
                                    currentQuestionIndex++
                                    selectedOptionIndex = null
                                    isAnswerChecked = false
                                } else {
                                    isLessonFinished = true
                                    onCompleteLesson(correctAnswersCount)
                                }
                            },
                            backgroundColor = if (isCorrect) WasabiGreen else SalmonCoral,
                            shadowColor = if (isCorrect) WasabiDark else SalmonDark,
                            modifier = Modifier.fillMaxWidth(),
                            testTag = "next_question_button"
                        )
                    }
                }
            }
        }
    }
}
