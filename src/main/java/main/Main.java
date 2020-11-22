package main;

import org.jgrapht.Graph;
import org.jgrapht.alg.interfaces.VertexColoringAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        List<List<String>> result=new ArrayList<>();
        List<String> ww=new ArrayList<>();
        ww.add("v1");
        ww.add("v2");
        result.add(new ArrayList<>(ww));
        ww.clear();
        ww.add("v2");
        ww.add("v3");
        result.add(new ArrayList<>(ww));
        ww.clear();
        ww.add("v2");
        ww.add("v4");
        result.add(new ArrayList<>(ww));
        ww.clear();
        ww.add("v2");
        ww.add("v5");
        result.add(new ArrayList<>(ww));
        ww.clear();
        Algorithm<String> algorithm=new Algorithm<>(100,50);
        VertexColoringAlgorithm.Coloring eval = algorithm.eval(result);
        Map<String,Integer> colors = (Map<String, Integer>)eval.getColors();
        for (Map.Entry<String, Integer> stringIntegerEntry : colors.entrySet()) {
            System.out.println(stringIntegerEntry.getKey()+" -> "+stringIntegerEntry.getValue());
        }
    }
}
