package objectfs;

import java.util.LinkedList;
import java.util.List;


class Inode {
    private Inode parent;
    private LinkedList<Inode> children = new LinkedList<Inode>();
    private long ino;

    public Inode(long ino, Inode parent) {
        this.parent = parent;
        this.ino = ino;
    }

    public Inode getParent() { return this.parent; }
    public List<Inode> getChildren() { return this.children; }

    public void addChild(Inode child) {
        this.children.add(child);
    }


    public String toString() {
        return "ObjectFs INODE: " + ino;
    }
}






