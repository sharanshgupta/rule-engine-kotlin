package com.sharansh.engine

import com.sharansh.domain.Rule
import com.sharansh.service.KnowledgeBaseService
import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RuleEngine {

    @Inject
    lateinit var knowledgeBaseService: KnowledgeBaseService

    fun run(inferenceEngine: InferenceEngine<*, *>, inputData: Any?): Any? {
        val ruleNamespace: String = inferenceEngine.getRuleNamespace().toString()
        //TODO: Here for each call, we are fetching all rules from db. It should be cache.
        val allRulesByNamespace: List<Rule?>? =
            knowledgeBaseService.getAllRuleByNamespace(ruleNamespace)
        return inferenceEngine.run(allRulesByNamespace as List<Rule>?, inputData)
    }
}