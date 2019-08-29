package com.test.endclothingtask.common.dagger.catalog

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.endclothingtask.common.Constants
import com.test.endclothingtask.data.service.ApiManager
import com.test.endclothingtask.data.service.ApiManagerImpl
import com.test.endclothingtask.data.service.ServiceApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class CatalogModule {

    @Provides
    @SessionScope
    @CatalogQualifier
    fun okClient(okHttpClientBuilder: OkHttpClient.Builder): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return okHttpClientBuilder
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @SessionScope
    fun roadService(
        @CatalogQualifier okClient: OkHttpClient,
        gson: Gson
    ): ServiceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okClient)
            .build()
            .create(ServiceApi::class.java)
    }

    @Provides
    @SessionScope
    fun roadApi(
        restService: ServiceApi
    ): ApiManager = ApiManagerImpl(restService)
}