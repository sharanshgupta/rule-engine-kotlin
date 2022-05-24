package com.sharansh.controller

import com.sharansh.domain.LoanDetails
import com.sharansh.domain.RuleDbModel
import com.sharansh.domain.UserDetails
import com.sharansh.engine.LoanInferenceEngine
import com.sharansh.engine.RuleEngine
import com.sharansh.repository.RulesRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import jakarta.inject.Inject
import java.util.*

@Controller
class RuleEngineController(@Inject val rulesRepository: RulesRepository, @Inject val ruleEngine: RuleEngine,
@Inject val loanInferenceEngine: LoanInferenceEngine) {

//    @Inject
//    lateinit var rulesRepository: RulesRepository

    @Get(value = "/get-all-rules")
    @Produces(MediaType.APPLICATION_JSON)
    fun getOrder(@Body userDetails: UserDetails): List<RuleDbModel?>? {
        println(userDetails.creditScore)
        println(userDetails.monthlySalary)

        val rules = rulesRepository.findAll()

        return rules
    }

    @Post(value = "/loan")
    fun postUserLoanDetails(@Body userDetails: UserDetails?): MutableHttpResponse<LoanDetails>? {
        var result: LoanDetails = ruleEngine.run(loanInferenceEngine, userDetails) as LoanDetails
        result.accountNumber(UUID.randomUUID().toString())
        return HttpResponse.ok<LoanDetails>(result)
//        return ResponseEntity.ok(result)
    }
}