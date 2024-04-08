package asint;



public class SintaxisAbstractaTiny {

    public static abstract class Nodo {
       public Nodo() {
		   fila=col=-1;
       }   
	   private int fila;
	   private int col;
	   public Nodo ponFila(int fila) {
		    this.fila = fila;
            return this;			
	   }
	   public Nodo ponCol(int col) {
		    this.col = col;
            return this;			
	   }
	   public int leeFila() {
		  return fila; 
	   }
	   public int leeCol() {
		  return col; 
	   }
    }

     public static class Prog extends Nodo {
        private Bloque bloque;

        public Prog(Bloque bloque) {
            super();
            this.bloque = bloque;
        }
        public Bloque bloque() {return bloque;}
        public String toString() {
            return "prog("+bloque+")";
        }
    }

    public static class Bloque extends Nodo {
	   private Decs decs;
       private Instrs insts;

       public Bloque(Decs decs, Instrs insts) {
          super();
          this.decs = decs;
          this.insts = insts;
       }

       public Decs decs() {return decs;}
       public Instrs insts() {return insts;}

       public String toString() {
            return "bloque("+decs+","+insts+")";
        }
    }

    public static abstract class Decs extends Nodo {
       public Decs() {
       }
       public LDecs ldecs() {throw new UnsupportedOperationException();}

    }
    public static class Si_decs extends Decs {
       private LDecs decs; 
       public Si_decs(LDecs decs) {
          super();
          this.decs = decs;
       }   
        public String toString() {
            return "si_decs("+decs+")";
        } 
        public LDecs ldecs() {return decs;}
 
    }
    public static class No_decs extends Decs {
       public No_decs() {
          super();
       }   
       public String toString() {
            return "no_decs()";
        } 
    }

    public static abstract class LDecs extends Nodo {
       public LDecs() {
		   super();
       }
       public Dec dec() {throw new UnsupportedOperationException();}
       public LDecs ldecs() {throw new UnsupportedOperationException();}
    }
    	
    public static class Muchas_decs extends LDecs {
       private LDecs decs;
       private Dec dec;
       public Muchas_decs(LDecs decs, Dec dec) {
          super();
          this.dec = dec;
          this.decs = decs;
       }
       public LDecs ldecs() {return decs;}
       public Dec dec() {return dec;}
       public String toString() {
            return "muchas_decs("+decs+","+dec+")";
        } 
    }

    public static class Una_dec extends LDecs {
       private Dec dec;
       public Una_dec(Dec dec) {
          super();
          this.dec = dec;
       }
       public Dec dec() {return dec;}
       public String toString() {
            return "una_dec("+dec+")";
        } 
    }

    public static abstract class Dec extends Nodo {
        public Dec() {
            super();
        }
        public Tipo tipo() {throw new UnsupportedOperationException();}
        public String iden() {throw new UnsupportedOperationException();}
        public Bloque bloque() {throw new UnsupportedOperationException();}
        public Params_form params_form() {throw new UnsupportedOperationException();}
    }

    public static class Dec_variable extends Dec {
        private Tipo tipo;
        private String id;

        public Dec_variable(Tipo tipo, String id) {
            super();
            this.tipo = tipo;
            this.id = id;
        }

        public Tipo tipo() {return tipo;}
        public String iden() {return id;}
        public String toString() {
            return "dec_variable("+tipo+","+id+")";
        }
    }

    public static class Dec_tipo extends Dec {
        private Tipo tipo;
        private String id;

        public Dec_tipo(Tipo tipo, String id) {
            super();
            this.tipo = tipo;
            this.id = id;
        }

        public Tipo tipo() {return tipo;}
        public String iden() {return id;}
        public String toString() {
            return "dec_tipo("+tipo+","+id+")";
        }
    }

    public static class Dec_proc extends Dec {
        private String id;
        private Params_form params_form;
        private Bloque bloque;

        public Dec_proc(String id, Params_form params_form, Bloque bloque) {
            super();
            this.id = id;
            this.params_form = params_form;
            this.bloque = bloque;
        }

        public String iden() {return id;}
        public Params_form params_form() {return params_form;}
        public Bloque bloque() {return bloque;}

        public String toString() {
            return "dec_proc("+id+","+params_form+","+bloque+")";
        }
    }

