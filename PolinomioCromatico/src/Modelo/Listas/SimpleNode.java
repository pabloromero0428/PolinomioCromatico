package Modelo.Listas;


public class SimpleNode {

    private Object data;
    private SimpleNode link;

    public SimpleNode(Object d) {
        this.link= null;
        this.data=d;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public SimpleNode getLink() {
        return link;
    }

    public void setLink(SimpleNode n) {
        this.link = n;
    }

}
