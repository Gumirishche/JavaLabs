package transports;

public class Transports {
    public static double arifMidl(Transport transport) {
        double sum = 0;
        for (double p : transport.allPrices()
        ) {
            System.out.println(sum);
            sum += p;
        }
        return sum / transport.allPrices().length;
    }

    public static void allPrices(Transport transport) {
        for (double price : transport.allPrices()
        ) {
            System.out.println("Марка:" + transport.getBrand());
            System.out.println("Цены:");
            System.out.println(price);
        }
    }

    public static void allNames(Transport transport) {
        for (String name : transport.allNames()
        ) {
            System.out.println("Марка:" + transport.getBrand());
            System.out.println("Название моделей:");
            System.out.println(name);
        }
    }
}
