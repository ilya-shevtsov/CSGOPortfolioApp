package com.example.csgocaseswatcherapp.features.caseanalytics.data

import com.example.csgocaseswatcherapp.features.caseanalytics.data.entities.CaseAnalyticsDto
import com.example.csgocaseswatcherapp.features.caseanalytics.data.entities.CaseAnalyticsMapper
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.CaseAnalyticsRepository
import com.example.csgocaseswatcherapp.features.caseanalytics.domain.entities.CaseAnalytics
import javax.inject.Inject

class LocalCaseAnalyticsServerRepository @Inject constructor() : CaseAnalyticsRepository {

    override suspend fun getCaseAnalyticsList(): List<CaseAnalytics> {
        val caseAnalyticsDtoListResponse = mockServerResponse
        return caseAnalyticsDtoListResponse.map { caseAnalyticsDto ->
            CaseAnalyticsMapper.map(
                caseAnalyticsDto
            )
        }
    }

    private val mockServerResponse = listOf(
        CaseAnalyticsDto(
            name = "Chroma Case",
            dailyAvgReturnInPercent = 0.24,
            dailyAvgReturnInRUB = 1.42,
            dailyStandardDeviation = 0.0481,
            dailySharpRatio = 0.0488,
            monthlyAvgReturnInPercent = 7.3,
            monthlyAvgReturnInRUB = 44.21,
            monthlyStandardDeviation = 0.2637,
            monthlySharpRatio = 0.2767
        ),
        CaseAnalyticsDto(
            name = "Chroma 2 Case",
            dailyAvgReturnInPercent = -0.21,
            dailyAvgReturnInRUB = -1.4,
            dailyStandardDeviation = 0.0571,
            dailySharpRatio = -0.0374,
            monthlyAvgReturnInPercent = -6.21,
            monthlyAvgReturnInRUB = -40.63,
            monthlyStandardDeviation = 0.3127,
            monthlySharpRatio = -0.1987
        ),
        CaseAnalyticsDto(
            name = "Chroma 3 Case",
            dailyAvgReturnInPercent = -0.09,
            dailyAvgReturnInRUB = -0.43,
            dailyStandardDeviation = 0.0836,
            dailySharpRatio = -0.0105,
            monthlyAvgReturnInPercent = -2.61,
            monthlyAvgReturnInRUB = -12.86,
            monthlyStandardDeviation = 0.4577,
            monthlySharpRatio = -0.0569
        ),
        CaseAnalyticsDto(
            name = "Clutch Case",
            dailyAvgReturnInPercent = -0.1,
            dailyAvgReturnInRUB = -0.65,
            dailyStandardDeviation = 0.0873,
            dailySharpRatio = -0.0115,
            monthlyAvgReturnInPercent = -2.94,
            monthlyAvgReturnInRUB = -19.64,
            monthlyStandardDeviation = 0.478,
            monthlySharpRatio = -0.0615
        ),
        CaseAnalyticsDto(
            name = "CS:GO Weapon Case",
            dailyAvgReturnInPercent = 0.1,
            dailyAvgReturnInRUB = 0.46,
            dailyStandardDeviation = 0.0437,
            dailySharpRatio = 0.0236,
            monthlyAvgReturnInPercent = 2.97,
            monthlyAvgReturnInRUB = 13.91,
            monthlyStandardDeviation = 0.2391,
            monthlySharpRatio = 0.1242
        ),
        CaseAnalyticsDto(
            name = "CS:GO Weapon Case 2",
            dailyAvgReturnInPercent = -0.2,
            dailyAvgReturnInRUB = -1.07,
            dailyStandardDeviation = 0.0645,
            dailySharpRatio = -0.0311,
            monthlyAvgReturnInPercent = -5.83,
            monthlyAvgReturnInRUB = -32.02,
            monthlyStandardDeviation = 0.3533,
            monthlySharpRatio = -0.1649
        ),
        CaseAnalyticsDto(
            name = "CS:GO Weapon Case 3",
            dailyAvgReturnInPercent = 0.07,
            dailyAvgReturnInRUB = 0.28,
            dailyStandardDeviation = 0.0912,
            dailySharpRatio = 0.0074,
            monthlyAvgReturnInPercent = 2.08,
            monthlyAvgReturnInRUB = 8.26,
            monthlyStandardDeviation = 0.4995,
            monthlySharpRatio = 0.0416
        ),
        CaseAnalyticsDto(
            name = "CS20 Case",
            dailyAvgReturnInPercent = -0.19,
            dailyAvgReturnInRUB = -1.46,
            dailyStandardDeviation = 0.102,
            dailySharpRatio = -0.0189,
            monthlyAvgReturnInPercent = -5.56,
            monthlyAvgReturnInRUB = -43.25,
            monthlyStandardDeviation = 0.5587,
            monthlySharpRatio = -0.0996
        ),
        CaseAnalyticsDto(
            name = "Danger Zone Case",
            dailyAvgReturnInPercent = 0.01,
            dailyAvgReturnInRUB = 0.09,
            dailyStandardDeviation = 0.0893,
            dailySharpRatio = 0.0011,
            monthlyAvgReturnInPercent = 0.34,
            monthlyAvgReturnInRUB = 2.57,
            monthlyStandardDeviation = 0.4891,
            monthlySharpRatio = 0.0069
        ),
        CaseAnalyticsDto(
            name = "eSports 2013 Case",
            dailyAvgReturnInPercent = 0.3,
            dailyAvgReturnInRUB = 2.45,
            dailyStandardDeviation = 0.0461,
            dailySharpRatio = 0.0653,
            monthlyAvgReturnInPercent = 9.28,
            monthlyAvgReturnInRUB = 77.26,
            monthlyStandardDeviation = 0.2523,
            monthlySharpRatio = 0.3676
        ),
        CaseAnalyticsDto(
            name = "eSports 2013 Winter Case",
            dailyAvgReturnInPercent = 0.03,
            dailyAvgReturnInRUB = 0.18,
            dailyStandardDeviation = 0.0811,
            dailySharpRatio = 0.0033,
            monthlyAvgReturnInPercent = 0.97,
            monthlyAvgReturnInRUB = 5.51,
            monthlyStandardDeviation = 0.444,
            monthlySharpRatio = 0.0219
        ),
        CaseAnalyticsDto(
            name = "eSports 2014 Summer Case",
            dailyAvgReturnInPercent = 0.07,
            dailyAvgReturnInRUB = 0.5,
            dailyStandardDeviation = 0.0412,
            dailySharpRatio = 0.0163,
            monthlyAvgReturnInPercent = 2.03,
            monthlyAvgReturnInRUB = 14.84,
            monthlyStandardDeviation = 0.2257,
            monthlySharpRatio = 0.0898
        ),
        CaseAnalyticsDto(
            name = "Falchion Case",
            dailyAvgReturnInPercent = 0.01,
            dailyAvgReturnInRUB = 0.05,
            dailyStandardDeviation = 0.0434,
            dailySharpRatio = 0.0013,
            monthlyAvgReturnInPercent = 0.28,
            monthlyAvgReturnInRUB = 1.26,
            monthlyStandardDeviation = 0.2378,
            monthlySharpRatio = 0.0117
        ),
        CaseAnalyticsDto(
            name = "Fracture Case",
            dailyAvgReturnInPercent = -0.04,
            dailyAvgReturnInRUB = -0.26,
            dailyStandardDeviation = 0.0855,
            dailySharpRatio = -0.0048,
            monthlyAvgReturnInPercent = -1.13,
            monthlyAvgReturnInRUB = -7.65,
            monthlyStandardDeviation = 0.4683,
            monthlySharpRatio = -0.0241
        ),
        CaseAnalyticsDto(
            name = "Gamma Case",
            dailyAvgReturnInPercent = 0.35,
            dailyAvgReturnInRUB = 1.02,
            dailyStandardDeviation = 0.0535,
            dailySharpRatio = 0.0646,
            monthlyAvgReturnInPercent = 10.95,
            monthlyAvgReturnInRUB = 33.01,
            monthlyStandardDeviation = 0.2932,
            monthlySharpRatio = 0.3734
        ),
        CaseAnalyticsDto(
            name = "Gamma 2 Case",
            dailyAvgReturnInPercent = -0.1,
            dailyAvgReturnInRUB = -0.77,
            dailyStandardDeviation = 0.0531,
            dailySharpRatio = -0.0184,
            monthlyAvgReturnInPercent = -2.95,
            monthlyAvgReturnInRUB = -22.75,
            monthlyStandardDeviation = 0.2908,
            monthlySharpRatio = -0.1015
        ),
        CaseAnalyticsDto(
            name = "Glove Case",
            dailyAvgReturnInPercent = 0.15,
            dailyAvgReturnInRUB = 0.53,
            dailyStandardDeviation = 0.0334,
            dailySharpRatio = 0.0436,
            monthlyAvgReturnInPercent = 4.61,
            monthlyAvgReturnInRUB = 16.87,
            monthlyStandardDeviation = 0.1829,
            monthlySharpRatio = 0.2522
        ),
        CaseAnalyticsDto(
            name = "Horizon Case",
            dailyAvgReturnInPercent = -0.12,
            dailyAvgReturnInRUB = -0.81,
            dailyStandardDeviation = 0.0445,
            dailySharpRatio = -0.0274,
            monthlyAvgReturnInPercent = -3.46,
            monthlyAvgReturnInRUB = -24.09,
            monthlyStandardDeviation = 0.2436,
            monthlySharpRatio = -0.142
        ),
        CaseAnalyticsDto(
            name = "Huntsman Weapon Case",
            dailyAvgReturnInPercent = 0.14,
            dailyAvgReturnInRUB = 0.46,
            dailyStandardDeviation = 0.0796,
            dailySharpRatio = 0.0178,
            monthlyAvgReturnInPercent = 4.35,
            monthlyAvgReturnInRUB = 14.46,
            monthlyStandardDeviation = 0.4361,
            monthlySharpRatio = 0.0998
        ),
        CaseAnalyticsDto(
            name = "Operation Bravo Case",
            dailyAvgReturnInPercent = 0.03,
            dailyAvgReturnInRUB = 0.28,
            dailyStandardDeviation = 0.0526,
            dailySharpRatio = 0.0063,
            monthlyAvgReturnInPercent = 0.92,
            monthlyAvgReturnInRUB = 8.7,
            monthlyStandardDeviation = 0.2882,
            monthlySharpRatio = 0.032
        ),
        CaseAnalyticsDto(
            name = "Operation Breakout Weapon Case",
            dailyAvgReturnInPercent = 0.07,
            dailyAvgReturnInRUB = 0.41,
            dailyStandardDeviation = 0.0334,
            dailySharpRatio = 0.0197,
            monthlyAvgReturnInPercent = 2.0,
            monthlyAvgReturnInRUB = 11.63,
            monthlyStandardDeviation = 0.183,
            monthlySharpRatio = 0.1095
        ),
        CaseAnalyticsDto(
            name = "Operation Hydra Case",
            dailyAvgReturnInPercent = 0.29,
            dailyAvgReturnInRUB = 1.62,
            dailyStandardDeviation = 0.072,
            dailySharpRatio = 0.0401,
            monthlyAvgReturnInPercent = 8.77,
            monthlyAvgReturnInRUB = 49.89,
            monthlyStandardDeviation = 0.3944,
            monthlySharpRatio = 0.2224
        ),
        CaseAnalyticsDto(
            name = "Operation Phoenix Weapon Case",
            dailyAvgReturnInPercent = 0.12,
            dailyAvgReturnInRUB = 0.63,
            dailyStandardDeviation = 0.0644,
            dailySharpRatio = 0.0189,
            monthlyAvgReturnInPercent = 3.72,
            monthlyAvgReturnInRUB = 19.72,
            monthlyStandardDeviation = 0.3528,
            monthlySharpRatio = 0.1054
        ),
        CaseAnalyticsDto(
            name = "Operation Vanguard Weapon Case",
            dailyAvgReturnInPercent = -0.09,
            dailyAvgReturnInRUB = -0.43,
            dailyStandardDeviation = 0.0621,
            dailySharpRatio = -0.0143,
            monthlyAvgReturnInPercent = -2.57,
            monthlyAvgReturnInRUB = -12.91,
            monthlyStandardDeviation = 0.34,
            monthlySharpRatio = -0.0758
        ),
        CaseAnalyticsDto(
            name = "Operation Wildfire Case",
            dailyAvgReturnInPercent = 0.05,
            dailyAvgReturnInRUB = 0.33,
            dailyStandardDeviation = 0.0918,
            dailySharpRatio = 0.0054,
            monthlyAvgReturnInPercent = 1.56,
            monthlyAvgReturnInRUB = 10.32,
            monthlyStandardDeviation = 0.5024,
            monthlySharpRatio = 0.031
        ),
        CaseAnalyticsDto(
            name = "Prisma Case",
            dailyAvgReturnInPercent = -0.18,
            dailyAvgReturnInRUB = -1.44,
            dailyStandardDeviation = 0.0958,
            dailySharpRatio = -0.0184,
            monthlyAvgReturnInPercent = -5.28,
            monthlyAvgReturnInRUB = -43.19,
            monthlyStandardDeviation = 0.5248,
            monthlySharpRatio = -0.1006
        ),
        CaseAnalyticsDto(
            name = "Prisma 2 Case",
            dailyAvgReturnInPercent = -0.09,
            dailyAvgReturnInRUB = -0.43,
            dailyStandardDeviation = 0.0422,
            dailySharpRatio = -0.0218,
            monthlyAvgReturnInPercent = -2.7,
            monthlyAvgReturnInRUB = -13.25,
            monthlyStandardDeviation = 0.231,
            monthlySharpRatio = -0.1168
        ),
        CaseAnalyticsDto(
            name = "Revolver Case",
            dailyAvgReturnInPercent = -0.08,
            dailyAvgReturnInRUB = -0.39,
            dailyStandardDeviation = 0.0748,
            dailySharpRatio = -0.0107,
            monthlyAvgReturnInPercent = -2.33,
            monthlyAvgReturnInRUB = -11.43,
            monthlyStandardDeviation = 0.4096,
            monthlySharpRatio = -0.0568
        ),
        CaseAnalyticsDto(
            name = "Shadow Case",
            dailyAvgReturnInPercent = 0.05,
            dailyAvgReturnInRUB = 0.19,
            dailyStandardDeviation = 0.079,
            dailySharpRatio = 0.0061,
            monthlyAvgReturnInPercent = 1.44,
            monthlyAvgReturnInRUB = 5.43,
            monthlyStandardDeviation = 0.4327,
            monthlySharpRatio = 0.0332
        ),
        CaseAnalyticsDto(
            name = "Shattered Web Case",
            dailyAvgReturnInPercent = -0.13,
            dailyAvgReturnInRUB = -0.9,
            dailyStandardDeviation = 0.0589,
            dailySharpRatio = -0.0226,
            monthlyAvgReturnInPercent = -3.76,
            monthlyAvgReturnInRUB = -27.16,
            monthlyStandardDeviation = 0.3226,
            monthlySharpRatio = -0.1166
        ),
        CaseAnalyticsDto(
            name = "Spectrum Case",
            dailyAvgReturnInPercent = 0.22,
            dailyAvgReturnInRUB = 1.52,
            dailyStandardDeviation = 0.0299,
            dailySharpRatio = 0.0724,
            monthlyAvgReturnInPercent = 6.87,
            monthlyAvgReturnInRUB = 49.18,
            monthlyStandardDeviation = 0.1637,
            monthlySharpRatio = 0.4195
        ),
        CaseAnalyticsDto(
            name = "Spectrum 2 Case",
            dailyAvgReturnInPercent = 0.18,
            dailyAvgReturnInRUB = 1.34,
            dailyStandardDeviation = 0.0843,
            dailySharpRatio = 0.0218,
            monthlyAvgReturnInPercent = 5.43,
            monthlyAvgReturnInRUB = 41.02,
            monthlyStandardDeviation = 0.4616,
            monthlySharpRatio = 0.1176
        ),
        CaseAnalyticsDto(
            name = "Winter Offensive Weapon Case",
            dailyAvgReturnInPercent = 0.11,
            dailyAvgReturnInRUB = 0.47,
            dailyStandardDeviation = 0.0732,
            dailySharpRatio = 0.0156,
            monthlyAvgReturnInPercent = 3.23,
            monthlyAvgReturnInRUB = 14.42,
            monthlyStandardDeviation = 0.4012,
            monthlySharpRatio = 0.0806
        )
    )
}

