package com.test.endclothingtask.presentation.catalog

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.test.endclothingtask.R
import com.test.endclothingtask.common.App
import com.test.endclothingtask.common.dagger.activity.ActivityComponent
import com.test.endclothingtask.common.dagger.activity.ActivityComponentHolder
import com.test.endclothingtask.common.dagger.activity.DaggerActivityComponent
import com.test.endclothingtask.data.model.Catalog
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), CatalogPresenter.CatalogView, ActivityComponentHolder {

    @Inject
    lateinit var catalogPresenter: CatalogPresenter
    private lateinit var adapter: CatalogAdapter
    private lateinit var linearLayoutManager: GridLayoutManager

    override fun showLoading() {
        prgMain.visibility = View.VISIBLE

    }

    override fun showCatalogProducts(catalog: Catalog) {
        prgMain.visibility = View.GONE
        //tvInfo.text = catalog.products[0].name
        adapter = CatalogAdapter(catalog)
        recyclerViewCatalog.adapter = adapter
    }

    override fun showError(message: String?) {
        Log.d("ERROR", message)
    }

    override val component: ActivityComponent by lazy {
        DaggerActivityComponent.builder()
            .sessionComponent((applicationContext as App).currentSession())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayoutManager = GridLayoutManager(this, 2)
        recyclerViewCatalog.layoutManager = linearLayoutManager

        component.inject(this)
        catalogPresenter.onViewAttached(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        catalogPresenter.onViewDetached()
    }
}
