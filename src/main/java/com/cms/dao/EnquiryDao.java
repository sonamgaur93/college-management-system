package com.cms.dao;

import com.cms.entity.Enquiry;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class EnquiryDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "studentName",
            "mobile",
            "email",
            "createdAt",
            "updatedAt"
    );

    public List<Enquiry> findAllEnquiries(
            Long collegeCourseId,   // ✅ ADDED THIS
            String search,
            int page,
            int size,
            String sortBy,
            String sortOrder
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Enquiry> cq = cb.createQuery(Enquiry.class);

        Root<Enquiry> root = cq.from(Enquiry.class);

        // Joins
        Join<Object, Object> collegeCourseJoin = root.join("collegeCourse", JoinType.LEFT);
        Join<Object, Object> collegeJoin = collegeCourseJoin.join("college", JoinType.LEFT);
        Join<Object, Object> courseJoin = collegeCourseJoin.join("course", JoinType.LEFT);

        List<Predicate> predicates = buildPredicates(
                cb,
                root,
                collegeCourseJoin,
                collegeJoin,
                courseJoin,
                collegeCourseId,
                search
        );

        cq.where(predicates.toArray(new Predicate[0]));

        // Sorting
        if (sortBy == null || !ALLOWED_SORT_FIELDS.contains(sortBy)) {
            sortBy = "createdAt";
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            cq.orderBy(cb.desc(root.get(sortBy)));
        } else {
            cq.orderBy(cb.asc(root.get(sortBy)));
        }

        TypedQuery<Enquiry> query = entityManager.createQuery(cq);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    private List<Predicate> buildPredicates(
            CriteriaBuilder cb,
            Root<Enquiry> root,
            Join<Object, Object> collegeCourseJoin,
            Join<Object, Object> collegeJoin,
            Join<Object, Object> courseJoin,
            Long collegeCourseId,
            String search
    ) {

        List<Predicate> predicates = new ArrayList<>();

        if (collegeCourseId != null) {
            predicates.add(
                    cb.equal(collegeCourseJoin.get("id"), collegeCourseId)
            );
        }

        if (search != null && !search.trim().isEmpty()) {

            String pattern = "%" + search.toLowerCase() + "%";

            predicates.add(
                    cb.or(
                            cb.like(cb.lower(root.get("studentName")), pattern),
                            cb.like(cb.lower(root.get("mobile")), pattern),
                            cb.like(cb.lower(root.get("email")), pattern),
                            cb.like(cb.lower(root.get("message")), pattern)
                    )
            );
        }

        return predicates;
    }
}