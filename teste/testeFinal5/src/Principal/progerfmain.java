/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio O Gomes
 */
public class progerfmain {
    public progerfmain(int modoDeExecucao, int fragmin, int fragmax, int gaps, int rep, String entrada, String saida, int overlap, int selecionarOverlap, int reps[]) throws IOException{
        
        // r = Minimum repeated times of motif. default 5;
	// i = Minimum length of motif. default 2;
	// y = Maximum length of motif. default 5;
	// g = Maximum allowed Gaps between motifs of a tandem repeat. default 0;
        //   = Overlap
	//int r=2, i=2, y=4, g=0; //r=2, i=3, y=5, g=0;
	//int janela;
        int contArq;
	int t;
        int aux = gaps; // aux para gaps.... fragmento de tamanho 1
        int contThread;
        int tam;
        long inicio,fim; 
        
        // java Principal r 8 i 2 y 4 g 0 q Sitalica.fasta - 557s
        // 13 minutos 52 segundos
        
        //  7 minutos 38 segundos
        //  4 minutos 43 segundos

        inicio = System.currentTimeMillis();
        ArrayList<Dados> dados = new ArrayList<>();
	//String arquivo = "Sal.fasta";//Sitalica_164.fasta";//null; Sal2.fasta
        //String saida = "saida.txt";
	
        /*
        // pega os argumentos da entrada
	for( k=0; k < args.length ; k++ ){
            if( args[ k ].equals("r") ){
		r =  Integer.parseInt( args[ k+1 ] ); k++;
            }else{
		if( args[ k ].equals("g") ){
                    g =  Integer.parseInt( args[ k+1 ] ); k++;
		}else{
                    if( args[ k ].equals("i") ){
			i =  Integer.parseInt( args[ k+1 ] ); k++;
                    }else{
			if( args[ k ].equals("y") ){
                            y =  Integer.parseInt( args[ k+1 ] ); k++;
			}else{
                            if( args[ k ].equals("q") ){
				arquivo =  args[ k+1 ]; k++;
                            }
			}
                    }
		}
            }
	}
        */
        // caso o arquivo de entrada nao exista
        if(entrada == null){
            System.out.printf( "Arquivo nao informado\n" ); 
            System.exit( 1 );  
	}

        int tamanhoHash;
        contArq = 1;
        // fragmento de tamanho i ate janela de tamanho y.   [ i , y ]
        contThread = 0;
        tam = (fragmax - fragmin) + 1;
        Thread myThreads[] = new Thread[tam];
        
        //System.out.printf("modo exec %d\n\n\n",modoDeExecucao);
        
        for( t=fragmin ; t <= fragmax ; t++ ){
            // String arquivo, int t, int r, int g, String saida

            // overlap nao selecionado
            if(selecionarOverlap == 0){
                rep = reps[t];
            }
            
            if(t == 1){
                aux = 0;
            }else{
                aux = gaps;
            }
            
            // nucleotideo
            if(modoDeExecucao == 0){
                System.out.printf("Nucleotide\n\n");
                myThreads[contThread] = new Thread(new wThread( entrada,  t,  rep,  aux, saida, 4));
                myThreads[contThread].start();
            }else{
                // protein
                System.out.printf("Protein\n\n");
                myThreads[contThread] = new Thread(new wThread( entrada,  t,  rep,  aux, saida, 20));
                myThreads[contThread].start();
            }

            contThread++;
           
	}
        /*
        for (int j = 0; j < argSize; j++) {
            myThreads[i].join(); //todo add catch exception
        }
        */
        
        // faz com que a thread que esteja executando suspenda ate que a thread t execute (ate terminar)
        for (int q = 0; q < tam; q++) {
            try {
                myThreads[q].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        
        // junta todos os arquivos criados(A1, A2, ..., An) em Saida.txt
        juntarArquivos j = new juntarArquivos( fragmin );
        
        // overlap
        // LerDados: le os dados do arquivo Saida.txt e os colocam em um arraylist
        System.out.printf( "lendo Dados\n" ); 
        LerDados Dados = new LerDados(dados, "SaidaA.txt");
        
        System.out.printf( "Ordenando Dados\n" ); 
        OrdenaLista ordena = new OrdenaLista(dados);
        System.out.printf( "\nordenado\n" ); 
        
        //for(Object str: dados){
	//	System.out.println(str);
	//}
        
        if(selecionarOverlap == 1){
            // dados, taxa overlap permitida, arquivo de saida
            Overlap o = new Overlap( dados, overlap, saida );
            //for(Object str: dados){
            //    System.out.println(str);
            //}
        }//else{
            //GravarResultado g = new GravarResultado(resultado, saida);
            GravarResultado g = new GravarResultado(dados, saida);
        //}
        
        // remover arquivos temporarios
        RemoverArquivosTemporarios r = new RemoverArquivosTemporarios( fragmin );
        
        fim = System.currentTimeMillis();
        System.out.printf("\n\n\tTempo total %d\n\n", fim - inicio );
    }
}
