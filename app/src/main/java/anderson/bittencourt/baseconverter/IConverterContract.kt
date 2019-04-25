package anderson.bittencourt.baseconverter

interface IConverterContract {

    interface View{
        fun showNumberConverted(number: String)
    }

    interface Presenter{
        fun binToDec(number: String): String
        fun decToBin(number: String): String
        fun decToHex(number: String): String
        fun hexToDec(number: String): String
    }
}