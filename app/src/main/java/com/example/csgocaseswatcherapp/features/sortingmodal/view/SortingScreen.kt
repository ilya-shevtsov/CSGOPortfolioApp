package com.example.csgocaseswatcherapp.features.sortingmodal.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.core.ui.theme.AppTheme
import com.example.csgocaseswatcherapp.features.sortingmodal.entities.SortingMethod

@Composable
fun SortingScreen(
    state: SortingModalViewState,
    onClick: (method: SortingMethod) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.dimensions.paddingL)
    ) {
        Text(
            "Sort",
            style = MaterialTheme.typography.titleMedium,
            color = AppTheme.colors.primary
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.sortingEntryList.forEach { entry ->
            Button(
                onClick = {
                    onClick(entry.method)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(
                        horizontal = AppTheme.dimensions.paddingML,
                        vertical = AppTheme.dimensions.paddingM
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppTheme.colors.primary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp),
                contentPadding = PaddingValues(),
            ) {
                Text(
                    text = entry.name,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview
@Composable
fun SortingScreenPreview() {
    AppTheme { SortingScreen(state = SortingModalViewState(listOf()), onClick = {}) }
}
