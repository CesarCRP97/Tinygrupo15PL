package c_ast_ascendente;

%%
%line
%column
%class AnalizadorLexicoTiny
%type  UnidadLexica
%unicode
%public
%cup

%{
  private ALexOperations ops;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yycolumn+1;}
  public void fijaGestionErrores(GestionErroresTiny errores) {
    this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

%ignorecase

int = int
real = real
bool = bool
string = string
and = and
or = or
not = not
null = null
TRUE = TRUE
FALSE = FALSE
proc = proc
if = if
else = else
while = while
struct = struct
new = new
delete = delete
read = read
write = write
nl = nl
type = type
call = call
suma = \+
resta = \-
mult = \*
div = \/
porcentaje = %
menorque = <
mayorque = >
menorigual = <=
mayorigual = >=
igual_comp = ==
noigual_comp = \!=
par_a = \(
par_c = \)
puntoycoma = ;
igual_op = =
corch_a = \[
corch_c = \]
punto = \.
capirote = \^
coma = \,
llave_a = \{
llave_c = \}
ampersand = &
dobleampersand = &&
arroba = \@
digPositivo = [1-9]
digito = {digPositivo}|0
parteEntera = ({digPositivo}{digito}*|0)
litEntero = ([\+,\-])?{parteEntera}
parteDecimal = \.({digito}*{digPositivo}|0)
parteExponencial = (e|E){litEntero}
segParteReal = {parteDecimal}|{parteExponencial}|{parteDecimal}{parteExponencial}
litReal = {litEntero}{segParteReal}
letra = [a-zA-Z]
caracter = {letra}|{digito}|_
iden = ({letra}|_){caracter}*
litCadena = \"[^\"]*\"
espacio = [ \b\r\t\n]
comentario = ##([^\n])*
eof = \$

%%
{espacio} {}
{comentario} {}
{int} {return ops.unidadInt();}
{real} {return ops.unidadReal();}
{bool} {return ops.unidadBool();}
{string} {return ops.unidadString();}
{and} {return ops.unidadAnd();}
{or} {return ops.unidadOr();}
{not} {return ops.unidadNot();}
{null} {return ops.unidadNull();}
{TRUE} {return ops.unidadTrue();}
{FALSE} {return ops.unidadFalse();}
{proc} {return ops.unidadProc();}
{if} {return ops.unidadIf();}
{else} {return ops.unidadElse();}
{while} {return ops.unidadWhile();}
{struct} {return ops.unidadStruct();}
{new} {return ops.unidadNew();}
{delete} {return ops.unidadDelete();}
{read} {return ops.unidadRead();}
{write} {return ops.unidadWrite();}
{nl} {return ops.unidadNl();}
{type} {return ops.unidadType();}
{call} {return ops.unidadCall();}
{suma} {return ops.unidadSuma();}
{resta} {return ops.unidadResta();}
{mult} {return ops.unidadMult();}
{div} {return ops.unidadDiv();}
{porcentaje} {return ops.unidadPorcentaje();}
{menorque} {return ops.unidadMenorQue();}
{mayorque} {return ops.unidadMayorQue();}
{menorigual} {return ops.unidadMenorIgual();}
{mayorigual} {return ops.unidadMayorIgual();}
{igual_comp} {return ops.unidadIgualComp();}
{noigual_comp} {return ops.unidadNoIgualComp();}
{par_a} {return ops.unidadParA();}
{par_c} {return ops.unidadParC();}
{puntoycoma} {return ops.unidadPuntoYComa();}
{igual_op} {return ops.unidadIgualOp();}
{corch_a} {return ops.unidadCorchA();}
{corch_c} {return ops.unidadCorchC();}
{punto} {return ops.unidadPunto();}
{capirote} {return ops.unidadCapirote();}
{coma} {return ops.unidadComa();}
{llave_a} {return ops.unidadLlaveA();}
{llave_c} {return ops.unidadLlaveC();}
{ampersand} {return ops.unidadAmpersand();}
{dobleampersand} {return ops.unidadDobleAmpersand();}
{arroba} {return ops.unidadArroba();}
{iden} {return ops.unidadIden();}
{litCadena} {return ops.unidadLitCadena();}
{litEntero} {return ops.unidadLitEntero();}
{litReal} {return ops.unidadLitReal();}
{eof} {return ops.unidadEof();}
[^]                       {errores.errorLexico(fila(), columna(), lexema());}  
