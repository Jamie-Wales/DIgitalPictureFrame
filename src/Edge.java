public class Edge {
    private final Photo destination;
    private int weight;

    public Edge(Photo destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    public void incrementWeight(int additionalWeight) {
        this.weight += additionalWeight;
    }

    public Photo getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }
}
