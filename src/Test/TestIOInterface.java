package Test;

import IOManager.IOinterface;

public class TestIOInterface {
    IOinterface ioi = new IOinterface();

    public void test()
    {
        testExport();
    }

    private void testExport() {
        ioi.exportICS("test");
    }

}
