public class Element {
    float value;
    float rang = -1;

    public Element(float value) {
        this.value = value;
    }

    public void setRang(float rang) {
        this.rang = rang;
    }

    @Override
    public String toString() {
        return "Element{" +
                "value=" + value +
                ", rang=" + rang +
                '}';
    }
}
