package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.Sushi3DButton
import com.example.ui.theme.CardBorder
import com.example.ui.theme.NoriDark
import com.example.ui.theme.NoriMedium
import com.example.ui.theme.NoriMuted
import com.example.ui.theme.RiceCream
import com.example.ui.theme.SalmonCoral
import com.example.ui.theme.SalmonDark
import com.example.ui.theme.SkyBlue
import com.example.ui.theme.SkyBlueDark
import com.example.ui.theme.SurfaceWhite
import com.example.ui.theme.WasabiDark
import com.example.ui.theme.WasabiGreen

@Composable
fun AuthScreen(
    onLoginSuccess: (username: String, pass: String) -> Boolean,
    onGuestLogin: () -> Unit
) {
    var username by remember { mutableStateOf("NoriMaster") }
    var password by remember { mutableStateOf("sushi123") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isSignUpMode by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(RiceCream)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 24.dp, vertical = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            // Mascot Image
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_sushi_mascot_1790941244721),
                    contentDescription = "Mascotte Sushi Language",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Sushi Language",
                fontSize = 28.sp,
                fontWeight = FontWeight.Black,
                color = NoriDark,
                textAlign = TextAlign.Center
            )

            Text(
                text = "Apprenez les langues et testez vos neurones 🍣✨",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = NoriMuted,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp, bottom = 24.dp)
            )

            // Auth Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("auth_form_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceWhite),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isSignUpMode) "Créer un compte" else "Connexion",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = NoriDark,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Username Input
                    OutlinedTextField(
                        value = username,
                        onValueChange = {
                            username = it
                            errorMessage = null
                        },
                        label = { Text("Nom d'utilisateur ou Email") },
                        placeholder = { Text("Ex: Sakura75") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Identifiant",
                                tint = WasabiGreen
                            )
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = WasabiGreen,
                            unfocusedBorderColor = CardBorder,
                            focusedLabelColor = WasabiDark
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("username_input")
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Password Input
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            errorMessage = null
                        },
                        label = { Text("Mot de passe") },
                        placeholder = { Text("••••••••") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Mot de passe",
                                tint = WasabiGreen
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                                Icon(
                                    imageVector = if (isPasswordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                    contentDescription = if (isPasswordVisible) "Masquer" else "Afficher",
                                    tint = NoriMuted
                                )
                            }
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                                val success = onLoginSuccess(username, password)
                                if (!success) {
                                    errorMessage = "Veuillez entrer un identifiant valide."
                                }
                            }
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = WasabiGreen,
                            unfocusedBorderColor = CardBorder,
                            focusedLabelColor = WasabiDark
                        ),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("password_input")
                    )

                    if (errorMessage != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = errorMessage ?: "",
                            color = SalmonCoral,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // 3D Login Button
                    Sushi3DButton(
                        text = if (isSignUpMode) "CRÉER MON COMPTE 🍣" else "SE CONNECTER 🍣",
                        onClick = {
                            focusManager.clearFocus()
                            val success = onLoginSuccess(username, password)
                            if (!success) {
                                errorMessage = "Veuillez renseigner un nom d'utilisateur."
                            }
                        },
                        backgroundColor = WasabiGreen,
                        shadowColor = WasabiDark,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "login_button"
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Sign up toggle
                    TextButton(
                        onClick = {
                            isSignUpMode = !isSignUpMode
                            errorMessage = null
                        },
                        modifier = Modifier.testTag("toggle_signup_button")
                    ) {
                        Text(
                            text = if (isSignUpMode) "Déjà un compte ? Se connecter" else "Pas de compte ? Inscrivez-vous",
                            color = NoriMedium,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Guest access button
            Sushi3DButton(
                text = "CONTINUER EN INVITÉ 🚀",
                onClick = onGuestLogin,
                backgroundColor = SkyBlue,
                shadowColor = SkyBlueDark,
                modifier = Modifier.fillMaxWidth(),
                testTag = "guest_button"
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}
