build: clean cleanjflex jflex cleancup cup
	javac -cp java-cup-11b-20160615-3.jar alex/*.java asint/*.java errors/*.java

debug: clean cleanjflex jflex cleancup debugcup
	javac -cp java-cup-11b-20160615-3.jar alex/*.java asint/*.java errors/*.java

domjudge: clean cleanjflex jflex cleancup cup
	javac -cp java-cup-11b-20160615-3.jar asint/*.java alex/*.java errors/*.java  DomJudge.java

zip: clean cleanjflex cleancup
	zip -r PLG15TinyCUP.zip asint/ alex/ errors/ ruta_cup ruta_jflex cup_options java-cup-11b-20160615-3.jar DomJudge.java

jflex:
	jflex alex/spec.jflex

CUPFLAGS = -parser AnalizadorSintacticoTiny -symbols ClaseLexica -destdir asint
DEBUGCUPFLAGS = -parser AnalizadorSintacticoTiny -symbols ClaseLexica -destdir asint -debug

cup:
	cup ${CUPFLAGS} asint/spec.cup

debugcup:
	cup ${DEBUGCUPFLAGS} asint/spec.cup

clean:
	rm -f *.class *~ PLG15TinyCUP.zip
	rm -f asint/*.class asint/*~
	rm -f alex/*.class alex/*~
	rm -f errors/*.class errors/*~
	rm -f output*

cleanjflex:
	rm -f alex/AnalizadorLexicoTiny.java

cleancup:
	rm -f asint/AnalizadorSintacticoTiny.java asint/ClaseLexica.java

cleanall: clean cleanjflex cleancup
