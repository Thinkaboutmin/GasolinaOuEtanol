package com.trab.comparadorcombustivel

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.material.snackbar.Snackbar
import java.util.concurrent.Future
import java.util.concurrent.FutureTask
import kotlin.time.ExperimentalTime

class MainActivity : AppCompatActivity() {
    var recImage: ImageView? = null

    @ExperimentalTime
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recImage = ImageView(this)
        val layout = findViewById<LinearLayout>(R.id.layoutComp)
        layout.addView(recImage)

        btnComparar.setOnClickListener {
            if (editTextEtn.text.isNotEmpty() && editTextGas.text.isNotEmpty()) {
                var etnValue: Float? = editTextEtn.text.toString().toFloatOrNull()
                var gasValue: Float? = editTextGas.text.toString().toFloatOrNull()

                if (gasValue == null || etnValue == null) {
                    val snack = Snackbar.make(
                        findViewById(R.id.content), R.string.text_convert_error_float, 3000
                    )

                    snack.show()
                } else {
                    if (etnValue / gasValue <= 0.7) {
                        recImage!!.setImageDrawable(ResourcesCompat.getDrawable(
                            this.resources,
                            R.drawable.abasteca_etanol,
                            this.theme
                        ))
                    } else {
                        recImage!!.setImageDrawable(ResourcesCompat.getDrawable(
                            this.resources,
                            R.drawable.abasteca_gasolina,
                            this.theme
                        ))
                    }
                }

            } else {
                val snack = Snackbar.make(
                    findViewById(R.id.content), R.string.text_empty, 3000
                )

                snack.show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val params = recImage!!.layoutParams
        params.height = 250.dp // Taken from IntToDp
        params.width = 250.dp // Taken from IntToDp
    }
}