package ua.training.delivery.controller.manager;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.training.delivery.entity.City;
import ua.training.delivery.service.CityService;
import static ua.training.delivery.constants.Constants.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/manager")
public class CityController {

    @Autowired
    private CityService cityService;

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @GetMapping("/add_city")
    public String addCityGet(Model model) {
        model.addAttribute(CITY_FORM, new City());
        return "manager/managerAddCity";
    }

    @PostMapping("/add_city")
    public String addCityPost(@ModelAttribute @Valid City cityForm, BindingResult bindingResult, Model model,
                              @RequestParam float lngDeg, @RequestParam float lngMin, @RequestParam float lngSec,
                              @RequestParam float latDeg, @RequestParam float latMin, @RequestParam float latSec,
                              @RequestParam String latParam, @RequestParam String lngParam) {

        boolean notValid = false;
        float latitude = cityService.convertToDecimalDegrees(latDeg, latMin, latSec);
        float longitude = cityService.convertToDecimalDegrees(lngDeg, lngMin, lngSec);
        latitude = NORTH.equals(latParam) ? latitude : -1 * latitude;
        longitude = EAST.equals(lngParam) ? longitude : -1 * longitude;

        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors()
                    .forEach(s -> model.addAttribute(NAME_ERROR, s.getRejectedValue()));
            notValid = true;
        }
        if (latitude > 90 || longitude > 90) {
            model.addAttribute(COORDINATE_ERROR, latitude > 90 ? LATITUDE : LONGITUDE);
            notValid = true;
        }
        if (notValid) {
            return "manager/managerAddCity";
        }
        cityForm.setLatitude(latitude);
        cityForm.setLongitude(longitude);
        try {
            cityService.create(cityForm);
            logger.info("New city was added: " + cityForm.getName());
            return "redirect:/success";
        } catch (DataIntegrityViolationException e) {
            model.addAttribute(CITY_EXITS_ERROR, cityForm.getName() + ", " + cityForm.getNameUk());
            logger.error("City: " + cityForm.getName() + " already exists!");
            return "manager/managerAddCity";
        }
    }
}
