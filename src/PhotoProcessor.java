import java.util.*;
import java.util.function.Predicate;

public class PhotoProcessor {
    private Map<String, List<Photo>> tagIndex = new HashMap<>();

    public static int compare(Photo o1, Photo o2) {
        Set<String> common = new HashSet<>(o1.getTags());
        common.retainAll(o2.getTags()); // Intersection

        Set<String> uniqueIno1 = new HashSet<>(o1.getTags());
        uniqueIno1.removeAll(o2.getTags()); // Unique in o1

        Set<String> uniqueIno2 = new HashSet<>(o2.getTags());
        uniqueIno2.removeAll(o1.getTags()); // Unique in o2

        return Math.max(common.size(), Math.max(uniqueIno1.size(), uniqueIno2.size()));
    }

    public void indexPhoto(Photo photo) {
        for (String tag : photo.getTags()) {
            tagIndex.computeIfAbsent(tag, k -> new ArrayList<>()).add(photo);
        }
    }


    public Set<Photo> photosWithCommonTags(Photo photo, boolean horizontal) {
        Set<Photo> matchingPhotos = new HashSet<>();
        for (String tag : photo.getTags()) {
           List<Photo> photosWithSameTag = tagIndex.get(tag);

           if (!horizontal) {
               Predicate<Photo> remove = Photo::isHorizontal;
               photosWithSameTag.removeIf(remove);
           }

           if (Objects.nonNull(photosWithSameTag)) {
                matchingPhotos.addAll(photosWithSameTag);
           }
        }

        matchingPhotos.remove(photo);
        return matchingPhotos;
    }

    private static List<Photo> getPhotos(Set<Photo> matchingPhotos) {
        List<Photo> output = new ArrayList<>(matchingPhotos);
        Comparator<Photo> photoComparator = PhotoProcessor::compare;

        output.sort(photoComparator);
        return output;
    }

    public static class Pair<T, T1> {
        T first;
        T1 second;


        public Pair(T first, T1 second) {
            this.first = first;
            this.second = second;
        }



        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair<T, T1> pair = (Pair<T, T1>) o;

            return (Objects.equals(first, pair.first) && Objects.equals(second, pair.second)) ||
                    (Objects.equals(first, pair.second) && Objects.equals(second, pair.first));
        }

        @Override
        public int hashCode() {
            // Use a combination of both elements for the hash code
            return Objects.hash(first, second) + Objects.hash(second, first);
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public T1 getSecond() {
            return second;
        }

        public void setSecond(T1 second) {
            this.second = second;
        }
    }



}
