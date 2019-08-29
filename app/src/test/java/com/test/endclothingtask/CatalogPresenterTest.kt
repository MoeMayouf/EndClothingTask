package com.test.endclothingtask

import com.test.endclothingtask.data.model.Catalog
import com.test.endclothingtask.data.model.Products
import com.test.endclothingtask.domain.CatalogUseCase
import com.test.endclothingtask.presentation.catalog.CatalogPresenter
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CatalogPresenterTest {
    @RelaxedMockK
    lateinit var catalogUseCase: CatalogUseCase

    @RelaxedMockK
    lateinit var view: CatalogPresenter.CatalogView

    private val sut by lazy { CatalogPresenter(catalogUseCase) }

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `check presenter is being attached`() {
        assert(sut.view != null)
    }

    @Test
    fun `get road status success`() {
        val products: List<Products> = listOf(
            Products(
                1,
                "Test Shirt",
                "£199",
                "https://media.endclothing.com/media/f_auto,q_auto,w_760,h_760/prodmedia/media/catalog/product/2/6/26-03-2018_gosha_rubchinskiyxadidas_copaprimeknitboostmidsneaker_yellow_g012sh12-220_ka_1.jpg"
            )
        )
        val catalog =
            Catalog(
                products,
                "Exercise Listing",
                50
            )

        every { catalogUseCase.execute() } returns Single.just(catalog)
        sut.onViewAttached(view)

        verify { view.showLoading() }
        verify { view.showCatalogProducts(catalog) }
        verify(exactly = 0) { view.showError(any()) }
    }

    @Test
    fun `get road status error io exception`() {

        every { catalogUseCase.execute() } returns Single.error(IOException("error_message"))
        sut.onViewAttached(view)

        verify { view.showLoading() }
        verify { view.showError("error_message") }
        verify(exactly = 0) { view.showCatalogProducts(any()) }
    }

    @Test
    fun `check presenter is being detached`() {
        sut.onViewDetached()
        assert(sut.view == null)
    }
}