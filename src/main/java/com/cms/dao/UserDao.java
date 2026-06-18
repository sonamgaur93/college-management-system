package com.cms.dao;

import com.cms.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of(
            "fullName",
            "email",
            "status",
            "createdAt",
            "updatedAt",
            "username"
    );

    public List<User> findAllUsers(
            String search,
            Boolean status,
            int page,
            int size,
            String sortBy,
            String sortOrder
    ) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);

        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = buildPredicates(
                cb,
                root,
                search,
                status
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

        TypedQuery<User> query = entityManager.createQuery(cq);

        // Pagination
        query.setFirstResult(page * size);
        query.setMaxResults(size);

        return query.getResultList();
    }

    private List<Predicate> buildPredicates(
            CriteriaBuilder cb,
            Root<User> root,
            String search,
            Boolean status
    ) {

        List<Predicate> predicates = new ArrayList<>();

        // Search filter
        if (search != null && !search.trim().isEmpty()) {

            String pattern = "%" + search.toLowerCase() + "%";

            predicates.add(
                    cb.or(
                            cb.like(cb.lower(root.get("fullName")), pattern),
                            cb.like(cb.lower(root.get("email")), pattern),
                            cb.like(cb.lower(root.get("username")), pattern)
                    )
            );
        }

        // Status filter
        if (status != null) {
            predicates.add(cb.equal(root.get("status"), status));
        }

        return predicates;
    }
}