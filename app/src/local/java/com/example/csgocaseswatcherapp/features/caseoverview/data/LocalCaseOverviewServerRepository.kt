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
            name = "Chroma Case",
            releaseDate = "08.01.2015",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 6.45,
            volume = 975,
            medianPrice = 6.61,
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%20Case",
            description = "The Chroma Case is a weapon case consisting of 14 community-desgined weapon skins released as part of the January 8, 2015 update. It requires a Chroma Case Key to be opened. The Chroma Case also has six exclusive community created knife finishes: Damascus Steel, Doppler, Marble Fade, Tiger Tooth, Rust Coat, and Ultraviolet. The Spectrum Case and Spectrum 2 Case includes these Chroma finishes on the Huntsman Knife, Butterfly Knife, Falchion Knife, Shadow Daggers and the Bowie Knife. The Prisma Case contains these Chroma finishes on the Navaja Knife, Stiletto Knife, Talon Knife, and the Ursus Knife."
        ),
        CaseDto(
            name = "Chroma 2 Case",
            releaseDate = "15.04.2015",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%202%20Case",
            description = "The Chroma 2 Case is a weapon case consisting of 15 community-made weapon skins released as part of the April 15, 2015 update. It requires a Chroma 2 Case Key to be opened."
        ),
        CaseDto(
            name = "Chroma 3 Case",
            releaseDate = "20.04.2016",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Chroma%203%20Case",
            description = "The Chroma 3 Case is a weapon case consisting of community weapons released as part of the April 27, 2016 update. It requires a Chroma 3 Case Key to be opened."
        ),
        CaseDto(
            name = "Clutch Case",
            releaseDate = "15.02.2018",
            dropStatus = "Active",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Clutch%20Case",
            description = "The Clutch Case is a weapon case featuring 17 community-designed weapon finishes and 24 new glove finishes. Released as part of the February 15, 2018 update. It requires a Clutch Case Key to be opened."
        ),
        CaseDto(
            name = "CS:GO Weapon Case",
            releaseDate = "14.08.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case",
            description = "The CS:GO Weapon Case is the first series of Weapon Cases introduced in Counter-Strike: Global Offensive and contains 9 Valve-made finishes during the August 14, 2013 Arms Deal update. The Weapon Case requires a standard CS:GO Case Key to be opened. Since the release of Weapon Case 2, this crate is very rarely dropped."
        ),
        CaseDto(
            name = "CS:GO Weapon Case 2",
            releaseDate = "08.11.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%202",
            description = "The CSGO Weapon Case 2 was introduced to Counter-Strike: Global Offensive as part of the November 6, 2013 update, switching the CSGO Weapon Case to a rare drop. The standard CSGO Case Key can be used to open this case. Since the release of CSGO Weapon Case 3, this case is a rare drop."
        ),
        CaseDto(
            name = "CS:GO Weapon Case 3",
            releaseDate = "12.02.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/CS%3AGO%20Weapon%20Case%203",
            description = "The Weapon Case 3 is a case featured in Counter-Strike: Global Offensive, released as part of the February 12, 2014 update when the CZ75-Auto was released as a pistol-exclusive weapon case. This case requires the standard CS:GO Case Key to open."
        ),
        CaseDto(
            name = "CS20 Case",
            releaseDate = "18.10.2019",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/CS20%20Case",
            description = "The CS20 Case is a weapon case featuring 17 community-made skins made for the CS20 event that have a Counter-Strike theme to it. It features the Classic Knife in 13 different finishes. It was released during the October 18, 2019 CS20 event, along with the CS20 Sticker Capsule. There is a tiny chance to unbox the Classic Knife."
        ),
        CaseDto(
            name = "Danger Zone Case",
            releaseDate = "06.12.2018",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Danger%20Zone%20Case",
            description = "The Danger Zone Case is a weapon case of community created weapon skins released in the December 6, 2018 update. It requires a Danger Zone Case Key to be opened. There is also a small chance to receive one of the Horizon knives when opening the case."
        ),
        CaseDto(
            name = "eSports 2013 Case",
            releaseDate = "14.08.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Case",
            description = "The eSports 2013 Case is the second ever weapon crate released in Counter-Strike: Global Offensive during the August 14, 2013 Arms Deal update. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community. Since the introduction of the eSports 2013 Winter Case, the eSports 2013 Case drops very rarely."
        ),
        CaseDto(
            name = "eSports 2013 Winter Case",
            releaseDate = "18.12.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202013%20Winter%20Case",
            description = "The eSports 2013 Winter Case is a weapon case in Counter-Strike: Global Offensive introduced as part of the December 18, 2013 Winter Offensive update that contains 12 Valve-made skins. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community. Since the introduction of the eSports 2014 Summer Case, the eSports 2013 Winter Case drops very rarely."
        ),
        CaseDto(
            name = "eSports 2014 Summer Case",
            releaseDate = "10.07.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/eSports%202014%20Summer%20Case",
            description = "The eSports 2014 Summer Case is a weapon crate in Counter-Strike: Global Offensive added as part of the July, 10, 2014 update. It contains 17 weapon skins that are made by Valve. A portion of the profits from the eSports key sales are given to the Counter-Strike eSports community."
        ),
        CaseDto(
            name = "Falchion Case",
            releaseDate = "26.05.2015",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Falchion%20Case",
            description = "The Falchion Case is a weapon case released on the May 26, 2015 update, alongside with Operation Bloodhound as an exclusive drop to pass holders. After Operation Bloodhound came to a close, the case was available to drop for all players. This case also introduces the case-exclusive Falchion Knife which can be unboxed at a very low chance."
        ),
        CaseDto(
            name = "Fracture Case",
            releaseDate = "07.08.2020",
            dropStatus = "Active",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Fracture%20Case",
            description = "The Fracture Case is a weapon case featuring 17 community-designed weapon finishes and along with the Shattered Web Case knives (Paracord Knife, Survival Knife, Skeleton Knife, Nomad Knife), in the standard knife finishes."
        ),
        CaseDto(
            name = "Gamma Case",
            releaseDate = "15.06.2016",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Gamma%20Case",
            description = "The Gamma Case is a weapon case consisting of community weapons and new knife finishes released as part of the June 15, 2016 update. It requires a Gamma Case Key to be opened. On Glock-18 Wasteland Rebel, the numbers 7355608 are hidden on the back of the gun. Glock-18 Wasteland Rebel contains many hidden smaller painted messages and symbols that can be seen at a very low weapon condition, which are covered by the Kill Them All graffiti on higher conditions."
        ),
        CaseDto(
            name = "Gamma 2 Case",
            releaseDate = "18.08.2016",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Gamma%202%20Case",
            description = "The Gamma 2 Case is a weapon case consisting of community weapons and new knife finishes released as part of the August 18, 2016 update. It requires a Gamma Case Key to be opened."
        ),
        CaseDto(
            name = "Glove Case",
            releaseDate = "28.11.2016",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Glove%20Case",
            description = "The Glove Case is a weapon case consisting of community weapons and new gloves released as part of the November 28, 2016 update. It requires a Glove Case Key to be opened."
        ),
        CaseDto(
            name = "Horizon Case",
            releaseDate = "02.08.2018",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Horizon%20Case",
            description = "The Horizon Case is a weapon case featuring 17 community-designed weapon finishes and along Ursus Knife, Navaja Knife, Stiletto Knife, Talon Knife. Released as part of the August 3, 2018 update. It requires a Horizon Case Key to be opened."
        ),
        CaseDto(
            name = "Huntsman Weapon Case",
            releaseDate = "01.05.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Huntsman%20Weapon%20Case",
            description = "The Huntsman Weapon Case is a weapon case consisting of 15 community-made weapon skins released as part of the May 1, 2014 update that also introduced the Bank Collection. It requires a Huntsman Weapon Case Key to be opened. There is a small chance to unbox a Huntsman Knife when opening the case. On June 11th, 2014, the Huntsman Weapon Case was updated, along with the Community Sticker Capsule 1 due to a copyright infringement. The M4A4 | Howl and several other weapon skins were removed and 6 new weapon skins were added."
        ),
        CaseDto(
            name = "Operation Bravo Case",
            releaseDate = "19.09.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Bravo%20Case",
            description = "The Operation Bravo Case is a crate containing 15 Valve-made weapon skins that was released during September 19, 2013. Since the release of Operation Bravo, any player could obtain this case. However, holders of the Operation Bravo pass had a higher chance to receive the case as a drop. Like the CSGO Weapon Case, the standard weapon case key can be used to open this case. Since the end of Operation Bravo, this case drops very rarely."
        ),
        CaseDto(
            name = "Operation Breakout Weapon Case",
            releaseDate = "01.07.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Breakout%20Weapon%20Case",
            description = "The Operation Breakout Case is a weapon case consisting of 14 community-created weapon skins released as part of Operation Breakout with the July 1, 2014 update. Like the Operation Phoenix Weapon Case during Operation Phoenix, Operation Breakout Coin holders received this case. Once the operation ended on October 2, 2014, the weapon case became available for all CS:GO players."
        ),
        CaseDto(
            name = "Operation Broken Fang Case",
            releaseDate = "04.12.2020",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Broken%20Fang%20Case",
            description = "The Broken Fang Case is a case that contains 17 community-made weapon skins released as part of Operation Broken Fang. There is a very small chance to unbox a pair of gloves."
        ),
        CaseDto(
            name = "Operation Hydra Case",
            releaseDate = "23.05.2017",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Hydra%20Case",
            description = "The Operation Hydra Case is a weapon case of community created weapon skins released in the May 23, 2017 update, alongside with Operation Hydra as an exclusive drop to pass holders. It requires a Operation Hydra Case Key to be opened."
        ),
        CaseDto(
            name = "Operation Phoenix Weapon Case",
            releaseDate = "20.02.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Phoenix%20Weapon%20Case",
            description = "The Operation Phoenix Case is a weapon case of 13 community-created weapon skins released on February 20, 2014 as part of Operation Phoenix. The chance for the case to drop was originally exclusive to pass owners. As of the June 11, 2014 update, the weapon case became available as a drop for all players."
        ),
        CaseDto(
            name = "Operation Vanguard Weapon Case",
            releaseDate = "11.11.2014",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Vanguard%20Weapon%20Case",
            description = "The Operation Vanguard Case is a weapon case consisting of 14 community-created weapon skins released as part of Operation Vanguard during the November 11, 2014 update. Like the previous operation cases, Operation Vanguard Coin holders receive exclusive case drops in addition to the normal timed drops during Operation Vanguard as well as the potential to receive a case as a mission drop."
        ),
        CaseDto(
            name = "Operation Wildfire Case",
            releaseDate = "17.02.2016",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Operation%20Wildfire%20Case",
            description = "The Operation Wildfire Case is a weapon case released in the February 17, 2016 update, alongside with Operation Wildfire as an exclusive drop to pass holders. This case also introduces the case-exclusive Bowie Knife which can be awarded at a very low chance."
        ),
        CaseDto(
            name = "Prisma Case",
            releaseDate = "13.03.2019",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Prisma%20Case",
            description = "The Prisma Case is a weapon case featuring 17 community-designed weapon finishes and along with Ursus Knife, Navaja Knife, Stiletto Knife, and Talon Knife, in Chroma finishes. The case was released as part of the March 13, 2019 update. It requires a Prisma Case Key to be opened."
        ),
        CaseDto(
            name = "Prisma 2 Case",
            releaseDate = "01.04.2020",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Prisma%202%20Case",
            description = "Case Prism 2 is a container with weapon skins in Counter-Strike: Global Offensive. To open requires a special key from the Prism 2 case."
        ),
        CaseDto(
            name = "Revolver Case",
            releaseDate = "08.12.2015",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Revolver%20Case",
            description = "The Revolver Case is a weapon case consisting of 16 community-created weapon skins released as part of the December 8, 2015 update. The Revolver Case was the first case to contain skins for the R8 Revolver."
        ),
        CaseDto(
            name = "Shadow Case",
            releaseDate = "17.09.2015",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Shadow%20Case",
            description = "The Shadow Case is a weapon case consisting of 16 community-made weapon skins released as part of the September 18, 2015 update. It requires a Shadow Case Key to be opened. There is a tiny chance to unbox a pair of Shadow Daggers."
        ),
        CaseDto(
            name = "Shattered Web Case",
            releaseDate = "18.11.2019",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Shattered%20Web%20Case",
            description = "The Shattered Web Case is a case that contains 17 community-made weapon skins released as part of Operation Shattered Web. There is a very small chance to unbox the Nomad Knife, Skeleton Knife, Paracord Knife, and the Survival Knife each in 13 different finishes."
        ),
        CaseDto(
            name = "Snakebite Case",
            releaseDate = "03.05.2021",
            dropStatus = "Active",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://www.csgodatabase.com/images/containers/webp/Snakebite_Case.webp",
            description = "The Snakebite Case is a weapon case introduced on 03 May 2021 as part of the End of Broken Fang update. The weapon case contains CS:GO skins from The Snakebite Collection."
        ),
        CaseDto(
            name = "Spectrum Case",
            releaseDate = "15.03.2017",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Spectrum%20Case",
            description = "The Spectrum Case is a weapon case consisting of community weapons and new knife finishes for second generation of knives in Chroma Case released as part of the March 15, 2017 update. It requires a Spectrum Case Key to be opened."
        ),
        CaseDto(
            name = "Spectrum 2 Case",
            releaseDate = "14.09.2017",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Spectrum%202%20Case",
            description = "The Spectrum 2 Case is a weapon case consisting of community weapons and Chroma finishes for the later-added knives, released as part of the September 14, 2017 update. It requires a Spectrum 2 Case Key to be opened."
        ),
        CaseDto(
            name = "Winter Offensive Weapon Case",
            releaseDate = "18.12.2013",
            dropStatus = "Inactive (Rare)",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Winter%20Offensive%20Weapon%20Case",
            description = "The Winter Offensive Weapon Case is a weapon case containing 12 community-created skins. It was released as part of the December 18, 2013 update and requires a Winter Offensive Case Key to open. Since the release of Operation Vanguard, this crate is very rarely dropped. It can also be bought in the Steam Community Market."
        ),
        CaseDto(
            name = "Dreams & Nightmares Case",
            releaseDate = "20.01.2022",
            dropStatus = "Active",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Dreams%20%26%20Nightmares%20Case",
            description = "The Dreams & Nightmares Case is a weapon case introduced on 20 January 2022 as part of the Dreams & Nightmares Case update. The weapon case contains CS:GO skins from The Dreams & Nightmares Collection. The case contains 17 skins, all of which were selected from the Dreams & Nightmares contest which ran from 22 July 2021 through to 21 October 2021. The rare special items in this case are the second generation Gamma Knives which first appeared in the Operation Riptide case."
        ),
        CaseDto(
            name = "Recoil Case",
            releaseDate = "01.07.2022",
            dropStatus = "Active",
            lowestPrice = 0.0,
            volume = 0,
            medianPrice = 0.0,
            imageUrl = "https://api.steamapis.com/image/item/730/Recoil%20Case",
            description = "The Recoil Case is a weapon case introduced on 01 July 2022 as part of the A New Case update. The weapon case contains CS:GO skins from The Recoil Collection. The rare special items in this case are the Broken Fang gloves which first appeared in the Operation Broken Fang case and also appear in the Snakebite case."
        )
    )
}



