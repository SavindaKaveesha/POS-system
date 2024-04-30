![image](https://github.com/SavindaKaveesha/billng-system/assets/115203716/ce840296-9ad2-4349-94e0-1a26250557f9)


# Point of Sale (POS) System

This is a Java-based Point of Sale (POS) system with a graphical user interface (GUI) developed using Java Swing. The POS system allows users to manage sales transactions, calculate totals, generate bills, and perform other related operations.

## Features

- **Database Integration:** Uses MySQL database for storing and retrieving product information.
- **User-Friendly Interface:** GUI-based application for easy navigation and interaction.
- **Sales Management:** Add, update, and delete items from sales transactions.
- **Billing:** Generate bills with detailed information about products, quantities, prices, and totals.

## Technical Details

- **Programming Language:** Java
- **GUI Framework:** Java Swing
- **Database:** MySQL
- **Database Connectivity:** JDBC (Java Database Connectivity)
- **IDE:** Developed in NetBeans IDE

## Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/pos-system.git
   ```

2. **Import Project:**
   - Open the project in your preferred Java IDE (e.g., NetBeans, Eclipse).
   - Make sure Java Development Kit (JDK) is installed.

3. **Database Setup:**
   - Create a MySQL database for the POS system.
   - Import the provided SQL script (`pos_system.sql`) into your database.

4. **Configure Database Connection:**
   - Open `DBconnect.java` file and update the database connection details (e.g., URL, username, password).

5. **Build and Run:**
   - Build the project in your IDE.
   - Run the application to start the POS system.

## Usage

1. Launch the application.
2. Enter product details such as product code, name, quantity, and price.
3. Add products to the sales table.
4. Calculate the total and generate bills.
5. Manage sales transactions and perform other operations as needed.

## Contributing

Contributions are welcome! If you find any bugs or have suggestions for improvements, please open an issue or create a pull request.
