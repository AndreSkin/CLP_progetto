public class Environment {
    private SymbolTable symbolTable;
    private int nestingLevel;
    private int offset;

    public Environment() {
        this.symbolTable = new SymbolTable();
        this.nestingLevel = -1;
        this.offset = 0;
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(SymbolTable st) {
        this.symbolTable = st;
    }

    public int getNestingLevel() {
        return this.nestingLevel;
    }

    public void setNestingLevel(int nl) {
        this.nestingLevel = nl;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int of) {
        this.offset = of;
    }

}