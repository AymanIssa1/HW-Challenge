package ayman.issa.hostelworld.features.properties_list.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CurrencyExchange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.common.util.TestTag
import ayman.issa.hostelworld.design_system.core.R
import ayman.issa.hostelworld.design_system.core.carroussel.Carrousel
import ayman.issa.hostelworld.design_system.core.carroussel.CarrouselItem
import ayman.issa.hostelworld.design_system.core.coreComponents.button.Button
import ayman.issa.hostelworld.features.properties_list.domain.model.PropertyUiModel
import coil.compose.AsyncImage
import ayman.issa.hostelworld.design_system.core.coreComponents.text.Text
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextOptions
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextSize
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextWeight
import ayman.issa.hostelworld.design_system.core.getFacilityIcon
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor
import kotlinx.coroutines.launch

@Composable
fun PropertiesListContent(
    viewModel: PropertiesListViewModel,
    state: Response<List<PropertyUiModel>>,
    onNavigateDetailScreen: (propertyId: Int, currency: String) -> Unit
) {
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    AnimatedContent(
        targetState = state,
        label = "Details Transition",
        transitionSpec = {
            fadeIn(animationSpec = tween(600)) togetherWith fadeOut(animationSpec = tween(600))
        }
    ) {
        when (it) {
            is Response.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Error: ${it.exception}")
                    Button(
                        text = "Retry",
                        onClick = {
                            coroutineScope.launch {
                                viewModel.getPropertiesList("EUR") }
                            }
                    )
                }
            }

            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .testTag(TestTag.PropertiesListLoading)
                    )
                }
            }

            is Response.Success -> {
                PropertiesListView(
                    properties = it.data,
                    onNavigateDetailScreen = onNavigateDetailScreen,
                    onCurrencyFabClick = { isBottomSheetVisible = true },
                    listState = listState
                )
                if (isBottomSheetVisible) {
                    CurrencySelectionBottomSheet(
                        onDismiss = { isBottomSheetVisible = false },
                        onCurrencySelected = { currency ->
                            viewModel.onEvent(PropertiesListEvents.GetPropertiesList(currency))
                            isBottomSheetVisible = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PropertiesListView(
    properties: List<PropertyUiModel>,
    onNavigateDetailScreen: (propertyId: Int, currency: String) -> Unit,
    onCurrencyFabClick: () -> Unit,
    listState: LazyListState
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = HostelWorldColor.White.color)
    ) {
        Column(
            modifier = Modifier.background(color = HostelWorldColor.White.color)
        ) {
            HostelWorldToolbar()
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag(TestTag.PropertiesList),
            ) {
                items(properties) { property ->
                    PropertyItem(
                        property = property,
                        onClick = { propertyId, currency ->
                            onNavigateDetailScreen(propertyId, currency)
                        }
                    )
                }
                item { Spacer(modifier = Modifier.height(100.dp)) }
            }
        }
        CurrencyFAB(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            onClick = onCurrencyFabClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencySelectionBottomSheet(
    onDismiss: () -> Unit,
    onCurrencySelected: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = sheetState,
        containerColor = HostelWorldColor.White.color,
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .background(color = HostelWorldColor.White.color)
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Select Currency",
                modifier = Modifier.padding(bottom = 16.dp),
                options = TextOptions(size = TextSize.XLarge, weight = TextWeight.Bold)
            )
            HorizontalDivider(thickness = 0.25.dp)
            listOf("EUR", "USD", "GBP").forEach { currency ->
                CurrencyOption(currency = currency) {
                    coroutineScope.launch {
                        sheetState.hide()
                        onCurrencySelected(currency)
                    }
                }
            }
        }
    }
}

@Composable
fun HostelWorldToolbar() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .padding(top = 20.dp, bottom = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "HostelWorld Challenge",
                options = TextOptions(size = TextSize.XLarge, weight = TextWeight.Bold)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = HostelWorldColor.Border.color)
        )
    }
}

@Composable
fun PropertyItem(
    property: PropertyUiModel,
    onClick: (propertyId: Int, currency: String) -> Unit
) {
    val carrouselItems = arrayListOf<CarrouselItem>()
    property.imagesGallery.forEach { image ->
        carrouselItems.add {
            AsyncImage(
                model = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .testTag(TestTag.PropertyGalleryImage),
                contentScale = ContentScale.FillWidth,
                contentDescription = "Hostel Image",
                placeholder = painterResource(R.drawable.placeholder)
            )
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .border(
                color = HostelWorldColor.Border.color,
                shape = RoundedCornerShape(20.dp),
                width = 1.dp
            )
            .background(
                color = HostelWorldColor.White.color,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick(property.id, property.currency) },
        elevation = CardDefaults.cardElevation(0.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = HostelWorldColor.White.color)
    ) {
        Box {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                Carrousel(
                    items = carrouselItems,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth(),
                    showEdgeItems = false,
                    showIndicators = true
                )
                Spacer(modifier = Modifier.height(12.dp))
                PropertyDetails(property)
            }
            if (property.isFeatured) {
                FeaturesBadge(
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .testTag(TestTag.FeaturedHostel)
                )
            }
        }
    }
}

