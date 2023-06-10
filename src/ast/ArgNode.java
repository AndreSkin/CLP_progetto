package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;

import java.util.ArrayList;

public class ArgNode implements Node{
    private TypeNode type;
    private String id;

    public ArgNode(TypeNode t, String id) {
        this.type = t ;
        this.id = id;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {

        ArrayList<SemanticError> errors = new ArrayList<SemanticError>();
        if (e.getSymbolTable().topLookup(id) != null)
            errors.add(new SemanticError("Variabile" + id + " già dichiarata per la funzione."));
        else {
            e.getSymbolTable().insert(id, type,e.getOffset()) ;
            e.incrementOffset();
        }
        // TODO: 6/7/23 Da fare qui o nella chiamata di funzione?
        e.getSymbolTable().lookup(id).setInitialized();
        return errors ;
    }

    public TypeNode getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        return null;
    }

    @Override
    public String toPrint(String s) {
        return this.type+" "+this.id;
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
