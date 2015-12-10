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
			setState(93);
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
			case ADD:
				enterOuterAlt(_localctx, 6);
				{
				setState(27);
				match(ADD);
				setState(28);
				_la = _input.LA(1);
				if ( !(_la==MEMBER || _la==CLIENT) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(29);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(30);
				match(NUMBER);
				setState(31);
				match(VAR);
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(32);
					match(VAR);
					}
					}
					setState(35); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case LOAD:
				enterOuterAlt(_localctx, 7);
				{
				setState(37);
				match(LOAD);
				setState(38);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(39);
				match(VAR);
				setState(40);
				match(STRING);
				}
				break;
			case REPLICATE:
				enterOuterAlt(_localctx, 8);
				{
				setState(41);
				match(REPLICATE);
				setState(42);
				match(VAR);
				setState(43);
				match(VAR);
				setState(44);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(45);
				_la = _input.LA(1);
				if ( !(_la==VAR || _la==STRING) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case SET:
				enterOuterAlt(_localctx, 9);
				{
				setState(46);
				match(SET);
				setState(47);
				match(VAR);
				setState(48);
				matchWildcard();
				setState(49);
				match(VAR);
				setState(50);
				match(ASSIGN);
				setState(51);
				match(STRING);
				}
				break;
			case INVOKE:
				enterOuterAlt(_localctx, 10);
				{
				setState(52);
				match(INVOKE);
				setState(53);
				match(NUMBER);
				setState(54);
				match(VAR);
				setState(55);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(56);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(57);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case INFO:
				enterOuterAlt(_localctx, 11);
				{
				setState(58);
				match(INFO);
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
			case KILL:
				enterOuterAlt(_localctx, 12);
				{
				setState(61);
				match(KILL);
				setState(62);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(63);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
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
				setState(66);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
				break;
			case RESTART:
				enterOuterAlt(_localctx, 14);
				{
				setState(67);
				match(RESTART);
				setState(68);
				_la = _input.LA(1);
				if ( !(_la==ALL || _la==VAR) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(69);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ALL) | (1L << MEMBER_ALL) | (1L << MEMBER_VAR) | (1L << CLIENT_ALL) | (1L << CLIENT_VAR))) != 0)) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				setState(70);
				match(VAR);
				setState(72); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(71);
					match(VAR);
					}
					}
					setState(74); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==VAR );
				}
				break;
			case CLEAN:
				enterOuterAlt(_localctx, 15);
				{
				setState(76);
				match(CLEAN);
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
				}
				break;
			case SLEEP:
				enterOuterAlt(_localctx, 16);
				{
				setState(79);
				match(SLEEP);
				setState(80);
				match(NUMBER);
				}
				break;
			case MEMBERS_ONLY:
				enterOuterAlt(_localctx, 17);
				{
				setState(81);
				match(MEMBERS_ONLY);
				setState(82);
				match(NUMBER);
				}
				break;
			case SAVE:
				enterOuterAlt(_localctx, 18);
				{
				setState(83);
				match(SAVE);
				setState(84);
				match(STRING);
				}
				break;
			case EXIT:
				enterOuterAlt(_localctx, 19);
				{
				setState(85);
				match(EXIT);
				}
				break;
			case SHOWSSH:
				enterOuterAlt(_localctx, 20);
				{
				setState(86);
				match(SHOWSSH);
				setState(87);
				match(BOOL);
				}
				break;
			case PROMPT:
				enterOuterAlt(_localctx, 21);
				{
				setState(88);
				match(PROMPT);
				setState(89);
				match(BOOL);
				}
				break;
			case HOMEIP:
				enterOuterAlt(_localctx, 22);
				{
				setState(90);
				match(HOMEIP);
				setState(91);
				match(STRING);
				}
				break;
			case COMMENT:
				enterOuterAlt(_localctx, 23);
				{
				setState(92);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\60b\4\2\t\2\4\3\t"+
		"\3\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\6\3\30\n\3\r\3\16\3\31\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\6\3$\n\3\r\3\16"+
		"\3%\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\6\3K\n\3\r\3\16\3L\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\5\3`\n\3\3\3\2\2\4\2\4\2\7\4\2\34\34**\3\2\r\16"+
		"\3\2\35\36\4\2**--\4\2\34\34\37\"x\2\6\3\2\2\2\4_\3\2\2\2\6\7\5\4\3\2"+
		"\7\3\3\2\2\2\b\t\7*\2\2\t\n\7&\2\2\n`\7-\2\2\13\f\7\7\2\2\f\r\7\6\2\2"+
		"\r\16\7-\2\2\16`\7-\2\2\17\20\7\t\2\2\20\21\7*\2\2\21\22\7+\2\2\22`\7"+
		"+\2\2\23\24\7\13\2\2\24\25\t\2\2\2\25\27\t\3\2\2\26\30\7*\2\2\27\26\3"+
		"\2\2\2\30\31\3\2\2\2\31\27\3\2\2\2\31\32\3\2\2\2\32`\3\2\2\2\33\34\7\f"+
		"\2\2\34`\t\2\2\2\35\36\7\6\2\2\36\37\t\4\2\2\37 \t\2\2\2 !\7+\2\2!#\7"+
		"*\2\2\"$\7*\2\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&`\3\2\2\2\'("+
		"\7\17\2\2()\t\2\2\2)*\7*\2\2*`\7-\2\2+,\7\n\2\2,-\7*\2\2-.\7*\2\2./\t"+
		"\5\2\2/`\t\5\2\2\60\61\7\20\2\2\61\62\7*\2\2\62\63\13\2\2\2\63\64\7*\2"+
		"\2\64\65\7&\2\2\65`\7-\2\2\66\67\7\21\2\2\678\7+\2\289\7*\2\29:\t\2\2"+
		"\2:;\t\2\2\2;`\t\6\2\2<=\7\22\2\2=>\t\2\2\2>`\t\6\2\2?@\7\23\2\2@A\t\2"+
		"\2\2A`\t\6\2\2BC\7\24\2\2CD\t\2\2\2D`\t\6\2\2EF\7\25\2\2FG\t\2\2\2GH\t"+
		"\6\2\2HJ\7*\2\2IK\7*\2\2JI\3\2\2\2KL\3\2\2\2LJ\3\2\2\2LM\3\2\2\2M`\3\2"+
		"\2\2NO\7\26\2\2OP\t\2\2\2P`\t\6\2\2QR\7\27\2\2R`\7+\2\2ST\7#\2\2T`\7+"+
		"\2\2UV\7\30\2\2V`\7-\2\2W`\7\31\2\2XY\7\32\2\2Y`\7\'\2\2Z[\7\33\2\2[`"+
		"\7\'\2\2\\]\7\3\2\2]`\7-\2\2^`\7\60\2\2_\b\3\2\2\2_\13\3\2\2\2_\17\3\2"+
		"\2\2_\23\3\2\2\2_\33\3\2\2\2_\35\3\2\2\2_\'\3\2\2\2_+\3\2\2\2_\60\3\2"+
		"\2\2_\66\3\2\2\2_<\3\2\2\2_?\3\2\2\2_B\3\2\2\2_E\3\2\2\2_N\3\2\2\2_Q\3"+
		"\2\2\2_S\3\2\2\2_U\3\2\2\2_W\3\2\2\2_X\3\2\2\2_Z\3\2\2\2_\\\3\2\2\2_^"+
		"\3\2\2\2`\5\3\2\2\2\6\31%L_";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}