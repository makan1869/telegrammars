package ir.serenade.telegrammars.repository;

import ir.serenade.telegrammars.domain.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by serenade on 8/7/17.
 */
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    public Campaign findByName(String name);
}
