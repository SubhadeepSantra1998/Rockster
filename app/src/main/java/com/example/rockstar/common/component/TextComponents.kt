package com.example.rockstar.common.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TitleTextComponent(
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        textAlign = textAlign,
    )
}

@Composable
fun HeadingTextComponent(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = modifier
    )
}


@Composable
fun DividerTextComponent(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        Divider(
            modifier = Modifier
                .width(70.dp)
                .padding(top = 4.dp),
            thickness = 1.dp
        )

        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp),
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.outline
        )

        Divider(
            modifier = Modifier
                .width(70.dp)
                .padding(top = 4.dp),
            thickness = 1.dp
        )
    }
}

@Preview
@Composable
fun DividerTextComponentPreview() {
    DividerTextComponent(text = "Test")
}


@Composable
fun ClickableAnnotatedLoginTextComponent(
    tryingToLogin: Boolean = true,
    onTextSelected: (String) -> Unit,
) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.onSurfaceVariant)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString,
        onClick = { offset ->

            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also { span ->
                    if (span.item == loginText) {
                        onTextSelected(span.item)
                    }
                }
        },
    )
}

@Composable
fun OtpTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onImeAction: () -> Unit,
) {
    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            if (newValue.length <= 1) {
                onValueChange(newValue)
            }
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { onImeAction() }),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        textStyle = TextStyle.Default.copy(color = Color.Black),
        cursorBrush = SolidColor(Color.Transparent) // Hide the cursor
    )
}


@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    hyperLinks: Map<String, String>,
    textStyle: TextStyle = TextStyle.Default,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.SemiBold,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    fontSize: TextUnit = TextUnit.Unspecified,
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)

        for ((key, value) in hyperLinks) {

            val startIndex = fullText.indexOf(key)
            val endIndex = startIndex + key.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize = fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = value,
                start = startIndex,
                end = endIndex
            )
        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }

    val uriHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        style = textStyle,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}

@Composable
fun TextError(errorMessage: String, modifier: Modifier = Modifier) {
    Text(
        text = errorMessage,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.error,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun TextErrorPreview() {
    TextError(errorMessage = "Error")
}

@Composable
fun HeadlineTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Bold,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.headlineSmall,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun HeadlineTextComponentPreview() {
    HeadlineTextComponent(text = "Hello")
}

@Composable
fun TitleMediumTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TitleMediumTextComponentPreview() {
    TitleMediumTextComponent(text = "Hello")
}

@Composable
fun TitleSmallTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onBackground,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun TitleSmallTextComponentPreview() {
    TitleSmallTextComponent(text = "Hello")
}

@Composable
fun BodyMediumTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun BodyMediumTextComponentPreview() {
    BodyMediumTextComponent(text = "Hello")
}

@Composable
fun BodySmallTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Center,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    fontWeight: FontWeight = FontWeight.SemiBold,
) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        style = MaterialTheme.typography.bodySmall,
        fontWeight = fontWeight,
        textAlign = textAlign,
    )
}

@Preview(showBackground = true)
@Composable
fun BodySmallTextComponentPreview() {
    BodySmallTextComponent(text = "Hello")
}

@Composable
fun DescriptionTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = MaterialTheme.colorScheme.outline,
    fontWeight: FontWeight = FontWeight.Medium,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun DescriptionTextComponentPreview() {
    DescriptionTextComponent(text = "Hello")
}

@Composable
fun LabelTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Black,
    fontWeight: FontWeight = FontWeight.Medium,
) {
    Text(
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.labelSmall,
        fontWeight = fontWeight,
        color = color
    )
}

@Preview(showBackground = true)
@Composable
fun LabelTextComponentPreview() {
    LabelTextComponent(text = "Hello")
}