import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PhotoImporter {
    public static void importPhotos(String filePath, PhotoProcessor processor) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            int id = 0;

            // file starts with no of photos
            line = reader.readLine();
            int noOfPhotos = Integer.parseInt(line);

            for (int i = 0; i < noOfPhotos; i++) {
                line = reader.readLine();
                String[] parts = line.split(" ");
                boolean isHorizontal = parts[0].equals("h");
                Set<String> tags = new HashSet<String>(Arrays.asList(parts).subList(2, parts.length));
                Photo photo = new Photo(isHorizontal, i, tags);
                processor.indexPhoto(photo);
            }

        } catch (IOException e) {
            throw new RuntimeException(e + ": Unable to import document");
        }
    }
}
