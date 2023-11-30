package com.example.list.di

import com.example.list.sessionmanager.ListSessionManager
import com.example.list.sessionmanager.UserSessionManager
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
}

    /*

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


    @Provides
    @Singleton
    fun providesFirebaseRepository(): FirebaseRepository{
        return FirebaseRepository()
    }

}
 */