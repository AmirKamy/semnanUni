package com.semnan.semnanuniversity.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.semnan.semnanuniversity.R


object AppFont {
    val fonts = FontFamily(
        Font(R.font.is_check_regular)
    )
}

val Typography = Typography(
    displaySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        fontFamily = AppFont.fonts
    ),
    headlineSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        fontFamily = AppFont.fonts
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        fontFamily = AppFont.fonts
    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        fontFamily = AppFont.fonts
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        letterSpacing = (.5).sp,
        fontSize = 18.sp,
        fontFamily = AppFont.fonts
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 28.sp,
        fontFamily = AppFont.fonts
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        fontFamily = AppFont.fonts
    )
)