# ExpenseTracker 💰

A personal finance management application for tracking income and expenses with categorization, real-time calculations, and persistent local storage.

## 📱 Features

- **Income/Expense Tracking**: Add and categorize financial transactions
- **Transaction Categories**: Organize expenses by type (Food, Transport, etc.)
- **Real-time Balance**: Live calculation of total balance
- **Transaction History**: View all past income and expenses
- **Data Persistence**: Local storage using SharedPreferences and JSON
- **Income vs Expense Toggle**: Easy switching between transaction types
- **Running Totals**: Track cumulative income, expenses, and balance
- **User-friendly Interface**: Clean and intuitive design

## 🛠️ Technologies Used

- **Language**: Java
- **Storage**: SharedPreferences with JSON serialization
- **UI**: RecyclerView, RadioGroup, Material Design
- **Data Format**: JSON (Gson library)
- **Architecture**: Local data persistence
- **Min SDK**: API 21+

## 📁 Project Structure

```
ExpenseTracker/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/expensetracker/
│   │   │   ├── MainActivity.java
│   │   │   ├── Transaction.java (model)
│   │   │   └── TransactionAdapter.java
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   └── item_transaction.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Transaction Entry**: Form for adding new income/expense items
- **Category Selection**: Radio buttons for transaction categories
- **Balance Calculation**: Real-time financial balance updates
- **Data Management**: Save/load transactions using SharedPreferences
- **List Management**: Display transactions in RecyclerView

#### Transaction.java (Model Class)
- **Data Structure**: Transaction properties (amount, type, category, date)
- **JSON Serialization**: Convert to/from JSON for storage
- **Data Validation**: Ensure transaction data integrity
- **Category Management**: Predefined expense categories

#### TransactionAdapter.java
- **RecyclerView Adapter**: Display transactions in list format
- **View Binding**: Efficient view holder pattern
- **Data Formatting**: Format currency and date display
- **Visual Indicators**: Different styling for income vs expenses

### Key Features

#### Transaction Types
- **Income**: Money coming in (salary, freelance, etc.)
- **Expense**: Money going out (food, transport, entertainment, etc.)

#### Categories
- Food & Dining
- Transportation
- Entertainment
- Shopping
- Bills & Utilities
- Healthcare
- Other

#### Financial Calculations
- **Total Income**: Sum of all income transactions
- **Total Expenses**: Sum of all expense transactions
- **Current Balance**: Income minus expenses
- **Real-time Updates**: Instant calculation updates

## 🎯 Learning Objectives

- Local data persistence with SharedPreferences
- JSON serialization and deserialization
- RecyclerView implementation with custom adapters
- Real-time calculations and UI updates
- Form validation and user input handling
- Financial data management
- Material Design principles
- Data model design for financial applications

## 🚀 Getting Started

1. Open the project in Android Studio
2. Run on emulator or physical device
3. Select transaction type (Income/Expense) using radio buttons
4. Enter transaction amount and select category
5. Add transaction to see it in the list
6. View real-time balance updates

## 📋 No Special Permissions Required

This app uses only local storage, so no additional permissions are needed.

## 🔄 App Flow

1. **Launch**: Load existing transactions from local storage
2. **Add Transaction**: 
   - Select Income or Expense
   - Enter amount
   - Choose category
   - Save transaction
3. **View Balance**: See updated total balance
4. **Transaction History**: Browse all past transactions
5. **Data Persistence**: All data saved locally for future sessions

## 💡 Data Structure

### Transaction Model
```java
public class Transaction {
    private String type;        // "Income" or "Expense"
    private double amount;      // Transaction amount
    private String category;    // Transaction category
    private String date;        // Transaction date
    private long timestamp;     // For sorting
}
```

### Storage Format
```json
{
  "transactions": [
    {
      "type": "Income",
      "amount": 1000.0,
      "category": "Salary",
      "date": "2024-01-15",
      "timestamp": 1642204800000
    },
    {
      "type": "Expense",
      "amount": 50.0,
      "category": "Food",
      "date": "2024-01-15",
      "timestamp": 1642208400000
    }
  ]
}
```

## 🎨 UI Components

- **RadioGroup**: Income/Expense selection
- **EditText**: Amount input field
- **Spinner/RadioButtons**: Category selection
- **Button**: Add transaction action
- **RecyclerView**: Transaction history list
- **TextViews**: Balance display (Income, Expense, Total)

## 🔄 Future Enhancements

- **Budget Planning**: Set monthly/weekly budgets
- **Charts & Analytics**: Visual spending analysis
- **Export Data**: CSV/PDF export functionality
- **Recurring Transactions**: Automatic monthly bills
- **Multiple Accounts**: Track different bank accounts
- **Receipt Scanner**: OCR for receipt data entry
- **Cloud Sync**: Backup to cloud storage
- **Spending Alerts**: Notifications for budget limits
- **Categories Customization**: User-defined categories
- **Date Range Filtering**: View transactions by date range
- **Search Functionality**: Find specific transactions

## 🐛 Common Issues

- **Data Loss**: Ensure SharedPreferences are properly saved
- **JSON Parsing**: Check Gson library integration
- **Balance Calculation**: Verify arithmetic operations
- **UI Updates**: Ensure RecyclerView notifies data changes

## 📊 Financial Insights

The app provides immediate visibility into:
- **Spending Patterns**: Where money is being spent
- **Income Tracking**: All sources of income
- **Net Worth**: Overall financial position
- **Category Analysis**: Spending by category

## 🎯 Use Cases

- **Personal Finance**: Individual expense tracking
- **Budget Management**: Stay within spending limits
- **Financial Planning**: Understand spending habits
- **Tax Preparation**: Organize financial records
- **Savings Goals**: Track progress toward financial goals

---

**Part of MAD Internship - Android Development Portfolio**
