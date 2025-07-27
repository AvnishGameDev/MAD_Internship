# AudioRecorder 🎙️

A comprehensive Android audio recording application that allows users to record, manage, and play back audio files with an intuitive interface.

## 📱 Features

- **Audio Recording**: Record high-quality audio using device microphone
- **Playback Control**: Play recorded audio files with media controls
- **File Management**: View list of all recorded audio files
- **File Import**: Import external audio files from device storage
- **Audio Details**: Display duration, file size, and metadata
- **Permissions Handling**: Runtime permission requests for microphone and storage
- **Persistent Storage**: Save recordings list using SharedPreferences

## 🛠️ Technologies Used

- **Language**: Java
- **UI**: RecyclerView, XML Layouts
- **Audio**: MediaRecorder, MediaPlayer APIs
- **Storage**: SharedPreferences, External Storage
- **Permissions**: Runtime permission handling
- **JSON**: Gson for data serialization
- **Min SDK**: API 21+

## 📁 Project Structure

```
AudioRecorder/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/audiorecorder/
│   │   │   ├── MainActivity.java
│   │   │   ├── AudioListAdapter.java
│   │   │   └── AudioFile.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── item_audio.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Recording Management**: Start/stop audio recording
- **File Operations**: Import, play, and manage audio files
- **UI Updates**: Real-time UI updates during recording/playback
- **Permission Handling**: Runtime permission requests

#### AudioListAdapter.java
- **RecyclerView Adapter**: Display audio files in a list
- **Item Interactions**: Click listeners for play/pause functionality
- **Data Binding**: Bind audio file data to view holders

#### AudioFile.java
- **Data Model**: Represents audio file with metadata
- **File Information**: Duration, size, format, and path storage

### Key Features

#### Recording Functionality
- MediaRecorder configuration for optimal quality
- Start/stop recording with visual feedback
- Automatic file naming with timestamps
- Error handling for recording failures

#### Playback System
- MediaPlayer integration for audio playback
- Play/pause/stop controls
- Progress indication during playback
- Multiple file playback support

#### File Management
- List all recorded audio files
- Import external audio files
- Display file metadata (duration, size)
- Persistent storage of file information

## 🎯 Learning Objectives

- MediaRecorder and MediaPlayer API usage
- RecyclerView implementation with custom adapters
- Runtime permission handling
- File system operations and storage management
- JSON serialization with SharedPreferences
- Audio metadata extraction
- User interface design for media applications

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on a physical device (recommended for microphone access)
3. Grant microphone and storage permissions when prompted
4. Tap the record button to start recording
5. Use the file list to manage and play recordings

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

## 🔄 Future Enhancements

- Audio editing capabilities (trim, merge)
- Cloud storage integration
- Audio sharing functionality
- Recording quality settings
- Audio visualization during recording
- Categorization and tagging system
- Export in different audio formats

## 🐛 Common Issues

- **Recording fails**: Check microphone permissions
- **Playback issues**: Ensure audio file is not corrupted
- **Storage errors**: Verify storage permissions are granted
- **App crashes**: Check for null MediaRecorder/MediaPlayer instances

## 🎨 UI Components

- **Record Button**: Toggle recording state with visual feedback
- **Audio List**: RecyclerView displaying all audio files
- **Import Button**: File picker for external audio import
- **Play Controls**: Individual play/pause buttons for each file

---

**Part of MAD Internship - Android Development Portfolio**
