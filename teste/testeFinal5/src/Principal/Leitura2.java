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
public class Leitura2 {

    public String id;
    public long tamanhoSequencia;
    public String result;
    public char fragc[];
    long posicaoInicial, posicaoFinal;
    //int posicaoHash;

    public Leitura2( String nomeArquivo, Lista Hash[], int janela, int r, int g , int tamanhoHash, String saida, int contArq ){
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
                        GravarDados gravar = new GravarDados( Hash, tamanhoHash, r, janela, "A" , contArq, tamanhoSequencia+1, buffer );
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
                            
                            //posicaoHash = FuncHash2( posicaoHash, caracter, janela );
                            posicaoHash = FuncHash( result, janela );
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
            GravarDados gravar = new GravarDados( Hash, tamanhoHash, r, janela, "A" , contArq, tamanhoSequencia+1, buffer );

            
	} catch ( IOException io ) {  
            System.out.println("arquivo " + nomeArquivo + " nao encontrado!");  
            System.out.println("programa abortado!");  
            System.exit( 1 );  
	} 
        
    }
    
     
    public static int verifica( int c ){
        if( c == 'X' ){
            return 1;
        }
        return 0;
    }
    
    // verifica se possuei elementos nao identificados
    public static int verifica2( String frag, int janela ){
        int i;
        for(i=0;i<janela;i++){
            if(frag.charAt(i) == 'X'){
                return i+1;
            }
        }
        return 0;
    }
    
    public static int FuncHash2( int inserir , int caracter, int janela ){
        //janela = janela*2;
        //inserir = inserir << 2;
        inserir = inserir * 19; // desloca 5 bits a esquerda
        int x;
        //int x = inserir & ((1<<janela) - 1);
        x = inserir + funhashSimplified( caracter );
        return x;
    }
    
    public static int funhashSimplified( int a ){
        int ret = 0;
        switch(a){
            // A letra G = 0
            case 'P':
                ret = 1;
                break;
            case 'A':
                ret = 2;
                break;
            case 'V':
                ret = 3;
                break;
            case 'L':
                ret = 4;
                break;
            case 'I':
                ret = 5;
                break;
            case 'M':
                ret = 6;
                break;
            case 'C':
                ret = 7;
                break;
            case 'F':
                ret = 8;
                break;
            case 'Y':
                ret = 9;
                break;
            case 'W':
                ret = 10;
                break;
            case 'H':
                ret = 11;
                break;
            case 'K':
                ret = 12;
                break;
            case 'R':
                ret = 13;
                break;
            case 'Q':
                ret = 14;
                break;
            case 'N':
                ret = 15;
                break;
            case 'E':
                ret = 16;
                break;
            case 'D':
                ret = 17;
                break;
            case 'S':
                ret = 18;
                break;
            case 'T':
                ret = 19;
        }
        return ret;
    }


    public static int FuncHash( String frag, int janela ){          
	int i,t,ret=0;
	t = janela - 1;
	for( i = 0 ; i < janela ; i++ ){
            switch( frag.charAt( i ) ){
            	  // A letra G = 0
                case 'P':	// P 1
                    ret += ((int)Math.pow(19, (t - i)));
                    break;
                case 'A':	// A 2
                    ret += (2*(int)Math.pow(19, (t - i)));
                    break;
                case 'V':	// V 3
                    ret += (3*(int)Math.pow(19, (t - i)) );
                    break;
                case 'L':	// L 4
                    ret += (4*(int)Math.pow(19, (t - i)));
                    break;
                case 'I':	// I 5
                    ret += (5*(int)Math.pow(19, (t - i)));
                    break;
                case 'M':	// M 6
                    ret += (6*(int)Math.pow(19, (t - i)));
                    break;
                case 'C':	// C 7
                    ret += (7*(int)Math.pow(19, (t - i)));
                    break;
                case 'F':	// F 8
                    ret += (8*(int)Math.pow(19, (t - i)));
                    break;
                case 'Y': 	// Y 9
                    ret += (9*(int)Math.pow(19, (t - i)));
                    break;
                case 'W':	// W 10
                    ret += (10*(int)Math.pow(19, (t - i)));
                    break;
                case 'H':	// H 11
                    ret += (11*(int)Math.pow(19, (t - i)));
                    break;
                case 'K':	// K 12
                    ret += (12*(int)Math.pow(19, (t - i)));
                    break;
                case 'R':	// R 13
                    ret += (13*(int)Math.pow(19, (t - i)));
                    break;
                case 'Q':	// Q 14
                    ret += (14*(int)Math.pow(19, (t - i)));
                    break;
                case 'N':	// N 15
                    ret += (15*(int)Math.pow(19, (t - i)));
                    break;
                case 'E':	// E 16
                    ret += (16*(int)Math.pow(19, (t - i)));
                    break;
                case 'D':	// D 17
                    ret += (17*(int)Math.pow(19, (t - i)));
                    break;
                case 'S':	// S 18
                    ret += (18*(int)Math.pow(19, (t - i)));
                    break;
                case 'T':	// T 19
                    ret += (19*(int)Math.pow(19, (t - i)));
	
            }
        }
        return ret;
    }

	
    public String getId(){ return id; }
    public void setId( String ID ){ id = ID; }
	
    public long getTamanhoSequencia(){ return tamanhoSequencia; }
    public void setTamanhoSequencia( long tamanho ){ tamanhoSequencia = tamanho; }

}