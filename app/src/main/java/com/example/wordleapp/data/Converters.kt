package com.example.wordleapp.data


import androidx.room.TypeConverter
import java.util.Date

/**
 * Esta clase le dice a Room cómo convertir tipos de datos complejos
 * (como Date y List) a tipos simples que puede guardar en la base de datos.
 */
class Converters {
    /**
     * Convierte un número (Long) de la base de datos a un objeto Date.
     */
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    /**
     * Convierte un objeto Date a un número (Long) para guardarlo en la base de datos.
     */
    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    /**
     * Convierte una lista de Strings a un solo String, separando los elementos por comas.
     */
    @TypeConverter
    fun fromListString(list: List<String>?): String? {
        return list?.joinToString(",")
    }

    /**
     * Convierte un String (separado por comas) de nuevo a una lista de Strings.
     */
    @TypeConverter
    fun toListString(string: String?): List<String>? {
        return string?.split(",")?.map { it.trim() }
    }
}