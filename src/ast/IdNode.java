package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.Status;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class IdNode implements Node{
    private String id;

    public IdNode(String id) {
        this.id = id;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> result = new ArrayList<SemanticError>();
        SymbolTableEntry s= e.getSymbolTable().lookup(this.id);
        if (s==null)
            result.add(new SemanticError("Variable non dichiarata."));
        return result;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error{
        SymbolTableEntry s= e.getSymbolTable().lookup(this.id);
        if(s == null) {
            //non dichiarata
            System.out.println("Variabile non dichiarata.");
            throw new Error();
        }
        //Qui sto facendo typecheck di quando la variabile occorre come termine di una exp, quindi qui devo controllare l'inizializzazione
        if (s.getStatus() == Status.DECLARED) {
            System.out.println("Uso di variabile dichiarata ma non inizializzata.");
            throw new Error();
        }
        //se volessi implementare anche le usate e non usate, l'update va messo qui
        return s.getType();
    }

    @Override
    public String toPrint(String s) {
        return s+this.id;
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }

    public String getId() {
        return this.id;
    }
}
