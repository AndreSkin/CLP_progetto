package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class AsgNode implements Node{

    private String id;
    private Node exp;
    private SymbolTableEntry st;
    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();
        this.st = e.getSymbolTable().lookup(id);
        if(st == null){
            System.out.println("Variabile non dichiarata nell'ambiente corrente.");
            throw new Error();
        } else if(this.exp != null) {
            res.addAll(this.exp.checkSemantics(e));
        }
        return res;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        if (st == null) {
            throw new Error();
        }
        if(st.getType().getType() != exp.typeCheck(e).getType()){
            System.out.println("Espressioni incompatibili.");
            throw new Error();
        }
        //Controllo se assegno una funzione?

        st.setInitialized();
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
