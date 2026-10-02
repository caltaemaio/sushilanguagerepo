package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.UserSession
import com.example.ui.components.Sushi3DButton
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
fun ChessGameScreen(
    user: UserSession,
    onWinPuzzle: (coins: Int) -> Unit,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    // 8x8 Board state represented as map of (row, col) to piece symbol
    // Initial tactical puzzle: White Queen on f3, White Bishop on c4, Black King on g8, Black Pawns on f7, g7, h7
    // Goal: White Queen to f7 is Checkmate!
    var board by remember {
        mutableStateOf(
            mapOf(
                Pair(0, 4) to "♚", // Black King e8
                Pair(1, 5) to "♟", // Black Pawn f7
                Pair(1, 6) to "♟", // Black Pawn g7
                Pair(1, 7) to "♟", // Black Pawn h7
                Pair(5, 5) to "♕", // White Queen f3
                Pair(4, 2) to "♗", // White Bishop c4
                Pair(7, 4) to "♔"  // White King e1
            )
        )
    }

    var selectedSquare by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var statusMessage by remember { mutableStateOf("🎯 Défi : Les Blancs jouent et gagnent (Mat en 1 coup) !") }
    var isPuzzleWon by remember { mutableStateOf(false) }

    fun resetPuzzle() {
        board = mapOf(
            Pair(0, 4) to "♚",
            Pair(1, 5) to "♟",
            Pair(1, 6) to "♟",
            Pair(1, 7) to "♟",
            Pair(5, 5) to "♕",
            Pair(4, 2) to "♗",
            Pair(7, 4) to "♔"
        )
        selectedSquare = null
        isPuzzleWon = false
        statusMessage = "🎯 Défi : Les Blancs jouent et gagnent (Mat en 1 coup) !"
    }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Échecs Tactiques",
                streakDays = user.streakDays,
                sushiCoins = user.sushiCoins,
                hearts = user.hearts,
                onBackClick = onBackClick
            )
        },
        containerColor = RiceCream
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Status banner
            Surface(
                color = if (isPuzzleWon) WasabiLight else SurfaceWhite,
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(2.dp, if (isPuzzleWon) WasabiGreen else CardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = statusMessage,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = if (isPuzzleWon) WasabiDark else NoriDark,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(12.dp)
                )
            }

            // Interactive 8x8 Chessboard
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(3.dp, Color(0xFF8B5A2B), RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    for (row in 0..7) {
                        Row(modifier = Modifier.weight(1f)) {
                            for (col in 0..7) {
                                val isLightSquare = (row + col) % 2 == 0
                                val squareColor = when {
                                    selectedSquare == Pair(row, col) -> Color(0xFFFFF59D) // Yellow highlighted
                                    isLightSquare -> Color(0xFFF0D9B5)
                                    else -> Color(0xFFB58863)
                                }
                                val piece = board[Pair(row, col)]

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .background(squareColor)
                                        .clickable {
                                            if (isPuzzleWon) return@clickable

                                            val currentSelected = selectedSquare
                                            if (currentSelected == null) {
                                                // Only select white pieces
                                                if (piece == "♕" || piece == "♗" || piece == "♔") {
                                                    selectedSquare = Pair(row, col)
                                                }
                                            } else {
                                                // Move attempted
                                                val movingPiece = board[currentSelected]
                                                if (movingPiece == "♕" && row == 1 && col == 5) {
                                                    // Winning move! Queen captures f7 with Bishop support -> Checkmate!
                                                    val newBoard = board.toMutableMap()
                                                    newBoard.remove(currentSelected)
                                                    newBoard[Pair(1, 5)] = "♕"
                                                    board = newBoard
                                                    isPuzzleWon = true
                                                    statusMessage = "🏆 Échec et Mat ! La Dame est protégée par le Fou en c4. +50 🍣 !"
                                                    onWinPuzzle(50)
                                                } else if (Pair(row, col) != currentSelected) {
                                                    // Move made
                                                    val newBoard = board.toMutableMap()
                                                    newBoard.remove(currentSelected)
                                                    if (movingPiece != null) {
                                                        newBoard[Pair(row, col)] = movingPiece
                                                    }
                                                    board = newBoard
                                                    statusMessage = "Coup joué. Essayez de trouver le mat en 1 coup direct !"
                                                }
                                                selectedSquare = null
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (piece != null) {
                                        Text(
                                            text = piece,
                                            fontSize = 28.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = if (piece == "♕" || piece == "♗" || piece == "♔") Color(0xFFFFFFFF) else Color(0xFF1E1E1E)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Controls
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Sushi3DButton(
                    text = "RÉINITIALISER",
                    onClick = { resetPuzzle() },
                    backgroundColor = Color(0xFF9E9E9E),
                    shadowColor = Color(0xFF757575),
                    modifier = Modifier.weight(1f)
                )

                Sushi3DButton(
                    text = "INDICE 💡",
                    onClick = {
                        statusMessage = "💡 Indice : La Dame en f3 peut viser la case faible f7 !"
                    },
                    backgroundColor = WasabiGreen,
                    shadowColor = WasabiDark,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
