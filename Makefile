zip1: clean memoriapdf
	zip -r PLg15fase1.zip memoria_lexico.pdf alex/implementacion_manual/ alex/implementacion_jflex/ alex/pruebas_tiny_0/ alex/pruebas_tiny/

memoriapdf:
	lowriter --convert-to pdf memoria.odt

clean: clean_manual clean_jflex
	rm -f PLg15fase1.zip PLg15fase2.zip *~ memoria.pdf

clean_manual:
	make -C alex/implementacion_manual/ clean

clean_jflex:
	make -C alex/implementacion_jflex/ clean