    public static abstract class Params_form extends Nodo {
        public Params_form() {
            super();
        }

        public LParams_form lparams_form() {throw new UnsupportedOperationException();}
    }
    
    public static class Si_params_form extends Params_form {
        private LParams_form lparams_form;

        public Si_params_form(LParams_form lparams_form) {
            super();
            this.lparams_form = lparams_form;
        }

        public LParams_form lparams_form() {return lparams_form;}

        public String toString() {
            return "si_params_form("+lparams_form+")";
        }
    }

    public static class No_params_form extends Params_form {
        public No_params_form() {
            super();
        }

        public String toString() {
            return "no_params_form()";
        }
    }

    public static abstract class LParams_form extends Nodo {
        public LParams_form() {
            super();
        }

        public Param_form param_form() {throw new UnsupportedOperationException();}
        public LParams_form lparams_form() {throw new UnsupportedOperationException();}
    }

    public static class Muchos_params_form extends LParams_form {
        private LParams_form lparams_form;
        private Param_form param_form;

        public Muchos_params_form(LParams_form lparams_form, Param_form param_form) {
            super();
            this.lparams_form = lparams_form;
            this.param_form = param_form;
        }

        public LParams_form lparams_form() {return lparams_form;}
        public Param_form param_form() {return param_form;}

        public String toString() {
            return "muchos_params_form("+lparams_form+","+param_form+")";
        }
    }

    public static class Un_param_form extends LParams_form {
        private Param_form param_form;

        public Un_param_form(Param_form param_form) {
            super();
            this.param_form = param_form;
        }

        public Param_form param_form() {return param_form;}

        public String toString() {
            return "uno_param_form("+param_form+")";
        }
    }

    public static abstract class Param_form extends Nodo {
        public Param_form() {
            super();
        }

        public Tipo tipo() {throw new UnsupportedOperationException();}
        public String iden() {throw new UnsupportedOperationException();}
    }

    public static class Param_form_normal extends Param_form {
        private Tipo tipo;
        private String id;

        public Param_form_normal(Tipo tipo, String id) {
            super();
            this.tipo = tipo;
            this.id = id;
        }

        public Tipo tipo() {return tipo;}
        public String iden() {return id;}

        public String toString() {
            return "param_form_normal("+tipo+","+id+")";
        }
    }

    public static class Param_form_ref extends Param_form {
        private Tipo tipo;
        private String id;

        public Param_form_ref(Tipo tipo, String id) {
            super();
            this.tipo = tipo;
            this.id = id;
        }

        public Tipo tipo() {return tipo;}
        public String iden() {return id;}

        public String toString() {
            return "param_form_ref("+tipo+","+id+")";
        }
    }

    public static abstract class Tipo extends Nodo {
        public Tipo() {
            super();
        }

        public Tipo tipo() {throw new UnsupportedOperationException();}
        public String num() {throw new UnsupportedOperationException();}
        public LCampos campos() {throw new UnsupportedOperationException();}
        public String iden() {throw new UnsupportedOperationException();}
    }

    public static class Tipo_int extends Tipo {
        public Tipo_int() {
            super();
        }

        public String toString() {
            return "tipo_int()";
        }
    }

    public static class Tipo_real extends Tipo {
        public Tipo_real() {
            super();
        }

        public String toString() {
            return "tipo_real()";
        }
    }

    public static class Tipo_bool extends Tipo {
        public Tipo_bool() {
            super();
        }

        public String toString() {
            return "tipo_bool()";
        }
    }

    public static class Tipo_string extends Tipo {
        public Tipo_string() {
            super();
        }

        public String toString() {
            return "tipo_string()";
        }
    }

    public static class Tipo_array extends Tipo {
        private Tipo tipo;
        private String num;

        public Tipo_array(Tipo tipo, String num) {
            super();
            this.tipo = tipo;
            this.num = num;
        }

        public Tipo tipo() {return tipo;}
        public String num() {return num;}

        public String toString() {
            return "tipo_array("+tipo+","+num+")";
        }
    }

    public static class Tipo_puntero extends Tipo {
        private Tipo tipo;

        public Tipo_puntero(Tipo tipo) {
            super();
            this.tipo = tipo;
        }

