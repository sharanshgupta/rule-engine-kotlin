package com.sharansh.engine

import com.sharansh.contant.RuleNamespace
import com.sharansh.domain.Rule
import com.sharansh.parser.RuleParser
import jakarta.inject.Inject
import jakarta.inject.Singleton
import java.util.*
import java.util.function.Predicate
import java.util.stream.Collectors

@Singleton
abstract class InferenceEngine<INPUT_DATA, OUTPUT_RESULT>{

//    @Inject val ruleParser: RuleParser<INPUT_DATA, OUTPUT_RESULT>

    @Inject
    lateinit var ruleParser: RuleParser<INPUT_DATA, OUTPUT_RESULT>

    /**
     * Run inference engine on set of rules for given data.
     * @param listOfRules
     * @param inputData
     * @return
     */
    open fun run(listOfRules: List<Rule>?, inputData: Any?): OUTPUT_RESULT? {
        if (null == listOfRules || listOfRules.isEmpty()) {
            return null
        }

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        val conflictSet: MutableList<Rule>? = match(listOfRules, inputData as INPUT_DATA)

        //STEP 2 (RESOLVE) : Resolve the conflict and give the selected one rule.
        val resolvedRule: Rule = resolve(conflictSet) ?: return null

        //STEP 3 (EXECUTE) : Run the action of the selected rule on given data and return the output.
        return executeRule(resolvedRule, inputData)
    }

    /**
     * We can use here any pattern matching algo:
     * 1. Rete
     * 2. Linear
     * 3. Treat
     * 4. Leaps
     *
     * Here we are using Linear matching algorithm for pattern matching.
     * @param listOfRules
     * @param inputData
     * @return
     */
    protected open fun match(listOfRules: List<Rule>, inputData: INPUT_DATA): MutableList<Rule>? {
        return listOfRules.stream()
            .filter(
                Predicate<Rule> { rule: Rule ->
                    val condition: String? = rule.condition
                    ruleParser.parseCondition(condition, inputData)
                }
            )
            .collect(Collectors.toList())
    }

    /**
     * We can use here any resolving techniques:
     * 1. Lex
     * 2. Recency
     * 3. MEA
     * 4. Refactor
     * 5. Priority wise
     *
     * Here we are using find first rule logic.
     * @param conflictSet
     * @return
     */
    protected open fun resolve(conflictSet: MutableList<Rule>?): Rule? {
        val rule: Optional<Rule> = conflictSet!!.stream()
            .findFirst()
        return if (rule.isPresent()) {
            rule.get()
        } else null
    }

    /**
     * Execute selected rule on input data.
     * @param rule
     * @param inputData
     * @return
     */
    protected open fun executeRule(rule: Rule?, inputData: INPUT_DATA): OUTPUT_RESULT {
        val outputResult = initializeOutputResult()
        return ruleParser.parseAction(rule?.action, inputData, outputResult)
    }

    public abstract fun initializeOutputResult(): OUTPUT_RESULT
    public abstract fun getRuleNamespace(): RuleNamespace?
}