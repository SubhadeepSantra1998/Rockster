package com.example.rockstar.common.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.rockstar.R

@Composable
fun IconOutlinedEditTextComponent(
    textState: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String,
    icon: Int,
    contentDescription: String,
    keyboardOptions: KeyboardOptions,
) {

    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        modifier = modifier,
        label = { Text(text = label) },
        singleLine = true,
        keyboardOptions = keyboardOptions,
        leadingIcon = {
            Icon(
                painterResource(id = icon),
                contentDescription = contentDescription
            )
        },
        textStyle = MaterialTheme.typography.titleSmall
    )

}

@Composable
fun PhoneTextField(
    textState: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
) {
    OutlinedTextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        modifier = modifier,
        label = { Text(text = stringResource(id = R.string.phno)) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone, imeAction = imeAction
        ),
        textStyle = MaterialTheme.typography.titleSmall,
        prefix = {
            Text(text = "+91")
        }
    )
}


@Composable
fun BasicEditTextComponent(
    textState: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.None,
    placeholder: String,
) {
    TextField(
        value = textState,
        onValueChange = { onTextChange(it) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text, imeAction = imeAction
        ),
        modifier = modifier
            .fillMaxWidth(),
        maxLines = 5,
    )
}