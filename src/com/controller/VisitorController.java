package com.controller;

import com.Model.User;
import com.Model.Visitor;
import com.service.VisitorService;
import com.util.Helper;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VisitorController {
	//private final MasterController masterController=new MasterController();

    private final VisitorService visitorService = new VisitorService();
    @SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
    public void createVisitor(User user,String apr) throws SQLException, ClassNotFoundException {
    	String contact,arrivalDate,depDate,time2,time;
    	Time depTime;
    	Date arrivalDate2,depDate2;
    	

		Date date= new Date(System.currentTimeMillis());;
		
		//String apr;
		
       
        System.out.print("Enter visitor name: ");
        String name = scanner.nextLine();
        while(true)
        {
        	 System.out.print("Enter visitor contact: ");
        	 contact = scanner.nextLine();
        	 if(Helper.isPhoneNumberValid(contact))
        		 break;
        	 else
        		 System.out.println("Wrong Phone number ,Please try again");

        }
         while(true)
         {
        	 System.out.print("Enter visit date (yyyy-mm-dd): ");
        	 
        	 arrivalDate = scanner.nextLine();
        	 arrivalDate2 = Date.valueOf(arrivalDate);
        	 
        	 if(Helper.isValidDate(arrivalDate) && (arrivalDate2.after(date)|| arrivalDate.equals(date)))
        	 {
        		 break;
        	 }
        	 else
        		 System.out.println("Invalid date ,Please try again");

         }
        System.out.print("Enter visit purpose: ");
        String purpose = scanner.nextLine();
        while(true)
        {
        	  System.out.print("Enter visit arrival time: ");
        	   time=scanner.nextLine();
        	   if(Helper.isValidTime(time))
        		   break;
        	   else
        		   System.out.println("Incoorect time format, PLease try again");
        }
        Time arrival_time= Time.valueOf(time) ;
        while(true)
        {
        	 System.out.print("Enter departure date (yyyy-mm-dd): ");
        	 depDate = scanner.nextLine();
        	 depDate2=Date.valueOf(depDate);
       	 if(  Helper.isValidDate(depDate)  && (depDate2.after(arrivalDate2) || arrivalDate.equals(depDate)))
       	 {
       		 break;
       	 }
       	 else
       		 System.out.println("Invalid date ,Please try again");

        }
       while(true)
       {
    	   System.out.print("Enter visit departure time: ");
    	   time2=scanner.nextLine();
           depTime= Time.valueOf(time2);
           if ( Helper.isValidTime(time2)  &&arrivalDate.equals(depDate)  && depTime.after(arrival_time)) {
               break;
           } else if (   Helper.isValidTime(time2) && depDate2.after(arrivalDate2)) {
               break; // Valid case, so exit the loop
           } else {
               System.out.println("Invalid date, departure date must be after arrival date, please try again");
           }
          	  
       }   
        Visitor visitor = new Visitor();
        visitor.setIdVisitor(Helper.generateUniqueId());
        visitor.setUserId(user.getIdUser());
        visitor.setName(name);
        visitor.setContactNo(contact);
        visitor.setPurpose(purpose);
        visitor.setDate(arrivalDate);
        visitor.setArrivalTime(time);
        visitor.setDepartureTime(time2);
        visitor.setApproved(apr);
        visitor.setDep_date(depDate);
     

        visitorService.addVisitor(visitor);
        System.out.println("Visitor created successfully!");
    }

    public void viewVisitor(String userId) throws SQLException, ClassNotFoundException {
    	 List<Visitor> visitors = visitorService.getAllVisitorReq(userId,"Pending");
    	 if(visitors==null || visitors.isEmpty())
    	 {
    		 System.out.println("Visitor not found!");	
    		 return;
    	 }
    	 else
    	 {
    		 System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
                     "S.No", "Name", "Contact", "Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
			   int serialNumber = 1;
			   for (Visitor visitor : visitors) {
			       System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
			                         serialNumber++, 
                         visitor.getName(), 
                         visitor.getContactNo(), 
                         visitor.getDate(), 
                         visitor.getPurpose(), 
                         visitor.getArrivalTime(), 
                         visitor.getDepartureTime(), 
                         visitor.getDep_date(), 
                         visitor.isApproved()); 

			   }
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
    	 }
    }
    public void viewVisitorByGuard(String userId) throws SQLException, ClassNotFoundException {
   	 List<Visitor> visitors = visitorService.getAllVisitorById(userId);
   	 if(visitors==null || visitors.isEmpty())
   	 {
   		 System.out.println("Visitor not found!");	
   		 return;
   	 }
   	 else
   	 {
   		 System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
                    "S.No", "Name", "Contact", "Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
			   int serialNumber = 1;
			   for (Visitor visitor : visitors) {
			       System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
			                         serialNumber++, 
                        visitor.getName(), 
                        visitor.getContactNo(), 
                        visitor.getDate(), 
                        visitor.getPurpose(), 
                        visitor.getArrivalTime(), 
                        visitor.getDepartureTime(), 
                        visitor.getDep_date(), 
                        visitor.isApproved()); 

			   }
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
   	 }
   }



    public void listVisitors() throws SQLException, ClassNotFoundException {
        List<Visitor> visitors = visitorService.getAllVisitors();
        if(visitors==null || visitors.isEmpty())
   	 {
   		 System.out.println("Visitor not found!");	
   		 
   	 }
   	 else
   	 {
   		System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
                "S.No", "Name", "Contact", "Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
		   System.out.println("------------------------------------------------------------------------------------------------------------------"
		                    + "------------------------------------------------------");
		   int serialNumber = 1;
		   for (Visitor visitor : visitors) {
		       System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
		                         serialNumber++, 
                    visitor.getName(), 
                    visitor.getContactNo(), 
                    visitor.getDate(), // Assuming this is the arrival date
                    visitor.getPurpose(), 
                    visitor.getArrivalTime(), 
                    visitor.getDepartureTime(), 
                    visitor.getDep_date(), 
                    visitor.isApproved()); 

		   }
		   System.out.println("------------------------------------------------------------------------------------------------------------------"
		                    + "------------------------------------------------------");}
    }

    public void updateVisitor(String userId) throws SQLException, ClassNotFoundException {
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        List<Visitor> visitors = visitorService.getVisitorById(userId);
        System.out.println("Enter which visitor to update");
        viewVisitor(userId);
        int choice=0;
        try {
			choice= scanner.nextInt();
			}catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                 }
       
    	if (choice < 1 || choice > visitors.size()) {
            System.out.println("Invalid choice, please try again.");
            return;
        }
        Visitor selectedVisitor= visitors.get(choice-1);
        String visitorId=selectedVisitor.getIdVisitor();
        String str="""
        		1) Name
        		2) Contact Number
        		3) Arrival Date
        		4) Arrival Time
        		5) Departure Date
        		6) Departure time
        		7) Exit
        		""";
        System.out.println(str);
        System.out.println("Select which one to update");
        int choice2=0;
        try {
			choice2= scanner.nextInt();
			}catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
               }
        
        switch(choice2)
        {
        case 1:
        {
        	 System.out.print("Enter new name: ");
             String name = scanner.nextLine();
             visitorService.updateVisitor(visitorId, "name",name);
             System.out.println("Vistor updated");
             break;
        }
        	
        case 2:
        {
        	 System.out.print("Enter new contact: ");
          String contact = scanner.nextLine();
          visitorService.updateVisitor(visitorId, "contact",contact);
          System.out.println("Vistor updated");
        	break;
        }
        case 3:
        {
        	 System.out.print("Enter new arrival date (yyyy-mm-dd): ");
             String arrivalDate = scanner.nextLine();
             visitorService.updateVisitor(visitorId, "date_of_arrival",arrivalDate);
             System.out.println("Vistor updated");
             break;
        }
        case 4:
        {
        	System.out.print("Enter arrival Time: ");
        	 String time=scanner.nextLine();
        	 
             Time arrival_time= Time.valueOf(time) ;
             visitorService.updateVisitor(visitorId, "arrivalTime",arrival_time.toString());
             System.out.println("Vistor updated");
             break;
        }
        case 5:
        {
        	System.out.print("Enter departure date: ");
        	 String DepartureDate = scanner.nextLine();
        	 visitorService.updateVisitor(visitorId, "departure_date",DepartureDate);
        	 System.out.println("Vistor updated");
        	 break;
        }
        case 6:
        {
        	System.out.print("Enter departure time: ");
        	 String time=scanner.nextLine();
             Time departure_time= Time.valueOf(time) ;
             visitorService.updateVisitor(visitorId, "dapartureTime",departure_time.toString());
             System.out.println("Vistor updated");
             break;
        }
        case 7:
        	return;
        default:
        {
        		System.out.println("Invalid input");
        }
    }
       
}
    public void deleteVisitor(String userId) throws SQLException, ClassNotFoundException {
    	@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        List<Visitor> visitors = visitorService.getVisitorById(userId);
        System.out.println("Enter which visitor to delete");
        viewVisitor(userId);
        int choice= scanner.nextInt();
    	if (choice < 1 || choice > visitors.size()) {
            System.out.println("Invalid choice, please try again.");
            return;
        }
        Visitor selectedVisitor= visitors.get(choice-1);
        String visitorId=selectedVisitor.getIdVisitor();
    	visitorService.deleteVisitor(visitorId);
        System.out.println("Visitor deleted successfully!");
    }
   
    public void verifyVisitor(User user) throws SQLException, ClassNotFoundException {


    	List<Visitor> visitors = visitorService.getVisitorById(user.getIdUser());
        if (visitors == null || visitors.isEmpty()) {
            System.out.println("No visitors found!");
            return;
        }
        
        else
        {
        	viewVisitorByGuard(user.getIdUser());
        	  System.out.println("Enter which visitor to verify");
              int choice=0;
              		try {
              			choice= scanner.nextInt();
              			}catch (InputMismatchException e) {
                              System.out.println("Invalid input. Please enter a number.");
                              scanner.nextLine();
                              }
          	if (choice < 1 || choice > visitors.size()) {
                  System.out.println("Invalid choice, please try again.");
                  return;
              }
          	
              Visitor selectedVisitor= visitors.get(choice-1);
              String visitorId2=selectedVisitor.getIdVisitor();
              visitorService.verifyVisitor(visitorId2);
        }
      
            }

    public void pendingRequests(String userId)throws SQLException, ClassNotFoundException
    {
    	 List<Visitor> visitors = visitorService.getAllVisitorReq(userId,"Pending");
    	 if(visitors==null || visitors.isEmpty())
    	 {
    		 System.out.println("Visitor not found!");	
    		 return;
    	 }
    	 else
    	 {
    		 System.out.printf("%-5s %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
                     "S.No", "Name", "Contact", "Arrival Date", "Purpose", "Arrival Time", "Departure Time", "Departure Date", "Approval Req");
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
			   int serialNumber = 1;
			   for (Visitor visitor : visitors) {
			       System.out.printf("%-5d %-20s %-15s %-15s %-20s %-20s %-20s %-15s %-15s\n", 
			                         serialNumber++, 
                         visitor.getName(), 
                         visitor.getContactNo(), 
                         visitor.getDate(), 
                         visitor.getPurpose(), 
                         visitor.getArrivalTime(), 
                         visitor.getDepartureTime(), 
                         visitor.getDep_date(), 
                         visitor.isApproved()); 

			   }
			   System.out.println("------------------------------------------------------------------------------------------------------------------"
			                    + "------------------------------------------------------");
			    	 System.out.println("Select which visitor to approve or deny");
    	 int choice=0;
    	 try {
 			choice= scanner.nextInt();
 			}catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please enter a number.");
                 scanner.nextLine();
                 }
    	 if (choice < 1 || choice > visitors.size()) {
             System.out.println("Invalid choice, please try again.");
             return;
         }
    	 
         Visitor selectedVisitor= visitors.get(choice-1);
         String visitorId=selectedVisitor.getIdVisitor();
        // System.out.println(visitorId);

         System.out.println("Select 1 to approve");
         System.out.println("Select 2 to deny");
         int choice2=0;
         try {
 			choice2= scanner.nextInt();
 			}catch (InputMismatchException e) {
                 System.out.println("Invalid input. Please enter a number.");
                 scanner.nextLine();
                  }
        
         if(choice2==1)
         {
        	//System.out.println("choice 1 selected");

        	 visitorService.updateApprovalStatus(visitorId, "Approved");
        	 System.out.println("Request approved");	
        	
         }
         else
         {
        	// System.out.println("choice 2 selected");
        	 visitorService.updateApprovalStatus(visitorId, "Rejected");
        	 System.out.println("Request Denied");
         }
       
    	 }
    }
    public String DeleteUserByadmin() throws ClassNotFoundException, SQLException
    {
    	List<Visitor> visitors =visitorService.getAllVisitors();
    	listVisitors();
    	System.out.println("Enter user  number which you need to delete");
    	int choice=0;
    	try {
			choice= scanner.nextInt();
			}catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                 }
    	if (choice < 1 || choice > visitors.size()) {
            System.out.println("Invalid choice, please try again.");
            return null;
        }
    	Visitor selectedVisitor= visitors.get(choice - 1);
    	return selectedVisitor.getIdVisitor();
    }
   }

;