# Jeevan_Rakshak
# 📱 Jeevan_Rakshak – One-Tap Emergency Assistance & Generic Medicine Platform



<p align="center">
  <b>Your Life Saver – Emergency SOS & Affordable Healthcare Platform for India 🇮🇳</b>
</p>
<p align="center">
  <img src="https://img.shields.io/badge/Platform-Android-green" />
  <img src="https://img.shields.io/badge/Language-Kotlin-blue" />
  <img src="https://img.shields.io/badge/UI-Jetpack%20Compose-orange" />
  <img src="https://img.shields.io/badge/Backend-Firebase-yellow" />
  <img src="https://img.shields.io/badge/Cloud-Cloudinary-purple" />
  <img src="https://img.shields.io/badge/Architecture-MVVM-red" />
</p>

<p align="center">
  <a href="#-problem-statement">Problem</a> •
  <a href="#-proposed-solution">Solution</a> •
  <a href="#-technology-stack">Tech Stack</a> •
  <a href="#-core-modules">Modules</a> •
  <a href="#-system-architecture">Architecture</a> •
  <a href="#-screens--features">Screens</a> •
  <a href="#-installation">Installation</a> •
  <a href="#-roadmap">Roadmap</a> •
  <a href="#-team">Team</a>
</p>

<p align="center">
  <img width="500" height="500" alt="logoStatic" src="https://github.com/user-attachments/assets/09ba69b3-c5c5-4922-8465-bc9b4fc567ad" alt="Jeevan Rakshak Logo"/>
</p>

# 📸 App Screenshots

---

## 🔐 Authentication & Role Selection

<p align="center">
  <img src="https://github.com/user-attachments/assets/58025272-2e78-4da0-a964-861be6ef71ec" width="250"/>
  <img src="https://github.com/user-attachments/assets/1cdef606-0659-4ff6-b46b-eac98552249b" width="250"/>
  <img src="https://github.com/user-attachments/assets/830fb834-d96e-4283-9705-b3607fc47f61" width="250"/>
</p>

<p align="center">
  <img src="https://github.com/user-attachments/assets/554e670a-ae42-4c3c-b615-1fd3110a1ac0" width="250"/>
</p>

---

## 🏠 Home Dashboard & Core Features

<p align="center">
  <img src="https://github.com/user-attachments/assets/b5debf41-422a-4f79-9c12-759d6923db50" width="250"/>
  <img src="https://github.com/user-attachments/assets/58363cbd-a761-453e-b94d-05228ec5ccef" width="250"/>
  <img src="https://github.com/user-attachments/assets/1f311f6c-dc81-418a-a8cf-4d155f553924" width="250"/>
</p>

---

## 🚑 Emergency & Ambulance Module

<p align="center">
  <img src="https://github.com/user-attachments/assets/723ca4d5-2305-4a39-bd8f-a0e526df5a8e" width="250"/>
  <img src="https://github.com/user-attachments/assets/22f15687-3cc0-4778-be68-22ccb56e4689" width="250"/>
  <img src="https://github.com/user-attachments/assets/8abee95c-b089-4cf0-a097-dafa967bc1c5" width="250"/>
</p>

---

<p align="center">
  ❤️ Built with Kotlin, Jetpack Compose & Firebase
</p>










---

# 🚨 Problem Statement

In India, medical and personal emergencies often suffer from:

- ⏳ Delayed response during critical situations  
- 📍 Difficulty in sharing real-time location  
- 🚑 Lack of unified emergency assistance platform  
- 💊 High cost of branded medicines  
- ❌ No single platform combining SOS + Ambulance + Generic Medicine  

During emergencies, individuals cannot quickly contact multiple people, share GPS location, or access nearby medical services instantly.

There is a strong need for a **unified, reliable, and easy-to-use mobile application** that provides:

- Instant emergency alerts  
- Location-based ambulance & medical assistance  
- Affordable generic medicine access  

---

# 💡 Proposed Solution

**Jeevan Rakshak** is a modern Android application designed to provide:

## 🆘 One-Tap Emergency SOS
- Instantly notify emergency contacts  
- Share live GPS location  
- Quick access to emergency numbers (108, 112)  

## 🚑 Ambulance & Medical Assistance
- Find nearby ambulance services  
- Display hospitals using Google Maps  
- Real-time location tracking  

## 💊 Generic Medicine Marketplace
- Search affordable generic medicines  
- View details and pricing  
- Add to cart & order medicines  
- Reduce healthcare costs  

## 👥 Multi-Role System
- 👤 Patient  
- 🩺 Doctor  
- 🚑 Ambulance Driver  

Each role has customized dashboard and features.

---

# 🛠 Technology Stack

## 🔹 Frontend (Android)

- **Kotlin**
- **Jetpack Compose**
- Material Design 3
- Android Jetpack (Navigation, ViewModel, Lifecycle)

