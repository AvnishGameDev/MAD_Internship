# SimpleQRScanner 📱

A modern QR code and barcode scanner application using Google ML Kit and CameraX for accurate and fast scanning capabilities.

## 📱 Features

- **QR Code Scanning**: Scan and decode QR codes in real-time
- **Barcode Support**: Scan various barcode formats (UPC, EAN, Code 128, etc.)
- **Real-time Detection**: Live scanning with instant feedback
- **ML Kit Integration**: Google ML Kit for accurate barcode detection
- **CameraX**: Modern camera implementation for better performance
- **Auto-focus**: Automatic focusing for optimal scanning
- **Scan Results**: Display decoded information with user-friendly formatting
- **Permission Management**: Runtime camera permission handling

## 🛠️ Technologies Used

- **Language**: Java
- **Camera**: CameraX (modern camera API)
- **ML Kit**: Google ML Kit Vision for barcode scanning
- **UI**: PreviewView for camera display
- **Permissions**: Runtime camera permission handling
- **Architecture**: Image analysis pipeline
- **Min SDK**: API 21+

## 📁 Project Structure

```
SimpleQRScanner/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/simpleqrscanner/
│   │   │   ├── MainActivity.java
│   │   │   ├── QRAnalyzer.java
│   │   │   └── ScanResultActivity.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── activity_scan_result.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Camera Setup**: Initialize CameraX with PreviewView
- **ML Kit Integration**: Configure barcode scanner with ML Kit
- **Permission Handling**: Request and manage camera permissions
- **Scan Processing**: Handle barcode detection results
- **UI Management**: Control scanning interface and feedback

```java
public class MainActivity extends AppCompatActivity {
    private PreviewView previewView;
    private BarcodeScanner scanner;
    private boolean isProcessingQRCode = false;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Initialize camera and scanner
        scanner = BarcodeScanning.getClient();
        setupCamera();
    }
}
```

#### Camera Configuration
```java
private void setupCamera() {
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture = 
        ProcessCameraProvider.getInstance(this);
    
    cameraProviderFuture.addListener(() -> {
        ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
        bindCameraUseCases(cameraProvider);
    }, ContextCompat.getMainExecutor(this));
}
```

#### Barcode Analysis
```java
private void bindCameraUseCases(ProcessCameraProvider cameraProvider) {
    Preview preview = new Preview.Builder().build();
    preview.setSurfaceProvider(previewView.getSurfaceProvider());
    
    ImageAnalysis imageAnalysis = new ImageAnalysis.Builder()
        .setTargetResolution(new Size(1280, 720))
        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
        .build();
        
    imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(this), 
        new QRCodeAnalyzer());
}
```

### ML Kit Integration

#### Barcode Detection
```java
@OptIn(markerClass = ExperimentalGetImage.class)
private void analyzeImage(ImageProxy imageProxy) {
    Image mediaImage = imageProxy.getImage();
    InputImage image = InputImage.fromMediaImage(mediaImage, 
        imageProxy.getImageInfo().getRotationDegrees());
    
    scanner.process(image)
        .addOnSuccessListener(barcodes -> {
            for (Barcode barcode : barcodes) {
                handleBarcodeResult(barcode);
            }
        })
        .addOnCompleteListener(task -> imageProxy.close());
}
```

## 🎯 Learning Objectives

- Modern camera implementation with CameraX
- Google ML Kit Vision API integration
- Real-time image analysis and processing
- Barcode and QR code scanning techniques
- Camera permission handling
- Asynchronous image processing
- Mobile computer vision applications

## 🚀 Getting Started

1. Open the project in Android Studio
2. Ensure Google Play Services is available on target device
3. Run on a **physical device** (camera required)
4. Grant camera permission when prompted
5. Point camera at QR code or barcode
6. View automatic detection and decoded results

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-feature android:name="android.hardware.camera" android:required="true" />
```

## 📋 Required Dependencies

```gradle
implementation 'androidx.camera:camera-core:1.1.0'
implementation 'androidx.camera:camera-camera2:1.1.0'
implementation 'androidx.camera:camera-lifecycle:1.1.0'
implementation 'androidx.camera:camera-view:1.1.0'
implementation 'com.google.mlkit:barcode-scanning:17.0.2'
```

## 🔄 App Flow

1. **Launch**: Initialize camera and ML Kit components
2. **Permission Check**: Request camera permission if not granted
3. **Camera Preview**: Display live camera feed
4. **Scan Detection**: ML Kit continuously analyzes camera frames
5. **Result Processing**: Handle detected QR codes/barcodes
6. **Display Results**: Show decoded information to user
7. **Continue Scanning**: Return to scanning mode for additional codes

