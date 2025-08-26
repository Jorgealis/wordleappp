package com.example.wordleapp.data

import kotlin.random.Random

// CAMBIO: Creamos un mapa para almacenar palabras por categoría.
private val WORDS_BY_CATEGORY: Map<String, List<String>> = mapOf(
    "ANIMALS" to listOf("PERRO", "GATOS", "CABRA", "TIGRE", "LEONA", "RATON", "CISNE"),
    "COLORS" to listOf("ROJO", "VERDE", "AZUL", "BLANCO", "NEGRO", "AMARILLO", "NARANJA"),
    "SPORTS" to listOf("FUTBOL", "TENIS", "GOLF", "BOXEO", "NADAR", "CORRER", "CICLISMO"),
    "GENERAL" to listOf(
        "LIGHT", "PLANT", "ROBOT", "HOUSE", "SMILE", "GRAPE", "BRICK", "SMART", "CLOUD",
        "MUSIC", "WORLD", "INPUT", "QUERY", "PHONE", "WATER", "BREAD", "HEART", "BRAIN"
    )
).mapValues { entry -> entry.value.map { it.uppercase() } }

// CAMBIO: La lista de todas las palabras válidas ahora se genera a partir de todas las categorías.
private val ALL_WORDS: List<String> = WORDS_BY_CATEGORY.values.flatten()

/**
 * CAMBIO: La función ahora acepta una categoría para devolver una palabra de esa lista.
 * Si la categoría no es válida, devuelve una palabra de la lista general.
 */
fun getRandomWord(category: String, random: Random = Random(System.currentTimeMillis())): String {
    val wordList = WORDS_BY_CATEGORY[category.uppercase()] ?: WORDS_BY_CATEGORY["GENERAL"]!!
    return wordList.random(random)
}

/**
 * La validación ahora comprueba contra todas las palabras de todas las categorías.
 */
fun isValidWord(word: String): Boolean = ALL_WORDS.contains(word.uppercase())
