package Modelo.Listas;


public class SimpleNode {

    private int data;
    private SimpleNode link;

    public SimpleNode(int d) {
        this.link= null;
        this.data=d;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public SimpleNode getLink() {
        return link;
    }

    public void setLink(SimpleNode n) {
        this.link = n;
    }

}