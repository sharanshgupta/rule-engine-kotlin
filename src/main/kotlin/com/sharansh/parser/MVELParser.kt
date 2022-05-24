package com.sharansh.parser

import jakarta.inject.Singleton
import org.mvel2.MVEL

@Singleton
class MVELParser {

    fun parseMvelExpression(expression: String?, inputObjects: Map<String?, Any?>?): Boolean {
        try {
            return MVEL.evalToBoolean(expression, inputObjects)
        } catch (e: Exception) {
//            logger.error("Can not parse Mvel Expression : {} Error: {}", expression, e.message)
            println("Can not parse Mvel Expression : {$expression} Error: {${e.message}}")
        }
        return false
    }
}