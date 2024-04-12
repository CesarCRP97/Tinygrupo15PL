asint: cleanasint
	javac asint/*.java


jflex: asint cleanjflex
	jflex c_ast_ascendente/spec.jflex 

cleanjflex:
	rm -f c_ast_ascendente/AnalizadorLexicoTiny.java


cup: asint jflex cleancup
	cup -parser ConstructorASTTiny -symbols ClaseLexica -destdir c_ast_ascendente c_ast_ascendente/spec.cup

cleancup:
	rm -f c_ast_ascendente/ConstructorASTTiny.java
	rm -f c_ast_ascendente/ClaseLexica.java


javacc: asint jflex cup cleanjavacc
	java -cp javacc.jar javacc -OUTPUT_DIRECTORY=c_ast_descendente c_ast_descendente/spec.jj

cleanjavacc:
	rm -f c_ast_descendente/TokenMgrError.java
	rm -f c_ast_descendente/ParseException.java
	rm -f c_ast_descendente/Token.java
	rm -f c_ast_descendente/SimpleCharStream.java
	rm -f c_ast_descendente/ConstructorASTsTiny.java
	rm -f c_ast_descendente/ConstructorASTsTinyConstants.java
	rm -f c_ast_descendente/ConstructorASTsTinyTokenManager.java


build: clean asint jflex cup javacc
	javac -cp cup.jar: c_ast_ascendente/* c_ast_descendente/* procesamientos/*.java Main.java DomJudge.java

cleanasint:
	rm -f asint/*.class
	rm -f asint/*~

cleanasc: cleanjflex cleancup
	rm -f c_ast_ascendente/*.class
	rm -f c_ast_ascendente/*~

cleandesc: cleanjavacc
	rm -f c_ast_descendente/*.class
	rm -f c_ast_descendente/*~

cleanev:
	rm -f procesamientos/*.class
	rm -f procesamientos/*~

cleanrun:
	rm -f output*.txt

clean: cleanasint cleanasc cleandesc cleanev cleanrun
	rm -f *.class
	rm -f *~
	rm -f sample*.outh

runrecasc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt rec asc > output1recasc.txt
	diff output1recasc.txt ../pruebas/pruebas_tiny/output1.txt

runrecasc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt rec asc > output2recasc.txt
	diff output2recasc.txt ../pruebas/pruebas_tiny/output2.txt

runrecasc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt rec asc > output3recasc.txt
	diff output3recasc.txt ../pruebas/pruebas_tiny/output3.txt

runrecasc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt rec asc > output4recasc.txt
	diff output4recasc.txt ../pruebas/pruebas_tiny/output4.txt

runinterasc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt inter asc > output1interasc.txt
	diff output1interasc.txt ../pruebas/pruebas_tiny/output1.txt

runinterasc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt inter asc > output2interasc.txt
	diff output2interasc.txt ../pruebas/pruebas_tiny/output2.txt

runinterasc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt inter asc > output3interasc.txt
	diff output3interasc.txt ../pruebas/pruebas_tiny/output3.txt

runinterasc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt inter asc > output4interasc.txt
	diff output4interasc.txt ../pruebas/pruebas_tiny/output4.txt

runvisasc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt vis asc > output1visasc.txt
	diff output1visasc.txt ../pruebas/pruebas_tiny/output1.txt

runvisasc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt vis asc > output2visasc.txt
	diff output2visasc.txt ../pruebas/pruebas_tiny/output2.txt

runvisasc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt vis asc > output3visasc.txt
	diff output3visasc.txt ../pruebas/pruebas_tiny/output3.txt

runvisasc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt vis asc > output4visasc.txt
	diff output4visasc.txt ../pruebas/pruebas_tiny/output4.txt

runrecdesc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt rec desc > output1recdesc.txt
	diff output1recdesc.txt ../pruebas/pruebas_tiny/output1.txt

runrecdesc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt rec desc > output2recdesc.txt
	diff output2recdesc.txt ../pruebas/pruebas_tiny/output2.txt

runrecdesc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt rec desc > output3recdesc.txt
	diff output3recdesc.txt ../pruebas/pruebas_tiny/output3.txt

runrecdesc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt rec desc > output4recdesc.txt
	diff output4recdesc.txt ../pruebas/pruebas_tiny/output4.txt

runinterdesc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt inter desc > output1interdesc.txt
	diff output1interdesc.txt ../pruebas/pruebas_tiny/output1.txt

runinterdesc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt inter desc > output2interdesc.txt
	diff output2interdesc.txt ../pruebas/pruebas_tiny/output2.txt

runinterdesc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt inter desc > output3interdesc.txt
	diff output3interdesc.txt ../pruebas/pruebas_tiny/output3.txt

runinterdesc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt inter desc > output4interdesc.txt
	diff output4interdesc.txt ../pruebas/pruebas_tiny/output4.txt

runvisdesc1:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input1.txt vis desc > output1visdesc.txt
	diff output1visdesc.txt ../pruebas/pruebas_tiny/output1.txt

runvisdesc2:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input2.txt vis desc > output2visdesc.txt
	diff output2visdesc.txt ../pruebas/pruebas_tiny/output2.txt

runvisdesc3:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input3.txt vis desc > output3visdesc.txt
	diff output3visdesc.txt ../pruebas/pruebas_tiny/output3.txt

runvisdesc4:
	java -cp cup.jar:. Main ../pruebas/pruebas_tiny/input4.txt vis desc > output4visdesc.txt
	diff output4visdesc.txt ../pruebas/pruebas_tiny/output4.txt

runcasosprueba:
	for i in "1" "2" "3" "4" "5"; do \
		cat ../pruebas/casos_prueba_dj/sample"$$i"a.in | java -cp cup.jar: DomJudge > sample"$$i"a.outh; \
		cat ../pruebas/casos_prueba_dj/sample"$$i"d.in | java -cp cup.jar: DomJudge > sample"$$i"d.outh; \
	done;
