import java.util.ArrayList;

public class SymbolTable {
	private ArrayList<SymbolTableEntry> symbolTable;
	
	public SymbolTable() {
		symbolTable = new ArrayList<SymbolTableEntry>();
	}
	
	public SymbolTable(SymbolTableEntry st) {
		symbolTable = new ArrayList<SymbolTableEntry>();
		symbolTable.add(st);
	}

	public void SymbolTableAdd(SymbolTableEntry st)
	{
		symbolTable.add(st);
	}
}
