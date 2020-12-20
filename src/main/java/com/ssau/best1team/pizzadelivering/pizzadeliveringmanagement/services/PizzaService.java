package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PizzaDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Pizza;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.PizzaRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.utils.FileUploadUtil;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;
    private ModelMapper modelMapper;

    private static final String IMAGE_DIRECTORY = "images/";

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, ModelMapper modelMapper) {
        this.pizzaRepository = pizzaRepository;
        this.modelMapper = modelMapper;
    }

    public List<PizzaDTO> findAll() {
        return pizzaRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PizzaDTO findById(long id) {
        return convertToDTO(pizzaRepository.findById(id).get());
    }

    public PizzaDTO save(PizzaDTO pizzaDTO, MultipartFile pizzaPhotoFile) throws IOException {
        Pizza pizza = convertToEntity(pizzaDTO);

        String imageFileName = StringUtils.cleanPath(pizzaPhotoFile.getOriginalFilename());
        FileUploadUtil.saveFile(IMAGE_DIRECTORY, imageFileName, pizzaPhotoFile);

        pizza.setPhoto(imageFileName);
        pizza = pizzaRepository.save(pizza);
        return convertToDTO(pizza);
    }

    public PizzaDTO update(PizzaDTO pizzaDTO) throws EntityNotFoundException {
        Pizza pizza = pizzaRepository.findById(pizzaDTO.getId()).orElseThrow(EntityNotFoundException::new);
        pizza.setPhoto("photo");
        pizza.setComposition(pizzaDTO.getComposition());
        pizza.setName(pizzaDTO.getName());
        pizza.setPrice(pizzaDTO.getPrice());
        return convertToDTO(pizzaRepository.save(pizza));
    }

    private Pizza convertToEntity(PizzaDTO pizzaDTO) {
        Pizza result = modelMapper.map(pizzaDTO, Pizza.class);
        return result;
    }

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO result = modelMapper.map(pizza, PizzaDTO.class);
        return result;
    }

    private byte[] getBytesFromImage(String fileName) throws IOException {
        try (InputStream inputStream = new FileInputStream(new File(IMAGE_DIRECTORY + fileName))) {
            return IOUtils.toByteArray(inputStream);
        }
    }

    private void saveImage(MultipartFile multipartFile) throws IOException {
        FileUploadUtil.saveFile(IMAGE_DIRECTORY, StringUtils.cleanPath(multipartFile.getOriginalFilename() == null ? ""
                : multipartFile.getOriginalFilename()), multipartFile);
    }
}
