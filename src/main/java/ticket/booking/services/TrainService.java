package ticket.booking.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ticket.booking.Train;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private Train train;
    private static final String LOCALDB_PATH = "localdb";

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
        System.out.println("------------------Absolute path: " + trains.getAbsolutePath()); // debug logic
        trainsList = objectMapper.readValue(trains, new TypeReference<List<Train>>() {
        });
    }

    public void saveTrains() throws IOException {
        File trains = new File(LOCALDB_PATH + "/trains.json");
        objectMapper.writeValue(trains, trainsList);
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

    public int getSeatsAavailable(String trainID) throws IOException {
        Train desiredTrain = null;
        // 1. Find the desired train
        Optional<Train> optionalTrain = trainsList.stream().filter(train1 -> train1.getTrainID().equals(trainID)).findFirst();
        if (optionalTrain.isPresent()) {
            desiredTrain = optionalTrain.get();
        }

        // 2. Find no of seats available and return
        int seatsAavailable = 0;

        assert desiredTrain != null; //extra defense for safe null checking
        for (var list : desiredTrain.getSeats()) {
            for (var seat : list) {
                if (seat == 0) {
                    seatsAavailable++;
                }
            }

        }
        return seatsAavailable;
    }

    public void bookTrainTicket(String trainID) throws IOException {
        Optional<Train> trainOpt = trainsList.stream()
                .filter(t -> t.getTrainID().equals(trainID))
                .findFirst();

        if (trainOpt.isEmpty()) {
            System.out.println("Train not found!");
            return;
        }

        Train desiredTrain = trainOpt.get();

        boolean seatBooked = false;

        // Assuming getSeats() returns List<List<Integer>>
        for (List<Integer> row : desiredTrain.getSeats()) {
            for (int i = 0; i < row.size(); i++) {
                if (row.get(i) == 0) { // seat not booked
                    row.set(i, 1); // mark as booked
                    seatBooked = true;
                    break;
                }
            }
            if (seatBooked) break;
        }

        if (seatBooked) {
            saveTrains();
            System.out.println("Seat Booked Successfully!");
        } else {
            System.out.println("No available seats!");
        }
    }
}
