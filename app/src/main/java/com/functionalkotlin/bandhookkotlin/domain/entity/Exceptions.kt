package com.functionalkotlin.bandhookkotlin.domain.entity

sealed class APIError

data class AlbumNotFound(val id: String) : APIError()

data class ArtistNotFound(val id: String) : APIError()

data class TopAlbumsNotFound(val id: String) : APIError()

object RecommendationNotFound: APIError()
