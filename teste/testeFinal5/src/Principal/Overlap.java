/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;

/**
 *
 * @author Sergio O Gomes
 */
public class Overlap {
    public int tamanhoLista;
    public long tamanhoA, tamanhoB;
    public int i, j, flag;
    public ArrayList<Dados> resultado;
    public Overlap( ArrayList<Dados> dados, double taxaOverlap, String saida ){
        /*for(Object str: dados){
		//System.out.println(str);
                Dados p1 = (Dados) str;
                System.out.printf("\noverlap  %d\n", p1.tamanhoSequencia);
                
	}*/
        System.out.printf("\noverlap\n");
        resultado = new ArrayList<>();
        tamanhoLista = dados.size();
        Object a, b;
        Dados A,B;
        i=0;
        while( i <= tamanhoLista-1 ){
            A = ( Dados ) dados.get( i );
            j = i+1;
            flag = 0;
            //System.out.printf("%s\n",A.idSeq);
            while( j < tamanhoLista ){
                B = ( Dados ) dados.get( j );
                if( A.idSeq.equals( B.idSeq ) ){
                    //System.out.printf("pi e pf %d-%d - %d-%d\n", A.pi, A.pf, B.pi, B.pf);
                    
                    // nao existe overlap, passa a proxima posicao de j;
                    if( B.pi > A.pf ){
                        flag = 1;
                        i = j-1;
                        break;
                        //j++;
                    }else{
                        //System.out.printf("existe overlap ");
                        // existe overlap
                        tamanhoA = A.pf - A.pi;
                        tamanhoB = B.pf - B.pi;
                        
                        // taxa de overlap (tamanho do overlap / tamanho do maior elemento * 100)
                        long t = (A.pf - B.pi);
                        long tt = maior(tamanhoA, tamanhoB);
                        double tx = (double)t/tt;
                        tx = tx*100;
                        

                        // microssatelite atual eh maior que o proximo e tx > taxaOverlap, remove dados( j )
                        if( tamanhoA >= tamanhoB && tx > taxaOverlap ){
                            //System.out.printf("- A >= B\n");
                            //System.out.printf("remove proximo tx=%f > overlap=%f\n",tx,taxaOverlap);
                            dados.remove( j );
                            tamanhoLista--;
                        }else{ // microssatelite atual eh menor que o proximo
                            //System.out.printf("- A < B\n");
                            // atual eh menor que o proximo e taxa > permitida
                            if(tx > taxaOverlap){
                                // atual eh removido...
                                //System.out.printf("remove atual tx=%f > overlap=%f\n",tx,taxaOverlap);
                                dados.remove( i );
                                tamanhoLista--;
                                // nao incrementa o contador
                                flag = 2;
                                break;
                            }else{
                                /// atual continua
                            }
                            j++;
                        }

                    }
                
                }else{
                    flag = 1;
                    break;
                    //j++;
                }
            }
            
            /* salva o elemento atual,
            if(flag == 0){
                //String idSeq, long tamanhoSequencia, int janela, int rep, long pi, long pf, long gap, String frag
                resultado.add( new Dados( A.idSeq, A.tamanhoSequencia, A.janela, A.rep, A.pi, A.pf, A.gap, A.frag) );
            }*/
            if(flag != 2){
                i++;
            }
        }
        
        //for(Object str: resultado){
		//System.out.println(str);
        //}
        
        
        //GravarResultado r = new GravarResultado(resultado, saida);
        
        //GravarResultado r = new GravarResultado(dados, saida);
        
    }
    public static long maior( long a, long b ){
        if( a > b ){
            return a;
        }else{
            return b;
        }
    }
    
    public static long mod( long a ){
        if( a > 0 ){
            return a;
        }else{
            return a * (-1);
        }
    }
}
