package biz.rageintegro.erealt.domain;

import junit.framework.TestCase;

import java.io.File;

public class PhotoManagerTest extends TestCase {

    private PhotoManager photoManager;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        photoManager = new PhotoManager();
        photoManager.setBaseDir("baseDir");
    }

    public void test1() {
        File f = new File("baseDir");
        f = new File(f, "000\\000\\012\\345");
        assertEquals(f, photoManager.getEntityDir((long) 12345));
    }
}
