package com.massive.smarthome.di

import android.content.Context
import com.massive.smarthome.data.local.AppDao
import com.massive.smarthome.data.local.AppDataBase
import com.massive.smarthome.utils.NetworkConnectivity
import com.massive.smarthome.utils.NetworkConnectivitySource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideNetworkConnectivity(@ApplicationContext context: Context): NetworkConnectivitySource {
        return NetworkConnectivity(context)
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return AppDataBase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideAppDao( appDataBase: AppDataBase): AppDao {
        return appDataBase.appDao()
    }


}