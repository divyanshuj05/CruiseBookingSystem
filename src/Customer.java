import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// 11-04-2023 Divyanshu Jain 102097010  PATHLOCK  Due Date: 2023-04-13  Customer.java
public class Customer {

    private String custID;
    private String name;
    private String emailID;
    private String bookingDate;

	private  String cruiseDate ;

    public Customer(String custID, String name, String emailID, String bookingDate, String cruiseDate) {
        if (custID == null || custID.trim().isEmpty()) {    //validate if custID is empty   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Customer ID cannot be empty");
        }
        
        this.custID = custID;
        if (name == null || name.trim().isEmpty()) {    //validate if name is empty   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Name cannot be empty");
        }
        
        this.name = name;
        if (emailID == null || emailID.trim().isEmpty()) {  //validate if emailID is empty   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Email ID cannot be empty");
        } else if (!emailID.contains("@") || !emailID.contains(".")) {  //validate if emailID is valid   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Email ID is invalid");
        }
        this.emailID = emailID;
        if (bookingDate == null || bookingDate.trim().isEmpty()) {  //validate if bookingDate is empty   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Booking Date cannot be empty");
            
        }   //validate if bookingDate is a valid date
        else if (!bookingDate.matches("\\d{2}/\\d{2}/\\d{4}")) {    //validate if bookingDate is a valid date   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Booking Date is invalid");
        }
        this.bookingDate = bookingDate;
        if (cruiseDate == null || cruiseDate.trim().isEmpty()) {    //validate if cruiseDate is empty   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Cruise Date cannot be empty");
        }
        else if (!cruiseDate.matches("\\d{2}/\\d{2}/\\d{4}")) {    //validate if cruiseDate is a valid date   ||  11-04-2023 Divyanshu Jain 102097010
            throw new IllegalArgumentException("Cruise Date is invalid");
        }
        this.cruiseDate = cruiseDate;
        
    }
		
	public String getCustID() {
        return custID;
    }

    public String getName() {
        return name;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getBookingDate() { return bookingDate; }
    
    public  String getCruiseDate() {
        return cruiseDate;
    }
  
    @Override
    public String toString() {
        return "Customer [custID=" + custID + ", name=" + name + ", emailID=" + emailID + ", bookingDate=" + bookingDate
                + ", cruiseDate=" + cruiseDate + "]";
    }
	
	public  String getDateOfCruise() {
		return cruiseDate;
	}

}
