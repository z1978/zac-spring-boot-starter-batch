package com.zac.batch.fortest;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SocialMediaSiteRepository extends JpaRepository<SocialMediaSite, Long> {

	SocialMediaSite findByName(String name);

}
