package com.example.jeevan_rakshak.navigation

sealed class Screen(val route: String) {
    // Auth Screens
    object Splash : Screen("splash")
    object SignIn : Screen("sign_in")
    object SignUp : Screen("sign_up")

    // Main Screen
    object Home : Screen("home")

    // ============================================
    // PATIENT SCREENS
    // ============================================
    object BookingHistory : Screen("booking_history")
    object MyOrders : Screen("my_orders")
    object Address : Screen("address")
    object MedicalRecords : Screen("medical_records")

    // ============================================
    // DOCTOR SCREENS
    // ============================================
    object PendingAppointments : Screen("pending_appointments")
    object DoctorSchedule : Screen("doctor_schedule")
    object MyPatients : Screen("my_patients")
    object ConsultationHistory : Screen("consultation_history")

    // ============================================
    // AMBULANCE SCREENS
    // ============================================
    object EmergencyVisit : Screen("emergency_visit")
    object UpdateLocation : Screen("update_location")
    object TripHistory : Screen("trip_history")
    object VehicleStatus : Screen("vehicle_status")

    // ============================================
    // COMMON SCREENS (All Users)
    // ============================================
    object MyContacts : Screen("my_contacts")
    object Account : Screen("account")
    object Notifications : Screen("notifications")
    object Cart : Screen("cart")
    object HelpSupport : Screen("help_support")
    object AboutApp : Screen("about_app")
    object Logout : Screen("logout")

    // ============================================
    // BOTTOM NAVIGATION SCREENS
    // ============================================
    object Ambulance : Screen("ambulance")
    object Pharmacy : Screen("pharmacy")
    object Hospital : Screen("hospital")
    object Profile : Screen("profile")
}