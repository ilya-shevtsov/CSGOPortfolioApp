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
            dailyAvgReturnInPercent = 0.14,
            dailyAvgReturnInRUB = -0.31,
            dailyStandardDeviation = 0.06421299942865188,
            dailySharpRatio = 0.03216030151453114,
            monthlyAvgReturnInPercent = 4.11,
            monthlyAvgReturnInRUB = -3.24,
            monthlyStandardDeviation = 0.22929070765645318,
            monthlySharpRatio = 0.21576985108546862
        ),
        CaseAnalyticsDto(
            name = "Chroma 2 Case",
            dailyAvgReturnInPercent = 1.68,
            dailyAvgReturnInRUB = -0.27,
            dailyStandardDeviation = 0.1524160599056166,
            dailySharpRatio = 0.0370190326265684,
            monthlyAvgReturnInPercent = 15.41,
            monthlyAvgReturnInRUB = -0.82,
            monthlyStandardDeviation = 0.34195554447499865,
            monthlySharpRatio = 0.31987632903015595
        ),
        CaseAnalyticsDto(
            name = "Chroma 3 Case",
            dailyAvgReturnInPercent = 1.54,
            dailyAvgReturnInRUB = -0.35,
            dailyStandardDeviation = 0.2098419492525978,
            dailySharpRatio = 0.012491048274180462,
            monthlyAvgReturnInPercent = 4.06,
            monthlyAvgReturnInRUB = -1.6,
            monthlyStandardDeviation = 0.3818740439585064,
            monthlySharpRatio = 0.25626089699068505
        ),
        CaseAnalyticsDto(
            name = "Clutch Case",
            dailyAvgReturnInPercent = 0.14,
            dailyAvgReturnInRUB = -0.57,
            dailyStandardDeviation = 0.093833285860041,
            dailySharpRatio = 0.04364821230734374,
            monthlyAvgReturnInPercent = 11.94,
            monthlyAvgReturnInRUB = -1.21,
            monthlyStandardDeviation = 0.5547821901837919,
            monthlySharpRatio = 0.22050355597804247
        ),
        CaseAnalyticsDto(
            name = "CSGO Weapon Case",
            dailyAvgReturnInPercent = 0.16,
            dailyAvgReturnInRUB = 0.86,
            dailyStandardDeviation = 0.04219373834469208,
            dailySharpRatio = 0.05870499080300662,
            monthlyAvgReturnInPercent = 9.45,
            monthlyAvgReturnInRUB = 28.53,
            monthlyStandardDeviation = 0.22593257865632063,
            monthlySharpRatio = 0.2983528956341716
        ),
        CaseAnalyticsDto(
            name = "CSGO Weapon Case 2",
            dailyAvgReturnInPercent = 0.16,
            dailyAvgReturnInRUB = -0.17,
            dailyStandardDeviation = 0.05061173969276477,
            dailySharpRatio = 0.03866724989216205,
            monthlyAvgReturnInPercent = 7.01,
            monthlyAvgReturnInRUB = 3.34,
            monthlyStandardDeviation = 0.227063696182529,
            monthlySharpRatio = 0.24644439359988166
        ),
        CaseAnalyticsDto(
            name = "CSGO Weapon Case 3",
            dailyAvgReturnInPercent = 0.2,
            dailyAvgReturnInRUB = 0.0,
            dailyStandardDeviation = 0.0518425820070694,
            dailySharpRatio = 0.03852681453679485,
            monthlyAvgReturnInPercent = 7.08,
            monthlyAvgReturnInRUB = -0.17,
            monthlyStandardDeviation = 0.21134281004805122,
            monthlySharpRatio = 0.26226913607224867
        ),
        CaseAnalyticsDto(
            name = "CS20 Case",
            dailyAvgReturnInPercent = -0.44,
            dailyAvgReturnInRUB = -0.88,
            dailyStandardDeviation = 0.06187707964886427,
            dailySharpRatio = 0.04745978964662422,
            monthlyAvgReturnInPercent = -1.32,
            monthlyAvgReturnInRUB = -3.0,
            monthlyStandardDeviation = 0.4082858384957723,
            monthlySharpRatio = 0.17444104428910445
        ),
        CaseAnalyticsDto(
            name = "Danger Zone Case",
            dailyAvgReturnInPercent = -0.31,
            dailyAvgReturnInRUB = -0.86,
            dailyStandardDeviation = 0.08180298086094384,
            dailySharpRatio = 0.017498231623633738,
            monthlyAvgReturnInPercent = 0.03,
            monthlyAvgReturnInRUB = -5.61,
            monthlyStandardDeviation = 0.5191341430675901,
            monthlySharpRatio = 0.49405598456211935
        ),
        CaseAnalyticsDto(
            name = "eSports 2013 Case",
            dailyAvgReturnInPercent = 0.25,
            dailyAvgReturnInRUB = 0.89,
            dailyStandardDeviation = 0.04365383580835551,
            dailySharpRatio = 0.0357194005908388,
            monthlyAvgReturnInPercent = 5.01,
            monthlyAvgReturnInRUB = 23.44,
            monthlyStandardDeviation = 0.12702036194032898,
            monthlySharpRatio = 0.2834327158392658
        ),
        CaseAnalyticsDto(
            name = "eSports 2013 Winter Case",
            dailyAvgReturnInPercent = 0.28,
            dailyAvgReturnInRUB = 0.12,
            dailyStandardDeviation = 0.04262290629316876,
            dailySharpRatio = 0.042252968456213465,
            monthlyAvgReturnInPercent = 7.7,
            monthlyAvgReturnInRUB = 3.47,
            monthlyStandardDeviation = 0.20412479277935103,
            monthlySharpRatio = 0.2590271670942722
        ),
        CaseAnalyticsDto(
            name = "eSports 2014 Summer Case",
            dailyAvgReturnInPercent = 0.1,
            dailyAvgReturnInRUB = -0.04,
            dailyStandardDeviation = 0.04169109918223724,
            dailySharpRatio = 0.04948239086383898,
            monthlyAvgReturnInPercent = 4.62,
            monthlyAvgReturnInRUB = 1.3,
            monthlyStandardDeviation = 0.1812102738968753,
            monthlySharpRatio = 0.3123991589055543
        ),
        CaseAnalyticsDto(
            name = "Falchion Case",
            dailyAvgReturnInPercent = 0.38,
            dailyAvgReturnInRUB = -0.14,
            dailyStandardDeviation = 0.09155371217792653,
            dailySharpRatio = 0.02172940366156984,
            monthlyAvgReturnInPercent = 1.82,
            monthlyAvgReturnInRUB = -0.81,
            monthlyStandardDeviation = 0.20145377243500426,
            monthlySharpRatio = 0.2596423366569627
        ),
        CaseAnalyticsDto(
            name = "Fracture Case",
            dailyAvgReturnInPercent = -0.97,
            dailyAvgReturnInRUB = -3.0,
            dailyStandardDeviation = 0.04760193264409307,
            dailySharpRatio = 0.11874159553223564,
            monthlyAvgReturnInPercent = -8.6,
            monthlyAvgReturnInRUB = -28.16,
            monthlyStandardDeviation = 0.25379585032327867,
            monthlySharpRatio = 0.06330518097839531
        ),
        CaseAnalyticsDto(
            name = "Gamma Case",
            dailyAvgReturnInPercent = 1.94,
            dailyAvgReturnInRUB = -0.45,
            dailyStandardDeviation = 0.21845730175067124,
            dailySharpRatio = 0.019039814434758835,
            monthlyAvgReturnInPercent = 9.14,
            monthlyAvgReturnInRUB = -2.14,
            monthlyStandardDeviation = 0.40059914490173504,
            monthlySharpRatio = 0.23669341611984596
        ),
        CaseAnalyticsDto(
            name = "Gamma 2 Case",
            dailyAvgReturnInPercent = 2.23,
            dailyAvgReturnInRUB = -0.26,
            dailyStandardDeviation = 0.18937652026288795,
            dailySharpRatio = 0.02017384338560475,
            monthlyAvgReturnInPercent = 9.12,
            monthlyAvgReturnInRUB = -0.45,
            monthlyStandardDeviation = 0.4199831022651426,
            monthlySharpRatio = 0.2887982091142146
        ),
        CaseAnalyticsDto(
            name = "Glove Case",
            dailyAvgReturnInPercent = 3.13,
            dailyAvgReturnInRUB = -0.32,
            dailyStandardDeviation = 0.24959537591824574,
            dailySharpRatio = 0.019255620926649807,
            monthlyAvgReturnInPercent = 15.82,
            monthlyAvgReturnInRUB = 0.45,
            monthlyStandardDeviation = 0.4552938377421306,
            monthlySharpRatio = 0.38391979564254813
        ),
        CaseAnalyticsDto(
            name = "Horizon Case",
            dailyAvgReturnInPercent = 0.12,
            dailyAvgReturnInRUB = -0.71,
            dailyStandardDeviation = 0.10310357075453534,
            dailySharpRatio = 0.034368576904486636,
            monthlyAvgReturnInPercent = -0.33,
            monthlyAvgReturnInRUB = -4.71,
            monthlyStandardDeviation = 0.34385372138312525,
            monthlySharpRatio = 0.2306456318513534
        ),
        CaseAnalyticsDto(
            name = "Huntsman Weapon Case",
            dailyAvgReturnInPercent = 0.07,
            dailyAvgReturnInRUB = -0.16,
            dailyStandardDeviation = 0.036407424813569324,
            dailySharpRatio = 0.05660221763349268,
            monthlyAvgReturnInPercent = 5.39,
            monthlyAvgReturnInRUB = 1.23,
            monthlyStandardDeviation = 0.22365726987156276,
            monthlySharpRatio = 0.2781937915299033
        ),
        CaseAnalyticsDto(
            name = "Operation Bravo Case",
            dailyAvgReturnInPercent = 0.13,
            dailyAvgReturnInRUB = 0.55,
            dailyStandardDeviation = 0.03774954061272306,
            dailySharpRatio = 0.056751075919010414,
            monthlyAvgReturnInPercent = 7.88,
            monthlyAvgReturnInRUB = 25.7,
            monthlyStandardDeviation = 0.20031792782417093,
            monthlySharpRatio = 0.26828930132946605
        ),
        CaseAnalyticsDto(
            name = "Operation Breakout Weapon Case",
            dailyAvgReturnInPercent = 1.23,
            dailyAvgReturnInRUB = -0.07,
            dailyStandardDeviation = 0.07895786060531648,
            dailySharpRatio = 0.06231992032312653,
            monthlyAvgReturnInPercent = 6.56,
            monthlyAvgReturnInRUB = -0.01,
            monthlyStandardDeviation = 0.2597544779899828,
            monthlySharpRatio = 0.5074028236050167
        ),
        CaseAnalyticsDto(
            name = "Operation Hydra Case",
            dailyAvgReturnInPercent = 0.26,
            dailyAvgReturnInRUB = 0.21,
            dailyStandardDeviation = 0.0451056986761098,
            dailySharpRatio = 0.07890224278077532,
            monthlyAvgReturnInPercent = 13.21,
            monthlyAvgReturnInRUB = 13.11,
            monthlyStandardDeviation = 0.29360279343750495,
            monthlySharpRatio = 0.3199416380210093
        ),
        CaseAnalyticsDto(
            name = "Operation Phoenix Weapon Case",
            dailyAvgReturnInPercent = 0.18,
            dailyAvgReturnInRUB = -0.17,
            dailyStandardDeviation = 0.0679348601780855,
            dailySharpRatio = 0.029187205561864955,
            monthlyAvgReturnInPercent = 6.76,
            monthlyAvgReturnInRUB = -1.54,
            monthlyStandardDeviation = 0.3495356209212063,
            monthlySharpRatio = 0.15711847390986275
        ),
        CaseAnalyticsDto(
            name = "Operation Vanguard Weapon Case",
            dailyAvgReturnInPercent = 0.15,
            dailyAvgReturnInRUB = -0.01,
            dailyStandardDeviation = 0.049994280810741656,
            dailySharpRatio = 0.03236143023163794,
            monthlyAvgReturnInPercent = 4.68,
            monthlyAvgReturnInRUB = -0.04,
            monthlyStandardDeviation = 0.21344492001724386,
            monthlySharpRatio = 0.18748294353128114
        ),
        CaseAnalyticsDto(
            name = "Operation Wildfire Case",
            dailyAvgReturnInPercent = 1.58,
            dailyAvgReturnInRUB = -0.16,
            dailyStandardDeviation = 0.17653255836852527,
            dailySharpRatio = 0.019611537649389346,
            monthlyAvgReturnInPercent = 7.95,
            monthlyAvgReturnInRUB = -0.05,
            monthlyStandardDeviation = 0.3407054966654649,
            monthlySharpRatio = 0.2890613421796659
        ),
        CaseAnalyticsDto(
            name = "Prisma Case",
            dailyAvgReturnInPercent = -0.45,
            dailyAvgReturnInRUB = -2.22,
            dailyStandardDeviation = 0.13380270262368044,
            dailySharpRatio = 0.03640447109785188,
            monthlyAvgReturnInPercent = -9.71,
            monthlyAvgReturnInRUB = -68.42,
            monthlyStandardDeviation = 0.5978097973586873,
            monthlySharpRatio = 0.27393907349169533
        ),
        CaseAnalyticsDto(
            name = "Prisma 2 Case",
            dailyAvgReturnInPercent = -1.13,
            dailyAvgReturnInRUB = -3.9,
            dailyStandardDeviation = 0.1323460826303923,
            dailySharpRatio = 0.04370842660471593,
            monthlyAvgReturnInPercent = -18.26,
            monthlyAvgReturnInRUB = -11.83,
            monthlyStandardDeviation = 0.592822834539657,
            monthlySharpRatio = 0.2336919652693242
        ),
        CaseAnalyticsDto(
            name = "Revolver Case",
            dailyAvgReturnInPercent = 1.7,
            dailyAvgReturnInRUB = -0.32,
            dailyStandardDeviation = 0.19370630278568932,
            dailySharpRatio = 0.008244159753300702,
            monthlyAvgReturnInPercent = 3.81,
            monthlyAvgReturnInRUB = -1.32,
            monthlyStandardDeviation = 0.29673818258130213,
            monthlySharpRatio = 0.16907490851160478
        ),
        CaseAnalyticsDto(
            name = "Shadow Case",
            dailyAvgReturnInPercent = 0.51,
            dailyAvgReturnInRUB = -0.3,
            dailyStandardDeviation = 0.11530650435448805,
            dailySharpRatio = 0.016637446077752503,
            monthlyAvgReturnInPercent = 2.54,
            monthlyAvgReturnInRUB = -0.55,
            monthlyStandardDeviation = 0.22058146799281947,
            monthlySharpRatio = 0.25954085556151535
        ),
        CaseAnalyticsDto(
            name = "Shattered Web Case",
            dailyAvgReturnInPercent = -0.03,
            dailyAvgReturnInRUB = -0.46,
            dailyStandardDeviation = 0.04791038673034622,
            dailySharpRatio = 0.018698810633850874,
            monthlyAvgReturnInPercent = 0.99,
            monthlyAvgReturnInRUB = -0.62,
            monthlyStandardDeviation = 0.19512322600347848,
            monthlySharpRatio = 0.052271545622341105
        ),
        CaseAnalyticsDto(
            name = "Spectrum Case",
            dailyAvgReturnInPercent = 1.98,
            dailyAvgReturnInRUB = -0.48,
            dailyStandardDeviation = 0.17961300549748602,
            dailySharpRatio = 0.03705196200427781,
            monthlyAvgReturnInPercent = 8.48,
            monthlyAvgReturnInRUB = -1.12,
            monthlyStandardDeviation = 0.3362193195427277,
            monthlySharpRatio = 0.48050205039872174
        ),
        CaseAnalyticsDto(
            name = "Spectrum 2 Case",
            dailyAvgReturnInPercent = 0.72,
            dailyAvgReturnInRUB = -1.14,
            dailyStandardDeviation = 0.13308094190746075,
            dailySharpRatio = 0.03397532614809876,
            monthlyAvgReturnInPercent = 4.45,
            monthlyAvgReturnInRUB = -0.79,
            monthlyStandardDeviation = 0.32915907283347456,
            monthlySharpRatio = 0.42911785057498864
        ),
        CaseAnalyticsDto(
            name = "Winter Offensive Weapon Case",
            dailyAvgReturnInPercent = 0.07,
            dailyAvgReturnInRUB = -0.1,
            dailyStandardDeviation = 0.03872034361912812,
            dailySharpRatio = 0.04120284610229608,
            monthlyAvgReturnInPercent = 4.07,
            monthlyAvgReturnInRUB = 2.17,
            monthlyStandardDeviation = 0.187154892218516,
            monthlySharpRatio = 0.22847104522404643
        )
    )
}

