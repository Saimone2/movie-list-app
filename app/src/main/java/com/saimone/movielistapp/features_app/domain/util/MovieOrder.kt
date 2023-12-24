package com.saimone.movielistapp.features_app.domain.util

sealed class MovieOrder(val orderType: OrderType) {
    class Title(orderType: OrderType) : MovieOrder(orderType)
    class ReleaseDate(orderType: OrderType) : MovieOrder(orderType)

    fun copy(orderType: OrderType): MovieOrder {
        return when (this) {
            is Title -> Title(orderType)
            is ReleaseDate -> ReleaseDate(orderType)
        }
    }
}