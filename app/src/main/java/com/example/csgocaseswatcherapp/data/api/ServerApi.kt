package com.example.csgocaseswatcherapp.data.api

import com.example.csgocaseswatcherapp.data.model.caseanalytics.CaseAnalyticsDto
import com.example.csgocaseswatcherapp.data.model.caseoverview.CaseDto
import com.example.csgocaseswatcherapp.data.model.prederredcurrencydto.PreferredCurrencyDto
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerApi {

    @GET("/getPreferredCurrency")
    suspend fun getPreferredCurrency(): PreferredCurrencyDto

    @POST("/postPreferredCurrency")
    suspend fun postPreferredCurrency(@Body preferredCurrency: PreferredCurrencyDto): PreferredCurrencyDto

    @GET("getCase")
    fun getCaseList(): Single<List<CaseDto>>

    @GET("getAnalyticalDetails")
    fun getCaseAnalyticsList(): Single<List<CaseAnalyticsDto>>
}