## 📱 Supported Barcode Formats

### QR Codes
- **Standard QR**: Regular QR codes with various data types
- **URLs**: Direct website links
- **Text**: Plain text information
- **Contact Info**: vCard contact information
- **WiFi**: WiFi network credentials
- **Email**: Email addresses and content

### Barcodes
- **UPC-A**: Universal Product Code
- **UPC-E**: UPC compressed format
- **EAN-13**: European Article Number
- **EAN-8**: EAN short format
- **Code 128**: High-density barcode
- **Code 39**: Alpha-numeric barcode
- **ITF**: Interleaved 2 of 5
- **Codabar**: Variable-length barcode

## 🎨 UI Components

- **PreviewView**: CameraX camera preview display
- **Overlay Graphics**: Scanning frame and guidelines
- **Result Display**: Toast messages or dialog for scan results
- **Permission Dialog**: Camera permission request interface
- **Scanning Indicator**: Visual feedback during scanning process

## ⚡ Performance Features

### Real-time Processing
- **Continuous Scanning**: Non-stop barcode detection
- **Optimized Performance**: Efficient image analysis pipeline
- **Background Processing**: Asynchronous barcode analysis
- **Memory Management**: Proper image proxy lifecycle management

### Scanning Optimization
- **Auto-focus**: Automatic camera focusing for clear scans
- **Resolution Settings**: Optimized resolution for scanning accuracy
- **Frame Rate**: Balanced frame rate for performance and accuracy
- **Processing Throttle**: Prevent duplicate scan processing

## 🔄 Scan Result Handling

### Data Types
```java
switch (barcode.getValueType()) {
    case Barcode.TYPE_URL:
        // Handle URL scanning
        break;
    case Barcode.TYPE_TEXT:
        // Handle plain text
        break;
    case Barcode.TYPE_CONTACT_INFO:
        // Handle contact information
        break;
    case Barcode.TYPE_WIFI:
        // Handle WiFi credentials
        break;
}
```

### Actions
- **Copy to Clipboard**: Copy scan results
- **Open URLs**: Launch web browser for URLs
- **Save Contacts**: Add contact information
- **Connect WiFi**: Join WiFi networks
- **Share Results**: Share scan results with other apps

## 🔄 Future Enhancements

- **Scan History**: Keep record of previously scanned codes
- **Batch Scanning**: Scan multiple codes in sequence
- **Custom Actions**: User-defined actions for different scan types
- **Offline Mode**: Save scans for later processing
- **Export Results**: Export scan data to CSV or other formats
- **Barcode Generation**: Create QR codes and barcodes
- **Flashlight Toggle**: Camera flash for low-light scanning
- **Zoom Control**: Pinch-to-zoom for distant barcodes
- **Sound Feedback**: Audio feedback for successful scans
- **Vibration**: Haptic feedback for scan confirmation
- **Custom Overlays**: Branded scanning interface
- **Analytics**: Track scanning patterns and success rates

## 🐛 Common Issues

- **Camera Permission**: Ensure camera permission is granted
- **Google Play Services**: Verify ML Kit availability
- **Lighting Conditions**: Ensure adequate lighting for scanning
- **Code Quality**: Check if barcode/QR code is clear and undamaged
- **Distance**: Maintain appropriate distance from code

## 📱 Device Requirements

### Hardware Requirements
- **Camera**: Functional camera with auto-focus capability
- **Processor**: Sufficient processing power for real-time analysis
- **Memory**: Adequate RAM for image processing
- **Google Play Services**: Required for ML Kit functionality

### Software Requirements
- **Android Version**: API 21+ (Android 5.0+)
- **CameraX Support**: Modern camera API support
- **ML Kit Compatibility**: Google Play Services available

## 🔒 Privacy & Security

- **Local Processing**: All scanning done on-device
- **No Cloud Upload**: Scan results not sent to external servers
- **Permission Transparency**: Clear camera permission usage
- **Data Control**: User controls all scan result data

## 🎯 Use Cases

- **Retail**: Product information and price checking
- **Events**: Ticket scanning and event check-in
- **Inventory**: Warehouse and inventory management
- **Payments**: QR code payment processing
- **Networking**: Quick WiFi connection sharing
- **Contact Sharing**: Exchange contact information
- **URL Sharing**: Quick website access

---

**Part of MAD Internship - Android Development Portfolio**

**Note**: This app requires a physical Android device with camera hardware and Google Play Services for ML Kit functionality. QR scanning will not work in the Android emulator.
