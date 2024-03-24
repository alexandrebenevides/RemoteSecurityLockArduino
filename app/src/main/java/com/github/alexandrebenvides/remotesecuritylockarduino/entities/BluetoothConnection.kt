package com.github.alexandrebenvides.remotesecuritylockarduino.entities

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat.getSystemService
import java.io.IOException
import java.io.OutputStream
import java.util.UUID

class BluetoothConnection(context: Context, private var bluetoothAdapter: BluetoothAdapter?) {
    private var bluetoothDevice: BluetoothDevice? = null
    private var bluetoothSocket: BluetoothSocket? = null
    private val bluetoothMac = "00:18:E4:40:00:06"
    private val uuid: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
    private var context: Context? = context
    private var outputStream: OutputStream? = null

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
            Log.e("Erro ao conectar no dispositivo Bluetooth.", e.message.toString())
            Toast.makeText(context, "A conexão Bluetooth não foi estabelecida.", Toast.LENGTH_SHORT).show()
            false
        }
    }

    fun close(): Boolean {
        if (bluetoothSocket != null) {
            return try {
                bluetoothSocket?.close()
                bluetoothSocket = null
                Toast.makeText(context, "Bluetooth desconectado.", Toast.LENGTH_SHORT).show()
                true
            } catch (e: IOException) {
                Log.e("Erro ao desconectar no dispositivo Bluetooth.", e.message.toString())
                Toast.makeText(context, "Erro ao desconectar. A conexão Bluetooth permanece estabelecida.", Toast.LENGTH_SHORT).show()
                false
            }
        }

        Toast.makeText(context, "Não há nenhuma conexão Bluetooth estabelecida a ser desconectada", Toast.LENGTH_SHORT).show()
        return true
    }

    fun sendMessage(message: String): Boolean {
        val msgBuffer = message.toByteArray()

        if (bluetoothSocket != null) {
            return try {
                outputStream = bluetoothSocket?.outputStream
                outputStream?.write(msgBuffer)
                true
            } catch (e: IOException) {
                Log.e("Erro ao enviar mensagem.", e.message.toString())
                Toast.makeText(context, "Erro ao enviar mensagem.", Toast.LENGTH_LONG).show()
                false
            }
        }

        Toast.makeText(context, "O dispositivo Bluetooth não está conectado.", Toast.LENGTH_LONG).show()
        return false
    }
}