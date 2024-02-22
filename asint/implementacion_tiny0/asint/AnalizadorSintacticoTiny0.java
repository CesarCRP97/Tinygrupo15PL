package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoTiny0;
import alex.ClaseLexica;
import errors.GestionErroresTiny0;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoTiny0 {
   private UnidadLexica anticipo;       // token adelantado
   private AnalizadorLexicoTiny0 alex;   // analizador léxico 
   private GestionErroresTiny0 errores;  // gestor de errores
   private Set<ClaseLexica> esperados;  // clases léxicas esperadas
   
   public AnalizadorSintacticoTiny0(Reader input) {
        // se crea el gestor de errores
      errores = new GestionErroresTiny0();
        // se crea el analizador léxico
      alex = new AnalizadorLexicoTiny0(input);
        // se fija el gestor de errores en el analizador léxico
        // (debe añadirse el método 'fijaGestionErrores' a
        //  dicho analizador)
      alex.fijaGestionErrores(errores);
        // se crea el conjunto de clases léxicas esperadas
        // (estará incializado a vacío)
      esperados = EnumSet.noneOf(ClaseLexica.class);
        // Se lee el primer token adelantado
      sigToken();                      
   }
   public void analiza() {
      programa();
      empareja(ClaseLexica.EOF);
   }
   private void programa() {
       bloque();
   }

   private void bloque() {
        empareja(ClaseLexica.LLAVE_APERTURA);
        declaraciones();
        instrucciones();
        empareja(ClaseLexica.LLAVE_CIERRE);
   }
   
   //private void expresion() {
       empareja(ClaseLexica.EVALUA);
       E0();
   }
   
   private void declaraciones() {
       switch(anticipo.clase()) {
           case INT, REAL, BOOL:
                 lista_declaraciones();
                 empareja(ClaseLexica.DOBLEAMPERSAND);
                 break;
           default:        
                esperados(ClaseLexica.INT,ClaseLexica.REAL,ClaseLexica.BOOL);
                break;
       } 
   }
   
   //private void lista_declaraciones() {
       declaracion();
       rlista_decs();
   }
   
   //private void declaracion() {
       empareja(ClaseLexica.IDEN);
       empareja(ClaseLexica.IGUAL);
       E0();
   }    
   
   //private void rlista_decs() {
       switch(anticipo.clase()) {
           case COMA: 
               empareja(ClaseLexica.COMA);
               declaracion();
               rlista_decs();
               break;
           default:
              esperados(ClaseLexica.COMA);
              break;
       }
   }
   
   //private void E0() {
       E1();
       RE0();
   }
   
   //private void RE0() {
       switch(anticipo.clase()) {
           case MAS, MENOS: 
               OP0();
               E1();
               RE0();
               break;
           default: 
              esperados(ClaseLexica.MAS,ClaseLexica.MENOS);
              break;
       }    
    }
   

   //private void E1() {
       E2();
       RE1();
   }
   
   //private void RE1() {
       switch(anticipo.clase()) {
           case POR, DIV: 
               OP1();
               E2();
               RE1();
               break;
           default: 
              esperados(ClaseLexica.POR,ClaseLexica.DIV);
              break;
       }    
    }
   
   //private void E2() {
      switch(anticipo.clase()) {
          case ENT: empareja(ClaseLexica.ENT); break;
          case REAL: empareja(ClaseLexica.REAL); break; 
          case IDEN: empareja(ClaseLexica.IDEN); break;
          case PAP: 
               empareja(ClaseLexica.PAP); 
               E0(); 
               empareja(ClaseLexica.PCIERRE); 
               break;
          default:
              esperados(ClaseLexica.ENT,ClaseLexica.REAL,ClaseLexica.IDEN,ClaseLexica.PAP);
              error();
   }   
   }
   
   //private void OP0() {
     switch(anticipo.clase()) {
         case MAS: empareja(ClaseLexica.MAS); break;  
         case MENOS: empareja(ClaseLexica.MENOS); break;  
         default:    
              esperados(ClaseLexica.MAS,ClaseLexica.MENOS);             
              error();
     }  
   }
   //private void OP1() {
     switch(anticipo.clase()) {
         case POR: empareja(ClaseLexica.POR); break;  
         case DIV: empareja(ClaseLexica.DIV); break;  
         default:    
             esperados(ClaseLexica.POR,ClaseLexica.DIV);             
             error();
     }  
   }
       

   //private void esperados(ClaseLexica ... esperadas) {
       for(ClaseLexica c: esperadas) {
           esperados.add(c);
       }
   }
   
   
   //private void empareja(ClaseLexica claseEsperada) {
      if (anticipo.clase() == claseEsperada)
          sigToken();
      else {
          esperados(claseEsperada);
          error();
      }
   }
   //private void sigToken() {
      try {
        anticipo = alex.yylex();
        esperados.clear();
      }
      catch(IOException e) {
        errores.errorFatal(e);
      }
   }
   
    //private void error() {
        errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
    }
  
}
