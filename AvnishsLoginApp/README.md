# AvnishsLoginApp 🔐

A Firebase-powered authentication application featuring Google Sign-In integration and user dashboard functionality.

## 📱 Features

- **Google Sign-In**: Seamless authentication using Google accounts
- **Firebase Authentication**: Secure user authentication backend
- **User Dashboard**: Post-login user information display
- **Session Management**: Persistent login state handling
- **Firestore Integration**: User data storage and retrieval
- **Real-time Authentication**: Live authentication state monitoring
- **Sign-out Functionality**: Secure logout with session cleanup

## 🛠️ Technologies Used

- **Language**: Java
- **Authentication**: Firebase Auth, Google Sign-In API
- **Database**: Firebase Firestore
- **UI**: XML Layouts, CardView
- **Backend**: Firebase Services
- **Min SDK**: API 21+

## 📁 Project Structure

```
AvnishsLoginApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/avnishsloginapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── DashboardActivity.java
│   │   │   └── RegisterActivity.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_dashboard.xml
│   │   │   │   └── activity_register.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Google Sign-In Configuration**: GoogleSignInClient setup
- **Authentication Flow**: Handle sign-in requests and responses
- **Firebase Integration**: Link Google account with Firebase Auth
- **User State Management**: Check and maintain authentication state

#### DashboardActivity.java
- **User Profile Display**: Show authenticated user information
- **Firebase User Data**: Retrieve and display user details from Firestore
- **Session Management**: Handle user session and logout functionality
- **UI Updates**: Real-time user interface updates based on auth state

#### RegisterActivity.java
- **User Registration**: Additional user registration flow
- **Profile Completion**: Extended user profile setup
- **Data Validation**: Input validation and error handling

### Firebase Configuration

#### Authentication Setup
- Google Sign-In provider configuration
- Firebase Auth instance management
- Authentication state listeners
- Sign-in result handling

#### Firestore Integration
- User document creation and updates
- Real-time data synchronization
- Error handling for database operations

## 🎯 Learning Objectives

- Firebase Authentication implementation
- Google Sign-In API integration
- Firestore database operations
- Authentication state management
- OAuth 2.0 flow understanding
- Secure user session handling
- Firebase SDK configuration

## 🚀 Getting Started

### Prerequisites
1. Create a Firebase project at [Firebase Console](https://console.firebase.google.com/)
2. Enable Authentication with Google Sign-In provider
3. Add your Android app to Firebase project
4. Download `google-services.json` and place in `app/` directory

### Setup Steps
1. Open project in Android Studio
2. Ensure `google-services.json` is in place
3. Configure Google Sign-In in Firebase Console
4. Add your SHA-1 fingerprint to Firebase project
5. Run the application
6. Test authentication flow

## 📋 Required Dependencies

```gradle
implementation 'com.google.firebase:firebase-auth'
implementation 'com.google.firebase:firebase-firestore'
implementation 'com.google.android.gms:play-services-auth'
```

## 🔄 Authentication Flow

1. **Initial Check**: Verify if user is already signed in
2. **Google Sign-In**: Present Google account selection
3. **Firebase Auth**: Link Google account with Firebase
4. **User Creation**: Create/update user document in Firestore
5. **Dashboard Navigation**: Redirect to user dashboard
6. **Session Persistence**: Maintain login state across app restarts

## 🔒 Security Features

- **Token Validation**: Verify Google ID tokens
- **Secure Communication**: HTTPS/SSL for all requests
- **Session Management**: Automatic token refresh
- **Sign-out Security**: Complete session cleanup on logout

## 🐛 Common Issues

- **SHA-1 Fingerprint**: Ensure correct SHA-1 is added to Firebase
- **google-services.json**: Verify file is in correct location
- **Network Issues**: Check internet connectivity for authentication
- **Package Name**: Ensure Firebase project package name matches app

## 🎨 UI Components

- **Sign-In Button**: Google-styled authentication button
- **User Avatar**: Display user profile picture
- **User Information**: Name, email, and profile details
- **Logout Button**: Secure session termination

## 🔄 Future Enhancements

- **Email/Password Authentication**: Alternative login methods
- **Profile Editing**: Allow users to update profile information
- **Account Linking**: Link multiple authentication providers
- **Offline Support**: Cached authentication state
- **Two-Factor Authentication**: Enhanced security measures

---

**Part of MAD Internship - Android Development Portfolio**
