grammar HzCmd;

// Parser Rules   antlr4 HzCmd.g4 -package antlr4

script
    : statement
    ;

statement
    : BOXES ADD STRING STRING
    | CLUSTER VAR NUMBER NUMBER

    | INSTALL   (ALL | VAR) (OS | EE) STRING
    | UNINSTALL (ALL | VAR)

    | MEMBER_BOX NUMBER (ALL | VAR)
    | ADD (MEMBER | CLIENT) (ALL | VAR) NUMBER STRING STRING+
    | LOAD (ALL | VAR) VAR STRING

    | REPLICATE VAR VAR (VAR | STRING) (VAR | STRING)
    | SET VAR.VAR ASSIGN STRING
    | INVOKE NUMBER VAR (ALL | VAR) (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | STOP VAR (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)?

    | INFO     (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)?
    | KILL     (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)?
    | CAT      (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)?
    | GREP     (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR) STRING

    | CLEAN    (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)?
    | RESTART  (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR) VAR VAR+
    | DOWNLOAD (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR) STRING

    | MEMBERS_ONLY NUMBER
    | SAVE STRING
    | EXIT
    | SHOWSSH BOOL
    | PROMPT BOOL
    | HOMEIP STRING
    ;

HOMEIP : 'homeIp';
USER : 'user' ;
VERSION : 'version';
ADD : 'add';

MEMBER_BOX : 'memberBox';
BOXES : 'boxes';
IP : 'ip';
CLUSTER : 'cluster' ;
REPLICATE : 'replicate' ;
INSTALL : 'install' ;
UNINSTALL : 'uninstall' ;
EE : 'EE';
OS : 'OS';
LOAD : 'load' ;
SET : 'set' ;
INVOKE : 'invoke' ;
STOP : 'stop';

INFO : 'info';
KILL : 'kill';
CAT : 'cat';
GREP : 'grep';
RESTART : 'restart';
CLEAN : 'clean';
SAVE : 'save';
EXIT : 'exit';
DOWNLOAD : 'download';
SHOWSSH : 'showSSH';
PROMPT : 'prompt';


ALL : '*';
MEMBER : 'member';
CLIENT : 'client';

MEMBER_ALL : 'Member'ALL;
MEMBER_VAR : 'Member'NUMBER;

CLIENT_ALL : 'Client'ALL;
CLIENT_VAR : 'Client'NUMBER;

MEMBERS_ONLY : 'membersOnly';
MEMBERS : 'members';
CLIENTS : 'clients';


BOOL : TRUE | FALSE;
TRUE : 'true';
FALSE : 'false';


VAR : ([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]|'_')* ;
NUMBER : [0-9]+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;

fragment CHAR   :( [a-z]|[A-Z]|[0-9]|'/'|'_'|'.'|'-'|' '|'='|'#'|':'|'\\'|'|'|'*'|'['|']' );
STRING : CHAR;


IP_PAIR : IP_STR','IP_STR;
IP_STR : Octet '.' Octet '.' Octet '.' Octet ;

fragment Octet :  Digit Digit Digit | Digit Digit | Digit ;
fragment Digit : '0'..'9';

