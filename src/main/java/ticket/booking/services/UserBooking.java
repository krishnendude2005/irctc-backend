package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Ticket;
import ticket.booking.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class UserBooking {
    private User user;
    private List<User> userList;

    private static final String USERS_PATH = "../localdb/users";
    private ObjectMapper objectMapper = new ObjectMapper();

    // create a constructor for - initializing a logged user and making the user global level so anyone can access the user
    public UserBooking(User user) throws IOException {
        this.user = user;
        File users = new File(USERS_PATH);
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
    }

    public Boolean loginUser() {
        Optional<User> loggedUser = userList.stream().filter(user1 -> user1.getName().equals(user.getName()) && user1.getHashedPassword().equals(user.getHashedPassword())).findFirst();
        if (loggedUser.isPresent()) {
            System.out.println("Login Successful");
            return true;
        } else {
            System.out.println("Login Failed");
        }
        return false;
    }

    public Boolean signUpUser(User user) {
        userList.add(user);
        try {
            saveUserToFile();
            System.out.println("Signup Successful");
            return Boolean.TRUE;
        }catch(Exception e) {
            System.out.println("SignUp Failed");
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

    public void saveUserToFile() throws IOException {
        File userFile = new File(USERS_PATH);
        objectMapper.writeValue(userFile, userList);

    }

    public void fetchBooking() {
        this.user.PrintTicketsBooked();
    }
    public boolean cancelBooking(String ticketId) throws IOException {
        // Step 1: Find the ticket in the logged-in user's bookings
        Optional<Ticket> ticketToCancel = user.getTicketsBooked()
                .stream()
                .filter(ticket -> ticket.getTicketID().equals(ticketId))
                .findFirst();

        // Step 2: If found, remove safely
        if (ticketToCancel.isPresent()) {
            // removeIf safely removes without concurrent modification
            boolean removed = user.getTicketsBooked().removeIf(ticket -> ticket.getTicketID().equals(ticketId));

            if (removed) {
                System.out.println("Cancellation Successful for TicketID: " + ticketId);
                saveUserToFile();  // Save only after a successful removal
                return true;
            }
        }

        // Step 3: If not found
        System.out.println("No booking found for TicketID: " + ticketId);
        return false;
    }


}

//NOTE :
// there can be multiple users that's why we are mapping it with List of users
// if we want to map it with single user - we can do it like this => user = objectMapper.readValue(users, User.class);
