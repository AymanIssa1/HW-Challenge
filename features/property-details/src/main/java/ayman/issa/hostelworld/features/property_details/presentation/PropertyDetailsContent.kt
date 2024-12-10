package ayman.issa.hostelworld.features.property_details.presentation

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.PinDrop
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ayman.issa.hostelworld.common.Response
import ayman.issa.hostelworld.common.util.TestTag
import ayman.issa.hostelworld.common.util.translateToFacility
import ayman.issa.hostelworld.design_system.core.R
import ayman.issa.hostelworld.design_system.core.VerticalGrid
import ayman.issa.hostelworld.design_system.core.coreComponents.button.Button
import ayman.issa.hostelworld.design_system.core.coreComponents.text.Text
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextOptions
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextSize
import ayman.issa.hostelworld.design_system.core.coreComponents.text.TextWeight
import ayman.issa.hostelworld.design_system.core.getFacilityIcon
import ayman.issa.hostelworld.design_system.core.tabSelector.TabNavigationItem
import ayman.issa.hostelworld.design_system.core.tabSelector.TabSelector
import ayman.issa.hostelworld.design_system.core.theme.HostelWorldColor
import ayman.issa.hostelworld.features.property_details.domain.model.FacilitiesGroup
import ayman.issa.hostelworld.features.property_details.domain.model.Facility
import ayman.issa.hostelworld.features.property_details.domain.model.PropertyDetailsUiModel
import ayman.issa.hostelworld.features.property_details.domain.model.RatingBreakdown
import coil.compose.AsyncImage


@Composable
fun PropertyDetailsContent(
    state: Response<PropertyDetailsUiModel>,
    onNavigateBack: () -> Unit,
) {
    val context = LocalContext.current
    var isBottomSheetVisible by remember { mutableStateOf(false) }
    var currentSelectTabIndex by remember { mutableIntStateOf(0) }

    BackHandler {
        if (isBottomSheetVisible) {
            isBottomSheetVisible = false
        } else {
            onNavigateBack()
        }
    }

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
                }
            }

            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .testTag(TestTag.PropertyDetailsLoading)
                    )
                }
            }

            is Response.Success -> {
                Box {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Toolbar(
                            name = it.data.name,
                            onNavigateBack = onNavigateBack
                        )

                        LazyColumn(
                            modifier = Modifier.testTag(TestTag.PropertyDetailsList)
                        ) {
                            item { Spacer(modifier = Modifier.height(12.dp)) }
                            item { ImageGalleryRow(it.data.imagesGallery) }
                            item {
                                HostelInfoCard(
                                    type = it.data.type,
                                    name = it.data.name,
                                    overallRating = it.data.overallRating,
                                    numberOfRatings = it.data.numberOfRatings,
                                )
                            }
                            item {
                                LazyRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    contentPadding = PaddingValues(start = 16.dp)
                                ) {
                                    items(it.data.headerFacilities) { facility ->
                                        FacilityItem(
                                            modifier = Modifier,
                                            facility = facility
                                        )
                                    }
                                }
                            }
                            item {
                                AboutSection(
                                    description = it.data.about,
                                    onReadMoreClick = {
                                        isBottomSheetVisible = true
                                        currentSelectTabIndex = 0
                                    }
                                )
                            }
                            item {
                                HorizontalDivider(
                                    thickness = 1.dp,
                                    color = HostelWorldColor.Border.color,
                                    modifier = Modifier.padding(vertical = 16.dp)
                                )
                            }
                            item {
                                HighlightedFacilities(
                                    facilitiesGroups = it.data.facilitiesGroup,
                                    onViewAllFacilitiesClick = {
                                        isBottomSheetVisible = true
                                        currentSelectTabIndex = 1
                                    }
                                )
                            }
                            item {
                                ReviewsSection(
                                    overallRating = it.data.overallRating,
                                    descriptionAndNumberOfRatings = it.data.descriptionAndNumberOfRatings,
                                    ratingBreakdown = it.data.ratingBreakdown,
                                )
                            }
                            item {
                                MapView(
                                    onClick = {
                                        startGoogleMaps(
                                            context = context,
                                            latitude = it.data.latitude,
                                            longitude = it.data.longitude,
                                        )
                                    }
                                )
                            }
                            item {
                                LocationSection(
                                    address = it.data.address,
                                    onAddressClick = {
                                        startGoogleMaps(
                                            context = context,
                                            latitude = it.data.latitude,
                                            longitude = it.data.longitude,
                                        )
                                    },
                                    onContentCopyClick = { address ->
                                        copy(context, address)
                                    }
                                )
                                Spacer(modifier = Modifier.height(160.dp))
                            }
                        }
                    }

                    ChooseRoomBottomBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter),
                        startingPrice = "${it.data.currencySign} ${it.data.startingPrice}",
                        onChooseRoomClick = {
                            // Handle room selection
                        }
                    )

                    AnimatedVisibility(
                        visible = isBottomSheetVisible,
                        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(tween(300)),
                        exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(tween(300)),
                    ) {
                        BottomSheetContent(
                            data = it.data,
                            onClose = { isBottomSheetVisible = false },
                            currentSelectTabIndex = currentSelectTabIndex,
                            onTabIndexChange = { newIndex -> currentSelectTabIndex = newIndex }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomSheetContent(
    data: PropertyDetailsUiModel,
    onClose: () -> Unit,
    currentSelectTabIndex: Int,
    onTabIndexChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(HostelWorldColor.White.color)
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .shadow(elevation = 1.dp, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Property Details",
                options = TextOptions(
                    size = TextSize.XLarge,
                    weight = TextWeight.Bold
                ),
                modifier = Modifier.weight(1f)
            )

            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                tint = HostelWorldColor.DarkGray.color,
                modifier = Modifier.clickable { onClose() }
            )
        }

        HorizontalDivider(thickness = 0.5.dp)

        val tabs = propertyDetailsTabs()
        TabSelector(
            modifier = Modifier.fillMaxWidth(),
            items = tabs,
            selectedIndex = currentSelectTabIndex,
            onIndexChanged = onTabIndexChange
        )

        HorizontalDivider(thickness = 0.5.dp)

        when (currentSelectTabIndex) {
            0 -> {
                BottomSheetAboutSection(data)
            }

            1 -> {
                BottomSheetFacilitiesSection(data)
            }

            2 -> {
                BottomSheetHomeRulesSection(data)
            }
        }
    }
}

