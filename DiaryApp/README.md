# DiaryApp 📖

A personal diary application with SQLite database integration for secure local storage of diary entries with full CRUD operations.

## 📱 Features

- **Create Entries**: Add new diary entries with title and content
- **View Entries**: Browse all diary entries in a list format
- **Read Full Entry**: Detailed view of individual diary entries
- **Edit Entries**: Modify existing diary entries
- **Delete Entries**: Remove unwanted diary entries
- **Local Storage**: SQLite database for offline data persistence
- **Search Functionality**: Find specific diary entries
- **Date Management**: Automatic timestamp for entries
- **Empty State**: User-friendly message when no entries exist

## 🛠️ Technologies Used

- **Language**: Java
- **Database**: SQLite with custom helper class
- **UI**: RecyclerView, FloatingActionButton, Toolbar
- **Storage**: Local SQLite database
- **Navigation**: Activity-based navigation with parent-child relationships
- **Min SDK**: API 21+

## 📁 Project Structure

```
DiaryApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/diaryapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── AddEntryActivity.java
│   │   │   ├── ViewEntryActivity.java
│   │   │   ├── DiaryDBHelper.java
│   │   │   └── DiaryEntryAdapter.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_add_entry.xml
│   │   │   │   ├── activity_view_entry.xml
│   │   │   │   └── item_diary_entry.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Entry List Display**: RecyclerView showing all diary entries
- **Navigation**: FAB for adding new entries
- **Empty State Management**: Handle UI when no entries exist
- **Database Integration**: Load and display entries from SQLite

#### AddEntryActivity.java
- **Entry Creation**: Form for creating new diary entries
- **Input Validation**: Ensure required fields are filled
- **Database Operations**: Save new entries to SQLite
- **Date Handling**: Automatic timestamp assignment

#### ViewEntryActivity.java
- **Entry Display**: Full-screen view of diary entry content
- **Toolbar Navigation**: Up navigation to parent activity
- **Content Formatting**: Proper display of title and content
- **Intent Data**: Receive and display entry data from list

#### DiaryDBHelper.java
- **Database Management**: SQLite database creation and versioning
- **CRUD Operations**: Create, Read, Update, Delete methods
- **Schema Definition**: Table structure for diary entries
- **Data Persistence**: Ensure data integrity and consistency

#### DiaryEntryAdapter.java
- **RecyclerView Adapter**: Bind diary entries to list items
- **Item Click Handling**: Navigate to entry details
- **View Binding**: Efficient view holder pattern
- **Data Updates**: Handle list data changes

### Database Schema

```sql
CREATE TABLE diary_entries (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    title TEXT NOT NULL,
    content TEXT NOT NULL,
    date_created DATETIME DEFAULT CURRENT_TIMESTAMP,
    date_modified DATETIME DEFAULT CURRENT_TIMESTAMP
);
```

## 🎯 Learning Objectives

- SQLite database integration in Android
- CRUD operations implementation
- RecyclerView with custom adapters
- Activity navigation and data passing
- Local data persistence strategies
- Database helper class patterns
- User interface design for content apps
- Form validation and error handling

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. Tap the "+" FAB to create your first diary entry
4. Fill in the title and content
5. Save the entry and view it in the list
6. Tap any entry to view full content

## 📋 No Special Permissions Required

This app uses only local storage, so no additional permissions are needed.

## 🔄 App Flow

1. **Launch**: Display list of existing diary entries (or empty state)
2. **Add Entry**: Tap FAB to open entry creation form
3. **Save Entry**: Store new entry in local SQLite database
4. **View List**: Return to main list with new entry visible
5. **Read Entry**: Tap any entry to view full content
6. **Navigation**: Use back/up buttons to navigate between screens

## 🎨 UI Components

- **RecyclerView**: Scrollable list of diary entries
- **FloatingActionButton**: Quick access to add new entries
- **Toolbar**: Navigation and title display
- **EditText Fields**: Title and content input forms
- **TextView**: Entry content display
- **Empty State**: Informative message when no entries exist

## 🔄 Database Operations

### Create Entry
```java
public long addEntry(String title, String content) {
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("title", title);
    values.put("content", content);
    return db.insert("diary_entries", null, values);
}
```

### Read Entries
```java
public List<DiaryEntry> getAllEntries() {
    List<DiaryEntry> entries = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor = db.query("diary_entries", null, null, null, null, null, "date_created DESC");
    // Process cursor and populate list
    return entries;
}
```

## 🔄 Future Enhancements

- **Search Functionality**: Search entries by title/content
- **Categories/Tags**: Organize entries by categories
- **Entry Editing**: Modify existing entries
- **Entry Deletion**: Remove unwanted entries
- **Export/Import**: Backup diary data
- **Password Protection**: Secure diary with PIN/password
- **Rich Text Formatting**: Bold, italic, and other formatting
- **Attachment Support**: Add photos to diary entries
- **Cloud Sync**: Optional cloud backup
- **Mood Tracking**: Add mood indicators to entries

## 🐛 Common Issues

- **Database Errors**: Check database helper implementation
- **Empty List**: Verify database operations are working
- **Navigation Issues**: Ensure proper activity declarations in manifest
- **Data Not Persisting**: Check SQLite write operations

## 📱 Screenshots Flow

1. **Main Screen**: List view with FAB
2. **Add Entry**: Form with title and content fields
3. **View Entry**: Full-screen entry display
4. **Empty State**: Friendly message when no entries

---

**Part of MAD Internship - Android Development Portfolio**
