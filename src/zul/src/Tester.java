package zul.src;

import zul.src.GraphImplementation.Graph;
import zul.src.GraphComponent.Vertex;

public class Tester {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex(new Vertex(86,22,0,0));
        graph.addVertex(new Vertex(29,17,1,1));
        graph.addVertex(new Vertex(4,50,8,2));
        graph.addVertex(new Vertex(25,13,6,3));
        graph.addVertex(new Vertex(67,37,5,4));

        graph.addAllEdge();

        graph.printAllVertexEdges();
    }
}
