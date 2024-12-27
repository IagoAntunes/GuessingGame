package com.iagoaf.guessinggame.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.iagoaf.guessinggame.R

private val DarkColorScheme = darkColorScheme(
    primary = AppColors.purpleL,
    secondary = AppColors.purpleM,
    tertiary = AppColors.purpleD
)

private val LightColorScheme = lightColorScheme(
    primary = AppColors.purpleL,
    secondary = AppColors.purpleM,
    tertiary = AppColors.purpleD,
)

val notoSansFont = FontFamily(
    Font(R.font.notosans_regular),
    Font(R.font.notosans_light, FontWeight.Light),
    Font(R.font.notosans_medium, FontWeight.Medium),
    Font(R.font.notosans_semibold, FontWeight.SemiBold),
    Font(R.font.notosans_extrabold, FontWeight.ExtraBold),
)


@Composable
fun GuessingGameTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}