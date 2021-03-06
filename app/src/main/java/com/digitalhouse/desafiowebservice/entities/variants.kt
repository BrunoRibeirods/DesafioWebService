package com.digitalhouse.desafiowebservice.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime
import java.io.Serializable
import java.security.cert.Extension
import java.sql.Date
import java.text.DateFormat
import java.util.*

data class Res(val data: Data)

data class Data(val offset: Int, var results: List<Hqs>)


data class ComicDate(
        val type: String,
        val date: String
): Serializable


data class ComicPrice(
        val type: String,
        val price: Float
)

data class Image(
        val path: String,
        val extension: String
): Serializable




