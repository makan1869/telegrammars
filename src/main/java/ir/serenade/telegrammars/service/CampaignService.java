package ir.serenade.telegrammars.service;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.domain.Reference;
import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.dto.DataBean;

import java.util.Set;

/**
 * Created by serenade on 8/7/17.
 */
public interface CampaignService {

    Campaign findById(Long id);

    Set<Campaign> findByOwner(User owner);

    Reference findReferenceById(Long id);

    Campaign save(Campaign campaign);

    DataBean getLineChartData(Campaign campaign);
}
