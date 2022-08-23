package com.ilyaden.linkcutservice.repositories;

import com.ilyaden.linkcutservice.models.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    //List<Link> findByShortLinkIdIs(@Param("shortLinkId") int a);
    Optional<Link> findTopByOrderByIdDesc();
    Optional<Link> findByLink(String s);




}
