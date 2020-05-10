
package rbtree;


public class rbtreedemo {

    public static void main(String[] args) throws Exception {

        RBTree rbTree = new RBTree();
          rbTree.insert(new Node(41));
          
          
          Node m=new Node(80);   
          Node n=new Node(45);    

          rbTree.insert(m);
          rbTree.insert(new Node(22));
          rbTree.insert(new Node(31));
          rbTree.insert(new Node(64));
          rbTree.insert(n);
          rbTree.insert(new Node(56));

          rbTree.traverseInorder();
        
        
        System.out.print("\n\n");
        rbTree.Search(m,80);
        rbTree.delete(m);
        rbTree.delete(n);
        rbTree.traverseInorder();


    }

}