# Clock - Digital Clock & Alarm Application ⏰

A comprehensive digital clock application with alarm functionality, notifications support, and fragment-based navigation using bottom navigation view.

## 📱 Features

### Core Functionality
- **Digital Clock Display** - Real-time digital clock with current time
- **Alarm Management** - Set, edit, and delete multiple alarms
- **Notification Support** - Alarm notifications with sound alerts
- **Background Service** - Alarm functionality works in background
- **Permission Handling** - Proper notification and alarm permissions

### User Interface
- **Bottom Navigation** - Fragment-based navigation between different sections
- **Modern Design** - Clean and intuitive user interface
- **Responsive Layout** - Optimized for different screen sizes
- **Material Design** - Following Android design guidelines

### Technical Features
- **Fragment Architecture** - Organized code structure with fragments
- **Alarm Manager** - Android AlarmManager for scheduling alarms
- **Foreground Service** - Background alarm monitoring service
- **Full Screen Intent** - Alarm ring activity with full screen display
- **Runtime Permissions** - Dynamic permission handling for notifications

## 🛠️ Technical Implementation

### Architecture
- **Language**: Java
- **UI Framework**: XML layouts with Fragment-based navigation
- **Navigation**: Bottom Navigation View
- **Permissions**: SCHEDULE_EXACT_ALARM, POST_NOTIFICATIONS, USE_FULL_SCREEN_INTENT, FOREGROUND_SERVICE

### Key Components
- `MainActivity.java` - Main activity with bottom navigation setup
- `AlarmFragment.java` - Fragment for alarm management
- `AlarmRingActivity.java` - Full screen alarm ring interface
- **Service Components** - Background alarm monitoring
- **Notification System** - Android notification framework integration

### Dependencies
- Material Design Components
- Android Support Libraries
- Fragment Navigation

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21 or higher
- Device/Emulator with notification support

### Installation
1. Open Android Studio
2. Navigate to `Clock/` directory
3. Open the project in Android Studio
4. Wait for Gradle sync to complete
5. Run the application

### Permissions Setup
The app will request the following permissions at runtime:
- **Notification Permission** - For alarm notifications
- **Exact Alarm Permission** - For precise alarm scheduling
- **Full Screen Intent** - For alarm ring screen

## 📖 How to Use

### Setting Alarms
1. Launch the Clock application
2. Navigate to the Alarm section using bottom navigation
3. Tap the add button to create a new alarm
4. Set your desired time and configure alarm settings
5. Save the alarm - it will be scheduled automatically

### Managing Alarms
- **View Alarms** - See all your scheduled alarms in the main list
- **Edit Alarms** - Tap on existing alarms to modify time or settings
- **Delete Alarms** - Remove unwanted alarms from the list
- **Toggle Alarms** - Enable/disable alarms without deleting them

### Alarm Notifications
- Alarms will trigger notifications at the scheduled time
- Full screen alarm ring activity will display for active alarms
- Sound alerts will play to wake you up
- Dismiss or snooze options available

## 🎯 Learning Objectives

This project demonstrates:
- **Fragment Navigation** - Bottom navigation with multiple fragments
- **Android Services** - Background processing and foreground services
- **Alarm Management** - Using AlarmManager for scheduling
- **Notification System** - Creating and managing notifications
- **Permission Handling** - Runtime permission requests
- **UI/UX Design** - Modern clock interface design
- **Background Processing** - Alarm monitoring while app is closed

## 🔧 Future Enhancements

- **Multiple Alarm Tones** - Custom sound selection for alarms
- **Snooze Functionality** - Customizable snooze intervals
- **World Clock** - Different time zones display
- **Stopwatch Feature** - Timer and stopwatch functionality
- **Alarm Labels** - Custom names for different alarms
- **Recurring Alarms** - Daily, weekly, or custom repeat patterns
- **Sleep Timer** - Auto-stop functionality for media

## 🐛 Troubleshooting

### Common Issues
- **Alarms Not Triggering**: Ensure notification permissions are granted
- **Background Issues**: Check if battery optimization is disabled for the app
- **Sound Not Playing**: Verify device volume and notification settings
- **Permission Denied**: Grant all required permissions in app settings

### Debug Tips
- Check Android notification settings for the app
- Verify alarm permissions in device settings
- Ensure the app is not being killed by battery optimization
- Test on different Android versions for compatibility

## 📄 License

This project is developed for educational purposes as part of the MAD Internship program.

---

**Note**: This application requires notification permissions to function properly. Some features may be limited on older Android versions.
