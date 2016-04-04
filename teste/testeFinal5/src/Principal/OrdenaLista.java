/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Sergio O Gomes
 */
public class OrdenaLista {
    public OrdenaLista(ArrayList<Dados> dados){
        // ordena peda idSeq
        Collections.sort (dados, new Comparator() {
            public int compare(Object o1, Object o2) {
                Dados p1 = (Dados) o1;
                Dados p2 = (Dados) o2;
                return ( p1.idSeq.compareTo(p2.idSeq) < 0 ) ? -1 : ( (p1.idSeq.compareTo(p2.idSeq) > 0 ) ? +1 : 0);
            }
        });
        
        // ordena pela posicao inicial e idSeq iguais 
        Collections.sort (dados, new Comparator() {
            public int compare(Object o1, Object o2) {
                Dados p1 = (Dados) o1;
                Dados p2 = (Dados) o2;
                return (p1.pi < p2.pi && p1.idSeq.compareTo(p2.idSeq) == 0 ) ? -1 : ( (p1.pi > p2.pi && p1.idSeq.compareTo(p2.idSeq) == 0) ? +1 : 0);
            }
        });
    }
}
