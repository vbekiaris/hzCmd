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
		HOMEIP=1, USER=2, VERSION=3, ADD=4, FILE=5, IP=6, CLUSTER=7, REPLICATE=8, 
		INSTALL=9, EE=10, OS=11, LOAD=12, SET=13, INVOKE=14, KILL=15, CAT=16, 
		RESTART=17, SLEEP=18, SAVE=19, EXIT=20, SHOWSSH=21, ALL=22, MEMBER=23, 
		CLIENT=24, MEMBER_ALL=25, MEMBER_VAR=26, CLIENT_ALL=27, CLIENT_VAR=28, 
		MEMBERS_ONLY=29, MEMBERS=30, CLIENTS=31, ASSIGN=32, BOOL=33, TRUE=34, 
		FALSE=35, VAR=36, NUMBER=37, WHITESPACE=38, STRING=39, IP_PAIR=40, IP_STR=41, 
		COMMENT=42;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "KILL", "CAT", "RESTART", 
		"SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
		"MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", 
		"ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", 
		"STRING", "IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'file'", "'ip'", "'cluster'", 
		"'replicate'", "'install'", "'EE'", "'OS'", "'load'", "'set'", "'invoke'", 
		"'kill'", "'cat'", "'restart'", "'sleep'", "'save'", "'exit'", "'showSSH'", 
		"'*'", "'member'", "'client'", null, null, null, null, "'membersOnly'", 
		"'members'", "'clients'", "'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "KILL", "CAT", "RESTART", 
		"SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2,\u017a\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3\"\3\"\5\"\u012b\n\"\3#\3#\3#\3#"+
		"\3#\3$\3$\3$\3$\3$\3$\3%\5%\u0139\n%\3%\7%\u013c\n%\f%\16%\u013f\13%\3"+
		"&\6&\u0142\n&\r&\16&\u0143\3\'\6\'\u0147\n\'\r\'\16\'\u0148\3\'\3\'\3"+
		"(\5(\u014e\n(\3)\3)\6)\u0152\n)\r)\16)\u0153\3)\3)\3*\3*\3*\3*\3+\3+\3"+
		"+\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\5,\u016c\n,\3-\3-\3.\3.\3.\3"+
		".\7.\u0174\n.\f.\16.\u0177\13.\3.\3.\2\2/\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O\2Q)S"+
		"*U+W\2Y\2[,\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\16\17\"\"\b"+
		"\2\"\"/;??C\\aac|\4\2\f\f\17\17\u017e\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2"+
		"\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2[\3\2\2\2\3]\3\2\2\2\5d\3\2\2\2\7"+
		"i\3\2\2\2\tq\3\2\2\2\13u\3\2\2\2\rz\3\2\2\2\17}\3\2\2\2\21\u0085\3\2\2"+
		"\2\23\u008f\3\2\2\2\25\u0097\3\2\2\2\27\u009a\3\2\2\2\31\u009d\3\2\2\2"+
		"\33\u00a2\3\2\2\2\35\u00a6\3\2\2\2\37\u00ad\3\2\2\2!\u00b2\3\2\2\2#\u00b6"+
		"\3\2\2\2%\u00be\3\2\2\2\'\u00c4\3\2\2\2)\u00c9\3\2\2\2+\u00ce\3\2\2\2"+
		"-\u00d6\3\2\2\2/\u00d8\3\2\2\2\61\u00df\3\2\2\2\63\u00e6\3\2\2\2\65\u00ef"+
		"\3\2\2\2\67\u00f8\3\2\2\29\u0101\3\2\2\2;\u010a\3\2\2\2=\u0116\3\2\2\2"+
		"?\u011e\3\2\2\2A\u0126\3\2\2\2C\u012a\3\2\2\2E\u012c\3\2\2\2G\u0131\3"+
		"\2\2\2I\u0138\3\2\2\2K\u0141\3\2\2\2M\u0146\3\2\2\2O\u014d\3\2\2\2Q\u014f"+
		"\3\2\2\2S\u0157\3\2\2\2U\u015b\3\2\2\2W\u016b\3\2\2\2Y\u016d\3\2\2\2["+
		"\u016f\3\2\2\2]^\7j\2\2^_\7q\2\2_`\7o\2\2`a\7g\2\2ab\7K\2\2bc\7r\2\2c"+
		"\4\3\2\2\2de\7w\2\2ef\7u\2\2fg\7g\2\2gh\7t\2\2h\6\3\2\2\2ij\7x\2\2jk\7"+
		"g\2\2kl\7t\2\2lm\7u\2\2mn\7k\2\2no\7q\2\2op\7p\2\2p\b\3\2\2\2qr\7c\2\2"+
		"rs\7f\2\2st\7f\2\2t\n\3\2\2\2uv\7h\2\2vw\7k\2\2wx\7n\2\2xy\7g\2\2y\f\3"+
		"\2\2\2z{\7k\2\2{|\7r\2\2|\16\3\2\2\2}~\7e\2\2~\177\7n\2\2\177\u0080\7"+
		"w\2\2\u0080\u0081\7u\2\2\u0081\u0082\7v\2\2\u0082\u0083\7g\2\2\u0083\u0084"+
		"\7t\2\2\u0084\20\3\2\2\2\u0085\u0086\7t\2\2\u0086\u0087\7g\2\2\u0087\u0088"+
		"\7r\2\2\u0088\u0089\7n\2\2\u0089\u008a\7k\2\2\u008a\u008b\7e\2\2\u008b"+
		"\u008c\7c\2\2\u008c\u008d\7v\2\2\u008d\u008e\7g\2\2\u008e\22\3\2\2\2\u008f"+
		"\u0090\7k\2\2\u0090\u0091\7p\2\2\u0091\u0092\7u\2\2\u0092\u0093\7v\2\2"+
		"\u0093\u0094\7c\2\2\u0094\u0095\7n\2\2\u0095\u0096\7n\2\2\u0096\24\3\2"+
		"\2\2\u0097\u0098\7G\2\2\u0098\u0099\7G\2\2\u0099\26\3\2\2\2\u009a\u009b"+
		"\7Q\2\2\u009b\u009c\7U\2\2\u009c\30\3\2\2\2\u009d\u009e\7n\2\2\u009e\u009f"+
		"\7q\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7f\2\2\u00a1\32\3\2\2\2\u00a2\u00a3"+
		"\7u\2\2\u00a3\u00a4\7g\2\2\u00a4\u00a5\7v\2\2\u00a5\34\3\2\2\2\u00a6\u00a7"+
		"\7k\2\2\u00a7\u00a8\7p\2\2\u00a8\u00a9\7x\2\2\u00a9\u00aa\7q\2\2\u00aa"+
		"\u00ab\7m\2\2\u00ab\u00ac\7g\2\2\u00ac\36\3\2\2\2\u00ad\u00ae\7m\2\2\u00ae"+
		"\u00af\7k\2\2\u00af\u00b0\7n\2\2\u00b0\u00b1\7n\2\2\u00b1 \3\2\2\2\u00b2"+
		"\u00b3\7e\2\2\u00b3\u00b4\7c\2\2\u00b4\u00b5\7v\2\2\u00b5\"\3\2\2\2\u00b6"+
		"\u00b7\7t\2\2\u00b7\u00b8\7g\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba\7v\2\2"+
		"\u00ba\u00bb\7c\2\2\u00bb\u00bc\7t\2\2\u00bc\u00bd\7v\2\2\u00bd$\3\2\2"+
		"\2\u00be\u00bf\7u\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2"+
		"\7g\2\2\u00c2\u00c3\7r\2\2\u00c3&\3\2\2\2\u00c4\u00c5\7u\2\2\u00c5\u00c6"+
		"\7c\2\2\u00c6\u00c7\7x\2\2\u00c7\u00c8\7g\2\2\u00c8(\3\2\2\2\u00c9\u00ca"+
		"\7g\2\2\u00ca\u00cb\7z\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7v\2\2\u00cd"+
		"*\3\2\2\2\u00ce\u00cf\7u\2\2\u00cf\u00d0\7j\2\2\u00d0\u00d1\7q\2\2\u00d1"+
		"\u00d2\7y\2\2\u00d2\u00d3\7U\2\2\u00d3\u00d4\7U\2\2\u00d4\u00d5\7J\2\2"+
		"\u00d5,\3\2\2\2\u00d6\u00d7\7,\2\2\u00d7.\3\2\2\2\u00d8\u00d9\7o\2\2\u00d9"+
		"\u00da\7g\2\2\u00da\u00db\7o\2\2\u00db\u00dc\7d\2\2\u00dc\u00dd\7g\2\2"+
		"\u00dd\u00de\7t\2\2\u00de\60\3\2\2\2\u00df\u00e0\7e\2\2\u00e0\u00e1\7"+
		"n\2\2\u00e1\u00e2\7k\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5"+
		"\7v\2\2\u00e5\62\3\2\2\2\u00e6\u00e7\7O\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9"+
		"\7o\2\2\u00e9\u00ea\7d\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7t\2\2\u00ec"+
		"\u00ed\3\2\2\2\u00ed\u00ee\5-\27\2\u00ee\64\3\2\2\2\u00ef\u00f0\7O\2\2"+
		"\u00f0\u00f1\7g\2\2\u00f1\u00f2\7o\2\2\u00f2\u00f3\7d\2\2\u00f3\u00f4"+
		"\7g\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\5K&\2\u00f7"+
		"\66\3\2\2\2\u00f8\u00f9\7E\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7k\2\2\u00fb"+
		"\u00fc\7g\2\2\u00fc\u00fd\7p\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\3\2\2"+
		"\2\u00ff\u0100\5-\27\2\u01008\3\2\2\2\u0101\u0102\7E\2\2\u0102\u0103\7"+
		"n\2\2\u0103\u0104\7k\2\2\u0104\u0105\7g\2\2\u0105\u0106\7p\2\2\u0106\u0107"+
		"\7v\2\2\u0107\u0108\3\2\2\2\u0108\u0109\5K&\2\u0109:\3\2\2\2\u010a\u010b"+
		"\7o\2\2\u010b\u010c\7g\2\2\u010c\u010d\7o\2\2\u010d\u010e\7d\2\2\u010e"+
		"\u010f\7g\2\2\u010f\u0110\7t\2\2\u0110\u0111\7u\2\2\u0111\u0112\7Q\2\2"+
		"\u0112\u0113\7p\2\2\u0113\u0114\7n\2\2\u0114\u0115\7{\2\2\u0115<\3\2\2"+
		"\2\u0116\u0117\7o\2\2\u0117\u0118\7g\2\2\u0118\u0119\7o\2\2\u0119\u011a"+
		"\7d\2\2\u011a\u011b\7g\2\2\u011b\u011c\7t\2\2\u011c\u011d\7u\2\2\u011d"+
		">\3\2\2\2\u011e\u011f\7e\2\2\u011f\u0120\7n\2\2\u0120\u0121\7k\2\2\u0121"+
		"\u0122\7g\2\2\u0122\u0123\7p\2\2\u0123\u0124\7v\2\2\u0124\u0125\7u\2\2"+
		"\u0125@\3\2\2\2\u0126\u0127\7?\2\2\u0127B\3\2\2\2\u0128\u012b\5E#\2\u0129"+
		"\u012b\5G$\2\u012a\u0128\3\2\2\2\u012a\u0129\3\2\2\2\u012bD\3\2\2\2\u012c"+
		"\u012d\7v\2\2\u012d\u012e\7t\2\2\u012e\u012f\7w\2\2\u012f\u0130\7g\2\2"+
		"\u0130F\3\2\2\2\u0131\u0132\7h\2\2\u0132\u0133\7c\2\2\u0133\u0134\7n\2"+
		"\2\u0134\u0135\7u\2\2\u0135\u0136\7g\2\2\u0136H\3\2\2\2\u0137\u0139\t"+
		"\2\2\2\u0138\u0137\3\2\2\2\u0139\u013d\3\2\2\2\u013a\u013c\t\3\2\2\u013b"+
		"\u013a\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013d\u013e\3\2"+
		"\2\2\u013eJ\3\2\2\2\u013f\u013d\3\2\2\2\u0140\u0142\t\4\2\2\u0141\u0140"+
		"\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0141\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		"L\3\2\2\2\u0145\u0147\t\5\2\2\u0146\u0145\3\2\2\2\u0147\u0148\3\2\2\2"+
		"\u0148\u0146\3\2\2\2\u0148\u0149\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b"+
		"\b\'\2\2\u014bN\3\2\2\2\u014c\u014e\t\6\2\2\u014d\u014c\3\2\2\2\u014e"+
		"P\3\2\2\2\u014f\u0151\7$\2\2\u0150\u0152\5O(\2\u0151\u0150\3\2\2\2\u0152"+
		"\u0153\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0155\3\2"+
		"\2\2\u0155\u0156\7$\2\2\u0156R\3\2\2\2\u0157\u0158\5U+\2\u0158\u0159\7"+
		".\2\2\u0159\u015a\5U+\2\u015aT\3\2\2\2\u015b\u015c\5W,\2\u015c\u015d\7"+
		"\60\2\2\u015d\u015e\5W,\2\u015e\u015f\7\60\2\2\u015f\u0160\5W,\2\u0160"+
		"\u0161\7\60\2\2\u0161\u0162\5W,\2\u0162V\3\2\2\2\u0163\u0164\5Y-\2\u0164"+
		"\u0165\5Y-\2\u0165\u0166\5Y-\2\u0166\u016c\3\2\2\2\u0167\u0168\5Y-\2\u0168"+
		"\u0169\5Y-\2\u0169\u016c\3\2\2\2\u016a\u016c\5Y-\2\u016b\u0163\3\2\2\2"+
		"\u016b\u0167\3\2\2\2\u016b\u016a\3\2\2\2\u016cX\3\2\2\2\u016d\u016e\4"+
		"\62;\2\u016eZ\3\2\2\2\u016f\u0170\7\61\2\2\u0170\u0171\7\61\2\2\u0171"+
		"\u0175\3\2\2\2\u0172\u0174\n\7\2\2\u0173\u0172\3\2\2\2\u0174\u0177\3\2"+
		"\2\2\u0175\u0173\3\2\2\2\u0175\u0176\3\2\2\2\u0176\u0178\3\2\2\2\u0177"+
		"\u0175\3\2\2\2\u0178\u0179\b.\2\2\u0179\\\3\2\2\2\r\2\u012a\u0138\u013b"+
		"\u013d\u0143\u0148\u014d\u0153\u016b\u0175\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}