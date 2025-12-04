package model;

import dao.DbOperations;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author acer
 */
public class GlobalVariables {
    // Define global variables as public static variables
    public static int TotalSaleOnline = 0;
    public static int TotalSaleCash = 0;
    public static int TotalSale = 0;
    public static Date BillDate;
    
    // Method to update or insert total sales data
    public static void updateOrInsertTotalSale() {
        // Correctly format the date
        SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yy");
        Date date = new Date();
        String todayDate = dFormat.format(date);
        
        // Check if the BillDate matches today's date
        if (BillDate != null && date==BillDate) {
            // Create the SQL update query
            String query = "UPDATE total SET onlinePayment='" + TotalSaleOnline + "', cashPayment='" + TotalSaleOnline+TotalSaleCash + "', totalSale='" + TotalSale + "' WHERE date='" + date + "'";
            DbOperations.setDataOrDelete(query, "Successfully updated!");
        } else {
            // If it's a new day, reset the sales and insert a new record
            SimpleDateFormat dFormate = new SimpleDateFormat("dd-MM-yy");
            Date datee = new Date();
            TotalSaleOnline = 0;
            TotalSaleCash = 0;
            TotalSale = 0;

            // Create the SQL insert query
            String query = "INSERT INTO total(date, onlinePayment, cashPayment, totalSale) VALUES ('" + datee + "', '" + TotalSaleOnline + "', '" + TotalSaleCash + "', '" + TotalSale + "')";
            DbOperations.setDataOrDelete(query, "Successfully inserted!");
        }
    }
}
