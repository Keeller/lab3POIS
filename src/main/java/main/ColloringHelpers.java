package main;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

public class ColloringHelpers {

    private static final int BAD_COST=100;

    private static  <V,E>  int validate(VertexColoringAlgorithm.Coloring<V> coloring, Graph<V,E> graph){
        for(V v:graph.vertexSet()){
            for (V g:graph.vertexSet()){
                if(!v.equals(g)&&graph.containsEdge(v,g)&&coloring.getColors().get(v).equals(coloring.getColors().get(g)))
                    return  coloring.getNumberColors()+BAD_COST;
            }
        }
        return 0;
    }


    public static <V,E> Integer getCost(VertexColoringAlgorithm.Coloring<V> coloring,Graph<V,E> graph){
        return coloring.getNumberColors()+validate(coloring,graph);
    }
}
