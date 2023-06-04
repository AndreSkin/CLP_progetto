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
       | 'if' '(' exp ')' '{' (stm)+ '}' ('else' '{' (stm)+ '}')? #ifStm
	   ;

exp    :  INTEGER #intExp
       | 'true' #trueExp
       | 'false' #falseExp
       | ID #idExp
       | '!' exp #notIdExp
       | exp ('*' | '/') exp #mulDivExp
       | exp ('+' | '-') exp #plusMinusExp
       | exp ('>' | '<' | '>=' | '<=' | '==') exp #cfrExp
       | exp ('&&' | '||') exp #logicalExp
       | 'if' '(' exp ')' '{' (stm)* exp '}' 'else' '{' (stm)* exp '}' #ifExp
       | '(' exp ')' #bracketExp
       | ID '(' (exp (',' exp)* )? ')' #funCallExp
       ;

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

ERR             : .  -> channel(HIDDEN);
