package main;

import io.jenetics.*;
import io.jenetics.engine.Engine;
import io.jenetics.engine.EvolutionResult;
import io.jenetics.engine.Limits;
import io.jenetics.util.ISeq;
import main.Interface.IAlgorithm;
import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;
import org.jgrapht.graph.DefaultEdge;

import java.util.*;
import java.util.stream.Collectors;

public class Algorithm<V> implements IAlgorithm<V> {

    private VertexColoringProblemFactory vertexColoringProblemFactory;
    private GraphFactory<V> graphFactory;
    private Integer generationSize;
    private Integer uniqColors;
    private static final int BAD_COST=100;
    private Integer maxGeneration;


    public Algorithm(Integer generationSize,Integer maxGeneration) {
        vertexColoringProblemFactory=new VertexColoringProblemFactory();
        graphFactory=new GraphFactory<>();
        this.generationSize=generationSize;
        this.maxGeneration=maxGeneration;
    }

    private  Engine<EnumGene<VertexColoringAlgorithm.Coloring>,Integer> buildEngine(VertexColloringProblem<V,DefaultEdge> problem){
        return Engine.builder(problem)
                .maximizing()
                .maximalPhenotypeAge(generationSize)
                .alterers(new PartiallyMatchedCrossover<>(0.4), new Mutator<>(0.3))
                .build();
    }

    public VertexColoringAlgorithm.Coloring eval(List<List<V>> graphConfig){

       Graph<V, DefaultEdge> stringDefaultEdgeGraph = graphFactory.buildGraph(graphConfig);
       List<VertexColoringAlgorithm.Coloring<V>> colorings=new ArrayList<>();
       for(int i=0;i<generationSize;i++) {
           Map<V, Integer> map = generateRandomColoring(stringDefaultEdgeGraph.vertexSet().size(), stringDefaultEdgeGraph);
           VertexColoringAlgorithm.ColoringImpl<V> vColoring = new VertexColoringAlgorithm.ColoringImpl<V>(map,uniqColors);
           colorings.add(vColoring);
       }
        VertexColloringProblem<V, DefaultEdge> build = vertexColoringProblemFactory.build(stringDefaultEdgeGraph, colorings, colorings.size());
        Engine<EnumGene<VertexColoringAlgorithm.Coloring>, Integer> enumGeneIntegerEngine = buildEngine(build);

        return build.codec()
                .decoder()
                .apply(enumGeneIntegerEngine.stream()
                        .limit(Limits.bySteadyFitness(maxGeneration))
                        .collect(EvolutionResult.toBestGenotype()))
                .stream()
                .filter(x->ColloringHelpers.getCost(x,stringDefaultEdgeGraph)<BAD_COST)
                .min((o1, o2) -> ColloringHelpers.getCost(o1,stringDefaultEdgeGraph).compareTo(ColloringHelpers.getCost(o2,stringDefaultEdgeGraph)))
                .orElseThrow();




    }

    private Map<V,Integer> generateRandomColoring(int coloringSize,Graph<V,DefaultEdge> graph){
        Map<V,Integer> res=new HashMap<>();
        Set<V> vs = graph.vertexSet();
        Random random=new Random();
        uniqColors=0;
        for (V v : vs) {
            int color=random.nextInt(coloringSize);
            if(!res.containsValue(color))
                uniqColors++;
            res.put(v,color);
        }
        return res;

    }


}
