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
		SLEEP=17, SAVE=18, EXIT=19, SHOWSSH=20, ALL=21, MEMBER=22, CLIENT=23, 
		MEMBER_ALL=24, MEMBER_VAR=25, CLIENT_ALL=26, CLIENT_VAR=27, MEMBERS_ONLY=28, 
		MEMBERS=29, CLIENTS=30, ASSIGN=31, BOOL=32, TRUE=33, FALSE=34, VAR=35, 
		NUMBER=36, WHITESPACE=37, STRING=38, IP_PAIR=39, IP_STR=40, COMMENT=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", "INSTALL", 
		"EE", "OS", "LOAD", "SET", "INVOKE", "KILL", "CAT", "RESTART", "SLEEP", 
		"SAVE", "EXIT", "SHOWSSH", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", 
		"CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", 
		"BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", "STRING", 
		"IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'user'", "'version'", "'add'", "'file'", "'ip'", "'cluster'", "'replicate'", 
		"'install'", "'EE'", "'OS'", "'load'", "'set'", "'invoke'", "'kill'", 
		"'cat'", "'restart'", "'sleep'", "'save'", "'exit'", "'showSSH'", "'*'", 
		"'member'", "'client'", null, null, null, null, "'membersOnly'", "'members'", 
		"'clients'", "'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "USER", "VERSION", "ADD", "FILE", "IP", "CLUSTER", "REPLICATE", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0171\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3"+
		"\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		"\37\3\37\3 \3 \3!\3!\5!\u0122\n!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3"+
		"#\3$\5$\u0130\n$\3$\7$\u0133\n$\f$\16$\u0136\13$\3%\6%\u0139\n%\r%\16"+
		"%\u013a\3&\6&\u013e\n&\r&\16&\u013f\3&\3&\3\'\5\'\u0145\n\'\3(\3(\6(\u0149"+
		"\n(\r(\16(\u014a\3(\3(\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3+\3+\3+\3+\5+\u0163\n+\3,\3,\3-\3-\3-\3-\7-\u016b\n-\f-\16-\u016e\13"+
		"-\3-\3-\2\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16"+
		"\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34"+
		"\67\359\36;\37= ?!A\"C#E$G%I&K\'M\2O(Q)S*U\2W\2Y+\3\2\b\4\2C\\c|\6\2\62"+
		";C\\aac|\3\2\62;\5\2\13\f\16\17\"\"\b\2\"\"/;??C\\aac|\4\2\f\f\17\17\u0175"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2Y\3\2\2\2\3"+
		"[\3\2\2\2\5`\3\2\2\2\7h\3\2\2\2\tl\3\2\2\2\13q\3\2\2\2\rt\3\2\2\2\17|"+
		"\3\2\2\2\21\u0086\3\2\2\2\23\u008e\3\2\2\2\25\u0091\3\2\2\2\27\u0094\3"+
		"\2\2\2\31\u0099\3\2\2\2\33\u009d\3\2\2\2\35\u00a4\3\2\2\2\37\u00a9\3\2"+
		"\2\2!\u00ad\3\2\2\2#\u00b5\3\2\2\2%\u00bb\3\2\2\2\'\u00c0\3\2\2\2)\u00c5"+
		"\3\2\2\2+\u00cd\3\2\2\2-\u00cf\3\2\2\2/\u00d6\3\2\2\2\61\u00dd\3\2\2\2"+
		"\63\u00e6\3\2\2\2\65\u00ef\3\2\2\2\67\u00f8\3\2\2\29\u0101\3\2\2\2;\u010d"+
		"\3\2\2\2=\u0115\3\2\2\2?\u011d\3\2\2\2A\u0121\3\2\2\2C\u0123\3\2\2\2E"+
		"\u0128\3\2\2\2G\u012f\3\2\2\2I\u0138\3\2\2\2K\u013d\3\2\2\2M\u0144\3\2"+
		"\2\2O\u0146\3\2\2\2Q\u014e\3\2\2\2S\u0152\3\2\2\2U\u0162\3\2\2\2W\u0164"+
		"\3\2\2\2Y\u0166\3\2\2\2[\\\7w\2\2\\]\7u\2\2]^\7g\2\2^_\7t\2\2_\4\3\2\2"+
		"\2`a\7x\2\2ab\7g\2\2bc\7t\2\2cd\7u\2\2de\7k\2\2ef\7q\2\2fg\7p\2\2g\6\3"+
		"\2\2\2hi\7c\2\2ij\7f\2\2jk\7f\2\2k\b\3\2\2\2lm\7h\2\2mn\7k\2\2no\7n\2"+
		"\2op\7g\2\2p\n\3\2\2\2qr\7k\2\2rs\7r\2\2s\f\3\2\2\2tu\7e\2\2uv\7n\2\2"+
		"vw\7w\2\2wx\7u\2\2xy\7v\2\2yz\7g\2\2z{\7t\2\2{\16\3\2\2\2|}\7t\2\2}~\7"+
		"g\2\2~\177\7r\2\2\177\u0080\7n\2\2\u0080\u0081\7k\2\2\u0081\u0082\7e\2"+
		"\2\u0082\u0083\7c\2\2\u0083\u0084\7v\2\2\u0084\u0085\7g\2\2\u0085\20\3"+
		"\2\2\2\u0086\u0087\7k\2\2\u0087\u0088\7p\2\2\u0088\u0089\7u\2\2\u0089"+
		"\u008a\7v\2\2\u008a\u008b\7c\2\2\u008b\u008c\7n\2\2\u008c\u008d\7n\2\2"+
		"\u008d\22\3\2\2\2\u008e\u008f\7G\2\2\u008f\u0090\7G\2\2\u0090\24\3\2\2"+
		"\2\u0091\u0092\7Q\2\2\u0092\u0093\7U\2\2\u0093\26\3\2\2\2\u0094\u0095"+
		"\7n\2\2\u0095\u0096\7q\2\2\u0096\u0097\7c\2\2\u0097\u0098\7f\2\2\u0098"+
		"\30\3\2\2\2\u0099\u009a\7u\2\2\u009a\u009b\7g\2\2\u009b\u009c\7v\2\2\u009c"+
		"\32\3\2\2\2\u009d\u009e\7k\2\2\u009e\u009f\7p\2\2\u009f\u00a0\7x\2\2\u00a0"+
		"\u00a1\7q\2\2\u00a1\u00a2\7m\2\2\u00a2\u00a3\7g\2\2\u00a3\34\3\2\2\2\u00a4"+
		"\u00a5\7m\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7n\2\2\u00a7\u00a8\7n\2\2"+
		"\u00a8\36\3\2\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7"+
		"v\2\2\u00ac \3\2\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af\7g\2\2\u00af\u00b0"+
		"\7u\2\2\u00b0\u00b1\7v\2\2\u00b1\u00b2\7c\2\2\u00b2\u00b3\7t\2\2\u00b3"+
		"\u00b4\7v\2\2\u00b4\"\3\2\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7n\2\2\u00b7"+
		"\u00b8\7g\2\2\u00b8\u00b9\7g\2\2\u00b9\u00ba\7r\2\2\u00ba$\3\2\2\2\u00bb"+
		"\u00bc\7u\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7x\2\2\u00be\u00bf\7g\2\2"+
		"\u00bf&\3\2\2\2\u00c0\u00c1\7g\2\2\u00c1\u00c2\7z\2\2\u00c2\u00c3\7k\2"+
		"\2\u00c3\u00c4\7v\2\2\u00c4(\3\2\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7"+
		"j\2\2\u00c7\u00c8\7q\2\2\u00c8\u00c9\7y\2\2\u00c9\u00ca\7U\2\2\u00ca\u00cb"+
		"\7U\2\2\u00cb\u00cc\7J\2\2\u00cc*\3\2\2\2\u00cd\u00ce\7,\2\2\u00ce,\3"+
		"\2\2\2\u00cf\u00d0\7o\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2\7o\2\2\u00d2"+
		"\u00d3\7d\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7t\2\2\u00d5.\3\2\2\2\u00d6"+
		"\u00d7\7e\2\2\u00d7\u00d8\7n\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7g\2\2"+
		"\u00da\u00db\7p\2\2\u00db\u00dc\7v\2\2\u00dc\60\3\2\2\2\u00dd\u00de\7"+
		"O\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7o\2\2\u00e0\u00e1\7d\2\2\u00e1\u00e2"+
		"\7g\2\2\u00e2\u00e3\7t\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\5+\26\2\u00e5"+
		"\62\3\2\2\2\u00e6\u00e7\7O\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7o\2\2\u00e9"+
		"\u00ea\7d\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7t\2\2\u00ec\u00ed\3\2\2"+
		"\2\u00ed\u00ee\5I%\2\u00ee\64\3\2\2\2\u00ef\u00f0\7E\2\2\u00f0\u00f1\7"+
		"n\2\2\u00f1\u00f2\7k\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7p\2\2\u00f4\u00f5"+
		"\7v\2\2\u00f5\u00f6\3\2\2\2\u00f6\u00f7\5+\26\2\u00f7\66\3\2\2\2\u00f8"+
		"\u00f9\7E\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc\7g\2\2"+
		"\u00fc\u00fd\7p\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0100"+
		"\5I%\2\u01008\3\2\2\2\u0101\u0102\7o\2\2\u0102\u0103\7g\2\2\u0103\u0104"+
		"\7o\2\2\u0104\u0105\7d\2\2\u0105\u0106\7g\2\2\u0106\u0107\7t\2\2\u0107"+
		"\u0108\7u\2\2\u0108\u0109\7Q\2\2\u0109\u010a\7p\2\2\u010a\u010b\7n\2\2"+
		"\u010b\u010c\7{\2\2\u010c:\3\2\2\2\u010d\u010e\7o\2\2\u010e\u010f\7g\2"+
		"\2\u010f\u0110\7o\2\2\u0110\u0111\7d\2\2\u0111\u0112\7g\2\2\u0112\u0113"+
		"\7t\2\2\u0113\u0114\7u\2\2\u0114<\3\2\2\2\u0115\u0116\7e\2\2\u0116\u0117"+
		"\7n\2\2\u0117\u0118\7k\2\2\u0118\u0119\7g\2\2\u0119\u011a\7p\2\2\u011a"+
		"\u011b\7v\2\2\u011b\u011c\7u\2\2\u011c>\3\2\2\2\u011d\u011e\7?\2\2\u011e"+
		"@\3\2\2\2\u011f\u0122\5C\"\2\u0120\u0122\5E#\2\u0121\u011f\3\2\2\2\u0121"+
		"\u0120\3\2\2\2\u0122B\3\2\2\2\u0123\u0124\7v\2\2\u0124\u0125\7t\2\2\u0125"+
		"\u0126\7w\2\2\u0126\u0127\7g\2\2\u0127D\3\2\2\2\u0128\u0129\7h\2\2\u0129"+
		"\u012a\7c\2\2\u012a\u012b\7n\2\2\u012b\u012c\7u\2\2\u012c\u012d\7g\2\2"+
		"\u012dF\3\2\2\2\u012e\u0130\t\2\2\2\u012f\u012e\3\2\2\2\u0130\u0134\3"+
		"\2\2\2\u0131\u0133\t\3\2\2\u0132\u0131\3\2\2\2\u0133\u0136\3\2\2\2\u0134"+
		"\u0132\3\2\2\2\u0134\u0135\3\2\2\2\u0135H\3\2\2\2\u0136\u0134\3\2\2\2"+
		"\u0137\u0139\t\4\2\2\u0138\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u0138"+
		"\3\2\2\2\u013a\u013b\3\2\2\2\u013bJ\3\2\2\2\u013c\u013e\t\5\2\2\u013d"+
		"\u013c\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u013d\3\2\2\2\u013f\u0140\3\2"+
		"\2\2\u0140\u0141\3\2\2\2\u0141\u0142\b&\2\2\u0142L\3\2\2\2\u0143\u0145"+
		"\t\6\2\2\u0144\u0143\3\2\2\2\u0145N\3\2\2\2\u0146\u0148\7$\2\2\u0147\u0149"+
		"\5M\'\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\7$\2\2\u014dP\3\2\2\2\u014e"+
		"\u014f\5S*\2\u014f\u0150\7.\2\2\u0150\u0151\5S*\2\u0151R\3\2\2\2\u0152"+
		"\u0153\5U+\2\u0153\u0154\7\60\2\2\u0154\u0155\5U+\2\u0155\u0156\7\60\2"+
		"\2\u0156\u0157\5U+\2\u0157\u0158\7\60\2\2\u0158\u0159\5U+\2\u0159T\3\2"+
		"\2\2\u015a\u015b\5W,\2\u015b\u015c\5W,\2\u015c\u015d\5W,\2\u015d\u0163"+
		"\3\2\2\2\u015e\u015f\5W,\2\u015f\u0160\5W,\2\u0160\u0163\3\2\2\2\u0161"+
		"\u0163\5W,\2\u0162\u015a\3\2\2\2\u0162\u015e\3\2\2\2\u0162\u0161\3\2\2"+
		"\2\u0163V\3\2\2\2\u0164\u0165\4\62;\2\u0165X\3\2\2\2\u0166\u0167\7\61"+
		"\2\2\u0167\u0168\7\61\2\2\u0168\u016c\3\2\2\2\u0169\u016b\n\7\2\2\u016a"+
		"\u0169\3\2\2\2\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2"+
		"\2\2\u016d\u016f\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0170\b-\2\2\u0170"+
		"Z\3\2\2\2\r\2\u0121\u012f\u0132\u0134\u013a\u013f\u0144\u014a\u0162\u016c"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}