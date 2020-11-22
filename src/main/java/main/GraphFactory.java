package main;

import main.Interface.IGraphFactory;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;

import org.jgrapht.graph.builder.GraphBuilder;
import org.jgrapht.graph.builder.GraphTypeBuilder;

import java.util.List;
import java.util.Map;

public class GraphFactory<V> implements IGraphFactory<V> {

    private  Graph<V,DefaultEdge> constructSimpleGraph(){
        return GraphTypeBuilder
                .<V,DefaultEdge>undirected()
                .allowingMultipleEdges(false)
                .allowingSelfLoops(false)
                .edgeClass(DefaultEdge.class)
                .weighted(false)
                .buildGraph();
    }

    public  Graph<V,DefaultEdge> buildGraph(List<List<V>> edges){
        GraphBuilder<V, DefaultEdge, Graph<V, DefaultEdge>> stringDefaultEdgeGraphGraphBuilder = new GraphBuilder<>(constructSimpleGraph());
        edges.forEach(x->stringDefaultEdgeGraphGraphBuilder.addEdge(x.get(0),x.get(1)));
        return stringDefaultEdgeGraphGraphBuilder.buildAsUnmodifiable();
    }
}
