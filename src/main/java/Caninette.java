public class Caninette {
    int id;
    String adresse, numero,etat, remarque;
    double positionE,positionN;



    public Caninette(int id, String adresse, String numero, String etat, String remarque, double positionE, double positionN) {
        this.id = id;
        this.adresse = adresse;
        this.numero = numero;
        this.etat = etat;
        this.remarque = remarque;
        this.positionE = positionE;
        this.positionN = positionN;
    }

    @Override
    public String toString() {
        return
                "Caninette n°: " + id +  "\n" +
                "Adresse: " + adresse +  "\n" +
                "Numero: " + numero +  "\n" +
                "Etat: " + etat +  "\n" +
                "Remarque: " + remarque +  "\n" +
                        "======================" + "\n"
                ;
    }

}
