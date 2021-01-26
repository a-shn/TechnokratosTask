package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dtos.CvDto;
import ru.itis.models.Cv;
import ru.itis.services.interfaces.CvService;

import java.util.List;

@Controller
@AllArgsConstructor
public class CvController {
    private final CvService cvService;

    @GetMapping("/new/cv")
    public String newCvPage() {
        return "newCv";
    }

    @GetMapping("/cv/{id}")
    public String getCv(@PathVariable String id, Model model) {
        model.addAttribute("id", id);
        return "cv";
    }

    @GetMapping("/cvs")
    public String getCvsPage() {
        return "cvs";
    }

    @GetMapping("/update/cv/{id}")
    public String updateCv(@PathVariable String id, Model model) {
        CvDto cvDto = CvDto.from(cvService.getCvById(id));
        model.addAttribute("id", id);
        model.addAttribute("name", cvDto.getName());
        model.addAttribute("surname", cvDto.getSurname());
        model.addAttribute("contacts", cvDto.getContacts());
        model.addAttribute("bornDate", cvDto.getBornDate());
        model.addAttribute("desiredPosition", cvDto.getDesiredPosition());
        model.addAttribute("aboutMe", cvDto.getAboutMe());
        return "updateCv";
    }

    @PostMapping("/new/cv")
    public void addCv(CvDto cvDto) {
        cvService.publish(cvDto);
    }

    @PostMapping("/update/cv/{id}")
    public void updateCv(@PathVariable String id, CvDto cvDto) {
        cvService.updateCv(cvDto, id);
    }

    @GetMapping("/api/cv/{id}")
    public @ResponseBody ResponseEntity<Cv> getCvAsJson(@PathVariable String id) {
        return ResponseEntity.ok(cvService.getCvById(id));
    }

    @GetMapping("/api/cvs")
    public @ResponseBody ResponseEntity<List<Cv>> getCvsAsJson() {
        return ResponseEntity.ok(cvService.getCvs());
    }

    @PostMapping("/api/delete/cv/{id}")
    public void deleteCvById(@PathVariable String id) {
        cvService.deleteCvById(id);
    }
}
