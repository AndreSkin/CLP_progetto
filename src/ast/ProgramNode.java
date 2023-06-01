package ast;

import java.util.ArrayList;

    public class ProgramNode implements Node {

        private ArrayList<Node> declarations;
        private ArrayList<Node> statements;

        private Node exp;
        private Environment localenv;

        public ProgramNode(ArrayList<Node> declarations, ArrayList<Node> statements, Node exp) {
            this.declarations = declarations;
            this.statements = statements;
            this.exp = exp;
        }

        public ProgramNode(Node exp) {
            this.exp = exp;
        }

        @Override
        public ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting) {
            return null;
        }

        @Override
        public Type typeCheck() {
            return null;
        }

        @Override
        public String codeGeneration() {
            return null;
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
        public TypeNode typeCheck(Environment env) throws TypeCheckException {
            // Typecheck the declarations
            if (this.declarations != null) {
                for (Node declaration : this.declarations) {
                    declaration.typeCheck(this.localenv);
                }
            }
            // Typecheck the statements
            if (this.statements != null) {
                for (Node statement : this.statements) {
                    statement.typeCheck(this.localenv);
                }
            }
            // Locate unused symbols
            for(String id: localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).keySet()){
                if(!localenv.getSymbolTableManager().getLevel(localenv.getNestingLevel()).get(id).getEffect().isUsed()){
                    System.out.println("[W] Symbol "+id+" is unused in program.");
                }
            }
            return new VoidTypeNode();
        }

}
