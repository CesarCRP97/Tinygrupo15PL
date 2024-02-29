build: clean cleanalex alex
	javac asint/Main.java

domjudge: clean cleanalex alex
	javac asint/* DomJudge.java

zip: clean cleanalex alex
	zip -r PLG15Tiny0.zip alex asint errors

alex: cleanalex
	cp -r ../../alex/implementacion_manual/alex .

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~ errors/*.class errors/*~
	rm -f alex/*.class alex/*~

cleanalex:
	rm -rf alex

cleanall: clean cleanalex
