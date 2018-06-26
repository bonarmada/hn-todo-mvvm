package com.bonarmada.hn_todo_mvvm.di.module

import android.content.Context
import com.bonarmada.hn_todo_mvvm.di.scope.AppScope
import com.bumptech.glide.Glide
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber


@Module(includes = [ContextModule::class])
class NetworkModule(private val baseUrl: String) {

    @AppScope
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Timber.tag(NetworkModule::class.java.simpleName).d(message) }).setLevel(HttpLoggingInterceptor.Level.BODY)

    @AppScope
    @Provides
    fun provideRequestInterceptor(): Interceptor =
            Interceptor { chain ->
                chain.proceed(chain.request().newBuilder()
                        //TODO: Fetch token from sharedprefs/somewhere
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", "Bearer MzM4NEAyY2M3NWIzN2I2OGE4ODYyMWZiMmExNTc1ZDEyNjRkNDMwNGNlNjZkMTE2NDMwODBjNmIxYTM3NWViNGY3YThi")
                        .build())
            }

    @AppScope
    @Provides
    internal fun provideHttpClient(requestInterceptor: Interceptor, loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(requestInterceptor)
                    .addInterceptor(loggingInterceptor)
                    .build()

    @Provides
    @AppScope
    internal fun provideGson(): Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create()

    @AppScope
    @Provides
    internal fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit =
            Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl(baseUrl)
                    .client(httpClient)
                    .build()
}
