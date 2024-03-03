build: clean cleanjflex jflex cleancup cup
	javac Main.java

zip: clean cleanjflex cleancup
	zip -r PLG15Tiny0.zip asint/ alex/ errors/ Main.java

jflex:
	jflex alex/spec.jflex

CUPFLAGS = -parser asint/AnalizadorSintacticoTiny -symbols alex/ClaseLexica

cup:
	cup ${CUPFLAGS} asint/spec.cup

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~
	rm -f alex/*.class alex/*~
	rm -f errors/*.class errors/*~

cleanjflex:
	rm -f alex/AnalizadorLexicoTiny.java

cleancup:
	rm -f asint/AnalizadorSintacticoTiny.java alex/ClaseLexica.java

cleanall: clean cleanjflex cleancup
