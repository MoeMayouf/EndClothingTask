package com.test.endclothingtask.common.dagger.session

import com.test.endclothingtask.data.service.ApiManager
import com.test.endclothingtask.data.service.ServiceApi
import com.test.endclothingtask.common.dagger.AppComponent
import com.test.endclothingtask.common.dagger.CommonOkHttpClientBuilderModule
import com.test.endclothingtask.common.dagger.catalog.CatalogModule
import com.test.endclothingtask.common.dagger.catalog.SessionScope
import dagger.Component

@Component(
    dependencies = [AppComponent::class],
    modules = [CatalogModule::class, CommonOkHttpClientBuilderModule::class]
)
@SessionScope
interface SessionComponent : AppComponent {
    fun apiManager(): ApiManager
    fun iService(): ServiceApi

}