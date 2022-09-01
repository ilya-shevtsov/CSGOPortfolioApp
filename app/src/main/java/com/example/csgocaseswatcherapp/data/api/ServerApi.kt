package com.example.csgocaseswatcherapp.data.api

import com.example.csgocaseswatcherapp.data.model.addedcase.AddedCaseDto
import com.example.csgocaseswatcherapp.data.model.caseanalytics.CaseAnalyticsDto
import com.example.csgocaseswatcherapp.data.model.caseoverview.CaseDto
import com.example.csgocaseswatcherapp.data.model.prederredcurrencydto.PreferredCurrencyDto
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

    @POST("/postPreferredCurrency")
    suspend fun postPreferredCurrency(@Body preferredCurrency: PreferredCurrencyDto): PreferredCurrencyDto

    @POST("/postAddedCase")
    suspend fun postAddedCase(@Body addedCaseDto: AddedCaseDto): AddedCaseDto
}

