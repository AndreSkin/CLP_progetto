package ast;

import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class FunCallNode implements Node{
    String id;
    ArrayList<Node> params;

    public FunCallNode(String id, ArrayList<Node> params) {
        this.id = id;
        this.params = params;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> results = new ArrayList<SemanticError>();
        if (e.getSymbolTable().lookup(this.id) == null)
            results.add(new SemanticError("Funzione " + this.id + " non dichiarata."));
        else
            if (this.params != null)
                for (Node par : params)
                    results.addAll(par.checkSemantics(e));
        return results;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        SymbolTableEntry ste = e.getSymbolTable().lookup(this.id);
        TypeNode t = ste.getType();
        if(!t.isFunction()) {
            throw new Error2("Errore durante l'invocazione. "+this.id+" non è una funzione.");
        }

        ArrayList<TypeNode> expectedParams = t.getParams();

        if(this.params.size() != expectedParams.size()) {
            throw new Error2("Errore durante l'invocazione di"+this.id+". Numero dei parametri errato.");
        }

        //Controllo dei tipi dei formali e attuali

        for(int i=0; i<this.params.size();i++) {
            if(!this.params.get(i).typeCheck(e).getType().equals(expectedParams.get(i).getType())) {
                throw new Error2("Errore durante l'invocazione di "+this.id+". Tipo errato per il "+(i+1)+" parametro.");
            }

            if(this.params.get(i).getClass() == IfExpNode.class || this.params.get(i).getClass() == IfStmNode.class) {
                throw new Error2("Errore durante l'invocazione di "+this.id+". Operatore If Then Else non consentito come parametro.");
            }
        }

    return t;
    }

    @Override
    public String toPrint(String s) {
        return this.id+" :"+this.params.toString();
    }

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