        public Tipo tipo() {return tipo;}

        public String toString() {
            return "tipo_puntero("+tipo+")";
        }
    }

    public static class Tipo_struct extends Tipo {
        private LCampos campos;

        public Tipo_struct(LCampos campos) {
            super();
            this.campos = campos;
        }

        public LCampos campos() {return campos;}

        public String toString() {
            return "tipo_struct("+campos+")";
        }
    }

    public static class Tipo_iden extends Tipo {
        private String id;

        public Tipo_iden(String id) {
            super();
            this.id = id;
        }

        public String iden() {return id;}

        public String toString() {
            return "tipo_iden("+id+")";
        }
    }

    public static abstract class LCampos extends Nodo {
        public LCampos() {
            super();
        }

        public Campo campo() {throw new UnsupportedOperationException();}
        public LCampos lcampos() {throw new UnsupportedOperationException();}
    }

    public static class Muchos_campos extends LCampos {
        private LCampos lcampos;
        private Campo campo;

        public Muchos_campos(LCampos lcampos, Campo campo) {
            super();
            this.lcampos = lcampos;
            this.campo = campo;
        }

        public LCampos lcampos() {return lcampos;}
        public Campo campo() {return campo;}

        public String toString() {
            return "muchos_campos("+lcampos+","+campo+")";
        }
    }

    public static class Un_campo extends LCampos {
        private Campo campo;

        public Un_campo(Campo campo) {
            super();
            this.campo = campo;
        }

        public Campo campo() {return campo;}

        public String toString() {
            return "un_campo("+campo+")";
        }
    }

    public static class Campo extends Nodo {
        private Tipo tipo;
        private String id;

        public Campo(Tipo tipo, String id) {
            super();
            this.tipo = tipo;
            this.id = id;
        }

        public Tipo tipo() {return tipo;}
        public String iden() {return id;}

        public String toString() {
            return "campo("+tipo+","+id+")";
        }
    }

    public static abstract class Instrs extends Nodo {
        public Instrs() {
            super();
        }

        public LInstrs linstrs() {throw new UnsupportedOperationException();}
    }

    public static class Si_instrs extends Instrs {
        private LInstrs linstrs;

        public Si_instrs(LInstrs linstrs) {
            super();
            this.linstrs = linstrs;
        }

        public LInstrs linstrs() {return linstrs;}

        public String toString() {
            return "si_instrs("+linstrs+")";
        }
    }

    public static class No_instrs extends Instrs {
        public No_instrs() {
            super();
        }

        public String toString() {
            return "no_instrs()";
        }
    }

    public static abstract class LInstrs extends Nodo {
        public LInstrs() {
            super();
        }

        public Instr instr() {throw new UnsupportedOperationException();}
        public LInstrs linstrs() {throw new UnsupportedOperationException();}
    }

    public static class Muchas_instrs extends LInstrs {
        private LInstrs linstrs;
        private Instr instr;

        public Muchas_instrs(LInstrs linstrs, Instr instr) {
            super();
            this.linstrs = linstrs;
            this.instr = instr;
        }

        public LInstrs linstrs() {return linstrs;}
        public Instr instr() {return instr;}

        public String toString() {
            return "muchas_instrs("+linstrs+","+instr+")";
        }
    }

    public static class Una_instr extends LInstrs {
        private Instr instr;

        public Una_instr(Instr instr) {
            super();
            this.instr = instr;
        }

        public Instr instr() {return instr;}

        public String toString() {
            return "una_instr("+instr+")";
        }
    }

    public static abstract class Instr extends Nodo {
        public Instr() {
            super();
        }

        public String iden() {throw new UnsupportedOperationException();}
        public Exp exp() {throw new UnsupportedOperationException();}
        public Bloque bloque() {throw new UnsupportedOperationException();}
        public Bloque bloque1() {throw new UnsupportedOperationException();}
        public Bloque bloque2() {throw new UnsupportedOperationException();}
        public Params_reales params_reales() {throw new UnsupportedOperationException();}
    }

    public static class Eval extends Instr {
        private Exp exp;

        public Eval(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "eval("+exp+")";
        }
    }

    public static class If extends Instr {
        private Exp exp;
        private Bloque bloque;

