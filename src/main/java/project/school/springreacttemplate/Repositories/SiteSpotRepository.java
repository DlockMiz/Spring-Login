package project.school.springreacttemplate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.school.springreacttemplate.Models.SiteSpotData.SiteSpots;

public interface SiteSpotRepository extends JpaRepository<SiteSpots, Integer> {}
