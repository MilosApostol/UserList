package com.example.list.items

import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import javax.inject.Inject

class ItemDataSource @Inject constructor(private val storageReference: StorageReference) {

    private val storage = FirebaseStorage.getInstance()

    suspend fun uploadItem(itemId: String, data: ByteArray): Task<UploadTask.TaskSnapshot> {
        val itemRef = storageReference.child("$itemId.txt")
        return itemRef.putBytes(data)
    }

    suspend fun downloadItem(itemId: String): Task<ByteArray>{
        val itemRef = storageReference.child("$itemId.txt")
        val maxSize: Long = 1024 * 1024
        return itemRef.getBytes(maxSize)
    }

    suspend fun deleteItem(itemId: String): Task<Void>{
        val itemRef = storageReference.child("$itemId.txt")
        return itemRef.delete()
    }

    suspend fun getItemById(itemId: String): Task<ByteArray>{
        val itemRef = storageReference.child("$itemId.txt")
        val maxSize: Long = 1024 * 1024
        return itemRef.getBytes(maxSize)
    }
}