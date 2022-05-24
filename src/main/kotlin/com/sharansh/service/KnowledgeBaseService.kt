package com.sharansh.service

import com.sharansh.contant.RuleNamespace
import com.sharansh.domain.Rule
import com.sharansh.domain.RuleDbModel
import com.sharansh.repository.RulesRepository
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.stream.Collectors

@Singleton
class KnowledgeBaseService {

    @Inject
    lateinit var rulesRepository: RulesRepository

    fun getAllRules(): List<Rule?>? {
        return rulesRepository.findAll()!!.stream()
            .map { ruleDbModel -> mapFromDbModel(ruleDbModel!!) }
            .collect(Collectors.toList())
    }

    fun getAllRuleByNamespace(ruleNamespace: String?): List<Rule?>? {
        return rulesRepository.findByRuleNamespace(ruleNamespace)!!.stream()
            .map { ruleDbModel -> mapFromDbModel(ruleDbModel!!) }
            .collect(Collectors.toList())
    }

    private fun mapFromDbModel(ruleDbModel: RuleDbModel): Rule? {
        val namespace: String = ruleDbModel.ruleNamespace // or get default
        return Rule(ruleNamespace = RuleNamespace.valueOf(namespace),
        ruleId = ruleDbModel.ruleId,
        condition = ruleDbModel.condition,
        action = ruleDbModel.action,
        description = ruleDbModel.description,
        priority = ruleDbModel.priority)
    }
}