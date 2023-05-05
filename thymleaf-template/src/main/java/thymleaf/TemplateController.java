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
        User adminActivate = new User("Team");

        model.addAttribute("hasImageIcon", hasImageIcon);
        model.addAttribute("companyInitial", "AB");
        model.addAttribute("messageTitle", "Google Cloud boss Kurian’s rocky path to profit: We were not in a very good");
        model.addAttribute("description", "U.S. regulators have asked banks for their best and final takeover offers for First Republic\n" +
                " by Sunday afternoon, in a move that authorities hope will calm markets and cap a period of uncertainty for regional lenders.\n" +
                "\n" +
                "JPMorgan Chase\n" +
                " and PNC\n" +
                " are likely bidders for the ailing lender, which would be seized in receivership and immediately sold to the winning bank, according to people with knowledge of the situation. The Wall Street Journal reported those banks’ interest late Friday.");
        model.addAttribute("icon", "data:image/png;base64," + "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");

        model.addAttribute("adminActivate", adminActivate);
        return "template";
    }
}