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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.data.model.GameItem
import com.example.data.model.UserSession
import com.example.ui.components.Sushi3DButton
import com.example.ui.components.SushiBubblyCard
import com.example.ui.components.SushiTopBar
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.NoriMuted
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueDark
import com.example.ui.theme.SkyBlueLight
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen

@Composable
fun GamesScreen(
    user: UserSession,
    games: List<GameItem>,
    onGameSelect: (gameId: String) -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Jeux d'Esprit",
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
                .testTag("games_list"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Surface(
                    color = SkyBlueLight,
                    shape = RoundedCornerShape(18.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "🧠", fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Entraînez votre réflexion",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = SkyBlueDark
                            )
                            Text(
                                text = "Défiez votre esprit avec des jeux classiques de stratégie.",
                                fontSize = 13.sp,
                                color = NoriDark
                            )
                        }
                    }
                }
            }

            items(games, key = { it.id }) { game ->
                GameCardItem(
                    game = game,
                    onPlay = { onGameSelect(game.id) }
                )
            }

            item {
                // Info box on upcoming games
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
                        Text(text = "✨", fontSize = 22.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Plus de jeux bientôt disponibles !",
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                color = NoriDark
                            )
                            Text(
                                text = "Sudoku, Go et Mots Croisés sushis sont en cours de préparation.",
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
fun GameCardItem(
    game: GameItem,
    onPlay: () -> Unit
) {
    SushiBubblyCard(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = SurfaceWhite,
        borderColor = CardBorder,
        shadowColor = Color(game.color).copy(alpha = 0.5f),
        elevationOffset = 4.dp,
        onClick = onPlay,
        testTag = "game_card_${game.id}"
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
                            .background(Color(game.color).copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = game.iconEmoji, fontSize = 28.sp)
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column {
                        Text(
                            text = game.name,
                            fontWeight = FontWeight.Black,
                            fontSize = 20.sp,
                            color = NoriDark
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(game.color).copy(alpha = 0.15f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = game.tag,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(game.color)
                            )
                        }
                    }
                }

                Text(
                    text = game.difficulty,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NoriMuted
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = game.description,
                fontSize = 13.sp,
                color = NoriMuted,
                lineHeight = 17.sp
            )

            Spacer(modifier = Modifier.height(14.dp))

            Sushi3DButton(
                text = "JOUER À ${game.name.uppercase()} 🎮",
                onClick = onPlay,
                backgroundColor = Color(game.color),
                shadowColor = Color(game.color).copy(alpha = 0.8f),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp),
                testTag = "play_game_button_${game.id}"
            )
        }
    }
}
