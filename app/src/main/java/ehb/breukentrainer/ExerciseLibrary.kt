package ehb.breukentrainer

import kotlin.random.Random

class ExerciseLibrary(maxNumber: Int) {

    private var mChoices = arrayOf("0", "1", "2", "3")
    private var maxNum = maxNumber
    private var mCorrectAnswer = ""

    fun getNextQuestion(): String? {
        var firstNum = Random.nextInt(0, maxNum);
        var firstDen = Random.nextInt(1, maxNum);
        var operatorInt = Random.nextInt(1, 4);
        var operator = when (operatorInt) {
            1 -> "+"
            2 -> "-"
            3 -> "*"
            4 -> "/"
            else -> {
                ""
            }
        }

        var secondNum = Random.nextInt(1, maxNum);
        var secondDen = Random.nextInt(1, maxNum);

        mChoices = arrayOf("0", "1", "2", "3")

        var frac1 = Frac(firstNum, firstDen)
        var frac2 = Frac(secondNum, secondDen)

        var fracResult: Frac = frac1
        if (operatorInt == 1) fracResult = frac1.plus(frac2)
        if (operatorInt == 2) fracResult = frac1.minus(frac2)
        if (operatorInt == 3) fracResult = frac1.times(frac2)
        if (operatorInt == 4) fracResult = frac1.div(frac2)

        //fracResult = frac1.plus(frac2)

        for (i in 0..3) {
            var fracRandom = Frac(Random.nextInt(0, maxNum), Random.nextInt(1, maxNum))
            mChoices[i] = fracRandom.toString()
        }

        mChoices[Random.nextInt(0, 3)] = fracResult.toString()
        mCorrectAnswer = fracResult.toString()
        return firstNum.toString().plus("/").plus(firstDen).plus(" ").plus(operator).plus(" ")
            .plus(secondNum).plus("/").plus(secondDen).plus("=")
    }

    fun getChoice1(): String? {
        return mChoices[0]
    }

    fun getChoice2(): String? {
        return mChoices[1]
    }

    fun getChoice3(): String? {
        return mChoices[2]
    }

    fun getChoice4(): String? {
        return mChoices[3]
    }

    fun getCorrectAnswer(): String? {
        return mCorrectAnswer
    }
}