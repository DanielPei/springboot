package com.daniel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.daniel.entity.Applicant;

public interface ApplicantRepository extends JpaRepository<Applicant, Long>{

}
