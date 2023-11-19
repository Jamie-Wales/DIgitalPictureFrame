import java.util.Comparator;
import java.util.Objects;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Comparator<Photo> comp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }

            public int compare(Photo o1, Photo o2) {

                return Integer.compare(o1.getTags().size(), o2.getTags().size());
            }

        };

        PriorityQueue<Photo> queue = new PriorityQueue<>(comp.reversed());
        PhotoProcessor processor = new PhotoProcessor();
        PhotoImporter.importPhotos("./assets/data.txt", queue, processor);
        Graph graph = processor.buildGraph();
        PhotoSelector photoSelector = new PhotoSelector(queue.poll(), graph);

        PhotoSelector.printSlideshowDetails(photoSelector.selectPhotos());
    }
}
