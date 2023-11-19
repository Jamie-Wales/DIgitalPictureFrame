import java.util.*;

public class SlideShowBuilder {
    private final PriorityQueue<Photo> photoQueue;
    private final PhotoProcessor photoProcessor = new PhotoProcessor();

    SlideShowBuilder(PriorityQueue<Photo> queue) {
        this.photoQueue = queue;
    }
}
