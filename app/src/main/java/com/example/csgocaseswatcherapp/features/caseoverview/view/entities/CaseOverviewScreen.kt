package com.example.csgocaseswatcherapp.features.caseoverview.view.entities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.csgocaseswatcherapp.features.caseoverview.view.CaseOverviewViewState

@Composable
fun CaseOverviewScreen(
    state: CaseOverviewViewState,
    onCaseClick: (CaseOverviewModel) -> Unit,
){

//    when (state){
//        is CaseOverviewViewState.Error -> {
//
//        }
//        is CaseOverviewViewState.Loading -> {
//
//        }
//        is CaseOverviewViewState.Content -> {
//            val items = state.caseOverviewItemList
//            LazyColumn {
//                items(
//                    items = items,
//                ) { item ->
//                    CaseOverviewItem(
//                        model = item,
//                        onClick = { onCaseClick(item) }
//                    )
//                }
//            }
//        }
//    }

}