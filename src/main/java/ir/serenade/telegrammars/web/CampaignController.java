package ir.serenade.telegrammars.web;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Created by serenade on 8/7/17.
 */
@Controller
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @RequestMapping(value = "/campaign/new", method = RequestMethod.GET)
    public String newCampaign() {
        return "campaign/create";
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
