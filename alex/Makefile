zip: clean memoriapdf
	zip -r PLg15fase1.zip memoria_lexico.pdf implementacion_manual/ implementacion_jflex/ pruebas_tiny_0/ pruebas_tiny/

memoriapdf:
	lowriter --convert-to pdf memoria_lexico.odt

clean: clean_manual clean_jflex
	rm -f PLg15fase1.zip *~ memoria_lexico.pdf

clean_manual:
	make -C implementacion_manual/ clean

clean_jflex:
	make -C implementacion_jflex/ clean
