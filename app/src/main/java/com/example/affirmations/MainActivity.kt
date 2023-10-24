/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun AffirmationsApp() {
    AffirmationList(affirmations = Datasource().loadAffirmations())
}

@Composable
fun AffirmationList(affirmations: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
       items (affirmations) { affirmation ->
            AffirmationCard(affirmation = affirmation, modifier = Modifier.padding(8.dp))

        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(id = affirmation.imageResourceId),
                contentDescription = stringResource(id = affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop

            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

/*
mdpi - resources for medium-density screens (~160 dpi)
hdpi - resources for high-density screens (~240 dpi)
xhdpi - resources for extra-high-density screens (~320 dpi)
xxhdpi - resources for extra-extra-high-density screens (~480 dpi)
xxxhdpi - resources for extra-extra-extra-high-density screens (~640 dpi)
nodpi - resources that are not meant to be scaled, regardless of the screen's pixel density
anydpi - resources that scale to any density

as imagens fican em res/mipmap-<denstity-name>  -> api < 26
 sao vetores xml aredondados e quadrados com extensao .webp
 a partir da api 26 tem icones adaptativos -> mipmap-anydapi-v26 (imagens vetorias)

 size for a vector background and foregroudn image
 108x108 dpi
 66 dpi do centro da zona segura

 ** o proprio resource manager gera os icones apartir dos xml background e foreground
*
* */



