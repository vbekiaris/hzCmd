// Generated from HzCmd.g4 by ANTLR 4.5
package antlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HzCmdLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		USER=1, VERSION=2, EE=3, KILL=4, CLUSTER=5, TERMINAL_FUNCTION=6, BOOL=7, 
		TRUE=8, FALSE=9, ID=10, TEXT=11, NUMBER=12, STRING=13, WHITESPACE=14, 
		COMMENT=15;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"USER", "VERSION", "EE", "KILL", "CLUSTER", "TERMINAL_FUNCTION", "BOOL", 
		"TRUE", "FALSE", "ID", "TEXT", "NUMBER", "STRING", "WHITESPACE", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'user'", "'version'", "'ee'", "'kill'", "'cluster'", null, null, 
		"'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "USER", "VERSION", "EE", "KILL", "CLUSTER", "TERMINAL_FUNCTION", 
		"BOOL", "TRUE", "FALSE", "ID", "TEXT", "NUMBER", "STRING", "WHITESPACE", 
		"COMMENT"
	};
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


	public HzCmdLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "HzCmd.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\21\u0087\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\5\7O\n\7\3\b\3\b\5\bS\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\7\13b\n\13\f\13\16\13e\13\13\3\f\6"+
		"\fh\n\f\r\f\16\fi\3\r\6\rm\n\r\r\r\16\rn\3\16\6\16r\n\16\r\16\16\16s\3"+
		"\17\6\17w\n\17\r\17\16\17x\3\17\3\17\3\20\3\20\3\20\3\20\7\20\u0081\n"+
		"\20\f\20\16\20\u0084\13\20\3\20\3\20\2\2\21\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21\3\2\b\3\2c|\5\2\62;C\\"+
		"c|\3\2\62;\7\2/\60\62;C\\aac|\5\2\13\f\16\17\"\"\4\2\f\f\17\17\u008e\2"+
		"\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2"+
		"\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2"+
		"\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\3!\3\2\2\2\5&\3\2\2"+
		"\2\7.\3\2\2\2\t\61\3\2\2\2\13\66\3\2\2\2\rN\3\2\2\2\17R\3\2\2\2\21T\3"+
		"\2\2\2\23Y\3\2\2\2\25_\3\2\2\2\27g\3\2\2\2\31l\3\2\2\2\33q\3\2\2\2\35"+
		"v\3\2\2\2\37|\3\2\2\2!\"\7w\2\2\"#\7u\2\2#$\7g\2\2$%\7t\2\2%\4\3\2\2\2"+
		"&\'\7x\2\2\'(\7g\2\2()\7t\2\2)*\7u\2\2*+\7k\2\2+,\7q\2\2,-\7p\2\2-\6\3"+
		"\2\2\2./\7g\2\2/\60\7g\2\2\60\b\3\2\2\2\61\62\7m\2\2\62\63\7k\2\2\63\64"+
		"\7n\2\2\64\65\7n\2\2\65\n\3\2\2\2\66\67\7e\2\2\678\7n\2\289\7w\2\29:\7"+
		"u\2\2:;\7v\2\2;<\7g\2\2<=\7t\2\2=\f\3\2\2\2>?\7k\2\2?@\7p\2\2@A\7u\2\2"+
		"AB\7v\2\2BC\7c\2\2CD\7n\2\2DO\7n\2\2EF\7w\2\2FG\7p\2\2GH\7k\2\2HI\7p\2"+
		"\2IJ\7u\2\2JK\7v\2\2KL\7c\2\2LM\7n\2\2MO\7n\2\2N>\3\2\2\2NE\3\2\2\2O\16"+
		"\3\2\2\2PS\5\21\t\2QS\5\23\n\2RP\3\2\2\2RQ\3\2\2\2S\20\3\2\2\2TU\7v\2"+
		"\2UV\7t\2\2VW\7w\2\2WX\7g\2\2X\22\3\2\2\2YZ\7h\2\2Z[\7c\2\2[\\\7n\2\2"+
		"\\]\7u\2\2]^\7g\2\2^\24\3\2\2\2_c\t\2\2\2`b\t\3\2\2a`\3\2\2\2be\3\2\2"+
		"\2ca\3\2\2\2cd\3\2\2\2d\26\3\2\2\2ec\3\2\2\2fh\t\2\2\2gf\3\2\2\2hi\3\2"+
		"\2\2ig\3\2\2\2ij\3\2\2\2j\30\3\2\2\2km\t\4\2\2lk\3\2\2\2mn\3\2\2\2nl\3"+
		"\2\2\2no\3\2\2\2o\32\3\2\2\2pr\t\5\2\2qp\3\2\2\2rs\3\2\2\2sq\3\2\2\2s"+
		"t\3\2\2\2t\34\3\2\2\2uw\t\6\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2\2xy\3\2\2"+
		"\2yz\3\2\2\2z{\b\17\2\2{\36\3\2\2\2|}\7\61\2\2}~\7\61\2\2~\u0082\3\2\2"+
		"\2\177\u0081\n\7\2\2\u0080\177\3\2\2\2\u0081\u0084\3\2\2\2\u0082\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0085\3\2\2\2\u0084\u0082\3\2\2\2\u0085"+
		"\u0086\b\20\2\2\u0086 \3\2\2\2\13\2NRcinsx\u0082\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}