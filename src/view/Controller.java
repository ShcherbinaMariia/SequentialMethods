package view;

import formulas.Formula;
import model.Node;
import model.Sequence;
import model.SequenceTree;
import parser.Parser;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class Controller {

    SequenceTree sequenceTree;
    Parser parser = new Parser();

    private LinkedList<Formula> getFormulas(String expressions, boolean value) throws Exception {
        LinkedList<Formula> result = new LinkedList<>();
        String[] expressionsGroup = expressions.split(";");

        for (int i = 0; i < expressionsGroup.length; i++){
            if (expressionsGroup[i].isEmpty())
                continue;
            Formula formula = parser.parse(expressionsGroup[i]);
            formula.logicalValue = value;
            result.add(formula);
        }
        return result;
    }

    boolean evaluate(String left, String right) throws Exception {
        LinkedList<Formula> formulas = getFormulas(left, true);
        formulas.addAll(getFormulas(right, false));
        Sequence sequence = new Sequence(formulas);
        sequenceTree = new SequenceTree(new Node(sequence));
        return sequenceTree.evaluate();
    }

    File showResult() throws IOException {
        return sequenceTree.getPresentation("results/temp.png");
    }

    File saveResult(String filePath) throws IOException {
        File file = new File("results/" + filePath + ".png");
        return sequenceTree.getPresentation(file.getPath());
    }
}
