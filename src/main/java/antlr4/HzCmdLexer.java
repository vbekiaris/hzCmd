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
		INSTALL=9, UNINSTALL=10, EE=11, OS=12, LOAD=13, SET=14, INVOKE=15, INFO=16, 
		KILL=17, CAT=18, RESTART=19, CLEAN=20, SLEEP=21, SAVE=22, EXIT=23, SHOWSSH=24, 
		ALL=25, MEMBER=26, CLIENT=27, MEMBER_ALL=28, MEMBER_VAR=29, CLIENT_ALL=30, 
		CLIENT_VAR=31, MEMBERS_ONLY=32, MEMBERS=33, CLIENTS=34, ASSIGN=35, BOOL=36, 
		TRUE=37, FALSE=38, VAR=39, NUMBER=40, WHITESPACE=41, STRING=42, IP_PAIR=43, 
		IP_STR=44, COMMENT=45;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", 
		"CAT", "RESTART", "CLEAN", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", 
		"MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", 
		"MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", "BOOL", "TRUE", "FALSE", 
		"VAR", "NUMBER", "WHITESPACE", "CHAR", "STRING", "IP_PAIR", "IP_STR", 
		"Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'file'", "'ip'", "'cluster'", 
		"'replicate'", "'install'", "'uninstall'", "'EE'", "'OS'", "'load'", "'set'", 
		"'invoke'", "'info'", "'kill'", "'cat'", "'restart'", "'clean'", "'sleep'", 
		"'save'", "'exit'", "'showSSH'", "'*'", "'member'", "'client'", null, 
		null, null, null, "'membersOnly'", "'members'", "'clients'", "'='", null, 
		"'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
		"INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", "INFO", "KILL", 
		"CAT", "RESTART", "CLEAN", "SLEEP", "SAVE", "EXIT", "SHOWSSH", "ALL", 
		"MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2/\u0195\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\3\2\3\2\3\2\3\2"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3%\3%\5%\u0146\n%\3"+
		"&\3&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3(\5(\u0154\n(\3(\7(\u0157\n(\f"+
		"(\16(\u015a\13(\3)\6)\u015d\n)\r)\16)\u015e\3*\6*\u0162\n*\r*\16*\u0163"+
		"\3*\3*\3+\5+\u0169\n+\3,\3,\6,\u016d\n,\r,\16,\u016e\3,\3,\3-\3-\3-\3"+
		"-\3.\3.\3.\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3/\3/\3/\5/\u0187\n/\3\60\3\60"+
		"\3\61\3\61\3\61\3\61\7\61\u018f\n\61\f\61\16\61\u0192\13\61\3\61\3\61"+
		"\2\2\62\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U\2W,Y-[.]\2_\2a/\3\2\b\4\2C\\c|\6\2"+
		"\62;C\\aac|\3\2\62;\5\2\13\f\16\17\"\"\b\2\"\"/;??C\\aac|\4\2\f\f\17\17"+
		"\u0199\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3"+
		"\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2a\3\2\2\2\3c\3\2\2\2\5j\3\2\2"+
		"\2\7o\3\2\2\2\tw\3\2\2\2\13{\3\2\2\2\r\u0080\3\2\2\2\17\u0083\3\2\2\2"+
		"\21\u008b\3\2\2\2\23\u0095\3\2\2\2\25\u009d\3\2\2\2\27\u00a7\3\2\2\2\31"+
		"\u00aa\3\2\2\2\33\u00ad\3\2\2\2\35\u00b2\3\2\2\2\37\u00b6\3\2\2\2!\u00bd"+
		"\3\2\2\2#\u00c2\3\2\2\2%\u00c7\3\2\2\2\'\u00cb\3\2\2\2)\u00d3\3\2\2\2"+
		"+\u00d9\3\2\2\2-\u00df\3\2\2\2/\u00e4\3\2\2\2\61\u00e9\3\2\2\2\63\u00f1"+
		"\3\2\2\2\65\u00f3\3\2\2\2\67\u00fa\3\2\2\29\u0101\3\2\2\2;\u010a\3\2\2"+
		"\2=\u0113\3\2\2\2?\u011c\3\2\2\2A\u0125\3\2\2\2C\u0131\3\2\2\2E\u0139"+
		"\3\2\2\2G\u0141\3\2\2\2I\u0145\3\2\2\2K\u0147\3\2\2\2M\u014c\3\2\2\2O"+
		"\u0153\3\2\2\2Q\u015c\3\2\2\2S\u0161\3\2\2\2U\u0168\3\2\2\2W\u016a\3\2"+
		"\2\2Y\u0172\3\2\2\2[\u0176\3\2\2\2]\u0186\3\2\2\2_\u0188\3\2\2\2a\u018a"+
		"\3\2\2\2cd\7j\2\2de\7q\2\2ef\7o\2\2fg\7g\2\2gh\7K\2\2hi\7r\2\2i\4\3\2"+
		"\2\2jk\7w\2\2kl\7u\2\2lm\7g\2\2mn\7t\2\2n\6\3\2\2\2op\7x\2\2pq\7g\2\2"+
		"qr\7t\2\2rs\7u\2\2st\7k\2\2tu\7q\2\2uv\7p\2\2v\b\3\2\2\2wx\7c\2\2xy\7"+
		"f\2\2yz\7f\2\2z\n\3\2\2\2{|\7h\2\2|}\7k\2\2}~\7n\2\2~\177\7g\2\2\177\f"+
		"\3\2\2\2\u0080\u0081\7k\2\2\u0081\u0082\7r\2\2\u0082\16\3\2\2\2\u0083"+
		"\u0084\7e\2\2\u0084\u0085\7n\2\2\u0085\u0086\7w\2\2\u0086\u0087\7u\2\2"+
		"\u0087\u0088\7v\2\2\u0088\u0089\7g\2\2\u0089\u008a\7t\2\2\u008a\20\3\2"+
		"\2\2\u008b\u008c\7t\2\2\u008c\u008d\7g\2\2\u008d\u008e\7r\2\2\u008e\u008f"+
		"\7n\2\2\u008f\u0090\7k\2\2\u0090\u0091\7e\2\2\u0091\u0092\7c\2\2\u0092"+
		"\u0093\7v\2\2\u0093\u0094\7g\2\2\u0094\22\3\2\2\2\u0095\u0096\7k\2\2\u0096"+
		"\u0097\7p\2\2\u0097\u0098\7u\2\2\u0098\u0099\7v\2\2\u0099\u009a\7c\2\2"+
		"\u009a\u009b\7n\2\2\u009b\u009c\7n\2\2\u009c\24\3\2\2\2\u009d\u009e\7"+
		"w\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2"+
		"\7u\2\2\u00a2\u00a3\7v\2\2\u00a3\u00a4\7c\2\2\u00a4\u00a5\7n\2\2\u00a5"+
		"\u00a6\7n\2\2\u00a6\26\3\2\2\2\u00a7\u00a8\7G\2\2\u00a8\u00a9\7G\2\2\u00a9"+
		"\30\3\2\2\2\u00aa\u00ab\7Q\2\2\u00ab\u00ac\7U\2\2\u00ac\32\3\2\2\2\u00ad"+
		"\u00ae\7n\2\2\u00ae\u00af\7q\2\2\u00af\u00b0\7c\2\2\u00b0\u00b1\7f\2\2"+
		"\u00b1\34\3\2\2\2\u00b2\u00b3\7u\2\2\u00b3\u00b4\7g\2\2\u00b4\u00b5\7"+
		"v\2\2\u00b5\36\3\2\2\2\u00b6\u00b7\7k\2\2\u00b7\u00b8\7p\2\2\u00b8\u00b9"+
		"\7x\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7m\2\2\u00bb\u00bc\7g\2\2\u00bc"+
		" \3\2\2\2\u00bd\u00be\7k\2\2\u00be\u00bf\7p\2\2\u00bf\u00c0\7h\2\2\u00c0"+
		"\u00c1\7q\2\2\u00c1\"\3\2\2\2\u00c2\u00c3\7m\2\2\u00c3\u00c4\7k\2\2\u00c4"+
		"\u00c5\7n\2\2\u00c5\u00c6\7n\2\2\u00c6$\3\2\2\2\u00c7\u00c8\7e\2\2\u00c8"+
		"\u00c9\7c\2\2\u00c9\u00ca\7v\2\2\u00ca&\3\2\2\2\u00cb\u00cc\7t\2\2\u00cc"+
		"\u00cd\7g\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7v\2\2\u00cf\u00d0\7c\2\2"+
		"\u00d0\u00d1\7t\2\2\u00d1\u00d2\7v\2\2\u00d2(\3\2\2\2\u00d3\u00d4\7e\2"+
		"\2\u00d4\u00d5\7n\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7\7c\2\2\u00d7\u00d8"+
		"\7p\2\2\u00d8*\3\2\2\2\u00d9\u00da\7u\2\2\u00da\u00db\7n\2\2\u00db\u00dc"+
		"\7g\2\2\u00dc\u00dd\7g\2\2\u00dd\u00de\7r\2\2\u00de,\3\2\2\2\u00df\u00e0"+
		"\7u\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2\7x\2\2\u00e2\u00e3\7g\2\2\u00e3"+
		".\3\2\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7z\2\2\u00e6\u00e7\7k\2\2\u00e7"+
		"\u00e8\7v\2\2\u00e8\60\3\2\2\2\u00e9\u00ea\7u\2\2\u00ea\u00eb\7j\2\2\u00eb"+
		"\u00ec\7q\2\2\u00ec\u00ed\7y\2\2\u00ed\u00ee\7U\2\2\u00ee\u00ef\7U\2\2"+
		"\u00ef\u00f0\7J\2\2\u00f0\62\3\2\2\2\u00f1\u00f2\7,\2\2\u00f2\64\3\2\2"+
		"\2\u00f3\u00f4\7o\2\2\u00f4\u00f5\7g\2\2\u00f5\u00f6\7o\2\2\u00f6\u00f7"+
		"\7d\2\2\u00f7\u00f8\7g\2\2\u00f8\u00f9\7t\2\2\u00f9\66\3\2\2\2\u00fa\u00fb"+
		"\7e\2\2\u00fb\u00fc\7n\2\2\u00fc\u00fd\7k\2\2\u00fd\u00fe\7g\2\2\u00fe"+
		"\u00ff\7p\2\2\u00ff\u0100\7v\2\2\u01008\3\2\2\2\u0101\u0102\7O\2\2\u0102"+
		"\u0103\7g\2\2\u0103\u0104\7o\2\2\u0104\u0105\7d\2\2\u0105\u0106\7g\2\2"+
		"\u0106\u0107\7t\2\2\u0107\u0108\3\2\2\2\u0108\u0109\5\63\32\2\u0109:\3"+
		"\2\2\2\u010a\u010b\7O\2\2\u010b\u010c\7g\2\2\u010c\u010d\7o\2\2\u010d"+
		"\u010e\7d\2\2\u010e\u010f\7g\2\2\u010f\u0110\7t\2\2\u0110\u0111\3\2\2"+
		"\2\u0111\u0112\5Q)\2\u0112<\3\2\2\2\u0113\u0114\7E\2\2\u0114\u0115\7n"+
		"\2\2\u0115\u0116\7k\2\2\u0116\u0117\7g\2\2\u0117\u0118\7p\2\2\u0118\u0119"+
		"\7v\2\2\u0119\u011a\3\2\2\2\u011a\u011b\5\63\32\2\u011b>\3\2\2\2\u011c"+
		"\u011d\7E\2\2\u011d\u011e\7n\2\2\u011e\u011f\7k\2\2\u011f\u0120\7g\2\2"+
		"\u0120\u0121\7p\2\2\u0121\u0122\7v\2\2\u0122\u0123\3\2\2\2\u0123\u0124"+
		"\5Q)\2\u0124@\3\2\2\2\u0125\u0126\7o\2\2\u0126\u0127\7g\2\2\u0127\u0128"+
		"\7o\2\2\u0128\u0129\7d\2\2\u0129\u012a\7g\2\2\u012a\u012b\7t\2\2\u012b"+
		"\u012c\7u\2\2\u012c\u012d\7Q\2\2\u012d\u012e\7p\2\2\u012e\u012f\7n\2\2"+
		"\u012f\u0130\7{\2\2\u0130B\3\2\2\2\u0131\u0132\7o\2\2\u0132\u0133\7g\2"+
		"\2\u0133\u0134\7o\2\2\u0134\u0135\7d\2\2\u0135\u0136\7g\2\2\u0136\u0137"+
		"\7t\2\2\u0137\u0138\7u\2\2\u0138D\3\2\2\2\u0139\u013a\7e\2\2\u013a\u013b"+
		"\7n\2\2\u013b\u013c\7k\2\2\u013c\u013d\7g\2\2\u013d\u013e\7p\2\2\u013e"+
		"\u013f\7v\2\2\u013f\u0140\7u\2\2\u0140F\3\2\2\2\u0141\u0142\7?\2\2\u0142"+
		"H\3\2\2\2\u0143\u0146\5K&\2\u0144\u0146\5M\'\2\u0145\u0143\3\2\2\2\u0145"+
		"\u0144\3\2\2\2\u0146J\3\2\2\2\u0147\u0148\7v\2\2\u0148\u0149\7t\2\2\u0149"+
		"\u014a\7w\2\2\u014a\u014b\7g\2\2\u014bL\3\2\2\2\u014c\u014d\7h\2\2\u014d"+
		"\u014e\7c\2\2\u014e\u014f\7n\2\2\u014f\u0150\7u\2\2\u0150\u0151\7g\2\2"+
		"\u0151N\3\2\2\2\u0152\u0154\t\2\2\2\u0153\u0152\3\2\2\2\u0154\u0158\3"+
		"\2\2\2\u0155\u0157\t\3\2\2\u0156\u0155\3\2\2\2\u0157\u015a\3\2\2\2\u0158"+
		"\u0156\3\2\2\2\u0158\u0159\3\2\2\2\u0159P\3\2\2\2\u015a\u0158\3\2\2\2"+
		"\u015b\u015d\t\4\2\2\u015c\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e\u015c"+
		"\3\2\2\2\u015e\u015f\3\2\2\2\u015fR\3\2\2\2\u0160\u0162\t\5\2\2\u0161"+
		"\u0160\3\2\2\2\u0162\u0163\3\2\2\2\u0163\u0161\3\2\2\2\u0163\u0164\3\2"+
		"\2\2\u0164\u0165\3\2\2\2\u0165\u0166\b*\2\2\u0166T\3\2\2\2\u0167\u0169"+
		"\t\6\2\2\u0168\u0167\3\2\2\2\u0169V\3\2\2\2\u016a\u016c\7$\2\2\u016b\u016d"+
		"\5U+\2\u016c\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016c\3\2\2\2\u016e"+
		"\u016f\3\2\2\2\u016f\u0170\3\2\2\2\u0170\u0171\7$\2\2\u0171X\3\2\2\2\u0172"+
		"\u0173\5[.\2\u0173\u0174\7.\2\2\u0174\u0175\5[.\2\u0175Z\3\2\2\2\u0176"+
		"\u0177\5]/\2\u0177\u0178\7\60\2\2\u0178\u0179\5]/\2\u0179\u017a\7\60\2"+
		"\2\u017a\u017b\5]/\2\u017b\u017c\7\60\2\2\u017c\u017d\5]/\2\u017d\\\3"+
		"\2\2\2\u017e\u017f\5_\60\2\u017f\u0180\5_\60\2\u0180\u0181\5_\60\2\u0181"+
		"\u0187\3\2\2\2\u0182\u0183\5_\60\2\u0183\u0184\5_\60\2\u0184\u0187\3\2"+
		"\2\2\u0185\u0187\5_\60\2\u0186\u017e\3\2\2\2\u0186\u0182\3\2\2\2\u0186"+
		"\u0185\3\2\2\2\u0187^\3\2\2\2\u0188\u0189\4\62;\2\u0189`\3\2\2\2\u018a"+
		"\u018b\7\61\2\2\u018b\u018c\7\61\2\2\u018c\u0190\3\2\2\2\u018d\u018f\n"+
		"\7\2\2\u018e\u018d\3\2\2\2\u018f\u0192\3\2\2\2\u0190\u018e\3\2\2\2\u0190"+
		"\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192\u0190\3\2\2\2\u0193\u0194\b\61"+
		"\2\2\u0194b\3\2\2\2\r\2\u0145\u0153\u0156\u0158\u015e\u0163\u0168\u016e"+
		"\u0186\u0190\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}