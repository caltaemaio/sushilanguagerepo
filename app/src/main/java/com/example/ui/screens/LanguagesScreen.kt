package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.LanguageItem
import com.example.data.model.UserSession
import com.example.ui.components.Sushi3DButton
import com.example.ui.components.SushiBubblyCard
import com.example.ui.components.SushiTopBar
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.NoriMuted
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen
import com.example.ui.theme.WasabiLight

@Composable
fun LanguagesScreen(
    user: UserSession,
    languages: List<LanguageItem>,
    onLanguageClick: (languageId: String) -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Langues Disponibles",
                streakDays = user.streakDays,
                sushiCoins = user.sushiCoins,
                hearts = user.hearts,
                onBackClick = onBackClick
            )
        },
        containerColor = RiceCream
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("languages_list"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header explanation
            item {
                Surface(
                    color = WasabiLight,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🥢", fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Choisissez votre parcours",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = WasabiDark
                            )
                            Text(
                                text = "${languages.size} langues disponibles avec leçons interactives rapides.",
                                fontSize = 13.sp,
                                color = NoriDark
                            )
                        }
                    }
                }
            }

            items(languages, key = { it.id }) { lang ->
                LanguageCardItem(
                    language = lang,
                    onStartLesson = { onLanguageClick(lang.id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun LanguageCardItem(
    language: LanguageItem,
    onStartLesson: () -> Unit
) {
    SushiBubblyCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        borderColor = CardBorder,
        shadowColor = Color(0xFFD6DBE1),
        elevationOffset = 4.dp,
        onClick = onStartLesson,
        testTag = "language_card_${language.id}"
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Flag circle
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF2F4F7)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = language.flagEmoji, fontSize = 26.sp)
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = language.name,
                                fontWeight = FontWeight.Black,
                                fontSize = 18.sp,
                                color = NoriDark
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(6.dp))
                                    .background(Color(0xFFE3F6FF))
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = language.level,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1482BA)
                                )
                            }
                        }
                        Text(
                            text = language.nativeGreeting,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = NoriMuted
                        )
                    }
                }

                Text(
                    text = "${language.completedLessons}/${language.totalLessons}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = WasabiDark
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = language.description,
                fontSize = 12.sp,
                color = NoriMuted,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Bar
            LinearProgressIndicator(
                progress = { language.progress },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = WasabiGreen,
                trackColor = Color(0xFFE5E5E5),
                strokeCap = StrokeCap.Round,
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Action row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Sushi3DButton(
                    text = "PRATIQUER ⚡",
                    onClick = onStartLesson,
                    backgroundColor = WasabiGreen,
                    shadowColor = WasabiDark,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp),
                    testTag = "practice_button_${language.id}"
                )
            }
        }
    }
}
