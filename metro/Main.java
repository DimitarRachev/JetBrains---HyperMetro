package metro;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File(args[0]);
        if (!file.isFile()) {
            System.out.println("Error! Such a file doesn't exist!");
            System.exit(0);
        }

        List<String> input = Files.readAllLines(file.toPath());

        if (input.size() == 0) {
            System.exit(0);
        }

        MetroMap metroMap = new MetroMap();

        metroMap.add("depot");
        input.forEach(metroMap::add);
        metroMap.add("depot");

        Station current = metroMap.getMap().getFirst();

        for (int i = 0; i < metroMap.getSize() - 2; i++) {
            Station second = current.getNext();
            Station third = second.getNext();
            System.out.println(current.getName() + " - " + second.getName()
            + " - " + third.getName());
            current = current.getNext();
        }

    }
}
