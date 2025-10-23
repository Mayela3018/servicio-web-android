package com.ticona.lab10_ticonam.data.remote

import com.ticona.lab10_ticonam.data.model.User
import retrofit2.http.GET

/**
 * Interfaz que define los endpoints de la API
 * Retrofit implementará automáticamente esta interfaz
 */
interface ApiService {


    @GET("users")
    suspend fun getUsers(): List<User>
}