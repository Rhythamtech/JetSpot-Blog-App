package com.rhytham.jetspot.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.accept
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

object ApiClient {

    @OptIn(ExperimentalSerializationApi::class)
    var client = HttpClient(Android){

        install(Logging){
            level = LogLevel.ALL
        }

        install(HttpTimeout){
            requestTimeoutMillis = 5000L
            connectTimeoutMillis = 5000L
            socketTimeoutMillis = 5000L
        }

        install(ContentNegotiation){
            json(Json{
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
                explicitNulls = false
            })

        }

        install(DefaultRequest){
            header(HttpHeaders.ContentType,ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }
}