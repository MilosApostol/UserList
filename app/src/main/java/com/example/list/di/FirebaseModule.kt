package com.example.list.di

import com.example.list.data.firebase.items.FirebaseRepository
import com.example.list.data.firebase.items.FirebaseViewModel
import com.example.list.data.firebase.items.auth.AuthRepositoryImpl
import com.example.list.sessionmanager.ListSessionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun providesFireStoreInstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun providesListManager(): ListSessionManager {
        return ListSessionManager()
    }

    @Provides
    @Singleton
    fun provideItemsRepository(): FirebaseRepository {
        return FirebaseRepository()
    }

    @Provides
    @Singleton
    fun provideFirebaseViewModel(repository: FirebaseRepository): FirebaseViewModel {
        return FirebaseViewModel(repository)
    }

    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesAuthRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepositoryImpl {
        return AuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

/*
    @Provides
    @Singleton
    fun providesDatabaseRepositoryImpl(): DatabaseRepository {
        return DatabaseRepositoryImpl()
    }

 */
}