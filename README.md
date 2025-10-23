# 📱 Laboratorio 10: Servicio Web en Android con Retrofit y Jetpack Compose

> ✨ Aplicación Android que consume datos de una API pública y los muestra con una interfaz moderna, reactiva y funcional.

![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Retrofit](https://img.shields.io/badge/Retrofit-ED3B3B?style=for-the-badge&logo=retrofit&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?style=for-the-badge&logo=kotlin&logoColor=white)
![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)

---

## 🎯 Objetivo
Desarrollar una aplicación Android utilizando **Jetpack Compose** que consuma un **servicio web real** mediante **Retrofit**, permitiendo listar, buscar y visualizar usuarios de forma eficiente y con una experiencia de usuario moderna.

---

## 🌐 API Utilizada
- **Endpoint**: [`https://jsonplaceholder.typicode.com/users`](https://jsonplaceholder.typicode.com/users)
- **Tipo**: REST API pública (solo lectura, sin autenticación)
- **Propósito**: Proveer datos ficticios para pruebas y demostraciones

---

## ✨ Funcionalidades Implementadas

### 🔍 Búsqueda en tiempo real
- Filtro dinámico por **nombre** o **correo electrónico**
- Campo de búsqueda con íconos de lupa y limpiar
- Resultados actualizados al instante mientras escribes

### 📋 Visualización de datos
Cada tarjeta de usuario muestra **6 campos**:
- `ID`
- `Nombre`
- `Email`
- `Teléfono`
- `Username` *(campo extra - reto)*
- `Website` *(campo extra - reto)*

### ⚙️ Manejo de estados
- **Cargando**: `CircularProgressIndicator` mientras se obtienen los datos
- **Éxito**: Lista de usuarios en `LazyColumn` con diseño limpio
- **Error**: Mensaje amigable + botón **"Reintentar"**
- **Sin resultados**: Mensaje cuando la búsqueda no coincide

### 🎨 Diseño moderno
- Componentes de **Material 3**
- Tarjetas con bordes redondeados y sombra
- Colores coherentes y jerarquía visual clara
- `TopAppBar` personalizada
- Espaciado y tipografía optimizados

---

## 🛠️ Tecnologías y Dependencias

| Categoría        | Tecnología / Librería                     |
|------------------|-------------------------------------------|
| Lenguaje         | Kotlin                                    |
| UI               | Jetpack Compose                           |
| Networking       | Retrofit + Gson Converter                 |
| Concurrencia     | Kotlin Coroutines                         |
| Arquitectura     | ViewModel + StateFlow                     |
| Gestión de estado| Sealed classes + MutableStateFlow         |
| Gradle           | Version Catalog (`libs.versions.toml`)    |

---

## 📁 Estructura del Proyecto
com.ticona.lab10_ticonam/
├── data/
│ ├── model/ → User.kt
│ ├── remote/ → ApiService.kt
│ └── repository/ → UserRepository.kt
├── ui/
│ ├── screen/ → UserListScreen.kt
│ └── viewmodel/ → UserViewModel.kt
├── MainActivity.kt
└── ui/theme/ → Tema personalizado (opcional)