@Composable
private fun BottomSheetHomeRulesSection(data: PropertyDetailsUiModel) {
    LazyColumn {
        item {
            Text(
                text = "Cancellation Policy",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                options = TextOptions(
                    size = TextSize.XLarge,
                    weight = TextWeight.Bold
                )
            )
            Text(
                text = data.freeCancellation.label,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp),
                options = TextOptions(
                    size = TextSize.Large,
                    weight = TextWeight.Bold
                )
            )
            Text(
                text = data.freeCancellation.description,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .padding(top = 16.dp)
            )
        }
    }
}

@Composable
private fun BottomSheetFacilitiesSection(data: PropertyDetailsUiModel) {
    LazyColumn {
        items(data.facilitiesGroup) { facilityGroup ->
            FacilitiesGroupItem(facilitiesGroup = facilityGroup)
        }
    }
}

@Composable
private fun BottomSheetAboutSection(data: PropertyDetailsUiModel) {
    Text(
        text = data.about,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp)
    )
}

private fun propertyDetailsTabs(): List<TabNavigationItem> {
    return listOf(
        TabNavigationItem(tabLabel = "About", index = 0, tab = {}),
        TabNavigationItem(tabLabel = "Facilities", index = 1, tab = {}),
        TabNavigationItem(tabLabel = "Home Rules", index = 2, tab = {})
    )
}

