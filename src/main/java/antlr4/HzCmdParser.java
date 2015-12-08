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
		USER=1, VERSION=2, ADD=3, FILE=4, IP=5, CLUSTER=6, REPLICATE=7, INSTALL=8, 
		EE=9, OS=10, LOAD=11, SET=12, INVOKE=13, KILL=14, CAT=15, RESTART=16, 
		SLEEP=17, SAVE=18, EXIT=19, SHOWSSH=20, ALL=21, MEMBER=22, CLIENT=23, 
		MEMBER_ALL=24, MEMBER_VAR=25, CLIENT_ALL=26, CLIENT_VAR=27, MEMBERS_ONLY=28, 
		MEMBERS=29, CLIENTS=30, ASSIGN=31, BOOL=32, TRUE=33, FALSE=34, VAR=35, 
		NUMBER=36, WHITESPACE=37, STRING=38, IP_PAIR=39, IP_STR=40, COMMENT=41;
	public static final int
		RULE_script = 0, RULE_statement = 1;
	public static final String[] ruleNames = {
		"script", "statement"
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
		public TerminalNode ADD() { return getToken(HzCmdParser.ADD, 0); }
		public TerminalNode IP() { return getToken(HzCmdParser.IP, 0); }
		public TerminalNode IP_PAIR() { return getToken(HzCmdParser.IP_PAIR, 0); }
		public TerminalNode FILE() { return getToken(HzCmdParser.FILE, 0); }
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
		public TerminalNode MEMBER() { return getToken(HzCmdParser.MEMBER, 0); }
		public TerminalNode CLIENT() { return getToken(HzCmdParser.CLIENT, 0); }
		public TerminalNode LOAD() { return getToken(HzCmdParser.LOAD, 0); }
		public TerminalNode SET() { return getToken(HzCmdParser.SET, 0); }
		public TerminalNode INVOKE() { return getToken(HzCmdParser.INVOKE, 0); }
		public TerminalNode MEMBER_ALL() { return getToken(HzCmdParser.MEMBER_ALL, 0); }
		public TerminalNode MEMBER_VAR() { return getToken(HzCmdParser.MEMBER_VAR, 0); }
		public TerminalNode CLIENT_ALL() { return getToken(HzCmdParser.CLIENT_ALL, 0); }
		public TerminalNode CLIENT_VAR() { return getToken(HzCmdParser.CLIENT_VAR, 0); }
		public TerminalNode KILL() { return getToken(HzCmdParser.KILL, 0); }
		public TerminalNode CAT() { return getToken(HzCmdParser.CAT, 0); }
		public TerminalNode RESTART() { return getToken(HzCmdParser.RESTART, 0); }
		public TerminalNode SLEEP() { return getToken(HzCmdParser.SLEEP, 0); }
		public TerminalNode MEMBERS_ONLY() { return getToken(HzCmdParser.MEMBERS_ONLY, 0); }
		public TerminalNode SAVE() { return getToken(HzCmdParser.SAVE, 0); }
		public TerminalNode EXIT() { return getToken(HzCmdParser.EXIT, 0); }
		public TerminalNode SHOWSSH() { return getToken(HzCmdParser.SHOWSSH, 0); }
		public TerminalNode BOOL() { return getToken(HzCmdParser.BOOL, 0); }
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
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
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
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(9);
				match(USER);
				setState(10);
				match(STRING);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(11);
				match(VERSION);
				setState(12);
				match(STRING);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(13);
				match(ADD);
				{
				setState(14);
				match(IP);
				setState(15);
				match(IP_PAIR);
				}
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(16);
				match(ADD);
				{
				setState(17);
				match(FILE);
				setState(18);
				match(STRING);
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(19);
				match(CLUSTER);
				setState(20);
				match(VAR);
				setState(21);
				match(NUMBER);
				setState(22);
				match(NUMBER);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(23);
				match(REPLICATE);
				setState(24);
				match(VAR);
				setState(25);
				match(VAR);
				setState(26);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(27);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(28);
				match(INSTALL);
				setState(29);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(30);
				_la = _input.LA(1);
				if ( !(_la==EE || _la==OS) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(32); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(31);
					match(VAR);
					}
					}
					setState(34); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case 9:
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
			case 10:
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
			case 11:
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
			case 12:
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
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(62);
				match(KILL);
				setState(63);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(64);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(65);
				match(CAT);
				setState(66);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(67);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(68);
				match(RESTART);
				setState(69);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(70);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(71);
				match(VAR);
				setState(73); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(72);
					match(VAR);
					}
					}
					setState(75); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(77);
				match(SLEEP);
				setState(78);
				match(NUMBER);
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(79);
				match(MEMBERS_ONLY);
				setState(80);
				match(NUMBER);
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(81);
				match(SAVE);
				setState(82);
				match(STRING);
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(83);
				match(EXIT);
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(84);
				match(SHOWSSH);
				setState(85);
				match(BOOL);
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(86);
				match(COMMENT);
				}
				break;
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3+\\\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3#\n\3\r\3\16\3$\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\6\3-\n\3\r\3\16\3.\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\6\3L\n\3\r\3\16\3M\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3"+
		"Z\n\3\3\3\2\2\4\2\4\2\7\4\2%%((\4\2\27\27%%\3\2\13\f\3\2\30\31\4\2\27"+
		"\27\32\35p\2\6\3\2\2\2\4Y\3\2\2\2\6\7\5\4\3\2\7\3\3\2\2\2\b\t\7%\2\2\t"+
		"\n\7!\2\2\nZ\7(\2\2\13\f\7\3\2\2\fZ\7(\2\2\r\16\7\4\2\2\16Z\7(\2\2\17"+
		"\20\7\5\2\2\20\21\7\7\2\2\21Z\7)\2\2\22\23\7\5\2\2\23\24\7\6\2\2\24Z\7"+
		"(\2\2\25\26\7\b\2\2\26\27\7%\2\2\27\30\7&\2\2\30Z\7&\2\2\31\32\7\t\2\2"+
		"\32\33\7%\2\2\33\34\7%\2\2\34\35\t\2\2\2\35Z\t\2\2\2\36\37\7\n\2\2\37"+
		" \t\3\2\2 \"\t\4\2\2!#\7%\2\2\"!\3\2\2\2#$\3\2\2\2$\"\3\2\2\2$%\3\2\2"+
		"\2%Z\3\2\2\2&\'\7\5\2\2\'(\t\5\2\2()\t\3\2\2)*\7&\2\2*,\7%\2\2+-\7%\2"+
		"\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2./\3\2\2\2/Z\3\2\2\2\60\61\7\r\2\2\61"+
		"\62\t\3\2\2\62\63\7%\2\2\63Z\7(\2\2\64\65\7\16\2\2\65\66\7%\2\2\66\67"+
		"\13\2\2\2\678\7%\2\289\7!\2\29Z\7(\2\2:;\7\17\2\2;<\7&\2\2<=\7%\2\2=>"+
		"\t\3\2\2>?\t\3\2\2?Z\t\6\2\2@A\7\20\2\2AB\t\3\2\2BZ\t\6\2\2CD\7\21\2\2"+
		"DE\t\3\2\2EZ\t\6\2\2FG\7\22\2\2GH\t\3\2\2HI\t\6\2\2IK\7%\2\2JL\7%\2\2"+
		"KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NZ\3\2\2\2OP\7\23\2\2PZ\7&\2\2"+
		"QR\7\36\2\2RZ\7&\2\2ST\7\24\2\2TZ\7(\2\2UZ\7\25\2\2VW\7\26\2\2WZ\7\"\2"+
		"\2XZ\7+\2\2Y\b\3\2\2\2Y\13\3\2\2\2Y\r\3\2\2\2Y\17\3\2\2\2Y\22\3\2\2\2"+
		"Y\25\3\2\2\2Y\31\3\2\2\2Y\36\3\2\2\2Y&\3\2\2\2Y\60\3\2\2\2Y\64\3\2\2\2"+
		"Y:\3\2\2\2Y@\3\2\2\2YC\3\2\2\2YF\3\2\2\2YO\3\2\2\2YQ\3\2\2\2YS\3\2\2\2"+
		"YU\3\2\2\2YV\3\2\2\2YX\3\2\2\2Z\5\3\2\2\2\6$.MY";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}