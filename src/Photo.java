import java.util.Set;

public class Photo implements Comparable<Photo> {
    private boolean horizontal;
    private Set<String> tags;
    private int id;

    public Photo(boolean horizontal, int id, Set<String> tags) {
        this.horizontal = horizontal;
        this.id = id;
        this.tags = tags;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public Set<String> getTags() {
        return tags;
    }

    public int getId() {
        return id;
    }


    public void addTag(String tag) {
        tags.add(tag);
    }


    @Override
    public int compareTo(Photo o) {
        return Integer.compare(this.tags.size(), o.tags.size());
    }
}
