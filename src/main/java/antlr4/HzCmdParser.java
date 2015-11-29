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
		USER=1, VERSION=2, EE=3, KILL=4, CLUSTER=5, TERMINAL_FUNCTION=6, BOOL=7, 
		TRUE=8, FALSE=9, ID=10, TEXT=11, NUMBER=12, STRING=13, WHITESPACE=14, 
		COMMENT=15;
	public static final int
		RULE_script = 0, RULE_statement = 1;
	public static final String[] ruleNames = {
		"script", "statement"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'user'", "'version'", "'ee'", "'kill'", "'cluster'", null, null, 
		"'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "USER", "VERSION", "EE", "KILL", "CLUSTER", "TERMINAL_FUNCTION", 
		"BOOL", "TRUE", "FALSE", "ID", "TEXT", "NUMBER", "STRING", "WHITESPACE", 
		"COMMENT"
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
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(5); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(4);
				statement();
				}
				}
				setState(7); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << USER) | (1L << VERSION) | (1L << EE) | (1L << KILL) | (1L << CLUSTER) | (1L << TERMINAL_FUNCTION) | (1L << COMMENT))) != 0) );
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
		public TerminalNode USER() { return getToken(HzCmdParser.USER, 0); }
		public TerminalNode ID() { return getToken(HzCmdParser.ID, 0); }
		public TerminalNode VERSION() { return getToken(HzCmdParser.VERSION, 0); }
		public TerminalNode STRING() { return getToken(HzCmdParser.STRING, 0); }
		public TerminalNode EE() { return getToken(HzCmdParser.EE, 0); }
		public TerminalNode BOOL() { return getToken(HzCmdParser.BOOL, 0); }
		public TerminalNode KILL() { return getToken(HzCmdParser.KILL, 0); }
		public TerminalNode CLUSTER() { return getToken(HzCmdParser.CLUSTER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(HzCmdParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(HzCmdParser.NUMBER, i);
		}
		public TerminalNode TERMINAL_FUNCTION() { return getToken(HzCmdParser.TERMINAL_FUNCTION, 0); }
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
		try {
			setState(25);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(9);
				match(USER);
				setState(10);
				match(ID);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				match(VERSION);
				setState(12);
				match(STRING);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(13);
				match(EE);
				setState(14);
				match(BOOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(15);
				match(KILL);
				setState(16);
				match(ID);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(17);
				match(CLUSTER);
				setState(18);
				match(ID);
				setState(19);
				match(NUMBER);
				setState(20);
				match(NUMBER);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(21);
				match(CLUSTER);
				setState(22);
				match(ID);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(23);
				match(TERMINAL_FUNCTION);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(24);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\21\36\4\2\t\2\4\3"+
		"\t\3\3\2\6\2\b\n\2\r\2\16\2\t\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\5\3\34\n\3\3\3\2\2\4\2\4\2\2#\2\7\3\2\2\2\4\33"+
		"\3\2\2\2\6\b\5\4\3\2\7\6\3\2\2\2\b\t\3\2\2\2\t\7\3\2\2\2\t\n\3\2\2\2\n"+
		"\3\3\2\2\2\13\f\7\3\2\2\f\34\7\f\2\2\r\16\7\4\2\2\16\34\7\17\2\2\17\20"+
		"\7\5\2\2\20\34\7\t\2\2\21\22\7\6\2\2\22\34\7\f\2\2\23\24\7\7\2\2\24\25"+
		"\7\f\2\2\25\26\7\16\2\2\26\34\7\16\2\2\27\30\7\7\2\2\30\34\7\f\2\2\31"+
		"\34\7\b\2\2\32\34\7\21\2\2\33\13\3\2\2\2\33\r\3\2\2\2\33\17\3\2\2\2\33"+
		"\21\3\2\2\2\33\23\3\2\2\2\33\27\3\2\2\2\33\31\3\2\2\2\33\32\3\2\2\2\34"+
		"\5\3\2\2\2\4\t\33";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}