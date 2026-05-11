package com.example.csgocaseswatcherapp.features.addcase.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MenuAnchorType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.example.csgocaseswatcherapp.core.ui.AppOutlinedTextField
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.addcase.domain.entities.AddCaseSuggestion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CaseNameTextFieldWithSuggestions(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    errorText: String?,
    suggestions: List<AddCaseSuggestion>,
    onSuggestionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var hasFocus by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current


    LaunchedEffect(hasFocus, value, suggestions) {
        expanded = hasFocus && value.isNotBlank() && suggestions.isNotEmpty()
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {},
        modifier = modifier
    ) {

        AppOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = label,
            error = errorText,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(MenuAnchorType.PrimaryEditable)
                .onFocusChanged { focusState -> hasFocus = focusState.isFocused }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .heightIn(max = 320.dp),
            containerColor = AppTheme.colors.surface,
            tonalElevation = AppTheme.dimensions.dropdownTonalElevation,
            shadowElevation = AppTheme.dimensions.dropdownShadowElevation
        ) {

            suggestions.forEachIndexed { index, suggestion ->
                CaseSuggestionItem(
                    suggestion = suggestion,
                    onClick = {
                        expanded = false
                        focusManager.clearFocus()
                        onSuggestionClick(suggestion.name)
                    }
                )

                if (index < suggestions.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = AppTheme.dimensions.paddingML),
                        color = AppTheme.colors.onSurface.copy(alpha = 0.08f),
                        thickness = AppTheme.dimensions.defaultDividerThickness
                    )
                }
            }
        }
    }
}