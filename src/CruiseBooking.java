import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// 11-04-2023 Divyanshu Jain 102097010  PATHLOCK  Due Date: 2023-04-13  CruiseBooking.java
public class CruiseBooking {
	
	//private File DATA_FILE_PATH = new File("Cruise_Data.csv");
	
		private List<Customer> customers;   //List of customers || 11-04-2023 Divyanshu Jain 102097010
	
	public void run() {
		customers = new ArrayList<Customer>();  //Initialize list of customers
		Scanner scanner = new Scanner(System.in);
		boolean exit = false;
		while (!exit) { //Display menu
			System.out.println("================MAIN MENU ==============");
			System.out.println("[1] Read customer data into list from file");
			System.out.println("[2] Display all customer id and names from list");
			System.out.println("[3] Search for customer in list");
			System.out.println("[4] Allow customer to book cruise");
			System.out.println("[5] View customers who booked cruise on a particular day");
			System.out.println("[6] Save customers to file using date of cruise as file name");
			System.out.println("[7] Exit");
			System.out.print("Enter your option: ");
			
			int option = 0;
			try {
				option = scanner.nextInt(); //Get user input
			} catch (InputMismatchException e) {    //Check if input is a number
				System.out.println("Invalid input. Please enter a number between 1 and 7.");
				scanner.nextLine();
				continue;
			}
			
			switch (option) {
				
				case 1 -> readCustomerDataFromFile(); //Read customer data into list from file || 11-04-2023 Divyanshu Jain 102097010
				case 2 -> displayCustomerIdsAndNames(); //Display id and name of all customers || 11-04-2023 Divyanshu Jain 102097010
				case 3 -> searchCustomerByName();   //Search for customer  || 11-04-2023 Divyanshu Jain 102097010
				case 4 -> allowCustomerToBookCruise();  //Allow customer to book cruise || 11-04-2023 Divyanshu Jain 102097010
				case 5 -> viewCustomersBookedOnParticularDay(); //View customer who booked cruise for a particular day || 11-04-2023 Divyanshu Jain 102097010
				case 6 -> saveCustomersToFile();    //Save customer’s list to file using date of cruise as file name   || 11-04-2023 Divyanshu Jain 102097010
				case 7 -> exit = true;  //Exit
				default -> System.out.println("Invalid option. Please enter a number between 0 and 6.");
				
			}
			
		}
		
		scanner.close();
		
	}
	
	private void readCustomerDataFromFile() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter full path of file like: src/Cruise_Data.csv ");
		System.out.print("Enter the name of the file: ");
		
		String fileName = scanner.nextLine();
		File file = new File(fileName); //Read customer data into list from file  ||  11-04-2023 Divyanshu Jain 102097010
		if (!file.exists()) {   //Check if file exists
			System.out.println("File does not exist.");
			return;
		}
		
		try (Scanner fileScanner = new Scanner(file)) {
			while (fileScanner.hasNextLine()) { //Read each line of the file
				String line = fileScanner.nextLine();
				String[] data = line.split(","); //Split the line into an array of strings || 11-04-2023 Divyanshu Jain 102097010
				if(data[0].equals("CustID")) continue; //Skip the first line (column headers) ||  11-04-2023 Divyanshu Jain 102097010
				Customer customer = new Customer(data[0], data[1], data[2], data[3], data[4]);
				customers.add(customer);
			}
		} catch (FileNotFoundException e) { //Catch exception if file not found || 11-04-2023 Divyanshu Jain 102097010
			System.out.println("File does not exist.");
		}
		
		System.out.println("Number of records read: " + customers.size());
		
	}
	
	private void displayCustomerIdsAndNames() {
		for (Customer customer : customers) {   //Display id and name of all customers   ||  11-04-2023 Divyanshu Jain 102097010
            System.out.println(customer.getCustID() + " " + customer.getName());
        }
		
	}
	
	private void searchCustomerByName() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the name of the customer: ");
		String name = scanner.nextLine();
		for (Customer customer : customers) {
			if (customer.getName().contains(name)) {    //Check if customer name contains the search term   ||  11-04-2023 Divyanshu Jain 102097010
				System.out.println(customer.getCustID() + " " + customer.getName());    //Display customer id and name   ||  11-04-2023 Divyanshu Jain 102097010
			}
			else {
				System.out.println("Customer not found.");
			}
		}
		
	}

	private void allowCustomerToBookCruise() {
		String CustomerID = "C00" + (customers.size()  + 1);
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the name of the customer: ");
		String name = scanner.nextLine();
		System.out.print("Enter the email of the customer: ");
		String email = scanner.nextLine();
		System.out.print("Enter the date of the cruise: ");
		String date = scanner.next();
		String bookingDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")); //Get current date   ||  11-04-2023 Divyanshu Jain 102097010
		Customer customer = new Customer(CustomerID,name, email, bookingDate, date);    //Create new customer
		customers.add(customer);    //Add customer to list
//		System.out.println("Booking successful.");
//		System.out.println("Booking details sent to " + email);
		try {
            sendEmail(customer);    //Send email to customer   ||  11-04-2023 Divyanshu Jain 102097010
        } catch (Exception e) {
            System.out.println("Error sending email.");
        }
				
	}
	
	private void sendEmail(Customer customer) {
		//Send email to customer
		System.out.println("Sending email to " + customer.getEmailID());
		System.out.println("Dear " + customer.getName() + ",");
		System.out.println("Thank you for booking a cruise with us.");
		System.out.println("Your booking details are as follows:");
		System.out.println("Customer ID: " + customer.getCustID());
		System.out.println("Booking Date: " + customer.getBookingDate());
		System.out.println("Date of Cruise: " + customer.getDateOfCruise());
		System.out.println("We look forward to seeing you on board.");
		System.out.println("Regards,");
		System.out.println("Cruise Booking Team");
		
	}
	
	private void viewCustomersBookedOnParticularDay() {  //View customer who booked cruise for a particular day   ||  11-04-2023 Divyanshu Jain 102097010
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the date of the cruise: ");
		String date = scanner.nextLine();
		for (Customer customer : customers) {
			if ( customer.getDateOfCruise().equals(date)) {//Check if customer date of cruise is equal to the date entered   ||  11-04-2023 Divyanshu Jain 102097010
				System.out.println(customer);
			}
			else {
				System.out.println("No customers booked on this date.");
			}
			
		}
		
	}

	private void saveCustomersToFile() {
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the file: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName); //Create new file
        try {
            PrintWriter fileWriter = new PrintWriter(file); //Create new file writer   ||  11-04-2023 Divyanshu Jain 102097010
            fileWriter.println("CustID,Name,Emailid,BookingDate,CruiseDate"); //Write column headers to file   ||  11-04-2023 Divyanshu Jain 102097010
            for (Customer customer : customers) {   //Write each customer to the file   ||  11-04-2023 Divyanshu Jain 102097010
                fileWriter.println(customer.getCustID() + "," + customer.getName() + "," + customer.getEmailID() + "," + customer.getBookingDate() + "," + customer.getDateOfCruise()); //Write customer to file   ||  11-04-2023 Divyanshu Jain 102097010
            }
            fileWriter.close(); //Close file writer   ||  11-04-2023 Divyanshu Jain 102097010
            System.out.println("Saved " + customers.size() + " customers to file.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
		
	}
	
	
}
