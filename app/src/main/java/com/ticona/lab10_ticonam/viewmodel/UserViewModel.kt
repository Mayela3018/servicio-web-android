package com.ticona.lab10_ticonam.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ticona.lab10_ticonam.data.model.User
import com.ticona.lab10_ticonam.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Estados posibles de la UI
 * Sealed class asegura que solo existen estos 3 estados
 */
sealed class UserUiState {
    object Loading : UserUiState() // Cargando datos
    data class Success(val users: List<User>) : UserUiState() // Datos cargados exitosamente
    data class Error(val message: String) : UserUiState() // Error al cargar
}

/**
 * ViewModel que maneja la lógica de negocio y el estado de la UI
 * Sobrevive a cambios de configuración (rotación de pantalla)
 */
class UserViewModel : ViewModel() {

    // Repositorio para obtener los datos
    private val repository = UserRepository()

    // Lista completa de usuarios (almacenada para filtrar)
    private var allUsers: List<User> = emptyList()

    // StateFlow privada y mutable (solo el ViewModel puede modificarlo)
    private val _uiState = MutableStateFlow<UserUiState>(UserUiState.Loading)

    // StateFlow pública e inmutable (la UI solo puede observarlo)
    val uiState: StateFlow<UserUiState> = _uiState

    // StateFlow para el texto de búsqueda
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    init {
        // Cargar usuarios automáticamente al crear el ViewModel
        loadUsers()
    }

    /**
     * Función pública para cargar o recargar usuarios
     * Maneja los diferentes estados: Loading, Success, Error
     */
    fun loadUsers() {
        // viewModelScope se cancela automáticamente cuando el ViewModel se destruye
        viewModelScope.launch {
            _uiState.value = UserUiState.Loading

            try {
                // Intentar obtener usuarios del repositorio
                allUsers = repository.getUsers()
                filterUsers(_searchQuery.value) // Aplicar filtro actual (incluso si está vacío)
            } catch (e: Exception) {
                // Capturar cualquier error (red, parseo, etc.)
                _uiState.value = UserUiState.Error(
                    e.message ?: "Error desconocido al cargar usuarios"
                )
            }
        }
    }

    /**
     * Actualiza el texto de búsqueda y filtra la lista de usuarios
     */
    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        filterUsers(query)
    }

    /**
     * Filtra la lista de usuarios según el texto de búsqueda
     * Busca en los campos 'name' y 'email' (ignorando mayúsculas/minúsculas)
     */
    private fun filterUsers(query: String) {
        val filtered = if (query.isBlank()) {
            allUsers
        } else {
            allUsers.filter { user ->
                user.name.contains(query, ignoreCase = true) ||
                        user.email.contains(query, ignoreCase = true)
            }
        }
        _uiState.value = UserUiState.Success(filtered)
    }
}