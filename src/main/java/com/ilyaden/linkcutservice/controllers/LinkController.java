package com.ilyaden.linkcutservice.controllers;

import com.ilyaden.linkcutservice.models.Link;
import com.ilyaden.linkcutservice.services.LinkService;
import com.ilyaden.linkcutservice.util.LinkErrorResponse;
import com.ilyaden.linkcutservice.util.LinkNotCratedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("")
public class LinkController {

    private final LinkService linkService;
    public LinkController(LinkService linkService) {
        this.linkService = linkService;
    }


    @GetMapping("{id}")
    public Link getLink(@PathVariable("id") String id){
        long count = id.chars().filter(ch -> ch == ')').count();
        Link l = linkService.findShortById(count);
        return l;
    }
    @GetMapping("goto/{id}")
    public RedirectView gotoLink(@PathVariable("id") String id) {
        long count = id.chars().filter(ch -> ch == ')').count();
        Link l = linkService.findShortById(count);
        return new RedirectView(l.getLink());
    }


    @PostMapping("/api/add")
    public Link create(@RequestBody Link Link, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder errorText = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorText.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new LinkNotCratedException(errors.toString());
        }
        System.out.println(Link);
        Link sl = linkService.addLink(Link);
        return sl;
    }
    @ExceptionHandler
    private ResponseEntity<LinkErrorResponse> handleException(LinkNotCratedException e){
        LinkErrorResponse linkErrorResponse = new LinkErrorResponse(
                "",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(linkErrorResponse,HttpStatus.BAD_REQUEST);
    }
}
