package com.test.endclothingtask.data.service

import com.test.endclothingtask.data.model.Catalog
import io.reactivex.Single
import javax.inject.Inject

class ApiManagerImpl @Inject constructor(private val serviceApi: ServiceApi): ApiManager {
    override fun getCatalog(): Single<Catalog> {
        return serviceApi.getCatalog()
    }
}