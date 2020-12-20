package com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.services;

import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.dto.PizzaDTO;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.exceptions.EntityNotFoundException;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.model.Pizza;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.repository.PizzaRepository;
import com.ssau.best1team.pizzadelivering.pizzadeliveringmanagement.utils.FileUploadUtil;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PizzaService {

    private PizzaRepository pizzaRepository;
    private ModelMapper modelMapper;

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
        Pizza pizza = new Pizza();
        pizza.setComposition(pizzaDTO.getComposition());
        pizza.setPrice(pizzaDTO.getPrice());
        pizza.setName(pizzaDTO.getName());

        String savedImageName = saveFile(pizzaPhotoFile);

        pizza.setPhoto(savedImageName);

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

    private PizzaDTO convertToDTO(Pizza pizza) {
        PizzaDTO result = modelMapper.map(pizza, PizzaDTO.class);
        if (!pizza.getPhoto().isEmpty()) {
            try {
                File pizzaImageFile = new File(Pizza.IMAGE_DIRECTORY + pizza.getPhoto());
                byte[] fileContent = FileUtils.readFileToByteArray(pizzaImageFile);
                String encodedContent = Base64.getEncoder().encodeToString(fileContent);
                result.setEncodedImage(encodedContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String saveFile(MultipartFile multipartFile) throws IOException {
        String imageFileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        FileUploadUtil.saveFile(Pizza.IMAGE_DIRECTORY, imageFileName, multipartFile);
        return imageFileName;
    }

}
