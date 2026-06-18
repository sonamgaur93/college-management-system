package com.cms.dao;

import com.cms.entity.CollegeCourse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CollegeCourseDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "fees",
            "totalSeats",
            "availableSeats",
            "createdAt",
            "updatedAt"
    );

    public List<CollegeCourse> findAllCollegeCourses(
            Long collegeId,
            Long courseId,
            String search,
            Boolean status,
            Boolean admissionOpen,
            int page,
            int size,
            String sortBy,
            String sortOrder
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<CollegeCourse> cq = cb.createQuery(CollegeCourse.class);

        Root<CollegeCourse> root = cq.from(CollegeCourse.class);

        // Joins
        Join<Object, Object> collegeJoin = root.join("college", JoinType.LEFT);
        Join<Object, Object> courseJoin = root.join("course", JoinType.LEFT);

        List<Predicate> predicates = buildPredicates(
                cb,
                root,
                collegeJoin,
                courseJoin,
                collegeId,
                courseId,
                search,
                status,
                admissionOpen
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

        TypedQuery<CollegeCourse> query = entityManager.createQuery(cq);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    private List<Predicate> buildPredicates(
            CriteriaBuilder cb,
            Root<CollegeCourse> root,
            Join<Object, Object> collegeJoin,
            Join<Object, Object> courseJoin,
            Long collegeId,
            Long courseId,
            String search,
            Boolean status,
            Boolean admissionOpen
    ) {

        List<Predicate> predicates = new ArrayList<>();

        // ✅ THIS IS YOUR NATIVE QUERY LOGIC (IMPORTANT FIX)
        if (collegeId != null) {
            predicates.add(cb.equal(collegeJoin.get("id"), collegeId));
        }

        if (courseId != null) {
            predicates.add(cb.equal(courseJoin.get("id"), courseId));
        }

        // Search filter
        if (search != null && !search.trim().isEmpty()) {

            List<Predicate> searchPredicates = new ArrayList<>();

            try {
                Integer intValue = Integer.valueOf(search.trim());

                searchPredicates.add(
                        cb.equal(root.get("availableSeats"), intValue)
                );

                searchPredicates.add(
                        cb.equal(root.get("totalSeats"), intValue)
                );

            } catch (NumberFormatException ignored) {
            }

            try {
                searchPredicates.add(
                        cb.equal(root.get("fees"), new java.math.BigDecimal(search.trim()))
                );
            } catch (NumberFormatException ignored) {
            }

            if (!searchPredicates.isEmpty()) {
                predicates.add(
                        cb.or(searchPredicates.toArray(new Predicate[0]))
                );
            }
        }

        // Status filter
        if (status != null) {
            predicates.add(cb.equal(root.get("status"), status));
        }

        // Admission open filter
        if (admissionOpen != null) {
            predicates.add(cb.equal(root.get("admissionOpen"), admissionOpen));
        }

        return predicates;
    }
}