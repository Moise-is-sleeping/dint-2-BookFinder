package com.calculator.bookfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.calculator.bookfinder.ui.theme.BookFinderTheme
import data.Routes.Routes
import ui.Screens.BooKDescriptionScreen
import ui.Screens.FriendsScreen
import ui.Screens.HomeScreen
import ui.Screens.HomeScreen
import ui.Screens.LoginScreen
import ui.Screens.RegisterScreen
import ui.Screens.SavedScreen
import ui.ViewModel.BookViewModel

import ui.Screens.SearchScreen
import ui.Screens.UsersProfileScreen
import ui.ViewModel.BookDatabaseViewModel
import ui.ViewModel.LoginViewModel
import ui.ViewModel.UserInteractionViewmodel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookFinderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val bookViewModel = BookViewModel()
                    val loginViewModel = LoginViewModel()
                    val bookDatabaseViewModel = BookDatabaseViewModel()
                    val userInteractionViewmodel = UserInteractionViewmodel()

                    val navController = rememberNavController()
                    NavHost(navController = navController , startDestination = Routes.LoginScreen.route ){
                        composable(Routes.LoginScreen.route){
                            LoginScreen(loginViewModel,navController,bookDatabaseViewModel)
                        }
                        composable(Routes.HomeScreen.route){
                            HomeScreen(bookViewModel,navController,bookDatabaseViewModel,userInteractionViewmodel)
                        }
                        composable(Routes.RegisterScreen.route){
                            RegisterScreen(loginViewModel,navController)
                        }
                        composable(Routes.SearchScreen.route){
                            SearchScreen(bookDatabaseViewModel,bookViewModel,navController,userInteractionViewmodel)
                        }
                        composable(Routes.BookDescriptionScreen.route){
                            BooKDescriptionScreen(bookDatabaseViewModel,bookViewModel, navController)
                        }
                        composable(Routes.SavedScreen.route){
                            SavedScreen(bookDatabaseViewModel,bookViewModel, navController)
                        }
                        composable(Routes.FriendsScreen.route){
                            FriendsScreen(bookViewModel,navController,bookDatabaseViewModel,userInteractionViewmodel)
                        }
                        composable(Routes.UsersProfileScreen.route){
                            UsersProfileScreen(bookDatabaseViewModel,bookViewModel, navController,userInteractionViewmodel)
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BookFinderTheme {
        Greeting("Android")
    }
}