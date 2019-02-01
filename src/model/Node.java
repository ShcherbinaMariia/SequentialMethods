package model;

import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.model.MutableNode;

import static guru.nidi.graphviz.model.Factory.mutNode;

public class Node {
    Sequence value;
    Node left = null;
    Node right = null;
    int MAX_DEPTH = 10;

    public Node(Sequence value) {
        this.value = value;
    }

    public boolean evaluate(int k) throws Exception {

        //System.out.println(getPresentation());
        if (k > MAX_DEPTH)
            return false;
        if (value == null)
            return false;

        if (value.isClosed())
            return true;

        if (value.isLeaf())
                return false;

        this.transform();

        if (this.equals(left)) return false;

        if (right != null) {
            return left.evaluate(k + 1) && right.evaluate(k + 1);
        } else
            if (left!=null)
                return left.evaluate(k + 1);
        return false;
    }

    public void transform() throws Exception {

        Sequence[] result = value.transform();
        this.left = new Node(result[0]);
        if (result.length == 2) {
            this.right = new Node(result[1]);
        }
    }


    public MutableNode getPresentation() {

        MutableNode newNode = mutNode(value.getPresentation());
        if (value.isClosed())
            newNode.add(Color.GREEN);
        else if  (value.isLeaf())
             newNode.add(Color.RED);
        if (left != null) newNode.addLink(left.getPresentation());
        if (right != null) newNode.addLink(right.getPresentation());
        return newNode;
    }
}