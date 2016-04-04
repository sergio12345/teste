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
public class No {
	public String frag;
	public long pi;
	public long pf;
	public int rep;
        public long gap;
	public No prox;
	
	public No(){
		frag = "";
		prox = null;
		pi = 0;
		pf = 0;
                gap = 0;
		rep = 0;
	}
	
	public No( String i, No p, long pos_i, long pos_f ){
		frag = i;
		prox = p;
		pi = pos_i;
		pf = pos_f;
		rep = 1;
                gap = 0;
	}
	
	public String retorna_info(){ return frag; }
	public void altera_prox( No p ){ prox = p; }
	public No retorna_prox(){ return prox; }
	
	public long retorna_pi(){ return pi; }
	public long retorna_pf(){ return pf; }
	public int retorna_rep(){ return rep; }
        public long retorna_gap(){ return gap; }
	
	public void altera_pi( long i ){ pi = i; }
	public void altera_pf( long f ){ pf = f; }
	public void altera_rep(){ rep++; }
        public void altera_gap( long g ){ gap += g; }
	public void zera_rep(){ rep=1; }
        public void zera_gap(){ gap=0; }
}