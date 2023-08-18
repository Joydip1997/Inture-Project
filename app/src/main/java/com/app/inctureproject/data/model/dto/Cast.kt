package com.app.inctureproject.data.model.dto

data class Cast(
    val billing: Int,
    val category: String,
    val characters: List<String>,
    val roles: List<Role>
)