/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio O Gomes
 */
public class GravarResultado {
    public String result;
        
    public GravarResultado( ArrayList<Dados> resultado, String saida ){
        try {
            try (FileWriter fw = new FileWriter( saida ); BufferedWriter bw = new BufferedWriter( fw )) {
                //,nome, tam, janela, aux->lenght, aux->posicaoini+1, aux->posicaofim+1, aux->gaps, aux->deg,aux->frag
                bw.write("ID\tSeqLen\tMotifLen\tStart\tEnd\tGaps\tRep\tMotif");
                bw.newLine();
                for(Object str: resultado){
                        //System.out.println(str);
                        result = str.toString();
                        //System.out.println(result);
                        bw.write(result);
                        bw.newLine();
                }
                
                bw.close(); fw.close();
            }
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", saida );
        }
    }
}
