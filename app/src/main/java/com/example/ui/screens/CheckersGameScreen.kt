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
import androidx.compose.foundation.shape.CircleShape
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
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueDark
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen
import com.example.ui.theme.WasabiLight
import kotlin.math.abs

enum class PieceColor { RED, WHITE }

data class CheckersPiece(
    val color: PieceColor,
    val isKing: Boolean = false
)

@Composable
fun CheckersGameScreen(
    user: UserSession,
    onBackClick: () -> Unit
) {
    BackHandler {
        onBackClick()
    }

    // Initial 8x8 checkers layout
    // Red on rows 0,1,2 on dark squares ((r+c)%2 != 0)
    // White on rows 5,6,7 on dark squares
    fun initialBoard(): Map<Pair<Int, Int>, CheckersPiece> {
        val map = mutableMapOf<Pair<Int, Int>, CheckersPiece>()
        for (r in 0..2) {
            for (c in 0..7) {
                if ((r + c) % 2 != 0) {
                    map[Pair(r, c)] = CheckersPiece(PieceColor.RED)
                }
            }
        }
        for (r in 5..7) {
            for (c in 0..7) {
                if ((r + c) % 2 != 0) {
                    map[Pair(r, c)] = CheckersPiece(PieceColor.WHITE)
                }
            }
        }
        return map
    }

    var board by remember { mutableStateOf(initialBoard()) }
    var selectedSquare by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var currentTurn by remember { mutableStateOf(PieceColor.WHITE) }
    var statusMessage by remember { mutableStateOf("⚪ À vous de jouer (Pions Blancs) !") }

    val whiteCount = board.values.count { it.color == PieceColor.WHITE }
    val redCount = board.values.count { it.color == PieceColor.RED }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Jeu de Dames",
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
            // Status and score card
            Surface(
                color = SurfaceWhite,
                shape = RoundedCornerShape(16.dp),
                border = androidx.compose.foundation.BorderStroke(1.5.dp, CardBorder),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "⚪ Blancs: $whiteCount",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = NoriDark
                    )
                    Text(
                        text = statusMessage,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = if (currentTurn == PieceColor.WHITE) WasabiDark else Color(0xFFFF5964),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = "🔴 Rouges: $redCount",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color(0xFFFF5964)
                    )
                }
            }

            // 8x8 Board
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(16.dp))
                    .border(3.dp, Color(0xFF263238), RoundedCornerShape(16.dp)),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    for (row in 0..7) {
                        Row(modifier = Modifier.weight(1f)) {
                            for (col in 0..7) {
                                val isPlayableSquare = (row + col) % 2 != 0
                                val isSelected = selectedSquare == Pair(row, col)

                                val squareColor = when {
                                    isSelected -> Color(0xFFFFF176)
                                    isPlayableSquare -> Color(0xFF455A64) // Dark playable square
                                    else -> Color(0xFFECEFF1) // Light square
                                }
                                val piece = board[Pair(row, col)]

                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxSize()
                                        .background(squareColor)
                                        .clickable(enabled = isPlayableSquare) {
                                            val current = selectedSquare
                                            if (current == null) {
                                                if (piece != null && piece.color == currentTurn) {
                                                    selectedSquare = Pair(row, col)
                                                }
                                            } else {
                                                val movingPiece = board[current]
                                                if (movingPiece != null && piece == null) {
                                                    val dr = row - current.first
                                                    val dc = col - current.second

                                                    // Simple diagonal move (1 step)
                                                    val isForward = if (movingPiece.isKing) true else if (movingPiece.color == PieceColor.WHITE) dr < 0 else dr > 0
                                                    val isSimpleMove = abs(dr) == 1 && abs(dc) == 1 && isForward

                                                    // Jump / Capture move (2 steps)
                                                    val isJump = abs(dr) == 2 && abs(dc) == 2
                                                    val midR = current.first + dr / 2
                                                    val midC = current.second + dc / 2
                                                    val jumpedPiece = board[Pair(midR, midC)]

                                                    if (isSimpleMove) {
                                                        val newBoard = board.toMutableMap()
                                                        newBoard.remove(current)
                                                        val becomesKing = movingPiece.isKing || (movingPiece.color == PieceColor.WHITE && row == 0) || (movingPiece.color == PieceColor.RED && row == 7)
                                                        newBoard[Pair(row, col)] = movingPiece.copy(isKing = becomesKing)
                                                        board = newBoard
                                                        currentTurn = if (currentTurn == PieceColor.WHITE) PieceColor.RED else PieceColor.WHITE
                                                        statusMessage = if (currentTurn == PieceColor.WHITE) "⚪ Tour aux Blancs !" else "🔴 Tour aux Rouges !"
                                                    } else if (isJump && jumpedPiece != null && jumpedPiece.color != movingPiece.color) {
                                                        val newBoard = board.toMutableMap()
                                                        newBoard.remove(current)
                                                        newBoard.remove(Pair(midR, midC))
                                                        val becomesKing = movingPiece.isKing || (movingPiece.color == PieceColor.WHITE && row == 0) || (movingPiece.color == PieceColor.RED && row == 7)
                                                        newBoard[Pair(row, col)] = movingPiece.copy(isKing = becomesKing)
                                                        board = newBoard
                                                        statusMessage = "🎉 Prise réussie !"
                                                        currentTurn = if (currentTurn == PieceColor.WHITE) PieceColor.RED else PieceColor.WHITE
                                                    }
                                                }
                                                selectedSquare = null
                                            }
                                        },
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (piece != null) {
                                        Box(
                                            modifier = Modifier
                                                .size(28.dp)
                                                .clip(CircleShape)
                                                .background(
                                                    if (piece.color == PieceColor.WHITE) Color(0xFFFAFAFA) else Color(0xFFD32F2F)
                                                )
                                                .border(
                                                    2.dp,
                                                    if (piece.color == PieceColor.WHITE) Color(0xFFB0BEC5) else Color(0xFFB71C1C),
                                                    CircleShape
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            if (piece.isKing) {
                                                Text(text = "👑", fontSize = 12.sp)
                                            }
                                        }
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
                    text = "NOUVELLE PARTIE 🔄",
                    onClick = {
                        board = initialBoard()
                        selectedSquare = null
                        currentTurn = PieceColor.WHITE
                        statusMessage = "⚪ Nouvelle partie lancée !"
                    },
                    backgroundColor = SkyBlue,
                    shadowColor = SkyBlueDark,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}
