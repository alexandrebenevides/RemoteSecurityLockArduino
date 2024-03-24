package com.github.alexandrebenvides.remotesecuritylockarduino.activities

import android.Manifest
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.alexandrebenvides.remotesecuritylockarduino.R
import com.github.alexandrebenvides.remotesecuritylockarduino.entities.BluetoothConnection
import java.io.IOException
import java.util.UUID

class ConnectActivity : AppCompatActivity() {
    private lateinit var enableBluetoothLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_connect)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        checkAndEnableBluetooth()
    }

    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    fun initBluetoothConnection(view: View) {
        val bluetoothConnection = BluetoothConnection(this)

        if (bluetoothConnection.start()) {
            val intent = Intent(this, LockControlActivity::class.java)
            intent.putExtra("bluetoothConnection", bluetoothConnection)
            startActivity(intent)
        }
    }

    private fun checkAndEnableBluetooth(): Boolean {
        val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val bluetoothAdapter = bluetoothManager.adapter

        enableBluetoothLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "O Bluetooth foi ativado com sucesso.", Toast.LENGTH_SHORT).show()
                return@registerForActivityResult
            }

            Toast.makeText(this, "Erro ao ativar o Bluetooth.", Toast.LENGTH_SHORT).show()
            return@registerForActivityResult
        }

        if (bluetoothAdapter == null) {
            Toast.makeText(this, "O dispositivo não suporta o Bluetooth.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!bluetoothAdapter.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothLauncher.launch(enableBluetoothIntent)
            return true
        }

        Toast.makeText(this, "O Bluetooth está ativo.", Toast.LENGTH_SHORT).show()
        return false
    }
}