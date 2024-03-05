package com.rodcollab.androidrodrigocavalcante.network

import com.rodcollab.androidrodrigocavalcante.model.ResponseData
import retrofit2.Response
import retrofit2.http.GET

interface MaxApi {
    @GET("android/cliente")
    suspend fun clientData(): Response<ResponseData>?
}

