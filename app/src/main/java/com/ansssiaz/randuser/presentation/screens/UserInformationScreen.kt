package com.ansssiaz.randuser.presentation.screens

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.ansssiaz.randuser.R
import com.ansssiaz.randuser.presentation.viewmodel.UsersViewModel
import com.ansssiaz.randuser.util.formatDate
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserInformationScreen(
    usersViewModel: UsersViewModel,
    onBack: () -> Unit
) {
    val state by usersViewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("${state.selectedUser?.name} ${state.selectedUser?.lastname}") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                state.selectedUser?.let { user ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        AsyncImage(
                            model = user.picture,
                            contentDescription = stringResource(R.string.user_image_description),
                            placeholder = ColorPainter(MaterialTheme.colorScheme.background),
                            modifier = Modifier
                                .size(256.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .shadow(8.dp, RoundedCornerShape(8.dp)),
                            contentScale = ContentScale.Fit
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "${user.name} ${user.lastname}",
                            style = MaterialTheme.typography.headlineSmall
                        )
                    }
                    Spacer(Modifier.height(16.dp))

                    Row {
                        Text(text = "Email: ", fontWeight = FontWeight.Bold)
                        Text(text = user.email)
                    }

                    Row {
                        Text(text = "Phone: ", fontWeight = FontWeight.Bold)
                        ClickablePhoneText(phone = user.phone)
                    }

                    Row {
                        Text(text = "Gender: ", fontWeight = FontWeight.Bold)
                        Text(text = user.gender)
                    }

                    Row {
                        Text(text = "Date of birth: ", fontWeight = FontWeight.Bold)
                        Text(text = "${formatDate(user.dateOfBirth)} (${user.age} years old)")
                    }

                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Address: ")
                        }
                        append("${user.country}, ${user.state}, ${user.city}, ${user.street} ${user.houseNumber}")
                    })

                    Row {
                        Text(text = "Coordinates: ", fontWeight = FontWeight.Bold)
                        Text(text = "${user.coordinates.latitude}, ${user.coordinates.longitude}")
                    }

                    Row {
                        Text(text = "Date of registration: ", fontWeight = FontWeight.Bold)
                        Text(text = formatDate(user.dateOfRegistration))
                    }
                } ?: run {
                    Text(text = stringResource(R.string.user_is_not_selected))
                }
            }
        }
    )
}

@Composable
fun ClickablePhoneText(phone: String) {
    val context = LocalContext.current
    Text(
        text = phone,
        color = MaterialTheme.colorScheme.primary,
        textDecoration = TextDecoration.Underline,
        modifier = Modifier.clickable {
            val intent = Intent(Intent.ACTION_DIAL, "tel:$phone".toUri())
            context.startActivity(intent)
        }
    )
}