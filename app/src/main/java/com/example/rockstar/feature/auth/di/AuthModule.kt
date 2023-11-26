package com.example.rockstar.feature.auth.di

import com.example.rockstar.feature.auth.data.repository.AuthRepoImpl
import com.example.rockstar.feature.auth.domain.repository.AuthRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Singleton
    @Provides
    fun providesFirebaseAuth():FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth):AuthRepo{
        return AuthRepoImpl(firebaseAuth)
    }
}