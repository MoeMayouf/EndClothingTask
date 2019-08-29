package com.test.endclothingtask.data.service

import com.test.endclothingtask.data.model.Catalog
import io.reactivex.Single

interface ApiManager {
    fun getCatalog(): Single<Catalog>
}