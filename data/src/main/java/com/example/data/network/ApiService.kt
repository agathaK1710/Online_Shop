package com.example.data.network

import com.example.data.network.models.ProductListDto
import retrofit2.http.GET

interface ApiService {
    @GET("97e721a7-0a66-4cae-b445-83cc0bcf9010")
    suspend fun getProducts():ProductListDto
}