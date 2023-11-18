import java.util.*;

public class SlideShowBuilder {
    private final PriorityQueue<Photo> photoQueue;
    private final PhotoProcessor photoProcessor = new PhotoProcessor();
    private Map<PhotoProcessor.Pair<Integer, Integer>, Integer> prevouslyComputedPairs = new HashMap<>();

    SlideShowBuilder(PriorityQueue<Photo> queue) {
        this.photoQueue = queue;
    }

    public void processImages() {
        int count = 1;
        Photo photo = photoQueue.poll();
        boolean isVertical = !photo.isHorizontal();
        do {
            Set<Photo> common = photoProcessor.photosWithCommonTags(photo, isVertical);


       } while (!photoQueue.isEmpty());
    }
}
