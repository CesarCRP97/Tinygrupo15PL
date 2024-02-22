build: alex
	javac asint/Main.java

zip: clean
	zip -r PLG15Tiny0.zip alex asint errors

alex: clean
	cp -r ../../alex/implementacion_manual/alex .

clean: 
	rm -f *.class *~ PLG15Tiny0.zip
	rm -rf alex
	rm -f asint/*.class asint/*~ errors/*.class errors/*~

