package com.example.top5movies.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.top5movies.ui.common.theme.Top5MoviesTheme

@Composable
fun MainTextField(
    value: String,
    label: String? = null,
    onValueChange: (String) -> Unit = {  },
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    onTrailingClick: () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        value = value,
        label = label?.let { { Text(text = it) } },
        trailingIcon = trailingIcon?.let { icon -> {
            IconButton(onClick = onTrailingClick) {
                Icon(icon, contentDescription = "")
            }
        } },
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        leadingIcon = leadingIcon?.let { { Icon(it, contentDescription = "") } },
        onValueChange = onValueChange
    )
}

@Preview
@Composable
private fun MainTextFieldPreview() {
    Top5MoviesTheme {
        MainTextField(
            value = "",
            leadingIcon = Icons.Rounded.Person
        )
    }
}

