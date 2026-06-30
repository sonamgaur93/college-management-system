package com.cms.repository;

import com.cms.entity.Otp;
import com.cms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Long> {

    Optional<Otp> findByUserAndVerifiedFalse(User user);

    void deleteByUserAndVerifiedFalse(User user);
}