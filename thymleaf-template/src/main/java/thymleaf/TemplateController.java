package thymleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/template")
    public String template(Model model, @RequestParam(value="hasImageIcon", required=false, defaultValue="true") Boolean hasImageIcon) {

        model.addAttribute("hasImageIcon", hasImageIcon);
        model.addAttribute("companyInitial", "AB");
        model.addAttribute("messageTitle", "Google Cloud boss Kurian’s rocky path to profit: We were not in a very good");
        model.addAttribute("description", "U.S. regulators have asked banks for their best and final takeover offers for First Republic\n" +
                " by Sunday afternoon, in a move that authorities hope will calm markets and cap a period of uncertainty for regional lenders.\n" +
                "\n" +
                "JPMorgan Chase\n" +
                " and PNC\n" +
                " are likely bidders for the ailing lender, which would be seized in receivership and immediately sold to the winning bank, according to people with knowledge of the situation. The Wall Street Journal reported those banks’ interest late Friday.");

        return "template";
    }
}