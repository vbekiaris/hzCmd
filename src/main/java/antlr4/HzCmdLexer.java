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
		SLEEP=24, SAVE=25, EXIT=26, DOWNLOAD=27, SHOWSSH=28, PROMPT=29, ALL=30, 
		MEMBER=31, CLIENT=32, MEMBER_ALL=33, MEMBER_VAR=34, CLIENT_ALL=35, CLIENT_VAR=36, 
		MEMBERS_ONLY=37, MEMBERS=38, CLIENTS=39, ASSIGN=40, BOOL=41, TRUE=42, 
		FALSE=43, VAR=44, NUMBER=45, WHITESPACE=46, STRING=47, IP_PAIR=48, IP_STR=49, 
		COMMENT=50;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", "CLUSTER", 
		"REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", 
		"STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", "SAVE", 
		"EXIT", "DOWNLOAD", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", 
		"MEMBER_VAR", "CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", 
		"ASSIGN", "BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", 
		"STRING", "IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'memberBox'", "'boxes'", 
		"'ip'", "'cluster'", "'replicate'", "'install'", "'uninstall'", "'EE'", 
		"'OS'", "'load'", "'set'", "'invoke'", "'stop'", "'info'", "'kill'", "'cat'", 
		"'grep'", "'restart'", "'clean'", "'sleep'", "'save'", "'exit'", "'download'", 
		"'showSSH'", "'prompt'", "'*'", "'member'", "'client'", null, null, null, 
		null, "'membersOnly'", "'members'", "'clients'", "'='", null, "'true'", 
		"'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", 
		"CLUSTER", "REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", 
		"INVOKE", "STOP", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", 
		"SAVE", "EXIT", "DOWNLOAD", "SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\64\u01be\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\4\65\t\65\4\66\t\66\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3"+
		"\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3"+
		"\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3"+
		"\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36\3"+
		"\36\3\36\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3"+
		"$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3&\3&\3"+
		"&\3&\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3)\3)\3*"+
		"\3*\5*\u0175\n*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\5-\u0183\n-\3-\7-"+
		"\u0186\n-\f-\16-\u0189\13-\3.\6.\u018c\n.\r.\16.\u018d\3/\6/\u0191\n/"+
		"\r/\16/\u0192\3/\3/\3\60\5\60\u0198\n\60\3\61\3\61\3\62\3\62\3\62\3\62"+
		"\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\63\3\64\3\64\3\64\3\64\3\64\3\64"+
		"\3\64\3\64\5\64\u01b0\n\64\3\65\3\65\3\66\3\66\3\66\3\66\7\66\u01b8\n"+
		"\66\f\66\16\66\u01bb\13\66\3\66\3\66\2\2\67\3\3\5\4\7\5\t\6\13\7\r\b\17"+
		"\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+"+
		"\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+"+
		"U,W-Y.[/]\60_\2a\61c\62e\63g\2i\2k\64\3\2\b\4\2C\\c|\6\2\62;C\\aac|\3"+
		"\2\62;\5\2\13\f\16\17\"\"\13\2\"\"%%,,/<??C_aac|~~\4\2\f\f\17\17\u01c1"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2"+
		"U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3\2\2\2\2c\3"+
		"\2\2\2\2e\3\2\2\2\2k\3\2\2\2\3m\3\2\2\2\5t\3\2\2\2\7y\3\2\2\2\t\u0081"+
		"\3\2\2\2\13\u0085\3\2\2\2\r\u008f\3\2\2\2\17\u0095\3\2\2\2\21\u0098\3"+
		"\2\2\2\23\u00a0\3\2\2\2\25\u00aa\3\2\2\2\27\u00b2\3\2\2\2\31\u00bc\3\2"+
		"\2\2\33\u00bf\3\2\2\2\35\u00c2\3\2\2\2\37\u00c7\3\2\2\2!\u00cb\3\2\2\2"+
		"#\u00d2\3\2\2\2%\u00d7\3\2\2\2\'\u00dc\3\2\2\2)\u00e1\3\2\2\2+\u00e5\3"+
		"\2\2\2-\u00ea\3\2\2\2/\u00f2\3\2\2\2\61\u00f8\3\2\2\2\63\u00fe\3\2\2\2"+
		"\65\u0103\3\2\2\2\67\u0108\3\2\2\29\u0111\3\2\2\2;\u0119\3\2\2\2=\u0120"+
		"\3\2\2\2?\u0122\3\2\2\2A\u0129\3\2\2\2C\u0130\3\2\2\2E\u0139\3\2\2\2G"+
		"\u0142\3\2\2\2I\u014b\3\2\2\2K\u0154\3\2\2\2M\u0160\3\2\2\2O\u0168\3\2"+
		"\2\2Q\u0170\3\2\2\2S\u0174\3\2\2\2U\u0176\3\2\2\2W\u017b\3\2\2\2Y\u0182"+
		"\3\2\2\2[\u018b\3\2\2\2]\u0190\3\2\2\2_\u0197\3\2\2\2a\u0199\3\2\2\2c"+
		"\u019b\3\2\2\2e\u019f\3\2\2\2g\u01af\3\2\2\2i\u01b1\3\2\2\2k\u01b3\3\2"+
		"\2\2mn\7j\2\2no\7q\2\2op\7o\2\2pq\7g\2\2qr\7K\2\2rs\7r\2\2s\4\3\2\2\2"+
		"tu\7w\2\2uv\7u\2\2vw\7g\2\2wx\7t\2\2x\6\3\2\2\2yz\7x\2\2z{\7g\2\2{|\7"+
		"t\2\2|}\7u\2\2}~\7k\2\2~\177\7q\2\2\177\u0080\7p\2\2\u0080\b\3\2\2\2\u0081"+
		"\u0082\7c\2\2\u0082\u0083\7f\2\2\u0083\u0084\7f\2\2\u0084\n\3\2\2\2\u0085"+
		"\u0086\7o\2\2\u0086\u0087\7g\2\2\u0087\u0088\7o\2\2\u0088\u0089\7d\2\2"+
		"\u0089\u008a\7g\2\2\u008a\u008b\7t\2\2\u008b\u008c\7D\2\2\u008c\u008d"+
		"\7q\2\2\u008d\u008e\7z\2\2\u008e\f\3\2\2\2\u008f\u0090\7d\2\2\u0090\u0091"+
		"\7q\2\2\u0091\u0092\7z\2\2\u0092\u0093\7g\2\2\u0093\u0094\7u\2\2\u0094"+
		"\16\3\2\2\2\u0095\u0096\7k\2\2\u0096\u0097\7r\2\2\u0097\20\3\2\2\2\u0098"+
		"\u0099\7e\2\2\u0099\u009a\7n\2\2\u009a\u009b\7w\2\2\u009b\u009c\7u\2\2"+
		"\u009c\u009d\7v\2\2\u009d\u009e\7g\2\2\u009e\u009f\7t\2\2\u009f\22\3\2"+
		"\2\2\u00a0\u00a1\7t\2\2\u00a1\u00a2\7g\2\2\u00a2\u00a3\7r\2\2\u00a3\u00a4"+
		"\7n\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7e\2\2\u00a6\u00a7\7c\2\2\u00a7"+
		"\u00a8\7v\2\2\u00a8\u00a9\7g\2\2\u00a9\24\3\2\2\2\u00aa\u00ab\7k\2\2\u00ab"+
		"\u00ac\7p\2\2\u00ac\u00ad\7u\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7c\2\2"+
		"\u00af\u00b0\7n\2\2\u00b0\u00b1\7n\2\2\u00b1\26\3\2\2\2\u00b2\u00b3\7"+
		"w\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7"+
		"\7u\2\2\u00b7\u00b8\7v\2\2\u00b8\u00b9\7c\2\2\u00b9\u00ba\7n\2\2\u00ba"+
		"\u00bb\7n\2\2\u00bb\30\3\2\2\2\u00bc\u00bd\7G\2\2\u00bd\u00be\7G\2\2\u00be"+
		"\32\3\2\2\2\u00bf\u00c0\7Q\2\2\u00c0\u00c1\7U\2\2\u00c1\34\3\2\2\2\u00c2"+
		"\u00c3\7n\2\2\u00c3\u00c4\7q\2\2\u00c4\u00c5\7c\2\2\u00c5\u00c6\7f\2\2"+
		"\u00c6\36\3\2\2\2\u00c7\u00c8\7u\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7"+
		"v\2\2\u00ca \3\2\2\2\u00cb\u00cc\7k\2\2\u00cc\u00cd\7p\2\2\u00cd\u00ce"+
		"\7x\2\2\u00ce\u00cf\7q\2\2\u00cf\u00d0\7m\2\2\u00d0\u00d1\7g\2\2\u00d1"+
		"\"\3\2\2\2\u00d2\u00d3\7u\2\2\u00d3\u00d4\7v\2\2\u00d4\u00d5\7q\2\2\u00d5"+
		"\u00d6\7r\2\2\u00d6$\3\2\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7p\2\2\u00d9"+
		"\u00da\7h\2\2\u00da\u00db\7q\2\2\u00db&\3\2\2\2\u00dc\u00dd\7m\2\2\u00dd"+
		"\u00de\7k\2\2\u00de\u00df\7n\2\2\u00df\u00e0\7n\2\2\u00e0(\3\2\2\2\u00e1"+
		"\u00e2\7e\2\2\u00e2\u00e3\7c\2\2\u00e3\u00e4\7v\2\2\u00e4*\3\2\2\2\u00e5"+
		"\u00e6\7i\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7g\2\2\u00e8\u00e9\7r\2\2"+
		"\u00e9,\3\2\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed\7u\2"+
		"\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7c\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1"+
		"\7v\2\2\u00f1.\3\2\2\2\u00f2\u00f3\7e\2\2\u00f3\u00f4\7n\2\2\u00f4\u00f5"+
		"\7g\2\2\u00f5\u00f6\7c\2\2\u00f6\u00f7\7p\2\2\u00f7\60\3\2\2\2\u00f8\u00f9"+
		"\7u\2\2\u00f9\u00fa\7n\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7g\2\2\u00fc"+
		"\u00fd\7r\2\2\u00fd\62\3\2\2\2\u00fe\u00ff\7u\2\2\u00ff\u0100\7c\2\2\u0100"+
		"\u0101\7x\2\2\u0101\u0102\7g\2\2\u0102\64\3\2\2\2\u0103\u0104\7g\2\2\u0104"+
		"\u0105\7z\2\2\u0105\u0106\7k\2\2\u0106\u0107\7v\2\2\u0107\66\3\2\2\2\u0108"+
		"\u0109\7f\2\2\u0109\u010a\7q\2\2\u010a\u010b\7y\2\2\u010b\u010c\7p\2\2"+
		"\u010c\u010d\7n\2\2\u010d\u010e\7q\2\2\u010e\u010f\7c\2\2\u010f\u0110"+
		"\7f\2\2\u01108\3\2\2\2\u0111\u0112\7u\2\2\u0112\u0113\7j\2\2\u0113\u0114"+
		"\7q\2\2\u0114\u0115\7y\2\2\u0115\u0116\7U\2\2\u0116\u0117\7U\2\2\u0117"+
		"\u0118\7J\2\2\u0118:\3\2\2\2\u0119\u011a\7r\2\2\u011a\u011b\7t\2\2\u011b"+
		"\u011c\7q\2\2\u011c\u011d\7o\2\2\u011d\u011e\7r\2\2\u011e\u011f\7v\2\2"+
		"\u011f<\3\2\2\2\u0120\u0121\7,\2\2\u0121>\3\2\2\2\u0122\u0123\7o\2\2\u0123"+
		"\u0124\7g\2\2\u0124\u0125\7o\2\2\u0125\u0126\7d\2\2\u0126\u0127\7g\2\2"+
		"\u0127\u0128\7t\2\2\u0128@\3\2\2\2\u0129\u012a\7e\2\2\u012a\u012b\7n\2"+
		"\2\u012b\u012c\7k\2\2\u012c\u012d\7g\2\2\u012d\u012e\7p\2\2\u012e\u012f"+
		"\7v\2\2\u012fB\3\2\2\2\u0130\u0131\7O\2\2\u0131\u0132\7g\2\2\u0132\u0133"+
		"\7o\2\2\u0133\u0134\7d\2\2\u0134\u0135\7g\2\2\u0135\u0136\7t\2\2\u0136"+
		"\u0137\3\2\2\2\u0137\u0138\5=\37\2\u0138D\3\2\2\2\u0139\u013a\7O\2\2\u013a"+
		"\u013b\7g\2\2\u013b\u013c\7o\2\2\u013c\u013d\7d\2\2\u013d\u013e\7g\2\2"+
		"\u013e\u013f\7t\2\2\u013f\u0140\3\2\2\2\u0140\u0141\5[.\2\u0141F\3\2\2"+
		"\2\u0142\u0143\7E\2\2\u0143\u0144\7n\2\2\u0144\u0145\7k\2\2\u0145\u0146"+
		"\7g\2\2\u0146\u0147\7p\2\2\u0147\u0148\7v\2\2\u0148\u0149\3\2\2\2\u0149"+
		"\u014a\5=\37\2\u014aH\3\2\2\2\u014b\u014c\7E\2\2\u014c\u014d\7n\2\2\u014d"+
		"\u014e\7k\2\2\u014e\u014f\7g\2\2\u014f\u0150\7p\2\2\u0150\u0151\7v\2\2"+
		"\u0151\u0152\3\2\2\2\u0152\u0153\5[.\2\u0153J\3\2\2\2\u0154\u0155\7o\2"+
		"\2\u0155\u0156\7g\2\2\u0156\u0157\7o\2\2\u0157\u0158\7d\2\2\u0158\u0159"+
		"\7g\2\2\u0159\u015a\7t\2\2\u015a\u015b\7u\2\2\u015b\u015c\7Q\2\2\u015c"+
		"\u015d\7p\2\2\u015d\u015e\7n\2\2\u015e\u015f\7{\2\2\u015fL\3\2\2\2\u0160"+
		"\u0161\7o\2\2\u0161\u0162\7g\2\2\u0162\u0163\7o\2\2\u0163\u0164\7d\2\2"+
		"\u0164\u0165\7g\2\2\u0165\u0166\7t\2\2\u0166\u0167\7u\2\2\u0167N\3\2\2"+
		"\2\u0168\u0169\7e\2\2\u0169\u016a\7n\2\2\u016a\u016b\7k\2\2\u016b\u016c"+
		"\7g\2\2\u016c\u016d\7p\2\2\u016d\u016e\7v\2\2\u016e\u016f\7u\2\2\u016f"+
		"P\3\2\2\2\u0170\u0171\7?\2\2\u0171R\3\2\2\2\u0172\u0175\5U+\2\u0173\u0175"+
		"\5W,\2\u0174\u0172\3\2\2\2\u0174\u0173\3\2\2\2\u0175T\3\2\2\2\u0176\u0177"+
		"\7v\2\2\u0177\u0178\7t\2\2\u0178\u0179\7w\2\2\u0179\u017a\7g\2\2\u017a"+
		"V\3\2\2\2\u017b\u017c\7h\2\2\u017c\u017d\7c\2\2\u017d\u017e\7n\2\2\u017e"+
		"\u017f\7u\2\2\u017f\u0180\7g\2\2\u0180X\3\2\2\2\u0181\u0183\t\2\2\2\u0182"+
		"\u0181\3\2\2\2\u0183\u0187\3\2\2\2\u0184\u0186\t\3\2\2\u0185\u0184\3\2"+
		"\2\2\u0186\u0189\3\2\2\2\u0187\u0185\3\2\2\2\u0187\u0188\3\2\2\2\u0188"+
		"Z\3\2\2\2\u0189\u0187\3\2\2\2\u018a\u018c\t\4\2\2\u018b\u018a\3\2\2\2"+
		"\u018c\u018d\3\2\2\2\u018d\u018b\3\2\2\2\u018d\u018e\3\2\2\2\u018e\\\3"+
		"\2\2\2\u018f\u0191\t\5\2\2\u0190\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192"+
		"\u0190\3\2\2\2\u0192\u0193\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0195\b/"+
		"\2\2\u0195^\3\2\2\2\u0196\u0198\t\6\2\2\u0197\u0196\3\2\2\2\u0198`\3\2"+
		"\2\2\u0199\u019a\5_\60\2\u019ab\3\2\2\2\u019b\u019c\5e\63\2\u019c\u019d"+
		"\7.\2\2\u019d\u019e\5e\63\2\u019ed\3\2\2\2\u019f\u01a0\5g\64\2\u01a0\u01a1"+
		"\7\60\2\2\u01a1\u01a2\5g\64\2\u01a2\u01a3\7\60\2\2\u01a3\u01a4\5g\64\2"+
		"\u01a4\u01a5\7\60\2\2\u01a5\u01a6\5g\64\2\u01a6f\3\2\2\2\u01a7\u01a8\5"+
		"i\65\2\u01a8\u01a9\5i\65\2\u01a9\u01aa\5i\65\2\u01aa\u01b0\3\2\2\2\u01ab"+
		"\u01ac\5i\65\2\u01ac\u01ad\5i\65\2\u01ad\u01b0\3\2\2\2\u01ae\u01b0\5i"+
		"\65\2\u01af\u01a7\3\2\2\2\u01af\u01ab\3\2\2\2\u01af\u01ae\3\2\2\2\u01b0"+
		"h\3\2\2\2\u01b1\u01b2\4\62;\2\u01b2j\3\2\2\2\u01b3\u01b4\7\61\2\2\u01b4"+
		"\u01b5\7\61\2\2\u01b5\u01b9\3\2\2\2\u01b6\u01b8\n\7\2\2\u01b7\u01b6\3"+
		"\2\2\2\u01b8\u01bb\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01ba"+
		"\u01bc\3\2\2\2\u01bb\u01b9\3\2\2\2\u01bc\u01bd\b\66\2\2\u01bdl\3\2\2\2"+
		"\f\2\u0174\u0182\u0185\u0187\u018d\u0192\u0197\u01af\u01b9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}