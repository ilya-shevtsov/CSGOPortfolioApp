package com.example.csgocaseswatcherapp.features.caseoverview.data

import com.example.csgocaseswatcherapp.features.caseoverview.data.entities.CaseDto
import com.example.csgocaseswatcherapp.features.caseoverview.data.entities.CaseOverviewMapper
import com.example.csgocaseswatcherapp.features.caseoverview.domain.CaseRepository
import com.example.csgocaseswatcherapp.features.caseoverview.domain.entities.CaseOverview

import javax.inject.Inject

class LocalCaseOverviewServerRepository @Inject constructor(

) : CaseRepository {

    override suspend fun getCaseOverviewList(): List<CaseOverview> {
        val caseDtoListResponse = mockServerResponse
        val caseOverViewList =
            caseDtoListResponse.map { caseDto -> CaseOverviewMapper.map(caseDto) }
        return caseOverViewList
    }

    private val mockServerResponse = listOf(
        CaseDto(
            "Chroma Case",
            "08.01.2015",
            "Inactive (Rare)",
            8.27,
            11876,
            9.55,
            "https://api.steamapis.com/image/item/730/Chroma%20Case",
            "The Chroma Case is a weapon case consisting of 14 community-desgined weapon skins released as part of the January 8, 2015 update. It requires a Chroma Case Key to be opened. The Chroma Case also has six exclusive community created knife finishes: Damascus Steel, Doppler, Marble Fade, Tiger Tooth, Rust Coat, and Ultraviolet. The Spectrum Case and Spectrum 2 Case includes these Chroma finishes on the Huntsman Knife, Butterfly Knife, Falchion Knife, Shadow Daggers and the Bowie Knife. The Prisma Case contains these Chroma finishes on the Navaja Knife, Stiletto Knife, Talon Knife, and the Ursus Knife."
        ),
        CaseDto(
            "Chroma 2 Case",
            "15.04.2015",
            "Inactive (Rare)",
            4.52,
            9972,
            4.91,
            "https://api.steamapis.com/image/item/730/Chroma%202%20Case",
            "The Chroma 2 Case is a weapon case consisting of 15 community-made weapon skins released as part of the April 15, 2015 update. It requires a Chroma 2 Case Key to be opened."
        ),
        CaseDto(
            "Chroma 3 Case",
            "20.04.2016",
            "Inactive (Rare)",
            7.41,
            10236,
            6.39,
            "https://api.steamapis.com/image/item/730/Chroma%203%20Case",
            "The Chroma 3 Case is a weapon case consisting of community weapons released as part of the April 27, 2016 update. It requires a Chroma 3 Case Key to be opened."
        ),
        CaseDto(
            "Clutch Case",
            "15.02.2018",
            "Active",
            5.94,
            12481,
            6.18,
            "https://api.steamapis.com/image/item/730/Clutch%20Case",
            "The Clutch Case is a weapon case featuring 17 community-designed weapon finishes and 24 new glove finishes. Released as part of the February 15, 2018 update. It requires a Clutch Case Key to be opened."
        ),
        CaseDto(
            "CS:GO Weapon Case",
            "14.08.2013",
            "Inactive (Rare)",
            9.83,
            10843,
            8.18,
            "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case",
            "The CS:GO Weapon Case is the first series of Weapon Cases introduced in Counter-Strike: Global Offensive and contains 9 Valve-made finishes during the August 14, 2013 Arms Deal update. The Weapon Case requires a standard CS:GO Case Key to be opened. Since the release of Weapon Case 2, this crate is very rarely dropped."
        ),
        CaseDto(
            "CS:GO Weapon Case 2",
            "08.11.2013",
            "Inactive (Rare)",
            4.33,
            18154,
            5.15,
            "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%202",
            "The CSGO Weapon Case 2 was introduced to Counter-Strike: Global Offensive as part of the November 6, 2013 update, switching the CSGO Weapon Case to a rare drop. The standard CSGO Case Key can be used to open this case. Since the release of CSGO Weapon Case 3, this case is a rare drop."
        ),
        CaseDto(
            "CS:GO Weapon Case 3",
            "12.02.2014",
            "Inactive (Rare)",
            3.25,
            11024,
            3.82,
            "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%203",
            "The Weapon Case 3 is a case featured in Counter-Strike: Global Offensive, released as part of the February 12, 2014 update when the CZ75-Auto was released as a pistol-exclusive weapon case. This case requires the standard CS:GO Case Key to open."
        ),
        CaseDto(
            "CS20 Case",
            "18.10.2019",
            "Inactive (Rare)",
            1.14,
            19254,
            0.95,
            "https://api.steamapis.com/image/item/730/CS20%20Case",
            "The CS20 Case is a weapon case featuring 17 community-made skins made for the CS20 event that have a Counter-Strike theme to it. It features the Classic Knife in 13 different finishes. It was released during the October 18, 2019 CS20 event, along with the CS20 Sticker Capsule. There is a tiny chance to unbox the Classic Knife."
        ),
        CaseDto(
            "Danger Zone Case",
            "06.12.2018",
            "Inactive (Rare)",
            8.61,
            10827,
            7.43,
            "https://api.steamapis.com/image/item/730/Danger%20Zone%20Case",
            "The Danger Zone Case is a weapon case of community created weapon skins released in the December 6, 2018 update. It requires a Danger Zone Case Key to be opened. There is also a small chance to receive one of the Horizon knives when opening the case."
        ),
        CaseDto(
            "eSports 2013 Case",
            "14.08.2013",
            "Inactive (Rare)",
            5.16,
            15982,
            5.46,
            "https://api.steamapis.com/image/item/730/eSports%202013%20Case",
            "The eSports 2013 Case is the second ever weapon crate released in Counter-Strike: Global Offensive during the August 14, 2013 Arms Deal update. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community. Since the introduction of the eSports 2013 Winter Case, the eSports 2013 Case drops very rarely."
        ),
        CaseDto(
            "eSports 2013 Winter Case",
            "18.12.2013",
            "Inactive (Rare)",
            6.42,
            7443,
            5.69,
            "https://api.steamapis.com/image/item/730/eSports%202013%20Winter%20Case",
            "The eSports 2013 Winter Case is a weapon case in Counter-Strike: Global Offensive introduced as part of the December 18, 2013 Winter Offensive update that contains 12 Valve-made skins. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community. Since the introduction of the eSports 2014 Summer Case, the eSports 2013 Winter Case drops very rarely."
        ),
        CaseDto(
            "eSports 2014 Summer Case",
            "10.07.2014",
            "Inactive (Rare)",
            3.62,
            17842,
            4.02,
            "https://api.steamapis.com/image/item/730/eSports%202014%20Summer%20Case",
            "The eSports 2014 Summer Case is a weapon crate in Counter-Strike: Global Offensive added as part of the July, 10, 2014 update. It contains 17 weapon skins that are made by Valve. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community."
        ),
        CaseDto(
            "Falchion Case",
            "26.05.2015",
            "Inactive (Rare)",
            2.44,
            19211,
            2.86,
            "https://api.steamapis.com/image/item/730/Falchion%20Case",
            "The Falchion Case is a weapon case released on the May 26, 2015 update, alongside with Operation Bloodhound as an exclusive drop to pass holders. After Operation Bloodhound came to a close, the case was available to drop for all players. This case also introduces the case-exclusive Falchion Knife which can be unboxed at a very low chance."
        ),
        CaseDto(
            "Fracture Case",
            "07.08.2020",
            "Active",
            7.21,
            11733,
            7.84,
            "https://api.steamapis.com/image/item/730/Fracture%20Case",
            "The Fracture Case is a weapon case featuring 17 community-designed weapon finishes and along with the Shattered Web Case knives (Paracord Knife, Survival Knife, Skeleton Knife, Nomad Knife), in the standard knife finishes."
        ),
        CaseDto(
            "Gamma Case",
            "15.06.2016",
            "Inactive (Rare)",
            9.42,
            9832,
            8.91,
            "https://api.steamapis.com/image/item/730/Gamma%20Case",
            "The Gamma Case is a weapon case consisting of community weapons and new knife finishes released as part of the June 15, 2016 update. It requires a Gamma Case Key to be opened. On Glock-18 Wasteland Rebel, the numbers 7355608 are hidden on the back of the gun. Glock-18 Wasteland Rebel contains many hidden smaller painted messages and symbols that can be seen at a very low weapon condition, which are covered by the Kill Them All graffiti on higher conditions."
        ),
        CaseDto(
            "Gamma 2 Case",
            "18.08.2016",
            "Inactive (Rare)",
            3.14,
            15483,
            3.57,
            "https://api.steamapis.com/image/item/730/Gamma%202%20Case",
            "The Gamma 2 Case is a weapon case consisting of community weapons and new knife finishes released as part of the August 18, 2016 update. It requires a Gamma Case Key to be opened."
        ),
        CaseDto(
            "Glove Case",
            "28.11.2016",
            "Inactive (Rare)",
            5.61,
            11983,
            4.84,
            "https://api.steamapis.com/image/item/730/Glove%20Case",
            "The Glove Case is a weapon case consisting of community weapons and new gloves released as part of the November 28, 2016 update. It requires a Glove Case Key to be opened."
        ),
        CaseDto(
            "Horizon Case",
            "02.08.2018",
            "Inactive (Rare)",
            4.12,
            17261,
            3.61,
            "https://api.steamapis.com/image/item/730/Horizon%20Case",
            "The Horizon Case is a weapon case featuring 17 community-designed weapon finishes and along Ursus Knife, Navaja Knife, Stiletto Knife, Talon Knife. Released as part of the August 3, 2018 update. It requires a Horizon Case Key to be opened."
        ),
        CaseDto(
            "Huntsman Weapon Case",
            "01.05.2014",
            "Inactive (Rare)",
            7.37,
            13924,
            8.15,
            "https://api.steamapis.com/image/item/730/Huntsman%20Weapon%20Case",
            "The Huntsman Weapon Case is a weapon case consisting of 15 community-made weapon skins released as part of the May 1, 2014 update that also introduced the Bank Collection. It requires a Huntsman Weapon Case Key to be opened. There is a small chance to unbox a Huntsman Knife when opening the case. On June 11th, 2014, the Huntsman Weapon Case was updated, along with the Community Sticker Capsule 1 due to a copyright infringement. The M4A4 | Howl and several other weapon skins were removed and 6 new weapon skins were added."
        ),
        CaseDto(
            "Operation Bravo Case",
            "19.09.2013",
            "Inactive (Rare)",
            9.91,
            13852,
            8.43,
            "https://api.steamapis.com/image/item/730/Operation%20Bravo%20Case",
            "The Operation Bravo Case is a crate containing 15 Valve-made weapon skins that was released during September 19, 2013. Since the release of Operation Bravo, any player could obtain this case. However, holders of the Operation Bravo pass had a higher chance to receive the case as a drop. Like the CSGO Weapon Case, the standard weapon case key can be used to open this case. Since the end of Operation Bravo, this case drops very rarely."
        ),
        CaseDto(
            "Operation Breakout Weapon Case",
            "01.07.2014",
            "Inactive (Rare)",
            1.64,
            15394,
            1.98,
            "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
            "The Operation Breakout Case is a weapon case consisting of 14 community-created weapon skins released as part of Operation Breakout with the July 1, 2014 update. Like the Operation Phoenix Weapon Case during Operation Phoenix, Operation Breakout Coin holders received this case. Once the operation ended on October 2, 2014, the weapon case became available for all CS:GO players."
        ),
        CaseDto(
            "Operation Broken Fang Case",
            "04.12.2020",
            "Inactive (Rare)",
            4.23,
            18621,
            3.99,
            "https://api.steamapis.com/image/item/730/Operation%20Broken%20Fang%20Case",
            "The Broken Fang Case is a case that contains 17 community-made weapon skins released as part of Operation Broken Fang. There is a very small chance to unbox a pair of gloves."
        ),
        CaseDto(
            "Operation Hydra Case",
            "23.05.2017",
            "Inactive (Rare)",
            9.77,
            10564,
            10.0,
            "https://api.steamapis.com/image/item/730/Operation%20Hydra%20Case",
            "The Operation Hydra Case is a weapon case of community created weapon skins released in the May 23, 2017 update, alongside with Operation Hydra as an exclusive drop to pass holders. It requires a Operation Hydra Case Key to be opened."
        ),
        CaseDto(
            "Operation Phoenix Weapon Case",
            "20.02.2014",
            "Inactive (Rare)",
            2.85,
            10942,
            2.61,
            "https://api.steamapis.com/image/item/730/Operation%20Phoenix%20Weapon%20Case",
            "The Operation Phoenix Case is a weapon case of 13 community-created weapon skins released on February 20, 2014 as part of Operation Phoenix. The chance for the case to drop was originally exclusive to pass owners. As of the June 11, 2014 update, the weapon case became available as a drop for all players."
        ),
        CaseDto(
            "Operation Vanguard Weapon Case",
            "11.11.2014",
            "Inactive (Rare)",
            4.91,
            16253,
            5.63,
            "https://api.steamapis.com/image/item/730/Operation%20Vanguard%20Weapon%20Case",
            "The Operation Vanguard Case is a weapon case consisting of 14 community-created weapon skins released as part of Operation Vanguard during the November 11, 2014 update. Like the previous operation cases, Operation Vanguard Coin holders receive exclusive case drops in addition to the normal timed drops during Operation Vanguard as well as the potential to receive a case as a mission drop."
        ),
        CaseDto(
            "Operation Wildfire Case",
            "17.02.2016",
            "Inactive (Rare)",
            2.73,
            10432,
            3.14,
            "https://api.steamapis.com/image/item/730/Operation%20Wildfire%20Case",
            "The Operation Wildfire Case is a weapon case released in the February 17, 2016 update, alongside with Operation Wildfire as an exclusive drop to pass holders. This case also introduces the case-exclusive Bowie Knife which can be awarded at a very low chance."
        ),
        CaseDto(
            "Prisma Case",
            "13.03.2019",
            "Inactive (Rare)",
            3.52,
            17122,
            3.11,
            "https://api.steamapis.com/image/item/730/Prisma%20Case",
            "The Prisma Case is a weapon case featuring 17 community-designed weapon finishes and along with Ursus Knife, Navaja Knife, Stiletto Knife, and Talon Knife, in Chroma finishes. The case was released as part of the March 13, 2019 update. It requires a Prisma Case Key to be opened."
        ),
        CaseDto(
            "Prisma 2 Case",
            "01.04.2020",
            "Inactive (Rare)",
            1.94,
            16632,
            1.73,
            "https://api.steamapis.com/image/item/730/Prisma%202%20Case",
            "Case Prism 2 is a container with weapon skins in Counter-Strike: Global Offensive. To open requires a special key from the Prism 2 case."
        ),
        CaseDto(
            "Revolver Case",
            "08.12.2015",
            "Inactive (Rare)",
            2.22,
            13542,
            1.91,
            "https://api.steamapis.com/image/item/730/Revolver%20Case",
            "The Revolver Case is a weapon case consisting of 16 community-created weapon skins released as part of the December 8, 2015 update. The Revolver Case was the first case to contain skins for the R8 Revolver."
        ),
        CaseDto(
            "Shadow Case",
            "17.09.2015",
            "Inactive (Rare)",
            4.31,
            14632,
            5.05,
            "https://api.steamapis.com/image/item/730/Shadow%20Case",
            "The Shadow Case is a weapon case consisting of 16 community-made weapon skins released as part of the September 18, 2015 update. It requires a Shadow Case Key to be opened. There is a tiny chance to unbox a pair of Shadow Daggers."
        ),
        CaseDto(
            "Shattered Web Case",
            "18.11.2019",
            "Inactive (Rare)",
            7.84,
            10212,
            9.03,
            "https://api.steamapis.com/image/item/730/Shattered%20Web%20Case",
            "The Shattered Web Case is a case that contains 17 community-made weapon skins released as part of Operation Shattered Web. There is a very small chance to unbox the Nomad Knife, Skeleton Knife, Paracord Knife, and the Survival Knife each in 13 different finishes."
        ),
        CaseDto(
            "Snakebite Case",
            "03.05.2021",
            "Active",
            1.55,
            18725,
            1.75,
            "https://www.csgodatabase.com/images/containers/webp/Snakebite_Case.webp",
            "The Snakebite Case is a weapon case introduced on 03 May 2021 as part of the End of Broken Fang update. The weapon case contains CS:GO skins from The Snakebite Collection."
        ),
        CaseDto(
            "Spectrum Case",
            "15.03.2017",
            "Inactive (Rare)",
            6.42,
            12043,
            7.12,
            "https://api.steamapis.com/image/item/730/Spectrum%20Case",
            "The Spectrum Case is a weapon case consisting of community weapons and new knife finishes for second generation of knives in Chroma Case released as part of the March 15, 2017 update. It requires a Spectrum Case Key to be opened."
        ),
        CaseDto(
            "Spectrum 2 Case",
            "14.09.2017",
            "Inactive (Rare)",
            4.83,
            15564,
            4.12,
            "https://api.steamapis.com/image/item/730/Spectrum%202%20Case",
            "The Spectrum 2 Case is a weapon case consisting of community weapons and Chroma finishes for the later-added knives, released as part of the September 14, 2017 update. It requires a Spectrum 2 Case Key to be opened."
        ),
        CaseDto(
            "Winter Offensive Weapon Case",
            "18.12.2013",
            "Inactive (Rare)",
            8.74,
            10125,
            9.55,
            "https://api.steamapis.com/image/item/730/Winter%20Offensive%20Weapon%20Case",
            "The Winter Offensive Weapon Case is a weapon case containing 12 community-created skins. It was released as part of the December 18, 2013 update and requires a Winter Offensive Case Key to open. Since the release of Operation Vanguard, this crate is very rarely dropped. It can also be bought in the Steam Community Market."
        ),
        CaseDto(
            "Dreams & Nightmares Case",
            "20.01.2022",
            "Active",
            2.91,
            18224,
            2.48,
            "https://api.steamapis.com/image/item/730/Dreams%20%26%20Nightmares%20Case",
            "The Dreams & Nightmares Case is a weapon case introduced on 20 January 2022 as part of the Dreams & Nightmares Case update. The weapon case contains CS:GO skins from The Dreams & Nightmares Collection. The case contains 17 skins, all of which were selected from the Dreams & Nightmares contest which ran from 22 July 2021 through to 21 October 2021. The rare special items in this case are the second generation Gamma Knives which first appeared in the Operation Riptide case."
        ),
        CaseDto(
            "Recoil Case",
            "01.07.2022",
            "Active",
            4.35,
            19243,
            3.99,
            "https://api.steamapis.com/image/item/730/Recoil%20Case",
            "The Recoil Case is a weapon case introduced on 01 July 2022 as part of the A New Case update. The weapon case contains CS:GO skins from The Recoil Collection. The rare special items in this case are the Broken Fang gloves which first appeared in the Operation Broken Fang case and also appear in the Snakebite case."
        )
    )
}



