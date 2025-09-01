package com.example.csgocaseswatcherapp.features.addcasefragment.view.entities

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.BackgroundDecorations
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcasefragment.view.AddCaseViewState

@Composable
fun AddCaseScreen(
    state: AddCaseViewState,
    onNameChanged: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onAddCaseClicked: () -> Unit,
    onSuggestionClicked: (String) -> Unit
) {

    when (state) {
        is AddCaseViewState.Loading -> LoadingScreen()
        is AddCaseViewState.Content -> AddCaseContent(
            state = state,
            onNameChanged = onNameChanged,
            onAmountChanged = onAmountChanged,
            onPriceChanged = onPriceChanged,
            onAddCaseClicked = onAddCaseClicked,
            onSuggestionClicked = onSuggestionClicked
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCaseContent(
    state: AddCaseViewState.Content,
    onNameChanged: (String) -> Unit,
    onAmountChanged: (String) -> Unit,
    onPriceChanged: (String) -> Unit,
    onAddCaseClicked: () -> Unit,
    onSuggestionClicked: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
    ) {
        BackgroundDecorations(modifier = Modifier.matchParentSize())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = AppTheme.dimensions.paddingML, vertical = AppTheme.dimensions.paddingL),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = state.caseName,
                    onValueChange = onNameChanged,
                    label = { Text("Case Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                        .onFocusChanged { focusState ->
                            if (focusState.isFocused) {
                                expanded = true
                            }
                        }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    state.caseNameSuggestionList.forEach { suggestion ->
                        DropdownMenuItem(
                            text = { Text(suggestion) },
                            onClick = {
                                onSuggestionClicked(suggestion)
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = state.amount,
                onValueChange = onAmountChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Amount of cases") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                )
            )

            OutlinedTextField(
                value = state.price,
                onValueChange = onPriceChanged,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Purchase price") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                )
            )

            Spacer(Modifier.weight(1f))

            Button(
                onClick = onAddCaseClicked,
                enabled = state.isAddCaseButtonActive,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(AppTheme.dimensions.paddingXS)
            ) {
                Text("Add Case")
            }
        }
    }
}


@Preview
@Composable
fun AddCaseContentPreview() {
    AppTheme(dynamicColor = false) {
        AddCaseScreen(
            state = AddCaseViewState.Content(
                caseName = "Chroma Case",
                amount = "2",
                price = "2.4",
                caseNameSearchQuery = "Chroma",
                isAddCaseButtonActive = false,
                caseNameSuggestionList = listOf()
            ),
            onNameChanged = {},
            onAmountChanged = {},
            onPriceChanged = {},
            onAddCaseClicked = {},
            onSuggestionClicked = {}

        )
    }
}

