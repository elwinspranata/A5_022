package com.example.projectpam

import android.app.Application
import com.example.projectpam.dl.AppContainer
import com.example.projectpam.dl.AppContainerImpl

class DokterApplications : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl()
    }
}


