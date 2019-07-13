package com.sonnek.sendemail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

abstract class MainActivity : AppCompatActivity() {

    private lateinit var TO: EditText
    private lateinit var Predmet: EditText
    private lateinit var Zprava: EditText
    private lateinit var Odeslat: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        TO = findViewById(R.id.edit_text_to)
        Predmet = findViewById(R.id.edit_text_subject)
        Zprava = findViewById(R.id.edit_text_message)

        Odeslat = findViewById(R.id.button_send)

        Odeslat.setOnClickListener { sendMail() }
    }

    private fun sendMail() {
        val prijemceList = TO.text.toString()
        val prijemce = prijemceList.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()

        val predmet = Predmet.text.toString()
        val zprava = Zprava.text.toString()

        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_EMAIL, prijemce)
        intent.putExtra(Intent.EXTRA_SUBJECT, prijemce)
        intent.putExtra(Intent.EXTRA_TEXT, zprava)
        intent.type = "messagee/rfc822"
        startActivity(Intent.createChooser(intent, "Vyberte e-mailového klienta"))

    }
}
