package com.example.csgocaseswatcherapp.features.addcasefragment.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcasefragment.view.entities.AddCaseModel

@Composable
fun AddCaseScreen(
    state: AddCaseViewState,
    onAction: (AddCaseViewAction) -> Unit,
) {
    LaunchedEffect(Unit) { onAction(AddCaseViewAction.OnCreate) }

    when (state) {
        is AddCaseViewState.Loading -> LoadingScreen()
        is AddCaseViewState.Content -> AddCaseContent(
            state = state,
            onAction = onAction,
        )

        AddCaseViewState.Error -> ErrorScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCaseContent(
    state: AddCaseViewState.Content,
    onAction: (AddCaseViewAction) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }

    LaunchedEffect(hasFocus, state.caseNameSearchQuery, state.caseNameSuggestionList) {
        expanded = hasFocus &&
                state.caseNameSearchQuery.isNotEmpty() &&
                state.caseNameSuggestionList.isNotEmpty()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .clickable { }
    ) {
        BackgroundDecorations(modifier = Modifier.matchParentSize())

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = AppTheme.dimensions.paddingML,
                    vertical = AppTheme.dimensions.paddingL
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { willExpand -> expanded = willExpand }
            ) {
                OutlinedTextField(
                    value = state.name,
                    onValueChange = { newValue ->
                        onAction(AddCaseViewAction.OnNameChanged(newValue))
                    },
                    label = { Text("Case Name") },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable)
                        .onFocusChanged { f -> hasFocus = f.isFocused }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    state.caseNameSuggestionList
                        .take(8)
                        .forEach { suggestion ->
                            DropdownMenuItem(
                                text = { Text(suggestion) },
                                onClick = {
                                    onAction(AddCaseViewAction.OnSuggestionClicked(suggestion))
                                    expanded = false
                                }
                            )
                        }
                }
            }

            OutlinedTextField(
                value = state.amount,
                onValueChange = { newValue -> onAction(AddCaseViewAction.OnAmountChanged(newValue)) },
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
                onValueChange = { newValue -> onAction(AddCaseViewAction.OnPriceChanged(newValue)) },
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
                onClick = { onAction(AddCaseViewAction.OnAddCaseClicked) },
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
    AppTheme {
        AddCaseScreen(
            state = AddCaseViewState.Content(
                name = "Chroma Case", amount = "37", price = "3.14",
                caseNameSearchQuery = "Chroma",
                isAddCaseButtonActive = false,
                caseNameSuggestionList = listOf("Chroma Case", "Chroma 2 Case")
            ),
            onAction = {},
        )
    }
}

@Preview
@Composable
fun AddCaseContentPreviewDark() {
    AppTheme(darkTheme = true) {
        AddCaseScreen(
            state = AddCaseViewState.Content(
                name = "Chroma Case", amount = "37", price = "3.14",
                caseNameSearchQuery = "Chroma",
                isAddCaseButtonActive = false,
                caseNameSuggestionList = listOf("Chroma Case", "Chroma 2 Case")
            ),
            onAction = {},
        )
    }
}