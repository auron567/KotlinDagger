package com.example.kotlindagger.di

import com.example.kotlindagger.model.SharedPreferencesStorage
import com.example.kotlindagger.model.Storage
import dagger.Binds
import dagger.Module

// Tells Dagger this is a module
@Module
abstract class StorageModule {

    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: SharedPreferencesStorage): Storage
}