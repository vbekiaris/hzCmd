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
		INSTALL=9, EE=10, OS=11, LOAD=12, SET=13, INVOKE=14, INFO=15, KILL=16, 
		CAT=17, RESTART=18, SLEEP=19, SAVE=20, EXIT=21, SHOWSSH=22, ALL=23, MEMBER=24, 
		CLIENT=25, MEMBER_ALL=26, MEMBER_VAR=27, CLIENT_ALL=28, CLIENT_VAR=29, 
		MEMBERS_ONLY=30, MEMBERS=31, CLIENTS=32, ASSIGN=33, BOOL=34, TRUE=35, 
		FALSE=36, VAR=37, NUMBER=38, WHITESPACE=39, STRING=40, IP_PAIR=41, IP_STR=42, 
		COMMENT=43;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", "CAT", 
		"RESTART", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", "MEMBER", "CLIENT", 
		"MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", 
		"MEMBERS", "CLIENTS", "ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", 
		"WHITESPACE", "CHAR", "STRING", "IP_PAIR", "IP_STR", "Octet", "Digit", 
		"COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'file'", "'ip'", "'cluster'", 
		"'replicate'", "'install'", "'EE'", "'OS'", "'load'", "'set'", "'invoke'", 
		"'info'", "'kill'", "'cat'", "'restart'", "'sleep'", "'save'", "'exit'", 
		"'showSSH'", "'*'", "'member'", "'client'", null, null, null, null, "'membersOnly'", 
		"'members'", "'clients'", "'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", "CAT", 
		"RESTART", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", "MEMBER", "CLIENT", 
		"MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", 
		"MEMBERS", "CLIENTS", "ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", 
		"WHITESPACE", "STRING", "IP_PAIR", "IP_STR", "COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2-\u0181\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3#\3#\5#\u0132"+
		"\n#\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3&\5&\u0140\n&\3&\7&\u0143\n&\f&"+
		"\16&\u0146\13&\3\'\6\'\u0149\n\'\r\'\16\'\u014a\3(\6(\u014e\n(\r(\16("+
		"\u014f\3(\3(\3)\5)\u0155\n)\3*\3*\6*\u0159\n*\r*\16*\u015a\3*\3*\3+\3"+
		"+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3-\3-\3-\3-\3-\3-\3-\3-\5-\u0173\n-\3"+
		".\3.\3/\3/\3/\3/\7/\u017b\n/\f/\16/\u017e\13/\3/\3/\2\2\60\3\3\5\4\7\5"+
		"\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23"+
		"%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G"+
		"%I&K\'M(O)Q\2S*U+W,Y\2[\2]-\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3\2\62;\5\2"+
		"\13\f\16\17\"\"\b\2\"\"/;??C\\aac|\4\2\f\f\17\17\u0185\2\3\3\2\2\2\2\5"+
		"\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2"+
		"\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K"+
		"\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2]\3\2"+
		"\2\2\3_\3\2\2\2\5f\3\2\2\2\7k\3\2\2\2\ts\3\2\2\2\13w\3\2\2\2\r|\3\2\2"+
		"\2\17\177\3\2\2\2\21\u0087\3\2\2\2\23\u0091\3\2\2\2\25\u0099\3\2\2\2\27"+
		"\u009c\3\2\2\2\31\u009f\3\2\2\2\33\u00a4\3\2\2\2\35\u00a8\3\2\2\2\37\u00af"+
		"\3\2\2\2!\u00b4\3\2\2\2#\u00b9\3\2\2\2%\u00bd\3\2\2\2\'\u00c5\3\2\2\2"+
		")\u00cb\3\2\2\2+\u00d0\3\2\2\2-\u00d5\3\2\2\2/\u00dd\3\2\2\2\61\u00df"+
		"\3\2\2\2\63\u00e6\3\2\2\2\65\u00ed\3\2\2\2\67\u00f6\3\2\2\29\u00ff\3\2"+
		"\2\2;\u0108\3\2\2\2=\u0111\3\2\2\2?\u011d\3\2\2\2A\u0125\3\2\2\2C\u012d"+
		"\3\2\2\2E\u0131\3\2\2\2G\u0133\3\2\2\2I\u0138\3\2\2\2K\u013f\3\2\2\2M"+
		"\u0148\3\2\2\2O\u014d\3\2\2\2Q\u0154\3\2\2\2S\u0156\3\2\2\2U\u015e\3\2"+
		"\2\2W\u0162\3\2\2\2Y\u0172\3\2\2\2[\u0174\3\2\2\2]\u0176\3\2\2\2_`\7j"+
		"\2\2`a\7q\2\2ab\7o\2\2bc\7g\2\2cd\7K\2\2de\7r\2\2e\4\3\2\2\2fg\7w\2\2"+
		"gh\7u\2\2hi\7g\2\2ij\7t\2\2j\6\3\2\2\2kl\7x\2\2lm\7g\2\2mn\7t\2\2no\7"+
		"u\2\2op\7k\2\2pq\7q\2\2qr\7p\2\2r\b\3\2\2\2st\7c\2\2tu\7f\2\2uv\7f\2\2"+
		"v\n\3\2\2\2wx\7h\2\2xy\7k\2\2yz\7n\2\2z{\7g\2\2{\f\3\2\2\2|}\7k\2\2}~"+
		"\7r\2\2~\16\3\2\2\2\177\u0080\7e\2\2\u0080\u0081\7n\2\2\u0081\u0082\7"+
		"w\2\2\u0082\u0083\7u\2\2\u0083\u0084\7v\2\2\u0084\u0085\7g\2\2\u0085\u0086"+
		"\7t\2\2\u0086\20\3\2\2\2\u0087\u0088\7t\2\2\u0088\u0089\7g\2\2\u0089\u008a"+
		"\7r\2\2\u008a\u008b\7n\2\2\u008b\u008c\7k\2\2\u008c\u008d\7e\2\2\u008d"+
		"\u008e\7c\2\2\u008e\u008f\7v\2\2\u008f\u0090\7g\2\2\u0090\22\3\2\2\2\u0091"+
		"\u0092\7k\2\2\u0092\u0093\7p\2\2\u0093\u0094\7u\2\2\u0094\u0095\7v\2\2"+
		"\u0095\u0096\7c\2\2\u0096\u0097\7n\2\2\u0097\u0098\7n\2\2\u0098\24\3\2"+
		"\2\2\u0099\u009a\7G\2\2\u009a\u009b\7G\2\2\u009b\26\3\2\2\2\u009c\u009d"+
		"\7Q\2\2\u009d\u009e\7U\2\2\u009e\30\3\2\2\2\u009f\u00a0\7n\2\2\u00a0\u00a1"+
		"\7q\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7f\2\2\u00a3\32\3\2\2\2\u00a4\u00a5"+
		"\7u\2\2\u00a5\u00a6\7g\2\2\u00a6\u00a7\7v\2\2\u00a7\34\3\2\2\2\u00a8\u00a9"+
		"\7k\2\2\u00a9\u00aa\7p\2\2\u00aa\u00ab\7x\2\2\u00ab\u00ac\7q\2\2\u00ac"+
		"\u00ad\7m\2\2\u00ad\u00ae\7g\2\2\u00ae\36\3\2\2\2\u00af\u00b0\7k\2\2\u00b0"+
		"\u00b1\7p\2\2\u00b1\u00b2\7h\2\2\u00b2\u00b3\7q\2\2\u00b3 \3\2\2\2\u00b4"+
		"\u00b5\7m\2\2\u00b5\u00b6\7k\2\2\u00b6\u00b7\7n\2\2\u00b7\u00b8\7n\2\2"+
		"\u00b8\"\3\2\2\2\u00b9\u00ba\7e\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7v"+
		"\2\2\u00bc$\3\2\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0"+
		"\7u\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3\7t\2\2\u00c3"+
		"\u00c4\7v\2\2\u00c4&\3\2\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7n\2\2\u00c7"+
		"\u00c8\7g\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7r\2\2\u00ca(\3\2\2\2\u00cb"+
		"\u00cc\7u\2\2\u00cc\u00cd\7c\2\2\u00cd\u00ce\7x\2\2\u00ce\u00cf\7g\2\2"+
		"\u00cf*\3\2\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7z\2\2\u00d2\u00d3\7k\2"+
		"\2\u00d3\u00d4\7v\2\2\u00d4,\3\2\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7"+
		"j\2\2\u00d7\u00d8\7q\2\2\u00d8\u00d9\7y\2\2\u00d9\u00da\7U\2\2\u00da\u00db"+
		"\7U\2\2\u00db\u00dc\7J\2\2\u00dc.\3\2\2\2\u00dd\u00de\7,\2\2\u00de\60"+
		"\3\2\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7o\2\2\u00e2"+
		"\u00e3\7d\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7t\2\2\u00e5\62\3\2\2\2\u00e6"+
		"\u00e7\7e\2\2\u00e7\u00e8\7n\2\2\u00e8\u00e9\7k\2\2\u00e9\u00ea\7g\2\2"+
		"\u00ea\u00eb\7p\2\2\u00eb\u00ec\7v\2\2\u00ec\64\3\2\2\2\u00ed\u00ee\7"+
		"O\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7o\2\2\u00f0\u00f1\7d\2\2\u00f1\u00f2"+
		"\7g\2\2\u00f2\u00f3\7t\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\5/\30\2\u00f5"+
		"\66\3\2\2\2\u00f6\u00f7\7O\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7o\2\2\u00f9"+
		"\u00fa\7d\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7t\2\2\u00fc\u00fd\3\2\2"+
		"\2\u00fd\u00fe\5M\'\2\u00fe8\3\2\2\2\u00ff\u0100\7E\2\2\u0100\u0101\7"+
		"n\2\2\u0101\u0102\7k\2\2\u0102\u0103\7g\2\2\u0103\u0104\7p\2\2\u0104\u0105"+
		"\7v\2\2\u0105\u0106\3\2\2\2\u0106\u0107\5/\30\2\u0107:\3\2\2\2\u0108\u0109"+
		"\7E\2\2\u0109\u010a\7n\2\2\u010a\u010b\7k\2\2\u010b\u010c\7g\2\2\u010c"+
		"\u010d\7p\2\2\u010d\u010e\7v\2\2\u010e\u010f\3\2\2\2\u010f\u0110\5M\'"+
		"\2\u0110<\3\2\2\2\u0111\u0112\7o\2\2\u0112\u0113\7g\2\2\u0113\u0114\7"+
		"o\2\2\u0114\u0115\7d\2\2\u0115\u0116\7g\2\2\u0116\u0117\7t\2\2\u0117\u0118"+
		"\7u\2\2\u0118\u0119\7Q\2\2\u0119\u011a\7p\2\2\u011a\u011b\7n\2\2\u011b"+
		"\u011c\7{\2\2\u011c>\3\2\2\2\u011d\u011e\7o\2\2\u011e\u011f\7g\2\2\u011f"+
		"\u0120\7o\2\2\u0120\u0121\7d\2\2\u0121\u0122\7g\2\2\u0122\u0123\7t\2\2"+
		"\u0123\u0124\7u\2\2\u0124@\3\2\2\2\u0125\u0126\7e\2\2\u0126\u0127\7n\2"+
		"\2\u0127\u0128\7k\2\2\u0128\u0129\7g\2\2\u0129\u012a\7p\2\2\u012a\u012b"+
		"\7v\2\2\u012b\u012c\7u\2\2\u012cB\3\2\2\2\u012d\u012e\7?\2\2\u012eD\3"+
		"\2\2\2\u012f\u0132\5G$\2\u0130\u0132\5I%\2\u0131\u012f\3\2\2\2\u0131\u0130"+
		"\3\2\2\2\u0132F\3\2\2\2\u0133\u0134\7v\2\2\u0134\u0135\7t\2\2\u0135\u0136"+
		"\7w\2\2\u0136\u0137\7g\2\2\u0137H\3\2\2\2\u0138\u0139\7h\2\2\u0139\u013a"+
		"\7c\2\2\u013a\u013b\7n\2\2\u013b\u013c\7u\2\2\u013c\u013d\7g\2\2\u013d"+
		"J\3\2\2\2\u013e\u0140\t\2\2\2\u013f\u013e\3\2\2\2\u0140\u0144\3\2\2\2"+
		"\u0141\u0143\t\3\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142"+
		"\3\2\2\2\u0144\u0145\3\2\2\2\u0145L\3\2\2\2\u0146\u0144\3\2\2\2\u0147"+
		"\u0149\t\4\2\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2"+
		"\2\2\u014a\u014b\3\2\2\2\u014bN\3\2\2\2\u014c\u014e\t\5\2\2\u014d\u014c"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150"+
		"\u0151\3\2\2\2\u0151\u0152\b(\2\2\u0152P\3\2\2\2\u0153\u0155\t\6\2\2\u0154"+
		"\u0153\3\2\2\2\u0155R\3\2\2\2\u0156\u0158\7$\2\2\u0157\u0159\5Q)\2\u0158"+
		"\u0157\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u0158\3\2\2\2\u015a\u015b\3\2"+
		"\2\2\u015b\u015c\3\2\2\2\u015c\u015d\7$\2\2\u015dT\3\2\2\2\u015e\u015f"+
		"\5W,\2\u015f\u0160\7.\2\2\u0160\u0161\5W,\2\u0161V\3\2\2\2\u0162\u0163"+
		"\5Y-\2\u0163\u0164\7\60\2\2\u0164\u0165\5Y-\2\u0165\u0166\7\60\2\2\u0166"+
		"\u0167\5Y-\2\u0167\u0168\7\60\2\2\u0168\u0169\5Y-\2\u0169X\3\2\2\2\u016a"+
		"\u016b\5[.\2\u016b\u016c\5[.\2\u016c\u016d\5[.\2\u016d\u0173\3\2\2\2\u016e"+
		"\u016f\5[.\2\u016f\u0170\5[.\2\u0170\u0173\3\2\2\2\u0171\u0173\5[.\2\u0172"+
		"\u016a\3\2\2\2\u0172\u016e\3\2\2\2\u0172\u0171\3\2\2\2\u0173Z\3\2\2\2"+
		"\u0174\u0175\4\62;\2\u0175\\\3\2\2\2\u0176\u0177\7\61\2\2\u0177\u0178"+
		"\7\61\2\2\u0178\u017c\3\2\2\2\u0179\u017b\n\7\2\2\u017a\u0179\3\2\2\2"+
		"\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f"+
		"\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180\b/\2\2\u0180^\3\2\2\2\r\2\u0131"+
		"\u013f\u0142\u0144\u014a\u014f\u0154\u015a\u0172\u017c\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}