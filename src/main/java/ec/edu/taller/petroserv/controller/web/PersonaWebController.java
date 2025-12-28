package ec.edu.taller.petroserv.controller.web;

import ec.edu.taller.petroserv.model.Persona;
import ec.edu.taller.petroserv.repository.EmpresaOperadoraRepository;
import ec.edu.taller.petroserv.repository.PersonaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/personas")
public class PersonaWebController {

    private final PersonaRepository personaRepository;
    private final EmpresaOperadoraRepository empresaOperadoraRepository;

    public PersonaWebController(PersonaRepository personaRepository,
                               EmpresaOperadoraRepository empresaOperadoraRepository) {
        this.personaRepository = personaRepository;
        this.empresaOperadoraRepository = empresaOperadoraRepository;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        return "personas/list";
    }

    @GetMapping("/nuevo")
    public String formulario(Model model) {
        model.addAttribute("persona", new Persona());
        model.addAttribute("empresas", empresaOperadoraRepository.findAll());
        return "personas/form";
    }

    @PostMapping
    public String guardar(@Valid @ModelAttribute("persona") Persona persona,
                          BindingResult bindingResult,
                          @RequestParam("empresaOperadoraId") Long empresaOperadoraId,
                          Model model,
                          RedirectAttributes redirectAttributes) {

        if (empresaOperadoraId == null) {
            bindingResult.rejectValue("empresaOperadora", "empresaOperadora",
                    "Debe seleccionar una empresa operadora");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("empresas", empresaOperadoraRepository.findAll());
            return "personas/form";
        }

        empresaOperadoraRepository.findById(empresaOperadoraId)
                .ifPresent(persona::setEmpresaOperadora);

        personaRepository.save(persona);

        redirectAttributes.addFlashAttribute("msg", "Persona registrada correctamente");
        return "redirect:/personas";
    }
}
