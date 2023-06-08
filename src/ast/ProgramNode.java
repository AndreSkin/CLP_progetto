package ast;

import others.SimpLanlib;
import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

    public class ProgramNode implements Node {

        private ArrayList<Node> declarations;
        private ArrayList<Node> statements;

        private Node exp;
        private Environment env;

        public ProgramNode(ArrayList<Node> declarations, ArrayList<Node> statements, Node exp) {
            this.declarations = declarations;
            this.statements = statements;
            this.exp = exp;
        }

        public ProgramNode(Node exp) {
            this.exp = exp;
        }

        @Override
        public String toPrint(String indent) {
            String res = "";
            if (this.declarations != null) {
                for (Node dec : declarations) {
                    res += dec.toPrint(indent + " ");
                }
            }
            if (this.statements != null) {
                for (Node dec : statements) {
                    res += dec.toPrint(indent + " ");
                }
            }

            if(this.exp != null) {
                res += exp.toPrint(indent+ " ");
            }
            return "\n" + "Program" + res;
        }

        @Override
        public String codeGeneration(Environment e)
        {
            String declCode="";
            for (Node d: declarations)
                declCode += d.codeGeneration(e);
            String stmCode="";
            for (Node s: statements)
                stmCode += s.codeGeneration(e);
            return  "move SP FP  \n"
                    + "pushr FP \n"
                    + "move SP AL \n"
                    + "pushr AL \n"
                    + declCode
                    + stmCode
                    + exp.codeGeneration(e)
                    + "halt\n" +
                    SimpLanlib.getCode();
        }

        @Override
        public ArrayList<SemanticError> checkSemantics(Environment e) {
            this.env = e;
            this.env.getSymbolTable().enterInNewBlock();
            ArrayList<SemanticError> result = new ArrayList<SemanticError>();
            // Check the semantics of declarations
            if (this.declarations != null && this.declarations.size() > 0) {
                for (Node n : this.declarations) {
                    result.addAll(n.checkSemantics(env));
                }
            }

            // Check the semantics of statements
            if (this.statements != null && this.statements.size() > 0) {
                for (Node n : this.statements) {
                    result.addAll(n.checkSemantics(env));
                }
            }

            if(this.exp != null) {
                result.addAll(exp.checkSemantics(env));
            }
            //this.env.getSymbolTable().exitFromBlock();
            return result;
        }

        @Override
        public TypeNode typeCheck(Environment e) {
            // Typecheck the declarations --- Non ho assegnamenti quando dichiaro!
            if (this.declarations != null) {
                for (Node declaration : this.declarations) {
                    declaration.typeCheck(e);
                }
            }
            // Typecheck the statements
            if (this.statements != null) {
                for (Node statement : this.statements) {
                    statement.typeCheck(e);
                }
            }
            //da modificare
            return this.exp!=null? exp.typeCheck(e):null;
        }


    }
