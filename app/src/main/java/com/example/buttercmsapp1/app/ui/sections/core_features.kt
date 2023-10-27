import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.ImagePainter
import androidx.compose.ui.layout.ContentScale
import com.example.buttercmsdivine1.ui.theme.ButterCMSDivine1Theme
import com.example.buttercmsdivine1.ui.theme.blueColor
import com.example.buttercmsdivine1.ui.theme.whiteColor


@Composable
fun CoreFeatures() {

    var coreFeatures by remember { mutableStateOf<List<CoreFeatureModel>>(emptyList()) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitClient.api.getCoreFeatures()
            coreFeatures = response
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
            .background(color = whiteColor)
            .padding(horizontal = 10.dp, vertical = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Core Features",
            style = MaterialTheme.typography.h6,
            color = blueColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Core premium feel features from our brand's luxurious men's wristwatches.",
            style = MaterialTheme.typography.body2,
            color = blackColor,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            items(coreFeatures) { feature ->
                CoreFeatureItem(feature = feature)
                }
            }
        }
    }
}

@Composable
fun CoreFeatureItem(feature: CoreFeatureModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = feature.imgPath,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(220.dp)
                    .clip(MaterialTheme.shapes.medium)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Icon(
                    painter = feature.icon,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp),
                    tint = blueColor
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = feature.featureName,
                    style = MaterialTheme.typography.h6,
                    color = whiteColor,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = feature.featureDesc,
                    style = MaterialTheme.typography.body2,
                    color = blackColor,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
