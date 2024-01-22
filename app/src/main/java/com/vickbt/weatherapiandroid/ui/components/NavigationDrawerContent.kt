package com.vickbt.weatherapiandroid.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NavigationDrawerContent(
    modifier: Modifier,
    isDarkTheme: Boolean,
    onThemeCheckChanged: (Boolean) -> Unit
) {
    var isDarkThemeOn by remember { mutableStateOf(isDarkTheme) }

    ModalDrawerSheet(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 24.dp)
        ) {
            //endregion Theme option
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Theme",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Switch(
                    checked = isDarkThemeOn,
                    onCheckedChange = {
                        isDarkThemeOn = it
                        onThemeCheckChanged(it)
                    }
                )
            }
            //endregion

            //region Measurement system
            /*Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Imperial System",
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Switch(
                    checked = isImperialOn,
                    onCheckedChange = {
                        isImperialOn = it
                        onImperialCheckChanged(it)
                    }
                )
            }*/
            //endregion
        }
    }
}
