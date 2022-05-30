package metro;


import java.util.HashMap;
import java.util.Map;

public class MetroMap {
    private Map<String, Line> metroMap;


    public MetroMap() {
        metroMap = new HashMap<>();
    }


    public void append(String LineName, String stationName) {
        Line line = getLine(LineName);
        line.add(stationName);

    }

    public void addHead(String LineName, String stationName) {
        Line line = getLine(LineName);
        line.addFirst(stationName);
    }

    public String output(String lineName) {
        Line line = metroMap.get(lineName);
        return line.toString();
    }

    private Line getLine(String LineName) {
        createLineIfNecessary(LineName);
        Line line = metroMap.get(LineName);
        return line;
    }

    private void createLineIfNecessary(String lineName) {
        if (metroMap.get(lineName) == null) {
            metroMap.put(lineName, new Line());
            Line line = metroMap.get(lineName);
            line.initiateLine();
        }
    }

    public void remove(String lineName, String station) {
        Line line = metroMap.get(lineName);
        line.remove(station);
    }

//    int getSize() {
//        return map.size();
//    }
//
//    Station getHead() {
//        return map.getFirst();
//    }
//
//    Station getTail() {
//        return map.getLast();
//    }
}

