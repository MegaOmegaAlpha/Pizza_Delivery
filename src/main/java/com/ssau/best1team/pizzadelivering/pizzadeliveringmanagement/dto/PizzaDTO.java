package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public class PizzaDTO {
    /**
     * todo: продумать момент с форматом передачи картинки
     * todo: хранить в бейс64? хранить путь до файла? какой объект возвращать?
     */

    private long id;
    private String name;
    private String composition;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MultipartFile image;
    private int price;
    private String encodedImage;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}
