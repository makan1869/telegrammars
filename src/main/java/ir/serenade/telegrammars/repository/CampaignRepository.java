package ir.serenade.telegrammars.repository;

import ir.serenade.telegrammars.domain.Campaign;
import ir.serenade.telegrammars.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by serenade on 8/7/17.
 */
public interface CampaignRepository extends JpaRepository<Campaign, Long>{
    public Set<Campaign> findByOwner(User owner);
}
