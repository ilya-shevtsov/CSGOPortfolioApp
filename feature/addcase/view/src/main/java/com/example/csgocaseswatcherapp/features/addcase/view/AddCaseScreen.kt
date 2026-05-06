package com.example.csgocaseswatcherapp.features.addcase.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.AppActionButton
import com.example.csgocaseswatcherapp.core.ui.AppOutlinedTextField
import com.example.csgocaseswatcherapp.core.ui.BackgroundDecorations
import com.example.csgocaseswatcherapp.core.ui.ErrorScreen
import com.example.csgocaseswatcherapp.core.ui.LoadingScreen
import com.example.csgocaseswatcherapp.core.ui.adaptive.DeviceConfigurationType
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewPortraitLandscapeDarkLight
import com.example.csgocaseswatcherapp.core.ui.preview.PreviewScreenWithTopBar
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcase.R
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion
import com.example.csgocaseswatcherapp.features.addcase.view.components.CaseNameTextFieldWithSuggestions

@Composable
fun AddCaseScreen(
    state: AddCaseViewState,
    onAction: (AddCaseAction) -> Unit,
    deviceConfigurationType: DeviceConfigurationType,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) { onAction(AddCaseAction.OnCreate) }

    when (state) {
        is AddCaseViewState.Error -> ErrorScreen()
        is AddCaseViewState.Loading -> LoadingScreen()
        is AddCaseViewState.Content -> AddCaseContent(
            state = state,
            onAction = onAction,
            modifier = modifier,
            deviceConfigurationType = deviceConfigurationType
        )
    }
}

@Composable
fun AddCaseContent(
    modifier: Modifier = Modifier,
    state: AddCaseViewState.Content,
    onAction: (AddCaseAction) -> Unit,
    deviceConfigurationType: DeviceConfigurationType
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(AppTheme.colors.background)
            .clickable { }
    ) {
        BackgroundDecorations(modifier = Modifier.matchParentSize())

        when (deviceConfigurationType) {
            DeviceConfigurationType.MOBILE_PORTRAIT -> {
                AddCaseForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = AppTheme.dimensions.paddingML,
                            vertical = AppTheme.dimensions.paddingL
                        )
                )
            }

            DeviceConfigurationType.MOBILE_LANDSCAPE -> {
                AddCaseForm(
                    state = state,
                    onAction = onAction,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .fillMaxWidth()
                        .widthIn(max = 420.dp)
                        .padding(AppTheme.dimensions.paddingL)
                )
            }
        }
    }
}

@Composable
private fun AddCaseForm(
    state: AddCaseViewState.Content,
    onAction: (AddCaseAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.paddingM)
    ) {
        CaseNameTextFieldWithSuggestions(
            value = state.name,
            onValueChange = { onAction(AddCaseAction.OnNameChanged(it)) },
            label = stringResource(R.string.case_name_label),
            errorText = state.nameError?.let { stringResource(it) },
            suggestions = state.caseNameSuggestionList,
            onSuggestionClick = { caseName ->
                onAction(AddCaseAction.OnSuggestionClicked(caseName))
            },
            modifier = Modifier.fillMaxWidth()
        )

        AppOutlinedTextField(
            value = state.amount,
            onValueChange = { onAction(AddCaseAction.OnAmountChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.amount_of_cases),
            error = state.amountError?.let { stringResource(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            )
        )

        AppOutlinedTextField(
            value = state.price,
            onValueChange = { onAction(AddCaseAction.OnPriceChanged(it)) },
            modifier = Modifier.fillMaxWidth(),
            label = stringResource(R.string.purchase_price),
            error = state.priceError?.let { stringResource(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Done
            )
        )

        Spacer(Modifier.weight(1f))

        AppActionButton(
            text = stringResource(R.string.add_case_button),
            onClick = { onAction(AddCaseAction.OnAddCaseClicked) },
            enabled = state.isAddCaseButtonActive,
            modifier = Modifier
                .align(Alignment.End)
                .padding(AppTheme.dimensions.paddingXS)
        )
    }
}

@PreviewPortraitLandscapeDarkLight
@Composable
private fun AddCaseContentPreview() {
    PreviewScreenWithTopBar(
        title = "Portfolio",
        canNavigateBack = true
    ) { deviceConfigurationType, paddingValues ->
        AddCaseScreen(
            modifier = Modifier.padding(paddingValues),
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
            deviceConfigurationType = deviceConfigurationType
        )
    }
}