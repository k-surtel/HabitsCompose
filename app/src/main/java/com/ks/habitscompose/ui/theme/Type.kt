package com.ks.habitscompose.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.ks.habitscompose.R

val Lato = FontFamily(
    Font(R.font.lato_thin, FontWeight.Thin),
    Font(R.font.lato_light, FontWeight.Light),
    Font(R.font.lato_regular, FontWeight.Normal),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_black, FontWeight.Black),
)

val Typography = Typography(
    h4 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Light,
        fontSize = 34.sp,
        color = Cherry
    ),
    h6 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Light,
        fontSize = 20.sp,
        color = GrayishWhite
    ),
    body1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Light,
        fontSize = 16.sp,
        color = GrayishWhite
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)


//body1 = TextStyle(
//fontFamily = FontFamily.Default,
//fontWeight = FontWeight.Normal,
//fontSize = 16.sp
//)