@Composable
private fun FacilitiesGroupItem(
    facilitiesGroup: FacilitiesGroup,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = facilitiesGroup.groupTitle,
            options = TextOptions(
                size = TextSize.Large,
                weight = TextWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        VerticalGrid(columns = 2) {
            facilitiesGroup.facilities.forEach { facility ->
                FacilityItem(
                    modifier = Modifier.padding(vertical = 8.dp),
                    facility = facility
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(thickness = 0.5.dp)
    }
}

@Composable
private fun HighlightedFacilities(
    facilitiesGroups: List<FacilitiesGroup>,
    onViewAllFacilitiesClick: () -> Unit
) {
    val shuffledFacilities = remember {
        facilitiesGroups
            .flatMap { it.facilities }
            .shuffled()
            .take(7)
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        VerticalGrid(columns = 2) {
            shuffledFacilities.forEach { facility ->
                FacilityItem(
                    modifier = Modifier.padding(vertical = 8.dp),
                    facility = facility
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onViewAllFacilitiesClick() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "View all facilities",
                options = TextOptions(weight = TextWeight.Bold),
                style = TextStyle(textDecoration = TextDecoration.Underline)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow",
                tint = HostelWorldColor.DarkGray.color,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun Toolbar(
    name: String,
    onNavigateBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 4.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onNavigateBack,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .testTag(TestTag.BackIcon)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = HostelWorldColor.DarkGray.color
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = name,
                modifier = Modifier.weight(5f),
                options = TextOptions(
                    size = TextSize.XLarge,
                    weight = TextWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                ),
                maxLines = 1
            )

            Spacer(modifier = Modifier.weight(1f))
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
private fun ImageGalleryRow(
    images: List<String>
) {
    val listState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TestTag.PropertyGalleryImage),
        state = listState,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        itemsIndexed(images) { index, image ->
            val roundedCorners = when (index) {
                0 -> RoundedCornerShape(
                    topStart = 16.dp,
                    bottomStart = 16.dp
                ) // First item
                images.size - 1 -> RoundedCornerShape(
                    topEnd = 16.dp,
                    bottomEnd = 16.dp
                ) // Last item
                else -> RoundedCornerShape(0.dp) // No rounding for middle items
            }

            AsyncImage(
                model = image,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
                    .clip(roundedCorners),
                contentScale = ContentScale.FillBounds,
                contentDescription = "Hostel Image",
                placeholder = painterResource(R.drawable.placeholder)
            )
        }
    }
}

@Composable
fun HostelInfoCard(
    type: String,
    name: String,
    overallRating: Float,
    numberOfRatings: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Section (Hostel name and location)
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = type,
                options = TextOptions(
                    size = TextSize.Small
                )
            )

            Text(
                text = name,
                options = TextOptions(
                    weight = TextWeight.Bold,
                    size = TextSize.XLarge
                )
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Map,
                    contentDescription = "Location",
                    tint = HostelWorldColor.DarkGray.color,
                    modifier = Modifier.size(16.dp)
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "Dublin",
                    options = TextOptions(
                        size = TextSize.Small
                    )
                )
            }
        }

        // Right Section (Rating and Reviews)
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                StarIcon()

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = overallRating.toString(),
                    options = TextOptions(
                        weight = TextWeight.Bold,
                        size = TextSize.XLarge
                    )
                )
            }

            Text(
                text = "$numberOfRatings reviews",
                options = TextOptions(
                    size = TextSize.Small
                ),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Composable
private fun StarIcon() {
    Icon(
        imageVector = Icons.Filled.Star,
        contentDescription = "Rating",
        tint = HostelWorldColor.Yellow.color,
        modifier = Modifier.size(20.dp)
    )
}

@Composable
fun FacilityItem(
    modifier: Modifier,
    facility: Facility
) {
    val facilityEnum = translateToFacility(facility.id)
    val facilityIcon = getFacilityIcon(facilityEnum)

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
                .size(40.dp)
                .background(
                    color = HostelWorldColor.FacilityIconBackgroundColor.color,
                    shape = CircleShape
                )
        ) {
            Icon(
                imageVector = facilityIcon,
                contentDescription = "",
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.Center),
                tint = HostelWorldColor.FacilityIconColor.color
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = facility.name,
            options = TextOptions(
                size = TextSize.Small
            )
        )
    }
}

@Composable
fun AboutSection(
    description: String,
    onReadMoreClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "About",
            options = TextOptions(
                weight = TextWeight.Bold,
                size = TextSize.XLarge
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = description,
            maxLines = 4,
            options = TextOptions(
                overflow = TextOverflow.Ellipsis
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onReadMoreClick() },
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Read more",
                options = TextOptions(
                    weight = TextWeight.Bold
                ),
                style = TextStyle(
                    textDecoration = TextDecoration.Underline
                )
            )

            Spacer(modifier = Modifier.width(4.dp))

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Arrow",
                tint = HostelWorldColor.DarkGray.color,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
fun ReviewItem(label: String, score: Float) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            options = TextOptions(
                size = TextSize.Small
            )
        )

        LinearProgressIndicator(
            progress = { score / 10f },
            modifier = Modifier
                .weight(0.75f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = HostelWorldColor.Yellow.color,
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = String.format("%.1f", score),
            options = TextOptions(
                weight = TextWeight.Bold
            )
        )
    }
}

