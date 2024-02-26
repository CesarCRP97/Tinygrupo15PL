build: clean cleanalex alex javacc
	javac asint/Main.java

zip: clean cleanalex alex
	zip -r PLG15Tiny0.zip alex asint

alex: cleanalex
	cp -r ../../alex/implementacion_manual/alex .

javacc:
	java javacc asint/Tiny.jj

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~
	rm -f alex/*.class alex/*~

cleanalex:
	rm -rf alex
