build: clean cleanjavacc javacc
	javac asint/Main.java

zip: clean cleanjavacc
	zip -r PLG15Tiny0.zip asint

javacc:
	cd asint; javacc Tiny.jj

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~

cleanjavacc:
	rm -f asint/Token.java asint/AnalizadorSintacticoTinyTokenManager.java asint/AnalizadorSintacticoTinyConstants.java asint/ParseException.java asint/TokenMgrError.java asint/AnalizadorSintacticoTiny.java asint/SimpleCharStream.java
