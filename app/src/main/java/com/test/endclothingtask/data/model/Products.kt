package com.test.endclothingtask.data.model

import com.google.gson.annotations.SerializedName


data class Products(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("price") val price: String,
    @SerializedName("image") val image: String
)