grammar HzCmd;

// Parser Rules   antlr4 HzCmd.g4 -package antlr4



 /*
                            case load:
                            case invoke:
                            case stop:
                                cluster.send(line);
                                break;


                            case ssh:
                                boxes.sshCmd(line.replace("ssh", ""));
                                break;
                             case grep:
                                cluster.grepMembers(words[1]);
                                break;
*/


script : statement+ ;

statement
    : USER ID
    | VERSION STRING
    | EE BOOL
    | KILL ID
    | CLUSTER ID NUMBER NUMBER
    | CLUSTER ID
    | ADDIP IP_PAIR
    | TERMINAL_FUNCTION
    | SLEEP NUMBER
    | MEMBERS_ONLY NUMBER
    | MEMBERS NUMBER
    | CLIENTS NUMBER
    | COMMENT
    ;

USER : 'user' ;
VERSION : 'version';
SLEEP : 'sleep';
EE : 'ee';
KILL : 'kill';

CLUSTER : 'cluster' ;
MEMBERS_ONLY : 'membersOnly';
MEMBERS : 'members';
CLIENTS : 'clients';

ADDIP : 'appip';

TERMINAL_FUNCTION : 'install' | 'init' | 'uninstall' | 'msg' | 'jps' | 'cat' | 'clean' | 'layout' | 'info' | 'exit';


HZTYPE : MEMBER | CLIENT | ALL;
MEMBER : 'member';
CLIENT : 'client';
ALL : 'all';


BOOL : TRUE | FALSE;
TRUE : 'true';
FALSE : 'false';



ID : [a-z][a-zA-Z0-9]* ;
TEXT : [a-z]+ ;
NUMBER : [0-9]+ ;
STRING : ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'.'|'-')+ ;

IP_PAIR : IP','IP;
IP : Octet '.' Octet '.' Octet '.' Octet ;

Octet :  Digit Digit Digit | Digit Digit | Digit ;

fragment Digit : '0'..'9';

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ -> skip ;
COMMENT : '//' ~[\r\n]* -> skip ;