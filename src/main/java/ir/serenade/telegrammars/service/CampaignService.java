package ir.serenade.telegrammars.service;

import ir.serenade.telegrammars.domain.Campaign;

/**
 * Created by serenade on 8/7/17.
 */
public interface CampaignService {
    Campaign findByName(String name);

    Campaign save(Campaign campaign);
}
