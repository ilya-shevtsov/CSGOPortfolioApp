package com.example.csgocaseswatcherapp.features.addcase.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.BackgroundDecorations
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.view.entities.CaseSuggestionItem


@Composable
fun AddCaseScreen(
    state: AddCaseViewState,
    onAction: (AddCaseAction) -> Unit,
) {
    LaunchedEffect(Unit) { onAction(AddCaseAction.OnCreate) }

    when (state) {
        is AddCaseViewState.Loading -> LoadingScreen()
        is AddCaseViewState.Content -> AddCaseContent(
            state = state,
            onAction = onAction,
        )

        is AddCaseViewState.Error -> ErrorScreen()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCaseContent(
    state: AddCaseViewState.Content,
    onAction: (AddCaseAction) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }


    //temporary solution until I know how to move this to AppTheme
    val outlinedTextFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = AppTheme.colors.onSurface,
        unfocusedTextColor = AppTheme.colors.onSurface,

        cursorColor = AppTheme.colors.primary,

        focusedBorderColor = AppTheme.colors.primary,
        unfocusedBorderColor = AppTheme.colors.onSurface.copy(alpha = 0.30f),

        focusedLabelColor = AppTheme.colors.primary,
        unfocusedLabelColor = AppTheme.colors.onSurface.copy(alpha = 0.60f),

        errorTextColor = AppTheme.colors.onError,
        errorBorderColor = AppTheme.colors.error,
        errorLabelColor = AppTheme.colors.error,
        errorCursorColor = AppTheme.colors.error,
    )

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
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(MenuAnchorType.PrimaryEditable)
                        .onFocusChanged { f -> hasFocus = f.isFocused },
                    value = state.name,
                    onValueChange = { newValue ->
                        onAction(AddCaseAction.OnNameChanged(newValue))
                    },
                    supportingText = { state.nameError?.let { ShowSupportText(stringResource(it)) } },
                    label = { Text("Case Name") },
                    singleLine = true,
                    colors = outlinedTextFieldColors
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .exposedDropdownSize(matchTextFieldWidth = true)
                        .heightIn(max = 320.dp),
                    containerColor = AppTheme.colors.surface,
                    tonalElevation = 2.dp,
                    shadowElevation = 8.dp
                ) {

                    val suggestions = state.caseNameSuggestionList

                    suggestions.forEachIndexed { index, suggestionData ->
                        CaseSuggestionItem(
                            suggestion = suggestionData,
                            onClick = {
                                onAction(AddCaseAction.OnSuggestionClicked(suggestionData.name))
                                expanded = false
                            }
                        )

                        if (index < suggestions.lastIndex) {
                            HorizontalDivider(
                                modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingML),
                                color = AppTheme.colors.onSurface.copy(alpha = 0.08f),
                                thickness = 2.dp
                            )
                        }
                    }
                }
            }

            OutlinedTextField(
                value = state.amount,
                onValueChange = { newValue -> onAction(AddCaseAction.OnAmountChanged(newValue)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Amount of cases") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                supportingText = { state.amountError?.let { ShowSupportText(stringResource(it)) } },
                colors = outlinedTextFieldColors
            )

            OutlinedTextField(
                value = state.price,
                onValueChange = { newValue ->
                    onAction(AddCaseAction.OnPriceChanged(newValue))
                },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Purchase price") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done
                ),
                supportingText = { state.priceError?.let { ShowSupportText(stringResource(it)) } },
                colors = outlinedTextFieldColors
            )

            Spacer(Modifier.weight(1f))

            Button(
                colors = ButtonColors(
                    containerColor = AppTheme.colors.primary,
                    contentColor = AppTheme.colors.onPrimary,
                    disabledContainerColor = AppTheme.colors.onSurface.copy(alpha = 0.12f),
                    disabledContentColor = AppTheme.colors.onSurface.copy(alpha = 0.38f)
                ),
                onClick = { onAction(AddCaseAction.OnAddCaseClicked) },
                enabled = state.isAddCaseButtonActive,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(AppTheme.dimensions.paddingXS)
            ) {
                Text("Add Case")
            }
        }
    }
}

@Composable
fun ShowSupportText(
    error: String?,
) {
    error?.let {
        Text(
            text = it,
            color = AppTheme.colors.error,
            style = AppTheme.typography.m3.bodySmall
        )
    }
}

@PreviewLightDark
@Composable
fun AddCaseContentPreview() {
    AppTheme {
        AddCaseScreen(
            state = AddCaseViewState.Content(
                name = "Chroma Case", amount = "37", price = "3.14",
                caseNameSearchQuery = "Chroma",
                isAddCaseButtonActive = false,
                caseNameSuggestionList = listOf(
                    AddCaseSuggestion(
                        name = "Chroma Case",
                        imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case"
                    ),
                    AddCaseSuggestion(
                        name = "Chroma 2 Case",
                        imageUrl = "https://api.steamapis.com/image/item/730/Chroma%202%20Case"
                    )
                )
            ),
            onAction = {},
        )
    }
}