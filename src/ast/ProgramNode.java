package ast;

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
            return "\n" + indent + "Program" + res;
        }

        @Override
        public String codeGeneration(Environment localenv) {
            return null;
        }

        @Override
        public ArrayList<SemanticError> checkSemantics(Environment e) {
            return null;
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
