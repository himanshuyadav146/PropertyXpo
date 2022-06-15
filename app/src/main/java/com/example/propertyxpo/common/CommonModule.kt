package com.example.propertyxpo.common

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(
            "propertyx_preferences",
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun providesAppSharedPreferenceManager(sharedPreferences: SharedPreferences):AppSharedPreferencesManager{
        return AppSharedPreferencesManager(sharedPreferences)
    }

    @Provides
    @Named("Token")
    fun providesBearerToken(appSharedPreferencesManager: AppSharedPreferencesManager):String{
        return "Bearer ${appSharedPreferencesManager.getValueFromSharedPref(StringConstants.LOGIN_TOKEN,"")}"
    }
}