
build: clean analizador
	javac DomJudge.java

zip: clean
	zip -r PLG15Tiny.zip DomJudge.java alex ruta_jflex

analizador:
	jflex alex/spec.jflex

clean: 
	rm -f alex/AnalizadorLexicoTiny.java
	rm -f *.class *~ PLG15Tiny.zip
	rm -f alex/*.class alex/*~
	rm -f alex/resources/*~
