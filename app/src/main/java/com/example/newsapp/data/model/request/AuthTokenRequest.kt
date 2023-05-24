package com.example.newsapp.data.model.request

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AuthTokenRequest(

    @SerializedName("client_id")
    val clientId: String,

    @SerializedName("client_secret")
    val clientSecret: String,

    @SerializedName("grant_type")
    val grantType: String,

    ) : Serializable