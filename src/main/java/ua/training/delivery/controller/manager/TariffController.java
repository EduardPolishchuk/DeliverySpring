package ua.training.delivery.controller.manager;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.training.delivery.entity.Tariff;
import ua.training.delivery.service.TariffService;

@Controller
@RequestMapping("/manager/tariff")
public class TariffController {

    @Autowired
    private TariffService tariffService;

    @GetMapping
    public String tariffPage(Model model) {
        model.addAttribute("tariff", tariffService.getTariff());
        return "manager/managerChangeTariff";
    }

    @PostMapping("/change")
    public String changeTariff(@ModelAttribute Tariff tariff) {
        tariffService.updateTariff(tariff);
        return "redirect:/manager/tariff";
    }
}
