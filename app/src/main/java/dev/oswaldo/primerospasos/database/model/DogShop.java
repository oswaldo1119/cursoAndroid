package dev.oswaldo.primerospasos.database.model;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class DogShop extends RealmObject {

    public String name;

    public String address;

    public String image;

    @PrimaryKey
    public String dogShopID;

    public DogShop(String name, String address, String image, String dogShopID) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.dogShopID = dogShopID;
    }

    public DogShop(){
        super();
    }

    @Override
    public String toString() {
        return "DogShop{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", image='" + image + '\'' +
                ", dogShopID='" + dogShopID + '\'' +
                '}';
    }
}
