package com.example.csgocaseswatcherapp.core.ui.preview

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Portrait Dark",
    group = "Portrait",
    device = "spec:width=411dp,height=923dp,dpi=420,isRound=false,chinSize=0dp,orientation=portrait",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Landscape Dark",
    group = "Landscape",
    device = "spec:width=923dp,height=411dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class PreviewPortraitLandscapeDark
