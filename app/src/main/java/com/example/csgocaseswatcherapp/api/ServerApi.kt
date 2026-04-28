package com.example.csgocaseswatcherapp.api

import com.example.csgocaseswatcherapp.features.addcase.data.entities.AddedCaseDto
import com.example.csgocaseswatcherapp.features.caseanalytics.data.CaseAnalyticsDto
import com.example.csgocaseswatcherapp.features.caseoverview.data.entities.CaseDto
import com.example.csgocaseswatcherapp.features.portfolio.data.entities.PortfolioItemDto
import com.example.csgocaseswatcherapp.features.start.data.entities.PreferredCurrencyDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerApi {

    @GET("/getPreferredCurrency")
    suspend fun getPreferredCurrency(): PreferredCurrencyDto

    @GET("getCase")
    suspend fun getCaseList(): List<CaseDto>

    @GET("getAnalyticalDetails")
    suspend fun getCaseAnalyticsList(): List<CaseAnalyticsDto>

    @GET("/getPortfolioData")
    suspend fun getPortfolioData():List<PortfolioItemDto>

    @POST("/postPreferredCurrency")
    suspend fun postPreferredCurrency(@Body preferredCurrency: PreferredCurrencyDto): PreferredCurrencyDto

    @POST("/postAddedCase")
    suspend fun postAddedCase(@Body addedCaseDto: AddedCaseDto): AddedCaseDto
}

