package ayman.issa.hostelworld.design_system.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Elevator
import androidx.compose.material.icons.filled.LocalParking
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import ayman.issa.hostelworld.common.util.FacilitiesEnum

fun getFacilityIcon(facility: FacilitiesEnum): ImageVector {
    return when (facility) {
        FacilitiesEnum.ADAPTORS -> Icons.Filled.Devices
        FacilitiesEnum.AIRCONDITIONING -> Icons.Filled.Air
        FacilitiesEnum.AIRPORTTRANSFERS -> Icons.Filled.AirportShuttle
        FacilitiesEnum.ATM -> Icons.Filled.Money
        FacilitiesEnum.BAR -> Icons.Filled.LocalBar
        FacilitiesEnum.BBQ -> Icons.Filled.OutdoorGrill
        FacilitiesEnum.BICYCLEHIRE -> Icons.Filled.PedalBike
        FacilitiesEnum.BICYCLEPARKING -> Icons.Filled.LocalParking
        FacilitiesEnum.BOARDGAMES -> Icons.Filled.Gamepad
        FacilitiesEnum.BOOKEXCHANGE -> Icons.Filled.LibraryBooks
        FacilitiesEnum.BREAKFASTINCLUDED -> Icons.Filled.FreeBreakfast
        FacilitiesEnum.BREAKFASTNOTINCLUDED -> Icons.Filled.BreakfastDining
        FacilitiesEnum.BUSINESSCENTRE -> Icons.Filled.Business
        FacilitiesEnum.CABLETV -> Icons.Filled.Tv
        FacilitiesEnum.CAFE -> Icons.Filled.LocalCafe
        FacilitiesEnum.CARDPHONES -> Icons.Filled.Phone
        FacilitiesEnum.CEILINGFAN -> Icons.Filled.AcUnit
        FacilitiesEnum.COMMONROOM -> Icons.Filled.Group
        FacilitiesEnum.COOKER -> Icons.Filled.Fastfood
        FacilitiesEnum.CURRENCYEXCHANGE -> Icons.Filled.Money
        FacilitiesEnum.DISHWASHER -> Icons.Filled.Wash
        FacilitiesEnum.DRYER -> Icons.Filled.DryCleaning
        FacilitiesEnum.DVDS -> Icons.Filled.Movie
        FacilitiesEnum.ELEVATOR -> Icons.Filled.Elevator
        FacilitiesEnum.EXPRESSCHECKINOUT -> Icons.Filled.Fastfood
        FacilitiesEnum.FAXSERVICE -> Icons.Filled.Fax
        FacilitiesEnum.FLEXIBLENRR -> Icons.Filled.Refresh
        FacilitiesEnum.FREEAIRPORTTRANSFERS -> Icons.Filled.AirportShuttle
        FacilitiesEnum.FREECITYMAPS -> Icons.Filled.Map
        FacilitiesEnum.FREECITYTOUR -> Icons.Filled.Explore
        FacilitiesEnum.FREEINTERNETACCESS -> Icons.Filled.Wifi
        FacilitiesEnum.FREEPARKING -> Icons.Filled.LocalParking
        FacilitiesEnum.FREEWIFI -> Icons.Filled.Wifi
        FacilitiesEnum.FRIDGEFREEZER -> Icons.Filled.Kitchen
        FacilitiesEnum.FUSBALL -> Icons.Filled.SportsSoccer
        FacilitiesEnum.GAMESROOM -> Icons.Filled.Games
        FacilitiesEnum.HAIRDRYERS -> Icons.Filled.Dry
        FacilitiesEnum.HAIRDRYERSFORHIRE -> Icons.Filled.Dry
        FacilitiesEnum.HOTSHOWERS -> Icons.Filled.Shower
        FacilitiesEnum.HOUSEKEEPING -> Icons.Filled.CleaningServices
        FacilitiesEnum.INTERNETACCESS -> Icons.Filled.Wifi
        FacilitiesEnum.IRONIRONINGBOARD -> Icons.Filled.Iron
        FacilitiesEnum.JOBSBOARD -> Icons.Filled.Work
        FacilitiesEnum.KEYCARDACCESS -> Icons.Filled.Lock
        FacilitiesEnum.KITCHEN -> Icons.Filled.Kitchen
        FacilitiesEnum.LATECHECKOUT -> Icons.Filled.Schedule
        FacilitiesEnum.LAUNDRYFACILITIES -> Icons.Filled.LocalLaundryService
        FacilitiesEnum.LINENINCLUDED -> Icons.Filled.Bed
        FacilitiesEnum.LOCKERS -> Icons.Filled.Lock
        FacilitiesEnum.LUGGAGESTORAGE -> Icons.Filled.Luggage
        FacilitiesEnum.MEALSAVAILABLE -> Icons.Filled.Restaurant
        FacilitiesEnum.MEETINGROOM -> Icons.Filled.Business
        FacilitiesEnum.MICROWAVE -> Icons.Filled.Microwave
        FacilitiesEnum.MINIBAR -> Icons.Filled.LocalBar
        FacilitiesEnum.MINISUPERMARKET -> Icons.Filled.ShoppingCart
        FacilitiesEnum.OUTDOORTERRACE -> Icons.Filled.Park
        FacilitiesEnum.PARKING -> Icons.Filled.LocalParking
        FacilitiesEnum.PLAYSTATION -> Icons.Filled.Gamepad
        FacilitiesEnum.POOLTABLE -> Icons.Filled.SportsTennis
        FacilitiesEnum.POSTALSERVICE -> Icons.Filled.LocalPostOffice
        FacilitiesEnum.READINGLIGHT -> Icons.Filled.Book
        FacilitiesEnum.RECEPTIONLIMITEDHOURS -> Icons.Filled.RecentActors
        FacilitiesEnum.RESTAURANT -> Icons.Filled.Restaurant
        FacilitiesEnum.SAFEDEPOSITBOX -> Icons.Filled.Lock
        FacilitiesEnum.SANITISATIONBADGE -> Icons.Filled.CleaningServices
        FacilitiesEnum.SHUTTLEBUS -> Icons.Filled.BusAlert
        FacilitiesEnum.TEACOFFEEMAKINGFACILITIES -> Icons.Filled.CoffeeMaker
        FacilitiesEnum.TOURSTRAVELDESK -> Icons.Filled.Public
        FacilitiesEnum.TOWELSFORHIRE -> Icons.Filled.Face
        FacilitiesEnum.TOWELSINCLUDED -> Icons.Filled.Shower
        FacilitiesEnum.TOWELSNOTINCLUDED -> Icons.Filled.Shower
        FacilitiesEnum.UTENSILS -> Icons.Filled.Kitchen
        FacilitiesEnum.VENDINGMACHINES -> Icons.Filled.LocalConvenienceStore
        FacilitiesEnum.WAKEUPCALLS -> Icons.Filled.Alarm
        FacilitiesEnum.WASHINGMACHINE -> Icons.Filled.LocalLaundryService
        FacilitiesEnum.WHEELCHAIRACCESSIBLE -> Icons.Filled.Accessibility
        FacilitiesEnum.WIFI -> Icons.Filled.Wifi
        FacilitiesEnum.TWENTYFOUR_HOUR_RECEPTION -> Icons.Filled.HourglassTop
        FacilitiesEnum.TWENTYFOUR_HOUR_SECURITY -> Icons.Filled.Security
        FacilitiesEnum.COTSAVAILABLE -> Icons.Filled.BedroomBaby
    }
}