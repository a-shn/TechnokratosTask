package ru.itis.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dtos.CvDto;
import ru.itis.models.Cv;
import ru.itis.security.details.UserDetailsImpl;
import ru.itis.services.interfaces.CvService;

import java.util.List;

@Controller
@AllArgsConstructor
public class CvController {
    private final CvService cvService;

    @GetMapping("/new/cv")
    @PreAuthorize("isAuthenticated()")
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
    @PreAuthorize("@securityUtils.isCvOwner(#userDetails, #id)")
    public String updateCv(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
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
    @PreAuthorize("isAuthenticated()")
    public String addCv(CvDto cvDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cvDto.setOwnerId(userDetails.getUser().get_id());
        cvService.publish(cvDto);
        return "redirect:/cvs";
    }

    @PostMapping("/update/cv/{id}")
    @PreAuthorize("@securityUtils.isCvOwner(#userDetails, #id)")
    public String updateCv(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails, CvDto cvDto) {
        cvDto.setOwnerId(userDetails.getUser().get_id());
        cvService.updateCv(cvDto, id);
        return "redirect:/cv/" + id;
    }

    @PostMapping("/delete/cv/{id}")
    @PreAuthorize("@securityUtils.isCvOwner(#userDetails, #id)")
    public String deleteCvById(@PathVariable String id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        cvService.deleteCvById(id);
        return "redirect:/cvs";
    }

    @GetMapping("/api/cv/{id}")
    public @ResponseBody ResponseEntity<Cv> getCvAsJson(@PathVariable String id) {
        return ResponseEntity.ok(cvService.getCvById(id));
    }

    @GetMapping("/api/cvs")
    public @ResponseBody ResponseEntity<List<Cv>> getCvsAsJson() {
        return ResponseEntity.ok(cvService.getCvs());
    }
}
