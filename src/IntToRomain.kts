import java.lang.StringBuilder

fun intToRoman(num: Int): String {
    val lookUpTable: MutableMap<Int, String> = HashMap()
    lookUpTable[1] = "I"
    lookUpTable[4] = "IV"
    lookUpTable[5] = "V"
    lookUpTable[9] = "IX"
    lookUpTable[10] = "X"
    lookUpTable[40] = "XL"
    lookUpTable[50] = "L"
    lookUpTable[90] = "XC"
    lookUpTable[100] = "C"
    lookUpTable[400] = "CD"
    lookUpTable[500] = "D"
    lookUpTable[900] = "CM"
    lookUpTable[1000] = "M"

    val levels: List<Int> = listOf(1,4,5,9,10,40,50,90,100,400,500,900,1000)

    var temp = num

    var i = levels.size - 1
    val resBuilder = StringBuilder()
    while (i >= 0) {
        if (levels[i] <= temp) {
            resBuilder.append(lookUpTable[levels[i]])
            temp -= levels[i]
        } else {
            i--
        }
    }
    return resBuilder.toString()
}

println(intToRoman(3))