grammar HzCmd;

// Parser Rules   antlr4 HzCmd.g4 -package antlr4


script
    : statement
    ;

statement
    : VAR ASSIGN STRING
    | USER STRING
    | VERSION STRING
    | ADD (IP IP_PAIR)
    | ADD (FILE STRING)
    | CLUSTER VAR NUMBER NUMBER
    | REPLICATE VAR VAR (VAR | STRING) (VAR | STRING)
    | INSTALL (ALL | VAR)  (OS | EE) VAR+
    | ADD (MEMBER | CLIENT) (ALL | VAR) NUMBER VAR VAR+
    | LOAD (ALL | VAR) VAR STRING
    | SET VAR.VAR ASSIGN STRING
    | INVOKE NUMBER VAR (ALL | VAR) (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | INFO (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | KILL (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | CAT (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | RESTART (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR) VAR VAR+
    | CLEAN (ALL | VAR | MEMBER_VAR | CLIENT_VAR)

    | SLEEP NUMBER
    | MEMBERS_ONLY NUMBER
    | SAVE STRING
    | EXIT
    | SHOWSSH BOOL
    | HOMEIP STRING

    | COMMENT
    ;



HOMEIP : 'homeIp';
USER : 'user' ;
VERSION : 'version';
ADD : 'add';

FILE : 'file';
IP : 'ip';
CLUSTER : 'cluster' ;
REPLICATE : 'replicate' ;
INSTALL : 'install' ;
EE : 'EE';
OS : 'OS';
LOAD : 'load' ;
SET : 'set' ;
INVOKE : 'invoke' ;

INFO : 'info';
KILL : 'kill';
CAT : 'cat';
RESTART : 'restart';
CLEAN : 'clean';
SLEEP : 'sleep';
SAVE : 'save';
EXIT : 'exit';
SHOWSSH : 'showSSH';


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


ASSIGN : '=' ;
BOOL : TRUE | FALSE;
TRUE : 'true';
FALSE : 'false';


VAR : ([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]|'_')* ;
NUMBER : [0-9]+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;

fragment CHAR   :( [a-z]|[A-Z]|[0-9]|'/'|'_'|'.'|'-'|' '|'=' );
STRING : '"'CHAR+'"';


IP_PAIR : IP_STR','IP_STR;
IP_STR : Octet '.' Octet '.' Octet '.' Octet ;

fragment Octet :  Digit Digit Digit | Digit Digit | Digit ;
fragment Digit : '0'..'9';

COMMENT : '//' ~[\r\n]* -> skip ;
