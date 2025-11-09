package ticket.booking;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Train {
    private String trainID;
    private String trainNumber;
    private Date departure;
    private Date arrival;
    private List<List<Integer>> seats;
    private Map<String, String> stationTimes;
    private List<String> stations;

    public Train() {}
    // Parameterized constructor
    public Train(String trainID, String trainNumber, Date departure, Date arrival,
                 List<List<Integer>> seats, Map<String, String> stationTimes, List<String> stations) {
        this.trainID = trainID;
        this.trainNumber = trainNumber;
        this.departure = departure;
        this.arrival = arrival;
        this.seats = seats;
        this.stationTimes = stationTimes;
        this.stations = stations;
    }

    // Getters
    public String getTrainID() {
        return trainID;
    }

    public String getTrainNumber() {
        return trainNumber;
    }

    public Date getDeparture() {
        return departure;
    }

    public Date getArrival() {
        return arrival;
    }

    public List<List<Integer>> getSeats() {
        return seats;
    }

    public Map<String, String> getStationTimes() {
        return stationTimes;
    }

    public List<String> getStations() {
        return stations;
    }

    // Setters
    public void setTrainID(String trainID) {
        this.trainID = trainID;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }

    public void setStationTimes(Map<String, String> stationTimes) {
        this.stationTimes = stationTimes;
    }

    public void setStations(List<String> stations) {
        this.stations = stations;
    }

}
