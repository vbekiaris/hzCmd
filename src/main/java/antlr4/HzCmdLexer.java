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
		INVOKE=16, INFO=17, KILL=18, CAT=19, GREP=20, RESTART=21, CLEAN=22, SLEEP=23, 
		SAVE=24, EXIT=25, SHOWSSH=26, PROMPT=27, ALL=28, MEMBER=29, CLIENT=30, 
		MEMBER_ALL=31, MEMBER_VAR=32, CLIENT_ALL=33, CLIENT_VAR=34, MEMBERS_ONLY=35, 
		MEMBERS=36, CLIENTS=37, ASSIGN=38, BOOL=39, TRUE=40, FALSE=41, VAR=42, 
		NUMBER=43, WHITESPACE=44, STRING=45, IP_PAIR=46, IP_STR=47, COMMENT=48;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", "CLUSTER", 
		"REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", "INVOKE", 
		"INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", "SAVE", "EXIT", 
		"SHOWSSH", "PROMPT", "ALL", "MEMBER", "CLIENT", "MEMBER_ALL", "MEMBER_VAR", 
		"CLIENT_ALL", "CLIENT_VAR", "MEMBERS_ONLY", "MEMBERS", "CLIENTS", "ASSIGN", 
		"BOOL", "TRUE", "FALSE", "VAR", "NUMBER", "WHITESPACE", "CHAR", "STRING", 
		"IP_PAIR", "IP_STR", "Octet", "Digit", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'homeIp'", "'user'", "'version'", "'add'", "'memberBox'", "'boxes'", 
		"'ip'", "'cluster'", "'replicate'", "'install'", "'uninstall'", "'EE'", 
		"'OS'", "'load'", "'set'", "'invoke'", "'info'", "'kill'", "'cat'", "'grep'", 
		"'restart'", "'clean'", "'sleep'", "'save'", "'exit'", "'showSSH'", "'prompt'", 
		"'*'", "'member'", "'client'", null, null, null, null, "'membersOnly'", 
		"'members'", "'clients'", "'='", null, "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "HOMEIP", "USER", "VERSION", "ADD", "MEMBER_BOX", "BOXES", "IP", 
		"CLUSTER", "REPLICATE", "INSTALL", "UNINSTALL", "EE", "OS", "LOAD", "SET", 
		"INVOKE", "INFO", "KILL", "CAT", "GREP", "RESTART", "CLEAN", "SLEEP", 
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\62\u01b2\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64"+
		"\t\64\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3"+
		"\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3"+
		"\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3"+
		" \3 \3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3%\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3"+
		"(\3(\5(\u0163\n(\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3+\5+\u0171\n+\3+\7"+
		"+\u0174\n+\f+\16+\u0177\13+\3,\6,\u017a\n,\r,\16,\u017b\3-\6-\u017f\n"+
		"-\r-\16-\u0180\3-\3-\3.\5.\u0186\n.\3/\3/\6/\u018a\n/\r/\16/\u018b\3/"+
		"\3/\3\60\3\60\3\60\3\60\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\61\3\62\3"+
		"\62\3\62\3\62\3\62\3\62\3\62\3\62\5\62\u01a4\n\62\3\63\3\63\3\64\3\64"+
		"\3\64\3\64\7\64\u01ac\n\64\f\64\16\64\u01af\13\64\3\64\3\64\2\2\65\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!"+
		"A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[\2]/_\60a\61c\2e\2g\62\3\2\b\4\2C\\c|\6\2"+
		"\62;C\\aac|\3\2\62;\5\2\13\f\16\17\"\"\13\2\"\"%%,,/<??C_aac|~~\4\2\f"+
		"\f\17\17\u01b6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2"+
		"\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\2"+
		"9\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3"+
		"\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2"+
		"\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2]\3\2\2\2\2_\3\2\2\2\2"+
		"a\3\2\2\2\2g\3\2\2\2\3i\3\2\2\2\5p\3\2\2\2\7u\3\2\2\2\t}\3\2\2\2\13\u0081"+
		"\3\2\2\2\r\u008b\3\2\2\2\17\u0091\3\2\2\2\21\u0094\3\2\2\2\23\u009c\3"+
		"\2\2\2\25\u00a6\3\2\2\2\27\u00ae\3\2\2\2\31\u00b8\3\2\2\2\33\u00bb\3\2"+
		"\2\2\35\u00be\3\2\2\2\37\u00c3\3\2\2\2!\u00c7\3\2\2\2#\u00ce\3\2\2\2%"+
		"\u00d3\3\2\2\2\'\u00d8\3\2\2\2)\u00dc\3\2\2\2+\u00e1\3\2\2\2-\u00e9\3"+
		"\2\2\2/\u00ef\3\2\2\2\61\u00f5\3\2\2\2\63\u00fa\3\2\2\2\65\u00ff\3\2\2"+
		"\2\67\u0107\3\2\2\29\u010e\3\2\2\2;\u0110\3\2\2\2=\u0117\3\2\2\2?\u011e"+
		"\3\2\2\2A\u0127\3\2\2\2C\u0130\3\2\2\2E\u0139\3\2\2\2G\u0142\3\2\2\2I"+
		"\u014e\3\2\2\2K\u0156\3\2\2\2M\u015e\3\2\2\2O\u0162\3\2\2\2Q\u0164\3\2"+
		"\2\2S\u0169\3\2\2\2U\u0170\3\2\2\2W\u0179\3\2\2\2Y\u017e\3\2\2\2[\u0185"+
		"\3\2\2\2]\u0187\3\2\2\2_\u018f\3\2\2\2a\u0193\3\2\2\2c\u01a3\3\2\2\2e"+
		"\u01a5\3\2\2\2g\u01a7\3\2\2\2ij\7j\2\2jk\7q\2\2kl\7o\2\2lm\7g\2\2mn\7"+
		"K\2\2no\7r\2\2o\4\3\2\2\2pq\7w\2\2qr\7u\2\2rs\7g\2\2st\7t\2\2t\6\3\2\2"+
		"\2uv\7x\2\2vw\7g\2\2wx\7t\2\2xy\7u\2\2yz\7k\2\2z{\7q\2\2{|\7p\2\2|\b\3"+
		"\2\2\2}~\7c\2\2~\177\7f\2\2\177\u0080\7f\2\2\u0080\n\3\2\2\2\u0081\u0082"+
		"\7o\2\2\u0082\u0083\7g\2\2\u0083\u0084\7o\2\2\u0084\u0085\7d\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7t\2\2\u0087\u0088\7D\2\2\u0088\u0089\7q\2\2"+
		"\u0089\u008a\7z\2\2\u008a\f\3\2\2\2\u008b\u008c\7d\2\2\u008c\u008d\7q"+
		"\2\2\u008d\u008e\7z\2\2\u008e\u008f\7g\2\2\u008f\u0090\7u\2\2\u0090\16"+
		"\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7r\2\2\u0093\20\3\2\2\2\u0094"+
		"\u0095\7e\2\2\u0095\u0096\7n\2\2\u0096\u0097\7w\2\2\u0097\u0098\7u\2\2"+
		"\u0098\u0099\7v\2\2\u0099\u009a\7g\2\2\u009a\u009b\7t\2\2\u009b\22\3\2"+
		"\2\2\u009c\u009d\7t\2\2\u009d\u009e\7g\2\2\u009e\u009f\7r\2\2\u009f\u00a0"+
		"\7n\2\2\u00a0\u00a1\7k\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7c\2\2\u00a3"+
		"\u00a4\7v\2\2\u00a4\u00a5\7g\2\2\u00a5\24\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7"+
		"\u00a8\7p\2\2\u00a8\u00a9\7u\2\2\u00a9\u00aa\7v\2\2\u00aa\u00ab\7c\2\2"+
		"\u00ab\u00ac\7n\2\2\u00ac\u00ad\7n\2\2\u00ad\26\3\2\2\2\u00ae\u00af\7"+
		"w\2\2\u00af\u00b0\7p\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7p\2\2\u00b2\u00b3"+
		"\7u\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7c\2\2\u00b5\u00b6\7n\2\2\u00b6"+
		"\u00b7\7n\2\2\u00b7\30\3\2\2\2\u00b8\u00b9\7G\2\2\u00b9\u00ba\7G\2\2\u00ba"+
		"\32\3\2\2\2\u00bb\u00bc\7Q\2\2\u00bc\u00bd\7U\2\2\u00bd\34\3\2\2\2\u00be"+
		"\u00bf\7n\2\2\u00bf\u00c0\7q\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7f\2\2"+
		"\u00c2\36\3\2\2\2\u00c3\u00c4\7u\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7"+
		"v\2\2\u00c6 \3\2\2\2\u00c7\u00c8\7k\2\2\u00c8\u00c9\7p\2\2\u00c9\u00ca"+
		"\7x\2\2\u00ca\u00cb\7q\2\2\u00cb\u00cc\7m\2\2\u00cc\u00cd\7g\2\2\u00cd"+
		"\"\3\2\2\2\u00ce\u00cf\7k\2\2\u00cf\u00d0\7p\2\2\u00d0\u00d1\7h\2\2\u00d1"+
		"\u00d2\7q\2\2\u00d2$\3\2\2\2\u00d3\u00d4\7m\2\2\u00d4\u00d5\7k\2\2\u00d5"+
		"\u00d6\7n\2\2\u00d6\u00d7\7n\2\2\u00d7&\3\2\2\2\u00d8\u00d9\7e\2\2\u00d9"+
		"\u00da\7c\2\2\u00da\u00db\7v\2\2\u00db(\3\2\2\2\u00dc\u00dd\7i\2\2\u00dd"+
		"\u00de\7t\2\2\u00de\u00df\7g\2\2\u00df\u00e0\7r\2\2\u00e0*\3\2\2\2\u00e1"+
		"\u00e2\7t\2\2\u00e2\u00e3\7g\2\2\u00e3\u00e4\7u\2\2\u00e4\u00e5\7v\2\2"+
		"\u00e5\u00e6\7c\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7v\2\2\u00e8,\3\2\2"+
		"\2\u00e9\u00ea\7e\2\2\u00ea\u00eb\7n\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed"+
		"\7c\2\2\u00ed\u00ee\7p\2\2\u00ee.\3\2\2\2\u00ef\u00f0\7u\2\2\u00f0\u00f1"+
		"\7n\2\2\u00f1\u00f2\7g\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7r\2\2\u00f4"+
		"\60\3\2\2\2\u00f5\u00f6\7u\2\2\u00f6\u00f7\7c\2\2\u00f7\u00f8\7x\2\2\u00f8"+
		"\u00f9\7g\2\2\u00f9\62\3\2\2\2\u00fa\u00fb\7g\2\2\u00fb\u00fc\7z\2\2\u00fc"+
		"\u00fd\7k\2\2\u00fd\u00fe\7v\2\2\u00fe\64\3\2\2\2\u00ff\u0100\7u\2\2\u0100"+
		"\u0101\7j\2\2\u0101\u0102\7q\2\2\u0102\u0103\7y\2\2\u0103\u0104\7U\2\2"+
		"\u0104\u0105\7U\2\2\u0105\u0106\7J\2\2\u0106\66\3\2\2\2\u0107\u0108\7"+
		"r\2\2\u0108\u0109\7t\2\2\u0109\u010a\7q\2\2\u010a\u010b\7o\2\2\u010b\u010c"+
		"\7r\2\2\u010c\u010d\7v\2\2\u010d8\3\2\2\2\u010e\u010f\7,\2\2\u010f:\3"+
		"\2\2\2\u0110\u0111\7o\2\2\u0111\u0112\7g\2\2\u0112\u0113\7o\2\2\u0113"+
		"\u0114\7d\2\2\u0114\u0115\7g\2\2\u0115\u0116\7t\2\2\u0116<\3\2\2\2\u0117"+
		"\u0118\7e\2\2\u0118\u0119\7n\2\2\u0119\u011a\7k\2\2\u011a\u011b\7g\2\2"+
		"\u011b\u011c\7p\2\2\u011c\u011d\7v\2\2\u011d>\3\2\2\2\u011e\u011f\7O\2"+
		"\2\u011f\u0120\7g\2\2\u0120\u0121\7o\2\2\u0121\u0122\7d\2\2\u0122\u0123"+
		"\7g\2\2\u0123\u0124\7t\2\2\u0124\u0125\3\2\2\2\u0125\u0126\59\35\2\u0126"+
		"@\3\2\2\2\u0127\u0128\7O\2\2\u0128\u0129\7g\2\2\u0129\u012a\7o\2\2\u012a"+
		"\u012b\7d\2\2\u012b\u012c\7g\2\2\u012c\u012d\7t\2\2\u012d\u012e\3\2\2"+
		"\2\u012e\u012f\5W,\2\u012fB\3\2\2\2\u0130\u0131\7E\2\2\u0131\u0132\7n"+
		"\2\2\u0132\u0133\7k\2\2\u0133\u0134\7g\2\2\u0134\u0135\7p\2\2\u0135\u0136"+
		"\7v\2\2\u0136\u0137\3\2\2\2\u0137\u0138\59\35\2\u0138D\3\2\2\2\u0139\u013a"+
		"\7E\2\2\u013a\u013b\7n\2\2\u013b\u013c\7k\2\2\u013c\u013d\7g\2\2\u013d"+
		"\u013e\7p\2\2\u013e\u013f\7v\2\2\u013f\u0140\3\2\2\2\u0140\u0141\5W,\2"+
		"\u0141F\3\2\2\2\u0142\u0143\7o\2\2\u0143\u0144\7g\2\2\u0144\u0145\7o\2"+
		"\2\u0145\u0146\7d\2\2\u0146\u0147\7g\2\2\u0147\u0148\7t\2\2\u0148\u0149"+
		"\7u\2\2\u0149\u014a\7Q\2\2\u014a\u014b\7p\2\2\u014b\u014c\7n\2\2\u014c"+
		"\u014d\7{\2\2\u014dH\3\2\2\2\u014e\u014f\7o\2\2\u014f\u0150\7g\2\2\u0150"+
		"\u0151\7o\2\2\u0151\u0152\7d\2\2\u0152\u0153\7g\2\2\u0153\u0154\7t\2\2"+
		"\u0154\u0155\7u\2\2\u0155J\3\2\2\2\u0156\u0157\7e\2\2\u0157\u0158\7n\2"+
		"\2\u0158\u0159\7k\2\2\u0159\u015a\7g\2\2\u015a\u015b\7p\2\2\u015b\u015c"+
		"\7v\2\2\u015c\u015d\7u\2\2\u015dL\3\2\2\2\u015e\u015f\7?\2\2\u015fN\3"+
		"\2\2\2\u0160\u0163\5Q)\2\u0161\u0163\5S*\2\u0162\u0160\3\2\2\2\u0162\u0161"+
		"\3\2\2\2\u0163P\3\2\2\2\u0164\u0165\7v\2\2\u0165\u0166\7t\2\2\u0166\u0167"+
		"\7w\2\2\u0167\u0168\7g\2\2\u0168R\3\2\2\2\u0169\u016a\7h\2\2\u016a\u016b"+
		"\7c\2\2\u016b\u016c\7n\2\2\u016c\u016d\7u\2\2\u016d\u016e\7g\2\2\u016e"+
		"T\3\2\2\2\u016f\u0171\t\2\2\2\u0170\u016f\3\2\2\2\u0171\u0175\3\2\2\2"+
		"\u0172\u0174\t\3\2\2\u0173\u0172\3\2\2\2\u0174\u0177\3\2\2\2\u0175\u0173"+
		"\3\2\2\2\u0175\u0176\3\2\2\2\u0176V\3\2\2\2\u0177\u0175\3\2\2\2\u0178"+
		"\u017a\t\4\2\2\u0179\u0178\3\2\2\2\u017a\u017b\3\2\2\2\u017b\u0179\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017cX\3\2\2\2\u017d\u017f\t\5\2\2\u017e\u017d"+
		"\3\2\2\2\u017f\u0180\3\2\2\2\u0180\u017e\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0182\3\2\2\2\u0182\u0183\b-\2\2\u0183Z\3\2\2\2\u0184\u0186\t\6\2\2\u0185"+
		"\u0184\3\2\2\2\u0186\\\3\2\2\2\u0187\u0189\7$\2\2\u0188\u018a\5[.\2\u0189"+
		"\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\u0189\3\2\2\2\u018b\u018c\3\2"+
		"\2\2\u018c\u018d\3\2\2\2\u018d\u018e\7$\2\2\u018e^\3\2\2\2\u018f\u0190"+
		"\5a\61\2\u0190\u0191\7.\2\2\u0191\u0192\5a\61\2\u0192`\3\2\2\2\u0193\u0194"+
		"\5c\62\2\u0194\u0195\7\60\2\2\u0195\u0196\5c\62\2\u0196\u0197\7\60\2\2"+
		"\u0197\u0198\5c\62\2\u0198\u0199\7\60\2\2\u0199\u019a\5c\62\2\u019ab\3"+
		"\2\2\2\u019b\u019c\5e\63\2\u019c\u019d\5e\63\2\u019d\u019e\5e\63\2\u019e"+
		"\u01a4\3\2\2\2\u019f\u01a0\5e\63\2\u01a0\u01a1\5e\63\2\u01a1\u01a4\3\2"+
		"\2\2\u01a2\u01a4\5e\63\2\u01a3\u019b\3\2\2\2\u01a3\u019f\3\2\2\2\u01a3"+
		"\u01a2\3\2\2\2\u01a4d\3\2\2\2\u01a5\u01a6\4\62;\2\u01a6f\3\2\2\2\u01a7"+
		"\u01a8\7\61\2\2\u01a8\u01a9\7\61\2\2\u01a9\u01ad\3\2\2\2\u01aa\u01ac\n"+
		"\7\2\2\u01ab\u01aa\3\2\2\2\u01ac\u01af\3\2\2\2\u01ad\u01ab\3\2\2\2\u01ad"+
		"\u01ae\3\2\2\2\u01ae\u01b0\3\2\2\2\u01af\u01ad\3\2\2\2\u01b0\u01b1\b\64"+
		"\2\2\u01b1h\3\2\2\2\r\2\u0162\u0170\u0173\u0175\u017b\u0180\u0185\u018b"+
		"\u01a3\u01ad\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}