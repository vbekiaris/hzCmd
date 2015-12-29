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
		STRING=1, HOMEIP=2, USER=3, VERSION=4, ADD=5, MEMBER_BOX=6, BOXES=7, IP=8, 
		CLUSTER=9, REPLICATE=10, INSTALL=11, UNINSTALL=12, EE=13, OS=14, LOAD=15, 
		SET=16, INVOKE=17, STOP=18, INFO=19, KILL=20, CAT=21, GREP=22, RESTART=23, 
		CLEAN=24, SAVE=25, EXIT=26, DOWNLOAD=27, SHOWSSH=28, PROMPT=29, ALL=30, 
		MEMBER=31, CLIENT=32, MEMBER_ALL=33, MEMBER_VAR=34, CLIENT_ALL=35, CLIENT_VAR=36, 
		MEMBERS_ONLY=37, MEMBERS=38, CLIENTS=39, BOOL=40, TRUE=41, FALSE=42, VAR=43, 
		NUMBER=44;
	public static final int
		RULE_script = 0, RULE_statement = 1;
	public static final String[] ruleNames = {
		"script", "statement"
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
		public TerminalNode BOXES() { return getToken(HzCmdParser.BOXES, 0); }
		public TerminalNode ADD() { return getToken(HzCmdParser.ADD, 0); }
		public List<TerminalNode> STRING() { return getTokens(HzCmdParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(HzCmdParser.STRING, i);
		}
		public TerminalNode CLUSTER() { return getToken(HzCmdParser.CLUSTER, 0); }
		public List<TerminalNode> VAR() { return getTokens(HzCmdParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(HzCmdParser.VAR, i);
		}
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
		public TerminalNode INVOKE() { return getToken(HzCmdParser.INVOKE, 0); }
		public TerminalNode MEMBER_ALL() { return getToken(HzCmdParser.MEMBER_ALL, 0); }
		public TerminalNode MEMBER_VAR() { return getToken(HzCmdParser.MEMBER_VAR, 0); }
		public TerminalNode CLIENT_ALL() { return getToken(HzCmdParser.CLIENT_ALL, 0); }
		public TerminalNode CLIENT_VAR() { return getToken(HzCmdParser.CLIENT_VAR, 0); }
		public TerminalNode STOP() { return getToken(HzCmdParser.STOP, 0); }
		public TerminalNode INFO() { return getToken(HzCmdParser.INFO, 0); }
		public TerminalNode KILL() { return getToken(HzCmdParser.KILL, 0); }
		public TerminalNode CAT() { return getToken(HzCmdParser.CAT, 0); }
		public TerminalNode GREP() { return getToken(HzCmdParser.GREP, 0); }
		public TerminalNode CLEAN() { return getToken(HzCmdParser.CLEAN, 0); }
		public TerminalNode RESTART() { return getToken(HzCmdParser.RESTART, 0); }
		public TerminalNode DOWNLOAD() { return getToken(HzCmdParser.DOWNLOAD, 0); }
		public TerminalNode MEMBERS_ONLY() { return getToken(HzCmdParser.MEMBERS_ONLY, 0); }
		public TerminalNode SAVE() { return getToken(HzCmdParser.SAVE, 0); }
		public TerminalNode EXIT() { return getToken(HzCmdParser.EXIT, 0); }
		public TerminalNode SHOWSSH() { return getToken(HzCmdParser.SHOWSSH, 0); }
		public TerminalNode BOOL() { return getToken(HzCmdParser.BOOL, 0); }
		public TerminalNode PROMPT() { return getToken(HzCmdParser.PROMPT, 0); }
		public TerminalNode HOMEIP() { return getToken(HzCmdParser.HOMEIP, 0); }
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
			setState(102);
			switch (_input.LA(1)) {
			case BOXES:
				enterOuterAlt(_localctx, 1);
				{
				setState(6);
				match(BOXES);
				setState(7);
				match(ADD);
				setState(8);
				match(STRING);
				setState(9);
				match(STRING);
				}
				break;
			case CLUSTER:
				enterOuterAlt(_localctx, 2);
				{
				setState(10);
				match(CLUSTER);
				setState(11);
				match(VAR);
				setState(12);
				match(NUMBER);
				setState(13);
				match(NUMBER);
				}
				break;
			case INSTALL:
				enterOuterAlt(_localctx, 3);
				{
				setState(14);
				match(INSTALL);
				setState(15);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(16);
				_la = _input.LA(1);
				if ( !(_la==EE || _la==OS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(17);
				match(STRING);
				}
				break;
			case UNINSTALL:
				enterOuterAlt(_localctx, 4);
				{
				setState(18);
				match(UNINSTALL);
				setState(19);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case MEMBER_BOX:
				enterOuterAlt(_localctx, 5);
				{
				setState(20);
				match(MEMBER_BOX);
				setState(21);
				match(NUMBER);
				setState(22);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case ADD:
				enterOuterAlt(_localctx, 6);
				{
				setState(23);
				match(ADD);
				setState(24);
				_la = _input.LA(1);
				if ( !(_la==MEMBER || _la==CLIENT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(25);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(26);
				match(NUMBER);
				setState(27);
				match(STRING);
				setState(29); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(28);
					match(STRING);
					}
					}
					setState(31); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STRING );
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 7);
				{
				setState(33);
				match(LOAD);
				setState(34);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(35);
				match(VAR);
				setState(36);
				match(STRING);
				}
				break;
			case REPLICATE:
				enterOuterAlt(_localctx, 8);
				{
				setState(37);
				match(REPLICATE);
				setState(38);
				match(VAR);
				setState(39);
				match(VAR);
				setState(40);
				_la = _input.LA(1);
				if ( !(_la==STRING || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(41);
				_la = _input.LA(1);
				if ( !(_la==STRING || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INVOKE:
				enterOuterAlt(_localctx, 9);
				{
				setState(42);
				match(INVOKE);
				setState(43);
				match(NUMBER);
				setState(44);
				match(VAR);
				setState(45);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(46);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(47);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case STOP:
				enterOuterAlt(_localctx, 10);
				{
				setState(48);
				match(STOP);
				setState(49);
				match(VAR);
				setState(50);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(52);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(51);
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
			case INFO:
				enterOuterAlt(_localctx, 11);
				{
				setState(54);
				match(INFO);
				setState(55);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(57);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(56);
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
				enterOuterAlt(_localctx, 12);
				{
				setState(59);
				match(KILL);
				setState(60);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(62);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(61);
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
				enterOuterAlt(_localctx, 13);
				{
				setState(64);
				match(CAT);
				setState(65);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(67);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(66);
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
				enterOuterAlt(_localctx, 14);
				{
				setState(69);
				match(GREP);
				setState(70);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(71);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(72);
				match(STRING);
				}
				break;
			case CLEAN:
				enterOuterAlt(_localctx, 15);
				{
				setState(73);
				match(CLEAN);
				setState(74);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(76);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) {
					{
					setState(75);
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
				enterOuterAlt(_localctx, 16);
				{
				setState(78);
				match(RESTART);
				setState(79);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(80);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(81);
				match(VAR);
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(82);
					match(VAR);
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case DOWNLOAD:
				enterOuterAlt(_localctx, 17);
				{
				setState(87);
				match(DOWNLOAD);
				setState(88);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(89);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(90);
				match(STRING);
				}
				break;
			case MEMBERS_ONLY:
				enterOuterAlt(_localctx, 18);
				{
				setState(91);
				match(MEMBERS_ONLY);
				setState(92);
				match(NUMBER);
				}
				break;
			case SAVE:
				enterOuterAlt(_localctx, 19);
				{
				setState(93);
				match(SAVE);
				setState(94);
				match(STRING);
				}
				break;
			case EXIT:
				enterOuterAlt(_localctx, 20);
				{
				setState(95);
				match(EXIT);
				}
				break;
			case SHOWSSH:
				enterOuterAlt(_localctx, 21);
				{
				setState(96);
				match(SHOWSSH);
				setState(97);
				match(BOOL);
				}
				break;
			case PROMPT:
				enterOuterAlt(_localctx, 22);
				{
				setState(98);
				match(PROMPT);
				setState(99);
				match(BOOL);
				}
				break;
			case HOMEIP:
				enterOuterAlt(_localctx, 23);
				{
				setState(100);
				match(HOMEIP);
				setState(101);
				match(STRING);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3.k\4\2\t\2\4\3\t\3"+
		"\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3 \n\3\r\3\16\3!\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\67\n\3\3"+
		"\3\3\3\3\3\5\3<\n\3\3\3\3\3\3\3\5\3A\n\3\3\3\3\3\3\3\5\3F\n\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\5\3O\n\3\3\3\3\3\3\3\3\3\3\3\6\3V\n\3\r\3\16\3W\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3i\n\3\3"+
		"\3\2\2\4\2\4\2\7\4\2  --\3\2\17\20\3\2!\"\4\2\3\3--\4\2  #&\u0085\2\6"+
		"\3\2\2\2\4h\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\7\t\2\2\t\n\7\7\2\2\n"+
		"\13\7\3\2\2\13i\7\3\2\2\f\r\7\13\2\2\r\16\7-\2\2\16\17\7.\2\2\17i\7.\2"+
		"\2\20\21\7\r\2\2\21\22\t\2\2\2\22\23\t\3\2\2\23i\7\3\2\2\24\25\7\16\2"+
		"\2\25i\t\2\2\2\26\27\7\b\2\2\27\30\7.\2\2\30i\t\2\2\2\31\32\7\7\2\2\32"+
		"\33\t\4\2\2\33\34\t\2\2\2\34\35\7.\2\2\35\37\7\3\2\2\36 \7\3\2\2\37\36"+
		"\3\2\2\2 !\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"i\3\2\2\2#$\7\21\2\2$%\t\2"+
		"\2\2%&\7-\2\2&i\7\3\2\2\'(\7\f\2\2()\7-\2\2)*\7-\2\2*+\t\5\2\2+i\t\5\2"+
		"\2,-\7\23\2\2-.\7.\2\2./\7-\2\2/\60\t\2\2\2\60\61\t\2\2\2\61i\t\6\2\2"+
		"\62\63\7\24\2\2\63\64\7-\2\2\64\66\t\2\2\2\65\67\t\6\2\2\66\65\3\2\2\2"+
		"\66\67\3\2\2\2\67i\3\2\2\289\7\25\2\29;\t\2\2\2:<\t\6\2\2;:\3\2\2\2;<"+
		"\3\2\2\2<i\3\2\2\2=>\7\26\2\2>@\t\2\2\2?A\t\6\2\2@?\3\2\2\2@A\3\2\2\2"+
		"Ai\3\2\2\2BC\7\27\2\2CE\t\2\2\2DF\t\6\2\2ED\3\2\2\2EF\3\2\2\2Fi\3\2\2"+
		"\2GH\7\30\2\2HI\t\2\2\2IJ\t\6\2\2Ji\7\3\2\2KL\7\32\2\2LN\t\2\2\2MO\t\6"+
		"\2\2NM\3\2\2\2NO\3\2\2\2Oi\3\2\2\2PQ\7\31\2\2QR\t\2\2\2RS\t\6\2\2SU\7"+
		"-\2\2TV\7-\2\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2Xi\3\2\2\2YZ\7\35"+
		"\2\2Z[\t\2\2\2[\\\t\6\2\2\\i\7\3\2\2]^\7\'\2\2^i\7.\2\2_`\7\33\2\2`i\7"+
		"\3\2\2ai\7\34\2\2bc\7\36\2\2ci\7*\2\2de\7\37\2\2ei\7*\2\2fg\7\4\2\2gi"+
		"\7\3\2\2h\b\3\2\2\2h\f\3\2\2\2h\20\3\2\2\2h\24\3\2\2\2h\26\3\2\2\2h\31"+
		"\3\2\2\2h#\3\2\2\2h\'\3\2\2\2h,\3\2\2\2h\62\3\2\2\2h8\3\2\2\2h=\3\2\2"+
		"\2hB\3\2\2\2hG\3\2\2\2hK\3\2\2\2hP\3\2\2\2hY\3\2\2\2h]\3\2\2\2h_\3\2\2"+
		"\2ha\3\2\2\2hb\3\2\2\2hd\3\2\2\2hf\3\2\2\2i\5\3\2\2\2\n!\66;@ENWh";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}