import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        PriorityQueue<Photo> queue = new PriorityQueue<>(Comparator.comparing(t -> t.getTags().size()));
        PhotoProcessor processor = new PhotoProcessor();
        PhotoImporter.importPhotos("./assets/data.txt", queue, processor);


    }
}
