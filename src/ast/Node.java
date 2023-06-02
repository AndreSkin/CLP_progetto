package ast;

import semanticanalysis.Environment;
import semanticanalysis.SemanticError;
import semanticanalysis.SymbolTable;

import java.util.ArrayList;

public interface Node {
    ArrayList<SemanticError> checkSemantics(Environment e);
    TypeNode typeCheck(Environment e);
    String toPrint(String s);
    String codeGeneration(/*LabelGenerator labgen,*/ Environment localenv);
}
