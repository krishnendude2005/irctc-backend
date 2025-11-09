package ticket.booking;

import ticket.booking.services.UserBooking;
import ticket.booking.utils.UserServiceUtil;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

//List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//List<Integer> l1 = list.stream().filter(isEven()).toList();
//List<Integer> ls = list.stream().map(apply()).toList();
//        for(Integer i : l1){
//        System.out.println(i);
//        }
//public static Predicate <Integer> isEven() {
//    return i -> i%2 == 0;
//}
//        static Function<Integer, Integer> apply() {
//            return i -> i*i;
//        }

public class Main {
    public static void main(String[] args) {
//        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        System.out.println("------------------------IRCTC---------------------");

        Scanner sc = new Scanner(System.in);

        int choice = 0;
        UserBooking userBookingServcie ;


        while (choice != 7) {
            System.out.println("Choose an option:");
            System.out.println("1. Login");
            System.out.println("2. SignUp");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Search Train");
            System.out.println("6. Get Seat Availability");
            System.out.println("7. Book Ticket");
            System.out.println("8. Exit");
            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    System.out.println("Chosen Option : Login");
                    System.out.println("Enter Username:");
                    String username = sc.nextLine();
                    System.out.println("Enter Password:");
                    String password = sc.nextLine();
                    User userToLogin = new User(username, password, UserServiceUtil.hashedPassword(password), new ArrayList<>(), UUID.randomUUID().toString());

                    try {
                        userBookingServcie = new UserBooking(userToLogin);
                        userBookingServcie.loginUser();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 2:
                    System.out.println("Chosen Option : SignUp");
                    System.out.println("Enter Username:");
                    String nameSignup = sc.nextLine();
                    System.out.println("Enter Password:");
                    String passwordSignup = sc.nextLine();
                    User userToSignup = new User(nameSignup, passwordSignup, UserServiceUtil.hashedPassword(passwordSignup), new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingServcie = new UserBooking(userToSignup);
                        userBookingServcie.signUpUser(userToSignup);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case 3:
                    System.out.println("Chosen Option : Fetch Booking");
                    if(UserBooking.getUser() == null) {
                        System.out.println("Login First to Fetch Booking");
                        break;
                    }
                    UserBooking.fetchBooking();
                    break;

                case 4:
                    System.out.println("Chosen Option : Cancel Booking");
                    System.out.println("Enter the TicketID for the Train you want to cancel");
                    String trainID = sc.nextLine();
                    try {
                        userBookingServcie = new UserBooking();
                        userBookingServcie.cancelBooking(trainID);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                case 5:
                    System.out.println("Chosen Option : Search Train");
                    System.out.println("Enter the Source Station");
                    String sourceStation = sc.nextLine();
                    System.out.println("Enter Destination Station");
                    String destinationStation = sc.nextLine();
                    try{
                        userBookingServcie = new UserBooking();
                        userBookingServcie.searchTrains(sourceStation, destinationStation);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                case 6:
                    System.out.println("Chosen Option : Get Seat Availability");
                    System.out.println("Enter the Train ID for the Train you want to get");
                    String trainId = sc.nextLine();
                    try {
                        userBookingServcie = new UserBooking();
                        userBookingServcie.getAvailableSeats(trainId);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

                case 7:
                    System.out.println("Chosen Option : Book Ticket");
                    System.out.println("Enter the Train ID for the Train you want to book");
                    String trainIdToBook = sc.nextLine();
                    try {
                        userBookingServcie = new UserBooking();
                        userBookingServcie.bookTicket(trainIdToBook);
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }

            }
        }

    }
}