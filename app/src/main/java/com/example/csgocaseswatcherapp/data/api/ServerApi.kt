package com.example.csgocaseswatcherapp.data.api

import com.example.csgocaseswatcherapp.screens.addcasefragment.data.entities.AddedCaseDto
import com.example.csgocaseswatcherapp.screens.caseanalytics.data.entities.CaseAnalyticsDto
import com.example.csgocaseswatcherapp.screens.caseoverview.data.entities.CaseDto
import com.example.csgocaseswatcherapp.screens.portfolio.data.entities.PortfolioItemDto
import com.example.csgocaseswatcherapp.screens.start.data.entities.PreferredCurrencyDto
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

