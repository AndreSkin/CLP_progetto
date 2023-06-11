package semanticanalysis;

import ast.TypeNode;

import java.util.Hashtable;
import java.util.Stack;

public class SymbolTable{
	private Stack<Hashtable<String, SymbolTableEntry>> symbolTable;

	public SymbolTable() {
		symbolTable = new Stack<>();
	}

	public SymbolTable(Stack<Hashtable<String, SymbolTableEntry>> symbolTable) {
		this.symbolTable = new Stack<Hashtable<String, SymbolTableEntry>>();
		for(Hashtable<String, SymbolTableEntry> entry: symbolTable) {
			Hashtable<String, SymbolTableEntry> newHash = new Hashtable<String, SymbolTableEntry>();
			for(String k: entry.keySet()) {
				SymbolTableEntry se = entry.get(k);
				SymbolTableEntry newSe = new SymbolTableEntry(se.getLabel(), se.getType(), se.getStatus(), se.getOffset());
				newHash.put(k,newSe);
			}
			this.symbolTable.push(newHash);
		}
	}

	public Stack<Hashtable<String, SymbolTableEntry>> getSymbolTable() {
		return this.symbolTable;
	};
	
	public SymbolTable(String elem, SymbolTableEntry st) {
		symbolTable = new Stack<>();
		Hashtable<String, SymbolTableEntry> current = new Hashtable<String, SymbolTableEntry>();
		current.put(elem, st);
		symbolTable.push(current);
	}
	public Hashtable<String, SymbolTableEntry> get(int i) {
		return symbolTable.get(i);
	}
	public int getSize() {return symbolTable.size();}
	public int nesting() {
		return symbolTable.size() - 1;
	}

	public SymbolTableEntry lookup(String id) {
		int n = symbolTable.size()-1;
		boolean found = false;
		SymbolTableEntry result = null;
		while (n>-1 && !found) {
			Hashtable<String, SymbolTableEntry> x = symbolTable.get(n);
			if(x.get(id) != null) {
				found = true;
				result = x.get(id);
			} else n-=1;
		}
		return result;
	}

	public SymbolTableEntry topLookup(String id) {
		int n = symbolTable.size()-1;
		Hashtable<String, SymbolTableEntry> x = symbolTable.get(n);
		return x.get(id);
	}

	public int nestingLookup(String id) {
		int n = symbolTable.size()-1;
		boolean found = false;

		while (n>0 && !found) {
			Hashtable<String, SymbolTableEntry> x = symbolTable.get(n);

			if(this.lookup(id).getOffset() == -1) {
				//contains
				if(x.containsKey(id)) {
					found = true;
				} else n-=1;
			} else {
				if(x.containsKey(id)) {
					found = true;
				} else n-=1;
			}


		}
		return n;
	}

	public void insert(String id, TypeNode type, int offset) {
		Hashtable<String, SymbolTableEntry> x = symbolTable.pop();
		SymbolTableEntry st = new SymbolTableEntry(id, type, Status.DECLARED, offset);
		x.put(id, st);
		symbolTable.push(x);
	}

	public void insert(String id, TypeNode type, int offset, String label) {
		Hashtable<String, SymbolTableEntry> x = symbolTable.pop();
		SymbolTableEntry st = new SymbolTableEntry(label, type, Status.DECLARED, offset);
		x.put(id, st);
		symbolTable.push(x);
	}



	public void enterInNewBlock() {
		this.symbolTable.push(new Hashtable<String, SymbolTableEntry>());
	}

	public void exitFromBlock() {
		this.symbolTable.pop();
	}

}
