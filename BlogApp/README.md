# BlogApp 📝

A full-featured blog application with Firebase backend integration, user authentication, and comprehensive blog management functionality.

## 📱 Features

- **User Authentication**: Firebase Auth with Google Sign-In
- **Blog Creation**: Rich text blog post creation and publishing
- **Blog Management**: View, edit, and delete blog posts
- **User Dashboard**: Centralized blog management interface
- **Real-time Updates**: Live synchronization with Firebase Firestore
- **Push Notifications**: Blog update notifications
- **Responsive Design**: Material Design UI components
- **Session Management**: Persistent login state

## 🛠️ Technologies Used

- **Language**: Java
- **Backend**: Firebase (Auth, Firestore)
- **Authentication**: Firebase Auth, Google Sign-In
- **Database**: Firebase Firestore
- **Notifications**: Firebase Cloud Messaging
- **UI**: CardView, RecyclerView, Material Design
- **Min SDK**: API 21+

## 📁 Project Structure

```
BlogApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/blogapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── LoginActivity.java
│   │   │   ├── AddBlogActivity.java
│   │   │   ├── ViewBlogsActivity.java
│   │   │   ├── BlogDisplayActivity.java
│   │   │   └── NotificationHelper.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_add_blog.xml
│   │   │   │   ├── activity_view_blogs.xml
│   │   │   │   └── activity_blog_display.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Dashboard Interface**: Central hub for blog operations
- **Authentication Check**: Verify user login status
- **Navigation**: Route to different blog functionalities
- **Session Management**: Handle user authentication state

#### AddBlogActivity.java
- **Blog Creation**: Rich text editor for blog content
- **Firebase Upload**: Save blog posts to Firestore
- **Media Integration**: Image upload and attachment
- **Validation**: Input validation and error handling

#### ViewBlogsActivity.java
- **Blog Listing**: Display all user blogs in RecyclerView
- **Search Functionality**: Filter and search blogs
- **Blog Management**: Edit and delete operations
- **Real-time Updates**: Live data synchronization

#### BlogDisplayActivity.java
- **Blog Reader**: Full-screen blog post display
- **Content Formatting**: Rich text rendering
- **Sharing Options**: Share blog posts with others
- **Comments System**: (If implemented) Blog comments

#### LoginActivity.java
- **User Authentication**: Firebase Auth integration
- **Google Sign-In**: OAuth authentication flow
- **Registration**: New user account creation
- **Error Handling**: Authentication error management

### Firebase Integration

#### Firestore Database Structure
```
blogs/
  └── {blogId}/
      ├── title: String
      ├── content: String
      ├── author: String
      ├── timestamp: Timestamp
      ├── userId: String
      └── imageUrl: String (optional)

users/
  └── {userId}/
      ├── name: String
      ├── email: String
      └── profileImage: String
```

## 🎯 Learning Objectives

- Firebase Firestore real-time database operations
- User authentication and session management
- CRUD operations with cloud databases
- RecyclerView with Firebase adapters
- Push notification implementation
- Material Design principles
- Cloud storage integration
- Real-time data synchronization

## 🚀 Getting Started

### Firebase Setup
1. Create a Firebase project
2. Enable Authentication (Email/Password, Google)
3. Create Firestore database
4. Add Android app to Firebase project
5. Download and add `google-services.json`
6. Configure authentication providers

### Running the App
1. Open project in Android Studio
2. Ensure Firebase configuration is complete
3. Run on emulator or device
4. Sign in with Google account
5. Start creating and managing blogs

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.POST_NOTIFICATIONS" />
```

## 🔄 App Flow

1. **Launch**: Check authentication status
2. **Login**: Authenticate user via Firebase
3. **Dashboard**: Main blog management interface
4. **Create Blog**: Rich text editor for new posts
5. **View Blogs**: List of all user blogs
6. **Read Blog**: Full-screen blog display
7. **Manage**: Edit or delete existing blogs

## 🔔 Notification System

- **Blog Published**: Notify when new blog is created
- **Blog Updated**: Alert on blog modifications
- **Permission Handling**: Runtime notification permissions
- **Custom Notifications**: Rich notification content

## 🎨 UI Components

- **CardView Dashboard**: Main navigation cards
- **RecyclerView**: Blog list display
- **FloatingActionButton**: Quick blog creation
- **Rich Text Editor**: Enhanced blog content creation
- **Material Design**: Consistent UI theme

## 🔄 Future Enhancements

- **Image Upload**: Blog post image attachments
- **Comments System**: Reader comments and interactions
- **Blog Categories**: Organize blogs by topics
- **Social Sharing**: Share blogs on social media
- **Offline Support**: Offline blog reading
- **Search & Filter**: Advanced blog discovery
- **User Profiles**: Public user profiles
- **Blog Analytics**: View counts and engagement

## 🐛 Common Issues

- **Firebase Config**: Ensure `google-services.json` is properly configured
- **Network Connectivity**: Check internet connection for Firebase operations
- **Authentication**: Verify Firebase Auth is enabled
- **Permissions**: Grant necessary permissions for notifications

---

**Part of MAD Internship - Android Development Portfolio**
