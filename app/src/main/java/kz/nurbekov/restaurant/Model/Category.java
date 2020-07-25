package kz.nurbekov.restaurant.Model;

public class Category {
    private String Name, Image;

    public Category() {
    }

    public Category(String image, String name) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
