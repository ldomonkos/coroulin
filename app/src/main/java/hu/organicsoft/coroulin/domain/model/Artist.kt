package hu.organicsoft.coroulin.domain.model

import java.io.Serializable

data class Artist(val name: String, val numberOfListeners: Int, val imageUrl: String?): Serializable
