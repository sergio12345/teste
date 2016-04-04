/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sergio O Gomes
 */

public class LerDados {
    public String idSeq;          //0
    public long tamanhoSequencia; //1
    public int janela;            //2
    public int rep;               //3
    public long pi;               //4
    public long pf;               //5
    public long gap;              //6
    public String frag;           //7
    public int totalRepeticoes;
    
    public LerDados( ArrayList<Dados> dados, String path ) throws IOException {
        
        try (BufferedReader buffRead = new BufferedReader(new FileReader(path))) {
            String linha = "";
            String s[];
            totalRepeticoes = 0;
            while(true) {
                if(linha != null) {
                    //System.out.println(linha);
                    if(linha.length() > 1){
                        s = linha.split("\t");
                        // string, string, long, long, long, long, int, int
                        idSeq = s[0];
                        tamanhoSequencia = Long.parseLong(s[1]);
                        janela = Integer.parseInt(s[2]);
                        rep = Integer.parseInt(s[3]);
                        totalRepeticoes = totalRepeticoes + rep;
                        pi = Long.parseLong(s[4]);
                        pf = Long.parseLong(s[5]);
                        gap = Long.parseLong(s[6]);
                        frag =  s[7];
                        dados.add( new Dados( idSeq, tamanhoSequencia, janela, rep, pi, pf, gap, frag) );
                    }
                }else{
                    break;
                }
                linha = buffRead.readLine();
            }
            
            System.out.printf("\n\tTotal Repetições: %d\n",totalRepeticoes);
            buffRead.close();
            //OrdenaLista ordena = new OrdenaLista(dados);
        }
    }
}
