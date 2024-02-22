build: clean alex
	javac asint/Main.java

zip: clean
	zip -r PLG15Tiny0.zip alex asint errors

alex: cleanalex
	cp -r ../../alex/implementacion_manual/alex .

clean: cleanalex
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~ errors/*.class errors/*~

cleanalex:
	rm -rf alex
