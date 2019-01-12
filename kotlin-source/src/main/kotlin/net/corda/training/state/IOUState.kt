package net.corda.training.state
import net.corda.finance.*
import net.corda.core.contracts.*
import net.corda.core.contracts.ContractState
import net.corda.core.identity.Party

import java.util.*

/**
 * This is where you'll add the definition of your state object. Look at the unit tests in [IOUStateTests] for
 * instructions on how to complete the [IOUState] class.
 *
 * Remove the "val data: String = "data" property before starting the [IOUState] tasks.
 */
data class IOUState(val amount: Amount<Currency>, val lender: Party, val borrower: Party, val paid: Amount<Currency> = Amount(0, amount.token), override val linearId: UniqueIdentifier = UniqueIdentifier() ): LinearState {
    override val participants: List<Party> get() = listOf(lender, borrower)
    fun pay(amount: Amount<Currency>):IOUState{
        return this.copy(paid=amount+this.paid)
    }
    fun withNewLender(newLender:Party):IOUState{
        return this.copy(lender=newLender)
    }
}