grammar HzCmd;

// Parser Rules   antlr4 HzCmd.g4 -package antlr4


script : statement+ ;


statement
    : VAR ASSIGN STRING
    | USER STRING
    | VERSION STRING
    | ADD (IP IP_PAIR)
    | ADD (FILE STRING)
    | CLUSTER VAR NUMBER NUMBER
    | REPLICATE VAR VAR (VAR | STRING) (VAR | STRING)
    | INSTALL (ALL | VAR)  (OS | EE) VAR+
    | ADD (MEMBER | CLIENT) VAR NUMBER VAR VAR
    | LOAD (ALL | VAR) VAR STRING
    | SET VAR.VAR ASSIGN STRING
    | INVOKE NUMBER VAR (ALL | VAR) (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | KILL (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)
    | START (ALL | VAR) (ALL | MEMBER_ALL | MEMBER_VAR | CLIENT_ALL | CLIENT_VAR)

    | SLEEP NUMBER
    | MEMBERS_ONLY NUMBER
    | SAVE STRING

    | COMMENT
    ;



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

KILL : 'kill';
START : 'start';


ALL : '*';
MEMBER : 'member';
CLIENT : 'client';

MEMBER_ALL : 'member'ALL;
MEMBER_VAR : 'member'VAR;

CLIENT_ALL : 'client'ALL;
CLIENT_VAR : 'client'VAR;



MEMBERS_ONLY : 'membersOnly';
MEMBERS : 'members';
CLIENTS : 'clients';



SLEEP : 'sleep';
SAVE : 'save';

ASSIGN : '=' ;
BOOL : TRUE | FALSE;
TRUE : 'true';
FALSE : 'false';



VAR : ([a-z]|[A-Z])([a-z]|[A-Z]|[0-9]|'_')* ;
TEXT : [a-z]+ ;
NUMBER : [0-9]+ ;

//fragment CHAR   :'a'..'z'|'A'..'Z'|'0'..'9'|'/'|'_'|'.'|'-';

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;


CHAR   :( [a-z]|[A-Z]|[0-9]|'/'|'_'|'.'|'-'|' ' );

STRING : '"'CHAR+'"';
//STRING : [a-z][a-zA-Z0-9]+ ;
//STRING : ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'.'|'-')+ ;





IP_PAIR : IP_STR','IP_STR;
IP_STR : Octet '.' Octet '.' Octet '.' Octet ;

Octet :  Digit Digit Digit | Digit Digit | Digit ;

fragment Digit : '0'..'9';

COMMENT : '//' ~[\r\n]* -> skip ;
