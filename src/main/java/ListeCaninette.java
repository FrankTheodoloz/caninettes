import java.util.ArrayList;

public class ListeCaninette {

    ArrayList <Caninette> listeCaninette = new ArrayList<>();

    static public void affichageCaninette(ArrayList <Caninette> listeCaninette){
        for (int i = 0; i < listeCaninette.size()-1 ; i++) {
            System.out.println(listeCaninette.get(i));
        }
    }

}


