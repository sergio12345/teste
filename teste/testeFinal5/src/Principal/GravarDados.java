/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sergio O Gomes
 */
public class GravarDados {
    public GravarDados( Lista hash[] , int t, int r, int janela, String nomeArquivo, int contArq, long tamanhoSequencia, String IDSequencia ){
        try {
            try (FileWriter fw = new FileWriter( nomeArquivo+contArq+".txt", true ); BufferedWriter bw = new BufferedWriter( fw )) {
                //PrintWriter gravarArq = new PrintWriter( arq );
                //,nome, tam, janela, aux->lenght, aux->posicaoini+1, aux->posicaofim+1, aux->gaps, aux->deg,aux->frag
                for( int k = 0 ; k < t ; k++ ){
                    hash[ k ].gravaResultado( bw, IDSequencia, tamanhoSequencia, janela, r );
                }
                bw.close(); fw.close();
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", nomeArquivo );
        }
    }
}
