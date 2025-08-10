# MAD Internship - Android Application Collection 📱

This repository contains a comprehensive collection of Android applications developed during my Mobile Application Development (MAD) Internship. Each project demonstrates different aspects of Android development, from basic UI concepts to advanced features like Firebase integration, camera functionality, and multimedia handling.

## 📋 Project Overview

This workspace contains **41 Android applications** that showcase various Android development concepts and technologies:

### 🎯 Individual Applications

1. **[Academica](./Academica/)** - Educational management application for students and academic institutions
2. **[AudioPlayer](./AudioPlayer/)** - Simple audio playback application with play, pause, and stop controls
3. **[AudioRecorder](./AudioRecorder/)** - Audio recording app with list management and playback functionality
4. **[AvnishsLoginApp](./AvnishsLoginApp/)** - Firebase-authenticated login application with Google Sign-In
5. **[BlogApp](./BlogApp/)** - Full-featured blog application with Firebase backend and user authentication
6. **[Clock](./Clock/)** - Digital clock application with alarm functionality and notification support
7. **[DiaryApp](./DiaryApp/)** - Personal diary application with SQLite database for local storage
8. **[EcommerceApp](./EcommerceApp/)** - E-commerce platform with admin/customer portals and Firebase integration
9. **[ExpenseTracker](./ExpenseTracker/)** - Personal expense tracking application with income/expense categorization
10. **[HealthTrackerApp](./HealthTrackerApp/)** - Health monitoring app with bottom navigation and fragment-based UI
11. **[InstagramLoginClone](./InstagramLoginClone/)** - Instagram-style login interface clone with modern UI design
12. **[JanAwaz](./JanAwaz/)** - Citizen awareness app for reporting public service issues and community problems
13. **[LocationDisplay](./LocationDisplay/)** - GPS location tracking and mapping application with real-time coordinates
14. **[LoginApp](./LoginApp/)** - Basic authentication system with SQLite database and session management
15. **[MusicPlayer](./MusicPlayer/)** - Genre-based music player with multiple music categories
16. **[NewsApp](./NewsApp/)** - News reading application with article management and notifications
17. **[ProductCard](./ProductCard/)** - Product display card component with modern e-commerce design
18. **[ProfileCard](./ProfileCard/)** - User profile card interface with customizable user information display
19. **[SimpleCameraApp](./SimpleCameraApp/)** - Camera application with photo capture and preview functionality
20. **[SimpleQRScanner](./SimpleQRScanner/)** - QR code scanner using ML Kit and CameraX
21. **[StatsApp](./StatsApp/)** - Statistics and analytics application with data visualization features
22. **[TextEditor](./TextEditor/)** - Simple text editor with file operations and sharing capabilities
23. **[ThemeSwitcher](./ThemeSwitcher/)** - Dark/Light theme switching demonstration
24. **[TicTacToe](./TicTacToe/)** - Classic tic-tac-toe game with score tracking and reset functionality
25. **[VideoPlayer](./VideoPlayer/)** - Video playback application with media controls
26. **[WeatherApp](./WeatherApp/)** - Weather information app with API integration
27. **[WhatsappClone](./WhatsappClone/)** - WhatsApp-like chat interface with recycler view

### 🎮 Multi-Project Application

**[InternshipApplication](./InternshipApplication/)** - A comprehensive hub application containing 14 embedded mini-projects accessible through button navigation:

28. **TempApp** - Simple App
29. **AuthApp** - Authentication system with login/register
30. **CheckboxRadioApp** - UI components demonstration (checkboxes, radio buttons)
31. **PizzaOrderCustomizer** - Pizza ordering system with customization options
32. **UTSApp** - Unreserved Ticketing System (Indian Railways) application
33. **CalculatorApp** - Basic calculator with arithmetic operations
34. **TempConversionApp** - Temperature unit conversion utility
35. **CoinFlipApp** - Random coin flip simulation
36. **DiceRollApp** - Random dice rolling application
37. **FlipkartClone** - E-commerce interface clone
38. **ShoppingList** - Basic shopping list manager
39. **CollegeList** - Educational institution listing
40. **NotesApp** - Note-taking application with CRUD operations
41. **AdvancedShoppingList** - Enhanced shopping list with context menus

## 🛠️ Technologies Used

- **Languages**: Java, XML
- **Database**: SQLite, Firebase Realtime Database, Firebase Firestore
- **Authentication**: Firebase Auth, Google Sign-In
- **UI Components**: RecyclerView, Fragments, Bottom Navigation, CardView
- **Camera & Media**: CameraX, Camera API, MediaPlayer, MediaRecorder
- **Machine Learning**: ML Kit (Barcode/QR Code scanning)
- **Networking**: HTTP requests, JSON parsing
- **Storage**: SharedPreferences, Internal/External storage
- **Design Patterns**: MVP, Observer, Adapter patterns

## 🔧 Development Environment

- **IDE**: Android Studio
- **Min SDK**: Varies by project (typically API 21+)
- **Target SDK**: API 33+
- **Build System**: Gradle
- **Version Control**: Git

## 📱 Features Demonstrated

### Core Android Concepts
- Activity lifecycle management
- Fragment-based navigation
- Intent-based communication
- Database operations (SQLite)
- File I/O operations
- Permissions handling

### UI/UX Design
- Material Design principles
- Custom layouts and themes
- RecyclerView with adapters
- Bottom navigation
- Context menus and dialogs

### Advanced Features
- Firebase integration (Auth, Firestore)
- Camera functionality
- Audio/Video playback
- QR code scanning
- API consumption
- Push notifications
- Theme switching

### Data Management
- SQLite database operations
- SharedPreferences for settings
- JSON data handling
- File storage and sharing

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21 or higher
- Java Development Kit (JDK) 8 or higher

### Installation
1. Clone this repository:
   ```bash
   git clone https://github.com/AvnishGameDev/MAD_Internship
   ```
2. Open Android Studio
3. Select "Open an existing Android Studio project"
4. Navigate to the desired project folder
5. Wait for Gradle sync to complete
6. Run the application on an emulator or physical device

### Firebase Setup (Required for some apps)
For applications using Firebase (BlogApp, EcommerceApp, AvnishsLoginApp):
1. Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Add your Android app to the Firebase project
3. Download the `google-services.json` file
4. Place it in the `app/` directory of the respective project
5. Enable required services (Authentication, Firestore, etc.)

## 📁 Project Structure

Each application follows standard Android project structure:
```
ProjectName/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/avnishgamedev/projectname/
│   │   │   ├── res/
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   ├── build.gradle
│   └── proguard-rules.pro
├── gradle/
├── build.gradle
├── settings.gradle
└── README.md
```

## 🎓 Learning Outcomes

This collection demonstrates proficiency in:
- Android application architecture
- User interface design and implementation
- Database integration and management
- API consumption and networking
- Multimedia handling
- Camera and sensor integration
- Authentication and security
- Testing and debugging
- Version control with Git

## 📄 License

This project is developed for educational purposes as part of the MAD Internship program.

## 👨‍💻 Developer

**Avnish Kirnalli**
- GitHub: [@avnishgamedev](https://github.com/avnishgamedev)
- Email: [avnishgamedev@gmail.com]

## 🤝 Contributing

This repository is primarily for educational and portfolio purposes. However, suggestions and improvements are welcome!

---

**Note**: Some applications require internet connectivity, camera permissions, or Firebase configuration to function properly. Please refer to individual project READMEs for specific setup instructions.
