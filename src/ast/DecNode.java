package ast;

import java.util.ArrayList;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

public class DecNode implements Node {
    private String id;
    private Node type;
    private Node exp;
    private int nesting;

    public DecNode(String _id, Node _type, Node _exp) {
        id = _id ;
        type = _type ;
        exp = _exp ;
    }

    public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        nesting = _nesting ;
        errors.addAll(exp.checkSemantics(ST, nesting));

        if (ST.top_lookup(id) == true)
            errors.add(new SemanticError("Var id " + id + " already declared"));
        else ST.insert(id, (Type) type, nesting,"") ;

        return errors ;
    }

    public Type typeCheck () {
        if (exp.typeCheck().getClass().equals(type.getClass() ))
            return null ;
        else {
            System.out.println("Type Error: incompatible type of expression for variable "+id) ;
            return new ErrorType() ;
        }
    }

    public String codeGeneration() {
        return exp.codeGeneration() +
                "pushr A0 \n" ;
    }

    public String toPrint(String s) {
        return s + "Var:" + id + type.toPrint(" ") + "\n" + exp.toPrint(s+"\t");
    }

}