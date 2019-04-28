package anderson.bittencourt.baseconverter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_converter.*

class ConverterActivity : AppCompatActivity(), IConverterContract.View {

    companion object{
        val BIN = "BIN"
        val DEC = "DEC"
        val HEX = "HEX"
    }

    private var spinnerFromSelected: String = BIN
    private var spinnerToSelected: String = BIN
    private lateinit var arrayOptionsSpinner: Array<String>

    private val presenter: IConverterContract.Presenter = ConverterPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_converter)
        setupOnClick()
        setupSpinner()
        arrayOptionsSpinner = resources.getStringArray(R.array.spinnerOptions)
    }

    private fun setupSpinner() {
        val adapter = ArrayAdapter.createFromResource(this, R.array.spinnerOptions, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
        spinnerFrom.adapter = adapter
        spinnerTo.adapter = adapter

        spinnerFrom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerFromSelected = arrayOptionsSpinner.get(position)
            }
        }

        spinnerTo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerToSelected = arrayOptionsSpinner.get(position)
            }
        }
    }

    private fun setupOnClick() {
        buttonConverter.setOnClickListener { converter() }
    }

    private fun converter() {
        when(spinnerFromSelected){
            BIN ->{
                when(spinnerToSelected){
                    BIN -> outputNumber.text = inputNumber.text
                    DEC -> outputNumber.text = presenter.binToDec(inputNumber.text.toString())
                    else -> notifyNotImplemented()
                }
            }
            DEC ->{
                when(spinnerToSelected){
                    BIN -> outputNumber.text = presenter.decToBin(inputNumber.text.toString())
                    DEC -> outputNumber.text = inputNumber.text
                    HEX -> outputNumber.text = presenter.decToHex(inputNumber.text.toString())
                    else -> notifyNotImplemented()
                }
            }
            HEX ->{
                when(spinnerToSelected){
                    DEC -> convertedValue.text = presenter.hexToDec(inputNumber.text.toString())
                    HEX -> convertedValue.text = inputNumber.text
                    else -> notifyNotImplemented()
                }
            }
            else -> notifyNotImplemented()
        }
    }

    private fun notifyNotImplemented() {
        Toast.makeText(this, "Não implementado", Toast.LENGTH_SHORT).show()
    }

    override fun showNumberConverted(number: String) {
        outputNumber.setText(number);
    }



}
