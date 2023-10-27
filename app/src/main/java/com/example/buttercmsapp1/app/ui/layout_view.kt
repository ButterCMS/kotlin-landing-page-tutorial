import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.icons.M3Icons
import androidx.compose.material3.icons.M3Icons.ClearRounded
import androidx.compose.material3.icons.M3Icons.MenuRounded
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalDensity.currentDensity
import androidx.compose.ui.platform.LocalDensity.currentDensity
import androidx.compose.ui.platform.LocalDensity.currentDensity
import androidx.compose.ui.platform.LocalDensity.currentDensity
import androidx.compose.ui.platform.LocalDensity.currentDensity
import androidx.compose.ui.tooling.preview.Preview
import com.example.buttercmsdivine1.src.App
import com.example.buttercmsdivine1.src.R
import com.example.buttercmsdivine1.src.sections.CoreFeatures
import com.example.buttercmsdivine1.src.sections.ProductFeatures
import com.example.buttercmsdivine1.src.sections.Products
import com.example.buttercmsdivine1.ui.theme.ButterCMSDivine1Theme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding


data class CoreFeatureModel(
    val featureName: String,
    val icon: Int, // Assuming you're using resource IDs for icons
    val featureDesc: String,
    val imgPath: Int // Assuming you're using resource IDs for images
)

data class ProductFeatureModel(
    val title: String,
    val description: String,
    val imageUrl: Int // Assuming you're using resource IDs for images
)

data class ProductModel(
    val assetName: String, // Assuming it's a String representing the asset name
    val title: String,
    val desc: String
)


@OptIn(ExperimentalMaterial3Api::class)
class LayoutView : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                ButterCMSDivine1Theme {
                    LayoutContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutContent() {
    val scaffoldState = rememberScaffoldState()
    val items = listOf("Home", "Contact us", "Explore More")

    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text("Rupa", style = MaterialTheme.typography.h6) },
                actions = {
                    BuyNowButton(onClick = {}) // Define your BuyNowButton composable
                    SideNavButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) // Define your SideNavButton composable
                },
                backgroundColor = Color.Gray
            )
        },
        endDrawer = {
            DrawerContent(items = items) {
                coroutineScope.launch {
                    scaffoldState.drawerState.close()
                }
            }
        },
        content = {
            LayoutBodyContent()
        }
    )
}

@Composable
fun DrawerContent(items: List<String>, onItemSelected: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        items.forEach { item ->
            Text(
                text = item,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .clickable { onItemSelected(item) }
                    .padding(8.dp)
            )
        }
    }
}

@Composable
fun LayoutBodyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Products()
        ProductFeatures()
        CoreFeatures()
    }
}

@Composable
fun BuyNowButton(onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth()
            .padding(horizontal = 10.dp, vertical = 8.dp)
    ) {
        Icon(
            imageVector = M3Icons.ShoppingCartRounded,
            contentDescription = null,
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = "Buy Now")
    }
}

@Composable
fun SideNavButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .padding(horizontal = 5.dp, vertical = 8.dp)
    ) {
        Icon(imageVector = M3Icons.MenuRounded, contentDescription = null)
    }
}
