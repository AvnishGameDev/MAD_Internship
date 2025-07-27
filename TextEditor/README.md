# TextEditor 📝

A simple yet powerful text editor application with file operations, document management, and sharing capabilities for creating and editing text documents.

## 📱 Features

- **Text Editing**: Create and edit text documents with full editing capabilities
- **File Operations**: Open, create, save, and manage text files
- **Document Sharing**: Share documents with other applications
- **File Picker**: Browse and select text files from device storage
- **Auto-save**: Automatic saving of document changes
- **Clean Interface**: Minimalist design focused on writing
- **Multiple Format Support**: Support for various text file formats
- **Storage Integration**: Integration with device file system

## 🛠️ Technologies Used

- **Language**: Java
- **File I/O**: Android file system operations
- **Storage**: Document provider framework
- **UI**: EditText with enhanced functionality
- **Intents**: File picker and sharing intents
- **Permissions**: Storage access permissions
- **Min SDK**: API 21+

## 📁 Project Structure

```
TextEditor/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/texteditor/
│   │   │   ├── MainActivity.java
│   │   │   ├── FileUtils.java
│   │   │   └── Document.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Text Editing Interface**: Large EditText for document editing
- **File Operations**: Open, create, save, and share functionality
- **Intent Handling**: Handle file picker and sharing intents
- **Document Management**: Track current document state and changes
- **Auto-save**: Periodic saving of document content

```java
public class MainActivity extends AppCompatActivity {
    private static final int CREATE_FILE = 2;
    private static final int PICK_TEXT_FILE = 1;
    private Uri currentUri = null;
    
    EditText etMain;
    Button btnOpen, btnCreate, btnSave, btnShare;
}
```

#### File Operations
```java
// Create new file
private void createFile() {
    Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType("text/plain");
    intent.putExtra(Intent.EXTRA_TITLE, "document.txt");
    startActivityForResult(intent, CREATE_FILE);
}

