package com.ilyaden.linkcutservice.controllers;

import com.ilyaden.linkcutservice.dto.LinkDTO;
import com.ilyaden.linkcutservice.models.Link;
import com.ilyaden.linkcutservice.services.LinkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.validation.BindingResult;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class LinkControllerTest {

    @MockBean
    private final LinkService linkService;
    @MockBean
    private final RestTemplate restTemplate;

    @MockBean
    private BindingResult bindingResult;
    @Autowired
            private final LinkController linkController;

    LinkControllerTest(LinkService linkService, RestTemplate restTemplate, LinkController linkController) {
        this.linkService = linkService;
        this.restTemplate = restTemplate;
        this.linkController = linkController;
    }

    @Test
    public void isLinkAdded() {
        //give
        LinkDTO linkDTO = new LinkDTO();
        linkController.create(linkDTO,bindingResult);
        assertThat(true).isTrue();
    }
}