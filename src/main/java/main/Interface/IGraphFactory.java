package main.Interface;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public interface IGraphFactory <V>{
    public Graph<V,DefaultEdge> buildGraph(List<List<V>> edges);
}
