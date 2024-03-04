zipalex: clean memoriapdf
	zip -r PLg15fase1.zip memoria.pdf alex/implementacion_manual/ alex/implementacion_jflex/ alex/pruebas_tiny_0/ alex/pruebas_tiny/

zipasint: clean memoriapdf
	zip -r PLg15fase2.zip memoria.pdf asint/implementacion* asint/pruebas_tiny_0/ asint/pruebas_tiny/

memoriapdf:
	lowriter --convert-to pdf memoria.odt

clean: clean_asint clean_alex
	rm -f PLg15fase1.zip PLg15fase2.zip *~ memoria.pdf

clean_asint:
	make -C asint/implementacion_tiny0 cleanall
	make -C asint/implementacion_javacc cleanall
	make -C asint/implementacion_cup cleanall


clean_alex:
	make -C alex/implementacion_manual clean
	make -C alex/implementacion_jflex clean
