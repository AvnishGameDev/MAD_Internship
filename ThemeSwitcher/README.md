# ThemeSwitcher 🌓

A demonstration application showcasing dynamic theme switching between light and dark modes with persistent theme preferences.

## 📱 Features

- **Dynamic Theme Switching**: Toggle between light and dark themes
- **Persistent Preferences**: Remember theme choice across app sessions
- **Real-time Updates**: Instant theme changes without app restart
- **Material Design**: Proper Material Design theme implementation
- **System Integration**: Follows Android theming best practices
- **Smooth Transitions**: Seamless theme switching experience
- **Custom Themes**: Light and dark theme variations
- **Settings Persistence**: SharedPreferences for theme storage

## 🛠️ Technologies Used

- **Language**: Java
- **Theming**: Android Theme and Style system
- **Storage**: SharedPreferences for theme persistence
- **UI**: SwitchCompat for theme toggle
- **Styling**: Custom theme resources and styles
- **Min SDK**: API 21+

## 📁 Project Structure

```
ThemeSwitcher/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/themeswitcher/
│   │   │   └── MainActivity.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   ├── values/
│   │   │   │   ├── colors.xml
│   │   │   │   ├── strings.xml
│   │   │   │   └── themes.xml
│   │   │   └── values-night/
│   │   │       ├── colors.xml
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Theme Management**: Load and apply themes based on user preference
- **Switch Control**: Handle theme toggle switch interactions
- **Preference Storage**: Save and retrieve theme preferences
- **Activity Recreation**: Properly recreate activity for theme changes

```java
public class MainActivity extends AppCompatActivity {
    private static final String PREFS = "theme_prefs";
    private static final String DARK_MODE = "dark_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Load theme preference before super.onCreate()
        SharedPreferences prefs = getSharedPreferences(PREFS, MODE_PRIVATE);
        boolean dark = prefs.getBoolean(DARK_MODE, false);
        setTheme(dark ? R.style.AppTheme_Dark : R.style.AppTheme);
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        setupThemeSwitch(dark);
    }
}
```

#### Theme Switch Implementation
```java
private void setupThemeSwitch(boolean isDark) {
    SwitchCompat swDarkMode = findViewById(R.id.sw_dark_mode);
    swDarkMode.setChecked(isDark);
    
    swDarkMode.setOnCheckedChangeListener((view, isChecked) -> {
        // Save preference
        getSharedPreferences(PREFS, MODE_PRIVATE)
            .edit()
            .putBoolean(DARK_MODE, isChecked)
            .apply();
        
        // Recreate activity to apply new theme
        recreate();
    });
}
```

### Theme Resources

#### Light Theme (values/themes.xml)
```xml
<style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
    <item name="colorPrimary">@color/colorPrimary</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
    <item name="colorAccent">@color/colorAccent</item>
    <item name="android:windowBackground">@color/light_background</item>
    <item name="android:textColor">@color/light_text</item>
</style>
```

#### Dark Theme (values/themes.xml)
```xml
<style name="AppTheme_Dark" parent="Theme.AppCompat">
    <item name="colorPrimary">@color/colorPrimaryDark</item>
    <item name="colorPrimaryDark">@color/colorPrimaryDarker</item>
    <item name="colorAccent">@color/colorAccentDark</item>
    <item name="android:windowBackground">@color/dark_background</item>
    <item name="android:textColor">@color/dark_text</item>
