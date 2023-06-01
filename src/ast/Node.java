package ast;

import semanticanalysis.SemanticError;

public interface Node {
    ArrayList<SemanticError> checkSemantics(SymbolTable ST, int _nesting);
    Type typeCheck();
    String codeGeneration();

    String toPrint(String s);

}
