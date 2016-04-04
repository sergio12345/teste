/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.File;

/**
 *
 * @author Sergio O Gomes
 */
public class RemoverArquivosTemporarios {
    public RemoverArquivosTemporarios(int i){
        String nomeArquivo;

        File arquivo;
        while(true){
            nomeArquivo = "A"+i+".txt";
            arquivo = new File( nomeArquivo );
            i++;
            // enquanto o arquivo i existir... chama o leitor e grava em Saida.txt
            if ( arquivo.exists() ) {
                arquivo.delete();
            }else{
                break;
            }
        }
        
        // remover o arquivo Saida.txt
        arquivo = new File( "SaidaA.txt" );
        if ( arquivo.exists() ) {
            arquivo.delete();
        }
    }
}
