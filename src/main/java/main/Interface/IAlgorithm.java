package main.Interface;

import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.List;

public interface IAlgorithm<V> {
     VertexColoringAlgorithm.Coloring eval(List<List<V>> graphConfig);
}
