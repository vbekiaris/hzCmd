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
		USER=1, VERSION=2, ADD=3, FILE=4, IP=5, CLUSTER=6, REPLICATE=7, INSTALL=8, 
		EE=9, OS=10, LOAD=11, SET=12, INVOKE=13, KILL=14, CAT=15, RESTART=16, 
		SLEEP=17, SAVE=18, EXIT=19, ALL=20, MEMBER=21, CLIENT=22, MEMBER_ALL=23, 
		MEMBER_VAR=24, CLIENT_ALL=25, CLIENT_VAR=26, MEMBERS_ONLY=27, MEMBERS=28, 
		CLIENTS=29, ASSIGN=30, BOOL=31, TRUE=32, FALSE=33, VAR=34, NUMBER=35, 
		WHITESPACE=36, STRING=37, IP_PAIR=38, IP_STR=39, COMMENT=40;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", "INSTALL", 
		"EE", "OS", "LOAD", "SET", "INVOKE", "KILL", "CAT", "RESTART", "SLEEP", 
		"SAVE", "EXIT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", 
		"CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", 
		"BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", "STRING", 
		"IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'user'", "'version'", "'add'", "'file'", "'ip'", "'cluster'", "'replicate'", 
		"'install'", "'EE'", "'OS'", "'load'", "'set'", "'invoke'", "'kill'", 
		"'cat'", "'restart'", "'sleep'", "'save'", "'exit'", "'*'", "'member'", 
		"'client'", null, null, null, null, "'membersOnly'", "'members'", "'clients'", 
		"'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "KILL", "CAT", "RESTART", 
		"SLEEP", "SAVE", "EXIT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", 
		"CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", 
		"BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "STRING", "IP_PAIR", 
		"IP_STR", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2*\u0167\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3 \3 \5 \u0118\n \3!\3!\3!\3"+
		"!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\5#\u0126\n#\3#\7#\u0129\n#\f#\16#\u012c"+
		"\13#\3$\6$\u012f\n$\r$\16$\u0130\3%\6%\u0134\n%\r%\16%\u0135\3%\3%\3&"+
		"\5&\u013b\n&\3\'\3\'\6\'\u013f\n\'\r\'\16\'\u0140\3\'\3\'\3(\3(\3(\3("+
		"\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\5*\u0159\n*\3+\3+\3,"+
		"\3,\3,\3,\7,\u0161\n,\f,\16,\u0164\13,\3,\3,\2\2-\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\'"+
		"O(Q)S\2U\2W*\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\16\17\"\""+
		"\7\2\"\"/;C\\aac|\4\2\f\f\17\17\u016b\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65"+
		"\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3"+
		"\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2M\3\2\2\2\2O\3\2\2"+
		"\2\2Q\3\2\2\2\2W\3\2\2\2\3Y\3\2\2\2\5^\3\2\2\2\7f\3\2\2\2\tj\3\2\2\2\13"+
		"o\3\2\2\2\rr\3\2\2\2\17z\3\2\2\2\21\u0084\3\2\2\2\23\u008c\3\2\2\2\25"+
		"\u008f\3\2\2\2\27\u0092\3\2\2\2\31\u0097\3\2\2\2\33\u009b\3\2\2\2\35\u00a2"+
		"\3\2\2\2\37\u00a7\3\2\2\2!\u00ab\3\2\2\2#\u00b3\3\2\2\2%\u00b9\3\2\2\2"+
		"\'\u00be\3\2\2\2)\u00c3\3\2\2\2+\u00c5\3\2\2\2-\u00cc\3\2\2\2/\u00d3\3"+
		"\2\2\2\61\u00dc\3\2\2\2\63\u00e5\3\2\2\2\65\u00ee\3\2\2\2\67\u00f7\3\2"+
		"\2\29\u0103\3\2\2\2;\u010b\3\2\2\2=\u0113\3\2\2\2?\u0117\3\2\2\2A\u0119"+
		"\3\2\2\2C\u011e\3\2\2\2E\u0125\3\2\2\2G\u012e\3\2\2\2I\u0133\3\2\2\2K"+
		"\u013a\3\2\2\2M\u013c\3\2\2\2O\u0144\3\2\2\2Q\u0148\3\2\2\2S\u0158\3\2"+
		"\2\2U\u015a\3\2\2\2W\u015c\3\2\2\2YZ\7w\2\2Z[\7u\2\2[\\\7g\2\2\\]\7t\2"+
		"\2]\4\3\2\2\2^_\7x\2\2_`\7g\2\2`a\7t\2\2ab\7u\2\2bc\7k\2\2cd\7q\2\2de"+
		"\7p\2\2e\6\3\2\2\2fg\7c\2\2gh\7f\2\2hi\7f\2\2i\b\3\2\2\2jk\7h\2\2kl\7"+
		"k\2\2lm\7n\2\2mn\7g\2\2n\n\3\2\2\2op\7k\2\2pq\7r\2\2q\f\3\2\2\2rs\7e\2"+
		"\2st\7n\2\2tu\7w\2\2uv\7u\2\2vw\7v\2\2wx\7g\2\2xy\7t\2\2y\16\3\2\2\2z"+
		"{\7t\2\2{|\7g\2\2|}\7r\2\2}~\7n\2\2~\177\7k\2\2\177\u0080\7e\2\2\u0080"+
		"\u0081\7c\2\2\u0081\u0082\7v\2\2\u0082\u0083\7g\2\2\u0083\20\3\2\2\2\u0084"+
		"\u0085\7k\2\2\u0085\u0086\7p\2\2\u0086\u0087\7u\2\2\u0087\u0088\7v\2\2"+
		"\u0088\u0089\7c\2\2\u0089\u008a\7n\2\2\u008a\u008b\7n\2\2\u008b\22\3\2"+
		"\2\2\u008c\u008d\7G\2\2\u008d\u008e\7G\2\2\u008e\24\3\2\2\2\u008f\u0090"+
		"\7Q\2\2\u0090\u0091\7U\2\2\u0091\26\3\2\2\2\u0092\u0093\7n\2\2\u0093\u0094"+
		"\7q\2\2\u0094\u0095\7c\2\2\u0095\u0096\7f\2\2\u0096\30\3\2\2\2\u0097\u0098"+
		"\7u\2\2\u0098\u0099\7g\2\2\u0099\u009a\7v\2\2\u009a\32\3\2\2\2\u009b\u009c"+
		"\7k\2\2\u009c\u009d\7p\2\2\u009d\u009e\7x\2\2\u009e\u009f\7q\2\2\u009f"+
		"\u00a0\7m\2\2\u00a0\u00a1\7g\2\2\u00a1\34\3\2\2\2\u00a2\u00a3\7m\2\2\u00a3"+
		"\u00a4\7k\2\2\u00a4\u00a5\7n\2\2\u00a5\u00a6\7n\2\2\u00a6\36\3\2\2\2\u00a7"+
		"\u00a8\7e\2\2\u00a8\u00a9\7c\2\2\u00a9\u00aa\7v\2\2\u00aa \3\2\2\2\u00ab"+
		"\u00ac\7t\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7v\2\2"+
		"\u00af\u00b0\7c\2\2\u00b0\u00b1\7t\2\2\u00b1\u00b2\7v\2\2\u00b2\"\3\2"+
		"\2\2\u00b3\u00b4\7u\2\2\u00b4\u00b5\7n\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7"+
		"\7g\2\2\u00b7\u00b8\7r\2\2\u00b8$\3\2\2\2\u00b9\u00ba\7u\2\2\u00ba\u00bb"+
		"\7c\2\2\u00bb\u00bc\7x\2\2\u00bc\u00bd\7g\2\2\u00bd&\3\2\2\2\u00be\u00bf"+
		"\7g\2\2\u00bf\u00c0\7z\2\2\u00c0\u00c1\7k\2\2\u00c1\u00c2\7v\2\2\u00c2"+
		"(\3\2\2\2\u00c3\u00c4\7,\2\2\u00c4*\3\2\2\2\u00c5\u00c6\7o\2\2\u00c6\u00c7"+
		"\7g\2\2\u00c7\u00c8\7o\2\2\u00c8\u00c9\7d\2\2\u00c9\u00ca\7g\2\2\u00ca"+
		"\u00cb\7t\2\2\u00cb,\3\2\2\2\u00cc\u00cd\7e\2\2\u00cd\u00ce\7n\2\2\u00ce"+
		"\u00cf\7k\2\2\u00cf\u00d0\7g\2\2\u00d0\u00d1\7p\2\2\u00d1\u00d2\7v\2\2"+
		"\u00d2.\3\2\2\2\u00d3\u00d4\7O\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7o\2"+
		"\2\u00d6\u00d7\7d\2\2\u00d7\u00d8\7g\2\2\u00d8\u00d9\7t\2\2\u00d9\u00da"+
		"\3\2\2\2\u00da\u00db\5)\25\2\u00db\60\3\2\2\2\u00dc\u00dd\7O\2\2\u00dd"+
		"\u00de\7g\2\2\u00de\u00df\7o\2\2\u00df\u00e0\7d\2\2\u00e0\u00e1\7g\2\2"+
		"\u00e1\u00e2\7t\2\2\u00e2\u00e3\3\2\2\2\u00e3\u00e4\5G$\2\u00e4\62\3\2"+
		"\2\2\u00e5\u00e6\7E\2\2\u00e6\u00e7\7n\2\2\u00e7\u00e8\7k\2\2\u00e8\u00e9"+
		"\7g\2\2\u00e9\u00ea\7p\2\2\u00ea\u00eb\7v\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ed\5)\25\2\u00ed\64\3\2\2\2\u00ee\u00ef\7E\2\2\u00ef\u00f0\7n\2\2"+
		"\u00f0\u00f1\7k\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7p\2\2\u00f3\u00f4"+
		"\7v\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\5G$\2\u00f6\66\3\2\2\2\u00f7\u00f8"+
		"\7o\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7o\2\2\u00fa\u00fb\7d\2\2\u00fb"+
		"\u00fc\7g\2\2\u00fc\u00fd\7t\2\2\u00fd\u00fe\7u\2\2\u00fe\u00ff\7Q\2\2"+
		"\u00ff\u0100\7p\2\2\u0100\u0101\7n\2\2\u0101\u0102\7{\2\2\u01028\3\2\2"+
		"\2\u0103\u0104\7o\2\2\u0104\u0105\7g\2\2\u0105\u0106\7o\2\2\u0106\u0107"+
		"\7d\2\2\u0107\u0108\7g\2\2\u0108\u0109\7t\2\2\u0109\u010a\7u\2\2\u010a"+
		":\3\2\2\2\u010b\u010c\7e\2\2\u010c\u010d\7n\2\2\u010d\u010e\7k\2\2\u010e"+
		"\u010f\7g\2\2\u010f\u0110\7p\2\2\u0110\u0111\7v\2\2\u0111\u0112\7u\2\2"+
		"\u0112<\3\2\2\2\u0113\u0114\7?\2\2\u0114>\3\2\2\2\u0115\u0118\5A!\2\u0116"+
		"\u0118\5C\"\2\u0117\u0115\3\2\2\2\u0117\u0116\3\2\2\2\u0118@\3\2\2\2\u0119"+
		"\u011a\7v\2\2\u011a\u011b\7t\2\2\u011b\u011c\7w\2\2\u011c\u011d\7g\2\2"+
		"\u011dB\3\2\2\2\u011e\u011f\7h\2\2\u011f\u0120\7c\2\2\u0120\u0121\7n\2"+
		"\2\u0121\u0122\7u\2\2\u0122\u0123\7g\2\2\u0123D\3\2\2\2\u0124\u0126\t"+
		"\2\2\2\u0125\u0124\3\2\2\2\u0126\u012a\3\2\2\2\u0127\u0129\t\3\2\2\u0128"+
		"\u0127\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a\u012b\3\2"+
		"\2\2\u012bF\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012f\t\4\2\2\u012e\u012d"+
		"\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u0131\3\2\2\2\u0131"+
		"H\3\2\2\2\u0132\u0134\t\5\2\2\u0133\u0132\3\2\2\2\u0134\u0135\3\2\2\2"+
		"\u0135\u0133\3\2\2\2\u0135\u0136\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138"+
		"\b%\2\2\u0138J\3\2\2\2\u0139\u013b\t\6\2\2\u013a\u0139\3\2\2\2\u013bL"+
		"\3\2\2\2\u013c\u013e\7$\2\2\u013d\u013f\5K&\2\u013e\u013d\3\2\2\2\u013f"+
		"\u0140\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0142\3\2"+
		"\2\2\u0142\u0143\7$\2\2\u0143N\3\2\2\2\u0144\u0145\5Q)\2\u0145\u0146\7"+
		".\2\2\u0146\u0147\5Q)\2\u0147P\3\2\2\2\u0148\u0149\5S*\2\u0149\u014a\7"+
		"\60\2\2\u014a\u014b\5S*\2\u014b\u014c\7\60\2\2\u014c\u014d\5S*\2\u014d"+
		"\u014e\7\60\2\2\u014e\u014f\5S*\2\u014fR\3\2\2\2\u0150\u0151\5U+\2\u0151"+
		"\u0152\5U+\2\u0152\u0153\5U+\2\u0153\u0159\3\2\2\2\u0154\u0155\5U+\2\u0155"+
		"\u0156\5U+\2\u0156\u0159\3\2\2\2\u0157\u0159\5U+\2\u0158\u0150\3\2\2\2"+
		"\u0158\u0154\3\2\2\2\u0158\u0157\3\2\2\2\u0159T\3\2\2\2\u015a\u015b\4"+
		"\62;\2\u015bV\3\2\2\2\u015c\u015d\7\61\2\2\u015d\u015e\7\61\2\2\u015e"+
		"\u0162\3\2\2\2\u015f\u0161\n\7\2\2\u0160\u015f\3\2\2\2\u0161\u0164\3\2"+
		"\2\2\u0162\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0165\3\2\2\2\u0164"+
		"\u0162\3\2\2\2\u0165\u0166\b,\2\2\u0166X\3\2\2\2\r\2\u0117\u0125\u0128"+
		"\u012a\u0130\u0135\u013a\u0140\u0158\u0162\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}