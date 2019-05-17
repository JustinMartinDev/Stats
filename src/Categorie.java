import java.util.ArrayList;
import java.util.List;

public class Categorie {

    private String name = "default";
    private List<Element> elements = new ArrayList<>();


    public Categorie(){}

    public Categorie(String name) {
        this.name = name;
    }

    public void addElement(Element element){
        elements.add(element);
    }

    public List<Element> getElements() {
        return elements;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        String fullString = "";
        for (Element element: elements) {
            fullString+=element.toString()+"\n";
        }
        return fullString;
    }

    public float sumRank(){
        float sumRank = 0f;
        for (Element element :elements) {
            sumRank+=element.rang;
        }
        return sumRank;
    }
}