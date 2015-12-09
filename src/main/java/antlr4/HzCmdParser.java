// Generated from HzCmd.g4 by ANTLR 4.5
package antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HzCmdParser extends Parser {
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
	public static final int
		RULE_script = 0, RULE_statement = 1;
	public static final String[] ruleNames = {
		"script", "statement"
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

	@Override
	public String getGrammarFileName() { return "HzCmd.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public HzCmdParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ScriptContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ScriptContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_script; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HzCmdListener ) ((HzCmdListener)listener).enterScript(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HzCmdListener ) ((HzCmdListener)listener).exitScript(this);
		}
	}

	public final ScriptContext script() throws RecognitionException {
		ScriptContext _localctx = new ScriptContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_script);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(4);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public List<TerminalNode> VAR() { return getTokens(HzCmdParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(HzCmdParser.VAR, i);
		}
		public TerminalNode ASSIGN() { return getToken(HzCmdParser.ASSIGN, 0); }
		public List<TerminalNode> STRING() { return getTokens(HzCmdParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HzCmdParser.STRING, i);
		}
		public TerminalNode USER() { return getToken(HzCmdParser.USER, 0); }
		public TerminalNode VERSION() { return getToken(HzCmdParser.VERSION, 0); }
		public TerminalNode BOXES() { return getToken(HzCmdParser.BOXES, 0); }
		public TerminalNode ADD() { return getToken(HzCmdParser.ADD, 0); }
		public TerminalNode CLUSTER() { return getToken(HzCmdParser.CLUSTER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(HzCmdParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(HzCmdParser.NUMBER, i);
		}
		public TerminalNode REPLICATE() { return getToken(HzCmdParser.REPLICATE, 0); }
		public TerminalNode INSTALL() { return getToken(HzCmdParser.INSTALL, 0); }
		public List<TerminalNode> ALL() { return getTokens(HzCmdParser.ALL); }
		public TerminalNode ALL(int i) {
			return getToken(HzCmdParser.ALL, i);
		}
		public TerminalNode OS() { return getToken(HzCmdParser.OS, 0); }
		public TerminalNode EE() { return getToken(HzCmdParser.EE, 0); }
		public TerminalNode UNINSTALL() { return getToken(HzCmdParser.UNINSTALL, 0); }
		public TerminalNode MEMBER() { return getToken(HzCmdParser.MEMBER, 0); }
		public TerminalNode CLIENT() { return getToken(HzCmdParser.CLIENT, 0); }
		public TerminalNode LOAD() { return getToken(HzCmdParser.LOAD, 0); }
		public TerminalNode SET() { return getToken(HzCmdParser.SET, 0); }
		public TerminalNode INVOKE() { return getToken(HzCmdParser.INVOKE, 0); }
		public List<TerminalNode> MEMBER_ALL() { return getTokens(HzCmdParser.MEMBER_ALL); }
		public TerminalNode MEMBER_ALL(int i) {
			return getToken(HzCmdParser.MEMBER_ALL, i);
		}
		public List<TerminalNode> MEMBER_VAR() { return getTokens(HzCmdParser.MEMBER_VAR); }
		public TerminalNode MEMBER_VAR(int i) {
			return getToken(HzCmdParser.MEMBER_VAR, i);
		}
		public List<TerminalNode> CLIENT_ALL() { return getTokens(HzCmdParser.CLIENT_ALL); }
		public TerminalNode CLIENT_ALL(int i) {
			return getToken(HzCmdParser.CLIENT_ALL, i);
		}
		public List<TerminalNode> CLIENT_VAR() { return getTokens(HzCmdParser.CLIENT_VAR); }
		public TerminalNode CLIENT_VAR(int i) {
			return getToken(HzCmdParser.CLIENT_VAR, i);
		}
		public TerminalNode INFO() { return getToken(HzCmdParser.INFO, 0); }
		public TerminalNode KILL() { return getToken(HzCmdParser.KILL, 0); }
		public TerminalNode CAT() { return getToken(HzCmdParser.CAT, 0); }
		public TerminalNode RESTART() { return getToken(HzCmdParser.RESTART, 0); }
		public TerminalNode CLEAN() { return getToken(HzCmdParser.CLEAN, 0); }
		public TerminalNode SLEEP() { return getToken(HzCmdParser.SLEEP, 0); }
		public TerminalNode MEMBERS_ONLY() { return getToken(HzCmdParser.MEMBERS_ONLY, 0); }
		public TerminalNode SAVE() { return getToken(HzCmdParser.SAVE, 0); }
		public TerminalNode EXIT() { return getToken(HzCmdParser.EXIT, 0); }
		public TerminalNode SHOWSSH() { return getToken(HzCmdParser.SHOWSSH, 0); }
		public TerminalNode BOOL() { return getToken(HzCmdParser.BOOL, 0); }
		public TerminalNode PROMPT() { return getToken(HzCmdParser.PROMPT, 0); }
		public TerminalNode HOMEIP() { return getToken(HzCmdParser.HOMEIP, 0); }
		public TerminalNode COMMENT() { return getToken(HzCmdParser.COMMENT, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HzCmdListener ) ((HzCmdListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HzCmdListener ) ((HzCmdListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(101);
			switch (_input.LA(1)) {
			case VAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				match(VAR);
				setState(7);
				match(ASSIGN);
				setState(8);
				match(STRING);
				}
				break;
			case USER:
				enterOuterAlt(_localctx, 2);
				{
				setState(9);
				match(USER);
				setState(10);
				match(STRING);
				}
				break;
			case VERSION:
				enterOuterAlt(_localctx, 3);
				{
				setState(11);
				match(VERSION);
				setState(12);
				match(STRING);
				}
				break;
			case BOXES:
				enterOuterAlt(_localctx, 4);
				{
				setState(13);
				match(BOXES);
				setState(14);
				match(ADD);
				setState(15);
				match(STRING);
				setState(16);
				match(STRING);
				}
				break;
			case CLUSTER:
				enterOuterAlt(_localctx, 5);
				{
				setState(17);
				match(CLUSTER);
				setState(18);
				match(VAR);
				setState(19);
				match(NUMBER);
				setState(20);
				match(NUMBER);
				}
				break;
			case REPLICATE:
				enterOuterAlt(_localctx, 6);
				{
				setState(21);
				match(REPLICATE);
				setState(22);
				match(VAR);
				setState(23);
				match(VAR);
				setState(24);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(25);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INSTALL:
				enterOuterAlt(_localctx, 7);
				{
				setState(26);
				match(INSTALL);
				setState(27);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(28);
				_la = _input.LA(1);
				if ( !(_la==EE || _la==OS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(30); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(29);
					match(VAR);
					}
					}
					setState(32); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case UNINSTALL:
				enterOuterAlt(_localctx, 8);
				{
				setState(34);
				match(UNINSTALL);
				setState(35);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case ADD:
				enterOuterAlt(_localctx, 9);
				{
				setState(36);
				match(ADD);
				setState(37);
				_la = _input.LA(1);
				if ( !(_la==MEMBER || _la==CLIENT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(38);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(39);
				match(NUMBER);
				setState(40);
				match(VAR);
				setState(42); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(41);
					match(VAR);
					}
					}
					setState(44); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 10);
				{
				setState(46);
				match(LOAD);
				setState(47);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(48);
				match(VAR);
				setState(49);
				match(STRING);
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 11);
				{
				setState(50);
				match(SET);
				setState(51);
				match(VAR);
				setState(52);
				matchWildcard();
				setState(53);
				match(VAR);
				setState(54);
				match(ASSIGN);
				setState(55);
				match(STRING);
				}
				break;
			case INVOKE:
				enterOuterAlt(_localctx, 12);
				{
				setState(56);
				match(INVOKE);
				setState(57);
				match(NUMBER);
				setState(58);
				match(VAR);
				setState(59);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(60);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(61);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INFO:
				enterOuterAlt(_localctx, 13);
				{
				setState(62);
				match(INFO);
				setState(63);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					{
					setState(64);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
					}
					setState(69);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case KILL:
				enterOuterAlt(_localctx, 14);
				{
				setState(70);
				match(KILL);
				setState(71);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(72);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case CAT:
				enterOuterAlt(_localctx, 15);
				{
				setState(73);
				match(CAT);
				setState(74);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(75);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case RESTART:
				enterOuterAlt(_localctx, 16);
				{
				setState(76);
				match(RESTART);
				setState(77);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(78);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(79);
				match(VAR);
				setState(81); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(80);
					match(VAR);
					}
					}
					setState(83); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case CLEAN:
				enterOuterAlt(_localctx, 17);
				{
				setState(85);
				match(CLEAN);
				setState(86);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_VAR) | (1L << VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case SLEEP:
				enterOuterAlt(_localctx, 18);
				{
				setState(87);
				match(SLEEP);
				setState(88);
				match(NUMBER);
				}
				break;
			case MEMBERS_ONLY:
				enterOuterAlt(_localctx, 19);
				{
				setState(89);
				match(MEMBERS_ONLY);
				setState(90);
				match(NUMBER);
				}
				break;
			case SAVE:
				enterOuterAlt(_localctx, 20);
				{
				setState(91);
				match(SAVE);
				setState(92);
				match(STRING);
				}
				break;
			case EXIT:
				enterOuterAlt(_localctx, 21);
				{
				setState(93);
				match(EXIT);
				}
				break;
			case SHOWSSH:
				enterOuterAlt(_localctx, 22);
				{
				setState(94);
				match(SHOWSSH);
				setState(95);
				match(BOOL);
				}
				break;
			case PROMPT:
				enterOuterAlt(_localctx, 23);
				{
				setState(96);
				match(PROMPT);
				setState(97);
				match(BOOL);
				}
				break;
			case HOMEIP:
				enterOuterAlt(_localctx, 24);
				{
				setState(98);
				match(HOMEIP);
				setState(99);
				match(STRING);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 25);
				{
				setState(100);
				match(COMMENT);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\60j\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3!\n\3\r\3\16\3\"\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\6\3-\n\3\r\3\16\3.\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3D\n\3\f\3\16\3G\13\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3T\n\3\r\3\16\3U\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3h\n\3\3\3"+
		"\2\2\4\2\4\2\t\4\2**--\4\2\34\34**\3\2\r\16\3\2\35\36\4\2\34\34\37\"\3"+
		"\2\37\"\6\2\34\34  \"\"**\u0083\2\6\3\2\2\2\4g\3\2\2\2\6\7\5\4\3\2\7\3"+
		"\3\2\2\2\b\t\7*\2\2\t\n\7&\2\2\nh\7-\2\2\13\f\7\4\2\2\fh\7-\2\2\r\16\7"+
		"\5\2\2\16h\7-\2\2\17\20\7\7\2\2\20\21\7\6\2\2\21\22\7-\2\2\22h\7-\2\2"+
		"\23\24\7\t\2\2\24\25\7*\2\2\25\26\7+\2\2\26h\7+\2\2\27\30\7\n\2\2\30\31"+
		"\7*\2\2\31\32\7*\2\2\32\33\t\2\2\2\33h\t\2\2\2\34\35\7\13\2\2\35\36\t"+
		"\3\2\2\36 \t\4\2\2\37!\7*\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\"#\3\2"+
		"\2\2#h\3\2\2\2$%\7\f\2\2%h\t\3\2\2&\'\7\6\2\2\'(\t\5\2\2()\t\3\2\2)*\7"+
		"+\2\2*,\7*\2\2+-\7*\2\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/h\3\2"+
		"\2\2\60\61\7\17\2\2\61\62\t\3\2\2\62\63\7*\2\2\63h\7-\2\2\64\65\7\20\2"+
		"\2\65\66\7*\2\2\66\67\13\2\2\2\678\7*\2\289\7&\2\29h\7-\2\2:;\7\21\2\2"+
		";<\7+\2\2<=\7*\2\2=>\t\3\2\2>?\t\3\2\2?h\t\6\2\2@A\7\22\2\2AE\t\3\2\2"+
		"BD\t\7\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2\2\2Fh\3\2\2\2GE\3\2\2\2"+
		"HI\7\23\2\2IJ\t\3\2\2Jh\t\6\2\2KL\7\24\2\2LM\t\3\2\2Mh\t\6\2\2NO\7\25"+
		"\2\2OP\t\3\2\2PQ\t\6\2\2QS\7*\2\2RT\7*\2\2SR\3\2\2\2TU\3\2\2\2US\3\2\2"+
		"\2UV\3\2\2\2Vh\3\2\2\2WX\7\26\2\2Xh\t\b\2\2YZ\7\27\2\2Zh\7+\2\2[\\\7#"+
		"\2\2\\h\7+\2\2]^\7\30\2\2^h\7-\2\2_h\7\31\2\2`a\7\32\2\2ah\7\'\2\2bc\7"+
		"\33\2\2ch\7\'\2\2de\7\3\2\2eh\7-\2\2fh\7\60\2\2g\b\3\2\2\2g\13\3\2\2\2"+
		"g\r\3\2\2\2g\17\3\2\2\2g\23\3\2\2\2g\27\3\2\2\2g\34\3\2\2\2g$\3\2\2\2"+
		"g&\3\2\2\2g\60\3\2\2\2g\64\3\2\2\2g:\3\2\2\2g@\3\2\2\2gH\3\2\2\2gK\3\2"+
		"\2\2gN\3\2\2\2gW\3\2\2\2gY\3\2\2\2g[\3\2\2\2g]\3\2\2\2g_\3\2\2\2g`\3\2"+
		"\2\2gb\3\2\2\2gd\3\2\2\2gf\3\2\2\2h\5\3\2\2\2\7\".EUg";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}