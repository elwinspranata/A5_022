package com.example.projectpam.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.getValue
import com.example.projectpam.ui.navigation.DestinasiNavigasi

object DestinasiHomeUtama : DestinasiNavigasi {
    override val route = "Home utama"
    override val titleRes = "Home Utama"
}

@Composable
fun HomeUtama(
    onDokterClick: () -> Unit,
    onHewanClick: () -> Unit,
    onJenisHewanClick: () -> Unit,
    onPerawatanClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        isVisible = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF1E88E5), // Biru gelap
                        Color(0xFF6AB7FF)  // Biru muda
                    )
                )
            )
    ) {
        // Title
        Text(
            text = "Menu Utama",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 40.dp, bottom = 24.dp)
                .align(Alignment.CenterHorizontally)
        )

        // Main content with buttons
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Buttons
            ModernButton(text = "Dokter", icon = Icons.Filled.Person, onClick = onDokterClick)
            Spacer(modifier = Modifier.height(16.dp))
            ModernButton(text = "Hewan", icon = Icons.Filled.CheckCircle, onClick = onHewanClick)
            Spacer(modifier = Modifier.height(16.dp))
            ModernButton(text = "Jenis Hewan", icon = Icons.Filled.CheckCircle, onClick = onJenisHewanClick)
            Spacer(modifier = Modifier.height(16.dp))
            ModernButton(text = "Perawatan", icon = Icons.Filled.AddCircle, onClick = onPerawatanClick)
        }
    }
}

@Composable
fun ModernButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White, // Tombol putih
            contentColor = Color(0xFF1E88E5) // Teks biru gelap
        ),
        shape = RoundedCornerShape(20.dp), // Sudut sangat melengkung
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp) // Tinggi tombol lebih besar
            .shadow(6.dp, RoundedCornerShape(20.dp)) // Bayangan lembut
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(28.dp), // Ikon lebih besar
                tint = Color(0xFF1E88E5) // Warna ikon biru gelap
            )
            Spacer(modifier = Modifier.width(12.dp)) // Jarak antara ikon dan teks
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
