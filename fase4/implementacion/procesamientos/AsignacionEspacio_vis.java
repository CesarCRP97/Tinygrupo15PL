package procesamientos;

import asint.SintaxisAbstractaTiny;
import asint.SintaxisAbstractaTiny.*;
import asint.ProcesamientoDef;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

public class AsignacionEspacio_vis extends ProcesamientoDef {

    public class ErrorAsignacionEspacio extends RuntimeException {
        private void avisoError(Nodo n) {
            System.out.println("Error de tipos en la línea " + n.leeFila() + " y columna " + n.leeCol() + " en nodo "
                    + n.toString());
            // System.out.println("El nodo es: " + n.toString());
        }
    }

    private int dir;
    private int nivel;
    private int desp;
    private int maxTamNivel;
    private int maxNivel;

    public int getMaxTamNivel() {
        return this.maxTamNivel;
    }

    public int getMaxNivel() {
        return this.maxNivel;
    }

    public AsignacionEspacio_vis() {
        this.dir = 0;
        this.nivel = 0;
        this.maxTamNivel = 0;//El tamaño máximo que puede tener un nivel
        this.maxNivel = 0;//El nivel máximo que se ha alcanzado
    }

    public void inc_dir(int inc) {
        this.dir = this.dir + inc;
        if (this.dir > this.maxTamNivel) {
            this.maxTamNivel = this.dir;
        }
    }

    public void procesa(Prog p) {
        p.bloque().procesa(this);
    }

    public void procesa(Bloque b) {
        b.decs().procesa1(this);
        b.decs().procesa2(this);
        b.insts().procesa(this);
    }

    public void procesa1(Si_decs decs) {
        decs.ldecs().procesa1(this);
    }

    public void procesa1(No_decs decs) {
    }

    public void procesa1(Muchas_decs decs) {
        decs.ldecs().procesa1(this);
        decs.dec().procesa1(this);
    }

    public void procesa1(Una_dec dec) {
        dec.dec().procesa1(this);
    }

    public void procesa2(Si_decs decs) {
        decs.ldecs().procesa2(this);
    }

    public void procesa2(No_decs decs) {
    }

    public void procesa2(Muchas_decs decs) {
        decs.ldecs().procesa2(this);
        decs.dec().procesa2(this);
    }

    public void procesa2(Una_dec dec) {
        dec.dec().procesa2(this);
    }

    public void procesa1(Dec_variable dec) {
        dec.tipo().procesa1(this);
        dec.setDir(this.dir);
        dec.setNivel(this.nivel);
        inc_dir(dec.tipo().getTam());
    }

    public void procesa2(Dec_variable dec) {
        dec.tipo().procesa2(this);
    }

    public void procesa1(Dec_tipo dec) {
        dec.tipo().procesa1(this);
    }

    public void procesa2(Dec_tipo dec) {
        dec.tipo().procesa2(this);
    }

    public void procesa1(Dec_proc dec) {
        int dirAnterior = this.dir;

        this.nivel++;
        if (this.nivel > this.maxNivel) {
            this.maxNivel = this.nivel;
        }
        
        dec.setNivel(this.nivel);
        this.dir = 0;

        dec.params_form().procesa1(this);
        dec.params_form().procesa2(this);
        dec.bloque().procesa(this);

        dec.setTam(this.dir);
        this.nivel--;
        this.dir = dirAnterior;
    }

    public void procesa1(Si_params_form params) {
        params.lparams_form().procesa1(this);
    }

    public void procesa1(Muchos_params_form params) {
        params.lparams_form().procesa1(this);
        params.param_form().procesa1(this);
    }

    public void procesa1(Un_param_form params) {
        params.param_form().procesa1(this);
    }

    public void procesa1(Param_form_normal param) {
        param.setDir(this.dir);
        param.setNivel(this.nivel);
        param.tipo().procesa1(this);
        this.inc_dir(param.tipo().getTam());
    }

    public void procesa1(Param_form_ref param) {
        param.setDir(this.dir);
        param.setNivel(this.nivel);
        param.tipo().procesa1(this);
        this.inc_dir(1);
    }

    public void procesa1(Tipo_int tipo) {
        tipo.setTam(1);
    }

    public void procesa2(Tipo_int tipo) {
    }

    public void procesa1(Tipo_real tipo) {
        tipo.setTam(1);
    }

    public void procesa2(Tipo_real tipo) {
    }

    public void procesa2(Tipo_bool tipo) {
        tipo.setTam(1);
    }

    public void procesa1(Tipo_bool tipo) {
    }

    public void procesa1(Tipo_string tipo) {
        tipo.setTam(1);
    }

    public void procesa2(Tipo_string tipo) {
    }

    public void procesa1(Tipo_array tipo) {
        tipo.tipo().procesa1(this);
        tipo.setTam(tipo.dim() * tipo.tipo().getTam());
    }

    public void procesa2(Tipo_array tipo) {
        tipo.tipo().procesa2(this);
    }

