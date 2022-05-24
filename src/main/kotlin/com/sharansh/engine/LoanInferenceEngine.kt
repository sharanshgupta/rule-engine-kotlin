package com.sharansh.engine

import com.sharansh.contant.RuleNamespace
import com.sharansh.domain.LoanDetails
import com.sharansh.domain.UserDetails
import jakarta.inject.Singleton

@Singleton
class LoanInferenceEngine: InferenceEngine<UserDetails, LoanDetails>() {
    override fun initializeOutputResult(): LoanDetails {
        return LoanDetails()
    }

    override fun getRuleNamespace(): RuleNamespace? {
        return RuleNamespace.LOAN
    }

}