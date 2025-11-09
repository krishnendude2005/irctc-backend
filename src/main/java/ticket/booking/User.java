package ticket.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String name;
    private String password;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userID;

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userID) {
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.ticketsBooked = ticketsBooked;
        this.userID = userID;
    }
    public User(){}
    // Getters
    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return ticketsBooked;
    }

    public String getUserID() {
        return userID;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.ticketsBooked = ticketsBooked;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void PrintTicketsBooked(){
        for(int i=0;i<ticketsBooked.size();i++){
            System.out.println(ticketsBooked.get(i).toString());
        }
    }

}
