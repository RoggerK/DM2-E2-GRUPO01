package idat.edu.pe.dm2.grupo01.clinica.common

import android.app.Application

class MyApplication: Application() {
    companion object {
        lateinit var instance: MyApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}