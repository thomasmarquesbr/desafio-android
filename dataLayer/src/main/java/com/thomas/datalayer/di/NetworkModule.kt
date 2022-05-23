package com.thomas.datalayer.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.thomas.datalayer.BuildConfig
import com.thomas.datalayer.features.users.remote.UsersAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val picPayRestApiScopeName = "PICPAY_REST_API"
val networkModule = module {

    factory {
        provideHttpLoggingInterceptor()
    }

    factory {
        providesOkHttpClient(
            get() as HttpLoggingInterceptor
        )
    }

    factory {
        providesGson()
    }

    single(named(picPayRestApiScopeName)) {
        provideRetrofit(
            okHttpClient = get(),
            gson = get()
        )
    }

    factory<UsersAPI> {
        provideApi(retrofit = get(named(picPayRestApiScopeName)))
    }

}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

fun providesGson(): Gson = GsonBuilder().create()

fun provideRetrofit(
    okHttpClient: OkHttpClient,
    gson: Gson
): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

fun providesOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

inline fun <reified T> provideApi(retrofit: Retrofit): T = retrofit.create(T::class.java)
