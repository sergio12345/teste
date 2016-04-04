/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

/**
 *
 * @author Sergio O Gomes
 */
public class Dados {
    public String idSeq;
    public long tamanhoSequencia;
    public int janela;
    public int rep;
    public long pi;
    public long pf;
    public long gap;
    public String frag;

    // idSeq, tamanhoSequencia, janela, rep, pi, pf, gap, frag
    public Dados(String idSeq, long tamanhoSequencia, int janela, int rep, long pi, long pf, long gap, String frag ) {
        this.idSeq = idSeq;
        this.tamanhoSequencia = tamanhoSequencia;
        this.janela = janela;
        this.rep = rep;
        this.pi = pi;
        this.pf = pf;
        this.gap = gap;
        this.frag = frag;
    }
    //IDSequencia  + tamanhoSequencia " + janela +  temp.retorna_rep() +
                //temp.retorna_pi() + temp.retorna_pf() " + temp.retorna_gap()  + temp.retorna_info()
    public String toString() {
        return idSeq +"\t"+ tamanhoSequencia +"\t"+ janela +"\t"+ pi +"\t"+ pf +"\t"+ gap +"\t" + rep +"\t"+ frag;
    }
    
    public String toString2() {
        return idSeq +"\t"+ tamanhoSequencia;
    }

}