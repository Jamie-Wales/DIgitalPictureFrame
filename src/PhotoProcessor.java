import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhotoProcessor {

    private final Map<String, List<Photo>> tagIndex = new HashMap<>();


    public Photo selectRoot() {
        Photo root = null;
        int maxTags = -1;

        for (String tag : tagIndex.keySet()) {
            for (Photo photo : tagIndex.get(tag)) {
                int tagCount = photo.getTags().size();
                if (tagCount > maxTags) {
                    maxTags = tagCount;
                    root = photo;
                }
            }
        }
        return root;
    }


    public void indexPhoto(Photo photo) {
        for (String tag : photo.getTags()) {
            tagIndex.computeIfAbsent(tag, k -> new ArrayList<>()).add(photo);
        }
    }


    public Graph buildGraph() {
        Graph graph = new Graph();
        for (String tag : tagIndex.keySet()) {
            List<Photo> elements = tagIndex.get(tag);

            for (Photo element : elements) {
                graph.addVertex(element);

            }
            for (int i = 0; i < elements.size() - 1; i++) {
                graph.updateEdge(elements.get(i), elements.get(i + 1), 1);
            }
        }

        return graph;
    }

}
