package com.test.endclothingtask.data.service

import com.test.endclothingtask.data.model.Catalog
import io.reactivex.Single
import retrofit2.http.GET

interface ServiceApi {

    @GET("example.json")
    fun getCatalog(): Single<Catalog>
}