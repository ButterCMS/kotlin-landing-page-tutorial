import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.icons.M3Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.buttercmsdivine1.ui.theme.ButterCMSDivine1Theme
import com.example.buttercmsdivine1.ui.theme.greyColor
import com.example.buttercmsdivine1.ui.theme.whiteColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFeatures() {

    var productFeatures by remember { mutableStateOf<List<ProductFeatureModel>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.api.getProductFeatures()
            productFeatures = response
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = greyColor)
            .padding(horizontal = 10.dp, vertical = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Product Features",
            style = MaterialTheme.typography.h6,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Explore our premium feel features from our brand's luxurious men's wristwatches.",
            style = MaterialTheme.typography.body2,
            color = Color.Black.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(features) { feature ->
                FeatureItem(feature = feature)
                }
            }
        }
    }
}

@Composable
fun FeatureItem(feature: FeatureModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = feature.icon,
                contentDescription = null,
                modifier = Modifier.size(70.dp),
                tint = greyColor
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = feature.featureName,
                style = MaterialTheme.typography.h6,
                color = ButterCMSDivine1Theme.colors.primary,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feature.featureDesc,
                style = MaterialTheme.typography.body2,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}
