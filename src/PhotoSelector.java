import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PhotoSelector {
    private final Graph graph;
    private final MaxSpanningTree maxSpanningTree;
    private final Photo root;

    public PhotoSelector(Photo root, Graph graph) {
        this.graph = graph;
        this.maxSpanningTree = new MaxSpanningTree(graph);
        this.root = root;
    }

    public List<Photo> selectPhotos() {
        List<Photo> selectedPhotos = new ArrayList<>();

        // Generate the maximum spanning tree
        Set<Edge> maxTree = maxSpanningTree.maximumSpanningTree(root);

        // Choose a starting point (this can vary based on your requirement)
        Photo currentPhoto = root;
        selectedPhotos.add(root);
        boolean lastPhotoWasVertical = false;
        boolean twoVerticalInARow = false;

        // Traverse the max spanning tree
        for (int i = 0; i < graph.adjVertices.size(); i++) {
            boolean requireVertical = lastPhotoWasVertical && !twoVerticalInARow;

            Photo nextPhoto = graph.findNextPhoto(currentPhoto, requireVertical);

            if (nextPhoto != null && !selectedPhotos.contains(nextPhoto)) {
                selectedPhotos.add(nextPhoto);
                // Update the flags based on the orientation of the current photo
                if (!nextPhoto.isHorizontal()) {
                    if (lastPhotoWasVertical) {
                        twoVerticalInARow = true; // Two vertical photos in a row
                    }
                    lastPhotoWasVertical = true;
                } else {
                    lastPhotoWasVertical = false;
                    twoVerticalInARow = false; // Reset as the current photo is horizontal
                }
                currentPhoto = nextPhoto;
            } else if (selectedPhotos.size() != maxTree.size()) {
                for (Edge edge : maxTree) {
                    if (!selectedPhotos.contains(edge.getDestination())) {
                        currentPhoto = edge.getDestination();
                        if (currentPhoto.isHorizontal()) {
                            if (twoVerticalInARow) {
                                selectedPhotos.add(currentPhoto);
                                lastPhotoWasVertical = false;
                            }
                        } else {
                            twoVerticalInARow = !twoVerticalInARow;

                            selectedPhotos.add(currentPhoto);
                            lastPhotoWasVertical = true;
                        }
                    }
                }
            } else {
                break;
            }
        }

        return selectedPhotos;
    }


    public static void printSlideshowDetails(List<Photo> selectedPhotos) {
        int totalScore = 0;
        System.out.println("Slideshow Details:");

        for (int i = 0; i < selectedPhotos.size(); i++) {
            Photo currentPhoto = selectedPhotos.get(i);
            Set<String> currentTags = currentPhoto.getTags();

            // Handle the case for vertical photo pairs
            if (!currentPhoto.isHorizontal() && i + 1 < selectedPhotos.size() && !selectedPhotos.get(i + 1).isHorizontal()) {
                Photo nextPhoto = selectedPhotos.get(i + 1);
                currentTags = Photo.mergeTags(currentPhoto.getTags(), nextPhoto.getTags());
                System.out.println("Slide " + i + ": Photo IDs " + currentPhoto.getId() + ", " + nextPhoto.getId() + " - " + currentTags);
                i++; // Skip the next photo as it's already included in this slide
            } else {
                System.out.println("Slide " + i + ": Photo ID " + currentPhoto.getId() + " - " + currentTags);
            }

            // Calculate the interest factor if there's a next slide
            if (i + 1 < selectedPhotos.size()) {
                Set<String> nextSlideTags;
                Photo nextPhoto = selectedPhotos.get(i + 1);

                // If the next photo is part of a vertical pair, merge their tags
                if (!nextPhoto.isHorizontal() && i + 2 < selectedPhotos.size() && !selectedPhotos.get(i + 2).isHorizontal()) {
                    nextSlideTags = Photo.mergeTags(selectedPhotos.get(i + 1).getTags(), selectedPhotos.get(i + 2).getTags());
                    i++; // Skip the next photo as it's already accounted for in the merged tags
                } else {
                    nextSlideTags = nextPhoto.getTags();
                }

                int interestFactor = Photo.calculateInterestFactor(currentTags, nextSlideTags);
                totalScore += interestFactor;
                System.out.println("Interest Factor for transition to next slide: " + interestFactor);
            }
        }

        System.out.println("Total Score: " + totalScore);
    }



}