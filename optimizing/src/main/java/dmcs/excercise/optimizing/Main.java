package dmcs.excercise.optimizing;

// class initialization
public class Main {
    static class Data {
        private int month;
        private String name;
        Data(int i, String s) {
            month = i;
            name = s;
        }
    }
    static Data months[] = {
            new Data(1, "January"),
            new Data(2, "February"),
            new Data(3, "March"),
            new Data(4, "April"),
            new Data(5, "May"),
            new Data(6, "June")
    };
    public static void main(String args[]) {
        System.out.println("I am efficient");
    }
}