        public If(Exp exp, Bloque bloque) {
            super();
            this.exp = exp;
            this.bloque = bloque;
        }

        public Exp exp() {return exp;}
        public Bloque bloque() {return bloque;}

        public String toString() {
            return "if("+exp+","+bloque+")";
        }
    }

    public static class IfElse extends Instr {
        private Exp exp;
        private Bloque bloque1;
        private Bloque bloque2;

        public IfElse(Exp exp, Bloque bloque1, Bloque bloque2) {
            super();
            this.exp = exp;
            this.bloque1 = bloque1;
            this.bloque2 = bloque2;
        }

        public Exp exp() {return exp;}
        public Bloque bloque1() {return bloque1;}
        public Bloque bloque2() {return bloque2;}

        public String toString() {
            return "if_else("+exp+","+bloque1+","+bloque2+")";
        }
    }

    public static class While extends Instr {
        private Exp exp;
        private Bloque bloque;

        public While(Exp exp, Bloque bloque) {
            super();
            this.exp = exp;
            this.bloque = bloque;
        }

        public Exp exp() {return exp;}
        public Bloque bloque() {return bloque;}

        public String toString() {
            return "while("+exp+","+bloque+")";
        }
    }

    public static class Read extends Instr {
        private Exp exp;

        public Read(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "read("+exp+")";
        }
    }

    public static class Write extends Instr {
        private Exp exp;

        public Write(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "write("+exp+")";
        }
    }

    public static class NL extends Instr {
        public NL() {
            super();
        }

        public String toString() {
            return "nl()";
        }
    }

    public static class New extends Instr {
        private Exp exp;

        public New(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "new("+exp+")";
        }
    }

    public static class Delete extends Instr {
        private Exp exp; 
        
        public Delete(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "delete("+exp+")";
        }
    }

    public static class Instr_compuesta extends Instr {
        private Bloque bloque;

        public Instr_compuesta(Bloque bloque) {
            super();
            this.bloque = bloque;
        }

        public Bloque bloque() {return bloque;}

        public String toString() {
            return "instr_compuesta("+bloque+")";
        }
    }

    public static class Invoc extends Instr {
        private String id;
        private Params_reales params_reales;

        public Invoc(String id, Params_reales params_reales) {
            super();
            this.id = id;
            this.params_reales = params_reales;
        }

        public String iden() {return id;}
        public Params_reales params_reales() {return params_reales;}

        public String toString() {
            return "invoc("+id+","+params_reales+")";
        }
    }

    public static abstract class Params_reales extends Nodo {
        public Params_reales() {
            super();
        }

        public LParams_reales lparams_reales() {throw new UnsupportedOperationException();}
    }

    public static class Si_params_reales extends Params_reales {
        private LParams_reales lparams_reales;

        public Si_params_reales(LParams_reales lparams_reales) {
            super();
            this.lparams_reales = lparams_reales;
        }

        public LParams_reales lparams_reales() {return lparams_reales;}

        public String toString() {
            return "si_params_reales("+lparams_reales+")";
        }
    }

    public static class No_params_reales extends Params_reales {
        public No_params_reales() {
            super();
        }

        public String toString() {
            return "no_params_reales()";
        }
    }

    public static abstract class LParams_reales extends Nodo {
        public LParams_reales() {
            super();
        }

        public Exp exp() {throw new UnsupportedOperationException();}
        public LParams_reales lparams_reales() {throw new UnsupportedOperationException();}
    }

    public static class Muchos_params_reales extends LParams_reales {
        private LParams_reales lparams_reales;
        private Exp exp;

        public Muchos_params_reales(LParams_reales lparams_reales, Exp exp) {
            super();
            this.lparams_reales = lparams_reales;
            this.exp = exp;
        }

        public LParams_reales lparams_reales() {return lparams_reales;}
        public Exp exp() {return exp;}

        public String toString() {
            return "muchos_params_reales("+lparams_reales+","+exp+")";
        }
    }

    public static class Un_param_real extends LParams_reales {
        private Exp exp;

        public Un_param_real(Exp exp) {
            super();
            this.exp = exp;
        }

        public Exp exp() {return exp;}

        public String toString() {
            return "un_param_real("+exp+")";
        }
    }


