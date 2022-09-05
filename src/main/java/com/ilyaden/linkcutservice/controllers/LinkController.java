package com.ilyaden.linkcutservice.controllers;

import com.ilyaden.linkcutservice.dto.LinkDTO;
import com.ilyaden.linkcutservice.models.Link;
import com.ilyaden.linkcutservice.services.LinkService;
import com.ilyaden.linkcutservice.util.LinkErrorResponse;
import com.ilyaden.linkcutservice.util.LinkNotCreatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("")
public class LinkController {

    private final LinkService linkService;

    private final RestTemplate restTemplate;

    public LinkController(LinkService linkService, RestTemplate restTemplate) {
        this.linkService = linkService;
        this.restTemplate = restTemplate;
    }


    @GetMapping("/api/get/{id}")
    public Link getLink(@PathVariable("id") String id){
        return linkService.findLinkById(id);
    }

    @GetMapping("goto/{id}")
    public RedirectView gotoLink(@PathVariable("id") String id) {
        return new RedirectView(linkService.redirectLink(id));
    }


    @PostMapping("/api/add")
    public Link create(@RequestBody @Valid LinkDTO linkDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            System.out.println("test");
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for(FieldError error : errors){
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new LinkNotCreatedException(errorMsg.toString());
        }
        Link sl = linkService.save(convertToLink(linkDTO));
        return sl;
    }

    @ExceptionHandler
    private ResponseEntity<LinkErrorResponse> handleException(LinkNotCreatedException e){
        LinkErrorResponse linkErrorResponse = new LinkErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(linkErrorResponse,HttpStatus.BAD_REQUEST);
    }

    private Link convertToLink(LinkDTO linkDTO) {
        Link link = new Link();
        link.setLink(linkDTO.getLink());
        return link;
    }
}
