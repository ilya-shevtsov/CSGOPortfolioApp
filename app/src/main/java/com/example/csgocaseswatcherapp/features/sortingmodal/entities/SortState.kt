package com.example.csgocaseswatcherapp.features.sortingmodal.entities

enum class SortState(val label: String) {
    NAME("Name"),
    AMOUNT("Amount"),
    PRICE("Price"),
    OVERALL_VALUE("Overall Value"),
    PROFIT_LOSS("Profit/Loss");

    override fun toString(): String = label
}

