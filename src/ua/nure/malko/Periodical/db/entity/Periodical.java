package ua.nure.malko.Periodical.db.entity;

import java.util.Arrays;

public class Periodical extends Entity {

    private static final long serialVersionUID = 3L;

    private String name;

    private Integer price;

    private Long categoryId;

    private byte[] image;

    private byte[] content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", image=" + Arrays.toString( image ) +
                ", content=" + Arrays.toString( content ) +
                '}';
    }

}