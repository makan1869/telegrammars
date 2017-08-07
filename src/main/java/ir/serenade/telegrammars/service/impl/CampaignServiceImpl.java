package ir.serenade.telegrammars.service.impl;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.repository.CampaignRepository;
import ir.serenade.telegrammars.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by serenade on 8/7/17.
 */
@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Override
    public Campaign findByName(String name) {
        return campaignRepository.findByName(name);
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }
}
