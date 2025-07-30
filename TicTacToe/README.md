# TicTacToe - Classic Game Implementation 🎮

A modern implementation of the classic Tic-Tac-Toe game for Android, featuring score tracking, game state management, and clean UI design.

## 📱 Features

### Game Mechanics
- **Classic 3x3 Grid** - Traditional tic-tac-toe gameplay
- **Two Player Mode** - Player X vs Player O alternating turns
- **Win Detection** - Automatic detection of winning combinations
- **Draw Detection** - Game recognizes when board is full with no winner
- **Game Reset** - Start new games while maintaining score history

### User Interface
- **Visual Grid** - Interactive 3x3 game board with ImageViews
- **Turn Indicator** - Clear display of whose turn it is
- **Score Tracking** - Persistent score counter for both players
- **Reset Button** - Easy game restart functionality
- **Responsive Design** - Clean and intuitive layout

### Technical Features
- **Game Logic** - Comprehensive win/draw checking algorithms
- **State Management** - Proper game state handling and transitions
- **Click Handling** - Responsive touch interactions
- **Score Persistence** - Score tracking across multiple games
- **Game Flow Control** - Turn management and game ending logic

## 🛠️ Technical Implementation

### Architecture
- **Language**: Java
- **UI Framework**: XML layouts with ImageViews and TextViews
- **Pattern**: MVC (Model-View-Controller) architecture
- **Game Logic**: 2D array representation of game board

### Key Components
- `MainActivity.java` - Main game activity with all game logic
- **Board Representation** - 2D integer array (0=empty, 1=X, 2=O)
- **UI Elements** - ImageViews for board, TextViews for scores and status
- **Click Listeners** - OnClickListener implementation for board interaction
- **Game Algorithms** - Win checking and draw detection methods

### Game Logic
```java
private final int[][] board = new int[3][3];
private boolean playerXTurn = true;
private int playerXScore = 0;
private int playerOScore = 0;
private boolean gameActive = true;
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Arctic Fox or later
- Android SDK API 21 or higher
- No special permissions required

### Installation
1. Open Android Studio
2. Navigate to `TicTacToe/` directory
3. Open the project in Android Studio
4. Wait for Gradle sync to complete
5. Run the application on device or emulator

### No Setup Required
- No network connection needed
- No permissions required
- Works offline completely

## 📖 How to Play

### Game Rules
1. **Starting** - Player X always goes first
2. **Taking Turns** - Players alternate placing X's and O's
3. **Winning** - Get three of your symbols in a row (horizontal, vertical, or diagonal)
4. **Draw** - If the board fills up with no winner, it's a draw
5. **New Game** - Press reset to start over while keeping scores

### Game Controls
- **Tap Grid Cell** - Place your symbol (X or O) in empty cell
- **Turn Display** - See whose turn it is at the top
- **Score Tracking** - View current scores for both players
- **Reset Button** - Start new game (scores remain)
- **Automatic Detection** - Game automatically detects wins and draws

### Winning Conditions
The game checks for wins in:
- **Horizontal Lines** - Three in a row across any row
- **Vertical Lines** - Three in a column down any column  
- **Diagonal Lines** - Three in a line diagonally across the board

## 🎯 Learning Objectives

This project demonstrates:
- **2D Array Management** - Using arrays to represent game state
- **Click Event Handling** - Responding to user touch interactions
- **Game Logic Implementation** - Win/lose/draw detection algorithms
- **UI State Management** - Updating interface based on game state
- **Turn-Based Logic** - Managing alternating player turns
- **Score Tracking** - Persistent data across game sessions
- **Image Resource Management** - Dynamic ImageView updates

## 🔧 Algorithm Details

### Win Detection
The app checks for winning combinations by:
1. **Row Check** - Scanning each horizontal line
2. **Column Check** - Scanning each vertical line  
3. **Diagonal Check** - Scanning both diagonal lines
4. **Pattern Matching** - Looking for three identical non-zero values

### Game Flow
1. Player taps empty cell → Place symbol
2. Check for win condition → End game or continue
3. Check for draw condition → End game or continue
4. Switch turns → Update UI and continue
5. Reset game → Clear board, maintain scores

## 🔧 Future Enhancements

- **AI Opponent** - Single player mode against computer
- **Difficulty Levels** - Easy, medium, hard AI opponents
- **Animation Effects** - Smooth animations for moves and wins
- **Sound Effects** - Audio feedback for moves and wins
- **Custom Themes** - Different visual styles and colors
- **Game Statistics** - Track wins, losses, draws over time
- **Multiplayer Network** - Online play with friends
- **Larger Grids** - 4x4, 5x5 game variations

## 🐛 Troubleshooting

### Common Issues
- **Cell Not Responding**: Ensure you're tapping empty cells only
- **Game Not Ending**: Win detection works automatically - no manual checking needed
- **Score Not Updating**: Scores update after each completed game
- **Reset Not Working**: Reset button clears board but maintains scores

### Debug Tips
- Only empty cells (value 0) can be clicked
- Game automatically detects wins and draws
- Use reset button to start new games
- Scores persist until app is closed

## 📄 License

This project is developed for educational purposes as part of the MAD Internship program.

---

**Note**: This is a local multiplayer game designed for two players sharing one device.
