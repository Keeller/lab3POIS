package main.Interface;

import main.VertexColloringProblem;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.Collection;

public interface IVertexColoringFactory {

    <V,E> VertexColloringProblem<V,E> build(Graph<V,E> graph, Collection<VertexColoringAlgorithm.Coloring<V>> colorings, int size);
}
