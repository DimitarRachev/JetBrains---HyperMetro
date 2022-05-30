package metro;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MetroMap metroMap = new MetroMap();
//TODO NEED TO FIX JSON CONVERSION commands are working correctly
        if (args.length > 0) {

            Gson gson = new Gson();
            File file = new File(args[0]);

            if (!file.isFile()) {
                System.out.println("Error! Such a file doesn't exist!");
                System.exit(0);
            } else {
                try {
                    ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
                    Object o = in.readObject();
                    Object json = gson.toJson(o);
                    try {
                        metroMap = (MetroMap) json;
                    } catch (Exception e) {
                        System.out.println("Incorrect file");
                        System.exit(0);
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
//        List<String> input = null;
//        try {
//            input = Files.readAllLines(file.toPath());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (input.size() == 0) {
//            System.exit(0);
//        }


        while (true) {
            List<String> tokens = getCommandTokens(scanner.nextLine());
            String command = tokens.get(0);
            String line;
            String station;

            switch (command) {
                case "/append":
                    line = tokens.get(1);
                    station = tokens.get(2);
                    metroMap.append(line, station);
                    break;
                case "/add-head":
                    line = tokens.get(1);
                    station = tokens.get(2);
                    metroMap.addHead(line, station);
                    break;
                case "/remove":
                    line = tokens.get(1);
                    station = tokens.get(2);
                    metroMap.remove(line, station);
                    break;
                case "/output":
                    line = tokens.get(1);
                    String output = metroMap.output(line);
                    System.out.println(output);
                    break;
                case "/exit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        }

    }

    private static List<String> getCommandTokens(String command) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean hasFoundQuotes = false;
        for (char c : command.toCharArray()) {
            if (c == '"' && !hasFoundQuotes) {
                hasFoundQuotes = true;
            } else if (c == '"' && hasFoundQuotes) {
                result.add(sb.toString());
                sb.setLength(0);
                hasFoundQuotes = false;
            } else if (c == ' ' && !hasFoundQuotes) {
                result.add(sb.toString());
                sb.setLength(0);
            } else {
                sb.append(c);
            }
        }

        if (sb.length() > 0) {
            result.add(sb.toString());
        }
        return result;
    }
}
