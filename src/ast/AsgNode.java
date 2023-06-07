package ast;

import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class AsgNode implements Node{

    private String id;
    private Node exp;

    public AsgNode(String id, Node exp) {
        this.id = id;
        this.exp = exp;
    }
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        SymbolTableEntry st = e.getSymbolTable().lookup(id);
        if(st == null){
            throw new Error2("Variabile "+this.id+" non dichiarata nell'ambiente corrente.");
        } else if(this.exp != null) {
            res.addAll(this.exp.checkSemantics(e));
        }
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        SymbolTableEntry st = e.getSymbolTable().lookup(id);
        if (st == null) {
            throw new Error();
        }
        if(!st.getType().getType().equals(this.exp.typeCheck(e).getType())){
            throw new Error2("Errore di TypeChecking: Espressioni incompatibili durante l'assegnamento.");
        }
        //Controllo se assegno una funzione?

        e.getSymbolTable().lookup(id).setInitialized();
        return exp.typeCheck(e);
    }

    @Override
    public String toPrint(String s) {
        return s + "Var:" + id + "\n" + exp.toPrint(s+"\t");
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
