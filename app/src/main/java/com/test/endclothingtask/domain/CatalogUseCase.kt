package com.test.endclothingtask.domain

import com.test.endclothingtask.data.model.Catalog
import com.test.endclothingtask.data.service.ApiManager
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatalogUseCase @Inject constructor(private val apiManager: ApiManager) {

    fun execute(): Single<Catalog> {
        return apiManager
            .getCatalog()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}