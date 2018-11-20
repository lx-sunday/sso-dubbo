package com.sso.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sso.server.entity.SsoUser;

public interface SsoUserDao extends JpaRepository<SsoUser, Long> {

}
