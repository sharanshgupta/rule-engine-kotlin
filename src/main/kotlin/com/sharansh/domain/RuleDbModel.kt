package com.sharansh.domain

//import io.micronaut.data.annotation.MappedEntity
import com.sharansh.contant.RuleNamespace
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
//@MappedEntity
@Table(name = "rules")
class RuleDbModel {
//    @Id
    @Column(name = "rule_namespace")
    lateinit var ruleNamespace: String

    @Id
    @Column(name = "rule_id")
    lateinit var ruleId: String

    @Column(name = "condition")
    lateinit var condition: String

    @Column(name = "action")
    lateinit var action: String

    @Column(name = "priority")
    lateinit var priority: Integer

    @Column(name = "description")
    lateinit var description: String

}