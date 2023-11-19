import java.util.HashSet;
import java.util.Objects;
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

    public static int calculateInterestFactor(Set<String> photo1, Set<String> photo2) {
        Set<String> commonTags = new HashSet<>(photo1);
        commonTags.retainAll(photo2);

        Set<String> uniqueInPhoto1 = new HashSet<>(photo1);
        uniqueInPhoto1.removeAll(photo2);

        Set<String> uniqueInPhoto2 = new HashSet<>(photo2);
        uniqueInPhoto2.removeAll(photo1);

        return Math.min(commonTags.size(), Math.min(uniqueInPhoto1.size(), uniqueInPhoto2.size()));
    }

    protected static Set<String> mergeTags(Set<String> tags1, Set<String> tags2) {
        Set<String> mergedTags = new HashSet<>(tags1);
        mergedTags.addAll(tags2);
        return mergedTags;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return horizontal == photo.horizontal && id == photo.id && Objects.equals(tags, photo.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(horizontal, tags, id);
    }

    @Override
    public int compareTo(Photo o) {
        return Integer.compare(this.tags.size(), o.tags.size());
    }
}
