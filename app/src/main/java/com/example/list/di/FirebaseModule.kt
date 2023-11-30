package com.example.list.di

import com.example.list.items.FirebaseData
import com.example.list.items.ItemDataSource
import com.example.list.loginout.ListSessionManager
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Singleton
    @Provides
    fun provideFirebaseData(): FirebaseData {
        return FirebaseData(FirebaseDatabase.getInstance())
    }

    @Provides
    @Singleton
    fun providesListSession(): ListSessionManager {
        return ListSessionManager()
    }
    @Provides
    @Singleton
    fun provideItemDataSource(firebaseStorage: FirebaseStorage): ItemDataSource {
        return ItemDataSource(firebaseStorage.reference.child("items"))
    }

    @Singleton
    @Provides
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

}
