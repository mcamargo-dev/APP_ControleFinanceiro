package com.example.app_controlefinanceiro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app_controlefinanceiro.ui.theme.App_ControleFinanceiroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            App_ControleFinanceiroTheme {

                novaTransacao()

            }
        }
    }
}
@Preview
@Composable
fun novaTransacao() {

    val contexto = LocalContext.current

    var valor by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var data by remember { mutableStateOf("") }

    var isDespesa by remember { mutableStateOf(true) }

    Scaffold { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxSize(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                        cabecalhoSimples()

                        Row(modifier = Modifier.fillMaxWidth()) {

                            Button(
                                onClick = { isDespesa = true },
                                modifier = Modifier.weight(1f).padding(end = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (isDespesa) Color.Red else Color.LightGray
                                )
                            ) {
                                Text(
                                    "Despesa (-)",
                                    color = if (isDespesa) Color.White else Color.Black
                                )
                            }

                            Button(
                                onClick = { isDespesa = false },
                                modifier = Modifier.weight(1f).padding(start = 4.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = if (!isDespesa) Color.Green else Color.LightGray
                                )
                            ) {
                                Text(
                                    "Receita (+)",
                                    color = if (!isDespesa) Color.White else Color.Black
                                )
                            }
                        }
                    }
                    OutlinedTextField(
                        value = valor,
                        onValueChange = { valor = it },
                        label = { Text("VALOR DA TRANSAÇÃO") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = descricao,
                        onValueChange = { descricao = it },
                        label = { Text("DESCRIÇÃO") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = categoria,
                        onValueChange = { categoria = it },
                        label = { Text("CATEGORIA") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = data,
                        onValueChange = { data = it },
                        label = { Text("DATA") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Button(
                    onClick = {
                        Toast.makeText(contexto, "Salvando...", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                ) {
                    Text("SALVAR TRANSAÇÃO")
                }
            }
        }
    }
}

@Composable
fun cabecalhoSimples(){

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "<- Nova Transação",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

    }

}