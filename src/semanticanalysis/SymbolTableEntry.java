package semanticanalysis;

import ast.TypeNode;

public class SymbolTableEntry {
	private String label;
	private TypeNode type;
	//private int nestingLevel;
	private Status status;
	
	public SymbolTableEntry(String l, TypeNode t, /*int nestingL,*/ Status s) {
		this.label = l;
		this.type = t;
		//this.nestingLevel = nestingL;
		this.status = s;
	}

	public String getLabel() {
		return this.label;
	}

	public TypeNode getType() {
		return this.type;
	}

	public Status getStatus() {
		return this.status;
	}


}
