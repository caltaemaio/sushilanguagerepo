package com.example.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.SalmonCoral
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.TamagoYellow
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen

@Composable
fun Sushi3DButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = WasabiGreen,
    shadowColor: Color = WasabiDark,
    textColor: Color = SurfaceWhite,
    icon: @Composable (() -> Unit)? = null,
    enabled: Boolean = true,
    testTag: String = "sushi_3d_button"
) {
    var isPressed by remember { mutableStateOf(false) }
    val pressOffset by animateFloatAsState(
        targetValue = if (isPressed) 4f else 0f,
        label = "press_anim"
    )

    Box(
        modifier = modifier
            .testTag(testTag)
            .height(54.dp)
            .pointerInput(enabled) {
                if (enabled) {
                    awaitEachGesture {
                        awaitFirstDown(requireUnconsumed = false)
                        isPressed = true
                        val up = waitForUpOrCancellation()
                        isPressed = false
                        if (up != null) {
                            onClick()
                        }
                    }
                }
            }
    ) {
        // Bottom 3D shadow layer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .offset(y = 4.dp)
                .background(
                    if (enabled) shadowColor else Color(0xFFC7CBD1),
                    shape = RoundedCornerShape(16.dp)
                )
        )

        // Top button face that depresses
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .offset(y = pressOffset.dp)
                .background(
                    if (enabled) backgroundColor else Color(0xFFE2E6EC),
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                if (icon != null) {
                    icon()
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    color = if (enabled) textColor else Color(0xFF8C929D),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    letterSpacing = 0.5.sp
                )
            }
        }
    }
}

@Composable
fun SushiTopBar(
    title: String,
    streakDays: Int,
    sushiCoins: Int,
    hearts: Int,
    onBackClick: (() -> Unit)? = null
) {
    Surface(
        color = SurfaceWhite,
        shadowElevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (onBackClick != null) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(40.dp)
                            .testTag("top_bar_back_button")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour",
                            tint = NoriDark
                        )
                    }
                    Spacer(modifier = Modifier.width(4.dp))
                }
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Black,
                    color = NoriDark,
                    fontSize = 18.sp
                )
            }

            // Stats row (Streak, Sushis, Hearts)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Streak
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF4E5))
                        .padding(horizontal = 7.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Flamme de série",
                        tint = Color(0xFFFF9600),
                        modifier = Modifier.size(17.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "$streakDays",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFCC7800),
                        fontSize = 13.sp
                    )
                }

                // Sushis / Coins
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFF9E0))
                        .padding(horizontal = 7.dp, vertical = 4.dp)
                ) {
                    Text(text = "🍣", fontSize = 13.sp)
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "$sushiCoins",
                        fontWeight = FontWeight.Bold,
                        color = TamagoYellow,
                        fontSize = 13.sp
                    )
                }

                // Hearts
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFFFEAEB))
                        .padding(horizontal = 7.dp, vertical = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Vies",
                        tint = SalmonCoral,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(3.dp))
                    Text(
                        text = "$hearts",
                        fontWeight = FontWeight.Bold,
                        color = SalmonCoral,
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}

@Composable
fun SushiBubblyCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SurfaceWhite,
    borderColor: Color = CardBorder,
    shadowColor: Color = Color(0xFFD6D6D6),
    elevationOffset: Dp = 4.dp,
    onClick: (() -> Unit)? = null,
    testTag: String = "sushi_bubbly_card",
    content: @Composable () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .testTag(testTag)
            .then(
                if (onClick != null) {
                    Modifier.pointerInput(Unit) {
                        awaitEachGesture {
                            awaitFirstDown(requireUnconsumed = false)
                            isPressed = true
                            val up = waitForUpOrCancellation()
                            isPressed = false
                            if (up != null) {
                                onClick()
                            }
                        }
                    }
                } else Modifier
            )
    ) {
        // Shadow base
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = elevationOffset)
                .background(shadowColor, shape = RoundedCornerShape(22.dp))
        )

        // Card Face
        Box(
            modifier = Modifier
                .offset(y = if (isPressed) elevationOffset else 0.dp)
                .background(backgroundColor, shape = RoundedCornerShape(22.dp))
                .border(BorderStroke(2.dp, borderColor), shape = RoundedCornerShape(22.dp))
                .padding(16.dp)
        ) {
            content()
        }
    }
}
