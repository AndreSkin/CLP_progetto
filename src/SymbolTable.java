import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;

public class SymbolTable {
	private Stack<Hashtable<String, SymbolTableEntry>> symbolTable;
	
	public SymbolTable() {
		symbolTable = new Stack<>();
	}
	
	public SymbolTable(String elem, SymbolTableEntry st) {
		symbolTable = new Stack<>();
		Hashtable<String, SymbolTableEntry> current = new Hashtable<String, SymbolTableEntry>();
		current.put(elem, st);
		symbolTable.push(current);
	}

	public void Add(String s, SymbolTableEntry st)
	{
		if(symbolTable.isEmpty()){
			Hashtable<String, SymbolTableEntry> current = new Hashtable<String, SymbolTableEntry>();
			current.put(s, st);
			symbolTable.push(current);
		} else {
			Hashtable<String, SymbolTableEntry> x = symbolTable.peek();
			if (x.contains(s)) {
				System.out.println("Variabile già dichiarata nel blocco corrente");
				throw new Error2();
			} else {
				x.put(s, st);
			}
		}
	}

	public void enterInNewBlock() {
		this.symbolTable.push(new Hashtable<String, SymbolTableEntry>());
	}

	public void exitFromBlock() {
		this.symbolTable.pop();
	}
}
