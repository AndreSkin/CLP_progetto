package semanticanalysis;

import ast.TypeNode;

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

	public int nesting() {
		return symbolTable.size() - 1;
	}

	public SymbolTableEntry lookup(String id) {
		int n = symbolTable.size()-1;
		boolean found = false;
		SymbolTableEntry result = null;
		while (n>0 && !found) {
			Hashtable<String, SymbolTableEntry> x = symbolTable.get(n);
			if(x.contains(id)) {
				found = true;
				result = x.get(id);
			} else n-=1;
		}
		return result;
	}

	public int nslookup(String id) {
		int n = symbolTable.size()-1;
		boolean found = false;
		while (n>0 && !found) {
			Hashtable<String, SymbolTableEntry> x = symbolTable.get(n);
			if(x.contains(id)) {
				found = true;
			} else n-=1;
		}
		return n;
	}

	public void add(String s, SymbolTableEntry st)
	{
		if(symbolTable.isEmpty()){
			Hashtable<String, SymbolTableEntry> current = new Hashtable<String, SymbolTableEntry>();
			current.put(s, st);
			symbolTable.push(current);
		} else {
			Hashtable<String, SymbolTableEntry> x = symbolTable.peek();
			x.put(s, st);
		}
	}

	public void insert(String id, TypeNode type) {
		Hashtable<String, SymbolTableEntry> x = symbolTable.peek();
		SymbolTableEntry st = new SymbolTableEntry(id, type, Status.DECLARED);
		x.put(id, st);
	}


	public void enterInNewBlock() {
		this.symbolTable.push(new Hashtable<String, SymbolTableEntry>());
	}

	public void exitFromBlock() {
		this.symbolTable.pop();
	}
}
