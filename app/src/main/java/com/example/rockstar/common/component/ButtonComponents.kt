package com.example.rockstar.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rockstar.R

@Composable
fun BackButtonComponent(
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = stringResource(R.string.back_arrow),
        )
    }
}

@Composable
fun IconButtonComponent(
    icon: ImageVector,
    onClick: () -> Unit,
) {

    ElevatedButton(
        onClick = onClick
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(R.string.back_arrow),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonComponentPreview() {
    BackButtonComponent(onClick = {})
}


@Composable
fun PrimaryButtonComponent(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(vertical = 5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonComponentPreview() {
    PrimaryButtonComponent(text = "Button", onClick = {})
}



