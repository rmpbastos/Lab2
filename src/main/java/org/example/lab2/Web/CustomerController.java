package org.example.lab2.Web;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.example.lab2.Entities.Customer;
import org.example.lab2.Repositories.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@SessionAttributes({"id"})
@Controller
@AllArgsConstructor
public class CustomerController {

    @Autowired
    private Repo rr;
    List<Customer> xx;


    @GetMapping(path = "/")
    public String InitialPage(Model model) {

        xx = rr.initialdata();
        model.addAttribute("customers", xx);
        model.addAttribute("customer", new Customer());

        return "Main";

    }

    @PostMapping(path = "/empInsert")
    public String Addcustomers(Model model, Customer customer, HttpSession session, ModelMap mm) {

        String em;
        String email = (String) session.getAttribute("id");

        if (email == null) {
            xx.add(customer);
            model.addAttribute("customers", xx);
            return "redirect:Main";
        }

        boolean flag = false;
        for (int x = 0; x < xx.size(); ++x) {
            em = xx.get(x).getCemail();
            if (email.equals(em)) {
                xx.remove(x);
                xx.add(customer);
                flag = true;
                break;
            }
        }

        mm.put("id", null);
        session.removeAttribute("id");
        return "redirect:Main";

    }

    @GetMapping(path = "/Main")
    public String IndexPage(Model model) {

        model.addAttribute("customers", xx);
        model.addAttribute("customer", new Customer());

        return "Main";

    }

    @GetMapping(path = "/editCustomer")
    public String editC(Model model, Customer customer, String id, HttpSession session, ModelMap mm) {

        String em;
        mm.put("id", id);
        for (int x = 0; x < xx.size(); ++x) {

            em = xx.get(x).getCemail();

            if (em.equals(id)) {

                model.addAttribute("customer", xx.get(x));
                break;

            }

        }

        return "Main";

    }

    @GetMapping(path = "/deleteCustomer")
    public String delete(Model model, String id) {

        String em;

        for (int x = 0; x < xx.size(); ++x) {

            em = xx.get(x).getCemail();

            if(em.equals(id)) {
                xx.remove(x);
                break;
            }

        }

        return "redirect:Main";

    }

}