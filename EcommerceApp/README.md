# EcommerceApp 🛒

A comprehensive e-commerce application with dual-portal architecture (Admin & Customer) built on Firebase backend with complete product management and shopping functionality.

## 📱 Features

### Admin Portal
- **Product Management**: Add, edit, and delete products
- **Inventory Control**: Manage product stock and pricing
- **Order Management**: View and process customer orders
- **Analytics Dashboard**: Sales and product performance insights
- **Category Management**: Organize products by categories

### Customer Portal
- **Product Browsing**: Browse products by categories
- **Shopping Cart**: Add/remove items with quantity control
- **Order Placement**: Complete purchase workflow
- **User Profile**: Manage customer account information
- **Order History**: View past purchases and order status
- **Search & Filter**: Find products easily

### Common Features
- **Firebase Authentication**: Secure user login system
- **Real-time Updates**: Live product and order synchronization
- **Responsive Design**: Material Design UI components
- **Session Management**: Persistent login state

## 🛠️ Technologies Used

- **Language**: Java
- **Backend**: Firebase (Auth, Firestore)
- **Authentication**: Firebase Auth
- **Database**: Firebase Firestore
- **UI**: CardView, RecyclerView, Material Design
- **Image Loading**: Firebase Storage (optional)
- **Min SDK**: API 21+

## 📁 Project Structure

```
EcommerceApp/
├── app/
│   ├── src/main/
│   │   ├── java/com/avnishgamedev/ecommerceapp/
│   │   │   ├── MainActivity.java
│   │   │   ├── LoginActivity.java
│   │   │   ├── AdminAddProductActivity.java
│   │   │   ├── CustomerViewActivity.java
│   │   │   ├── CartActivity.java
│   │   │   └── Product.java (model)
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml
│   │   │   │   ├── activity_login.xml
│   │   │   │   ├── activity_admin_add_product.xml
│   │   │   │   ├── activity_customer_view.xml
│   │   │   │   ├── activity_cart.xml
│   │   │   │   └── item_product.xml
│   │   │   └── values/
│   │   └── AndroidManifest.xml
│   └── build.gradle
└── README.md
```

## 🔧 Implementation Details

### Core Components

#### MainActivity.java
- **Portal Selection**: Choose between Admin and Customer portals
- **Authentication Check**: Verify user login status
- **Navigation Hub**: Route to appropriate portal based on user role
- **Session Management**: Handle user authentication state

#### AdminAddProductActivity.java
- **Product Creation**: Form for adding new products
- **Product Management**: Edit and update existing products
- **Image Upload**: Product image handling
- **Inventory Management**: Stock quantity and pricing controls
- **Category Assignment**: Organize products by categories

#### CustomerViewActivity.java
- **Product Catalog**: Display all available products
- **Cart Integration**: Add products to shopping cart
- **Search Functionality**: Find products by name/category
- **Product Details**: Detailed product information display
- **User Experience**: Smooth browsing and purchasing flow

#### CartActivity.java
- **Cart Management**: View and modify cart contents
- **Quantity Control**: Increase/decrease product quantities
- **Total Calculation**: Real-time cart total updates
- **Checkout Process**: Complete purchase workflow
- **Order Placement**: Save orders to Firebase

### Firebase Integration

#### Firestore Database Structure
```
products/
  └── {productId}/
      ├── name: String
      ├── description: String
      ├── price: Number
      ├── category: String
      ├── stock: Number
      ├── imageUrl: String
      └── timestamp: Timestamp

orders/
  └── {orderId}/
      ├── userId: String
      ├── products: Array
      ├── totalAmount: Number
      ├── status: String
      ├── timestamp: Timestamp
      └── customerInfo: Map

users/
  └── {userId}/
      ├── name: String
      ├── email: String
      ├── role: String (admin/customer)
      └── address: String
```

## 🎯 Learning Objectives

- E-commerce application architecture
- Firebase Firestore complex queries
- User role management and access control
- Shopping cart implementation
- Order management system
- Product catalog design
- Payment workflow simulation
- Admin panel development
- Real-time data synchronization

## 🚀 Getting Started

### Firebase Setup
1. Create a Firebase project
2. Enable Authentication (Email/Password)
3. Create Firestore database with appropriate rules
4. Add Android app to Firebase project
5. Download and add `google-services.json`
6. Configure security rules for admin/customer access

### Running the App
1. Open project in Android Studio
2. Ensure Firebase configuration is complete
3. Run on emulator or device
4. Create user account or sign in
5. Choose between Admin or Customer portal
6. Start managing products or shopping

## 📋 Required Permissions

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🔄 App Flow

### Admin Flow
1. **Login**: Authenticate as admin user
2. **Dashboard**: Access admin portal
3. **Add Products**: Create new product listings
4. **Manage Inventory**: Update stock and pricing
5. **View Orders**: Process customer orders

### Customer Flow
1. **Login**: Authenticate as customer
2. **Browse**: View product catalog
3. **Add to Cart**: Select desired products
4. **Cart Review**: Modify cart contents
5. **Checkout**: Complete purchase process

## 🛡️ Security Features

- **Role-based Access**: Separate admin and customer permissions
- **Firebase Rules**: Server-side security rules
- **Input Validation**: Client-side form validation
- **Authentication Required**: All operations require login

## 🎨 UI Components

- **Portal Cards**: Admin and Customer portal selection
- **Product Grid**: RecyclerView with product cards
- **Shopping Cart**: Cart items with quantity controls
- **Form Inputs**: Product creation and editing forms
- **Navigation**: Intuitive app navigation flow

## 🔄 Future Enhancements

- **Payment Integration**: Real payment gateway integration
- **Order Tracking**: Real-time order status updates
- **Product Reviews**: Customer rating and review system
- **Wishlist**: Save products for later purchase
- **Recommendations**: AI-powered product suggestions
- **Multi-vendor Support**: Multiple seller support
- **Inventory Alerts**: Low stock notifications
- **Sales Analytics**: Detailed sales reporting
- **Discount System**: Coupon and promotional codes
- **Multi-language Support**: Internationalization

## 🐛 Common Issues

- **Firebase Config**: Ensure proper Firebase setup
- **Network Issues**: Check internet connectivity
- **Authentication**: Verify Firebase Auth configuration
- **Data Loading**: Check Firestore security rules
- **Cart Persistence**: Ensure cart data is properly saved

## 📊 Admin Features

- Product CRUD operations
- Order management dashboard
- Sales analytics and reporting
- Inventory tracking
- Customer management

## 🛍️ Customer Features

- Product browsing and search
- Shopping cart management
- Order placement and tracking
- User profile management
- Purchase history

---

**Part of MAD Internship - Android Development Portfolio**
