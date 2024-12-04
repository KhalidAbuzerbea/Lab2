package ca.uottawa.ca.calculator2.calculator

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {

    @GetMapping("/")
    fun home(): String {
        return "home"
    }

    @GetMapping("/calculate")
    fun calculate(
        @RequestParam("num1", required = false, defaultValue = "0") num1: String,
        @RequestParam("num2", required = false, defaultValue = "0") num2: String,
        @RequestParam("operation", required = false, defaultValue = "") operation: String,
        model: Model
    ): String {
        try {
            val number1 = num1.toDouble()
            val number2 = num2.toDouble()
            val result = when (operation) {
                "add" -> number1 + number2
                "subtract" -> number1 - number2
                "multiply" -> number1 * number2
                "divide" -> if (number2 != 0.0) number1 / number2 else throw ArithmeticException("Division by zero")
                else -> null
            }

            model.addAttribute("result", result)
            model.addAttribute("error", "")
        } catch (e: Exception) {
            model.addAttribute("result", "")
            model.addAttribute("error", "Invalid input or operation")
        }

        model.addAttribute("num1", num1)
        model.addAttribute("num2", num2)
        model.addAttribute("operation", operation)

        return "home"
    }
}
