build: clean cleanjavacc javacc
	javac asint/Main.java

domjudge: clean cleanjavacc javacc 
	javac DomJudge.java

debug: clean cleanjavacc javacc-debug
	javac asint/Main.java

zip: clean cleanjavacc
	zip -r PLG15TinyJavacc.zip asint ruta_javacc DomJudge.java

javacc:
	java -cp javacc.jar javacc -output_directory=asint/ asint/Tiny.jj

javacc-debug:
	java -cp javacc.jar javacc -debug_parser -output_directory=asint/ asint/Tiny.jj

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~

cleanjavacc:
	rm -f asint/Token.java asint/AnalizadorSintacticoTinyTokenManager.java asint/AnalizadorSintacticoTinyConstants.java asint/ParseException.java asint/TokenMgrError.java asint/AnalizadorSintacticoTiny.java asint/SimpleCharStream.java

cleanall: clean cleanjavacc
