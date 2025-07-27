# MusicPlayer 🎵

A genre-based music player application with categorized music browsing and comprehensive media playback controls for different music genres.

## 📱 Features

- **Genre Categories**: Organized music by Hip-Hop, Lo-Fi, Classical, and Indie genres
- **Music Browsing**: Browse songs within each category
- **Media Playback**: Full audio playback controls (play, pause, stop, seek)
- **Playlist Management**: Genre-specific playlists
- **User Interface**: Intuitive navigation with genre selection
- **Audio Controls**: Professional media player controls
- **Music Organization**: Structured approach to music library management

## 🛠️ Technologies Used

- **Language**: Java
- **Media**: MediaPlayer API for audio playback
- **UI**: Activity-based navigation, Material Design
- **Audio Resources**: Local audio files organized by genre
- **Navigation**: Intent-based activity switching
- **Min SDK**: API 21+

## 📁 Project Structure

```
MusicPlayer/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/musicplayer/
│   │   │   ├── MainActivity.java
│   │   │   ├── MusicListActivity.java
│   │   │   └── Song.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_music_list.xml
│   │   │   │   └── item_song.xml
│   │   │   ├── raw/ (music files organized by genre)
│   │   │   │   ├── hip_hop/
│   │   │   │   ├── lofi/
│   │   │   │   ├── classical/
│   │   │   │   └── indie/
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Genre Selection Hub**: Main interface with four genre category buttons
- **Navigation Control**: Route to specific genre playlists
- **User Interface**: Clean, organized genre selection screen
- **Exit Functionality**: Graceful app termination option

```java
// Genre selection buttons
btnHipHop.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_HIP_HOP));
btnLofi.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_LOFI));
btnClassical.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_CLASSICAL));
btnIndie.setOnClickListener(v -> startMusicListActivity(MusicListActivity.GENRE_INDIE));
```

#### MusicListActivity.java
- **Genre-Specific Playlists**: Display songs for selected genre
- **Music Playback**: MediaPlayer integration for audio control
- **Song Management**: List all songs in the selected genre
- **Media Controls**: Play, pause, stop, and seek functionality
- **Now Playing**: Current song information display

### Music Genre Categories

#### 🎤 Hip-Hop
- **Style**: Urban, rap, contemporary beats
- **Features**: Bass-heavy tracks, rhythmic vocals
- **Target**: Urban music enthusiasts

#### 🎧 Lo-Fi
- **Style**: Chill, atmospheric, relaxing beats
- **Features**: Soft instrumentals, ambient sounds
- **Target**: Study and relaxation music lovers

#### 🎼 Classical
- **Style**: Traditional orchestral and instrumental pieces
- **Features**: Symphonies, concertos, chamber music
- **Target**: Classical music appreciators

#### 🎸 Indie
- **Style**: Independent, alternative, experimental
- **Features**: Unique sounds, non-mainstream artists
- **Target**: Alternative music enthusiasts

## 🎯 Learning Objectives

- MediaPlayer API implementation and lifecycle management
- Audio resource organization and management
- Genre-based application architecture
- Music player UI design patterns
- Audio playback state management
- User navigation in media applications
- Audio file handling and organization

## 🚀 Getting Started

1. Open the project in Android Studio
2. Ensure music files are placed in appropriate `res/raw/` subdirectories
3. Run on emulator or physical device
4. Select a music genre from the main screen
5. Browse and play songs from the selected genre
6. Use media controls to manage playback

## 📋 Required Audio Setup

### Audio File Organization
```
res/raw/
├── hip_hop/
│   ├── track1.mp3
│   ├── track2.mp3
│   └── track3.mp3
├── lofi/
│   ├── chill1.mp3
│   ├── chill2.mp3
│   └── ambient1.mp3
├── classical/
│   ├── symphony1.mp3
│   ├── concerto1.mp3
│   └── sonata1.mp3
└── indie/
    ├── alternative1.mp3
    ├── experimental1.mp3
    └── indie_rock1.mp3
```

## 🔄 App Navigation Flow

```
MainActivity (Genre Selection)
├── Hip-Hop Button → MusicListActivity (Hip-Hop Playlist)
├── Lo-Fi Button → MusicListActivity (Lo-Fi Playlist)
├── Classical Button → MusicListActivity (Classical Playlist)
├── Indie Button → MusicListActivity (Indie Playlist)
└── Exit Button → App Termination
```

## 🎨 UI Components

### Main Screen
- **Genre Buttons**: Four large, themed buttons for each genre
- **Exit Button**: Clean app termination
- **Consistent Design**: Material Design principles

### Music List Screen
- **Song List**: RecyclerView with song information
- **Media Controls**: Play/pause/stop buttons
- **Progress Bar**: Track playback progress
- **Now Playing**: Current song display
- **Back Navigation**: Return to genre selection

## 🎵 Media Player Features

### Playback Controls
- **Play**: Start audio playback
- **Pause**: Pause current track
- **Stop**: Stop playback and reset position
- **Seek**: Navigate to specific track position (if implemented)

### Audio Management
- **Queue Management**: Handle playlist order
- **Track Information**: Display song metadata
- **Playback State**: Track current playing status
- **Resource Management**: Proper MediaPlayer lifecycle

## 🔄 Future Enhancements

- **Shuffle & Repeat**: Playlist shuffle and repeat modes
- **Cross-fade**: Smooth transitions between tracks
- **Equalizer**: Audio enhancement controls
- **Playlist Creation**: Custom user playlists
- **Search Functionality**: Find songs across genres
- **Album Art**: Display song artwork
- **Lyrics Display**: Show song lyrics during playback
- **Favorites**: Mark and access favorite tracks
- **Sleep Timer**: Auto-stop after specified time
- **Background Playback**: Continue playing when app is minimized
- **Streaming Support**: Online music streaming integration
- **Social Features**: Share currently playing songs

## 🐛 Common Issues

- **Audio Files**: Ensure music files are in correct res/raw/ directories
- **MediaPlayer**: Proper MediaPlayer initialization and release
- **Memory Management**: Handle large audio files efficiently
- **File Formats**: Verify supported audio formats (MP3, AAC, etc.)
- **Resource Conflicts**: Avoid resource naming conflicts

## 📱 User Experience

### Intuitive Navigation
- Clear genre categorization
- Easy-to-use media controls
- Smooth navigation between screens
- Consistent visual design

### Music Discovery
- Genre-based organization helps users find preferred music styles
- Clear song listing with metadata
- Quick access to different music categories

## 🎼 Audio Quality

- **High-Quality Playback**: Support for various audio formats
- **Optimized Performance**: Efficient audio streaming
- **Resource Management**: Proper memory usage for audio files

---

**Part of MAD Internship - Android Development Portfolio**