</style>
```

## 🎯 Learning Objectives

- Android theming system and style resources
- Dynamic theme switching implementation
- SharedPreferences for persistent settings
- Activity lifecycle management during theme changes
- Material Design theming principles
- Resource management for different themes
- User preference handling and storage

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. Observe the current theme (light by default)
4. Use the theme switch to toggle between light and dark modes
5. Close and reopen the app to verify theme persistence
6. Experiment with different UI elements in both themes

## 📋 No Special Permissions Required

This app uses only local preferences storage and doesn't require additional permissions.

## 🔄 App Flow

1. **Launch**: 
   - Load saved theme preference
   - Apply theme before activity creation
   - Initialize UI with current theme
2. **Theme Toggle**:
   - User taps theme switch
   - Save new preference to SharedPreferences
   - Recreate activity to apply new theme
3. **Theme Persistence**:
   - Theme choice remembered across app sessions
   - Automatic theme application on next launch

## 🎨 Theme Components

### Light Theme Features
- **Bright Background**: White or light gray backgrounds
- **Dark Text**: Black or dark gray text for readability
- **Colorful Accents**: Vibrant accent colors
- **Traditional Look**: Standard light mode appearance

### Dark Theme Features
- **Dark Background**: Black or dark gray backgrounds
- **Light Text**: White or light gray text
- **Muted Accents**: Subdued accent colors for dark mode
- **Eye-friendly**: Reduced strain in low-light conditions

### Theme Elements
- **Background Colors**: Primary background and surface colors
- **Text Colors**: Primary and secondary text colors
- **Accent Colors**: Interactive element highlighting
- **Status Bar**: Appropriate status bar styling
- **Action Bar**: Themed action bar and toolbar

## 🔧 Technical Implementation

### Theme Application Process
1. **Preference Check**: Read theme preference from SharedPreferences
2. **Theme Setting**: Apply theme using `setTheme()` before `setContentView()`
3. **UI Update**: Initialize UI components with theme-appropriate styling
4. **State Management**: Update switch state to reflect current theme

### Activity Recreation
```java
swDarkMode.setOnCheckedChangeListener((view, state) -> {
    // Save preference
    prefs.edit().putBoolean(DARK_MODE, state).apply();
    
    // Recreate activity to apply theme
    recreate();
});
```

## 🎨 UI Components

- **Theme Switch**: SwitchCompat for toggling between themes
- **Themed Backgrounds**: Backgrounds that change with theme
- **Styled Text**: Text elements that adapt to theme colors
- **Themed Icons**: Icons that work well in both light and dark modes
- **Consistent Styling**: All UI elements follow theme guidelines

## 🔄 Color Management

### Color Resources
```xml
<!-- Light theme colors -->
<color name="light_background">#FFFFFF</color>
<color name="light_text">#000000</color>

<!-- Dark theme colors -->
<color name="dark_background">#121212</color>
<color name="dark_text">#FFFFFF</color>
```

### Dynamic Color Application
- **Automatic Color Selection**: Colors automatically chosen based on theme
- **Contrast Optimization**: Proper contrast ratios for accessibility
- **Brand Consistency**: Maintain brand colors across themes

## 🔄 Future Enhancements

- **System Theme Following**: Follow system dark mode setting
- **Multiple Themes**: Add more theme options (blue, green, etc.)
- **Custom Colors**: Allow users to customize theme colors
- **Automatic Switching**: Time-based automatic theme switching
- **Theme Preview**: Preview themes before applying
- **Advanced Customization**: Font size, element sizing options
- **Material You**: Dynamic color extraction from wallpaper
- **Theme Scheduler**: Schedule theme changes for specific times
- **Accessibility Options**: High contrast and accessibility themes
- **Theme Import/Export**: Share theme configurations
- **Gradient Themes**: Support for gradient backgrounds
- **Animation**: Smooth theme transition animations

## 🐛 Common Issues

- **Theme Timing**: Ensure `setTheme()` is called before `setContentView()`
- **Resource Conflicts**: Check for proper resource organization
- **State Management**: Verify switch state matches actual theme
- **Activity Lifecycle**: Handle theme changes during activity recreation

## 🎯 Use Cases

- **Accessibility**: Better visibility in different lighting conditions
- **User Preference**: Personal preference for dark or light interfaces
- **Battery Saving**: Dark themes can save battery on OLED screens
- **Eye Strain**: Reduce eye strain in low-light environments
- **Professional Apps**: Provide theme options for user comfort

## 🌙 Dark Mode Benefits

### User Experience
- **Reduced Eye Strain**: Easier on eyes in low-light conditions
- **Better Sleep**: Less blue light exposure before bedtime
- **Focus Enhancement**: Reduced visual distraction
- **Professional Look**: Modern, sleek appearance

### Technical Benefits
- **Battery Saving**: Lower power consumption on OLED displays
- **AMOLED Optimization**: True black pixels turn off completely
- **Performance**: Potentially better performance on some devices

## 🔒 Privacy & Settings

- **Local Storage**: Theme preferences stored locally only
- **No Data Collection**: No user data sent to external servers
- **Instant Changes**: Theme changes apply immediately
- **Persistent Settings**: Preferences survive app updates

---

**Part of MAD Internship - Android Development Portfolio**
