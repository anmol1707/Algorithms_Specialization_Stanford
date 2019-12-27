package Course1.Week4;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContractedVertex {
    private List<Integer> vertices;

    public ContractedVertex() {
        this.vertices = new ArrayList<>();
    }

    public void addVertex(Integer vertex) {
        this.vertices.add(vertex);
    }

    public void addVertices(List<Integer> newVertices) {
        this.vertices.addAll(newVertices);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContractedVertex)) return false;
        ContractedVertex that = (ContractedVertex) o;
        return vertices.equals(that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }

    public List<Integer> getVertices() {
        return vertices;
    }
}
