package Test;

import IOManager.ioICS;

public class TestioICS {
    ioICS ioi = new ioICS();

    public void test()
    {
        testExport();
    }

    private void testExport() {
        ioi.exportICS("test",
                "uid1@example.com",
                "19970714T170000Z",
                "19970714T170000Z",
                "19970715T035959Z",
                "",
                "Termin for test");
    }

}
