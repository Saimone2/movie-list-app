package com.saimone.movielistapp.features_app.domain.util

sealed class OrderType {
    data object Ascending : OrderType()
    data object Descending : OrderType()
}