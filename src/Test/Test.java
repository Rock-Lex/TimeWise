package Test;

public class Test {


    public static void main(String[] args) {
        TestCalendar testCalendar = new TestCalendar();
        TestTermin testTermin = new TestTermin();
        TestDatenbank testDatenbank = new TestDatenbank();

        testCalendar.test();
        testTermin.test();
        testDatenbank.test();
    }
}
