package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Ticket;
import ticket.booking.Train;
import ticket.booking.User;
import ticket.booking.utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBooking {
    private User user;
    private List<User> userList;

    private static final String LOCALDB_PATH = "../localdb";
    private ObjectMapper objectMapper = new ObjectMapper();

    // create a constructor for - initializing a logged user and making the user global level so anyone can access the user
    public UserBooking(User user) throws IOException {
        this.user = user;
        loadUsers();
    }

    public UserBooking() throws IOException {
        loadUsers();
    }

    public List<User> loadUsers() throws IOException {
        File users = new File(LOCALDB_PATH + "/users.json");
        userList = objectMapper.readValue(users, new TypeReference<List<User>>() {
        });
        return userList;
    }

    public void saveUserToFile() throws IOException {
        File userFile = new File(LOCALDB_PATH + "/users.json");
        objectMapper.writeValue(userFile, userList);

    }

    // Features a user can use

    public Boolean loginUser() {
        Optional<User> loggedUser = userList.stream().filter(user1 -> user1.getName().equals(user.getName()) && UserServiceUtil.checkPassword(user.getPassword(), user1.getHashedPassword())).findFirst();
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
        } catch (Exception e) {
            System.out.println("SignUp Failed");
            e.printStackTrace();
        }
        return Boolean.FALSE;
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

    public List<Train> searchTrains(String source, String destination) throws IOException {
        TrainService ts = new TrainService();
        return ts.searchTrains(source, destination);
    }

}

//NOTE :
// there can be multiple users that's why we are mapping it with List of users
// if we want to map it with single user - we can do it like this => user = objectMapper.readValue(users, User.class);
