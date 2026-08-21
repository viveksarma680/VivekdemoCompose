package com.example.vivekdemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport {
        App()
    }
}

@Composable
fun App() {

    var display by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf<Double?>(null) }
    var operation by remember { mutableStateOf<String?>(null) }
    var shouldResetDisplay by remember { mutableStateOf(false) }

    fun numberClick(number: String) {
        if (display == "0" || shouldResetDisplay) {
            display = number
            shouldResetDisplay = false
        } else {
            display += number
        }
    }

    fun decimalClick() {
        if (shouldResetDisplay) {
            display = "0."
            shouldResetDisplay = false
        } else if (!display.contains(".")) {
            display += "."
        }
    }

    fun operationClick(op: String) {
        firstNumber = display.toDoubleOrNull()
        operation = op
        shouldResetDisplay = true
    }

    fun calculate() {

        val first = firstNumber ?: return
        val second = display.toDoubleOrNull() ?: return

        val result = when (operation) {
            "+" -> first + second
            "-" -> first - second
            "×" -> first * second
            "÷" -> {
                if (second == 0.0) {
                    Double.NaN
                } else {
                    first / second
                }
            }

            else -> second
        }

        display = if (result.isNaN()) {
            "Error"
        } else {
            result.toString().removeSuffix(".0")
        }

        firstNumber = null
        operation = null
        shouldResetDisplay = true
    }

    fun clear() {
        display = "0"
        firstNumber = null
        operation = null
        shouldResetDisplay = false
    }

    MaterialTheme {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                // Calculator container
                Surface(
                    modifier = Modifier
                        .width(420.dp),
                    shape = RoundedCornerShape(28.dp),
                    tonalElevation = 6.dp
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {

                        // Title
                        Text(
                            text = "Calculator",
                            style = MaterialTheme.typography.headlineSmall
                        )

                        Spacer(
                            modifier = Modifier.height(16.dp)
                        )

                        // Display
                        Surface(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(110.dp),
                            shape = RoundedCornerShape(20.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant
                        ) {

                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                horizontalAlignment = Alignment.End,
                                verticalArrangement = Arrangement.Center
                            ) {

                                if (operation != null && firstNumber != null) {
                                    Text(
                                        text = "${firstNumber} $operation",
                                        fontSize = 16.sp,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }

                                Text(
                                    text = display,
                                    fontSize = 38.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }

                        Spacer(
                            modifier = Modifier.height(20.dp)
                        )

                        // Row 1
                        CalculatorRow {

                            CalculatorButton(
                                text = "7",
                                onClick = { numberClick("7") }
                            )

                            CalculatorButton(
                                text = "8",
                                onClick = { numberClick("8") }
                            )

                            CalculatorButton(
                                text = "9",
                                onClick = { numberClick("9") }
                            )

                            OperatorButton(
                                text = "÷",
                                onClick = { operationClick("÷") }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Row 2
                        CalculatorRow {

                            CalculatorButton(
                                text = "4",
                                onClick = { numberClick("4") }
                            )

                            CalculatorButton(
                                text = "5",
                                onClick = { numberClick("5") }
                            )

                            CalculatorButton(
                                text = "6",
                                onClick = { numberClick("6") }
                            )

                            OperatorButton(
                                text = "×",
                                onClick = { operationClick("×") }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Row 3
                        CalculatorRow {

                            CalculatorButton(
                                text = "1",
                                onClick = { numberClick("1") }
                            )

                            CalculatorButton(
                                text = "2",
                                onClick = { numberClick("2") }
                            )

                            CalculatorButton(
                                text = "3",
                                onClick = { numberClick("3") }
                            )

                            OperatorButton(
                                text = "-",
                                onClick = { operationClick("-") }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Row 4
                        CalculatorRow {

                            CalculatorButton(
                                text = "0",
                                onClick = { numberClick("0") }
                            )

                            CalculatorButton(
                                text = ".",
                                onClick = { decimalClick() }
                            )

                            ClearButton(
                                onClick = { clear() }
                            )

                            OperatorButton(
                                text = "+",
                                onClick = { operationClick("+") }
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Equals
                        Button(
                            onClick = { calculate() },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                text = "=",
                                fontSize = 22.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CalculatorRow(
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        content = content
    )
}

@Composable
fun RowScope.CalculatorButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(60.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            contentColor = MaterialTheme.colorScheme.onSurface
        )
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}

@Composable
fun RowScope.OperatorButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(60.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = text,
            fontSize = 22.sp
        )
    }
}

@Composable
fun RowScope.ClearButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .weight(1f)
            .height(60.dp),
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.errorContainer,
            contentColor = MaterialTheme.colorScheme.onErrorContainer
        )
    ) {
        Text(
            text = "C",
            fontSize = 20.sp
        )
    }
}