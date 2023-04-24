package com.countrywise.Repos;


import org.springframework.data.jpa.repository.JpaRepository;

import com.countrywise.Model.Country;


public interface CountryRepository extends JpaRepository<Country, Long> {
}
