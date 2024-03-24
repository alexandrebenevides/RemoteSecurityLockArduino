package com.github.alexandrebenvides.remotesecuritylockarduino.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.alexandrebenvides.remotesecuritylockarduino.R

class LockControlActivity : AppCompatActivity() {
    private var passwordField: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_lock_control)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        passwordField = findViewById(R.id.passwordField)
    }

    fun disconnectBluetooth(view: View) {
        if (ConnectActivity.bluetoothConnection?.close() == true) {
            finish()
        }
    }

    fun unlock(view: View) {
        if (passwordField?.text.toString() != "") {
            ConnectActivity.bluetoothConnection?.sendMessage("a:" + passwordField?.text.toString())
        }
    }

    fun lock(view: View) {
        ConnectActivity.bluetoothConnection?.sendMessage("f:0")
    }
}