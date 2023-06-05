package ast;

import semanticanalysis.Environment;
import semanticanalysis.Error2;
import semanticanalysis.SemanticError;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DecFunNode implements Node{
    private TypeNode type;
    private String id;
    private ArrayList<Node> params;
    private ArrayList<Node> innerDecs;
    private ArrayList<Node> innerStatements;
    private Node innerExp;
    private Environment env;

    public DecFunNode(Node type, String id, ArrayList<Node> params, ArrayList<Node> innerDecs, ArrayList<Node> innerStatements, Node innerExp) {
        this.type = (TypeNode) type;
        this.id = id;
        this.params = params;
        this.innerDecs = innerDecs;
        this.innerStatements = innerStatements;
        this.innerExp = innerExp;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment e) {
        return null;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        if (innerDecs != null)
            for(Node innerD: this.innerDecs)
                innerD.typeCheck(e);
        if (innerStatements != null)
            for(Node innerS: this.innerStatements)
                innerS.typeCheck(e);

        if (innerExp != null) {
            TypeNode returnType = innerExp.typeCheck(e);
            if (returnType.getType() != this.type.getType())
                throw new Error2("Errore di TypeChecking: tipo di ritorno della funzione diverso dal tipo atteso.");
        }
        return this.type;
    }

    @Override
    public String toPrint(String s) {
        String parlstr="";
        for (Node par:params){
            parlstr += par.toPrint(s);
        }
        String declstr= "";
      /*  if (innerDecs!=null)
            for (Node dec:innerDecs)
                declstr+=dec.toPrint(s+" ");*/
        return s+"Fun:" + id +"\n"
                +parlstr
                +declstr
                + "\n";
    }    //Da sistemare se si vuole far printare anche il body

    @Override
    public String codeGeneration(Environment localenv) {
        return null;
    }
}
