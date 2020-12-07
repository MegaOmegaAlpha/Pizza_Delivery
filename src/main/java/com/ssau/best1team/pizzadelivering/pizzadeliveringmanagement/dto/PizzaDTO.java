package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto;

import org.springframework.web.multipart.MultipartFile;

public class PizzaDTO {
    /**
     * todo: продумать момент с форматом передачи картинки
     * todo: хранить в бейс64? хранить путь до файла? какой объект возвращать?
     */

    private long id;
    private String name;
    private String composition;
    private MultipartFile multipartFile;
    private int price;
    private byte[] bytes;

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

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }
}
