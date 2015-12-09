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
		HOMEIP=1, USER=2, VERSION=3, ADD=4, BOXES=5, IP=6, CLUSTER=7, REPLICATE=8, 
		INSTALL=9, UNINSTALL=10, EE=11, OS=12, LOAD=13, SET=14, INVOKE=15, INFO=16, 
		KILL=17, CAT=18, RESTART=19, CLEAN=20, SLEEP=21, SAVE=22, EXIT=23, SHOWSSH=24, 
		PROMPT=25, ALL=26, MEMBER=27, CLIENT=28, MEMBER_ALL=29, MEMBER_VAR=30, 
		CLIENT_ALL=31, CLIENT_VAR=32, MEMBERS_ONLY=33, MEMBERS=34, CLIENTS=35, 
		ASSIGN=36, BOOL=37, TRUE=38, FALSE=39, VAR=40, NUMBER=41, WHITESPACE=42, 
		STRING=43, IP_PAIR=44, IP_STR=45, COMMENT=46;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "BOXES", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", 
		"CAT", "RESTART", "CLEAN", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "PROMPT", 
		"ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", 
		"MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", "BOOL", "TRUE", "FALSE", 
		"VAR", "NUMBER", "WHITESPACE", "CHAR", "STRING", "IP_PAIR", "IP_STR", 
		"Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'boxes'", "'ip'", "'cluster'", 
		"'replicate'", "'install'", "'uninstall'", "'EE'", "'OS'", "'load'", "'set'", 
		"'invoke'", "'info'", "'kill'", "'cat'", "'restart'", "'clean'", "'sleep'", 
		"'save'", "'exit'", "'showSSH'", "'prompt'", "'*'", "'member'", "'client'", 
		null, null, null, null, "'membersOnly'", "'members'", "'clients'", "'='", 
		null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "BOXES", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", 
		"CAT", "RESTART", "CLEAN", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "PROMPT", 
		"ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", 
		"MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", "BOOL", "TRUE", "FALSE", 
		"VAR", "NUMBER", "WHITESPACE", "STRING", "IP_PAIR", "IP_STR", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\60\u019f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3%\3%\3&\3&\5&\u0150\n&\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3("+
		"\3(\3)\5)\u015e\n)\3)\7)\u0161\n)\f)\16)\u0164\13)\3*\6*\u0167\n*\r*\16"+
		"*\u0168\3+\6+\u016c\n+\r+\16+\u016d\3+\3+\3,\5,\u0173\n,\3-\3-\6-\u0177"+
		"\n-\r-\16-\u0178\3-\3-\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\3\60\3\60\3"+
		"\60\3\60\3\60\3\60\3\60\3\60\5\60\u0191\n\60\3\61\3\61\3\62\3\62\3\62"+
		"\3\62\7\62\u0199\n\62\f\62\16\62\u019c\13\62\3\62\3\62\2\2\63\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C"+
		"#E$G%I&K\'M(O)Q*S+U,W\2Y-[.]/_\2a\2c\60\3\2\b\4\2C\\c|\6\2\62;C\\aac|"+
		"\3\2\62;\5\2\13\f\16\17\"\"\b\2\"\"/;??C\\aac|\4\2\f\f\17\17\u01a3\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3"+
		"\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2c\3\2\2\2\3e\3\2\2\2\5l\3\2\2"+
		"\2\7q\3\2\2\2\ty\3\2\2\2\13}\3\2\2\2\r\u0083\3\2\2\2\17\u0086\3\2\2\2"+
		"\21\u008e\3\2\2\2\23\u0098\3\2\2\2\25\u00a0\3\2\2\2\27\u00aa\3\2\2\2\31"+
		"\u00ad\3\2\2\2\33\u00b0\3\2\2\2\35\u00b5\3\2\2\2\37\u00b9\3\2\2\2!\u00c0"+
		"\3\2\2\2#\u00c5\3\2\2\2%\u00ca\3\2\2\2\'\u00ce\3\2\2\2)\u00d6\3\2\2\2"+
		"+\u00dc\3\2\2\2-\u00e2\3\2\2\2/\u00e7\3\2\2\2\61\u00ec\3\2\2\2\63\u00f4"+
		"\3\2\2\2\65\u00fb\3\2\2\2\67\u00fd\3\2\2\29\u0104\3\2\2\2;\u010b\3\2\2"+
		"\2=\u0114\3\2\2\2?\u011d\3\2\2\2A\u0126\3\2\2\2C\u012f\3\2\2\2E\u013b"+
		"\3\2\2\2G\u0143\3\2\2\2I\u014b\3\2\2\2K\u014f\3\2\2\2M\u0151\3\2\2\2O"+
		"\u0156\3\2\2\2Q\u015d\3\2\2\2S\u0166\3\2\2\2U\u016b\3\2\2\2W\u0172\3\2"+
		"\2\2Y\u0174\3\2\2\2[\u017c\3\2\2\2]\u0180\3\2\2\2_\u0190\3\2\2\2a\u0192"+
		"\3\2\2\2c\u0194\3\2\2\2ef\7j\2\2fg\7q\2\2gh\7o\2\2hi\7g\2\2ij\7K\2\2j"+
		"k\7r\2\2k\4\3\2\2\2lm\7w\2\2mn\7u\2\2no\7g\2\2op\7t\2\2p\6\3\2\2\2qr\7"+
		"x\2\2rs\7g\2\2st\7t\2\2tu\7u\2\2uv\7k\2\2vw\7q\2\2wx\7p\2\2x\b\3\2\2\2"+
		"yz\7c\2\2z{\7f\2\2{|\7f\2\2|\n\3\2\2\2}~\7d\2\2~\177\7q\2\2\177\u0080"+
		"\7z\2\2\u0080\u0081\7g\2\2\u0081\u0082\7u\2\2\u0082\f\3\2\2\2\u0083\u0084"+
		"\7k\2\2\u0084\u0085\7r\2\2\u0085\16\3\2\2\2\u0086\u0087\7e\2\2\u0087\u0088"+
		"\7n\2\2\u0088\u0089\7w\2\2\u0089\u008a\7u\2\2\u008a\u008b\7v\2\2\u008b"+
		"\u008c\7g\2\2\u008c\u008d\7t\2\2\u008d\20\3\2\2\2\u008e\u008f\7t\2\2\u008f"+
		"\u0090\7g\2\2\u0090\u0091\7r\2\2\u0091\u0092\7n\2\2\u0092\u0093\7k\2\2"+
		"\u0093\u0094\7e\2\2\u0094\u0095\7c\2\2\u0095\u0096\7v\2\2\u0096\u0097"+
		"\7g\2\2\u0097\22\3\2\2\2\u0098\u0099\7k\2\2\u0099\u009a\7p\2\2\u009a\u009b"+
		"\7u\2\2\u009b\u009c\7v\2\2\u009c\u009d\7c\2\2\u009d\u009e\7n\2\2\u009e"+
		"\u009f\7n\2\2\u009f\24\3\2\2\2\u00a0\u00a1\7w\2\2\u00a1\u00a2\7p\2\2\u00a2"+
		"\u00a3\7k\2\2\u00a3\u00a4\7p\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7v\2\2"+
		"\u00a6\u00a7\7c\2\2\u00a7\u00a8\7n\2\2\u00a8\u00a9\7n\2\2\u00a9\26\3\2"+
		"\2\2\u00aa\u00ab\7G\2\2\u00ab\u00ac\7G\2\2\u00ac\30\3\2\2\2\u00ad\u00ae"+
		"\7Q\2\2\u00ae\u00af\7U\2\2\u00af\32\3\2\2\2\u00b0\u00b1\7n\2\2\u00b1\u00b2"+
		"\7q\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7f\2\2\u00b4\34\3\2\2\2\u00b5\u00b6"+
		"\7u\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7v\2\2\u00b8\36\3\2\2\2\u00b9\u00ba"+
		"\7k\2\2\u00ba\u00bb\7p\2\2\u00bb\u00bc\7x\2\2\u00bc\u00bd\7q\2\2\u00bd"+
		"\u00be\7m\2\2\u00be\u00bf\7g\2\2\u00bf \3\2\2\2\u00c0\u00c1\7k\2\2\u00c1"+
		"\u00c2\7p\2\2\u00c2\u00c3\7h\2\2\u00c3\u00c4\7q\2\2\u00c4\"\3\2\2\2\u00c5"+
		"\u00c6\7m\2\2\u00c6\u00c7\7k\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7n\2\2"+
		"\u00c9$\3\2\2\2\u00ca\u00cb\7e\2\2\u00cb\u00cc\7c\2\2\u00cc\u00cd\7v\2"+
		"\2\u00cd&\3\2\2\2\u00ce\u00cf\7t\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7"+
		"u\2\2\u00d1\u00d2\7v\2\2\u00d2\u00d3\7c\2\2\u00d3\u00d4\7t\2\2\u00d4\u00d5"+
		"\7v\2\2\u00d5(\3\2\2\2\u00d6\u00d7\7e\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9"+
		"\7g\2\2\u00d9\u00da\7c\2\2\u00da\u00db\7p\2\2\u00db*\3\2\2\2\u00dc\u00dd"+
		"\7u\2\2\u00dd\u00de\7n\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7g\2\2\u00e0"+
		"\u00e1\7r\2\2\u00e1,\3\2\2\2\u00e2\u00e3\7u\2\2\u00e3\u00e4\7c\2\2\u00e4"+
		"\u00e5\7x\2\2\u00e5\u00e6\7g\2\2\u00e6.\3\2\2\2\u00e7\u00e8\7g\2\2\u00e8"+
		"\u00e9\7z\2\2\u00e9\u00ea\7k\2\2\u00ea\u00eb\7v\2\2\u00eb\60\3\2\2\2\u00ec"+
		"\u00ed\7u\2\2\u00ed\u00ee\7j\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0\7y\2\2"+
		"\u00f0\u00f1\7U\2\2\u00f1\u00f2\7U\2\2\u00f2\u00f3\7J\2\2\u00f3\62\3\2"+
		"\2\2\u00f4\u00f5\7r\2\2\u00f5\u00f6\7t\2\2\u00f6\u00f7\7q\2\2\u00f7\u00f8"+
		"\7o\2\2\u00f8\u00f9\7r\2\2\u00f9\u00fa\7v\2\2\u00fa\64\3\2\2\2\u00fb\u00fc"+
		"\7,\2\2\u00fc\66\3\2\2\2\u00fd\u00fe\7o\2\2\u00fe\u00ff\7g\2\2\u00ff\u0100"+
		"\7o\2\2\u0100\u0101\7d\2\2\u0101\u0102\7g\2\2\u0102\u0103\7t\2\2\u0103"+
		"8\3\2\2\2\u0104\u0105\7e\2\2\u0105\u0106\7n\2\2\u0106\u0107\7k\2\2\u0107"+
		"\u0108\7g\2\2\u0108\u0109\7p\2\2\u0109\u010a\7v\2\2\u010a:\3\2\2\2\u010b"+
		"\u010c\7O\2\2\u010c\u010d\7g\2\2\u010d\u010e\7o\2\2\u010e\u010f\7d\2\2"+
		"\u010f\u0110\7g\2\2\u0110\u0111\7t\2\2\u0111\u0112\3\2\2\2\u0112\u0113"+
		"\5\65\33\2\u0113<\3\2\2\2\u0114\u0115\7O\2\2\u0115\u0116\7g\2\2\u0116"+
		"\u0117\7o\2\2\u0117\u0118\7d\2\2\u0118\u0119\7g\2\2\u0119\u011a\7t\2\2"+
		"\u011a\u011b\3\2\2\2\u011b\u011c\5S*\2\u011c>\3\2\2\2\u011d\u011e\7E\2"+
		"\2\u011e\u011f\7n\2\2\u011f\u0120\7k\2\2\u0120\u0121\7g\2\2\u0121\u0122"+
		"\7p\2\2\u0122\u0123\7v\2\2\u0123\u0124\3\2\2\2\u0124\u0125\5\65\33\2\u0125"+
		"@\3\2\2\2\u0126\u0127\7E\2\2\u0127\u0128\7n\2\2\u0128\u0129\7k\2\2\u0129"+
		"\u012a\7g\2\2\u012a\u012b\7p\2\2\u012b\u012c\7v\2\2\u012c\u012d\3\2\2"+
		"\2\u012d\u012e\5S*\2\u012eB\3\2\2\2\u012f\u0130\7o\2\2\u0130\u0131\7g"+
		"\2\2\u0131\u0132\7o\2\2\u0132\u0133\7d\2\2\u0133\u0134\7g\2\2\u0134\u0135"+
		"\7t\2\2\u0135\u0136\7u\2\2\u0136\u0137\7Q\2\2\u0137\u0138\7p\2\2\u0138"+
		"\u0139\7n\2\2\u0139\u013a\7{\2\2\u013aD\3\2\2\2\u013b\u013c\7o\2\2\u013c"+
		"\u013d\7g\2\2\u013d\u013e\7o\2\2\u013e\u013f\7d\2\2\u013f\u0140\7g\2\2"+
		"\u0140\u0141\7t\2\2\u0141\u0142\7u\2\2\u0142F\3\2\2\2\u0143\u0144\7e\2"+
		"\2\u0144\u0145\7n\2\2\u0145\u0146\7k\2\2\u0146\u0147\7g\2\2\u0147\u0148"+
		"\7p\2\2\u0148\u0149\7v\2\2\u0149\u014a\7u\2\2\u014aH\3\2\2\2\u014b\u014c"+
		"\7?\2\2\u014cJ\3\2\2\2\u014d\u0150\5M\'\2\u014e\u0150\5O(\2\u014f\u014d"+
		"\3\2\2\2\u014f\u014e\3\2\2\2\u0150L\3\2\2\2\u0151\u0152\7v\2\2\u0152\u0153"+
		"\7t\2\2\u0153\u0154\7w\2\2\u0154\u0155\7g\2\2\u0155N\3\2\2\2\u0156\u0157"+
		"\7h\2\2\u0157\u0158\7c\2\2\u0158\u0159\7n\2\2\u0159\u015a\7u\2\2\u015a"+
		"\u015b\7g\2\2\u015bP\3\2\2\2\u015c\u015e\t\2\2\2\u015d\u015c\3\2\2\2\u015e"+
		"\u0162\3\2\2\2\u015f\u0161\t\3\2\2\u0160\u015f\3\2\2\2\u0161\u0164\3\2"+
		"\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163R\3\2\2\2\u0164\u0162"+
		"\3\2\2\2\u0165\u0167\t\4\2\2\u0166\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168"+
		"\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169T\3\2\2\2\u016a\u016c\t\5\2\2"+
		"\u016b\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016b\3\2\2\2\u016d\u016e"+
		"\3\2\2\2\u016e\u016f\3\2\2\2\u016f\u0170\b+\2\2\u0170V\3\2\2\2\u0171\u0173"+
		"\t\6\2\2\u0172\u0171\3\2\2\2\u0173X\3\2\2\2\u0174\u0176\7$\2\2\u0175\u0177"+
		"\5W,\2\u0176\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\u0176\3\2\2\2\u0178"+
		"\u0179\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017b\7$\2\2\u017bZ\3\2\2\2\u017c"+
		"\u017d\5]/\2\u017d\u017e\7.\2\2\u017e\u017f\5]/\2\u017f\\\3\2\2\2\u0180"+
		"\u0181\5_\60\2\u0181\u0182\7\60\2\2\u0182\u0183\5_\60\2\u0183\u0184\7"+
		"\60\2\2\u0184\u0185\5_\60\2\u0185\u0186\7\60\2\2\u0186\u0187\5_\60\2\u0187"+
		"^\3\2\2\2\u0188\u0189\5a\61\2\u0189\u018a\5a\61\2\u018a\u018b\5a\61\2"+
		"\u018b\u0191\3\2\2\2\u018c\u018d\5a\61\2\u018d\u018e\5a\61\2\u018e\u0191"+
		"\3\2\2\2\u018f\u0191\5a\61\2\u0190\u0188\3\2\2\2\u0190\u018c\3\2\2\2\u0190"+
		"\u018f\3\2\2\2\u0191`\3\2\2\2\u0192\u0193\4\62;\2\u0193b\3\2\2\2\u0194"+
		"\u0195\7\61\2\2\u0195\u0196\7\61\2\2\u0196\u019a\3\2\2\2\u0197\u0199\n"+
		"\7\2\2\u0198\u0197\3\2\2\2\u0199\u019c\3\2\2\2\u019a\u0198\3\2\2\2\u019a"+
		"\u019b\3\2\2\2\u019b\u019d\3\2\2\2\u019c\u019a\3\2\2\2\u019d\u019e\b\62"+
		"\2\2\u019ed\3\2\2\2\r\2\u014f\u015d\u0160\u0162\u0168\u016d\u0172\u0178"+
		"\u0190\u019a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}