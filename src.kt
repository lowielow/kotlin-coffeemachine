const val ZERO = 0
const val INITIAL_WATER = 400
const val INITIAL_MILK = 540
const val INITIAL_BEAN = 120
const val INITIAL_CUP = 9
const val INITIAL_MONEY = 550
const val ESPRESSO_WATER = 250
const val ESPRESSO_MILK = 0
const val ESPRESSO_BEAN = 16
const val ESPRESSO_PRICE = 4
const val LATTE_WATER = 350
const val LATTE_MILK = 75
const val LATTE_BEAN = 20
const val LATTE_PRICE = 7
const val CAPPUCINO_WATER = 200
const val CAPPUCINO_MILK = 100
const val CAPPUCINO_BEAN = 12
const val CAPPUCINO_PRICE = 6

enum class Coffee(val water: Int, val milk: Int, val bean: Int, val price: Int) {
    ESPRESSO(ESPRESSO_WATER, ESPRESSO_MILK, ESPRESSO_BEAN, ESPRESSO_PRICE),
    LATTE(LATTE_WATER, LATTE_MILK, LATTE_BEAN, LATTE_PRICE),
    CAPPUCINO(CAPPUCINO_WATER, CAPPUCINO_MILK, CAPPUCINO_BEAN, CAPPUCINO_PRICE);
}

enum class State {
    CHOOSE_ACTION, CHOOSE_COFFEE, FILL_WATER, FILL_MILK, FILL_BEAN, FILL_CUP
}

class coffeeMachine {
    
    var water: Int = INITIAL_WATER
    var milk: Int = INITIAL_MILK
    var bean: Int = INITIAL_BEAN
    var cup: Int = INITIAL_CUP
    var money: Int = INITIAL_MONEY
    var state: State = State.CHOOSE_ACTION
    
    init {
        println("\nWrite action (buy, fill, take, remaining, exit):")
    }
    
   	fun handleInput(input: String) {
        when (state) {
            State.CHOOSE_ACTION -> {
                when (input) {
                    "buy" -> {
                        state = State.CHOOSE_COFFEE
                        println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu")
                    }
                    "fill" -> {
                        state = State.FILL_WATER
                        println("\nWrite how many ml of water you want to add:")
                    }
                    "take" -> {
                        println("\nI gave you $$money")
                        money = ZERO
                        println("\nWrite action (buy, fill, take, remaining, exit):")
                    }
                    "remaining" -> {
                        println("\nThe coffee machine has:")
                        println("$water ml of water")
                        println("$milk ml of milk")
                        println("$bean g of coffee beans")
                        println("$cup disposable cups")
                        println("$$money of money")
                        println("\nWrite action (buy, fill, take, remaining, exit):")
                    }
                }
            }
            State.CHOOSE_COFFEE -> {
                when (input) {
                    "1" -> {
                        when {
                            water < Coffee.ESPRESSO.water -> println("Sorry, not enough water!")
                            milk < Coffee.ESPRESSO.milk -> println("Sorry, not enough milk!")
                            bean < Coffee.ESPRESSO.bean -> println("Sorry, not enough bean!")
                            cup == 0 -> println("Sorry, not enough cup!")
                            else -> {
                                println("I have enough resources, making you a coffee!")
                                water -= Coffee.ESPRESSO.water
                                milk -= Coffee.ESPRESSO.milk
                                bean -= Coffee.ESPRESSO.bean
                                money += Coffee.ESPRESSO.price
                                cup--
                            }
                        }
                    }
                    "2" -> {
                        when {
                            water < Coffee.LATTE.water -> println("Sorry, not enough water!")
                            milk < Coffee.LATTE.milk -> println("Sorry, not enough milk!")
                            bean < Coffee.LATTE.bean -> println("Sorry, not enough bean!")
                            cup == 0 -> println("Sorry, not enough cup!")
                            else -> {
                                println("I have enough resources, making you a coffee!")
                                water -= Coffee.LATTE.water
                                milk -= Coffee.LATTE.milk
                                bean -= Coffee.LATTE.bean
                                money += Coffee.LATTE.price
                                cup--
                            }
                        }                        
                    }
                    "3" -> {
                        when {
                            water < Coffee.CAPPUCINO.water -> println("Sorry, not enough water!")
                            milk < Coffee.CAPPUCINO.milk -> println("Sorry, not enough milk!")
                            bean < Coffee.CAPPUCINO.bean -> println("Sorry, not enough bean!")
                            cup == 0 -> println("Sorry, not enough cup!")
                            else -> {
                                println("I have enough resources, making you a coffee!")
                                water -= Coffee.CAPPUCINO.water
                                milk -= Coffee.CAPPUCINO.milk
                                bean -= Coffee.CAPPUCINO.bean
                                money += Coffee.CAPPUCINO.price
                                cup--
                            }
                        }
                    }
                    "back" -> {}
                } 
                state = State.CHOOSE_ACTION
                println("\nWrite action (buy, fill, take, remaining, exit):")
            }
            State.FILL_WATER -> {
                println(" Write how many ml of water you want to add:")
                water += input.toInt()
                state = State.FILL_MILK
            }
            State.FILL_MILK -> {
                println(" Write how many ml of milk you want to add:")
                milk += input.toInt()
                state = State.FILL_BEAN 
            }
            State.FILL_BEAN -> {
                println(" Write how many grams of coffee beans you want to add:")
                bean += input.toInt()
                state = State.FILL_CUP
            }
            State.FILL_CUP -> {
                println(" Write how many disposable cups you want to add:")
                cup += input.toInt()
                state = State.CHOOSE_ACTION
            }
        }
    }
   
}

fun main() {
    
    val myCoffeeMachine = coffeeMachine()	
    
    while (true) {
        val input = readln()
        if (input == "exit") {
            break     
        } else {
            myCoffeeMachine.handleInput(input)   
        }
    }

}   
