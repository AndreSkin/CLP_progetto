grammar SimpLanPlus ;

prog   : exp    	           #singleExp
       | (dec)+ (stm)* (exp)?  #multipleExp
       ;

dec    : type ID ';'               #varDec
       | type ID '(' ( param ( ',' param)* )? ')' '{' body '}' #funDec
       ;

param  : type ID ;

body   : (dec)* (stm)* (exp)?
	   ;

type   : 'int'
       | 'bool'
       | 'void'
       ;

stm    : ID '=' exp ';' #asgStm
       | ID '(' (exp (',' exp)* )? ')' ';' #funCallStm
       | 'if' '(' condition=exp ')' '{' then=thenStmBranch '}' ('else' '{' else=elseStmBranch '}')? #ifStm
	   ;

thenStmBranch    : (stm)+ ;

elseStmBranch    : (stm)+ ;


exp    :  INTEGER #intExp
       | 'true' #trueExp
       | 'false' #falseExp
       | ID #idExp
       | '!' exp #notIdExp
       | e1=exp (mul='*' | div='/') e2=exp #mulDivExp
       | e1=exp ('+' | '-') e2=exp #plusMinusExp
       | e1=exp ('>' | '<' | '>=' | '<=' | '==') e2=exp #cfrExp
       | e1=exp ('&&' | '||') e2=exp #logicalExp
       | 'if' '(' condition=exp ')' '{' then=thenExpBranch '}' 'else' '{' else=elseExpBranch '}' #ifExp
       | '(' exp ')' #bracketExp
       | ID '(' (exp (',' exp)* )? ')' #funCallExp
       ;

thenExpBranch    : (stm)* exp ;

elseExpBranch    : (stm)* exp ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

//Numbers
fragment DIGIT  : '0'..'9';
INTEGER         : DIGIT+;

//IDs
fragment CHAR   : 'a'..'z' |'A'..'Z' ;
ID              : CHAR (CHAR | DIGIT)* ;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMENTS     : '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMENTS    : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMENTS)* '*/' -> skip;