package metro;


import java.util.LinkedList;

public class MetroMap {
    private LinkedList<Station> map;


    public MetroMap() {
        map = new LinkedList<>();
    }

    public LinkedList<Station> getMap() {
        return map;
    }

    public void add(String name) {
        Station station = new Station(name);
        if (map.size() > 0) {
            Station last = getTail();
            last.setNext(station);
        }
        map.add(station);

    }

    int getSize() {
        return map.size();
    }

    Station getHead() {
        return map.getFirst();
    }

    Station getTail() {
        return map.getLast();
    }
}

