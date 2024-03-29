package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.SimpLanCustomError;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public class DecFunNode implements Node{
    private TypeNode type;
    private String id;
    private ArrayList<Node> params;
    private ArrayList<Node> innerDecs;
    private ArrayList<Node> innerStatements;
    private Node innerExp;
    private String flabel;

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
        this.flabel = SimpLanlib.freshFunLabel() ;


        e.getSymbolTable().insert(this.id, function,-1, this.flabel);

        st = e.getSymbolTable();
        e.enterInNewBlock();

        for(Node arg: params) {
            results.addAll(arg.checkSemantics(e));
        }

        e.incrementOffset();

        for(Node dec: innerDecs) {
            results.addAll(dec.checkSemantics(e));
        }

        for(Node stm: innerStatements) {
            results.addAll(stm.checkSemantics(e));
        }

        if(innerExp != null)
            results.addAll(innerExp.checkSemantics(e));


        e.exitFromBlock();
        return results;
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
            if (!returnType.getType().equals(this.type.getType()))
                throw new SimpLanCustomError("Errore di TypeChecking: Tipo di ritorno della funzione diverso dal tipo atteso.");
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
        return s+"Fun:" + id +"\n"
                +parlstr
                +declstr
                + "\n";
    }

    @Override
    public String codeGeneration(Environment e)
    {
        int paramSpace = this.params.size();
        String declCode = "" ;
        if (innerDecs.size() != 0) {
            for (Node dec:innerDecs){
                declCode += dec.codeGeneration(e);
            }
        }

        String innerStmCode = "";
        if (innerStatements != null)
            for(Node innerS: this.innerStatements)
                innerStmCode += innerS.codeGeneration(e);

        String innerExpCode = "";
        if (innerExp != null)
            innerExpCode += innerExp.codeGeneration(e);



        SimpLanlib.putCode(
                flabel + ":\n"
                        + "pushr RA \n"
                        + declCode
                        + innerStmCode
                        + innerExpCode
                        + "addi SP " + 	innerDecs.size() + "\n"
                        + "popr RA \n"
                        + "addi SP " + 	paramSpace + "\n"
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
