package com.example.demoapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.SendCountExceedException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.takeFrom
import io.ktor.util.encodeBase64
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import java.security.MessageDigest
import java.security.cert.X509Certificate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.concurrent.atomic.AtomicBoolean
import javax.net.ssl.X509TrustManager
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val driveSyncUrl = "aviva-uat.drivesync.com"
        var cioClient: HttpClient? = null

        val result: String = runBlocking {
            val isResumed = AtomicBoolean(false)

            suspendCoroutine { continuation ->
                cioClient = createCioClient(driveSyncUrl) {
                    if (isResumed.getAndSet(true).not()) {
                        continuation.resume(it)
                    }
                }

                try {
                    Log.d(
                        "MY_TAG",
                        "downloadServerCertificates: from url: ${"https://${driveSyncUrl}:443"}"
                    )

                    Log.d("MY_TAG", "start async")
                    runBlocking {
                        val httpResponse: HttpResponse? = cioClient?.get {
                            url.takeFrom("https://${driveSyncUrl}:443")
                        }
                        Log.d(
                            "MY_TAG",
                            "downloadServerCertificates: httpResponse: $httpResponse"
                        )
                    }
                } catch (error: SendCountExceedException) {

                    error.printStackTrace()
                } finally {
                    cioClient?.close()
                }
            }
        }


        Log.d("MY_TAG", "onCreate: result: $result")
    }

    private fun generateNetworkSecurityConfig(
        certificateExpiration: String,
        certificateFingerptint: String,
        driveSyncUrl: String,
    ): String {

        if (certificateExpiration.isEmpty()) {
            throw IllegalArgumentException(
                "certificateExpiration is empty"
            )
        }
        if (certificateFingerptint.isEmpty()) {
            throw IllegalArgumentException(
                "certificateFingerptint is empty"
            )
        }
        return contentSecured(
            driveSyncUrl = driveSyncUrl,
            certificateExpiration = certificateExpiration,
            certificateFingerptint = certificateFingerptint
        )
    }

    private fun createCioClient(driveSyncUrl: String, action: (String) -> Unit): HttpClient {
        return HttpClient(CIO) {
            engine {
                https {
                    trustManager = object : X509TrustManager {
                        override fun checkServerTrusted(
                            chain: Array<out X509Certificate>?,
                            s: String?
                        ) {
                            Log.d(
                                "MY_TAG",
                                "downloadServerCertificates: driveSyncUrl:$driveSyncUrl"
                            )
                            Log.d("MY_TAG", "downloadServerCertificates: chain.size=${chain?.size}")
                            chain?.firstOrNull()?.also { certificate ->
                                val expiration = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                                    .withZone(ZoneOffset.UTC)
                                    .format(certificate.notAfter.toInstant())
                                val fingerprint = MessageDigest.getInstance("SHA-256")
                                    .digest(certificate.publicKey.encoded).encodeBase64()

                                Log.d(
                                    "MY_TAG",
                                    "downloadServerCertificates: " +
                                            "fingerptint=$fingerprint" +
                                            " expiration=$expiration"
                                )
                                Log.d(
                                    "MY_TAG",
                                    "downloadServerCertificates: certificate=$certificate"
                                )
                                action.invoke(
                                    generateNetworkSecurityConfig(
                                        certificateExpiration = expiration,
                                        certificateFingerptint = fingerprint,
                                        driveSyncUrl = driveSyncUrl
                                    )
                                )
                            }
                        }

                        override fun getAcceptedIssuers(): Array<X509Certificate> = emptyArray()
                        override fun checkClientTrusted(
                            chain: Array<out X509Certificate>?,
                            sslEngine: String?
                        ) = Unit
                    }
                }
            }
        }
    }

    companion object {
        private fun contentSecured(
            driveSyncUrl: String,
            certificateExpiration: String,
            certificateFingerptint: String
        ): String =
            """
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config>
        <domain includeSubdomains="true">$driveSyncUrl</domain>
        <pin-set expiration="$certificateExpiration">
            <pin digest="SHA-256">$certificateFingerptint</pin>
        </pin-set>
    </domain-config>
</network-security-config>
            """.trimIndent()
    }

}


interface Client {

    suspend fun onCall(): String
}

class ClientImpl(private val action: (String) -> Unit) : Client {
    override suspend fun onCall(): String {
        delay(3000)
        action.invoke("resume result")

        return "Call result"
    }

}
