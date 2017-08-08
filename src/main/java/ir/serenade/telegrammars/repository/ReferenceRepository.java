package ir.serenade.telegrammars.repository;

import ir.serenade.telegrammars.domain.Reference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferenceRepository extends JpaRepository<Reference, Long>{
}
