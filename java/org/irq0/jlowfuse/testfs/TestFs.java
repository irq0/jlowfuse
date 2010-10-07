package org.irq0.jlowfuse.testfs;

import org.irq0.jlowfuse.*;
import java.util.*;

public class TestFs {
    public static void main(String args[]) {
        JLowFuse f = new JLowFuse();

        f.init(new TestFsOpts());
    }
      
}

