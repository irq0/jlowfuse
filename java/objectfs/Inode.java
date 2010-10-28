package objectfs;

import java.util.LinkedList;
import java.util.List;
import java.nio.ByteBuffer;

import fuse.Stat;

class Inode {
    private Inode parent;
    private LinkedList<Inode> children = new LinkedList<Inode>();
    private long ino;
    private String name;
    private Stat stat;
    private ByteBuffer data;
    
    private static long nextIno = 1;
    
    public static long getNextIno() {
        return Inode.nextIno++;
    }
    
    public Inode(long ino, Inode parent, String name) {
        this.parent = parent;
        this.ino = ino;
        this.name = name;
    }

    public Inode(Inode parent, String name) {
        this(Inode.getNextIno(), parent, name);
    }

    public void setData(ByteBuffer data) { this.data = data; }
    public ByteBuffer getData() { return this.data; }
    
    public void setParent(Inode parent) { this.parent = parent; }
    public Inode getParent() { return this.parent; }

    public long getIno() { return this.ino; }
    public String getName() { return this.name; }

    public Stat getStat() { return this.stat; }
    public void setStat(Stat stat) { this.stat = stat; }
    
    public List<Inode> getChildren() { return this.children; }

    public void addChild(Inode child) {
        this.children.add(child);
    }

    public String toString() {
        return "ObjectFs.Inode(ino=" + ino + ";name=" + name +")";
    }
}






