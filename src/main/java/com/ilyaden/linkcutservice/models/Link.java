package com.ilyaden.linkcutservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Link")
public class Link {



    private String link;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Transient
    private String LinkText;

    @Override
    public String toString() {
        return "Link{" +
                "linkId='" + link + '\'' +
                ", shortLinkId='" + id + '\'' +
                '}';
    }
}
