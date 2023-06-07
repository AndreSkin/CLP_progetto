package semanticanalysis;

public class Environment{
    private SymbolTable symbolTable;
    private int offset;

    public Environment() {
        this.symbolTable = new SymbolTable();
        this.offset = 0;
    }

    public Environment(Environment from) {
        this.symbolTable = new SymbolTable(from.getSymbolTable().getSymbolTable());
        this.offset = from.getOffset();
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int of) {
        this.offset = of;
    }

    public void incrementOffset() {
        this.offset+=1;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(SymbolTable st) {
        this.symbolTable = st;
    }

    public void enterInNewBlock() {this.symbolTable.enterInNewBlock();}

    public void exitFromBlock() {
        this.symbolTable.exitFromBlock();
    }



    public int getNestingLevel() {
        return this.symbolTable.nesting();
    }

    /*public void setNestingLevel(int nl) {
        this.nestingLevel = nl;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int of) {
        this.offset = of;
    }*/

}
