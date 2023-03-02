package se.magictechnology.pia11api

import android.util.Log
import android.widget.TextView
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException

class ChuckAPI {

    private val client = OkHttpClient()

    fun loadjoke(callback: (Chuckjoke) -> Unit) {
        val req = Request.Builder().url("https://api.chucknorris.io/jokes/random").build()

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("PIA11DEBUG", "HÄMTNING INTE OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var responseString = response.body!!.string()

                    val thejoke = Json { ignoreUnknownKeys = true}.decodeFromString<Chuckjoke>(responseString)
                    callback(thejoke)
                }
            }
        })
    }


    fun loaddata() {
        // https://api.chucknorris.io/jokes/random

        // Detta är fel
        /*
        var chuckurl = URL("https://api.chucknorris.io/jokes/random")
        var chuckstring = chuckurl.readText()
        Log.i("PIA11DEBUG", chuckstring)
        */

        val req = Request.Builder().url("https://api.chucknorris.io/jokes/random").build()

        /*
        client.newCall(req).execute().use { response ->
            if(response.isSuccessful) {
                Log.i("PIA11DEBUG", "HÄMTNING OK")
                Log.i("PIA11DEBUG", response.body!!.string())
            } else {
                Log.i("PIA11DEBUG", "HÄMTNING INTE OK")
            }
        }
        */

        client.newCall(req).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("PIA11DEBUG", "HÄMTNING INTE OK")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var responseString = response.body!!.string()

                    Log.i("PIA11DEBUG", "HÄMTNING OK")
                    Log.i("PIA11DEBUG", responseString)

                    dojsonstuff(responseString)
                }
            }
        })
    }


    fun dojsonstuff(thejson : String) {
        Log.i("PIA11DEBUG", "dojsonstuff")
        Log.i("PIA11DEBUG", thejson)

        //val moshi = Moshi.Builder().build()
        //val jsonAdapter : JsonAdapter<Chuckjoke> = moshi.adapter<Chuckjoke>()

        val thejoke = Json { ignoreUnknownKeys = true}.decodeFromString<Chuckjoke>(thejson)

        Log.i("PIA11DEBUG", thejoke.value)

        /*
        runOnUiThread {
            findViewById<TextView>(R.id.jokeTextview).text = thejoke.value
        }
        */

    }
}