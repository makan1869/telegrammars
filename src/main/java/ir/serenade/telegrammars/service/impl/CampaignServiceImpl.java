package ir.serenade.telegrammars.service.impl;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.domain.Reference;
import ir.serenade.telegrammars.domain.ReportViews;
import ir.serenade.telegrammars.domain.User;
import ir.serenade.telegrammars.dto.DataBean;
import ir.serenade.telegrammars.dto.SeriesBean;
import ir.serenade.telegrammars.repository.CampaignRepository;
import ir.serenade.telegrammars.repository.ReferenceRepository;
import ir.serenade.telegrammars.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by serenade on 8/7/17.
 */
@Service
public class CampaignServiceImpl implements CampaignService {

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ReferenceRepository referenceRepository;


    @Override
    public Campaign findById(Long id) {
        return campaignRepository.findOne(id);
    }

    @Override
    public Set<Campaign> findByOwner(User owner) {
        return campaignRepository.findByOwner(owner);
    }

    @Override
    public Reference findReferenceById(Long id) {
        return referenceRepository.findOne(id);
    }

    @Override
    public Campaign save(Campaign campaign) {
        return campaignRepository.save(campaign);
    }


    @Override
    public DataBean getLineChartData(Campaign campaign) {
        List<SeriesBean> list = new ArrayList<SeriesBean>();
        ArrayList<Long> views = new ArrayList<Long>();
        ArrayList<Long> diffs = new ArrayList<Long>();
        ArrayList<String> dates = new ArrayList<String>();


        long lastView = -1;
        for(ReportViews view : campaign.getReference().getViews()) {
            if(view.getPostLink().equals(campaign.getReference().getPostLink())) {
                if(lastView < 0) {
                    lastView = view.getViews();
                }
                diffs.add(view.getViews() - lastView);
                lastView = view.getViews();
                views.add(view.getViews());
                dates.add(view.getCreationDate().toString());
            }

        }
        list.add(new SeriesBean(campaign.getName(), "#3366cc", views.toArray(new Long[views.size()])));
        list.add(new SeriesBean("تغییرات", "#000000", diffs.toArray(new Long[views.size()])));

        return new DataBean("chart1-container", campaign.getName(), "Views", "Run Dates", dates, list);
    }


}
