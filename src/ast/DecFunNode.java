package ast;

import semanticanalysis.*;

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
        ArrayList<SemanticError> results = new ArrayList<>();
        SymbolTable st = e.getSymbolTable();
        if (st.lookup(this.id) != null) {
            results.add(new SemanticError("Identificatore della funzione già presente nel blocco corrente."));
            return results;
        }
        ArrayList<TypeNode> paramsList = new ArrayList<>();
        if(this.params != null) {
            for(Node p: this.params) {
                ArgNode tmp = (ArgNode) p;
                paramsList.add(new TypeNode(tmp.getType().getType()));
            }
        }

        TypeNode function = new TypeNode(this.type.getType(), paramsList);
        // TODO: 6/7/23 Offset va bene?
        st.insert(this.id, function,-1);
        //Analizzo dentro
        this.env = new Environment();

        this.env.getSymbolTable().enterInNewBlock();
// TODO: 6/7/23 Controlla se ti servono le label qui e se devi incrementare l'offset per il return value
        this.env.getSymbolTable().insert(this.id, function, -1);

        for(Node arg: params) {
            results.addAll(arg.checkSemantics(this.env));
        }

        for(Node dec: innerDecs) {
            results.addAll(dec.checkSemantics(this.env));
        }

        for(Node stm: innerStatements) {
            results.addAll(stm.checkSemantics(this.env));
        }

        if(innerExp != null)
            results.addAll(innerExp.checkSemantics(this.env));

        return results;
    }

    @Override
    public TypeNode typeCheck(Environment e) throws Error {
        if (innerDecs != null)
            for(Node innerD: this.innerDecs)
                innerD.typeCheck(this.env);
        if (innerStatements != null)
            for(Node innerS: this.innerStatements)
                innerS.typeCheck(this.env);

        if (innerExp != null) {
            TypeNode returnType = innerExp.typeCheck(this.env);
            if (!returnType.getType().equals(this.type.getType()))
                throw new Error2("Errore di TypeChecking: Tipo di ritorno della funzione diverso dal tipo atteso.");
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
    public String codeGeneration(Environment e)
    {
        String declCode = "" ;
        if (declist.size() != 0) {
            for (Node dec:declist){
                declCode = declCode + dec.codeGeneration();
            }
        }

        SimpLanlib.putCode(
                flabel + ":\n"
                        + "pushr RA \n"
                        + declCode
                        + body.codeGeneration(e)
                        + "addi SP " + 	declist.size() + "\n"
                        + "popr RA \n"
                        + "addi SP " + 	parlist.size() + "\n"
                        + "pop \n"
                        + "store FP 0(FP) \n"
                        + "move FP AL \n"
                        + "subi AL 1 \n"
                        + "pop \n"
                        + "rsub RA \n"
        );

        return "push "+ flabel +"\n";
    }
}
