grammar HzCmd;

// Parser Rules   antlr4 HzCmd.g4 -package antlr4

script : statement+ ;

statement
    : CLUSTER ID NUMBER NUMBER
    | CMD_0ARG
    ;

CLUSTER : 'cluster' ;

CMD_0ARG : 'user' | 'install' | 'uninstall' ;


ID : [a-z][a-zA-Z]* ;
TEXT : [a-z]+ ;
NUMBER : [0-9]+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;