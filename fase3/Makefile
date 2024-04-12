memoriapdf:
	lowriter --convert-to pdf ../memoria.odt

zip: memoriapdf
	zip -r PLG15fase3.zip implementacion/ pruebas/ memoria.pdf

clean:
	rm -f PLG15fase3.zip memoria.pdf *~
	make -C implementacion clean
