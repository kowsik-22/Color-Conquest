import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.Screen
import com.example.myapplication.content
import com.example.myapplication.content1

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(route = Screen.Main.route) {
            content(navController = navController)
        }
        composable(
            route = Screen.Detail.route
        ) {
            content1()
        }
    }
}