// Open existing file
private void openFile() {
    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
    intent.addCategory(Intent.CATEGORY_OPENABLE);
    intent.setType("text/*");
    startActivityForResult(intent, PICK_TEXT_FILE);
}
```

### Document Management

#### File Reading
```java
private String readTextFromUri(Uri uri) throws IOException {
    StringBuilder stringBuilder = new StringBuilder();
    try (InputStream inputStream = getContentResolver().openInputStream(uri);
         BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line).append('\n');
        }
    }
    return stringBuilder.toString();
}
```

#### File Writing
```java
private void saveTextToUri(Uri uri, String text) throws IOException {
    try (ParcelFileDescriptor pfd = getContentResolver().openFileDescriptor(uri, "w");
         FileOutputStream fileOutputStream = new FileOutputStream(pfd.getFileDescriptor())) {
        fileOutputStream.write(text.getBytes());
    }
}
```

## 🎯 Learning Objectives

- File system operations in Android
- Document provider framework usage
- Intent handling for file operations
- Storage Access Framework (SAF) implementation
- Text editing and manipulation
- File I/O with proper error handling
- Content provider interactions

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. Use "Create" to create a new text document
4. Use "Open" to browse and open existing text files
5. Edit text in the main editing area
6. Use "Save" to save changes to current document
7. Use "Share" to share document with other apps

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

## 🔄 App Flow

1. **Launch**: Open text editor with empty document
2. **Create Document**: 
   - Tap "Create" button
   - Choose file name and location
   - Start editing new document
3. **Open Document**:
   - Tap "Open" button
   - Browse and select text file
   - Load content into editor
4. **Edit Content**: Type and modify text in editor
5. **Save Changes**: Save current document to storage
6. **Share Document**: Share document content with other apps

## 📝 Text Editor Features

### Editing Capabilities
- **Multi-line Editing**: Full-featured text editing with multiple lines
- **Text Selection**: Select, copy, cut, and paste text
- **Undo/Redo**: (Potential) Edit history management
- **Find/Replace**: (Potential) Search and replace functionality
- **Word Count**: (Potential) Real-time word and character count

### File Format Support
- **Plain Text (.txt)**: Standard text files
- **Rich Text**: (Potential) Basic formatting support
- **Markdown**: (Potential) Markdown document support
- **Various Encodings**: UTF-8 and other character encodings

### Document Operations
- **New Document**: Create blank documents
- **Open Recent**: (Potential) Quick access to recent files
- **Auto-save**: Automatic saving of changes
- **File Backup**: (Potential) Backup document versions

## 🎨 UI Components

- **Large EditText**: Main text editing area with scroll support
- **Toolbar Buttons**: File operation controls (Open, Create, Save, Share)
- **Status Indicators**: Show current document status
- **File Dialogs**: System file picker integration
- **Share Dialog**: Android sharing interface

## 📄 Document Management

### File Operations
- **Create**: New document creation with file picker
- **Open**: Browse and open existing text files
- **Save**: Save current document to selected location
- **Save As**: (Potential) Save document with new name/location
- **Recent Files**: (Potential) Quick access to recently opened files

### Content Handling
- **Large Files**: Efficient handling of large text documents
- **Encoding Support**: Proper character encoding handling
- **Line Endings**: Cross-platform line ending support
- **Memory Management**: Efficient text storage and manipulation

## 🔄 Sharing Integration

### Share Options
```java
private void shareDocument() {
    String text = etMain.getText().toString();
    Intent shareIntent = new Intent(Intent.ACTION_SEND);
    shareIntent.setType("text/plain");
    shareIntent.putExtra(Intent.EXTRA_TEXT, text);
    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Shared Document");
    startActivity(Intent.createChooser(shareIntent, "Share document"));
}
```

### Integration Points
- **Email**: Share document via email
- **Messaging**: Send document content via SMS/messaging apps
- **Cloud Storage**: Save to Google Drive, Dropbox, etc.
- **Social Media**: Share excerpts on social platforms

## 🔄 Future Enhancements

- **Syntax Highlighting**: Code highlighting for programming languages
- **Rich Text Formatting**: Bold, italic, and other formatting options
- **Document Templates**: Pre-designed document templates
- **Export Options**: Export to PDF, HTML, and other formats
- **Cloud Sync**: Automatic backup to cloud storage
- **Collaborative Editing**: Multi-user document editing
- **Version History**: Track document changes over time
- **Password Protection**: Secure sensitive documents
- **Plugin System**: Extensible functionality with plugins
- **Themes**: Dark mode and custom editor themes
- **Word Processing**: Advanced formatting and layout options
- **Table Support**: Insert and edit tables
- **Image Insertion**: Embed images in documents
- **Spell Check**: Real-time spelling and grammar checking

## 🐛 Common Issues

- **Storage Permissions**: Ensure file access permissions are granted
- **File Access**: Check if selected files are accessible
- **Large Files**: Handle memory efficiently for large documents
- **Character Encoding**: Ensure proper encoding for international text
- **Save Conflicts**: Handle file saving errors gracefully

## 📱 Storage Integration

### Storage Access Framework
- **Document Provider**: Use SAF for file operations
- **Permission Management**: Handle storage permissions properly
- **Cross-platform**: Support various storage providers
- **Security**: Secure file access without broad permissions

### File System Support
- **Internal Storage**: App-specific document storage
- **External Storage**: SD card and external storage access
- **Cloud Providers**: Integration with cloud storage services
- **Network Storage**: (Potential) Network file system support

## 🎯 Use Cases

- **Note Taking**: Quick notes and reminders
- **Document Creation**: Create letters, reports, and documents
- **Code Editing**: Basic programming and script editing
- **Creative Writing**: Stories, articles, and creative content
- **Academic Writing**: Essays and research documents
- **Business Documents**: Memos, proposals, and business text

## 🔒 Privacy & Security

- **Local Storage**: Documents stored locally on device
- **No Cloud Upload**: Default local-only storage (unless user shares)
- **Permission Control**: User controls file access permissions
- **Data Ownership**: Complete user control over document content

---

**Part of MAD Internship - Android Development Portfolio**
