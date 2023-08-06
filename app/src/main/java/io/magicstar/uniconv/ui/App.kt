package io.magicstar.uniconv.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.magicstar.uniconv.R
import io.magicstar.uniconv.ui.theme.*

import io.magicstar.uniconv.unit.*
import java.nio.file.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("MutableCollectionMutableState")
@Composable
fun App() {
    val magnitudes = setOf(
        stringResource(R.string.length),
        stringResource(R.string.weight),
        stringResource(R.string.time),
        stringResource(R.string.heat),
        stringResource(R.string.area),
        stringResource(R.string.volume),
        stringResource(R.string.speed),
        stringResource(R.string.electric_current),
        stringResource(R.string.power)
    )
    var magnitude by remember { mutableStateOf(magnitudes.elementAt(0)) }

    var enabled by remember { mutableStateOf(false) }

    var magnExpanded by remember { mutableStateOf(false) }
    var unit1Expanded by remember { mutableStateOf(false) }
    var unit2Expanded by remember { mutableStateOf(false) }

    var value by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    var reference by remember { mutableStateOf(length_reference) }
    var unit1 by remember { mutableStateOf(reference.keys.elementAt(0)) }
    var unit2 by remember { mutableStateOf(reference.keys.elementAt(1)) }

    enabled = value != ""
    reference = updateMagnitudes(magnitudes, magnitude)

    val keyboardController = LocalSoftwareKeyboardController.current

    UniconvTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(R.drawable.uniconv) ,
                    contentDescription = "App logo",
                    modifier = Modifier.size(90.dp)
                )
                Text(
                    text = "Uniconv",
                    fontSize = 30.sp
                )
                Row(
                    modifier = Modifier
                        .padding(vertical = 14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                    )
                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        expanded = magnExpanded,
                        onExpandedChange = { magnExpanded = !magnExpanded }
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = magnitude,
                            onValueChange = { },
                            label = {
                                Text(
                                    modifier = Modifier.background(Color.Transparent),
                                    text = stringResource(id = R.string.magnitude),
                                    style = TextStyle(background = Color.Transparent)
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    Icons.Filled.KeyboardArrowDown,
                                    null,
                                    Modifier.rotate(if (magnExpanded) 180f else 0f)
                                )
                            },
                            shape = CircleShape,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0x5590CAF9),
                                focusedContainerColor = if (magnExpanded) Color.White
                                else Color(0x5590CAF9)
                            )
                        )

                        ExposedDropdownMenu(
                            expanded = magnExpanded,
                            onDismissRequest = { magnExpanded = false }
                        ) {
                            magnitudes.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        magnExpanded = false

                                        magnitude = it
                                        reference = updateMagnitudes(magnitudes, magnitude)

                                        unit1 = reference.keys.elementAt(0)
                                        unit2 = reference.keys.elementAt(1)
                                    }
                                )
                            }
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                    )
                }

                OutlinedTextField(
                    modifier = Modifier.padding(horizontal = 5.dp),
                    shape = CircleShape,
                    label = {
                        Text(
                            text = stringResource(R.string.value)
                        )
                    },
                    value = value,
                    onValueChange = {
                        value = it
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0x5590CAF9),
                        focusedContainerColor = Color.White
                    ),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Go
                    ),
                    keyboardActions = KeyboardActions(
                        onGo = {
                            result = if (magnitude == magnitudes.elementAt(3))
                                    "${heatConvert(value.toDouble(), unit1, unit2)} $unit2"
                                else
                                    "${conversion(value.toDouble(), reference[unit1]!!, reference[unit2]!!)} $unit2"
                            keyboardController!!.hide()
                        }
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 20.dp,
                            vertical = 10.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.67f),
                        expanded = unit1Expanded,
                        onExpandedChange = {
                            unit1Expanded = !unit1Expanded
                        }
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = unit1,
                            onValueChange = { },
                            label = { Text(stringResource(id = R.string.origin)) },
                            trailingIcon = {
                                Icon(
                                    Icons.Filled.KeyboardArrowDown,
                                    null,
                                    Modifier.rotate(if (unit1Expanded) 180f else 0f)
                                )
                            },
                            shape = CircleShape,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0x5590CAF9),
                                focusedContainerColor = if (unit1Expanded) Color.White
                                    else Color(0x5590CAF9)
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = unit1Expanded,
                            onDismissRequest = {
                                unit1Expanded = false
                            }
                        ) {
                            reference.keys.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        unit1 = it
                                        unit1Expanded = false
                                    }
                                )
                            }
                        }
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.1f)
                            .padding(horizontal = 2.dp),
                        text = stringResource(R.string.to),
                        textAlign = TextAlign.Center
                    )

                    ExposedDropdownMenuBox(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.69f),
                        expanded = unit2Expanded,
                        onExpandedChange = {
                            unit2Expanded = !unit2Expanded
                        }
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.menuAnchor(),
                            readOnly = true,
                            value = unit2,
                            onValueChange = { },
                            label = { Text(stringResource(id = R.string.target)) },
                            trailingIcon = {
                                Icon(
                                    Icons.Filled.KeyboardArrowDown,
                                    null,
                                    Modifier.rotate(if (unit2Expanded) 180f else 0f)
                                )
                            },
                            shape = CircleShape,
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0x5590CAF9),
                                focusedContainerColor = if (unit2Expanded) Color.White
                                else Color(0x5590CAF9)
                            )
                        )
                        ExposedDropdownMenu(
                            expanded = unit2Expanded,
                            onDismissRequest = {
                                unit2Expanded = false
                            }
                        ) {
                            reference.keys.forEach {
                                DropdownMenuItem(
                                    text = { Text(it) },
                                    onClick = {
                                        unit2 = it
                                        unit2Expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                Button(
                    modifier = Modifier.padding(vertical = 5.dp),
                    enabled = enabled,
                    onClick = {
                        result = if (magnitude == magnitudes.elementAt(3))
                            "${heatConvert(value.toDouble(), unit1, unit2)} $unit2"
                        else
                            "${conversion(value.toDouble(), reference[unit1]!!, reference[unit2]!!)} $unit2"
                    },

                ) {
                    Text(
                        text = stringResource(id = R.string.convert),
                        fontSize = 17.5.sp
                    )
                }
                SelectionContainer {
                    Text(
                        text = result,
                        fontSize = 25.sp
                    )
                }
            }
    }
}

fun updateMagnitudes(magnitudes: Set<String>, magnitude: String): HashMap<String, Double> {
    return when (magnitude) {
        magnitudes.elementAt(0) -> length_reference
        magnitudes.elementAt(1) -> weight_reference
        magnitudes.elementAt(2) -> time_reference
        magnitudes.elementAt(3) -> heat_reference
        magnitudes.elementAt(4) -> surface_reference
        magnitudes.elementAt(5) -> volume_reference
        magnitudes.elementAt(6) -> speed_reference
        magnitudes.elementAt(7) -> elec_current_reference
        magnitudes.elementAt(8) -> power_reference
        else -> length_reference
    }
}