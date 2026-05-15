package br.com.hellodev.moviestreaming

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import br.com.hellodev.moviestreaming.navigation.hosts.OnBoardingNavHost
import br.com.hellodev.moviestreaming.systemdesign.theme.MovieStreamingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            Box(
                modifier = Modifier
                    .safeDrawingPadding(),
                content = {
                    MovieStreamingTheme {
                        OnBoardingNavHost(navHostController = navController)
                    }
                }
            )
        }
    }
}