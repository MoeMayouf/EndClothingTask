package com.test.endclothingtask.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Catalog (

	@SerializedName("products") val products : List<Products>,
	@SerializedName("title") val title : String,
	@SerializedName("product_count") val product_count : Int
)