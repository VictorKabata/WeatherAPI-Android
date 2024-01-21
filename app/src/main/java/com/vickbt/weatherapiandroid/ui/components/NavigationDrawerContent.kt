package com.vickbt.weatherapiandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.weatherapiandroid.R

@Composable
fun NavigationDrawerContent(
    modifier: Modifier,
    locationQuery: String,
    locationQueryChange: (String) -> Unit,
    onLocationQueried: () -> Unit,
    isThemeCheckedOn: Boolean,
    onThemeCheckChanged: (Boolean) -> Unit,
    isImperialCheckedOn: Boolean,
    onImperialCheckChanged: (Boolean) -> Unit
) {
    ModalDrawerSheet(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 24.dp)
        ) {

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                value = locationQuery,
                onValueChange = { locationQueryChange(it) },
                keyboardActions = KeyboardActions.Default,
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(2.dp),
                placeholder = { Text(text = stringResource(R.string.enter_location)) }
            )

            //endregion Theme option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp,horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Theme",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Switch(checked = isThemeCheckedOn, onCheckedChange = { onThemeCheckChanged(it) })
            }
            //endregion

            //region Measurement system
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp,horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Imperial System",
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Switch(
                    checked = isImperialCheckedOn,
                    onCheckedChange = { onImperialCheckChanged(it) })
            }
            //endregion
        }
    }
}
