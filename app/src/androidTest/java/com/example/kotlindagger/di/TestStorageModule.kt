package com.example.kotlindagger.di

import com.example.kotlindagger.model.FakeStorage
import com.example.kotlindagger.model.Storage
import dagger.Binds
import dagger.Module

// Overrides StorageModule in android tests
@Module
abstract class TestStorageModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
    @Binds
    abstract fun provideStorage(storage: FakeStorage): Storage
}