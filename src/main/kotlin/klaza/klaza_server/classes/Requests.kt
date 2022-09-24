package klaza.klaza_server.classes

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response

class Requests(
    var baseurl: String,
    var authBearer: String,
) {

    fun get(url: String): Response {

        val request = OkHttpClient().newBuilder().build().newCall(
            Request.Builder()
                .url("$baseurl/${url.replace("/", "")}")
                .method("GET", null)
                .addHeader("Authorization", "Bearer $authBearer")
                .build()
        ).execute()

        request.close()

        return request

    }

    fun post(url: String, body: String): Response {

        val request = OkHttpClient().newBuilder().build().newCall(
            Request.Builder()
                .url("$baseurl/${url.replace("/", "")}")
                .method("POST", body.toRequestBody("application/json".toMediaTypeOrNull()))
                .addHeader("Authorization", "Bearer $authBearer")
                .build()
        ).execute()

        request.close()

        return request

    }

}