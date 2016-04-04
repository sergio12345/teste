/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Sergio O Gomes
 */
public class juntarArquivos {
    public int cont=1;

    // grava os dados dos arquivos em Saida.txt
    public static void leitor(BufferedWriter bw, String path) throws IOException {
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha = "";
            while(true) {
                if(linha != null) {
                    //System.out.println(linha);
                    bw.append( linha );
                    bw.newLine();
                }else{
                    break;
                }
                linha = buffRead.readLine();
            }
            buffRead.close();
        }
    }
    
    // cria o arquivo de saida (resultado)
    // abre os arquivos temporarios ...
    // saida2.txt    saida3.txt   .... saida n .txt
    public juntarArquivos( int i ) throws IOException {
        String nomeArquivo;
        try ( // cria o arquivo de saida
         
            // Saida.txt eh o arquivo final - juntar todos arquivos - arquivo temporario
            FileWriter fw = new FileWriter( "SaidaA.txt", true );
            BufferedWriter bw = new BufferedWriter( fw )) {
                nomeArquivo = "A"+i+".txt";
                File arquivo;
                while(true){
                    nomeArquivo = "A"+i+".txt";
                    arquivo = new File( nomeArquivo );
                    i++;
                    // enquanto o arquivo i existir... chama o leitor e grava em Saida.txt
                    if ( arquivo.exists() ) {
                        leitor( bw, nomeArquivo );
                    }else{
                        break;
                    }
                }
                bw.close();
            }
    }
}
