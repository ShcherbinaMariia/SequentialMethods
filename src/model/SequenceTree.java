package model;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.MutableGraph;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.mutGraph;

public class SequenceTree {

    Node root;

    public SequenceTree(Node root){
        this.root = root;
    }

    public boolean evaluate() throws Exception {
        return root.evaluate(0);
    }

    public File getPresentation(String filePath) throws IOException {

        MutableGraph g = mutGraph("SequentialTree").setDirected(true).add(root.getPresentation());
        File resultFile = new File(filePath);
        Graphviz.fromGraph(g).width(2000).render(Format.PNG).toFile(resultFile);
        return resultFile;
    }
}
