package rbtree;


class Node {
    Node l,r,p;
    byte c;
    int key;

    Node() {
        this.c=0;
        this.l=null;
        this.r=null;
        
    }

    Node(int key) {
        this.key=key;
    }

    Node(Node l,Node r, byte c,int key, Node p){
        this.l=l;
        this.r=r;  
        this.c=c;
        this.p=p;
        this.key=key;
       
        
        
    }
}
