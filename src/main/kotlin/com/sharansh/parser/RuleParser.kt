package com.sharansh.parser

import jakarta.inject.Inject
import jakarta.inject.Singleton

@Singleton
class RuleParser<INPUT_DATA, OUTPUT_RESULT> {

    @Inject
    lateinit var mvelParser: MVELParser

    private val INPUT_KEYWORD = "input"
    private val OUTPUT_KEYWORD = "output"

    fun parseCondition(expression: String?, inputData: INPUT_DATA): Boolean {
        val input: MutableMap<String?, Any?> = HashMap()
        input[INPUT_KEYWORD] = inputData
        return mvelParser.parseMvelExpression(expression, input)
    }

    fun parseAction(expression: String?, inputData: INPUT_DATA, outputResult: OUTPUT_RESULT): OUTPUT_RESULT {
        val input: MutableMap<String?, Any?> = HashMap()
        input[INPUT_KEYWORD] = inputData
        input[OUTPUT_KEYWORD] = outputResult
        mvelParser.parseMvelExpression(expression, input)
        return outputResult
    }
}