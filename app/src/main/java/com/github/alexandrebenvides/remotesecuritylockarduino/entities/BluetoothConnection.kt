package com.github.alexandrebenvides.remotesecuritylockarduino.entities

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresPermission
import com.github.alexandrebenvides.remotesecuritylockarduino.activities.LockControlActivity
import java.io.IOException
import java.io.Serializable
import java.util.UUID

class BluetoothConnection(context: Context): Serializable {
    private var bluetoothDevice: BluetoothDevice? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var bluetoothSocket: BluetoothSocket? = null
    private val bluetoothMac = "00:18:E4:40:00:06"
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private var context: Context? = context

    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    fun start(): Boolean {
        if (!BluetoothAdapter.checkBluetoothAddress(bluetoothMac)) {
            Toast.makeText(context, "O endereço MAC do dispositivo Bluetooth não é válido.", Toast.LENGTH_SHORT).show()
        }

        bluetoothDevice = bluetoothAdapter?.getRemoteDevice(bluetoothMac)

        return try {
            bluetoothSocket = bluetoothDevice?.createInsecureRfcommSocketToServiceRecord(uuid)
            bluetoothSocket?.connect()

            Toast.makeText(context, "Bluetooth conectado.", Toast.LENGTH_SHORT).show()
            true
        } catch (e: IOException) {
            Log.e("Erro ao conectar no dispositivo Bluetooth", e.message.toString())
            Toast.makeText(context, "A conexão Bluetooth não foi estabelecida.", Toast.LENGTH_SHORT).show()
            false
        }
    }
}