@Composable
fun PropertyDetails(property: PropertyUiModel) {
    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
        Text(
            text = property.name,
            modifier = Modifier.padding(4.dp),
            options = TextOptions(size = TextSize.XLarge, weight = TextWeight.Bold)
        )
        Spacer(modifier = Modifier.height(4.dp))
        RatingRow(property)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "${property.type} - ${property.distanceFromCityCentre} from city centre",
            options = TextOptions(size = TextSize.Small, weight = TextWeight.Light)
        )
        Spacer(modifier = Modifier.height(4.dp))
        FacilitiesRow(property)
        Spacer(modifier = Modifier.height(4.dp))
        PricesAndDiscount(property)
        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun RatingRow(property: PropertyUiModel) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "⭐")
        Text(
            text = "${property.overallRating}",
            options = TextOptions(size = TextSize.XLarge, weight = TextWeight.Bold)
        )
        Text(
            text = property.numberOfRatings,
            options = TextOptions(size = TextSize.Regular, weight = TextWeight.Light)
        )
    }
}

@Composable
fun FacilitiesRow(property: PropertyUiModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        property.facilities
            .map { facilityEnum -> getFacilityIcon(facilityEnum) }
            .toSet()
            .forEach { facilityIcon ->
                FacilityIcon(imageVector = facilityIcon)
            }
    }
}

@Composable
private fun PricesAndDiscount(property: PropertyUiModel) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        StartingFromPriceView(
            title = "Privates from",
            currency = property.currencySign,
            startingPrice = property.privateRoomStartingPrice,
            originalPrice = property.privateRoomOriginalPrice,
            discount = property.privateRoomDiscountPercentage
        )
        if (property.dormStartingPrice != null) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .height(88.dp)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                        .background(color = HostelWorldColor.Border.color)
                )
            }
            StartingFromPriceView(
                title = "Dorms from",
                currency = property.currencySign,
                startingPrice = property.dormStartingPrice,
                originalPrice = property.dormOriginalPrice,
                discount = property.dormDiscountPercentage
            )
        }
    }
}

@Composable
fun DiscountBadge(discount: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(width = 60.dp, height = 30.dp)
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 1f, size.height)
                lineTo(size.width, 0f)
                lineTo(size.width * 0.0f, 0f)
                lineTo(size.width * 0.1f, size.height)
                close()
            }
            drawPath(path, color = HostelWorldColor.Magenta.color)
        }
        Text(
            text = discount,
            color = HostelWorldColor.White,
            options = TextOptions(weight = TextWeight.Bold, size = TextSize.XSmall),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun StartingFromPriceView(
    title: String,
    currency: String,
    startingPrice: String?,
    originalPrice: String?,
    discount: Int?
) {
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        if (discount != null && discount > 0) {
            DiscountBadge(discount = "-$discount%")
        }
        Text(
            text = title,
            color = HostelWorldColor.GrayTextColor,
            options = TextOptions(size = TextSize.Small)
        )
        Text(
            text = "$currency $startingPrice",
            options = TextOptions(weight = TextWeight.Bold, size = TextSize.Large)
        )
        if (!originalPrice.isNullOrBlank() && discount != null && discount > 0) {
            Text(
                text = "$currency $originalPrice",
                color = HostelWorldColor.GrayTextColor,
                style = TextStyle(textDecoration = TextDecoration.LineThrough),
                options = TextOptions(size = TextSize.Small)
            )
        }
    }
}

@Composable
fun FacilityIcon(imageVector: ImageVector) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .background(
                color = HostelWorldColor.FacilityIconBackgroundColor.color,
                shape = CircleShape
            )
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center),
            tint = HostelWorldColor.FacilityIconColor.color
        )
    }
}

@Composable
fun CurrencyFAB(
    modifier: Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        containerColor = HostelWorldColor.SecondaryColor.color,
        contentColor = HostelWorldColor.White.color
    ) {
        Icon(
            imageVector = Icons.Filled.CurrencyExchange,
            contentDescription = "Currency Icon",
            tint = HostelWorldColor.White.color
        )
    }
}

@Composable
fun CurrencyOption(currency: String, onClick: () -> Unit) {
    Text(
        text = currency,
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp)
    )
}

@Composable
fun FeaturesBadge(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(32.dp)
            .wrapContentWidth()
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val path = Path().apply {
                moveTo(0f, size.height)
                lineTo(size.width * 0.95f, size.height)
                lineTo(size.width, 0f)
                lineTo(size.width * 0.0f, 0f)
                lineTo(size.width * 0.0f, size.height)
                close()
            }
            drawPath(path, color = HostelWorldColor.Magenta.color)
        }
        Text(
            text = "Hostel World Featured",
            color = HostelWorldColor.White,
            options = TextOptions(weight = TextWeight.Bold, size = TextSize.XSmall),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .align(Alignment.Center)
        )
    }
}