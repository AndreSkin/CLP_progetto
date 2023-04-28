
public class SymbolTableEntry {
	private String label;
	private Type type;
	private int nestingLevel;
	private Status status;
	
	public SymbolTableEntry(String l, Type t,int nestingL, Status s) {
		this.label = l;
		this.type = t;
		this.nestingLevel = nestingL;
		this.status = s;
	}
}
