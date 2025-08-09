package com.example.csgocaseswatcherapp.core.ui

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.csgocaseswatcherapp.R

@Composable
fun MainMenuButton(
    buttonText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp, top = 16.dp)
            .width(280.dp)
            .height(60.dp),
        shape = RoundedCornerShape(8.dp), // adjust radius to match screenshot
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primaryColor),
            contentColor = Color.White
        ),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp) // flat like the image
    ) {
        Text(
            text = buttonText,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview
fun MainMenuButtonPreview(){
    MainMenuButton("Case Overview",{})
}