/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

public class RBTree {

    Node root;
    Node nil;

    RBTree() {
        nil = new Node();
        root = nil;
    }

    public void insert(Node z) {

        Node y = nil;
        Node x = root;

        while (x != nil) {
            y = x;

            if (z.key < x.key) {
                x = x.l;
            } else {
                x = x.r;
            }
        }
        z.p = y;

        if (y == nil) {
            root = z;
        } else if (z.key < y.key) {
            y.l = z;
        } else {
            y.r = z;
        }
        z.l = nil;
        z.r = nil;
        z.c = 1;

        FIXUP(z);
    }

    public void Search(Node m, int a) throws Exception {

        if (m == nil) {
            System.out.println("EXISTS");
        }

        if (m.key == a) {
            System.out.println("FOUND");
        } else if (m.key > a) {
            Search(m.r, a);
        } else {
            Search(m.l, a);
        }

    }

    public void sea(int a) throws Exception {
        Search(root, a);
    }

    public void FIXUP(Node z) {

        Node y;
        while (z.p.c == 1) {
            //case a
            if (z.p == z.p.p.l) {
                y = z.p.p.r;

                //case 1
                if (y.c == 1) {
                    z.p.c = 0;
                    y.c = 0;
                    z.p.p.c = 1;
                    z = z.p.p;
                } //case 2 and case 3
                else {
                    if (z == z.p.r) {
                        z = z.p;
                        lRotate(z);

                    }
                    z.p.c = 0;
                    z.p.p.c = 1;
                    rRotate(z.p.p);
                }
            } //case b
            else {
                y = z.p.p.l;

                if (y.c == 1) {
                    z.p.c = 0;
                    y.c = 0;
                    z.p.p.c = 1;
                    z = z.p.p;
                } else {
                    if (z == z.p.l) {
                        z = z.p;
                        rRotate(z);
                    }
                    z.p.c = 0;
                    z.p.p.c = 1;
                    lRotate(z.p.p);
                }
            }

        }
        root.c = 0;

    }

    public void insertFixUp(Node z) {
        Node y;
        while (z.p.c == 1) {
            //case a
            if (z.p == z.p.p.l) {
                y = z.p.p.r;

                //case 1
                if (y.c == 1) {
                    z.p.c = 0;
                    y.c = 0;
                    z.p.p.c = 1;
                    z = z.p.p;
                } //case 2 and case 3
                else {
                    if (z == z.p.r) {
                        z = z.p;
                        lRotate(z);

                    }
                    z.p.c = 0;
                    z.p.p.c = 1;
                    rRotate(z.p.p);
                }
            } //case b
            else {
                y = z.p.p.l;

                if (y.c == 1) {
                    System.out.println("case 1 b");
                    z.p.c = 0;
                    y.c = 0;
                    z.p.p.c = 1;
                    z = z.p.p;
                } else {
                    if (z == z.p.l) {
                        z = z.p;
                        rRotate(z);
                    }
                    z.p.c = 0;
                    z.p.p.c = 1;
                    lRotate(z.p.p);
                }
            }

        }
        root.c = 0;
    }

    public void lRotate(Node x) {
        Node y = x.r;
        x.r = y.l;

        if (y.l != nil) {
            y.l.p = x;
        }
        y.p = x.p;
        if (x.p == nil) {
            root = y;
        } else if (x == x.p.l) {
            x.p.l = y;
        } else {
            x.p.r = y;
        }
        y.l = x;
        x.p = y;
    }

    public void rRotate(Node x) {
        Node y = x.l;
        x.l = y.r;

        if (y.r != nil) {
            y.r.p = x;
        }
        y.p = x.p;
        if (x.p == nil) {
            root = y;
        } else if (x == x.p.r) {
            x.p.r = y;
        } else {
            x.p.l = y;
        }
        y.r = x;
        x.p = y;
    }

    public void traverseInorder(Node node) {
        if (node == nil) {
            return;
        }
        traverseInorder(node.l);
        String col = "red";
        if (node.c == 0) {
            col = "black";
        }

        System.out.println(node.key + " " + col);
        traverseInorder(node.r);

    }

    void transplant(Node target, Node with) {

        if (target.p == nil) {
            root = with;

        } else if (target == target.p.l) {

            target.p.l = with;
        } else {
            target.p.r = with;
        }
        with.p = target.p;

    }

    Node treeMinimum(Node s) {
        while (s.l != nil) {
            s = s.l;
        }
        return s;
    }

    boolean delete(Node z) {

        Node x;
        Node y = z;
        int y_original_c = y.c;

        if (z.l == nil) {
            x = z.r;
            transplant(z, z.r);
        } else if (z.r == nil) {
            x = z.l;
            transplant(z, z.l);
        } else {
            y = treeMinimum(z.r);
            y_original_c = y.c;
            x = y.r;
            if (y.p == z) {
                x.p = y;
            } else {
                transplant(y, y.r);
                y.r = z.r;
                y.r.p = y;
            }
            transplant(z, y);
            y.l = z.l;
            y.l.p = y;
            y.c = z.c;
        }
        if (y_original_c == 0) {
            deleteFixup(x);
        }
        
        return true;
        
        
    }

    void deleteFixup(Node x) {
        
        
        while (x != root && x.c == 0) {
            if (x == x.p.l) {
                Node w = x.p.r;
                if (w.c == 1) {
                    w.c = 0;
                    x.p.c = 1;
                    rRotate(x.p);
                    w = x.p.r;
                }
                if (w.l.c == 0 && w.r.c == 0) {
                    w.c = 1;
                    x = x.p;
                    continue;
                } else if (w.r.c == 0) {
                    w.l.c = 0;                                                              
                    w.c = 1;
                    rRotate(w);
                    w = x.p.r;
                }
                if (w.r.c == 1) {
                    w.c = x.p.c;
                    x.p.c = 0;
                    w.r.c = 0;
                    lRotate(x.p);
                    x = root;
                }
            } else {
                Node w = x.p.l;
                if (w.c == 1) {
                    w.c = 0;
                    x.p.c = 1;
                    lRotate(x.p);
                    w = x.p.l;
                }
                if (w.r.c == 0 && w.l.c == 0) {
                    w.c = 1;
                    x = x.p;
                    continue;
                } else if (w.l.c == 0) {
                    w.r.c = 0;
                    w.c = 1;
                    lRotate(w);
                    w = x.p.l;
                }
                if (w.l.c == 1) {
                    w.c = x.p.c;
                    x.p.c = 0;
                    w.l.c = 0;
                    rRotate(x.p);
                    x = root;
                }
            }
        }
        x.c = 0;
    }

    public void traverseInorder() {
     
     traverseInorder(root);
    }
}