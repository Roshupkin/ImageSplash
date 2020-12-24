package android.com.roshchupkin.unsplashapp.di

import android.com.roshchupkin.unsplashapp.network.service.UnsplashAPI
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RetrofitModule {
    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpInterceptor(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson, client: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl("https://api.unsplash.com/")
            .addConverterFactory(GsonConverterFactory.create(gson)).client(client)
    }

    @Singleton
    @Provides
    fun provideUnsplashAPI(retrofit: Retrofit.Builder): UnsplashAPI {
        return retrofit
            .build()
            .create(UnsplashAPI::class.java)

    }
}