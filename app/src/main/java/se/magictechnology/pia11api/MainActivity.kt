package se.magictechnology.pia11api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import okio.IOException
import java.net.URL

class MainActivity : AppCompatActivity() {

    var capi = ChuckAPI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        capi.loadjoke {
            runOnUiThread {
                findViewById<TextView>(R.id.jokeTextview).text = it.value
            }
        }

        findViewById<Button>(R.id.newJokeButton).setOnClickListener {
            capi.loadjoke {
                runOnUiThread {
                    findViewById<TextView>(R.id.jokeTextview).text = it.value
                }
            }
        }
    }



}

