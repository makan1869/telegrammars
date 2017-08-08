package ir.serenade.telegrammars.web;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.domain.Reference;
import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.dto.DataBean;
import ir.serenade.telegrammars.service.CampaignService;
import ir.serenade.telegrammars.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by serenade on 8/7/17.
 */
@Controller
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @Autowired
    UserService userService;


    @RequestMapping("/campaign/list")
    public String list(Model model) {
        User currentUser = userService.getLoggedInUser(false);
        if(currentUser != null) {
            model.addAttribute("campaigns",campaignService.findByOwner(currentUser));
        }
        return "campaign/list";
    }

    @RequestMapping("/campaign/show")
    public String show(@RequestParam("id") Long id, Model model) {
        Campaign campaign = campaignService.findById(id);
        User currentUser = userService.getLoggedInUser(false);
        if(campaign != null && currentUser != null && campaign.getOwner().equals(currentUser)) {
            model.addAttribute("campaign",campaign);
        }

        return "campaign/show";
    }


    @RequestMapping(value = "/campaign/new", method = RequestMethod.GET)
    public String newCampaign() {
        return "campaign/create";
    }


    @RequestMapping({"/campaign/chart"})
    @ResponseBody
    public DataBean showLineChart(@RequestParam("id") Long id) {
        Campaign  campaign = campaignService.findById(id);
        if(campaign != null) {
            return campaignService.getLineChartData(campaign);
        } else {
            return null;
        }
    }


    /*
    @RequestMapping(value = "/campaign/create", method = RequestMethod.POST)
    public String save(@Valid Campaign campaign, BindingResult result) {
        if (result.hasErrors()) {
            return "campaign/create";
        }
        Campaign persistedCampaign = campaignService.save(campaign);
        if (persistedCampaign != null) {
            return "campaign/show";
        } else {
            return "campaign/create";
        }
    }
    */
}
