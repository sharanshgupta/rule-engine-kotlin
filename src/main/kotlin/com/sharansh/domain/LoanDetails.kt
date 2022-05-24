package com.sharansh.domain

data class LoanDetails(
    var accountNumber: String? = "",
    var processingFees: Double? = 0.0,
    var approvalStatus: Boolean? = false,
    var interestRate: Float? = 0.0f,
    var sanctionedPercentage: Float? = 0.0f
) {
    fun accountNumber(accountNumber: String) {
        this.accountNumber = accountNumber
    }

}