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
		HOMEIP=1, USER=2, VERSION=3, ADD=4, MEMBER_BOX=5, BOXES=6, IP=7, CLUSTER=8, 
		REPLICATE=9, INSTALL=10, UNINSTALL=11, EE=12, OS=13, LOAD=14, SET=15, 
		INVOKE=16, INFO=17, KILL=18, CAT=19, GREP=20, RESTART=21, CLEAN=22, SLEEP=23, 
		SAVE=24, EXIT=25, SHOWSSH=26, PROMPT=27, ALL=28, MEMBER=29, CLIENT=30, 
		MEMBER_ALL=31, MEMBER_VAR=32, CLIENT_ALL=33, CLIENT_VAR=34, MEMBERS_ONLY=35, 
		MEMBERS=36, CLIENTS=37, ASSIGN=38, BOOL=39, TRUE=40, FALSE=41, VAR=42, 
		NUMBER=43, WHITESPACE=44, STRING=45, IP_PAIR=46, IP_STR=47, COMMENT=48;
	public static final int
		RULE_script = 0, RULE_statement = 1;
	public static final String[] ruleNames = {
		"script", "statement"
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
		public TerminalNode BOXES() { return getToken(HzCmdParser.BOXES, 0); }
		public TerminalNode ADD() { return getToken(HzCmdParser.ADD, 0); }
		public TerminalNode CLUSTER() { return getToken(HzCmdParser.CLUSTER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(HzCmdParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(HzCmdParser.NUMBER, i);
		}
		public TerminalNode INSTALL() { return getToken(HzCmdParser.INSTALL, 0); }
		public List<TerminalNode> ALL() { return getTokens(HzCmdParser.ALL); }
		public TerminalNode ALL(int i) {
			return getToken(HzCmdParser.ALL, i);
		}
		public TerminalNode OS() { return getToken(HzCmdParser.OS, 0); }
		public TerminalNode EE() { return getToken(HzCmdParser.EE, 0); }
		public TerminalNode UNINSTALL() { return getToken(HzCmdParser.UNINSTALL, 0); }
		public TerminalNode MEMBER_BOX() { return getToken(HzCmdParser.MEMBER_BOX, 0); }
		public TerminalNode MEMBER() { return getToken(HzCmdParser.MEMBER, 0); }
		public TerminalNode CLIENT() { return getToken(HzCmdParser.CLIENT, 0); }
		public TerminalNode LOAD() { return getToken(HzCmdParser.LOAD, 0); }
		public TerminalNode REPLICATE() { return getToken(HzCmdParser.REPLICATE, 0); }
		public TerminalNode SET() { return getToken(HzCmdParser.SET, 0); }
		public TerminalNode INVOKE() { return getToken(HzCmdParser.INVOKE, 0); }
		public TerminalNode MEMBER_ALL() { return getToken(HzCmdParser.MEMBER_ALL, 0); }
		public TerminalNode MEMBER_VAR() { return getToken(HzCmdParser.MEMBER_VAR, 0); }
		public TerminalNode CLIENT_ALL() { return getToken(HzCmdParser.CLIENT_ALL, 0); }
		public TerminalNode CLIENT_VAR() { return getToken(HzCmdParser.CLIENT_VAR, 0); }
		public TerminalNode INFO() { return getToken(HzCmdParser.INFO, 0); }
		public TerminalNode KILL() { return getToken(HzCmdParser.KILL, 0); }
		public TerminalNode CAT() { return getToken(HzCmdParser.CAT, 0); }
		public TerminalNode GREP() { return getToken(HzCmdParser.GREP, 0); }
		public TerminalNode CLEAN() { return getToken(HzCmdParser.CLEAN, 0); }
		public TerminalNode RESTART() { return getToken(HzCmdParser.RESTART, 0); }
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
			setState(108);
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
			case BOXES:
				enterOuterAlt(_localctx, 2);
				{
				setState(9);
				match(BOXES);
				setState(10);
				match(ADD);
				setState(11);
				match(STRING);
				setState(12);
				match(STRING);
				}
				break;
			case CLUSTER:
				enterOuterAlt(_localctx, 3);
				{
				setState(13);
				match(CLUSTER);
				setState(14);
				match(VAR);
				setState(15);
				match(NUMBER);
				setState(16);
				match(NUMBER);
				}
				break;
			case INSTALL:
				enterOuterAlt(_localctx, 4);
				{
				setState(17);
				match(INSTALL);
				setState(18);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==EE || _la==OS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(21); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(20);
					match(VAR);
					}
					}
					setState(23); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case UNINSTALL:
				enterOuterAlt(_localctx, 5);
				{
				setState(25);
				match(UNINSTALL);
				setState(26);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case MEMBER_BOX:
				enterOuterAlt(_localctx, 6);
				{
				setState(27);
				match(MEMBER_BOX);
				setState(28);
				match(NUMBER);
				setState(29);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case ADD:
				enterOuterAlt(_localctx, 7);
				{
				setState(30);
				match(ADD);
				setState(31);
				_la = _input.LA(1);
				if ( !(_la==MEMBER || _la==CLIENT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(32);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(33);
				match(NUMBER);
				setState(34);
				match(VAR);
				setState(36); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(35);
					match(VAR);
					}
					}
					setState(38); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 8);
				{
				setState(40);
				match(LOAD);
				setState(41);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(42);
				match(VAR);
				setState(43);
				match(STRING);
				}
				break;
			case REPLICATE:
				enterOuterAlt(_localctx, 9);
				{
				setState(44);
				match(REPLICATE);
				setState(45);
				match(VAR);
				setState(46);
				match(VAR);
				setState(47);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(48);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 10);
				{
				setState(49);
				match(SET);
				setState(50);
				match(VAR);
				setState(51);
				matchWildcard();
				setState(52);
				match(VAR);
				setState(53);
				match(ASSIGN);
				setState(54);
				match(STRING);
				}
				break;
			case INVOKE:
				enterOuterAlt(_localctx, 11);
				{
				setState(55);
				match(INVOKE);
				setState(56);
				match(NUMBER);
				setState(57);
				match(VAR);
				setState(58);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(59);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(60);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INFO:
				enterOuterAlt(_localctx, 12);
				{
				setState(61);
				match(INFO);
				setState(62);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(64);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(63);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
				break;
			case KILL:
				enterOuterAlt(_localctx, 13);
				{
				setState(66);
				match(KILL);
				setState(67);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(69);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(68);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
				break;
			case CAT:
				enterOuterAlt(_localctx, 14);
				{
				setState(71);
				match(CAT);
				setState(72);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(74);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(73);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
				break;
			case GREP:
				enterOuterAlt(_localctx, 15);
				{
				setState(76);
				match(GREP);
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
				match(STRING);
				}
				break;
			case CLEAN:
				enterOuterAlt(_localctx, 16);
				{
				setState(80);
				match(CLEAN);
				setState(81);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(83);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(82);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
					_errHandler.recoverInline(this);
					} else {
						consume();
					}
					}
				}

				}
				break;
			case RESTART:
				enterOuterAlt(_localctx, 17);
				{
				setState(85);
				match(RESTART);
				setState(86);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(87);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(88);
				match(VAR);
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(89);
					match(VAR);
					}
					}
					setState(92); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case SLEEP:
				enterOuterAlt(_localctx, 18);
				{
				setState(94);
				match(SLEEP);
				setState(95);
				match(NUMBER);
				}
				break;
			case MEMBERS_ONLY:
				enterOuterAlt(_localctx, 19);
				{
				setState(96);
				match(MEMBERS_ONLY);
				setState(97);
				match(NUMBER);
				}
				break;
			case SAVE:
				enterOuterAlt(_localctx, 20);
				{
				setState(98);
				match(SAVE);
				setState(99);
				match(STRING);
				}
				break;
			case EXIT:
				enterOuterAlt(_localctx, 21);
				{
				setState(100);
				match(EXIT);
				}
				break;
			case SHOWSSH:
				enterOuterAlt(_localctx, 22);
				{
				setState(101);
				match(SHOWSSH);
				setState(102);
				match(BOOL);
				}
				break;
			case PROMPT:
				enterOuterAlt(_localctx, 23);
				{
				setState(103);
				match(PROMPT);
				setState(104);
				match(BOOL);
				}
				break;
			case HOMEIP:
				enterOuterAlt(_localctx, 24);
				{
				setState(105);
				match(HOMEIP);
				setState(106);
				match(STRING);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 25);
				{
				setState(107);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\62q\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\6\3\30\n\3\r\3\16\3\31\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6"+
		"\3\'\n\3\r\3\16\3(\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3C\n\3\3\3\3\3\3\3\5\3"+
		"H\n\3\3\3\3\3\3\3\5\3M\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3V\n\3\3\3\3"+
		"\3\3\3\3\3\3\3\6\3]\n\3\r\3\16\3^\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3o\n\3\3\3\2\2\4\2\4\2\7\4\2\36\36,,\3\2\16\17"+
		"\3\2\37 \4\2,,//\4\2\36\36!$\u008d\2\6\3\2\2\2\4n\3\2\2\2\6\7\5\4\3\2"+
		"\7\3\3\2\2\2\b\t\7,\2\2\t\n\7(\2\2\no\7/\2\2\13\f\7\b\2\2\f\r\7\6\2\2"+
		"\r\16\7/\2\2\16o\7/\2\2\17\20\7\n\2\2\20\21\7,\2\2\21\22\7-\2\2\22o\7"+
		"-\2\2\23\24\7\f\2\2\24\25\t\2\2\2\25\27\t\3\2\2\26\30\7,\2\2\27\26\3\2"+
		"\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32o\3\2\2\2\33\34\7\r\2"+
		"\2\34o\t\2\2\2\35\36\7\7\2\2\36\37\7-\2\2\37o\t\2\2\2 !\7\6\2\2!\"\t\4"+
		"\2\2\"#\t\2\2\2#$\7-\2\2$&\7,\2\2%\'\7,\2\2&%\3\2\2\2\'(\3\2\2\2(&\3\2"+
		"\2\2()\3\2\2\2)o\3\2\2\2*+\7\20\2\2+,\t\2\2\2,-\7,\2\2-o\7/\2\2./\7\13"+
		"\2\2/\60\7,\2\2\60\61\7,\2\2\61\62\t\5\2\2\62o\t\5\2\2\63\64\7\21\2\2"+
		"\64\65\7,\2\2\65\66\13\2\2\2\66\67\7,\2\2\678\7(\2\28o\7/\2\29:\7\22\2"+
		"\2:;\7-\2\2;<\7,\2\2<=\t\2\2\2=>\t\2\2\2>o\t\6\2\2?@\7\23\2\2@B\t\2\2"+
		"\2AC\t\6\2\2BA\3\2\2\2BC\3\2\2\2Co\3\2\2\2DE\7\24\2\2EG\t\2\2\2FH\t\6"+
		"\2\2GF\3\2\2\2GH\3\2\2\2Ho\3\2\2\2IJ\7\25\2\2JL\t\2\2\2KM\t\6\2\2LK\3"+
		"\2\2\2LM\3\2\2\2Mo\3\2\2\2NO\7\26\2\2OP\t\2\2\2PQ\t\6\2\2Qo\7/\2\2RS\7"+
		"\30\2\2SU\t\2\2\2TV\t\6\2\2UT\3\2\2\2UV\3\2\2\2Vo\3\2\2\2WX\7\27\2\2X"+
		"Y\t\2\2\2YZ\t\6\2\2Z\\\7,\2\2[]\7,\2\2\\[\3\2\2\2]^\3\2\2\2^\\\3\2\2\2"+
		"^_\3\2\2\2_o\3\2\2\2`a\7\31\2\2ao\7-\2\2bc\7%\2\2co\7-\2\2de\7\32\2\2"+
		"eo\7/\2\2fo\7\33\2\2gh\7\34\2\2ho\7)\2\2ij\7\35\2\2jo\7)\2\2kl\7\3\2\2"+
		"lo\7/\2\2mo\7\62\2\2n\b\3\2\2\2n\13\3\2\2\2n\17\3\2\2\2n\23\3\2\2\2n\33"+
		"\3\2\2\2n\35\3\2\2\2n \3\2\2\2n*\3\2\2\2n.\3\2\2\2n\63\3\2\2\2n9\3\2\2"+
		"\2n?\3\2\2\2nD\3\2\2\2nI\3\2\2\2nN\3\2\2\2nR\3\2\2\2nW\3\2\2\2n`\3\2\2"+
		"\2nb\3\2\2\2nd\3\2\2\2nf\3\2\2\2ng\3\2\2\2ni\3\2\2\2nk\3\2\2\2nm\3\2\2"+
		"\2o\5\3\2\2\2\n\31(BGLU^n";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}