import java.util.HashMap;

class UndergroundSystem {

    HashMap<Integer, String> idToCheckIn;
    HashMap<Integer, String> idToCheckOut;
    HashMap<Integer, Integer> idToTime;
    public UndergroundSystem() {
        idToCheckIn = new HashMap<>();
        idToCheckOut = new HashMap<>();
        idToTime = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        if (!idToCheckIn.containsKey(id)) {
            idToCheckIn.put(id, stationName);
            idToTime.put(id, t);
        }
    }

    public void checkOut(int id, String stationName, int t) {
        if (idToCheckIn.containsKey(id)) {
            idToCheckOut.put(id, stationName);
            idToTime.put(id, t - idToTime.get(id));
        }
    }

    public double getAverageTime(String startStation, String endStation) {
        double totalTime = 0;
        double numOfPeople = 0;
        for (Integer id : idToCheckIn.keySet()) {
            if (idToCheckIn.get(id).equals(startStation) && idToCheckOut.containsKey(id)
                    && idToCheckOut.get(id).equals(endStation)) {
                totalTime += idToTime.get(id);
                numOfPeople += 1;
            }
        }
        return numOfPeople == 0 ? 0 : totalTime / numOfPeople;
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */