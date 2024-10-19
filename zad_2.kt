//Маємо такий код

import Currency.EUR 
import Currency.UAH 
import Currency.USD 
 
data class Trader(val name: String, val city: String) 
 
data class Transaction(val trader: Trader, val year: Int, val month: Int, val 
value: Int, val currency: Currency) 
 
enum class Currency { 
    UAH, USD, EUR 
} 
 
fun main() { 
    val raoul = Trader("Raoul", "Cambridge") 
    val mario = Trader("Mario", "Milan") 
    val alan = Trader("Alan", "Cambridge") 
    val brian = Trader("Brian", "Cambridge") 
    val transactions = listOf( 
        Transaction(brian, 2011, 12, 300, UAH), 
        Transaction(raoul, 2012, 10, 1000, UAH), 
        Transaction(raoul, 2011, 11, 400, USD), 
        Transaction(mario, 2012, 9, 710, UAH), 
        Transaction(mario, 2012, 7, 700, USD), 
        Transaction(alan, 2012, 4, 950, EUR)
    ) 
    //Приклад коду:
    //val transactionsIn2011 = transactions
    //.filter { it.year == 2011 }
    //.sortedBy { it.value }
//transactionsIn2011.forEach { println(it) }
//}

//1. Знайти усі транзакції за 2011 рік і посортувати за вартістю (від малого до високого). 

val transactionsIn2011 = transactions
    .filter { it.year == 2011 }
    .sortedBy { it.value }
transactionsIn2011.forEach { println(it) }

//Вийде: Transaction(trader=Trader(name=Brian, city=Cambridge), year=2011, month=12, value=300, currency=UAH)
//Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2011, month=11, value=400, currency=USD)

//2. У яких унікальних містах працюють трейдери?

val uniqueCities = transactions
    .map { it.trader.city }
    .distinct()
println(uniqueCities)

//Вийде: [Cambridge, Milan]

//3. Знайдіть усіх трейдерів із Кембриджа та відсортуйте їх за назвою:

val tradersFromCambridge = transactions
    .map { it.trader }
    .filter { it.city == "Cambridge" }
    .distinct()
    .sortedBy { it.name }
tradersFromCambridge.forEach { println(it) }

//Вийде: Trader(name=Alan, city=Cambridge); Trader(name=Brian, city=Cambridge); Trader(name=Raoul, city=Cambridge)

//4. Поверніть рядок імен усіх трейдерів, відсортованих за алфавітом:

val traderNames = transactions
    .map { it.trader.name }
    .distinct()
    .sorted()
    .joinToString(", ")
println(traderNames)

//Вийде: Alan, Brian, Mario, Raoul

//5. Чи є трейдери в Мілані? 

val hasTradersInMilan = transactions
    .any { it.trader.city == "Milan" }
println("Чи є трейдери в Мілані: $hasTradersInMilan")


//Вийде: Чи є трейдери в Мілані: true

//6. Виведіть у консоль всі значення транзакцій від трейдерів, які проживають у Кембриджі

val cambridgeTransactions = transactions
    .filter { it.trader.city == "Cambridge" }
    .map { it.value }
println(cambridgeTransactions)

//Вийде: [300, 1000, 400, 950]

//7. Знайдіть транзакцію з найбільшою вартістю:

val maxTransaction = transactions.maxByOrNull { it.value }
println("Найбільша транзакція: $maxTransaction")

//Вийде: Найбільша транзакція: Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2012, month=10, value=1000, currency=UAH)

//8. Згрупуйте всі транзакції за валютою. 

val transactionsByCurrency = transactions.groupBy { it.currency }
transactionsByCurrency.forEach { (currency, transactions) ->
    println("$currency: $transactions")
}

//Вийде: UAH: [Transaction(trader=Trader(name=Brian, city=Cambridge), year=2011, month=12, value=300, currency=UAH), Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2012, month=10, value=1000, currency=UAH), Transaction(trader=Trader(name=Mario, city=Milan), year=2012, month=9, value=710, currency=UAH)]
//USD: [Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2011, month=11, value=400, currency=USD), Transaction(trader=Trader(name=Mario, city=Milan), year=2012, month=7, value=700, currency=USD)]
//EUR: [Transaction(trader=Trader(name=Alan, city=Cambridge), year=2012, month=4, value=950, currency=EUR)]

//9. Знайдіть суму транзакцій у гривнях. 

val sumInUAH = transactions
    .filter { it.currency == UAH }
    .sumOf { it.value }
println("Сума транзакцій у гривнях: $sumInUAH")


//Вийде: Сума транзакцій у гривнях: 2010

//10. Створіть рядок, у якому буде виведена послідовність транзакцій відсортована за датою у наступному вигляді (<назва параметру, який потрібно вставити> ):

val sortedTransactions = transactions
    .sortedWith(compareBy({ it.year }, { it.month }))
    .mapIndexed { index, transaction ->
        "${index + 1}. ${transaction.month}-${transaction.year}: ${transaction.value} ${transaction.currency}"
    }
    .joinToString(" -> ")

println(sortedTransactions)

//Вийде: 1. 11-2011: 400 USD -> 2. 12-2011: 300 UAH -> 3. 4-2012: 950 EUR -> 4. 7-2012: 700 USD -> 5. 9-2012: 710 UAH -> 6. 10-2012: 1000 UAH
