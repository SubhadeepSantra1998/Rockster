package com.example.rockstar.common.component

import android.graphics.Rect
import android.view.ViewTreeObserver
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun OtpInputField(
    onOtpChanged: (String) -> Unit,
) {
    val otpLength = 6
    val localFocusManager = LocalFocusManager.current
    var otpValue by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = FocusRequester()
    val pattern = remember { Regex("^\\d+\$") }

    BasicTextField(
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged {
                isFocused = it.hasFocus
            },
        value = otpValue, onValueChange = { value ->
            if (value.length <= otpLength) {
                if (value.isEmpty() || value.matches(pattern)){
                    otpValue = value
                    onOtpChanged(otpValue)
                }
            }

            if (otpValue.length == otpLength) localFocusManager.clearFocus()
        },
        decorationBox = {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(otpLength) { index ->

                    val char = when {
                        index >= otpValue.length -> ""
                        else -> otpValue[index].toString()
                    }

                    val isFocus = index == otpValue.length && isFocused

                    /*if (otpValue.isEmpty() && isFocused) {
                        // true
                    } else if (otpValue.lastIndex >= index && isFocused) {
                        // true
                    } else if () {

                    } else {
                        // false
                    }*/

                    OtpCell(
                        char = char,
                        isFocus = isFocus,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = { localFocusManager.clearFocus() })
    )
}

@Composable
fun OtpCell(
    char: String,
    isFocus: Boolean,
    modifier: Modifier = Modifier,
) {

    val borderColor = if (isFocus) MaterialTheme.colorScheme.primary
    else MaterialTheme.colorScheme.outline

    Surface(
        modifier = modifier
            .aspectRatio(1f)
            .border(width = 1.5.dp, color = borderColor, shape = MaterialTheme.shapes.small)
    ) {
        Text(
            text = char,
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.wrapContentSize(align = Alignment.Center)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun OtpInputFieldPreview() {
    MaterialTheme {
        Box(modifier = Modifier.padding(24.dp)) {
            OtpInputField(onOtpChanged = {})
        }
    }
}

@Preview(name = "OptCell Focus", showBackground = true)
@Composable
fun OtpCellFocusPreview(
) {

    MaterialTheme {
        Box(modifier = Modifier.padding(24.dp)) {
            OtpCell(char = "7", isFocus = true)
        }
    }

}


enum class KeyboardStatus {
    Opened,
    Closed
}

@Composable
fun keyboardAsState(initial: KeyboardStatus = KeyboardStatus.Closed): State<KeyboardStatus> {
    val keyboardState = remember { mutableStateOf(initial) }
    val view = LocalView.current
    DisposableEffect(view) {
        val onGlobalListener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardState.value = if (keypadHeight > screenHeight * 0.15) {
                KeyboardStatus.Opened
            } else {
                KeyboardStatus.Closed
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(onGlobalListener)

        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(onGlobalListener)
        }
    }

    return keyboardState
}