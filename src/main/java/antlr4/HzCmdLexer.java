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
		STRING=1, HOMEIP=2, USER=3, VERSION=4, ADD=5, MEMBER_BOX=6, BOXES=7, IP=8, 
		CLUSTER=9, REPLICATE=10, INSTALL=11, UNINSTALL=12, EE=13, OS=14, LOAD=15, 
		SET=16, INVOKE=17, STOP=18, INFO=19, KILL=20, CAT=21, GREP=22, RESTART=23, 
		CLEAN=24, SAVE=25, EXIT=26, DOWNLOAD=27, SHOWSSH=28, PROMPT=29, ALL=30, 
		MEMBER=31, CLIENT=32, MEMBER_ALL=33, MEMBER_VAR=34, CLIENT_ALL=35, CLIENT_VAR=36, 
		MEMBERS_ONLY=37, MEMBERS=38, CLIENTS=39, BOOL=40, TRUE=41, FALSE=42, VAR=43, 
		NUMBER=44;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"STRING", "HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", 
		"CLUSTER", "REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", 
		"INVOKE", "STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SAVE", 
		"EXIT", "DOWNLOAD", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
		"MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", 
		"BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "CHAR", "Octet", "Digit"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'homeIp'", "'user'", "'version'", "'add'", "'memberBox'", 
		"'boxes'", "'ip'", "'cluster'", "'replicate'", "'install'", "'uninstall'", 
		"'EE'", "'OS'", "'load'", "'set'", "'invoke'", "'stop'", "'info'", "'kill'", 
		"'cat'", "'grep'", "'restart'", "'clean'", "'save'", "'exit'", "'download'", 
		"'showSSH'", "'prompt'", "'*'", "'member'", "'client'", null, null, null, 
		null, "'membersOnly'", "'members'", "'clients'", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STRING", "HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", 
		"IP", "CLUSTER", "REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", 
		"SET", "INVOKE", "STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", 
		"SAVE", "EXIT", "DOWNLOAD", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", 
		"MEMBER_ALL", "MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", 
		"MEMBERS", "CLIENTS", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2.\u018f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\3\2\6\2c\n\2\r\2\16\2d\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3"+
		"\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3"+
		"$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\3&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)"+
		"\3)\5)\u0166\n)\3*\3*\3*\3*\3*\3+\3+\3+\3+\3+\3+\3,\5,\u0174\n,\3,\7,"+
		"\u0177\n,\f,\16,\u017a\13,\3-\6-\u017d\n-\r-\16-\u017e\3.\5.\u0182\n."+
		"\3/\3/\3/\3/\3/\3/\3/\3/\5/\u018c\n/\3\60\3\60\2\2\61\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K"+
		"\'M(O)Q*S+U,W-Y.[\2]\2_\2\3\2\5\4\2C\\c|\6\2\62;C\\aac|\13\2\"\"%%,,/"+
		"<??C_aac|~~\u0191\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3b\3\2\2\2\5f\3\2\2\2"+
		"\7m\3\2\2\2\tr\3\2\2\2\13z\3\2\2\2\r~\3\2\2\2\17\u0088\3\2\2\2\21\u008e"+
		"\3\2\2\2\23\u0091\3\2\2\2\25\u0099\3\2\2\2\27\u00a3\3\2\2\2\31\u00ab\3"+
		"\2\2\2\33\u00b5\3\2\2\2\35\u00b8\3\2\2\2\37\u00bb\3\2\2\2!\u00c0\3\2\2"+
		"\2#\u00c4\3\2\2\2%\u00cb\3\2\2\2\'\u00d0\3\2\2\2)\u00d5\3\2\2\2+\u00da"+
		"\3\2\2\2-\u00de\3\2\2\2/\u00e3\3\2\2\2\61\u00eb\3\2\2\2\63\u00f1\3\2\2"+
		"\2\65\u00f6\3\2\2\2\67\u00fb\3\2\2\29\u0104\3\2\2\2;\u010c\3\2\2\2=\u0113"+
		"\3\2\2\2?\u0115\3\2\2\2A\u011c\3\2\2\2C\u0123\3\2\2\2E\u012c\3\2\2\2G"+
		"\u0135\3\2\2\2I\u013e\3\2\2\2K\u0147\3\2\2\2M\u0153\3\2\2\2O\u015b\3\2"+
		"\2\2Q\u0165\3\2\2\2S\u0167\3\2\2\2U\u016c\3\2\2\2W\u0173\3\2\2\2Y\u017c"+
		"\3\2\2\2[\u0181\3\2\2\2]\u018b\3\2\2\2_\u018d\3\2\2\2ac\5[.\2ba\3\2\2"+
		"\2cd\3\2\2\2db\3\2\2\2de\3\2\2\2e\4\3\2\2\2fg\7j\2\2gh\7q\2\2hi\7o\2\2"+
		"ij\7g\2\2jk\7K\2\2kl\7r\2\2l\6\3\2\2\2mn\7w\2\2no\7u\2\2op\7g\2\2pq\7"+
		"t\2\2q\b\3\2\2\2rs\7x\2\2st\7g\2\2tu\7t\2\2uv\7u\2\2vw\7k\2\2wx\7q\2\2"+
		"xy\7p\2\2y\n\3\2\2\2z{\7c\2\2{|\7f\2\2|}\7f\2\2}\f\3\2\2\2~\177\7o\2\2"+
		"\177\u0080\7g\2\2\u0080\u0081\7o\2\2\u0081\u0082\7d\2\2\u0082\u0083\7"+
		"g\2\2\u0083\u0084\7t\2\2\u0084\u0085\7D\2\2\u0085\u0086\7q\2\2\u0086\u0087"+
		"\7z\2\2\u0087\16\3\2\2\2\u0088\u0089\7d\2\2\u0089\u008a\7q\2\2\u008a\u008b"+
		"\7z\2\2\u008b\u008c\7g\2\2\u008c\u008d\7u\2\2\u008d\20\3\2\2\2\u008e\u008f"+
		"\7k\2\2\u008f\u0090\7r\2\2\u0090\22\3\2\2\2\u0091\u0092\7e\2\2\u0092\u0093"+
		"\7n\2\2\u0093\u0094\7w\2\2\u0094\u0095\7u\2\2\u0095\u0096\7v\2\2\u0096"+
		"\u0097\7g\2\2\u0097\u0098\7t\2\2\u0098\24\3\2\2\2\u0099\u009a\7t\2\2\u009a"+
		"\u009b\7g\2\2\u009b\u009c\7r\2\2\u009c\u009d\7n\2\2\u009d\u009e\7k\2\2"+
		"\u009e\u009f\7e\2\2\u009f\u00a0\7c\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2"+
		"\7g\2\2\u00a2\26\3\2\2\2\u00a3\u00a4\7k\2\2\u00a4\u00a5\7p\2\2\u00a5\u00a6"+
		"\7u\2\2\u00a6\u00a7\7v\2\2\u00a7\u00a8\7c\2\2\u00a8\u00a9\7n\2\2\u00a9"+
		"\u00aa\7n\2\2\u00aa\30\3\2\2\2\u00ab\u00ac\7w\2\2\u00ac\u00ad\7p\2\2\u00ad"+
		"\u00ae\7k\2\2\u00ae\u00af\7p\2\2\u00af\u00b0\7u\2\2\u00b0\u00b1\7v\2\2"+
		"\u00b1\u00b2\7c\2\2\u00b2\u00b3\7n\2\2\u00b3\u00b4\7n\2\2\u00b4\32\3\2"+
		"\2\2\u00b5\u00b6\7G\2\2\u00b6\u00b7\7G\2\2\u00b7\34\3\2\2\2\u00b8\u00b9"+
		"\7Q\2\2\u00b9\u00ba\7U\2\2\u00ba\36\3\2\2\2\u00bb\u00bc\7n\2\2\u00bc\u00bd"+
		"\7q\2\2\u00bd\u00be\7c\2\2\u00be\u00bf\7f\2\2\u00bf \3\2\2\2\u00c0\u00c1"+
		"\7u\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7v\2\2\u00c3\"\3\2\2\2\u00c4\u00c5"+
		"\7k\2\2\u00c5\u00c6\7p\2\2\u00c6\u00c7\7x\2\2\u00c7\u00c8\7q\2\2\u00c8"+
		"\u00c9\7m\2\2\u00c9\u00ca\7g\2\2\u00ca$\3\2\2\2\u00cb\u00cc\7u\2\2\u00cc"+
		"\u00cd\7v\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7r\2\2\u00cf&\3\2\2\2\u00d0"+
		"\u00d1\7k\2\2\u00d1\u00d2\7p\2\2\u00d2\u00d3\7h\2\2\u00d3\u00d4\7q\2\2"+
		"\u00d4(\3\2\2\2\u00d5\u00d6\7m\2\2\u00d6\u00d7\7k\2\2\u00d7\u00d8\7n\2"+
		"\2\u00d8\u00d9\7n\2\2\u00d9*\3\2\2\2\u00da\u00db\7e\2\2\u00db\u00dc\7"+
		"c\2\2\u00dc\u00dd\7v\2\2\u00dd,\3\2\2\2\u00de\u00df\7i\2\2\u00df\u00e0"+
		"\7t\2\2\u00e0\u00e1\7g\2\2\u00e1\u00e2\7r\2\2\u00e2.\3\2\2\2\u00e3\u00e4"+
		"\7t\2\2\u00e4\u00e5\7g\2\2\u00e5\u00e6\7u\2\2\u00e6\u00e7\7v\2\2\u00e7"+
		"\u00e8\7c\2\2\u00e8\u00e9\7t\2\2\u00e9\u00ea\7v\2\2\u00ea\60\3\2\2\2\u00eb"+
		"\u00ec\7e\2\2\u00ec\u00ed\7n\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7c\2\2"+
		"\u00ef\u00f0\7p\2\2\u00f0\62\3\2\2\2\u00f1\u00f2\7u\2\2\u00f2\u00f3\7"+
		"c\2\2\u00f3\u00f4\7x\2\2\u00f4\u00f5\7g\2\2\u00f5\64\3\2\2\2\u00f6\u00f7"+
		"\7g\2\2\u00f7\u00f8\7z\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa\7v\2\2\u00fa"+
		"\66\3\2\2\2\u00fb\u00fc\7f\2\2\u00fc\u00fd\7q\2\2\u00fd\u00fe\7y\2\2\u00fe"+
		"\u00ff\7p\2\2\u00ff\u0100\7n\2\2\u0100\u0101\7q\2\2\u0101\u0102\7c\2\2"+
		"\u0102\u0103\7f\2\2\u01038\3\2\2\2\u0104\u0105\7u\2\2\u0105\u0106\7j\2"+
		"\2\u0106\u0107\7q\2\2\u0107\u0108\7y\2\2\u0108\u0109\7U\2\2\u0109\u010a"+
		"\7U\2\2\u010a\u010b\7J\2\2\u010b:\3\2\2\2\u010c\u010d\7r\2\2\u010d\u010e"+
		"\7t\2\2\u010e\u010f\7q\2\2\u010f\u0110\7o\2\2\u0110\u0111\7r\2\2\u0111"+
		"\u0112\7v\2\2\u0112<\3\2\2\2\u0113\u0114\7,\2\2\u0114>\3\2\2\2\u0115\u0116"+
		"\7o\2\2\u0116\u0117\7g\2\2\u0117\u0118\7o\2\2\u0118\u0119\7d\2\2\u0119"+
		"\u011a\7g\2\2\u011a\u011b\7t\2\2\u011b@\3\2\2\2\u011c\u011d\7e\2\2\u011d"+
		"\u011e\7n\2\2\u011e\u011f\7k\2\2\u011f\u0120\7g\2\2\u0120\u0121\7p\2\2"+
		"\u0121\u0122\7v\2\2\u0122B\3\2\2\2\u0123\u0124\7O\2\2\u0124\u0125\7g\2"+
		"\2\u0125\u0126\7o\2\2\u0126\u0127\7d\2\2\u0127\u0128\7g\2\2\u0128\u0129"+
		"\7t\2\2\u0129\u012a\3\2\2\2\u012a\u012b\5=\37\2\u012bD\3\2\2\2\u012c\u012d"+
		"\7O\2\2\u012d\u012e\7g\2\2\u012e\u012f\7o\2\2\u012f\u0130\7d\2\2\u0130"+
		"\u0131\7g\2\2\u0131\u0132\7t\2\2\u0132\u0133\3\2\2\2\u0133\u0134\5Y-\2"+
		"\u0134F\3\2\2\2\u0135\u0136\7E\2\2\u0136\u0137\7n\2\2\u0137\u0138\7k\2"+
		"\2\u0138\u0139\7g\2\2\u0139\u013a\7p\2\2\u013a\u013b\7v\2\2\u013b\u013c"+
		"\3\2\2\2\u013c\u013d\5=\37\2\u013dH\3\2\2\2\u013e\u013f\7E\2\2\u013f\u0140"+
		"\7n\2\2\u0140\u0141\7k\2\2\u0141\u0142\7g\2\2\u0142\u0143\7p\2\2\u0143"+
		"\u0144\7v\2\2\u0144\u0145\3\2\2\2\u0145\u0146\5Y-\2\u0146J\3\2\2\2\u0147"+
		"\u0148\7o\2\2\u0148\u0149\7g\2\2\u0149\u014a\7o\2\2\u014a\u014b\7d\2\2"+
		"\u014b\u014c\7g\2\2\u014c\u014d\7t\2\2\u014d\u014e\7u\2\2\u014e\u014f"+
		"\7Q\2\2\u014f\u0150\7p\2\2\u0150\u0151\7n\2\2\u0151\u0152\7{\2\2\u0152"+
		"L\3\2\2\2\u0153\u0154\7o\2\2\u0154\u0155\7g\2\2\u0155\u0156\7o\2\2\u0156"+
		"\u0157\7d\2\2\u0157\u0158\7g\2\2\u0158\u0159\7t\2\2\u0159\u015a\7u\2\2"+
		"\u015aN\3\2\2\2\u015b\u015c\7e\2\2\u015c\u015d\7n\2\2\u015d\u015e\7k\2"+
		"\2\u015e\u015f\7g\2\2\u015f\u0160\7p\2\2\u0160\u0161\7v\2\2\u0161\u0162"+
		"\7u\2\2\u0162P\3\2\2\2\u0163\u0166\5S*\2\u0164\u0166\5U+\2\u0165\u0163"+
		"\3\2\2\2\u0165\u0164\3\2\2\2\u0166R\3\2\2\2\u0167\u0168\7v\2\2\u0168\u0169"+
		"\7t\2\2\u0169\u016a\7w\2\2\u016a\u016b\7g\2\2\u016bT\3\2\2\2\u016c\u016d"+
		"\7h\2\2\u016d\u016e\7c\2\2\u016e\u016f\7n\2\2\u016f\u0170\7u\2\2\u0170"+
		"\u0171\7g\2\2\u0171V\3\2\2\2\u0172\u0174\t\2\2\2\u0173\u0172\3\2\2\2\u0174"+
		"\u0178\3\2\2\2\u0175\u0177\t\3\2\2\u0176\u0175\3\2\2\2\u0177\u017a\3\2"+
		"\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179X\3\2\2\2\u017a\u0178"+
		"\3\2\2\2\u017b\u017d\5_\60\2\u017c\u017b\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017fZ\3\2\2\2\u0180\u0182\t\4\2\2"+
		"\u0181\u0180\3\2\2\2\u0182\\\3\2\2\2\u0183\u0184\5_\60\2\u0184\u0185\5"+
		"_\60\2\u0185\u0186\5_\60\2\u0186\u018c\3\2\2\2\u0187\u0188\5_\60\2\u0188"+
		"\u0189\5_\60\2\u0189\u018c\3\2\2\2\u018a\u018c\5_\60\2\u018b\u0183\3\2"+
		"\2\2\u018b\u0187\3\2\2\2\u018b\u018a\3\2\2\2\u018c^\3\2\2\2\u018d\u018e"+
		"\4\62;\2\u018e`\3\2\2\2\13\2d\u0165\u0173\u0176\u0178\u017e\u0181\u018b"+
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