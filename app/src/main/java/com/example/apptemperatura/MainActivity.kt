package com.example.apptemperatura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.apptemperatura.ui.theme.AppTemperaturaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTemperaturaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConversorTemperatura()
                }
            }
        }
    }
}


@Composable
fun ConversorTemperatura() {
    var temperaturaFahrenheit by remember {
        mutableStateOf("")
    }

    var temperaturaCelsius by remember {
        mutableStateOf("")
    }

    var ResultadoCtoF by remember {
        mutableStateOf("0")
    }

    var ResultadoFtoC by remember {
        mutableStateOf("0")
    }

    Column(modifier = Modifier.padding(24.dp)) {
        Text(
            text = "ConverTemp!",
            fontSize = 38.sp,
            modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
        )

        Text(
            text = "Conversor de Temperatura",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Text(
            text = "Fahrenheit e Celsius.",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 28.dp)
        )

        Text(
            text = "Digite a temperatura em graus Fahrenheit",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = temperaturaFahrenheit,
            onValueChange = { temperaturaFahrenheit = it },
            label = {
                Text(text = "Graus Fahrenheit")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth ()
        )

        Text(
            text = "Digite a temperatura em graus Celsius",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        OutlinedTextField(
            value = temperaturaCelsius,
            onValueChange = { temperaturaCelsius = it },
            label = {
                Text(text = "Graus Celsius")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.fillMaxWidth ()
        )

        Spacer(modifier = Modifier.width(8.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (temperaturaFahrenheit.isNotBlank()) {
                    val tempF = temperaturaFahrenheit.toDouble()
                    ResultadoFtoC = "${fahrenheitParaCelsius(tempF)} °C"
                }
                if (temperaturaCelsius.isNotBlank()) {
                    val tempC = temperaturaCelsius.toDouble()
                    ResultadoCtoF = "${celsiusParaFahrenheit(tempC)} °F"
                }

            }, colors = ButtonDefaults.buttonColors(

            ), modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Converter")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (ResultadoFtoC.isNotBlank() || ResultadoCtoF.isNotBlank()) {
            Text(
                text = "Conversão de Fahrenheit para Celsius: $ResultadoFtoC"
            )
            Text(
                text = "Conversão de Celsius para Fahrenheit: $ResultadoCtoF"
            )
        }
    }
}

fun celsiusParaFahrenheit(temperaturaCelsius: Double): Double {
    return temperaturaCelsius * 1.8 + 32
}

fun fahrenheitParaCelsius(temperaturaFahrenheit: Double): Double {
    return (temperaturaFahrenheit - 32) / 1.8
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTemperaturaTheme {
        ConversorTemperatura()
    }
}