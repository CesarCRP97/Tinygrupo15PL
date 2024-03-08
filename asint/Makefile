build: clean
	make -C implementacion_javacc
	make -C implementacion_cup
	javac Main.java

runasc: 
	java -cp .:implementacion_cup/java-cup-11b-20160615-3.jar Main pruebas_tiny/input1.txt asc
	
rundesc:
	java -cp .:implementacion_cup/java-cup-11b-20160615-3.jar Main pruebas_tiny/input1.txt desc

clean:
	make -C implementacion_javacc cleanall
	make -C implementacion_cup cleanall
	rm -f *.class
	rm -f *.zip
	rm -f memoria.pdf

zip: clean memoriapdf
	zip -r PLG15fase2.zip memoria.pdf implementacion* pruebas* Main.java

memoriapdf:
	lowriter --convert-to pdf ../memoria.odt

