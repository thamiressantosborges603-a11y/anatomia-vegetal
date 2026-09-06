package br.com.anatomiavegetal;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SiteController {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/raizes")
    public String raizes() {
        return "raizes";
    }

    @GetMapping("/caules")
    public String caules() {
        return "caules";
    }

    @GetMapping("/folhas")
    public String folhas() {
        return "folhas";
    }

    @GetMapping("/aluno")
    public String aluno() {
        return "aluno";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String email,
            @RequestParam String senha,
            HttpSession session,
            Model model) {

        if ("aluno@escola.com".equals(email)
                && "123456".equals(senha)) {

            session.setAttribute("logado", true);

            return "redirect:/quiz";
        }

        model.addAttribute(
                "erro",
                "E-mail ou senha incorretos."
        );

        return "aluno";
    }

    @GetMapping("/quiz")
    public String quiz(HttpSession session) {

        if (session.getAttribute("logado") == null) {
            return "redirect:/aluno";
        }

        return "quiz";
    }

    @PostMapping("/quiz")
    public String corrigirQuiz(
            @RequestParam String q1,
            @RequestParam String q2,
            @RequestParam String q3,
            @RequestParam String q4,
            @RequestParam String q5,
            @RequestParam String q6,
            @RequestParam String q7,
            @RequestParam String q8,
            @RequestParam String q9,
            @RequestParam String q10,
            HttpSession session,
            Model model) {

        if (session.getAttribute("logado") == null) {
            return "redirect:/aluno";
        }

        int pontos = 0;

        if ("b".equals(q1)) pontos++;
        if ("a".equals(q2)) pontos++;
        if ("a".equals(q3)) pontos++;
        if ("a".equals(q4)) pontos++;
        if ("a".equals(q5)) pontos++;
        if ("a".equals(q6)) pontos++;
        if ("a".equals(q7)) pontos++;
        if ("a".equals(q8)) pontos++;
        if ("a".equals(q9)) pontos++;
        if ("a".equals(q10)) pontos++;

        model.addAttribute("pontos", pontos);

        return "resultado";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/";
    }
}
