memoriapdf:
	lowriter --convert-to pdf ../memoria.odt

zip: memoriapdf
	zip -r PLG15fase4.zip implementacion/ pruebas/ memoria.pdf

clean:
	rm -f PLG15fase4.zip memoria.pdf *~
	make -C implementacion clean
	rm -f pruebas/pruebas_tiny/*~
	rm -f pruebas/casos_basicos/*~
	rm -f pruebas/casos_elaborados/*~
	rm -f pruebas/casos_errores/*~
