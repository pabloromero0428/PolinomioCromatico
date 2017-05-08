package Modelo.Listas;

public class SList {
    protected SimpleNode first;
    protected SimpleNode last;
    
    public SList(){
        first = last = null;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public SimpleNode firstNode(){
        return first;
    }
    
    public SimpleNode lastNode(){
        return last;
    }
    
    public SimpleNode previousNode(SimpleNode x){
        SimpleNode p, y;
        if (first==null) {
            return null;
        }
        if (isEmpty()) {
            return first;
        }
        p=firstNode();
        if (p==first) {
            y=null;
        }
        else{
            y=first;
        }
        while(p!=x){
            y=p;
            p=p.getLink();
        }
        return y;
    }
    
    public boolean isTheEnd(SimpleNode x){
        return x==null;
    }
    
    //Aqui va recorre
    
    public void insert(Object d, SimpleNode y){
        SimpleNode x;
        x= new SimpleNode(d);
        connect(x,y);
    }

    private void connect(SimpleNode x, SimpleNode y) {
        if (y!=null) {
            x.setLink(y.getLink());
            y.setLink(x);
            if (y==last) {
                last=x;
            }
        }
        else{
            x.setLink(first);
            if (first==null) {
                last=x;
            }
            first=x;
        }
    }
    
    public SimpleNode findData(Object d, SimpleNode y){
        SimpleNode x;
        x=firstNode();
        y=previousNode(x);
        while(!isTheEnd(x) && x.getData()!=d){
            y=x;
            x=x.getLink();
        }
        return x;
    }
    
    public void delete(SimpleNode x, SimpleNode y){
        if (x==null) {
            return;
        }
        disconnect(x,y);
    }

    public void disconnect(SimpleNode x, SimpleNode y) {
        if (x!=first) {
            y.setLink(x.getLink());
            if (x==last) {
                last=y;
            }
        }
        else{
            first=first.getLink();
            if (first==null) {
                last=null;
            }
        }
    }
}
