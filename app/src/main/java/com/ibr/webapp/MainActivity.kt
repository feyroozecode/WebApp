@file:OptIn(ExperimentalMaterial3Api::class)

package com.ibr.webapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.ViewGroup.LayoutParams
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
// compose
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.ibr.webapp.ui.theme.WebAppTheme

/*
  Classe Principal appelant notre View a savoir MainContainer
*/
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainContent()
                }
            }
        }
    }
}

// lien de la page a afficher par le webview
const val BASE_URL:String = "https://www.google.com"

/*
  Notre Ecran principal
  Diviser en plusieurs partie
  - Scaffold => Notre echafaudage(represente le squelette de notre ecran)
     Le Scaffold contient deux (2) partie l'entete et le contenue
    - topBar un paramatre de Scaffold pour ajouter une entete TopAppBar
    - content: Vue principal a afficher dans le corp de l'ecran ici: MyWebView
*/
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent() {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            text = "Web App",
                            color = Color.DarkGray,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        },
        content = { MyWebView(url = BASE_URL) }
    )
}

/*
  Notre WebView affichant la page web
  avec un parametre pour le lien de la page a charger
  On utilise ici AndroidView qui est composable et on lui passe comme paramatre
  un WebView(Composable)
  on applique un layoutParams pour ocupper tout le space de l'ecran
  Puis on charge l'app avec @loadUrl un parametre de WebView
*/
@Composable
fun MyWebView(url: String) {

    AndroidView(factory = {
        WebView(it)
            .apply {
                layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT
                )
                webViewClient = WebViewClient()
                loadUrl(url)

            }
        },
        update = {
            it.loadUrl(url)
        }
    )

}
// Pour affciher en previsualant notre application
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WebAppTheme {
        MainContent()
    }
}