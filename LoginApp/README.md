# LoginApp 🔑

A simple yet comprehensive authentication system with SQLite database integration, featuring user registration, login, and session management functionality.

## 📱 Features

- **User Registration**: Create new user accounts with validation
- **Secure Login**: Username/password authentication with hashed passwords
- **Session Management**: Persistent login state using SharedPreferences
- **User Dashboard**: Post-login user interface with personalized content
- **Local Database**: SQLite storage for user credentials
- **Password Security**: Password hashing for secure storage
- **Input Validation**: Form validation and error handling
- **Automatic Navigation**: Smart routing based on authentication state

## 🛠️ Technologies Used

- **Language**: Java
- **Database**: SQLite with custom helper class
- **Security**: Password hashing for secure storage
- **Storage**: SharedPreferences for session management
- **UI**: Material Design components
- **Validation**: Form input validation and error handling
- **Min SDK**: API 21+

## 📁 Project Structure

```
LoginApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/loginapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── LoginActivity.java
│   │   │   ├── RegisterActivity.java
│   │   │   ├── UserActivity.java
│   │   │   └── UserDBHelper.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_register.xml
│   │   │   │   └── activity_user.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Entry Point**: Check authentication status on app launch
- **Smart Navigation**: Route to login or user dashboard based on session
- **Session Validation**: Verify stored credentials against database
- **Quick Access**: Direct access for returning users

#### LoginActivity.java
- **Authentication Form**: Username and password input fields
- **Credential Validation**: Check against stored user data
- **Password Security**: Hash comparison for secure authentication
- **Error Handling**: Display appropriate error messages
- **Session Creation**: Save login state on successful authentication

#### RegisterActivity.java
- **User Registration**: New account creation form
- **Input Validation**: Ensure all required fields are completed
- **Duplicate Prevention**: Check for existing usernames
- **Password Security**: Hash passwords before database storage
- **Automatic Login**: Redirect to user dashboard after registration

#### UserActivity.java
- **User Dashboard**: Personalized post-login interface
- **User Information**: Display logged-in user details
- **Logout Functionality**: Clear session and return to login
- **Protected Content**: Content accessible only to authenticated users

#### UserDBHelper.java
- **Database Management**: SQLite database creation and operations
- **User CRUD Operations**: Create, read, update user records
- **Password Security**: Handle password hashing and verification
- **Data Integrity**: Ensure database consistency and validation

### Security Implementation

#### Password Hashing
```java
// Hash password before storage
String hashedPassword = String.valueOf(password.hashCode());
```

#### Session Management
```java
// Save login session
SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
prefs.edit().putString("username", username).apply();
```

## 🎯 Learning Objectives

- SQLite database integration and management
- User authentication and session handling
- Password security and hashing techniques
- SharedPreferences for data persistence
- Form validation and error handling
- Activity navigation and lifecycle management
- Secure local data storage practices

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. First-time users: Tap "Register" to create an account
4. Existing users: Enter credentials to log in
5. Explore the user dashboard after authentication
6. Use logout to clear session and return to login

## 📋 No Special Permissions Required

This app uses only local storage and doesn't require network or special permissions.

## 🔄 Authentication Flow

1. **App Launch**: Check for existing session in SharedPreferences
2. **Session Check**: Validate stored username against database
3. **Route Decision**: 
   - If valid session → UserActivity (Dashboard)
   - If no session → LoginActivity
4. **Login Process**:
   - Enter credentials
   - Validate against database
   - Create session on success
   - Navigate to dashboard
5. **Registration Process**:
   - Fill registration form
   - Validate input
   - Store in database with hashed password
   - Auto-login and navigate to dashboard

## 🔒 Security Features

### Password Protection
- **Password Hashing**: Passwords stored as hash values, not plain text
- **Hash Verification**: Compare hashed values during authentication
- **No Plain Text Storage**: Original passwords never stored

### Session Security
- **Session Validation**: Verify session against database
- **Automatic Logout**: Clear session on logout
- **Session Persistence**: Maintain login across app restarts

### Input Validation
- **Required Fields**: Ensure all necessary fields are completed
- **Username Uniqueness**: Prevent duplicate usernames
- **Error Messaging**: Clear feedback for validation failures

## 🎨 UI Components

- **Login Form**: Username and password input fields
- **Registration Form**: User details and account creation
- **Navigation Buttons**: Login/Register switching
- **User Dashboard**: Post-authentication interface
- **Logout Button**: Session termination control

## 🗄️ Database Schema

```sql
CREATE TABLE users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL,
    email TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🔄 Future Enhancements

- **Password Strength**: Enforce strong password requirements
- **Email Verification**: Email-based account verification
- **Password Recovery**: Forgot password functionality
- **Profile Management**: Edit user profile information
- **Remember Me**: Extended session duration option
- **Biometric Auth**: Fingerprint/face recognition support
- **Two-Factor Authentication**: Enhanced security with 2FA
- **Account Lockout**: Prevent brute force attacks
- **Password Expiry**: Regular password change requirements
- **Audit Logging**: Track login attempts and security events

## 🐛 Common Issues

- **Database Errors**: Check SQLite helper implementation
- **Session Issues**: Verify SharedPreferences operations
- **Password Problems**: Ensure consistent hashing method
- **Navigation Loops**: Check activity navigation logic
- **Input Validation**: Verify form validation is working

## 📱 User Experience Flow

### New User Journey
1. **Open App** → Login Screen
2. **Tap Register** → Registration Form
3. **Fill Details** → Account Creation
4. **Auto Login** → User Dashboard

### Returning User Journey
1. **Open App** → Check Session
2. **Valid Session** → Direct to Dashboard
3. **Invalid Session** → Login Screen
4. **Enter Credentials** → Dashboard

## 🎯 Use Cases

- **Personal Apps**: Secure access to personal applications
- **Learning**: Understanding authentication concepts
- **Prototyping**: Foundation for larger applications
- **Portfolio**: Demonstrate security implementation skills

---

**Part of MAD Internship - Android Development Portfolio**
