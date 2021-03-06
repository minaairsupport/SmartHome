package com.massive.smarthome.data.remote

import com.massive.smarthome.BuildConfig
import com.massive.smarthome.data.remote.moshiFactories.MyKotlinJsonAdapterFactory
import com.massive.smarthome.data.remote.moshiFactories.MyStandardJsonAdapters
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

private const val timeoutRead = 3000   //In milliseconds
private const val contentType = "Content-Type"
private const val contentTypeValue = "application/json"
private const val timeoutConnect = 3000   //In milliseconds
private const val baseUrl = "http://storage42.com/modulotest/"

@Singleton
class ServiceGenerator @Inject constructor(){

    private val okHttpBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
    private val retrofit: Retrofit


    private var headerInterceptor = Interceptor { chain ->

        val original = chain.request()
        val request  = original.newBuilder()
            .header(contentType, contentTypeValue)
            .method(original.method , original.body)
            .build()

        chain.proceed(request)
    }

    private val logger: HttpLoggingInterceptor
                 get() {
                     val loggerInterceptor = HttpLoggingInterceptor()
                     if(BuildConfig.DEBUG){
                         loggerInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
                     }
                     return loggerInterceptor
                 }

    init {
        var client = okHttpBuilder.addInterceptor(headerInterceptor)
                        .addInterceptor(logger)
                        .connectTimeout(timeoutConnect.toLong(), TimeUnit.MILLISECONDS)
                        .readTimeout(timeoutRead.toLong() , TimeUnit.MILLISECONDS)
                        .build()

          retrofit = Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                        .client(client)
                        .build()
    }

    fun <S> createService (serviceClass: Class<S>) : S {
        return retrofit.create(serviceClass)
    }

    private fun getMoshi(): Moshi {
        return Moshi.Builder()
            .add(MyKotlinJsonAdapterFactory())
            .add(MyStandardJsonAdapters.FACTORY)
            .build()
    }


}