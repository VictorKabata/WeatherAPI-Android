package com.vickbt.shared.data.network

import com.vickbt.shared.BuildKonfig
import com.vickbt.shared.domain.utils.Constants.BASE_URL
import com.vickbt.shared.domain.utils.Constants.URL_PATH
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.client.HttpClient
import io.ktor.client.plugins.addDefaultResponseValidation
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.URLProtocol
import io.ktor.http.path
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object WeatherApiClient {

    /**Create custom instance of [HttpClient] used to authenticate, log and format REST network requests*/
    fun createWeatherApiClient(enableNetworkLogs: Boolean = false) = HttpClient {
        expectSuccess = true
        addDefaultResponseValidation()

        // Append api key on all requests
        defaultRequest {
            url {
                protocol = URLProtocol.HTTPS
                host = BASE_URL
                path(URL_PATH)
                parameters.append(
                    "key",
                    BuildKonfig.API_KEY
                )
            }
        }

        // Enable logging network requests if app is debug variant
        if (enableNetworkLogs) {
            install(Logging) {
                level = LogLevel.HEADERS
                logger = object : Logger {
                    override fun log(message: String) {
                        Napier.i(tag = "Http Client", message = message)
                    }
                }
            }.also {
                Napier.base(DebugAntilog())
            }
        }

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                }
            )
        }
    }
}
