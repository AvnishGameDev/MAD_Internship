# AudioPlayer 🎵

A simple Android audio player application that demonstrates basic media playback functionality using Android's MediaPlayer API.

## 📱 Features

- **Play Audio**: Start playback of pre-loaded audio file
- **Pause Audio**: Pause the currently playing audio
- **Stop Audio**: Stop playback and reset to beginning
- **User Feedback**: Toast messages for user actions
- **Resource Management**: Proper MediaPlayer lifecycle management

## 🛠️ Technologies Used

- **Language**: Java
- **UI**: XML Layouts
- **Media**: MediaPlayer API
- **Audio Format**: Raw audio resources
- **Min SDK**: API 21+

## 📁 Project Structure

```
AudioPlayer/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/audioplayer/
│   │   │   └── MainActivity.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   ├── raw/
│   │   │   │   └── idol.mp3 (or similar audio file)
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### MainActivity.java
- **MediaPlayer Management**: Creates, starts, pauses, and releases MediaPlayer
- **Button Controls**: Three buttons for play, pause, and stop functionality
- **Lifecycle Handling**: Proper cleanup in onDestroy() method
- **State Management**: Checks MediaPlayer state before operations

### Key Methods
- `onCreate()`: Initializes UI components and MediaPlayer
- `onDestroy()`: Releases MediaPlayer resources
- Button click listeners for media control operations

## 🎯 Learning Objectives

- Understanding MediaPlayer API usage
- Audio resource management in Android
- Proper lifecycle management of media components
- Button event handling and user feedback
- Resource cleanup and memory management

## 🚀 Getting Started

1. Open the project in Android Studio
2. Ensure you have an audio file in `res/raw/` directory
3. Update the resource reference in MainActivity if needed
4. Run on emulator or physical device
5. Use the three buttons to control audio playback

## 📋 Permissions

No special permissions required as the app uses local audio resources.

## 🔄 Future Enhancements

- Multiple audio file support
- Seek bar for audio scrubbing
- Volume controls
- Playlist functionality
- Audio file picker from device storage
- Background playback with service

## 🐛 Common Issues

- **Audio not playing**: Ensure audio file exists in `res/raw/` directory
- **App crashes**: Check MediaPlayer null checks and proper initialization
- **Memory leaks**: Ensure MediaPlayer is released in onDestroy()

---

**Part of MAD Internship - Android Development Portfolio**