    public void procesa1(Tipo_puntero tipo) {
        tipo.setTam(1);
        if (!(tipo.tipo() instanceof Tipo_iden)) {
            tipo.tipo().procesa1(this);
        }
    }

    public void procesa2(Tipo_puntero tipo) {
        if (tipo.tipo() instanceof Tipo_iden) {
            Dec_tipo dec = (Dec_tipo) tipo.tipo().vinculo();
            dec.tipo().procesa2(this);
            tipo.tipo().setTam(dec.tipo().getTam());
        } else {
            tipo.tipo().procesa2(this);
        }
    }

    public void procesa1(Tipo_iden tipo) {
        tipo.vinculo().procesa1(this);
        Dec_tipo dec = (Dec_tipo) tipo.vinculo();
        tipo.setTam(dec.tipo().getTam());
    }

    public void procesa2(Tipo_iden tipo) {
    }

    public void procesa1(Tipo_struct tipo) {
        tipo.campos().procesa1(this);
        tipo.setTam(tipo.campos().getTam());
        desp = 0;
    }

    public void procesa1(Campos campos) {
        campos.lcampos().procesa1(this);
        campos.setTam(campos.lcampos().getTam());
    }

    public void procesa1(Muchos_campos campos) {
        campos.lcampos().procesa1(this);
        campos.campo().procesa1(this);
        campos.setTam(campos.lcampos().getTam() + campos.campo().getTam());
    }

    public void procesa1(Un_campo campo) {
        campo.campo().procesa1(this);
        campo.setTam(campo.campo().getTam());
    }

    public void procesa1(Campo campo) {
        campo.tipo().procesa1(this);
        campo.setTam(campo.tipo().getTam());
        campo.setDesp(desp);
        desp += campo.tipo().getTam();
    }

    public void procesa2(Tipo_struct tipo) {
        tipo.campos().procesa2(this);
    }

    public void procesa2(Campos campos) {
        campos.lcampos().procesa2(this);
    }

    public void procesa2(Muchos_campos campos) {
        campos.lcampos().procesa2(this);
        campos.campo().procesa2(this);
    }

    public void procesa2(Un_campo campo) {
        campo.campo().procesa2(this);
    }

    public void procesa2(Campo campo) {
        campo.tipo().procesa2(this);
    }

    public void procesa(Si_instrs instrs) {
        instrs.linstrs().procesa(this);
    }

    public void procesa(No_instrs instrs) {
    }

    public void procesa(Muchas_instrs instrs) {
        instrs.linstrs().procesa(this);
        instrs.instr().procesa(this);
    }

    public void procesa(Una_instr instr) {
        instr.instr().procesa(this);
    }

    public void procesa(If instr) {
        int dirAnterior = this.dir;
        instr.bloque().procesa(this);
        this.dir = dirAnterior;
    }

    public void procesa(IfElse instr) {
        int dirAnterior = this.dir;
        instr.bloque1().procesa(this);
        this.dir = dirAnterior;
        dirAnterior = this.dir;
        instr.bloque2().procesa(this);
        this.dir = dirAnterior;
    }

    public void procesa(While instr) {
        int dirAnterior = this.dir;
        instr.bloque().procesa(this);
        this.dir = dirAnterior;
    }

    public void procesa(Instr_compuesta instr) {
        int dirAnterior = this.dir;
        instr.bloque().procesa(this);
        this.dir = dirAnterior;
    }

    // ---------------------------------------------------------------------------------------------------

    public void procesa(Eval instr) {
    }

    public void procesa(Read instr) {
    }

    public void procesa(Write instr) {
    }

    public void procesa(NL instr) {
    }

    public void procesa(New instr) {
    }

    public void procesa(Delete instr) {
    }

    public void procesa(Invoc instr) {
    }

    public void procesa(Asignacion exp) {
    }

    public void procesa(Igual_comp exp) {
    }

    public void procesa(Distinto_comp exp) {
    }

    public void procesa(Menor_que exp) {
    }

    public void procesa(Mayor_que exp) {
    }

    public void procesa(Menor_igual exp) {
    }

    public void procesa(Mayor_igual exp) {
    }

    public void procesa(Suma exp) {
    }

    public void procesa(Resta exp) {
    }

    public void procesa(And exp) {
    }

    public void procesa(Or exp) {
    }

    public void procesa(Mul exp) {
    }

    public void procesa(Div exp) {
    }

    public void procesa(Mod exp) {
    }

    public void procesa(Menos_unario exp) {
    }

    public void procesa(Not exp) {
    }

    public void procesa(Indexacion exp) {
    }

    public void procesa(Acceso exp) {
    }

    public void procesa(Indireccion exp) {
    }

    public void procesa(Lit_ent exp) {
    }

    public void procesa(Lit_real exp) {
    }

    public void procesa(True exp) {
    }

    public void procesa(False exp) {
    }

    public void procesa(Lit_cadena exp) {
    }

    public void procesa(Iden exp) {
    }

    public void procesa(Null exp) {
    }

    public void procesa(String str) {
    }
}