    public static abstract class Exp extends  Nodo {
       public Exp() {
		   super();
       }   
       public String iden() {throw new UnsupportedOperationException();}
       public String valor() {throw new UnsupportedOperationException();}
       public Exp opnd0() {throw new UnsupportedOperationException();}
       public Exp opnd1() {throw new UnsupportedOperationException();}
    }
   
    
    private static abstract class ExpBin extends Exp {
        protected Exp opnd0;
        protected Exp opnd1;
        public ExpBin(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}

    }

    public static class Asignacion extends ExpBin {
        public Asignacion(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "asignacion("+opnd0+","+opnd1+")";
        } 
    }

    public static class Igual_comp extends ExpBin {
        public Igual_comp(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "igual_comp("+opnd0+","+opnd1+")";
        } 
    }

    public static class Distinto_comp extends ExpBin {
        public Distinto_comp(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "distinto_comp("+opnd0+","+opnd1+")";
        } 
    }

    public static class Menor_que extends ExpBin {
        public Menor_que(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "menor_que("+opnd0+","+opnd1+")";
        } 
    }

    public static class Mayor_que extends ExpBin {
        public Mayor_que(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "mayor_que("+opnd0+","+opnd1+")";
        } 
    }

    public static class Menor_igual extends ExpBin {
        public Menor_igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "menor_igual("+opnd0+","+opnd1+")";
        } 
    }

    public static class Mayor_igual extends ExpBin {
        public Mayor_igual(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "mayor_igual("+opnd0+","+opnd1+")";
        } 
    }
            
    public static class Suma extends ExpBin {
        public Suma(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "suma("+opnd0+","+opnd1+")";
        } 
    }
    public static class Resta extends ExpBin {
        public Resta(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "resta("+opnd0+","+opnd1+")";
        } 
    }

    public static class And extends ExpBin {
        public And(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "and("+opnd0+","+opnd1+")";
        } 
    }

    public static class Or extends ExpBin {
        public Or(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "or("+opnd0+","+opnd1+")";
        } 
    }

    public static class Mul extends ExpBin {
        public Mul(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "mul("+opnd0+","+opnd1+")";
        } 
    }

    public static class Div extends ExpBin {
        public Div(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "div("+opnd0+","+opnd1+")";
        } 
    }

    public static class Mod extends ExpBin {
        public Mod(Exp opnd0, Exp opnd1) {
            super(opnd0,opnd1);
        }
        public String toString() {
            return "mod("+opnd0+","+opnd1+")";
        } 
    }

    public static class Menos_unario extends Exp {
        private Exp opnd;
        public Menos_unario(Exp opnd) {
            super();
            this.opnd = opnd;
        }
        public Exp opnd0() {return opnd;}
        public String toString() {
            return "menos_unario("+opnd+")";
        } 
    }

    public static class Not extends Exp {
        private Exp opnd;
        public Not(Exp opnd) {
            super();
            this.opnd = opnd;
        }
        public Exp opnd0() {return opnd;}
        public String toString() {
            return "not("+opnd+")";
        } 
    }

    public static class Indexacion extends Exp {
        private Exp opnd0;
        private Exp opnd1;
        public Indexacion(Exp opnd0, Exp opnd1) {
            super();
            this.opnd0 = opnd0;
            this.opnd1 = opnd1;
        }
        public Exp opnd0() {return opnd0;}
        public Exp opnd1() {return opnd1;}
        public String toString() {
            return "indexacion("+opnd0+","+opnd1+")";
        } 
    }

    public class Acceso extends Exp {
        private Exp opnd;
        private String id;
        public Acceso(Exp opnd, String id) {
            super();
            this.opnd = opnd;
            this.id = id;
        }
        public Exp opnd0() {return opnd;}
        public String iden() {return id;}
        public String toString() {
            return "acceso("+opnd+","+id+")";
        } 
    }

    public static class Indireccion extends Exp {
        private Exp opnd;
        public Indireccion(Exp opnd) {
            super();
            this.opnd = opnd;
        }
        public Exp opnd0() {return opnd;}
        public String toString() {
            return "indireccion("+opnd+")";
        } 
    }
    
    public static class Lit_ent extends Exp {
        private String num;
        public Lit_ent(String num) {
            super();
            this.num = num;
        }
        public String valor() {return num;}
        public String toString() {
            return "lit_ent("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class Lit_real extends Exp {
        private String num;
        public Lit_real(String num) {
            super();
            this.num = num;
        }
        public String valor() {return num;}
        public String toString() {
            return "lit_real("+num+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class True extends Exp {
        public True() {
            super();
        }
        public String toString() {
            return "true()";
        } 
    }

    public static class False extends Exp {
        public False() {
            super();
        }
        public String toString() {
            return "false()";
        } 
    }

    public static class Lit_cadena extends Exp {
        private String cadena;
        public Lit_cadena(String cadena) {
            super();
            this.cadena = cadena;
        }
        public String valor() {return cadena;}
        public String toString() {
            return "lit_cadena("+cadena+"["+leeFila()+","+leeCol()+"])";
        } 
    }
	
    public static class Iden extends Exp {
        private String id;
        public Iden(String id) {
            super();
            this.id = id;
        }
        public String iden() {return id;}
        public String toString() {
            return "iden("+id+"["+leeFila()+","+leeCol()+"])";
        } 
    }

    public static class Null extends Exp {
        public Null() {
            super();
        }
        public String toString() {
            return "null()";
        }
    }



     // Constructoras    
    //
    public Prog prog(Bloque bloque) {
        return new Prog(bloque);
    }
    public Bloque bloque(Decs decs, Instrs insts) {
        return new Bloque(decs,insts);
    }
    public Decs si_decs(LDecs decs) {
        return new Si_decs(decs);
    }
    public Decs no_decs() {
        return new No_decs();
    }
    public LDecs muchas_decs(LDecs decs, Dec dec) {
        return new Muchas_decs(decs,dec);
    }
    public LDecs una_dec(Dec dec) {
        return new Una_dec(dec);
    }
    public Dec dec_variable(Tipo tipo, String id) {
        return new Dec_variable(tipo,id);
    }
    public Dec dec_tipo(Tipo tipo, String id) {
        return new Dec_tipo(tipo,id);
    }
    public Dec dec_proc(String id, Params_form params_form, Bloque bloque) {
        return new Dec_proc(id,params_form,bloque);
    }
    public Params_form si_params_form(LParams_form params_form) {
        return new Si_params_form(params_form);
    }
    public Params_form no_params_form() {
        return new No_params_form();
    }
    public LParams_form muchos_params_form(LParams_form params_form, Param_form param_form) {
        return new Muchos_params_form(params_form,param_form);
    }
    public LParams_form un_param_form(Param_form param_form) {
        return new Un_param_form(param_form);
    }
    public Param_form param_form_normal(Tipo tipo, String id) {
        return new Param_form_normal(tipo,id);
    }
    public Param_form param_form_ref(Tipo tipo, String id) {
        return new Param_form_ref(tipo,id);
    }
    public Tipo tipo_int() {
        return new Tipo_int();
    }
    public Tipo tipo_real() {
        return new Tipo_real();
    }
    public Tipo tipo_bool() {
        return new Tipo_bool();
    }
    public Tipo tipo_string() {
        return new Tipo_string();
    }
    public Tipo tipo_array(Tipo tipo, String num) {
        return new Tipo_array(tipo,num);
    }
    public Tipo tipo_puntero(Tipo tipo) {
        return new Tipo_puntero(tipo);
    }
    public Tipo tipo_struct(LCampos campos) {
        return new Tipo_struct(campos);
    }
    public Tipo tipo_iden(String id) {
        return new Tipo_iden(id);
    }
    public LCampos muchos_campos(LCampos campos, Campo campo) {
        return new Muchos_campos(campos,campo);
    }
    public LCampos un_campo(Campo campo) {
        return new Un_campo(campo);
    }
    public Campo campo(Tipo tipo, String id) {
        return new Campo(tipo,id);
    }
    public Instrs si_instrs(LInstrs instrs) {
        return new Si_instrs(instrs);
    }
    public Instrs no_instrs() {
        return new No_instrs();
    }
    public LInstrs muchas_instrs(LInstrs instrs, Instr instr) {
        return new Muchas_instrs(instrs,instr);
    }
    public LInstrs una_instr(Instr instr) {
        return new Una_instr(instr);
    }
    public Instr eval(Exp exp) {
        return new Eval(exp);
    }
    public Instr if_(Exp exp, Bloque bloque) {
        return new If(exp,bloque);
    }
    public Instr if_else(Exp exp, Bloque bloque1, Bloque bloque2) {
        return new IfElse(exp,bloque1,bloque2);
    }
    public Instr while_(Exp exp, Bloque bloque) {
        return new While(exp,bloque);
    }
    public Instr read(Exp exp) {
        return new Read(exp);
    }
    public Instr write(Exp exp) {
        return new Write(exp);
    }
    public Instr nl() {
        return new NL();
    }
    public Instr new_(Exp exp) {
        return new New(exp);
    }
    public Instr delete(Exp exp) {
        return new Delete(exp);
    }
    public Instr instr_compuesta(Bloque bloque) {
        return new Instr_compuesta(bloque);
    }
    public Instr invoc(String id, Params_reales params_reales) {
        return new Invoc(id,params_reales);
    }
    public Params_reales si_params_reales(LParams_reales params_reales) {
        return new Si_params_reales(params_reales);
    }
    public Params_reales no_params_reales() {
        return new No_params_reales();
    }
    public LParams_reales muchos_params_reales(LParams_reales params_reales, Exp exp) {
        return new Muchos_params_reales(params_reales,exp);
    }
    public LParams_reales un_param_real(Exp exp) {
        return new Un_param_real(exp);
    }
    public Exp asignacion(Exp opnd0, Exp opnd1) {
        return new Asignacion(opnd0,opnd1);
    }
    public Exp igual_comp(Exp opnd0, Exp opnd1) {
        return new Igual_comp(opnd0,opnd1);
    }
    public Exp distinto_comp(Exp opnd0, Exp opnd1) {
        return new Distinto_comp(opnd0,opnd1);
    }
    public Exp menor_que(Exp opnd0, Exp opnd1) {
        return new Menor_que(opnd0,opnd1);
    }
    public Exp mayor_que(Exp opnd0, Exp opnd1) {
        return new Mayor_que(opnd0,opnd1);
    }
    public Exp menor_igual(Exp opnd0, Exp opnd1) {
        return new Menor_igual(opnd0,opnd1);
    }
    public Exp mayor_igual(Exp opnd0, Exp opnd1) {
        return new Mayor_igual(opnd0,opnd1);
    }
    public Exp suma(Exp opnd0, Exp opnd1) {
        return new Suma(opnd0,opnd1);
    }
    public Exp resta(Exp opnd0, Exp opnd1) {
        return new Resta(opnd0,opnd1);
    }
    public Exp and(Exp opnd0, Exp opnd1) {
        return new And(opnd0,opnd1);
    }
    public Exp or(Exp opnd0, Exp opnd1) {
        return new Or(opnd0,opnd1);
    }
    public Exp mul(Exp opnd0, Exp opnd1) {
        return new Mul(opnd0,opnd1);
    }
    public Exp div(Exp opnd0, Exp opnd1) {
        return new Div(opnd0,opnd1);
    }
    public Exp mod(Exp opnd0, Exp opnd1) {
        return new Mod(opnd0,opnd1);
    }
    public Exp menos_unario(Exp opnd) {
        return new Menos_unario(opnd);
    }
    public Exp not(Exp opnd) {
        return new Not(opnd);
    }
    public Exp indexacion(Exp opnd0, Exp opnd1) {
        return new Indexacion(opnd0,opnd1);
    }
    public Exp acceso(Exp opnd, String id) {
        return new Acceso(opnd,id);
    }
    public Exp indireccion(Exp opnd) {
        return new Indireccion(opnd);
    }
    public Exp lit_ent(String num) {
        return new Lit_ent(num);
    }
    public Exp lit_real(String num) {
        return new Lit_real(num);
    }
    public Exp true_() {
        return new True();
    }
    public Exp false_() {
        return new False();
    }
    public Exp lit_cadena(String cadena) {
        return new Lit_cadena(cadena);
    }
    public Exp iden(String id) {
        return new Iden(id);
    }
    public Exp null_() {
        return new Null();
    }
   }