@Composable
fun ReviewsSection(
    overallRating: Float,
    descriptionAndNumberOfRatings: String,
    ratingBreakdown: RatingBreakdown
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .testTag(TestTag.PropertyDetailsReviewsBreakdown)
    ) {
        Text(
            text = "Reviews",
            options = TextOptions(
                weight = TextWeight.Bold,
                size = TextSize.XLarge
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StarIcon()

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = overallRating.toString(),
                options = TextOptions(
                    weight = TextWeight.Bold,
                    size = TextSize.XLarge
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = descriptionAndNumberOfRatings
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Review Details Section
        val reviews = listOf(
            "Value for money" to ratingBreakdown.value,
            "Security" to ratingBreakdown.security,
            "Atmosphere" to ratingBreakdown.atmosphere,
            "Cleanliness" to ratingBreakdown.clean,
            "Staff" to ratingBreakdown.staff,
            "Location" to ratingBreakdown.location,
            "Facilities" to ratingBreakdown.facilities
        )

        reviews.forEach { (label, score) ->
            ReviewItem(label = label, score = score)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun MapView(
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(vertical = 16.dp)
            .clickable { onClick() },
        painter = painterResource(R.drawable.default_map),
        contentScale = ContentScale.Crop,
        contentDescription = "Map"
    )
}

@Composable
fun LocationSection(
    address: String,
    onAddressClick: () -> Unit,
    onContentCopyClick: (address: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .testTag(TestTag.PropertyDetailsLocationSection),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Location",
            options = TextOptions(
                weight = TextWeight.Bold,
                size = TextSize.XLarge
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.PinDrop,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = HostelWorldColor.DarkGray.color
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = address,
                modifier = Modifier
                    .weight(1f)
                    .clickable { onAddressClick() },
                options = TextOptions(
                    size = TextSize.Small
                )
            )

            Icon(
                imageVector = Icons.Default.ContentCopy,
                contentDescription = null,
                modifier = Modifier
                    .size(16.dp)
                    .clickable { onContentCopyClick(address) },
                tint = HostelWorldColor.MediumGray.color
            )
        }
    }
}

@Composable
fun ChooseRoomBottomBar(
    modifier: Modifier,
    startingPrice: String,
    onChooseRoomClick: () -> Unit
) {
    Row(
        modifier = modifier
            .background(HostelWorldColor.White.color)
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .shadow(1.dp, shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .fillMaxWidth()
            .padding(24.dp)
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "From",
                options = TextOptions(
                    size = TextSize.Small
                )
            )

            Text(
                text = startingPrice,
                options = TextOptions(
                    size = TextSize.XLarge,
                    weight = TextWeight.Bold
                )
            )
        }

        Button(
            onClick = { onChooseRoomClick() },
            modifier = Modifier.padding(start = 8.dp),
            text = "Choose Room"
        )
    }
}

fun startGoogleMaps(
    context: Context,
    latitude: Double,
    longitude: Double
) {
    val strUri = "http://maps.google.com/maps?q=loc:$latitude,$longitude"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(strUri))
    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity")
    context.startActivity(intent)
}

fun copy(context: Context, text: String) {
    val clipboard: ClipboardManager =
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

    val clip = ClipData.newPlainText("HostelWorld", text)
    clipboard.setPrimaryClip(clip)
}