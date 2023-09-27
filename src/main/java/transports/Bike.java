package transports;

import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.text.SimpleDateFormat;
import java.util.*;

public class Bike implements Transport{
    private class Model {
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

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше 0");
        }
        Model model1 = head.next;
        while ((model1).equals(head)) {
            if ((model1.name).equals(name)) {
                throw new DuplicateModelNameException("Модель с названием: " + name + " уже есть");
            }
            model1 = model1.next;
        }
        Model model = new Model(name, price);
        head.prev.next = model;
        Model preModel = head.prev;
        head.prev = model;
        head.prev.prev = preModel;
        head.prev.next = head;
        size++;
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public String[] allNames() {
        Model newModel = head;
        String[] names = new String[size];
        names[0] = newModel.name;
        for (int i = 1; i < size; i++) {
            newModel = newModel.next;
            names[i] = newModel.name;
        }
        return names;
    }

    public double[] allPrices() {
        Model newModel = head;
        double[] prices = new double[size];
        prices[0] = newModel.price;
        for (int i = 0; i < size; i++) {
            newModel = newModel.next;
            prices[i] = newModel.price;
        }
        return prices;
    }

    public double priceOfModel(String name) throws NoSuchModelNameException {
        Model model = head;
        for (int i = 0; i < size; i++) {
            if ((model.name).equals(name)) {
                return model.price;
            }
            model = model.next;
        }
        throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
    }

    public void modifyName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        boolean flag = false;
        Model model1 = head.next;
        while ((model1).equals(head)) {
            if ((model1.name).equals(newName)) {
                throw new DuplicateModelNameException("Модель с названием: " + newName + " уже есть");
            }
            model1 = model1.next;
        }
        Model newModel = head;
        for (int i = 0; i < size; i++) {
            if ((newModel.name).equals(oldName)) {
                newModel.name = newName;
                flag = true;
                break;
            }
            newModel = newModel.next;
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + oldName + " не существует");
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public void modifyPrice(String name, double price) throws NoSuchModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше 0");
        }
        boolean flag = false;
        Model newModel = head;
        for (int i = 0; i < size; i++) {
            if ((newModel.name).equals(name)) {
                newModel.price = price;
                flag = true;
                break;
            }
            newModel = newModel.next;
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        Model reModel = head.next;
        boolean flag = false;
        for (int i = 1; i < size; i++) {
            if ((reModel.name).equals(name)) {
                reModel.prev.next = reModel.next;
                reModel.next.prev = reModel.prev;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
        }
        SimpleDateFormat format = new SimpleDateFormat("ddMMyyyyhhmmss");
        this.lastModified = Long.parseLong(format.format(new Date()));
    }

    public void getDateOfChange() {
        System.out.println(lastModified);
    }

    public static void main(String[] args) throws NoSuchModelNameException, InterruptedException, DuplicateModelNameException {
        Bike bike = new Bike(4);
        String[] names1 = bike.allNames();
        bike.getDateOfChange();
        bike.addModel("RZ", 1800000);
        String[] names2 = bike.allNames();
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
