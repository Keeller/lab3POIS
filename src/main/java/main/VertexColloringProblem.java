package main;

import io.jenetics.EnumGene;
import io.jenetics.Genotype;
import io.jenetics.IntegerChromosome;
import io.jenetics.engine.Codec;
import io.jenetics.engine.Problem;
import io.jenetics.engine.Codecs;
import io.jenetics.util.ISeq;
import io.jenetics.util.IntRange;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class VertexColloringProblem<V,E> implements Problem<ISeq<VertexColoringAlgorithm.Coloring>, EnumGene<VertexColoringAlgorithm.Coloring>, Integer> {

    private Graph<V,E> graph;
    private ISeq<VertexColoringAlgorithm.Coloring> colorings;
    private Integer size;

    private static final int BAD_COST=100;

    public VertexColloringProblem(Graph<V, E> graph, ISeq<VertexColoringAlgorithm.Coloring> colorings, Integer size) {
        this.graph = graph;
        this.colorings = colorings;
        this.size = size;
    }




    @Override
    public Function<ISeq<VertexColoringAlgorithm.Coloring>, Integer> fitness() {
        return s->s.stream().mapToInt(x->ColloringHelpers.getCost(x,graph)).min().orElseThrow();
    }

    @Override
    public Codec<ISeq<VertexColoringAlgorithm.Coloring>, EnumGene<VertexColoringAlgorithm.Coloring>> codec() {
        return Codecs.ofSubSet(colorings,size);
    }
}