## 🔹 Backend & Cloud

- Firebase Authentication (Email/Phone Login)
- Firebase Realtime Database
- Firebase Firestore
- Firebase Cloud Functions
- Firebase Cloud Messaging (FCM)

## 🔹 Location & Maps

- Google Maps SDK
- Fused Location Provider
- Google Places API

## 🔹 External Data Sources

- myUpchar API  
- openFDA  
- DrugBank  
- Custom emergency datasets  

---

# 🧩 Core Modules

## 1️⃣ User Authentication Module
- Email/Phone login & registration
- Role-based access (Patient / Doctor / Ambulance)
- Secure session handling
- Realtime user data storage

---

## 2️⃣ SOS Emergency Module
- One-tap SOS button
- Auto call & SMS with GPS location
- Emergency logging history
- Instant alert notifications

---

## 3️⃣ Emergency Contacts Module
- Add / Edit / Delete contacts
- Firebase storage
- Priority contact ordering

---

## 4️⃣ Ambulance & Medical Assistance Module
- Show nearby ambulances on map
- Quick-dial emergency numbers
- Trip tracking & history
- Real-time location updates

---

## 5️⃣ Generic Medicine Marketplace
- Medicine search
- Detailed medicine information
- Add to cart
- Place orders
- Order tracking

---

## 6️⃣ Doctor Consultation Module
- Manage appointments
- View patient history
- Accept / Reject requests
- Digital prescriptions

---

## 7️⃣ Notification Module
- SOS alerts
- Order updates
- Appointment reminders
- Emergency confirmations

---

# 🏗 System Architecture

```
Android App (Jetpack Compose)
        │
        ▼
ViewModel Layer (State Management)
        │
        ▼
Repository Layer (Data Handling)
        │
        ▼
Firebase Cloud Services
   ├── Authentication
   ├── Realtime Database
   ├── Firestore
   ├── Cloud Functions
   └── Cloud Messaging (FCM)
```

The app follows a **Client–Cloud Architecture**, eliminating the need for a dedicated backend server while ensuring scalability and high availability.

---

# 📱 Screens & Features

## 🔐 Authentication Screens
- Splash Screen
- Sign In (Role-based login)
- Sign Up (Validation + Terms)

## 👤 Patient Screens
- Home Dashboard
- Booking History
- My Orders
- Address Management
- Emergency Contacts

## 🩺 Doctor Screens
- Dashboard
- Pending Appointments
- Consultation History

## 🚑 Ambulance Screens
- Emergency Requests
- Update Location
- Trip History

## ⚙ Common Screens
- My Contacts
- Account Settings
- Notifications
- Cart
- Help & Support
- Logout

---

# 📦 Installation

### 1️⃣ Clone the Repository

```bash
git clone https://github.com/your-username/Jeevan_Rakshak.git
```

### 2️⃣ Open in Android Studio

- Open project
- Sync Gradle
- Add your `google-services.json`

### 3️⃣ Configure Firebase

- Enable Authentication (Email + Phone)
- Enable Realtime Database
- Enable Firestore
- Enable Cloud Messaging

### 4️⃣ Run the App 🚀

---

# 📂 Project Structure

```
com.example.jeevan_rakshak
│
├── navigation/
├── ui/
│   ├── screens/
│   ├── components/
│   └── theme/
│
├── viewmodels/
├── repository/
└── MainActivity.kt
```

---

# 🛣 Detailed Tech Roadmap

## 🛠 Phase 1 — Foundation
✔ Firebase Setup  
✔ Authentication  
✔ SOS Logic  
✔ Project Architecture  

## 🌍 Phase 2 — Emergency Module
✔ GPS + Maps  
✔ Ambulance Database  
✔ Nearby Hospitals  

## 💊 Phase 3 — Medicine Marketplace
✔ Medicine API Integration  
✔ Cart + Orders  
✔ Firestore Storage  

## 📦 Phase 4 — Notifications & Polish
✔ Push Notifications  
✔ UX Improvements  
✔ Testing & Debugging  

---

# 🎯 Conclusion

**Jeevan Rakshak** aims to provide a fast, reliable, and affordable healthcare assistance solution by integrating:

🆘 Emergency SOS  
🚑 Ambulance Support  
💊 Generic Medicine Marketplace  

All within one unified Android platform.

It is a scalable, real-world, and socially impactful solution tailored for India.

---<img width="500" height="500" alt="logoStatic" src="https://github.com/user-attachments/assets/09ba69b3-c5c5-4922-8465-bc9b4fc567ad" />


# 👨‍💻 Team

- **Monu Yaduwanshi**


---

# 📜 License

This project is developed for academic and social innovation purposes.  
Open for learning and enhancement.

---

<p align="center">
  ❤️ Built with Kotlin & Firebase for saving lives
</p>
