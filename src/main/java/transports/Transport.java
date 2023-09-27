package transports;

import exceptions.DuplicateModelNameException;
import exceptions.NoSuchModelNameException;

public interface Transport {
    String getBrand();

    void setBrand(String brand);

    String[] allNames();

    double[] allPrices();

    double priceOfModel(String name) throws NoSuchModelNameException;

    void modifyName(String oldName, String newName) throws NoSuchModelNameException, DuplicateModelNameException;

    void modifyPrice(String name, double price) throws NoSuchModelNameException;

    void addModel(String name, double price) throws DuplicateModelNameException;

    void removeModel(String name) throws NoSuchModelNameException;

}
