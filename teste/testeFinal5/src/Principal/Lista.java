/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author Sergio O Gomes
 */
public class Lista {

    public No cabeca;
    public No fim;
    public int elementos; // quantidade de nos
    public String id;

    public Lista(){
	cabeca = null;
        fim = null;
        elementos = 0;
    }

    public void insere( String a, long pos_i, long pos_f, int r, int g ){
	if( cabeca == null ){
            cabeca = new No( a, null, pos_i, pos_f );
            fim = cabeca;
            elementos++;
            //System.out.printf("NULL\n");
	}else{
            // verifica se possui as repeticoes minimas
            if( fim.retorna_rep() < r ){
                // verifica se o gap esta dentro do permitido
                // caso nao, o no atual substitui o anterior
		if(  (pos_i - fim.retorna_pf() >= 1) && (pos_i - fim.retorna_pf() <= (1+g)) ){
                    fim.altera_rep();
                    fim.altera_gap( (long)(pos_i - fim.retorna_pf() -1) );
                    fim.altera_pf( pos_f );
		}else{
                    // substitui o ultimo no
                    if((pos_i - fim.retorna_pf()) > (1+g)){
                        fim.zera_rep();
                        fim.zera_gap();
                        fim.altera_pi( pos_i );
                        fim.altera_pf( pos_f );
                    }
		}
            }else{
                // possui as repeticoes necessarias
                // verificas se o gap esta dentro do permitido
                // e armazena no msm no, atualizando a posical final e gaps
		if( (pos_i - fim.retorna_pf() >= 1) && (pos_i - fim.retorna_pf() <= (1+g)) ){
                    fim.altera_rep();
                    fim.altera_gap( (long)(pos_i - fim.retorna_pf() -1) );
                    fim.altera_pf( pos_f );
		}else{
                    // caso nao, eh criado um novo no
                    if(pos_i > fim.retorna_pf()){
                        No novo = new No( a, null, pos_i, pos_f );
                        fim.altera_prox( novo );
                        fim = novo;
                        elementos++;
                    }
		}
            }
	}
    }

    public int retorna_elementos(){ return elementos; }
    public String retorna_id(){ return id; }

    // mostra os nos com total de repeticoes >= r
    public void resultado( int r ){
	No temp = cabeca;
        
	if( temp != null && temp.retorna_rep() >= r ){
            while ( temp != null ){
		if( temp.retorna_rep() >= r ){
                    System.out.printf( "[%s|%d|%d|%d] - ", temp.retorna_info(), temp.retorna_pi(), temp.retorna_pf(), temp.retorna_rep() );
		}
		temp = temp.retorna_prox();
            }
            System.out.printf("\n");
	}
    }
    
    //nome, tam, janela, aux->lenght, aux->posicaoini+1, aux->posicaofim+1, aux->gaps, aux->deg,aux->frag);
    //IDSequencia, tamanhoSequencia, janela, r 
    public void gravaResultado( BufferedWriter bw, String IDSequencia, long tamanhoSequencia, int janela, int r ) throws IOException{
	No temp = cabeca;
        
        if( temp != null && temp.retorna_rep() >= r ){
            while( temp != null ) {
                if( temp.retorna_rep() >= r ){
                    //System.out.printf( "[%s|%d|%d|%d] - ", temp.retorna_info(), temp.retorna_pi(), temp.retorna_pf(), temp.retorna_rep() );
                    //gravarArq.printf( "%s\t%d\t%d\t%d\n", temp.retorna_info(), temp.retorna_pi(), temp.retorna_pf(), temp.retorna_rep() );
                    bw.write( IDSequencia + "\t" + tamanhoSequencia + "\t" + janela + "\t" + temp.retorna_rep()+ "\t" +
                              temp.retorna_pi() + "\t" + temp.retorna_pf() + "\t" + temp.retorna_gap() +"\t" + temp.retorna_info()
                            );
                    bw.newLine();
                }
                temp = temp.retorna_prox();
            }
	}
    }

    
    public void free(){
	cabeca = null;
        fim = null;
        elementos = 0;
    }
	
	
}