package com.ticona.lab10_ticonam.data.model

/**
 * Modelo de datos que representa un Usuario
 * Esta clase se mapea directamente con el JSON que retorna la API
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val username: String, // extra
    val website: String   // extra
)