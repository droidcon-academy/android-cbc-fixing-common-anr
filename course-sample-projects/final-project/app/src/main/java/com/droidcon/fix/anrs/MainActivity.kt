package com.droidcon.fix.anrs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.droidcon.fix.anrs.samples.anrByOneLongOperation
import com.droidcon.fix.anrs.samples.anrByMissingOnForegroundCall
import com.droidcon.fix.anrs.samples.anrByManySmallOperations
import com.droidcon.fix.anrs.samples.anrBySlowBroadcastReceiver
import com.droidcon.fix.anrs.samples.anrByThreadDeadlock
import com.droidcon.fix.anrs.samples.anrByWaitingOnBgThread
import com.droidcon.fix.anrs.ui.theme.FixAnrsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val anrExamples = listOf(
            AnrExample("ANR by blocking main thread", ::anrByOneLongOperation),
            AnrExample("ANR by thread deadlock", ::anrByThreadDeadlock),
            AnrExample("ANR by waiting on background thread", ::anrByWaitingOnBgThread),
            AnrExample("ANR by scheduling too much work", ::anrByManySmallOperations),
            AnrExample("ANR by missing onForeground call (crashes app)") {
                anrByMissingOnForegroundCall(applicationContext)
            },
            AnrExample(
                "ANR by slow BroadcastReceiver"
            ) { anrBySlowBroadcastReceiver(applicationContext) },
        )

        setContent {
            FixAnrsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(16.dp),
                            text = "Click any button to trigger an ANR.\n\nYou must then tap anywhere else on the screen, and after a few seconds the ANR dialog will show.",
                            textAlign = TextAlign.Center
                        )
                        ScrollableAnrExampleList(anrExamples)
                    }
                }
            }
        }
    }
}

@Composable
private fun ScrollableAnrExampleList(examples: List<AnrExample>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)
    ) {
        items(examples) { example ->
            Button(
                onClick = example.action,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(text = example.description)
            }
        }
    }
}

private data class AnrExample(val description: String, val action: () -> Unit)
