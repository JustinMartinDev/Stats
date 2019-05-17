import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static List<Categorie> listeCategorie = new ArrayList<>();
    static List<Element> listeValeurTotal = new ArrayList<>();

    public static void main(String[] argv){

        System.out.print("Entrez le nombre de catégorie : ");
        Scanner scanner = new Scanner(System.in);

        int nbCategories = scanner.nextInt();
        if(nbCategories<=2){
            System.out.println("Ce test n'est pas approprié");
            return;
        }

        for(int i=0; i<nbCategories; i++){
            System.out.print("Entrez la nombre d'élément de la " + (i+1) + " catégorie : ");
            int nbElements = scanner.nextInt();
            if(nbElements<=0){
                System.out.println("Aucune catégorie ne peut être vide");
                return;
            }

            System.out.print("Entrez le nom de la catégorie n° " + (i+1) + " : ");
            scanner.nextLine();
            String name = scanner.nextLine();
            Categorie categorie = new Categorie(name);

            for(int y=0; y<nbElements; y++){
                System.out.print("Entrez un l'élément de la " + (i+1) + " catégorie : ");
                float valeur = scanner.nextFloat();
                categorie.addElement(new Element(valeur));
            }
            listeCategorie.add(categorie);
        }

        //displayData();
        for (Categorie categorie : listeCategorie){
            listeValeurTotal.addAll(categorie.getElements());
        }

        listeValeurTotal.sort(new ComparableElement());

        affectRang();
        ChangeRankEqualsValue();

        float variableDiscriminante = calculVariableDiscriminante();
        System.out.println(variableDiscriminante);

        float tauxErreur = 0f;
        System.out.println("Entrez t-alpha : ");
        tauxErreur = scanner.nextFloat();

        if(variableDiscriminante>tauxErreur){
            System.out.println("On rejete H0");
        }
        else {
            System.out.println("On accepte H0");
        }

        System.out.println(listeCategorie.get(0));

    }

    private static float calculVariableDiscriminante() {

        float variableDiscri = 12/(listeValeurTotal.size()*(listeValeurTotal.size()+1));

        float sumRi = 0f;
        for(Categorie categorie : listeCategorie){
            sumRi+=((categorie.sumRank()*categorie.sumRank())/categorie.getElements().size());
        }

        System.out.println(sumRi);

        variableDiscri*=sumRi;
        variableDiscri-=3*(listeValeurTotal.size()+1);
        return variableDiscri;
    }

    private static void ChangeRankEqualsValue() {

        List<Element> listToChange = new ArrayList<>();

        for(Element element : listeValeurTotal) {
            float sumRang = element.rang;
            int nbSame = 1;
            for(Element element2 : listeValeurTotal) {
               if(!element2.equals(element) && element.value == element2.value) {
                   listToChange.add(element2);
                   sumRang += element2.rang;
                   nbSame++;
               }
            }
            System.out.println(sumRang/nbSame);
            element.setRang(sumRang/nbSame);
            float finalSumRang = sumRang;
            int finalNbSame = nbSame;
            listToChange.forEach(element1 -> {
                element1.setRang(finalSumRang / finalNbSame);
            });
            listToChange.clear();
        }
    }

    private static void affectRang() {
        int rang=1;

        for(Element element : listeValeurTotal) {
            element.setRang(rang);
            rang++;
        }
    }


    static void displayData(){
        for (Categorie categorie : listeCategorie){
            System.out.println("----------------------------");
            System.out.println("Liste : " + categorie.getName());
            System.out.println(categorie);
        }
    }

    static void displayList(){
        for (Element element : listeValeurTotal) {
            System.out.println(element);
        }
    }
}
