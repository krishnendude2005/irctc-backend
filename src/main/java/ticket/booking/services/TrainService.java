package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Train;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Train train;
    private static final String LOCALDB_PATH = "../localdb";
    private List<Train> trainsList;
    public TrainService() throws IOException {
        loadTrains();
    }
    public TrainService(Train train) throws IOException {
       this.train = train;
       loadTrains();
    }
    public void loadTrains() throws IOException {
        File trains = new File(LOCALDB_PATH + "/trains.json");
        trainsList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }
    public List<Train> searchTrains(String source, String destination) throws IOException {
        return trainsList.stream().filter(train1 -> validateTrain(train1, source, destination)).toList();
    }
    public boolean validateTrain(Train train, String source, String destination) {
      List<String> stationOrder = train.getStations();
      int sourceIndex = stationOrder.indexOf(source.toLowerCase());
      int destinationIndex = stationOrder.indexOf(destination.toLowerCase());

      return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
    public int getSeatsAavailable(Train train) throws IOException {
        List<List<Integer>> allSeats = train.getSeats();
        int seatsAavailable = 0;
        for(List<Integer> seats : allSeats) {
            for(int seat : seats) {
                if(seat == 0) {
                    seatsAavailable++;
                }
            }
        }
        return seatsAavailable;
    }
}
