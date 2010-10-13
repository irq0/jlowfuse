package jlowfuse.testfs;

import jlowfuse.*;
import java.util.*;

public class TestFs {
    public static void main(String args[]) {
        JLowFuse f = new JLowFuse();

        f.lowlevelNew(new TestFsOpts());
    }
      
}

