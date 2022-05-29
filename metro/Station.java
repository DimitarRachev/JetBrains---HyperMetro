package metro;

public class Station {
   private String name;
   private Station next;

    public Station(String name) {
        this.name = name;
    }

    public void setNext(Station next) {
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public Station getNext() {
        return next;
    }
}
