package com.example.propertyxpo.data

import com.example.propertyxpo.data.login.LoginService
import com.example.propertyxpo.data.meeting.MeetingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkServiceHelper {

    @Singleton
    @Provides
    fun providesLoginService(retrofit: Retrofit): LoginService{
        return retrofit.create(LoginService::class.java)
    }

    @Singleton
    @Provides
    fun providesMeetingService(retrofit: Retrofit): MeetingService{
        return retrofit.create(MeetingService::class.java)
    }
}