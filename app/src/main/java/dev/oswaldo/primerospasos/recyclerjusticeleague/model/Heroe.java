package dev.oswaldo.primerospasos.recyclerjusticeleague.model;

public class Heroe {

    public String mainCharacter;

    public String title;

    public String year;

    public String image;

    public String heroeID;

    public Heroe(String mainCharacter, String title, String year, String image, String heroeID) {
        this.mainCharacter = mainCharacter;
        this.title = title;
        this.year = year;
        this.image = image;
        this.heroeID = heroeID;
    }

    public Heroe() {
        super();
    }

    @Override
    public String toString() {
        return "Heroe{" +
                "mainCharacter='" + mainCharacter + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", image='" + image + '\'' +
                ", heroeID='" + heroeID + '\'' +
                '}';
    }
}
