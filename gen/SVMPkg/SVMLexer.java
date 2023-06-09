// Generated from /home/alessio/Projects/CLP_progetto/src/SVM.g4 by ANTLR 4.12.0
package SVMPkg;

import java.util.HashMap;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SVMLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, LOAD=4, STORE=5, STOREI=6, MOVE=7, ADD=8, ADDI=9, 
		SUB=10, SUBI=11, MUL=12, MULI=13, DIV=14, DIVI=15, PUSH=16, PUSHR=17, 
		POP=18, POPR=19, BRANCH=20, BRANCHEQ=21, BRANCHLESSEQ=22, JUMPSUB=23, 
		RETURNSUB=24, HALT=25, BRANCHEGT=26, BRANCHEQLT=27, BRANCHEQGTE=28, REG=29, 
		LABEL=30, NUMBER=31, WHITESP=32, LINECOMENTS=33, ERR=34;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "LOAD", "STORE", "STOREI", "MOVE", "ADD", "ADDI", 
			"SUB", "SUBI", "MUL", "MULI", "DIV", "DIVI", "PUSH", "PUSHR", "POP", 
			"POPR", "BRANCH", "BRANCHEQ", "BRANCHLESSEQ", "JUMPSUB", "RETURNSUB", 
			"HALT", "BRANCHEGT", "BRANCHEQLT", "BRANCHEQGTE", "REG", "LABEL", "NUMBER", 
			"WHITESP", "LINECOMENTS", "ERR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "':'", "'load'", "'store'", "'storei'", "'move'", 
			"'add'", "'addi'", "'sub'", "'subi'", "'mul'", "'muli'", "'div'", "'divi'", 
			"'push'", "'pushr'", "'pop'", "'popr'", "'b'", "'beq'", "'bleq'", "'jsub'", 
			"'rsub'", "'halt'", "'bgt'", "'blt'", "'bgte'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "LOAD", "STORE", "STOREI", "MOVE", "ADD", "ADDI", 
			"SUB", "SUBI", "MUL", "MULI", "DIV", "DIVI", "PUSH", "PUSHR", "POP", 
			"POPR", "BRANCH", "BRANCHEQ", "BRANCHLESSEQ", "JUMPSUB", "RETURNSUB", 
			"HALT", "BRANCHEGT", "BRANCHEQLT", "BRANCHEQGTE", "REG", "LABEL", "NUMBER", 
			"WHITESP", "LINECOMENTS", "ERR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	 
	public int lexicalErrors=0;


	public SVMLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "SVM.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 33:
			ERR_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void ERR_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			 System.err.println("Invalid char: "+ getText()); lexicalErrors++;  
			break;
		}
	}

	public static final String _serializedATN =
		"\u0004\u0000\"\u00fc\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017"+
		"\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018"+
		"\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u001a"+
		"\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b"+
		"\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0003\u001c\u00d0\b\u001c"+
		"\u0001\u001d\u0001\u001d\u0005\u001d\u00d4\b\u001d\n\u001d\f\u001d\u00d7"+
		"\t\u001d\u0001\u001e\u0001\u001e\u0003\u001e\u00db\b\u001e\u0001\u001e"+
		"\u0001\u001e\u0005\u001e\u00df\b\u001e\n\u001e\f\u001e\u00e2\t\u001e\u0003"+
		"\u001e\u00e4\b\u001e\u0001\u001f\u0004\u001f\u00e7\b\u001f\u000b\u001f"+
		"\f\u001f\u00e8\u0001\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0005"+
		" \u00f1\b \n \f \u00f4\t \u0001 \u0001 \u0001!\u0001!\u0001!\u0001!\u0001"+
		"!\u0000\u0000\"\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005"+
		"\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019"+
		"\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015"+
		"+\u0016-\u0017/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f"+
		"? A!C\"\u0001\u0000\u0004\u0002\u0000AZaz\u0003\u000009AZaz\u0003\u0000"+
		"\t\n\r\r  \u0002\u0000\n\n\r\r\u0107\u0000\u0001\u0001\u0000\u0000\u0000"+
		"\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000"+
		"\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000"+
		"\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f"+
		"\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013"+
		"\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017"+
		"\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b"+
		"\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f"+
		"\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000"+
		"\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000"+
		"\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000"+
		"-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001"+
		"\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000"+
		"\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001\u0000\u0000\u0000\u0000"+
		";\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000\u0000\u0000?\u0001"+
		"\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000C\u0001\u0000\u0000"+
		"\u0000\u0001E\u0001\u0000\u0000\u0000\u0003G\u0001\u0000\u0000\u0000\u0005"+
		"I\u0001\u0000\u0000\u0000\u0007K\u0001\u0000\u0000\u0000\tP\u0001\u0000"+
		"\u0000\u0000\u000bV\u0001\u0000\u0000\u0000\r]\u0001\u0000\u0000\u0000"+
		"\u000fb\u0001\u0000\u0000\u0000\u0011f\u0001\u0000\u0000\u0000\u0013k"+
		"\u0001\u0000\u0000\u0000\u0015o\u0001\u0000\u0000\u0000\u0017t\u0001\u0000"+
		"\u0000\u0000\u0019x\u0001\u0000\u0000\u0000\u001b}\u0001\u0000\u0000\u0000"+
		"\u001d\u0081\u0001\u0000\u0000\u0000\u001f\u0086\u0001\u0000\u0000\u0000"+
		"!\u008b\u0001\u0000\u0000\u0000#\u0091\u0001\u0000\u0000\u0000%\u0095"+
		"\u0001\u0000\u0000\u0000\'\u009a\u0001\u0000\u0000\u0000)\u009c\u0001"+
		"\u0000\u0000\u0000+\u00a0\u0001\u0000\u0000\u0000-\u00a5\u0001\u0000\u0000"+
		"\u0000/\u00aa\u0001\u0000\u0000\u00001\u00af\u0001\u0000\u0000\u00003"+
		"\u00b4\u0001\u0000\u0000\u00005\u00b8\u0001\u0000\u0000\u00007\u00bc\u0001"+
		"\u0000\u0000\u00009\u00cf\u0001\u0000\u0000\u0000;\u00d1\u0001\u0000\u0000"+
		"\u0000=\u00e3\u0001\u0000\u0000\u0000?\u00e6\u0001\u0000\u0000\u0000A"+
		"\u00ec\u0001\u0000\u0000\u0000C\u00f7\u0001\u0000\u0000\u0000EF\u0005"+
		"(\u0000\u0000F\u0002\u0001\u0000\u0000\u0000GH\u0005)\u0000\u0000H\u0004"+
		"\u0001\u0000\u0000\u0000IJ\u0005:\u0000\u0000J\u0006\u0001\u0000\u0000"+
		"\u0000KL\u0005l\u0000\u0000LM\u0005o\u0000\u0000MN\u0005a\u0000\u0000"+
		"NO\u0005d\u0000\u0000O\b\u0001\u0000\u0000\u0000PQ\u0005s\u0000\u0000"+
		"QR\u0005t\u0000\u0000RS\u0005o\u0000\u0000ST\u0005r\u0000\u0000TU\u0005"+
		"e\u0000\u0000U\n\u0001\u0000\u0000\u0000VW\u0005s\u0000\u0000WX\u0005"+
		"t\u0000\u0000XY\u0005o\u0000\u0000YZ\u0005r\u0000\u0000Z[\u0005e\u0000"+
		"\u0000[\\\u0005i\u0000\u0000\\\f\u0001\u0000\u0000\u0000]^\u0005m\u0000"+
		"\u0000^_\u0005o\u0000\u0000_`\u0005v\u0000\u0000`a\u0005e\u0000\u0000"+
		"a\u000e\u0001\u0000\u0000\u0000bc\u0005a\u0000\u0000cd\u0005d\u0000\u0000"+
		"de\u0005d\u0000\u0000e\u0010\u0001\u0000\u0000\u0000fg\u0005a\u0000\u0000"+
		"gh\u0005d\u0000\u0000hi\u0005d\u0000\u0000ij\u0005i\u0000\u0000j\u0012"+
		"\u0001\u0000\u0000\u0000kl\u0005s\u0000\u0000lm\u0005u\u0000\u0000mn\u0005"+
		"b\u0000\u0000n\u0014\u0001\u0000\u0000\u0000op\u0005s\u0000\u0000pq\u0005"+
		"u\u0000\u0000qr\u0005b\u0000\u0000rs\u0005i\u0000\u0000s\u0016\u0001\u0000"+
		"\u0000\u0000tu\u0005m\u0000\u0000uv\u0005u\u0000\u0000vw\u0005l\u0000"+
		"\u0000w\u0018\u0001\u0000\u0000\u0000xy\u0005m\u0000\u0000yz\u0005u\u0000"+
		"\u0000z{\u0005l\u0000\u0000{|\u0005i\u0000\u0000|\u001a\u0001\u0000\u0000"+
		"\u0000}~\u0005d\u0000\u0000~\u007f\u0005i\u0000\u0000\u007f\u0080\u0005"+
		"v\u0000\u0000\u0080\u001c\u0001\u0000\u0000\u0000\u0081\u0082\u0005d\u0000"+
		"\u0000\u0082\u0083\u0005i\u0000\u0000\u0083\u0084\u0005v\u0000\u0000\u0084"+
		"\u0085\u0005i\u0000\u0000\u0085\u001e\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0005p\u0000\u0000\u0087\u0088\u0005u\u0000\u0000\u0088\u0089\u0005s"+
		"\u0000\u0000\u0089\u008a\u0005h\u0000\u0000\u008a \u0001\u0000\u0000\u0000"+
		"\u008b\u008c\u0005p\u0000\u0000\u008c\u008d\u0005u\u0000\u0000\u008d\u008e"+
		"\u0005s\u0000\u0000\u008e\u008f\u0005h\u0000\u0000\u008f\u0090\u0005r"+
		"\u0000\u0000\u0090\"\u0001\u0000\u0000\u0000\u0091\u0092\u0005p\u0000"+
		"\u0000\u0092\u0093\u0005o\u0000\u0000\u0093\u0094\u0005p\u0000\u0000\u0094"+
		"$\u0001\u0000\u0000\u0000\u0095\u0096\u0005p\u0000\u0000\u0096\u0097\u0005"+
		"o\u0000\u0000\u0097\u0098\u0005p\u0000\u0000\u0098\u0099\u0005r\u0000"+
		"\u0000\u0099&\u0001\u0000\u0000\u0000\u009a\u009b\u0005b\u0000\u0000\u009b"+
		"(\u0001\u0000\u0000\u0000\u009c\u009d\u0005b\u0000\u0000\u009d\u009e\u0005"+
		"e\u0000\u0000\u009e\u009f\u0005q\u0000\u0000\u009f*\u0001\u0000\u0000"+
		"\u0000\u00a0\u00a1\u0005b\u0000\u0000\u00a1\u00a2\u0005l\u0000\u0000\u00a2"+
		"\u00a3\u0005e\u0000\u0000\u00a3\u00a4\u0005q\u0000\u0000\u00a4,\u0001"+
		"\u0000\u0000\u0000\u00a5\u00a6\u0005j\u0000\u0000\u00a6\u00a7\u0005s\u0000"+
		"\u0000\u00a7\u00a8\u0005u\u0000\u0000\u00a8\u00a9\u0005b\u0000\u0000\u00a9"+
		".\u0001\u0000\u0000\u0000\u00aa\u00ab\u0005r\u0000\u0000\u00ab\u00ac\u0005"+
		"s\u0000\u0000\u00ac\u00ad\u0005u\u0000\u0000\u00ad\u00ae\u0005b\u0000"+
		"\u0000\u00ae0\u0001\u0000\u0000\u0000\u00af\u00b0\u0005h\u0000\u0000\u00b0"+
		"\u00b1\u0005a\u0000\u0000\u00b1\u00b2\u0005l\u0000\u0000\u00b2\u00b3\u0005"+
		"t\u0000\u0000\u00b32\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005b\u0000"+
		"\u0000\u00b5\u00b6\u0005g\u0000\u0000\u00b6\u00b7\u0005t\u0000\u0000\u00b7"+
		"4\u0001\u0000\u0000\u0000\u00b8\u00b9\u0005b\u0000\u0000\u00b9\u00ba\u0005"+
		"l\u0000\u0000\u00ba\u00bb\u0005t\u0000\u0000\u00bb6\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0005b\u0000\u0000\u00bd\u00be\u0005g\u0000\u0000\u00be"+
		"\u00bf\u0005t\u0000\u0000\u00bf\u00c0\u0005e\u0000\u0000\u00c08\u0001"+
		"\u0000\u0000\u0000\u00c1\u00c2\u0005A\u0000\u0000\u00c2\u00d0\u00050\u0000"+
		"\u0000\u00c3\u00c4\u0005R\u0000\u0000\u00c4\u00d0\u0005A\u0000\u0000\u00c5"+
		"\u00c6\u0005F\u0000\u0000\u00c6\u00d0\u0005P\u0000\u0000\u00c7\u00c8\u0005"+
		"S\u0000\u0000\u00c8\u00d0\u0005P\u0000\u0000\u00c9\u00ca\u0005A\u0000"+
		"\u0000\u00ca\u00d0\u0005L\u0000\u0000\u00cb\u00cc\u0005T\u0000\u0000\u00cc"+
		"\u00d0\u00050\u0000\u0000\u00cd\u00ce\u0005T\u0000\u0000\u00ce\u00d0\u0005"+
		"1\u0000\u0000\u00cf\u00c1\u0001\u0000\u0000\u0000\u00cf\u00c3\u0001\u0000"+
		"\u0000\u0000\u00cf\u00c5\u0001\u0000\u0000\u0000\u00cf\u00c7\u0001\u0000"+
		"\u0000\u0000\u00cf\u00c9\u0001\u0000\u0000\u0000\u00cf\u00cb\u0001\u0000"+
		"\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00d0:\u0001\u0000\u0000"+
		"\u0000\u00d1\u00d5\u0007\u0000\u0000\u0000\u00d2\u00d4\u0007\u0001\u0000"+
		"\u0000\u00d3\u00d2\u0001\u0000\u0000\u0000\u00d4\u00d7\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d5\u00d6\u0001\u0000\u0000"+
		"\u0000\u00d6<\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000\u0000"+
		"\u00d8\u00e4\u00050\u0000\u0000\u00d9\u00db\u0005-\u0000\u0000\u00da\u00d9"+
		"\u0001\u0000\u0000\u0000\u00da\u00db\u0001\u0000\u0000\u0000\u00db\u00dc"+
		"\u0001\u0000\u0000\u0000\u00dc\u00e0\u000219\u0000\u00dd\u00df\u00020"+
		"9\u0000\u00de\u00dd\u0001\u0000\u0000\u0000\u00df\u00e2\u0001\u0000\u0000"+
		"\u0000\u00e0\u00de\u0001\u0000\u0000\u0000\u00e0\u00e1\u0001\u0000\u0000"+
		"\u0000\u00e1\u00e4\u0001\u0000\u0000\u0000\u00e2\u00e0\u0001\u0000\u0000"+
		"\u0000\u00e3\u00d8\u0001\u0000\u0000\u0000\u00e3\u00da\u0001\u0000\u0000"+
		"\u0000\u00e4>\u0001\u0000\u0000\u0000\u00e5\u00e7\u0007\u0002\u0000\u0000"+
		"\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7\u00e8\u0001\u0000\u0000\u0000"+
		"\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000\u0000\u0000"+
		"\u00e9\u00ea\u0001\u0000\u0000\u0000\u00ea\u00eb\u0006\u001f\u0000\u0000"+
		"\u00eb@\u0001\u0000\u0000\u0000\u00ec\u00ed\u0005/\u0000\u0000\u00ed\u00ee"+
		"\u0005/\u0000\u0000\u00ee\u00f2\u0001\u0000\u0000\u0000\u00ef\u00f1\b"+
		"\u0003\u0000\u0000\u00f0\u00ef\u0001\u0000\u0000\u0000\u00f1\u00f4\u0001"+
		"\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f2\u00f3\u0001"+
		"\u0000\u0000\u0000\u00f3\u00f5\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001"+
		"\u0000\u0000\u0000\u00f5\u00f6\u0006 \u0001\u0000\u00f6B\u0001\u0000\u0000"+
		"\u0000\u00f7\u00f8\t\u0000\u0000\u0000\u00f8\u00f9\u0006!\u0002\u0000"+
		"\u00f9\u00fa\u0001\u0000\u0000\u0000\u00fa\u00fb\u0006!\u0000\u0000\u00fb"+
		"D\u0001\u0000\u0000\u0000\b\u0000\u00cf\u00d5\u00da\u00e0\u00e3\u00e8"+
		"\u00f2\u0003\u0000\u0001\u0000\u0006\u0000\u0000\u0001!\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}