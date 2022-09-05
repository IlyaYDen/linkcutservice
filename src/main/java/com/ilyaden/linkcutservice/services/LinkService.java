package com.ilyaden.linkcutservice.services;

import com.ilyaden.linkcutservice.models.Link;
import com.ilyaden.linkcutservice.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LinkService {

    private final LinkRepository linkRepository;

    @Autowired
    public LinkService(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }


    //public void save(Link longLink){
    //    linkRepository.save(longLink);
    //}

    public Link findById(long id) {
        Optional<Link> link = linkRepository.findById(id);
        return link.orElse(null);
    }

    @Transactional
    public Link save(Link link) {
        if(linkRepository.findByLink(link.getLink()).isPresent()){
            Link l = linkRepository.findByLink(link.getLink()).get();

            StringBuilder b = new StringBuilder();
            for(int i = 0; i < l.getId(); i++) b.append(")");
            l.setLinkText("http://localhost:8080/"+b);

            return linkRepository.findByLink(link.getLink()).get();
        }
        Link link1 = new Link();
        link1.setLink(link.getLink());
        Optional<Link> optionalLink = linkRepository.findTopByOrderByIdDesc();


        if(optionalLink.isPresent()){
            link1.setId(optionalLink.get().getId()+1);
            save(link1);
        } else {
            link1.setId(1);
            linkRepository.save(link1);
        }
        StringBuilder b = new StringBuilder();
        for(int i = 0; i < link1.getId(); i++) b.append(")");
        link1.setLinkText("http://localhost:8080/"+b);
        return link1;
    }

    public Link findLinkById(long count) {
        //Link link = linkRepository.findByShortLinkId(count);
        Optional<Link> l = linkRepository.findById(count);
        return l.orElse(null);
    }
    public Link findLinkById(String id) {
        if(Long.getLong(id.trim()) != null) {
            return findLinkById(Long.getLong(id.trim()));
        }
        else {
            long count = id.chars().filter(ch -> ch == ')').count();
            return findLinkById(count);
        }
    }


    public String redirectLink(String id) {
            long count = id.chars().filter(ch -> ch == ')').count();
            Link l = findLinkById(count);
            return l.getLink();

    }


}
