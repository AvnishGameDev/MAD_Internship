# HealthTrackerApp 🏥

A comprehensive health monitoring application with fragment-based navigation, featuring health data tracking, logging, and user profile management.

## 📱 Features

- **Health Dashboard**: Overview of health metrics and status
- **Activity Logging**: Track daily health activities and measurements
- **Profile Management**: User profile with health information
- **Fragment Navigation**: Bottom navigation with three main sections
- **Health Metrics**: Monitor various health parameters
- **Data Visualization**: (Potential) Charts and graphs for health trends
- **Local Storage**: Persistent health data storage

## 🛠️ Technologies Used

- **Language**: Java
- **Architecture**: Fragment-based with Bottom Navigation
- **UI**: Fragments, BottomNavigationView, Material Design
- **Navigation**: Fragment transactions and management
- **Storage**: Local data persistence (SharedPreferences/SQLite)
- **Min SDK**: API 21+

## 📁 Project Structure

```
HealthTrackerApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/healthtrackerapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── HomeFragment.java
│   │   │   ├── LogFragment.java
│   │   │   ├── ProfileFragment.java
│   │   │   └── HealthData.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── fragment_home.xml
│   │   │   │   ├── fragment_log.xml
│   │   │   │   └── fragment_profile.xml
│   │   │   ├── menu/
│   │   │   │   └── bottom_nav_menu.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Fragment Container**: Host for all health tracking fragments
- **Bottom Navigation**: Handle navigation between different sections
- **Fragment Management**: Load and switch between fragments
- **Lifecycle Management**: Proper fragment lifecycle handling

#### HomeFragment.java
- **Health Overview**: Dashboard showing current health status
- **Quick Actions**: Fast access to common health functions
- **Health Summary**: Display key health metrics
- **Data Visualization**: Charts and progress indicators

#### LogFragment.java
- **Health Logging**: Input forms for health data entry
- **Activity Tracking**: Log daily health activities
- **Measurement Entry**: Record health measurements
- **History View**: View past health entries

#### ProfileFragment.java
- **User Profile**: Personal information and health profile
- **Settings**: App preferences and configuration
- **Health Goals**: Set and track health objectives
- **Account Management**: User account information

### Navigation Structure

#### Bottom Navigation Menu
1. **Home (🏠)**: Health dashboard and overview
2. **Log (📝)**: Health data entry and tracking
3. **Profile (👤)**: User profile and settings

## 🎯 Learning Objectives

- Fragment-based Android application architecture
- Bottom navigation implementation
- Fragment lifecycle management
- Inter-fragment communication
- Health data modeling and storage
- Material Design for health apps
- User interface design for healthcare
- Data persistence in healthcare applications

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. Navigate between sections using bottom navigation
4. Explore different fragments:
   - **Home**: View health overview
   - **Log**: Add health data entries
   - **Profile**: Manage user information

## 📋 No Special Permissions Required

Basic version uses only local storage, no additional permissions needed.

## 🔄 App Flow

1. **Launch**: Open to Home fragment (dashboard)
2. **Navigation**: Use bottom navigation to switch sections
3. **Log Health Data**: Switch to Log fragment to enter health information
4. **View Profile**: Access Profile fragment for user settings
5. **Data Persistence**: All health data saved locally

## 🏥 Health Tracking Features

### Potential Health Metrics
- **Vital Signs**: Blood pressure, heart rate, temperature
- **Physical Activity**: Steps, exercise duration, calories
- **Weight Management**: Weight tracking and BMI calculation
- **Sleep Tracking**: Sleep duration and quality
- **Medication**: Medication reminders and tracking
- **Symptoms**: Log health symptoms and conditions

### Data Types
- **Measurements**: Numerical health values
- **Activities**: Exercise and physical activities
- **Medications**: Drug schedules and adherence
- **Appointments**: Healthcare appointments
- **Goals**: Health and fitness objectives

## 🎨 UI Components

- **BottomNavigationView**: Main navigation component
- **Fragments**: Three main content sections
- **CardViews**: Health metric display cards
- **Forms**: Health data input forms
- **Charts**: (Potential) Health trend visualization
- **Progress Indicators**: Goal tracking displays

## 🔄 Fragment Communication

### Data Sharing Between Fragments
- **Shared ViewModel**: (Potential) Common data model
- **Interface Callbacks**: Fragment-to-activity communication
- **Bundle Arguments**: Pass data between fragments
- **Local Storage**: Persistent data access across fragments

## 🔄 Future Enhancements

- **Health Charts**: Visual health trend analysis
- **Medication Reminders**: Push notifications for medications
- **Doctor Integration**: Share health data with healthcare providers
- **Wearable Integration**: Sync with fitness trackers
- **Health Goals**: Set and track health objectives
- **Export Data**: Generate health reports
- **Cloud Sync**: Backup health data to cloud
- **Emergency Contacts**: Quick access to emergency information
- **Health Insights**: AI-powered health recommendations
- **Appointment Scheduling**: Integrate with calendar

## 🔒 Health Data Privacy

- **Local Storage**: Health data stored locally on device
- **Data Encryption**: (Potential) Encrypt sensitive health information
- **Access Control**: Secure access to health data
- **Privacy Settings**: User control over data sharing

## 🐛 Common Issues

- **Fragment Lifecycle**: Ensure proper fragment lifecycle management
- **Navigation State**: Maintain navigation state across app restarts
- **Data Persistence**: Verify health data is properly saved
- **Memory Management**: Handle fragment memory efficiently

## 📊 Health Dashboard

The Home fragment serves as a comprehensive health dashboard featuring:
- **Current Health Status**: Overview of recent health metrics
- **Quick Stats**: Key health numbers at a glance
- **Recent Activities**: Latest health activities and logs
- **Health Trends**: (Potential) Visual progress indicators
- **Quick Actions**: Fast access to common health functions

## 🎯 Target Users

- **Health-conscious individuals**: Track personal health metrics
- **Chronic condition patients**: Monitor ongoing health conditions
- **Fitness enthusiasts**: Track exercise and physical activity
- **Elderly users**: Monitor vital signs and medications
- **Healthcare professionals**: (Potential) Patient monitoring tool

---

**Part of MAD Internship - Android Development Portfolio**
