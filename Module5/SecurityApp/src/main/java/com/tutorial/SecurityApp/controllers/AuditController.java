package com.tutorial.SecurityApp.controllers;

import com.tutorial.SecurityApp.entities.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/audit")
public class AuditController {

    private final EntityManagerFactory entityManagerFactory;

    @GetMapping("/posts/{postId}")
    public List<PostEntity> getPostRevisions(@PathVariable Long postId) {
        AuditReader auditReader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());
        List<Number> revisions = auditReader.getRevisions(PostEntity.class, postId);
        return revisions
                .stream()
                .map(revisionNumber -> auditReader.find(PostEntity.class, postId, revisionNumber))
                .toList();
    }

}
