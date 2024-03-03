build: clean cleanjflex jflex cleancup cup
	javac -cp java-cup-11b-20160615-3.jar alex/*.java asint/*.java errors/*.java

zip: clean cleanjflex cleancup
	zip -r PLG15Tiny0.zip asint/ alex/ errors/ Main.java

jflex:
	jflex alex/spec.jflex

CUPFLAGS = -parser AnalizadorSintacticoTiny -symbols ClaseLexica

cup:
	cup ${CUPFLAGS} asint/spec.cup
	mv AnalizadorSintacticoTiny.java asint/
	mv ClaseLexica.java asint/

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~
	rm -f alex/*.class alex/*~
	rm -f errors/*.class errors/*~

cleanjflex:
	rm -f alex/AnalizadorLexicoTiny.java

cleancup:
	rm -f asint/AnalizadorSintacticoTiny.java asint/ClaseLexica.java

cleanall: clean cleanjflex cleancup
