package com.test.endclothingtask.presentation.catalog

import com.test.endclothingtask.data.model.Catalog
import com.test.endclothingtask.domain.CatalogUseCase
import com.test.endclothingtask.presentation.base.BasePresenter
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class CatalogPresenter @Inject constructor(
    private val catalogUseCase: CatalogUseCase
) : BasePresenter<CatalogPresenter.CatalogView>() {

    private val compositeDisposable = CompositeDisposable()

    override fun onViewAttached(view: CatalogView) {
        super.onViewAttached(view)
        view.showLoading()
        compositeDisposable.add(
            catalogUseCase.execute()
                .subscribe({ value ->
                    view.showCatalogProducts(value)
                },
                    {
                        view.showError(it.message)
                    })
        )
    }

    interface CatalogView : BasePresenter.View {
        fun showLoading()
        fun showCatalogProducts(catalog: Catalog)
        fun showError(message: String?)
    }
}