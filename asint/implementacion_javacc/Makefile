build: clean cleanalex alex javacc
	javac asint/Main.java

zip: clean cleanalex alex
	zip -r PLG15Tiny0.zip asint

javacc:
	javacc asint/Tiny.jj

clean:
	rm -f *.class *~ PLG15Tiny0.zip
	rm -f asint/*.class asint/*~
