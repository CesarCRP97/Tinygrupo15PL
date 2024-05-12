asint: cleanasint
	javac asint/*.java


jflex: asint cleanjflex
	jflex c_ast_ascendente/spec.jflex 

cleanjflex:
	rm -f c_ast_ascendente/AnalizadorLexicoTiny.java


cup: asint jflex cleancup
	java -jar java-cup-11b.jar -parser ConstructorASTTiny -symbols ClaseLexica -destdir c_ast_ascendente c_ast_ascendente/spec.cup

cleancup:
	rm -f c_ast_ascendente/ConstructorASTTiny.java
	rm -f c_ast_ascendente/ClaseLexica.java


javacc: asint jflex cup cleanjavacc
	java -cp javacc.jar javacc -OUTPUT_DIRECTORY=c_ast_descendente c_ast_descendente/spec.jj

cleanjavacc:
	rm -f c_ast_descendente/TokenMgrError.java
	rm -f c_ast_descendente/ParseException.java
	rm -f c_ast_descendente/Token.java
	rm -f c_ast_descendente/SimpleCharStream.java
	rm -f c_ast_descendente/ConstructorASTsTiny.java
	rm -f c_ast_descendente/ConstructorASTsTinyConstants.java
	rm -f c_ast_descendente/ConstructorASTsTinyTokenManager.java


build: clean asint jflex cup javacc
	javac -cp java-cup-11b.jar: c_ast_ascendente/*.java c_ast_descendente/*.java procesamientos/*.java Main.java DomJudge.java

zipdj:
	zip -r PLG15fase4DJ.zip maquinap asint c_ast_ascendente c_ast_descendente DomJudge.java procesamientos ruta_* cup_options

cleanasint:
	rm -f asint/*.class
	rm -f asint/*~

cleanasc: cleanjflex cleancup
	rm -f c_ast_ascendente/*.class
	rm -f c_ast_ascendente/*~

cleandesc: cleanjavacc
	rm -f c_ast_descendente/*.class
	rm -f c_ast_descendente/*~

cleanev:
	rm -f procesamientos/*.class
	rm -f procesamientos/*~

cleanrun:
	rm -f output*.txt

clean: cleanasint cleanasc cleandesc cleanev cleanrun
	rm -f maquinap/*.class
	rm -f maquinap/*~
	rm -f *.class
	rm -f *~
	rm -f sample*.outh
	rm -f PLG15fase4DJ.zip

runasc1:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input1.txt asc

runasc2:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input2.txt asc

runasc3:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input3.txt asc

runasc4:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input4.txt asc

rundesc1:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input1.txt desc

rundesc2:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input2.txt desc

rundesc3:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input3.txt desc

rundesc4:
	java -cp java-cup-11b.jar:. Main ../pruebas/pruebas_tiny/input4.txt desc
