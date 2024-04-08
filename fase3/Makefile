asint: cleanasint
	javac asint/*.java


jflex: asint cleanjflex
	jflex c_ast_ascendente/spec.jflex 

cleanjflex:
	rm -f c_ast_ascendente/AnalizadorLexicoTiny.java


cup: asint jflex cleancup
	cup -parser ConstructorASTTiny -symbols ClaseLexica -destdir c_ast_ascendente c_ast_ascendente/spec.cup
	javac -cp java-cup-11b-20160615-3.jar: c_ast_ascendente/*.java

cleancup:
	rm -f c_ast_ascendente/ConstructorASTTiny.java
	rm -f c_ast_ascendente/ClaseLexica.java


javacc: asint jflex cup cleanjavacc
	javacc -OUTPUT_DIRECTORY=c_ast_descendente c_ast_descendente/spec.jj
	javac c_ast_descendente/*.java

cleanjavacc:
	rm -f c_ast_descendente/TokenMgrError.java
	rm -f c_ast_descendente/ParseException.java
	rm -f c_ast_descendente/Token.java
	rm -f c_ast_descendente/SimpleCharStream.java
	rm -f c_ast_descendente/ConstructorASTsTiny.java
	rm -f c_ast_descendente/ConstructorASTsTinyConstants.java
	rm -f c_ast_descendente/ConstructorASTsTinyTokenManager.java


build: clean asint jflex cup javacc
	javac -cp java-cup-11b-20160615-3.jar: evaluador/*.java

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
	rm -f evaluador/*.class
	rm -f evaluador/*~

clean: cleanasint cleanasc cleandesc cleanev
	rm -f *.class
	rm -f *~

