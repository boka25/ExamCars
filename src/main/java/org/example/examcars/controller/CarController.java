package org.example.examcars.controller;


import org.example.examcars.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.example.examcars.repository.CarRepository;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {
    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping
    public String getAllCars(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", carRepository.findAll());
        return "home/car-list";
    }
    @GetMapping("/new")
    public String createProductForm(Model model)
    {
        model.addAttribute("car", new Car());
        return "home/car-form";
    }
    @PostMapping
    public String saveCar(@ModelAttribute Car car) {
        if(car.getId() == 0)
            carRepository.save(car);
        return "redirect:/cars";
    }
}
