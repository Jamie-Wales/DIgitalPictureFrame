import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class PhotoImporter {
    public static void importPhotos(String filePath, PriorityQueue<Photo> queue, PhotoProcessor processor) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int id = 0;

            line = reader.readLine();
            int noOfPhotos = Integer.parseInt(line);

            for (int i = 0; i < noOfPhotos - 1; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                boolean isHorizontal = parts[0].equals("H");
                Set<String> tags = new HashSet<String>(Arrays.asList(parts).subList(2, parts.length));
                Photo photo = new Photo(isHorizontal, i, tags);
                queue.add(photo);
                processor.indexPhoto(photo);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
