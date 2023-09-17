import java.util.Arrays;
import java.util.Objects;

public class Vehicle {
    private String brand;
    private Model[] models;

    public Vehicle(String brand, Model[] models) {
        this.brand = brand;
        this.models = models;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public class Model {
        private String name;
        private double price;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public void modifyModels(Model[] models) {
        this.models = models;
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

    public double priceOfModel(String name) {
        double price = 0;
        int i = 0;
        while (!Objects.equals(models[i].name, name)) {
            i++;
        }
        return models[i].price;
    }

    public void modifyPrice(String name, double price) {
        int i = 0;
        while (Objects.equals(models[i].name, name)) {
            i++;
        }
        models[i].price = price;
    }

    public Model[] addModel(Model model) {
        models = Arrays.copyOf(models, models.length+1);
        models[models.length-1] = model;
        return models;
    }

    public void removeModel(String name, double price) {
        Model[] newModels = new Model[models.length-1];
        int i = 0;
        while (!Objects.equals(models[i].name, name) || !Objects.equals(models[i].price, price)) {
            i++;
        }
        System.arraycopy(models,0,newModels,0,i);
        System.arraycopy(models,i+1,newModels,i,models.length-i-1);
        models=newModels;
    }


    public static void main(String[] args) {
        int[] i = {1, 2, 3, 4, 5, 6};
        int[] j = new int[5];
        j = Arrays.copyOf(i,i.length+1);
        j[j.length-1] = 1;
//        System.arraycopy(i, 0, j, 0, 3);
//        System.arraycopy(i, 4, j, 3, i.length-3-1);
//        j = Arrays.copyOf(j, 7);
        for (int l : j
        ) {
//            if (!Objects.equals(i[2], l)) {
                System.out.println(l);
//            }
        }
    }
}
