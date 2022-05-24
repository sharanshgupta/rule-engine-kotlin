package com.sharansh.domain

import com.sharansh.contant.RuleNamespace

data class Rule(var ruleNamespace: RuleNamespace? = null, var ruleId: String? = null, var condition: String? = null,
                var action: String? = null, var priority: Integer? = null, var description: String? = null) {}
