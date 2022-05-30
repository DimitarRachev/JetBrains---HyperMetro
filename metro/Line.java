package metro;

import java.util.LinkedList;

public class Line {
    private LinkedList<Station> stationList;


    public Line() {
        stationList = new LinkedList<>();
    }

    public LinkedList<Station> getStationList() {
        return stationList;
    }

    public void add(String name) {
        Station newStation = new Station(name);

        Station tail = getTail();
        Station last = tail.getPrevious();
        stationList.removeLast();
        stationList.add(newStation);
        stationList.add(tail);
        last.setNext(newStation);
        newStation.setPrevious(last);
        newStation.setNext(tail);
        tail.setPrevious(newStation);
    }

    int getSize() {
        return stationList.size();
    }

    Station getHead() {
        return stationList.getFirst();
    }

    Station getTail() {
        return stationList.getLast();
    }

    public void addFirst(String stationName) {
        Station newStation = new Station(stationName);
        Station head = stationList.getFirst();
        Station last = head.getNext();
        stationList.removeFirst();
        stationList.addFirst(newStation);
        stationList.addFirst(head);
        head.setNext(newStation);
        newStation.setPrevious(head);
        newStation.setNext(last);
        last.setPrevious(newStation);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Station current = stationList.getFirst();

        for (int i = 0; i < stationList.size() - 2; i++) {
            Station second = current.getNext();
            Station third = second.getNext();
            sb.append(current.getName()).append(" - ").append(second.getName())
                    .append(" - ").append(third.getName());
            if (i < stationList.size() - 3) {
                sb.append(System.lineSeparator());
            }
            current = current.getNext();
        }


        return sb.toString();
    }

    public void initiateLine() {
        Station head = new Station("depot");
        Station tail = new Station("depot");
        head.setNext(tail);
        tail.setPrevious(head);
        stationList.add(head);
        stationList.add(tail);
    }

    public void remove(String stationName) {
        Station station = getStationByName(stationName);
        if (station == null) {
            //Throw some exception if necessary
        }
        Station previous = station.getPrevious();
        Station next = station.getNext();
        previous.setNext(next);
        next.setPrevious(previous);
        stationList.remove(station);
        //TODO check if it's necessary for GC to se null values
        station.setNext(null);
        station.setPrevious(null);
    }

    private Station getStationByName(String stationName) {
        for (Station station : stationList) {
            if (station.getName().equals(stationName)) {
                return station;
            }
        }
        return null;
    }
}
