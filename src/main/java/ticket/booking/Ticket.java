package ticket.booking;

import java.util.Date;

public class Ticket {
    private String ticketID;
    private String userID;
    private String source;
    private String destination;
    private Date dateOfJourney;
    private Train train;

    // Parameterized constructor
    public Ticket(String ticketID, String userID, String source, String destination, Date dateOfJourney, Train train) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.source = source;
        this.destination = destination;
        this.dateOfJourney = dateOfJourney;
        this.train = train;
    }

    // Getters
    public String getTicketID() {
        return ticketID;
    }

    public String getUserID() {
        return userID;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public Date getDateOfJourney() {
        return dateOfJourney;
    }

    public Train getTrain() {
        return train;
    }

    // Setters
    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateOfJourney(Date dateOfJourney) {
        this.dateOfJourney = dateOfJourney;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    // toString() for easy debugging
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", userID='" + userID + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", dateOfJourney=" + dateOfJourney +
                ", train=" + (train != null ? train.getTrainNumber() : "N/A") +
                '}';
    }

    public String getTicketInfo() {
        return String.format(
                "Ticket Details:\n" +
                        "------------------------\n" +
                        "Ticket ID     : %s\n" +
                        "User ID       : %s\n" +
                        "Source        : %s\n" +
                        "Destination   : %s\n" +
                        "Date of Journey: %s\n" +
                        "Train         : %s\n",
                this.ticketID,
                this.userID,
                this.source,
                this.destination,
                this.dateOfJourney != null ? this.dateOfJourney.toString() : "N/A",
                this.train != null ? this.train.toString() : "N/A"
        );
    }

}
