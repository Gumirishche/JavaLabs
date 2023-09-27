package transports;

import exceptions.DuplicateModelNameException;
import exceptions.ModelPriceOutOfBoundsException;
import exceptions.NoSuchModelNameException;

import java.util.Arrays;
import java.util.Objects;

public class Vehicle implements Transport{
    private class Model {
        String name;
        double price;

        public Model(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    private String brand;
    private Model[] models;

    public Vehicle(String brand, Model[] models) {
        this.brand = brand;
        this.models = models;
    }

    public Vehicle(String brand, int size) {
        this.brand = brand;
        models = new Model[size];
        for (int i = 0; i < size; i++) {
            models[i] = new Model("RC" + i, 1000 * i);
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String[] allNames() {
        String[] names = new String[models.length];
        int i = 0;
        for (Model model : models
        ) {
            names[i] = model.name;
            i++;
        }
        return names;
    }

    public double[] allPrices() {
        double[] prices = new double[models.length];
        int i = 0;
        for (Model model : models
        ) {
            prices[i] = model.price;
            i++;
        }
        return prices;
    }

    public double priceOfModel(String name) throws NoSuchModelNameException {
        for (Model model : models) {
            if (Objects.equals(model.name, name)) {
                return model.price;
            }
        }
        throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
    }

    public void modifyName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException {
        boolean flag = false;
        for (Model m : models
        ) {
            if ((m.name).equals(newName)) {
                throw new DuplicateModelNameException("Модель с названием: " + newName + " уже есть");
            }
        }
        for (Model model : models) {
            if ((model.name).equals(oldName)) {
                model.name = newName;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + oldName + " не существует");
        }
    }

    public void modifyPrice(String name, double price) throws NoSuchModelNameException {
        boolean flag = false;
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше 0");
        }
        for (Model model : models) {
            if ((model.name).equals(name)) {
                model.price = price;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
        }
    }

    public void addModel(String name, double price) throws DuplicateModelNameException {
        if (price < 0) {
            throw new ModelPriceOutOfBoundsException("Цена не может быть меньше 0");
        }
        for (Model m : models
        ) {
            if ((m.name).equals(name)) {
                throw new DuplicateModelNameException("Модель с названием: " + name + " уже есть");
            }
        }
        Model model = new Model(name, price);
        models = Arrays.copyOf(models, models.length + 1);
        models[models.length - 1] = model;
    }

    public void removeModel(String name) throws NoSuchModelNameException {
        Model[] newModels = new Model[models.length - 1];
        boolean flag = false;
        for (int i = 0; i < models.length; i++) {
            if (Objects.equals(models[i].name, name)) {
                System.arraycopy(models, 0, newModels, 0, i);
                System.arraycopy(models, i + 1, newModels, i, models.length - i - 1);
                models = newModels;
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new NoSuchModelNameException("Модели с таким именем: " + name + " не существует");
        }
    }


    public static void main(String[] args) throws NoSuchModelNameException, DuplicateModelNameException {
        Vehicle vehicle = new Vehicle("Mitsy", 6);
        String[] names = vehicle.allNames();
        double[] prices = vehicle.allPrices();
        for (int i = 0; i < vehicle.allNames().length; i++) {
            System.out.println(names[i] + " : " + prices[i]);
        }
        vehicle.modifyPrice("RC4", 12345);
        prices = vehicle.allPrices();
        names = vehicle.allNames();
        for (int i = 0; i < vehicle.allNames().length; i++) {
            System.out.println(names[i] + " : " + prices[i]);
        }
        vehicle.addModel("CZ", 54312);
        prices = vehicle.allPrices();
        names = vehicle.allNames();
        for (int i = 0; i < vehicle.allNames().length; i++) {
            System.out.println(names[i] + " : " + prices[i]);
        }
        vehicle.removeModel("RC3");
        prices = vehicle.allPrices();
        names = vehicle.allNames();
        for (int i = 0; i < vehicle.allNames().length; i++) {
            System.out.println(names[i] + " : " + prices[i]);
        }
//        vehicle.modifyPrice("RC",12345);
//        System.out.println(vehicle.priceOfModel("RC"));
    }
}
