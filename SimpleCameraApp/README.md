# SimpleCameraApp 📸

A comprehensive camera application with photo capture, preview functionality, and image management features using Android's Camera API.

## 📱 Features

- **Photo Capture**: Take high-quality photos using device camera
- **Live Preview**: Real-time camera preview with SurfaceView
- **Image Gallery**: View captured photos in thumbnail gallery
- **Fullscreen View**: Detailed view of captured images
- **Camera Permissions**: Runtime permission handling for camera access
- **Image Storage**: Save captured photos to device storage
- **Thumbnail Display**: Quick preview of recently captured photos
- **Image Management**: Basic photo organization and viewing

## 🛠️ Technologies Used

- **Language**: Java
- **Camera**: Android Camera API (Camera class)
- **UI**: SurfaceView for camera preview, ImageView for thumbnails
- **Storage**: External storage for image saving
- **Permissions**: Runtime camera and storage permissions
- **Image Processing**: Bitmap manipulation and rotation handling
- **Min SDK**: API 21+

## 📁 Project Structure

```
SimpleCameraApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/simplecameraapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── FullscreenImageActivity.java
│   │   │   └── CameraUtils.java (utility)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── activity_fullscreen_image.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Camera Control**: Initialize and manage camera instance
- **Surface Management**: Handle SurfaceView for camera preview
- **Photo Capture**: Implement camera capture functionality with callbacks
- **Permission Handling**: Request and manage camera/storage permissions
- **Image Display**: Show captured photo thumbnails
- **File Management**: Save photos to external storage with proper naming

```java
public class MainActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private Camera camera;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private Button captureButton;
    private ImageView thumbView;
    private String lastImagePath;
}
```

#### FullscreenImageActivity.java
- **Image Display**: Full-screen viewing of captured photos
- **Image Loading**: Load and display high-resolution images
- **Navigation**: Browse through captured images
- **Image Actions**: Share, delete, or edit photo options
- **Gesture Support**: Zoom and pan functionality for detailed viewing

### Camera Implementation

#### Camera Initialization
```java
private void initializeCamera() {
    try {
        camera = Camera.open();
        camera.setDisplayOrientation(90); // Portrait orientation
        camera.setPreviewDisplay(surfaceHolder);
        camera.startPreview();
    } catch (IOException e) {
        // Handle camera initialization error
    }
}
```

#### Photo Capture
```java
camera.takePicture(null, null, new Camera.PictureCallback() {
    public void onPictureTaken(byte[] data, Camera camera) {
        // Save photo to storage
        // Update thumbnail display
        // Restart preview
    }
});
```

## 🎯 Learning Objectives

- Android Camera API implementation and lifecycle
- SurfaceView and SurfaceHolder management
- Runtime permission handling for camera and storage
- Image file management and storage
- Bitmap manipulation and image processing
- Activity lifecycle in camera applications
- Hardware resource management

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on a **physical device** (camera not available in emulator)
3. Grant camera and storage permissions when prompted
4. Point camera at subject and tap capture button
5. View captured photo in thumbnail
6. Tap thumbnail to view full-screen image

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-feature android:name="android.hardware.camera" android:required="true" />
```

## 🔄 App Flow

1. **Launch**: Initialize camera and request permissions
2. **Preview**: Display live camera preview in SurfaceView
3. **Capture**: Tap capture button to take photo
4. **Save**: Automatically save photo to device storage
5. **Thumbnail**: Display captured photo in thumbnail view
6. **View**: Tap thumbnail to open full-screen image view
7. **Gallery**: Browse captured photos in fullscreen activity

## 📸 Camera Features

### Photo Capture
- **High-Quality Images**: Capture photos at device's maximum resolution
- **Auto-Focus**: Automatic focus before capture
- **Flash Support**: Camera flash integration (if available)
- **Orientation Handling**: Proper image orientation management

### Preview System
- **Live Preview**: Real-time camera feed display
- **Surface Management**: Efficient camera preview rendering
- **Aspect Ratio**: Maintain proper preview aspect ratio
- **Performance**: Optimized preview for smooth operation

### Image Management
- **Automatic Saving**: Save photos with timestamp-based naming
- **Thumbnail Generation**: Create efficient thumbnail previews
- **File Organization**: Organized storage structure
- **Quick Access**: Fast thumbnail loading and display

## 🎨 UI Components

- **SurfaceView**: Camera preview display
- **Capture Button**: Large, accessible photo capture control
- **Thumbnail ImageView**: Preview of last captured photo
- **Permission Dialogs**: User-friendly permission requests
- **Fullscreen Viewer**: Immersive photo viewing experience

## 📱 Hardware Integration

### Camera Hardware
- **Camera Access**: Direct hardware camera control
- **Resolution Settings**: Multiple resolution support
- **Focus Modes**: Auto-focus and manual focus options
- **Flash Control**: Camera flash on/off functionality

### Storage Integration
- **External Storage**: Save photos to device gallery
- **File Naming**: Unique timestamp-based file names
- **Directory Management**: Organized photo storage structure
- **Storage Checks**: Verify available storage space

## 🔄 Image Processing

### Bitmap Handling
```java
// Rotate image to correct orientation
Matrix matrix = new Matrix();
matrix.postRotate(90);
Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, 
    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
```

### Thumbnail Generation
- **Efficient Loading**: Generate thumbnails without loading full images
- **Memory Management**: Optimize memory usage for image display
- **Caching**: Cache thumbnails for improved performance

## 🔄 Future Enhancements

- **Video Recording**: Add video capture functionality
- **Multiple Camera Support**: Front/back camera switching
- **Camera Effects**: Filters and real-time effects
- **Burst Mode**: Capture multiple photos rapidly
- **Manual Controls**: Exposure, ISO, and focus controls
- **Gallery Integration**: Better integration with device gallery
- **Photo Editing**: Basic editing tools (crop, rotate, adjust)
- **Social Sharing**: Direct sharing to social media platforms
- **Cloud Backup**: Automatic photo backup to cloud storage
- **Face Detection**: Automatic face detection and focus
- **QR Code Scanner**: Integrate QR code scanning capability
- **Panorama Mode**: Wide-angle panoramic photo capture

## 🐛 Common Issues

- **Permission Denial**: Ensure camera and storage permissions are granted
- **Camera Initialization**: Handle camera access errors gracefully
- **Image Rotation**: Correct image orientation issues
- **Memory Management**: Prevent OutOfMemoryError with large images
- **Surface Lifecycle**: Proper surface creation and destruction

## 📱 Device Compatibility

### Requirements
- **Physical Device**: Camera hardware required (not available in emulator)
- **Camera Permission**: Must grant camera access
- **Storage Space**: Sufficient storage for photo saving
- **Camera Hardware**: Functional camera hardware

### Optimization
- **Performance**: Optimized for various Android devices
- **Resolution**: Adaptive resolution based on device capabilities
- **Memory**: Efficient memory usage for photo processing

## 🔒 Privacy & Security

- **Permission Transparency**: Clear explanation of permission usage
- **Local Storage**: Photos stored locally on device
- **No Cloud Upload**: Default local-only storage
- **User Control**: Complete user control over captured photos

---

**Part of MAD Internship - Android Development Portfolio**

**Note**: This app requires a physical Android device with camera hardware for full functionality. The camera preview and capture features will not work in the Android emulator.
