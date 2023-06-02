package ast;

import semanticanalysis.Environment;

public class NotExpNode extends ExpNode{

    public NotExpNode(Node e) {
        super(e);
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        if(exp.typeCheck(e).getType() != "bool"){
            System.out.println("Operatore non booleano.");
        }
        return new TypeNode("bool");
    }
}
