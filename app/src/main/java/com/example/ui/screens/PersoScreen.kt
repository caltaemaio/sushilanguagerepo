package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.CategoryCard
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

@Composable
fun PersoScreen(
    user: UserSession,
    categories: List<CategoryCard>,
    onCategoryClick: (categoryId: String) -> Unit,
    onAddCategory: (title: String, subtitle: String, countLabel: String, icon: String) -> Unit,
    onLogout: () -> Unit
) {
    // BackHandler on root screen asks or logs out
    BackHandler {
        onLogout()
    }

    var showAddDialog by remember { mutableStateOf(false) }
    var newTitle by remember { mutableStateOf("") }
    var newSubtitle by remember { mutableStateOf("") }
    var newEmoji by remember { mutableStateOf("🍱") }

    Scaffold(
        topBar = {
            SushiTopBar(
                title = "Mon Espace Perso",
                streakDays = user.streakDays,
                sushiCoins = user.sushiCoins,
                hearts = user.hearts,
                onBackClick = null
            )
        },
        containerColor = RiceCream
    ) { innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .testTag("perso_grid"),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile Welcome Banner
            item(span = { GridItemSpan(2) }) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("perso_profile_banner"),
                    shape = RoundedCornerShape(22.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE5F9DB)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "🍣", fontSize = 28.sp)
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Konnichiwa, ${user.username} !",
                                fontSize = 19.sp,
                                fontWeight = FontWeight.Black,
                                color = NoriDark
                            )
                            Text(
                                text = "Choisissez un univers pour continuer votre entraînement !",
                                fontSize = 13.sp,
                                color = NoriMuted,
                                lineHeight = 16.sp
                            )
                        }

                        IconButton(
                            onClick = onLogout,
                            modifier = Modifier.testTag("logout_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ExitToApp,
                                contentDescription = "Déconnexion",
                                tint = NoriMuted
                            )
                        }
                    }
                }
            }

            // Section Header
            item(span = { GridItemSpan(2) }) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "VOS UNIVERS D'APPRENTISSAGE",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = NoriMuted,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "${categories.size} sections",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = WasabiDark
                    )
                }
            }

            // The Square Buttons
            items(categories, key = { it.id }) { cat ->
                SquareCategoryButton(
                    category = cat,
                    onClick = { onCategoryClick(cat.id) }
                )
            }

            // Future Extensibility Card: "+ Ajouter un bouton"
            item {
                SushiBubblyCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    backgroundColor = Color(0xFFF0F3F6),
                    borderColor = CardBorder,
                    shadowColor = Color(0xFFD6DBE1),
                    onClick = { showAddDialog = true },
                    testTag = "add_category_card"
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(Color.White),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Ajouter un univers",
                                tint = WasabiGreen,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "+ Nouveau",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = NoriDark
                        )
                        Text(
                            text = "Ajouter un univers",
                            fontSize = 11.sp,
                            color = NoriMuted,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            // Motivational quote footer
            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    color = Color(0xFFFFF9E0),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "💡", fontSize = 24.sp)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Astuce : 5 minutes par jour suffisent pour entretenir la flamme de votre savoir !",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF7A6000)
                        )
                    }
                }
            }
        }
    }

    // Add category dialog for future extensibility
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = {
                Text(
                    text = "Ajouter un univers",
                    fontWeight = FontWeight.Bold,
                    color = NoriDark
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Personnalisez votre page Perso avec une nouvelle section.",
                        fontSize = 13.sp,
                        color = NoriMuted
                    )
                    OutlinedTextField(
                        value = newTitle,
                        onValueChange = { newTitle = it },
                        label = { Text("Titre (ex: Histoire)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newSubtitle,
                        onValueChange = { newSubtitle = it },
                        label = { Text("Description courte") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = newEmoji,
                        onValueChange = { newEmoji = it },
                        label = { Text("Émoji (ex: 🏛️)") },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newTitle.isNotBlank()) {
                            onAddCategory(
                                newTitle.trim(),
                                if (newSubtitle.isBlank()) "Nouvelle section" else newSubtitle.trim(),
                                "À explorer",
                                if (newEmoji.isBlank()) "🍱" else newEmoji.trim()
                            )
                            newTitle = ""
                            newSubtitle = ""
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Ajouter", fontWeight = FontWeight.Bold, color = WasabiGreen)
                }
            },
            dismissButton = {
                TextButton(onClick = { showAddDialog = false }) {
                    Text("Annuler", color = NoriMuted)
                }
            }
        )
    }
}

@Composable
fun SquareCategoryButton(
    category: CategoryCard,
    onClick: () -> Unit
) {
    val baseColor = Color(category.color)
    val shadowColor = Color(category.shadowColor)

    SushiBubblyCard(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        backgroundColor = SurfaceWhite,
        borderColor = Color(category.color).copy(alpha = 0.4f),
        shadowColor = shadowColor,
        elevationOffset = 6.dp,
        onClick = onClick,
        testTag = "category_button_${category.id}"
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Badge
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(baseColor.copy(alpha = 0.15f))
                    .padding(horizontal = 8.dp, vertical = 3.dp)
            ) {
                Text(
                    text = category.countLabel,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = baseColor
                )
            }

            // Big Central Emoji / Mascot
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(CircleShape)
                    .background(baseColor.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = category.iconEmoji, fontSize = 30.sp)
            }

            // Title & Subtitle
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = category.title,
                    fontWeight = FontWeight.Black,
                    fontSize = 17.sp,
                    color = NoriDark,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = category.subtitle,
                    fontSize = 11.sp,
                    color = NoriMuted,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
