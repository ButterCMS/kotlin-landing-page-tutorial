import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.icons.M3Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.icons.M3Icons.ShoppingCartRounded
import androidx.compose.material3.icons.M3Icons.CheckCircleOutline
import androidx.compose.material3.icons.M3Icons.CloseCircleOutline
import androidx.compose.material3.icons.M3Icons.CloseRounded
import androidx.compose.material3.icons.M3Icons.DoneRounded
import androidx.compose.material3.icons.M3Icons.DoneAllRounded
import androidx.compose.material3.icons.M3Icons.ClearRounded
import androidx.compose.material3.icons.M3Icons.DeleteOutline
import androidx.compose.material3.icons.M3Icons.InfoRounded
import androidx.compose.material3.icons.M3Icons.MenuRounded
import androidx.compose.material3.icons.M3Icons.MoreVertRounded
import androidx.compose.material3.icons.M3Icons.SearchRounded
import androidx.compose.material3.icons.M3Icons.StarOutline
import androidx.compose.material3.icons.M3Icons.WarningRounded
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.buttercmsdivine1.ui.theme.ButterCMSDivine1Theme
import com.example.buttercmsdivine1.ui.theme.greyColor
import com.example.buttercmsdivine1.ui.theme.whiteColor

@Composable
fun Products() {

    var products by remember { mutableStateOf<List<ProductModel>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.api.getProducts()
            products = response
            loading = false
        } catch (e: Exception) {
            // Handle error (e.g., show a Snackbar)
            loading = false
        }
    }

    if (loading) {
        // Show loading indicator
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    } else {

        LazyColumn {
            items(products) { product ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    BuildIntoImg(assetName = product.assetName)
                    BuildIntoName(
                        title = product.title,
                        desc = product.desc
                    )
                }
            }
        }
    }
}

@Composable
fun BuildIntoImg(assetName: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = greyColor)
    ) {
        Image(
            painter = painterResource("assets/$assetName.png"),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun BuildIntoName(title: String, desc: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp)
            .background(color = whiteColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = desc,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colorScheme.primaryVariant
            )
            Spacer(modifier = Modifier.height(10.dp))
            BuyNowBtn(txt = "Buy Now")
        }
    }
}

@Composable
fun BuyNowBtn(txt: String) {
    OutlinedButton(
        onClick = { /* Handle Buy Now button click */ },
        modifier = Modifier
            .wrapContentWidth()
    ) {
        Icon(imageVector = M3Icons.ShoppingCartRounded, contentDescription = null)
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = txt)
    }
}
