package com.example.s8104425_assignment2.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//annotate the Application class to enable Hilt in order to inject dependencies anywhere in the app
@HiltAndroidApp //initializes dependencies injection at app start
class MyApp : Application(){//Application -- acts as the base class
    //this class in entry point of the app and initializes Hilt automatically
}