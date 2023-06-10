package ast;

import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTableEntry;

import java.util.ArrayList;

public class FunCallNode implements Node{
    String id;
    ArrayList<Node> params;

    SymbolTableEntry st;
    private int nesting;

    private int nestingNode;

    public FunCallNode(String id, ArrayList<Node> params) {
        this.id = id;
        this.params = params;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        ArrayList<SemanticError> results = new ArrayList<SemanticError>();
        st = e.getSymbolTable().lookup(this.id);

        nesting = e.getNestingLevel();
        nestingNode = e.getSymbolTable().nestingLookup(this.id);
        //System.out.println(this.id+": "+nesting+", "+nestingNode);
        if (st == null)
            results.add(new SemanticError("Funzione " + this.id + " non dichiarata."));
        else
            if (this.params != null)
                for (Node par : params)
                    results.addAll(par.checkSemantics(e));
        return results;
    }

    @Override
    public TypeNode typeCheck(Environment e) {
        TypeNode t = st.getType();
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
    public String codeGeneration(Environment e) {
        // TODO: 6/9/23  Non credo serva? i parametri non generano codice
        String parCode="";
        for (int i = 0; i < this.params.size() ; i = i+1)
            parCode += this.params.get(i).codeGeneration(e) + "pushr A0\n" ;

        String getAR="";

        for (int i=0; i < nesting - nestingNode; i++)
            getAR+="store T1 0(T1) \n";
        // formato AR: control_link + access link + parameters + indirizzo di ritorno + dich_locali

        return  "pushr FP \n"			// carico il frame pointer
                + "move SP FP \n"
                + "addi FP 1 \n"	// salvo in FP il puntatore all'indirizzo del frame pointer caricato
                + "move AL T1\n"		// risalgo la catena statica
                + getAR
                + "pushr T1 \n"			// salvo sulla pila l'access link statico
                + parCode 				// calcolo i parametri attuali con l'access link del chiamante
                + "move FP AL \n"
                + "subi AL 1 \n"
                + "jsub " + e.getSymbolTable().lookup(id).getLabel() + "\n" ;
    }
}
