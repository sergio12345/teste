/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Sergio O Gomes
 */
public class Leitura {

    public String id;
    public long tamanhoSequencia;
    public String result;
    public char fragc[];
    long posicaoInicial, posicaoFinal;
    //int posicaoHash;

    public Leitura( String nomeArquivo, Lista Hash[], int janela, int r, int g , int tamanhoHash, String saida, int contArq ){
	try {  
            InputStream is = new FileInputStream( nomeArquivo );
            BufferedReader bf = new BufferedReader( new InputStreamReader( is ) );
            int caracter, i;
            String buffer=null;
            int flag = 0;// primeira sequencia
            int flag2 = 0;// verifica se possui elementos nao identificados ou invalidos
            int posicaoHash = -1; // -1 ainda nao foi calculado
            fragc = new char[ janela ];
            tamanhoSequencia = 0;
           
            while ( (caracter = bf.read() ) != -1 ) {
                if( caracter == '>' ){
                    flag2 = 0;
                    posicaoInicial = 0;
                    posicaoFinal = janela-1;//janela;
                    // so grava os dados ao final da sequencia
                    if( flag != 0 ){
                        // gravar dados
                        GravarDados gravar = new GravarDados( Hash, tamanhoHash, r, janela, "A" , contArq, tamanhoSequencia+1, buffer.split(" ")[0] );
                        for(int ii=0; ii < tamanhoHash; ii++){
                            Hash[ii] = new Lista();
                        }
                        System.out.printf("\n[%d]\n", tamanhoSequencia );
                        tamanhoSequencia = 0;
                    }
                    buffer = bf.readLine();
                    System.out.printf("\nnova sequencia = %s\n", buffer );
                    flag = 1;
                }else{
                    if(caracter != '\n' && caracter != '\r'){
                        // insere um elemento por vez
                        if( flag == 2 ){
                            // verifica se eh minusculo, se for subtrai 32 e transforma para maiusculo
                            if(caracter >= 97){
                                caracter = caracter - 32;
                            }
                            
                            //desloca uma unidade e insere um novo elemento
                            result = result + ( char )caracter;
                            result = result.substring( 1 ); //remove the first letter from the input string
                            //System.out.printf("[%s]",result);
                            tamanhoSequencia++;
                            
                            // verifica se eh caracter invalido 'N', se for eh ignorada n vezes
                            if(verifica( caracter ) == 1){
                                flag2 = janela;
                            }else{
                                if(flag2 > 0){
                                    flag2--;
                                }
                            }
                            
                            // calcula novo valor, mas so insere se nao tiver 'N'
                            posicaoHash = FuncHash2( posicaoHash, caracter, janela );
                            //System.out.printf("posicao hash = %d   %c\n",posicaoHash, caracter);
                            // flag2 == 0, nao possui elementos nao identificados
                            if(flag2 == 0){
                                //posicaoHash = FuncHash( result, janela );
                                //System.out.printf("Insere\n");
                                Hash[ posicaoHash ].insere( result, posicaoInicial+1, posicaoFinal+1, r, g );
                            }
                        }else{
                            // le a primeira sequencia, forma o motivo de tamanho n (janela)
                            flag = 2;
                            // pega uma sequencia de tamanho janela
                            i = 0;
                            while( i < janela ){
                                // verifica se eh minusculo, se for subtrai 32 e transforma para maiusculo
                                if(caracter >= 97){
                                    caracter = caracter - 32;
                                }
                                fragc[ i ] = ( char ) caracter;
                                
                                if(i < janela-1){
                                    caracter = bf.read();
                                }
                                
                                // final do arquivo
                                if(caracter == -1){
                                    break;
                                }
                                i++;
                            }
                            tamanhoSequencia = janela-1;
                            result = new String( fragc ); // Convert to a string.
                            
                            // verifica se possui 'N'
                            if(verifica2(result, janela) == 0){
                                posicaoHash = FuncHash( result, janela );
                                //System.out.printf("posicao hash = %d\n",posicaoHash);
                                Hash[ posicaoHash ].insere( result, posicaoInicial+1, posicaoFinal+1, r, g );
                                // flag2 ok
                                flag2 = 0;
                            }else{
                                flag2 = verifica2(result, janela);
                            }
                        }
                        posicaoInicial++; posicaoFinal++;
                    }
                }
            }
            
            // imprime a hash
            //System.out.printf( "\nFragmento, PosicaoInicial, PosicaoFinal, Repeticoes\n" );
            // imprimir o tamanho e a SeqID
            //for(int ii=0; ii < tamanhoHash; ii++){
            //    Hash[ ii ].resultado( r );
            //}
            
            // grava dados da ultima sequencia
            GravarDados gravar = new GravarDados( Hash, tamanhoHash, r, janela, "A" , contArq, tamanhoSequencia+1, buffer.split(" ")[0] );

            
	} catch ( IOException io ) {  
            System.out.println("arquivo " + nomeArquivo + " nao encontrado!");  
            System.out.println("programa abortado!");  
            System.exit( 1 );  
	} 
        
    }
    
     
    public static int verifica( int c ){
        if( c == 'N' ){
            return 1;
        }
        return 0;
    }
    
    // verifica se possuei elementos nao identificados
    public static int verifica2( String frag, int janela ){
        int i;
        for(i=0;i<janela;i++){
            if(frag.charAt(i) == 'N'){
                return i+1;
            }
        }
        return 0;
    }
    
    public static int FuncHash2( int inserir , int caracter, int janela ){
        janela = janela*2;
        inserir = inserir << 2;
        int x = inserir & ((1<<janela) - 1);
        x += funhashSimplified( caracter );
        return x;
    }
    
    public static int funhashSimplified( int a ){
        int ret = 0;
        switch(a){
            case 'C':
                ret = 1;
                break;
            case 'G':
                ret = 2;
                break;
            case 'T':
                ret = 3;
                break;
        }
        return ret;
    }


    public static int FuncHash( String frag, int janela ){          
	int i,t,ret=0;
	t = janela - 1;
	for( i = 0 ; i < janela ; i++ ){
            switch( frag.charAt( i ) ){
            //case 'A': ret += Math.pow( 2, (t-i) * 2 );
            //	  break;
            case 'C': ret += ( (int)Math.pow( 2, ( (t - i) * 2 )));
                break;
            case 'G': ret += ( (int)Math.pow( 2, (( (t - i) * 2) + 1)));
		break;
            case 'T': ret += ( (int)Math.pow( 2, (( (t - i) * 2) + 1)));
		ret += ( (int)Math.pow( 2, ( (t - i) * 2) ));
                break;
            }
	}
	return ret;
    }  

	
    public String getId(){ return id; }
    public void setId( String ID ){ id = ID; }
	
    public long getTamanhoSequencia(){ return tamanhoSequencia; }
    public void setTamanhoSequencia( long tamanho ){ tamanhoSequencia = tamanho; }

}