package anderson.bittencourt.baseconverter

import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import java.lang.StringBuilder
import kotlin.math.pow

class ConverterPresenter: IConverterContract.Presenter, OnItemSelectedListener{


    companion object {
        val BASE_HEXADECIMAL: Int = 16
        val BASE_BINARIA: Int = 2
    }

    override fun binToDec(number: String): String {
        val values = convertToList(number)
        return convertToDec(values, BASE_BINARIA).toString()
    }

    override fun decToBin(number: String): String {
        return convertFromDec(number.toLong(), BASE_BINARIA)
    }

    override fun decToHex(number: String): String {
        return convertFromDec(number.toLong(), BASE_HEXADECIMAL)
    }

    override fun hexToDec(number: String): String {
        val values = convertToList(number.toUpperCase())
        return convertToDec(values, BASE_HEXADECIMAL).toString()
    }

    private fun convertToList(number: String): List<Int> {
        val values = mutableListOf<Int>()
        for(position in number){
            when(position){
                'A' -> values.add(10)
                'B' -> values.add(11)
                'C' -> values.add(12)
                'D' -> values.add(13)
                'E' -> values.add(14)
                'F' -> values.add(15)
                else -> values.add(position.toString().toInt())
            }
        }
        return values
    }

    private fun convertToDec(values: List<Int>, base: Int ): Long{
        var result: Long = 0
        var position = values.size - 1
        for(value in values){
            result = result.plus(value.times(base.toDouble().pow(position))).toLong()
            position--;
        }
        return result
    }

    private fun convertFromDec(value: Long, base: Int ): String{
        val result = mutableListOf<Int>()
        var rest: Int
        var newValue = value
        while(newValue >= base){
            rest = newValue.rem(base).toInt()
            newValue = newValue.div(base)
            result.add(rest)
        }
        result.add(newValue.toInt())

        return listToString(result).reversed()
    }

    private fun listToString(result: List<Int>): String {
        val builder = StringBuilder()

        for(value in result){
            when(value){
                15 -> builder.append("F")
                14 -> builder.append("E")
                13 -> builder.append("D")
                12 -> builder.append("C")
                11 -> builder.append("B")
                10 -> builder.append("A")
                else -> builder.append(value.toString())
            }
        }

        return builder.toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}