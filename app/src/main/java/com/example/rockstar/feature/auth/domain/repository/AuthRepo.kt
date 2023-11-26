package com.example.rockstar.feature.auth.domain.repository

import android.app.Activity
import com.example.rockstar.common.util.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepo {

    fun createUserWithPhoneNo(
        phone:String,
        activity: Activity
    ) : Flow<Resource<String>>

    fun signIn(
        otp:String
    ): Flow<Resource<String>>

}