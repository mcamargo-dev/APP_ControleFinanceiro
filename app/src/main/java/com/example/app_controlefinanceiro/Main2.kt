package com.example.app_controlefinanceiro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_controlefinanceiro.ui.theme.App_ControleFinanceiroTheme

class Main2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_ControleFinanceiroTheme {
                dashboardVisaoGeral()
            }
        }
    }
}
//vai commmit AAAAAAAA
@Preview(showBackground = true)
@Composable
fun dashboardVisaoGeral() {
    var filtroSelecionado by remember { mutableStateOf("Todas") }
    var mostrarTransacao by remember { mutableStateOf(true) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
                containerColor = Color.Blue
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Adicionar", tint = Color.White)
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Text(
                    text = "Visão Geral",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                cardSaldoHero()

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        onClick = { filtroSelecionado = "Todas" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (filtroSelecionado == "Todas") Color.Blue else Color.LightGray
                        )
                    ) {
                        Text("Todas", color = if (filtroSelecionado == "Todas") Color.White else Color.Black)
                    }

                    Button(
                        onClick = { filtroSelecionado = "Alimentação" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (filtroSelecionado == "Alimentação") Color.Blue else Color.LightGray
                        )
                    ) {
                        Text("Alimentação", color = if (filtroSelecionado == "Alimentação") Color.White else Color.Black)
                    }

                    Button(
                        onClick = { filtroSelecionado = "Transporte" },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (filtroSelecionado == "Transporte") Color.Blue else Color.LightGray
                        )
                    ) {
                        Text("Transporte", color = if (filtroSelecionado == "Transporte") Color.White else Color.Black)
                    }
                }

                if (mostrarTransacao) {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        border = BorderStroke(1.dp, Color.LightGray)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = "Combustível",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color.Black
                                )
                                Text(
                                    text = "- R$ 180,00",
                                    color = Color.Red,
                                    fontSize = 14.sp
                                )
                            }

                            Row {
                                IconButton(onClick = {}) {
                                    Icon(Icons.Filled.Edit, contentDescription = "Editar", tint = Color.Gray)
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                IconButton(onClick = { mostrarTransacao = false }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Deletar", tint = Color.Red)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun cardSaldoHero() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF263238))
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "Saldo Consolidado", color = Color.LightGray, fontSize = 14.sp)

            Text(
                text = "R$ 2.450,00",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "▲ R$ 3.500,00", color = Color.Green, fontSize = 14.sp)
                Text(text = "▼ R$ 1.050,00", color = Color.Red, fontSize = 14.sp)
            }
        }
    }
}