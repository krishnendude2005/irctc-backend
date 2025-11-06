package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        // there can be multiple users that's why we are mapping it with List of users
        // if we want to map it with single user - we can do it like this => user = objectMapper.readValue(users, User.class);
    }

    public Boolean loginUser() {
        Optional<User> loggedUser = userList.stream().filter(user1 -> user1.getName().equals(user.getName()) && user1.getHashedPassword().equals(user.getHashedPassword())).findFirst();
        return  loggedUser.isPresent();
    }
    public void signUpUser(User user) throws IOException {

    }

}
