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
		HOMEIP=1, USER=2, VERSION=3, ADD=4, MEMBER_BOX=5, BOXES=6, IP=7, CLUSTER=8, 
		REPLICATE=9, INSTALL=10, UNINSTALL=11, EE=12, OS=13, LOAD=14, SET=15, 
		INVOKE=16, STOP=17, INFO=18, KILL=19, CAT=20, GREP=21, RESTART=22, CLEAN=23, 
		SLEEP=24, SAVE=25, EXIT=26, SHOWSSH=27, PROMPT=28, ALL=29, MEMBER=30, 
		CLIENT=31, MEMBER_ALL=32, MEMBER_VAR=33, CLIENT_ALL=34, CLIENT_VAR=35, 
		MEMBERS_ONLY=36, MEMBERS=37, CLIENTS=38, ASSIGN=39, BOOL=40, TRUE=41, 
		FALSE=42, VAR=43, NUMBER=44, WHITESPACE=45, STRING=46, IP_PAIR=47, IP_STR=48, 
		COMMENT=49;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", "CLUSTER", 
		"REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", 
		"STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", "SAVE", 
		"EXIT", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
		"MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", 
		"ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", 
		"STRING", "IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'memberBox'", "'boxes'", 
		"'ip'", "'cluster'", "'replicate'", "'install'", "'uninstall'", "'EE'", 
		"'OS'", "'load'", "'set'", "'invoke'", "'stop'", "'info'", "'kill'", "'cat'", 
		"'grep'", "'restart'", "'clean'", "'sleep'", "'save'", "'exit'", "'showSSH'", 
		"'prompt'", "'*'", "'member'", "'client'", null, null, null, null, "'membersOnly'", 
		"'members'", "'clients'", "'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", 
		"CLUSTER", "REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", 
		"INVOKE", "STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", 
		"SAVE", "EXIT", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
		"MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", 
		"ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "STRING", 
		"IP_PAIR", "IP_STR", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\63\u01b9\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3"+
		"\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3"+
		"\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3"+
		"\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$\3$\3%"+
		"\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'"+
		"\3\'\3\'\3\'\3\'\3\'\3(\3(\3)\3)\5)\u016a\n)\3*\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3+\3+\3,\5,\u0178\n,\3,\7,\u017b\n,\f,\16,\u017e\13,\3-\6-\u0181\n-"+
		"\r-\16-\u0182\3.\6.\u0186\n.\r.\16.\u0187\3.\3.\3/\5/\u018d\n/\3\60\3"+
		"\60\6\60\u0191\n\60\r\60\16\60\u0192\3\60\3\60\3\61\3\61\3\61\3\61\3\62"+
		"\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3\63\3\63\3\63"+
		"\3\63\5\63\u01ab\n\63\3\64\3\64\3\65\3\65\3\65\3\65\7\65\u01b3\n\65\f"+
		"\65\16\65\u01b6\13\65\3\65\3\65\2\2\66\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.["+
		"/]\2_\60a\61c\62e\2g\2i\63\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\5\2\13"+
		"\f\16\17\"\"\13\2\"\"%%,,/<??C_aac|~~\4\2\f\f\17\17\u01bd\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W"+
		"\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2i\3\2"+
		"\2\2\3k\3\2\2\2\5r\3\2\2\2\7w\3\2\2\2\t\177\3\2\2\2\13\u0083\3\2\2\2\r"+
		"\u008d\3\2\2\2\17\u0093\3\2\2\2\21\u0096\3\2\2\2\23\u009e\3\2\2\2\25\u00a8"+
		"\3\2\2\2\27\u00b0\3\2\2\2\31\u00ba\3\2\2\2\33\u00bd\3\2\2\2\35\u00c0\3"+
		"\2\2\2\37\u00c5\3\2\2\2!\u00c9\3\2\2\2#\u00d0\3\2\2\2%\u00d5\3\2\2\2\'"+
		"\u00da\3\2\2\2)\u00df\3\2\2\2+\u00e3\3\2\2\2-\u00e8\3\2\2\2/\u00f0\3\2"+
		"\2\2\61\u00f6\3\2\2\2\63\u00fc\3\2\2\2\65\u0101\3\2\2\2\67\u0106\3\2\2"+
		"\29\u010e\3\2\2\2;\u0115\3\2\2\2=\u0117\3\2\2\2?\u011e\3\2\2\2A\u0125"+
		"\3\2\2\2C\u012e\3\2\2\2E\u0137\3\2\2\2G\u0140\3\2\2\2I\u0149\3\2\2\2K"+
		"\u0155\3\2\2\2M\u015d\3\2\2\2O\u0165\3\2\2\2Q\u0169\3\2\2\2S\u016b\3\2"+
		"\2\2U\u0170\3\2\2\2W\u0177\3\2\2\2Y\u0180\3\2\2\2[\u0185\3\2\2\2]\u018c"+
		"\3\2\2\2_\u018e\3\2\2\2a\u0196\3\2\2\2c\u019a\3\2\2\2e\u01aa\3\2\2\2g"+
		"\u01ac\3\2\2\2i\u01ae\3\2\2\2kl\7j\2\2lm\7q\2\2mn\7o\2\2no\7g\2\2op\7"+
		"K\2\2pq\7r\2\2q\4\3\2\2\2rs\7w\2\2st\7u\2\2tu\7g\2\2uv\7t\2\2v\6\3\2\2"+
		"\2wx\7x\2\2xy\7g\2\2yz\7t\2\2z{\7u\2\2{|\7k\2\2|}\7q\2\2}~\7p\2\2~\b\3"+
		"\2\2\2\177\u0080\7c\2\2\u0080\u0081\7f\2\2\u0081\u0082\7f\2\2\u0082\n"+
		"\3\2\2\2\u0083\u0084\7o\2\2\u0084\u0085\7g\2\2\u0085\u0086\7o\2\2\u0086"+
		"\u0087\7d\2\2\u0087\u0088\7g\2\2\u0088\u0089\7t\2\2\u0089\u008a\7D\2\2"+
		"\u008a\u008b\7q\2\2\u008b\u008c\7z\2\2\u008c\f\3\2\2\2\u008d\u008e\7d"+
		"\2\2\u008e\u008f\7q\2\2\u008f\u0090\7z\2\2\u0090\u0091\7g\2\2\u0091\u0092"+
		"\7u\2\2\u0092\16\3\2\2\2\u0093\u0094\7k\2\2\u0094\u0095\7r\2\2\u0095\20"+
		"\3\2\2\2\u0096\u0097\7e\2\2\u0097\u0098\7n\2\2\u0098\u0099\7w\2\2\u0099"+
		"\u009a\7u\2\2\u009a\u009b\7v\2\2\u009b\u009c\7g\2\2\u009c\u009d\7t\2\2"+
		"\u009d\22\3\2\2\2\u009e\u009f\7t\2\2\u009f\u00a0\7g\2\2\u00a0\u00a1\7"+
		"r\2\2\u00a1\u00a2\7n\2\2\u00a2\u00a3\7k\2\2\u00a3\u00a4\7e\2\2\u00a4\u00a5"+
		"\7c\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7g\2\2\u00a7\24\3\2\2\2\u00a8\u00a9"+
		"\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ac\7v\2\2\u00ac"+
		"\u00ad\7c\2\2\u00ad\u00ae\7n\2\2\u00ae\u00af\7n\2\2\u00af\26\3\2\2\2\u00b0"+
		"\u00b1\7w\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4\7p\2\2"+
		"\u00b4\u00b5\7u\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8"+
		"\7n\2\2\u00b8\u00b9\7n\2\2\u00b9\30\3\2\2\2\u00ba\u00bb\7G\2\2\u00bb\u00bc"+
		"\7G\2\2\u00bc\32\3\2\2\2\u00bd\u00be\7Q\2\2\u00be\u00bf\7U\2\2\u00bf\34"+
		"\3\2\2\2\u00c0\u00c1\7n\2\2\u00c1\u00c2\7q\2\2\u00c2\u00c3\7c\2\2\u00c3"+
		"\u00c4\7f\2\2\u00c4\36\3\2\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7g\2\2\u00c7"+
		"\u00c8\7v\2\2\u00c8 \3\2\2\2\u00c9\u00ca\7k\2\2\u00ca\u00cb\7p\2\2\u00cb"+
		"\u00cc\7x\2\2\u00cc\u00cd\7q\2\2\u00cd\u00ce\7m\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf\"\3\2\2\2\u00d0\u00d1\7u\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7q"+
		"\2\2\u00d3\u00d4\7r\2\2\u00d4$\3\2\2\2\u00d5\u00d6\7k\2\2\u00d6\u00d7"+
		"\7p\2\2\u00d7\u00d8\7h\2\2\u00d8\u00d9\7q\2\2\u00d9&\3\2\2\2\u00da\u00db"+
		"\7m\2\2\u00db\u00dc\7k\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7n\2\2\u00de"+
		"(\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2\7v\2\2\u00e2"+
		"*\3\2\2\2\u00e3\u00e4\7i\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7g\2\2\u00e6"+
		"\u00e7\7r\2\2\u00e7,\3\2\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7g\2\2\u00ea"+
		"\u00eb\7u\2\2\u00eb\u00ec\7v\2\2\u00ec\u00ed\7c\2\2\u00ed\u00ee\7t\2\2"+
		"\u00ee\u00ef\7v\2\2\u00ef.\3\2\2\2\u00f0\u00f1\7e\2\2\u00f1\u00f2\7n\2"+
		"\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7c\2\2\u00f4\u00f5\7p\2\2\u00f5\60\3"+
		"\2\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7n\2\2\u00f8\u00f9\7g\2\2\u00f9"+
		"\u00fa\7g\2\2\u00fa\u00fb\7r\2\2\u00fb\62\3\2\2\2\u00fc\u00fd\7u\2\2\u00fd"+
		"\u00fe\7c\2\2\u00fe\u00ff\7x\2\2\u00ff\u0100\7g\2\2\u0100\64\3\2\2\2\u0101"+
		"\u0102\7g\2\2\u0102\u0103\7z\2\2\u0103\u0104\7k\2\2\u0104\u0105\7v\2\2"+
		"\u0105\66\3\2\2\2\u0106\u0107\7u\2\2\u0107\u0108\7j\2\2\u0108\u0109\7"+
		"q\2\2\u0109\u010a\7y\2\2\u010a\u010b\7U\2\2\u010b\u010c\7U\2\2\u010c\u010d"+
		"\7J\2\2\u010d8\3\2\2\2\u010e\u010f\7r\2\2\u010f\u0110\7t\2\2\u0110\u0111"+
		"\7q\2\2\u0111\u0112\7o\2\2\u0112\u0113\7r\2\2\u0113\u0114\7v\2\2\u0114"+
		":\3\2\2\2\u0115\u0116\7,\2\2\u0116<\3\2\2\2\u0117\u0118\7o\2\2\u0118\u0119"+
		"\7g\2\2\u0119\u011a\7o\2\2\u011a\u011b\7d\2\2\u011b\u011c\7g\2\2\u011c"+
		"\u011d\7t\2\2\u011d>\3\2\2\2\u011e\u011f\7e\2\2\u011f\u0120\7n\2\2\u0120"+
		"\u0121\7k\2\2\u0121\u0122\7g\2\2\u0122\u0123\7p\2\2\u0123\u0124\7v\2\2"+
		"\u0124@\3\2\2\2\u0125\u0126\7O\2\2\u0126\u0127\7g\2\2\u0127\u0128\7o\2"+
		"\2\u0128\u0129\7d\2\2\u0129\u012a\7g\2\2\u012a\u012b\7t\2\2\u012b\u012c"+
		"\3\2\2\2\u012c\u012d\5;\36\2\u012dB\3\2\2\2\u012e\u012f\7O\2\2\u012f\u0130"+
		"\7g\2\2\u0130\u0131\7o\2\2\u0131\u0132\7d\2\2\u0132\u0133\7g\2\2\u0133"+
		"\u0134\7t\2\2\u0134\u0135\3\2\2\2\u0135\u0136\5Y-\2\u0136D\3\2\2\2\u0137"+
		"\u0138\7E\2\2\u0138\u0139\7n\2\2\u0139\u013a\7k\2\2\u013a\u013b\7g\2\2"+
		"\u013b\u013c\7p\2\2\u013c\u013d\7v\2\2\u013d\u013e\3\2\2\2\u013e\u013f"+
		"\5;\36\2\u013fF\3\2\2\2\u0140\u0141\7E\2\2\u0141\u0142\7n\2\2\u0142\u0143"+
		"\7k\2\2\u0143\u0144\7g\2\2\u0144\u0145\7p\2\2\u0145\u0146\7v\2\2\u0146"+
		"\u0147\3\2\2\2\u0147\u0148\5Y-\2\u0148H\3\2\2\2\u0149\u014a\7o\2\2\u014a"+
		"\u014b\7g\2\2\u014b\u014c\7o\2\2\u014c\u014d\7d\2\2\u014d\u014e\7g\2\2"+
		"\u014e\u014f\7t\2\2\u014f\u0150\7u\2\2\u0150\u0151\7Q\2\2\u0151\u0152"+
		"\7p\2\2\u0152\u0153\7n\2\2\u0153\u0154\7{\2\2\u0154J\3\2\2\2\u0155\u0156"+
		"\7o\2\2\u0156\u0157\7g\2\2\u0157\u0158\7o\2\2\u0158\u0159\7d\2\2\u0159"+
		"\u015a\7g\2\2\u015a\u015b\7t\2\2\u015b\u015c\7u\2\2\u015cL\3\2\2\2\u015d"+
		"\u015e\7e\2\2\u015e\u015f\7n\2\2\u015f\u0160\7k\2\2\u0160\u0161\7g\2\2"+
		"\u0161\u0162\7p\2\2\u0162\u0163\7v\2\2\u0163\u0164\7u\2\2\u0164N\3\2\2"+
		"\2\u0165\u0166\7?\2\2\u0166P\3\2\2\2\u0167\u016a\5S*\2\u0168\u016a\5U"+
		"+\2\u0169\u0167\3\2\2\2\u0169\u0168\3\2\2\2\u016aR\3\2\2\2\u016b\u016c"+
		"\7v\2\2\u016c\u016d\7t\2\2\u016d\u016e\7w\2\2\u016e\u016f\7g\2\2\u016f"+
		"T\3\2\2\2\u0170\u0171\7h\2\2\u0171\u0172\7c\2\2\u0172\u0173\7n\2\2\u0173"+
		"\u0174\7u\2\2\u0174\u0175\7g\2\2\u0175V\3\2\2\2\u0176\u0178\t\2\2\2\u0177"+
		"\u0176\3\2\2\2\u0178\u017c\3\2\2\2\u0179\u017b\t\3\2\2\u017a\u0179\3\2"+
		"\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d"+
		"X\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0181\t\4\2\2\u0180\u017f\3\2\2\2"+
		"\u0181\u0182\3\2\2\2\u0182\u0180\3\2\2\2\u0182\u0183\3\2\2\2\u0183Z\3"+
		"\2\2\2\u0184\u0186\t\5\2\2\u0185\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188\u0189\3\2\2\2\u0189\u018a\b."+
		"\2\2\u018a\\\3\2\2\2\u018b\u018d\t\6\2\2\u018c\u018b\3\2\2\2\u018d^\3"+
		"\2\2\2\u018e\u0190\7$\2\2\u018f\u0191\5]/\2\u0190\u018f\3\2\2\2\u0191"+
		"\u0192\3\2\2\2\u0192\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\3\2"+
		"\2\2\u0194\u0195\7$\2\2\u0195`\3\2\2\2\u0196\u0197\5c\62\2\u0197\u0198"+
		"\7.\2\2\u0198\u0199\5c\62\2\u0199b\3\2\2\2\u019a\u019b\5e\63\2\u019b\u019c"+
		"\7\60\2\2\u019c\u019d\5e\63\2\u019d\u019e\7\60\2\2\u019e\u019f\5e\63\2"+
		"\u019f\u01a0\7\60\2\2\u01a0\u01a1\5e\63\2\u01a1d\3\2\2\2\u01a2\u01a3\5"+
		"g\64\2\u01a3\u01a4\5g\64\2\u01a4\u01a5\5g\64\2\u01a5\u01ab\3\2\2\2\u01a6"+
		"\u01a7\5g\64\2\u01a7\u01a8\5g\64\2\u01a8\u01ab\3\2\2\2\u01a9\u01ab\5g"+
		"\64\2\u01aa\u01a2\3\2\2\2\u01aa\u01a6\3\2\2\2\u01aa\u01a9\3\2\2\2\u01ab"+
		"f\3\2\2\2\u01ac\u01ad\4\62;\2\u01adh\3\2\2\2\u01ae\u01af\7\61\2\2\u01af"+
		"\u01b0\7\61\2\2\u01b0\u01b4\3\2\2\2\u01b1\u01b3\n\7\2\2\u01b2\u01b1\3"+
		"\2\2\2\u01b3\u01b6\3\2\2\2\u01b4\u01b2\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5"+
		"\u01b7\3\2\2\2\u01b6\u01b4\3\2\2\2\u01b7\u01b8\b\65\2\2\u01b8j\3\2\2\2"+
		"\r\2\u0169\u0177\u017a\u017c\u0182\u0187\u018c\u0192\u01aa\u01b4\3\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}