build: clean cleanjavacc javacc
	javac asint/*.java

domjudge: clean cleanjavacc javacc 
	javac asint/*.java DomJudge.java

debug: clean cleanjavacc javacc-debug
	javac asint/Main.java

zip: clean cleanjavacc
	zip -r PLG15TinyJavacc.zip asint ruta_javacc DomJudge.java

javacc:
	java -cp javacc.jar javacc -output_directory=asint/ asint/spec.jj

javacc-debug:
	java -cp javacc.jar javacc -debug_parser -output_directory=asint/ asint/spec.jj

clean:
	rm -f *.class *~ PLG15TinyJavacc.zip
	rm -f asint/*.class asint/*~

cleanjavacc:
	rm -f asint/Token.java asint/AnalizadorSintacticoTinyTokenManager.java asint/AnalizadorSintacticoTinyConstants.java asint/ParseException.java asint/TokenMgrError.java asint/AnalizadorSintacticoTiny.java asint/SimpleCharStream.java

cleanall: clean cleanjavacc
