build: clean
	make -C implementacion_javacc
	make -C implementacion_cup
	javac Main.java

runasc: 
	for i in "1" "2" "3" "4"; do \
		echo "Probando con pruebas_tiny/input$$i.txt asc\n"; \
		java -cp .:implementacion_cup/java-cup-11b-20160615-3.jar Main pruebas_tiny/input$$i.txt asc; \
	done
	
rundesc:
	for i in "1" "2" "3" "4"; do \
		echo "Probando con pruebas_tiny/input$$i.txt desc\n"; \
		java -cp .:implementacion_cup/java-cup-11b-20160615-3.jar Main pruebas_tiny/input$$i.txt desc; \
	done

clean:
	make -C implementacion_javacc cleanall
	make -C implementacion_cup cleanall
	rm -f *.class
	rm -f *.zip
	rm -f memoria.pdf

zip: clean memoriapdf
	zip -r PLG15fase2.zip memoria.pdf implementacion* pruebas* Main.java Makefile

memoriapdf:
	lowriter --convert-to pdf ../memoria.odt

