package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Train;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TrainService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Train train;
    private static final String LOCALDB_PATH = "../localdb";

    public TrainService() throws IOException {
        loadTrains();
    }
    public TrainService(Train train) throws IOException {
       this.train = train;
       loadTrains();
    }
    public List<Train> loadTrains() throws IOException {
        File trains = new File(LOCALDB_PATH + "/trains.json");
        return objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }
    public List<Train> searchTrains(String source, String destination) throws IOException {
        List<Train> trains = loadTrains();
        for (Train train : trains) {
            if(train.getStations().contains(source) && train.getStations().contains(destination)) {
                trains.add(train);
            }
        }
        return trains;
    }
}
