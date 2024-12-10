package ayman.issa.hostelworld.feature

import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import ayman.issa.hostelworld.MainActivity
import ayman.issa.hostelworld.MainApp
import ayman.issa.hostelworld.common.util.TestTag
import ayman.issa.hostelworld.fakeProperty1
import ayman.issa.hostelworld.fakeProperty2
import ayman.issa.hostelworld.fakeProperty3
import ayman.issa.hostelworld.remote.di.RemoteModule
import ayman.issa.hostelworld.ui.theme.HostelWorldAymanIssaTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(
    RemoteModule::class,
    ayman.issa.hostelworld.features.properties_list.data.di.RepositoryModule::class,
    ayman.issa.hostelworld.features.properties_list.data.di.UseCaseModule::class,
    ayman.issa.hostelworld.features.property_details.data.di.RepositoryModule::class,
    ayman.issa.hostelworld.features.property_details.data.di.UseCaseModule::class,
)
class PropertiesScreensTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    @ExperimentalAnimationApi
    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.activity.setContent {
            HostelWorldAymanIssaTheme {
                MainApp()
            }
        }
    }

    @Test
    fun testPropertiesList() {
        composeRule.onNode(hasTestTag(TestTag.PropertiesList)).assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(hasText(fakeProperty1.name))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(hasText(fakeProperty2.name))
            .assertIsDisplayed()
        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(hasText(fakeProperty3.name))
            .assertIsDisplayed()
    }

    @Test
    fun testNavigationToPropertyDetails() {
        val itemMatcher = hasText(fakeProperty1.name)
        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()


        composeRule.onNodeWithTag(TestTag.BackIcon).assertIsDisplayed()
    }

    @Test
    fun testNavigationToPropertyDetailsAndDisplayInfo() {
        val itemMatcher = hasText(fakeProperty1.name)

        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList)
            .performScrollToNode(hasTestTag(TestTag.PropertyGalleryImage))
            .onChildren()
            .filterToOne(hasTestTag(TestTag.PropertyGalleryImage))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList)
            .performScrollToNode(hasText(fakeProperty1.name))
            .onChildren()
            .filterToOne(hasText(fakeProperty1.name))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList)
            .performScrollToNode(hasText(fakeProperty1.overview))
            .onChildren()
            .filterToOne(hasText(fakeProperty1.overview))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList)
            .performScrollToNode(hasTestTag(TestTag.PropertyDetailsReviewsBreakdown))
            .onChildren()
            .filterToOne(hasTestTag(TestTag.PropertyDetailsReviewsBreakdown))
            .assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.PropertyDetailsList)
            .performScrollToNode(hasTestTag(TestTag.PropertyDetailsLocationSection))
            .assertIsDisplayed()

    }

    @Test
    fun testNavigationToPropertyDetailsAndGoBack() {
        val itemMatcher = hasText(fakeProperty1.name)

        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(itemMatcher)
            .onChildren()
            .filterToOne(itemMatcher)
            .performClick()

        composeRule.onNodeWithTag(TestTag.BackIcon).assertIsDisplayed()

        composeRule.onNodeWithTag(TestTag.BackIcon).performClick()

        composeRule.onNodeWithTag(TestTag.PropertiesList)
            .performScrollToNode(hasText(fakeProperty3.name))
            .assertIsDisplayed()
    }

}