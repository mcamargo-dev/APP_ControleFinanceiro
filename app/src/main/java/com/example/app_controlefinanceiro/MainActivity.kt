package com.example.app_controlefinanceiro

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
                TelaExtrato()
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



@Composable
fun TelaExtrato() {

    var pesquisa by remember {
        mutableStateOf("")
    }

    var categoria by remember {
        mutableStateOf("Todas")
    }

    val extratos = listOf(
        listOf(
            "Almoço Restaurante",
            "Alimentação",
            "- R$ 38,50",
            "HOJE - 28 AGOSTO"
        ),
        listOf(
            "Farmácia",
            "Saúde",
            "- R$ 27,90",
            "HOJE - 28 AGOSTO"
        ),
        listOf(
            "Uber",
            "Transporte",
            "- R$ 18,40",
            "HOJE - 28 AGOSTO"
        ),
        listOf(
            "Supermercado",
            "Alimentação",
            "- R$ 450,00",
            "26 AGOSTO"
        ),
        listOf(
            "Ônibus",
            "Transporte",
            "- R$ 6,50",
            "26 AGOSTO"
        ),
        listOf(
            "Medicamento",
            "Saúde",
            "- R$ 42,00",
            "26 AGOSTO"
        )
    )

    val resultado = extratos.filter { extrato ->

        val nome = extrato[0]
        val categoriaExtrato = extrato[1]
        val valor = extrato[2]

        val pesquisaCorreta =
            nome.contains(pesquisa, ignoreCase = true) ||
                    valor.contains(pesquisa, ignoreCase = true)

        val categoriaCorreta =
            categoria == "Todas" ||
                    categoriaExtrato == categoria

        pesquisaCorreta && categoriaCorreta
    }

    Scaffold(
        containerColor = Color(0xFFF7F8FA)
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {

            Text(
                text = "Extrato de Contas",
                fontSize = 27.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF172033)
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {

                OutlinedTextField(
                    value = pesquisa,
                    onValueChange = {
                        pesquisa = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("🔍 Buscar por nome ou valor...")
                    },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedBorderColor = Color(0xFFD8DEE6),
                        unfocusedBorderColor = Color(0xFFD8DEE6)
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            FiltroCategorias(
                categoriaSelecionada = categoria,
                aoSelecionar = {
                    categoria = it
                }
            )

            Spacer(
                modifier = Modifier.height(22.dp)
            )

            val hoje = resultado.filter { extrato ->
                extrato[3] == "HOJE - 28 AGOSTO"
            }

            if (hoje.isNotEmpty()) {

                Text(
                    text = "HOJE - 28 AGOSTO",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF617087)
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                for (extrato in hoje) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(
                            1.dp,
                            Color(0xFFDCE1E7)
                        ),
                        shape = RoundedCornerShape(0.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 14.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = extrato[0],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF172033)
                                )

                                Text(
                                    text = extrato[1],
                                    fontSize = 14.sp,
                                    color = Color(0xFF8792A3)
                                )
                            }

                            Text(
                                text = extrato[2],
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB5232D)
                            )
                        }
                    }
                }
            }

            val dia26 = resultado.filter { extrato ->
                extrato[3] == "26 AGOSTO"
            }

            if (dia26.isNotEmpty()) {

                Text(
                    text = "26 AGOSTO",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF617087)
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                for (extrato in dia26) {

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 14.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        border = BorderStroke(
                            1.dp,
                            Color(0xFFDCE1E7)
                        ),
                        shape = RoundedCornerShape(0.dp)
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    horizontal = 15.dp,
                                    vertical = 14.dp
                                ),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = extrato[0],
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF172033)
                                )

                                Text(
                                    text = extrato[1],
                                    fontSize = 14.sp,
                                    color = Color(0xFF8792A3)
                                )
                            }

                            Text(
                                text = extrato[2],
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFFB5232D)
                            )
                        }
                    }
                }
            }

            if (resultado.isEmpty()) {

                Text(
                    text = "Nenhum extrato encontrado",
                    fontSize = 15.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 20.dp)
                )
            }
        }
    }
}

@Composable
fun FiltroCategorias(
    categoriaSelecionada: String,
    aoSelecionar: (String) -> Unit
) {

    val categorias = listOf(
        "Todas",
        "Alimentação",
        "Saúde",
        "Transporte"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {

        for (categoria in categorias) {

            val selecionado =
                categoriaSelecionada == categoria

            Button(
                onClick = {
                    aoSelecionar(categoria)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =
                        if (selecionado) {
                            Color(0xFF2F6FE4)
                        } else {
                            Color.White
                        },
                    contentColor =
                        if (selecionado) {
                            Color.White
                        } else {
                            Color(0xFF172033)
                        }
                ),
                border =
                    if (selecionado) {
                        null
                    } else {
                        BorderStroke(
                            1.dp,
                            Color(0xFFDCE1E7)
                        )
                    },
                shape = RoundedCornerShape(25.dp)
            ) {

                Text(
                    text = categoria,
                    fontSize = 15.sp
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )
        }
    }
}