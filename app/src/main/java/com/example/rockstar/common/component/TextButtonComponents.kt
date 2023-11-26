package com.example.rockstar.common.component

import android.graphics.fonts.FontStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LargeTextButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.SemiBold,
    onClick: () -> Unit,
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LargeTextButtonComponentPreview() {
    LargeTextButtonComponent(text = "Hello", onClick = {})
}

@Composable
fun MediumTextButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.SemiBold,
    onClick: () -> Unit,
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}
@Preview(showBackground = true)
@Composable
fun MediumTextButtonComponentPreview() {
    MediumTextButtonComponent(text = "Hello", onClick = {})
}

@Composable
fun SmallTextButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.primary,
    fontWeight: FontWeight = FontWeight.SemiBold,
    onClick: () -> Unit,
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = fontWeight,
            color = textColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SmallTextButtonComponentPreview() {
    SmallTextButtonComponent(text = "Hello", onClick = {})
}