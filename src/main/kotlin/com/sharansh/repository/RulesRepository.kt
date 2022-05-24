package com.sharansh.repository

import com.sharansh.domain.RuleDbModel
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface RulesRepository: JpaRepository<RuleDbModel, String> {
    fun findByRuleNamespace(ruleNamespace: String?): List<RuleDbModel?>?
    override fun findAll(): List<RuleDbModel?>?
}