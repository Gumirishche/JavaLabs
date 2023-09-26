import java.text.SimpleDateFormat;
import java.util.*;

public class Bike {
    private static class Model {
        String name;
        double price;
        Model next;
        Model prev;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private String brand;
    private int size = 1;
    private final Model head = new Model("CZ", 2800000);

    {
        head.prev = head;
        head.next = head;
    }

    private long lastModified;

    public Bike(int size) {
        this.size = size;
        for (int i = 1; i < size; i++) {
            Model newModel = new Model("CZ" + i, 1000000 + i * 1000);
            head.prev.next = newModel;
            head.prev = newModel;
            head.prev.next = head;
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void addModel(Model model) {
        head.prev.next = model;
        Model preModel = head.prev;
        head.prev = model;
        head.prev.prev = preModel;
        head.prev.next = head;
        size++;
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public String[] getNames() {
        Model newModel = head;
        String[] names = new String[size];
        names[0] = newModel.name;
        for (int i = 1; i < size; i++) {
            newModel = newModel.next;
            names[i] = newModel.name;
        }
        return names;
    }

    public double[] getPrice() {
        Model newModel = head;
        double[] prices = new double[size];
        prices[0] = newModel.price;
        for (int i = 0; i < size; i++) {
            newModel = newModel.next;
            prices[i] = newModel.price;
        }
        return prices;
    }

    public double priceOfModel(String name) {
        Model model = head;
        for (int i = 0; i < size; i++) {
            if ((model.name).equals(name)) {
                break;
            }
            model = model.next;
        }
        return model.price;
    }

    public void modifyPrice(String name, double price) {
        Model newModel = head;
        for (int i = 0; i < size; i++) {
            if ((newModel.name).equals(name)) {
                newModel.price = price;
            }
            newModel = newModel.next;
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public void removeModel(String name, double price) {
        Model reModel = head.next;
        for (int i = 1; i < size; i++) {
            if ((reModel.name).equals(name) && reModel.price == price) {
                reModel.prev.next = reModel.next;
                reModel.next.prev = reModel.prev;
            }
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public long getDateOfChange(){
        System.out.println(lastModified);
        return lastModified;
    }

    public static void main(String[] args) throws InterruptedException {
        Bike bike = new Bike(4);
        String[] names1 = bike.getNames();
        bike.getDateOfChange();
        Model model = new Model("RZ", 1800000);
        bike.addModel(model);
        String[] names2 = bike.getNames();
        bike.getDateOfChange();
        for (String name : names1
        ) {
            System.out.println(name);
        }
        System.out.println(" ");
        for (String name : names2
        ) {
            System.out.println(name);
        }
        System.out.println(bike.priceOfModel("CZ3"));
        Thread.sleep(10000);
        bike.modifyPrice("CZ3", 12345);
        System.out.println(bike.priceOfModel("CZ3"));
        bike.getDateOfChange();
    }
}
