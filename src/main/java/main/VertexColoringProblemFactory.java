package main;

import io.jenetics.util.ISeq;
import main.Interface.IVertexColoringFactory;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.Collection;

public class VertexColoringProblemFactory implements IVertexColoringFactory {


    public   <V,E>  VertexColloringProblem<V,E> build(Graph<V,E> graph,
                                                    Collection<VertexColoringAlgorithm.Coloring<V>> colorings,
                                                    int size
    ){
        return new VertexColloringProblem<V,E>(graph, ISeq.of(colorings),size);
    }



}
