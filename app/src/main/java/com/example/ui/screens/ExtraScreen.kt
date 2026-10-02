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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.SubjectItem
import com.example.data.model.UserSession
import com.example.ui.components.Sushi3DButton
import com.example.ui.components.SushiBubblyCard
import com.example.ui.components.SushiTopBar
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.NoriMuted
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TamagoDark
import com.example.ui.theme.TamagoLight
import com.example.ui.theme.TamagoYellow

@Composable
fun ExtraScreen(
    user: UserSession,
    subjects: List<SubjectItem>,
    onSubjectClick: (subjectId: String) -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Matières Scolaires",
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
                .testTag("subjects_list"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header banner
            item {
                Surface(
                    color = TamagoLight,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🎓", fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Culture Générale & Sciences",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = TamagoDark
                            )
                            Text(
                                text = "Réviser et consolider vos compétences scolaires pas à pas.",
                                fontSize = 13.sp,
                                color = NoriDark
                            )
                        }
                    }
                }
            }

            items(subjects, key = { it.id }) { subject ->
                SubjectCardItem(
                    subject = subject,
                    onStartQuiz = { onSubjectClick(subject.id) }
                )
            }

            item {
                // Info box on upcoming subjects
                Surface(
                    color = SurfaceWhite,
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, CardBorder),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "📚", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Matières évolutives",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = NoriDark
                            )
                            Text(
                                text = "Histoire, Littérature et Philosophie pourront être ajoutées facilement !",
                                fontSize = 12.sp,
                                color = NoriMuted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubjectCardItem(
    subject: SubjectItem,
    onStartQuiz: () -> Unit
) {
    SushiBubblyCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        borderColor = CardBorder,
        shadowColor = Color(subject.color).copy(alpha = 0.5f),
        elevationOffset = 4.dp,
        onClick = onStartQuiz,
        testTag = "subject_card_${subject.id}"
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape)
                            .background(Color(subject.color).copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = subject.iconEmoji, fontSize = 28.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = subject.name,
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            color = NoriDark
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(subject.color).copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = subject.badge,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(subject.color)
                            )
                        }
                    }
                }

                Text(
                    text = "${subject.questions.size} questions",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(subject.color)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = subject.description,
                fontSize = 13.sp,
                color = NoriMuted,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Sushi3DButton(
                text = "COMMENCER LE QUIZ 📝",
                onClick = onStartQuiz,
                backgroundColor = Color(subject.color),
                shadowColor = Color(subject.color).copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                testTag = "start_quiz_button_${subject.id}"
            )
        }
    }
}
