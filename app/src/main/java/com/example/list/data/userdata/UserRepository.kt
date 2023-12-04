package com.example.list.data.userdata

import com.example.list.sessionmanager.UserSessionManager
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userSessionManager: UserSessionManager
) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }
    suspend fun getUserName(userName: String): User?{
        return userDao.getUserByUsername(userName)
    }

    fun login(userName: String, password: String): Boolean{
        return userDao.login(userName, password)
    }

    fun getUserByUsername(userName: String): User {
        return userDao.getUserByUsername(userName)
    }

    fun getAllUsers(): Flow<List<User>> {
        return userDao.getAllUsers()
    }

    fun getUserById(userId: Int): User {
        return userDao.getUserById(userId)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    private fun hashPassword(password: String): String {
        //impement hashting algortm for password or dont
        // you can use BCrypt
        return password
    }

    suspend fun logout() {
       userSessionManager.logout()
    }
}
