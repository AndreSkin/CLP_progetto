package semanticanalysis;

import java.util.ArrayList;

public class Environment{
    private SymbolTable symbolTable;
    private ArrayList<Integer> offset = new ArrayList<>();

    public Environment() {
        this.symbolTable = new SymbolTable();
        offset = new ArrayList<Integer>() ;
        offset.add(1);
    }

    public Environment(Environment from) {
        this.symbolTable = new SymbolTable(from.getSymbolTable().getSymbolTable());
        offset = new ArrayList<Integer>() ;
        for(int i:from.getOffsetArray())
            offset.add(i);
    }

    public ArrayList<Integer> getOffsetArray() {
        return this.offset;
    }

    public int getOffset() {
        return this.offset.get(this.offset.size()-1);
    }


    public void incrementOffset() {
        this.offset.set(this.offset.size()-1, this.offset.get(this.offset.size()-1)+1);
    }

    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }

    public void setSymbolTable(SymbolTable st) {
        this.symbolTable = st;
    }

    public void enterInNewBlock() {
        this.symbolTable.enterInNewBlock();
        this.offset.add(1);
    }

    public void exitFromBlock() {
        this.symbolTable.exitFromBlock();
        this.offset.remove(this.offset.size()-1);
    }



    public int getNestingLevel() {
        return this.symbolTable.nesting();
    }

}
