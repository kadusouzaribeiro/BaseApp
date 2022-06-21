package br.com.android.baseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import br.com.android.baseapp.adapter.FactAdapter
import br.com.android.baseapp.data.ResponseStatus
import br.com.android.baseapp.data.remote.dto.Fact
import br.com.android.baseapp.databinding.ActivityMainBinding
import br.com.android.baseapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModel()

    private lateinit var binding: ActivityMainBinding
    private lateinit var factAdapter: FactAdapter

    private var list: List<Fact> = mutableListOf()

    private val RECYCLER_STATE = "recycler_state"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setObservable()

        if (list.isEmpty()) {
            viewModel.getFacts()
        }
    }

    private fun setObservable() {
        viewModel.state.observe(this) {
            when(it.status) {
                ResponseStatus.SUCCESS -> {
                    it.data?.let { it1 ->
                        list = it1
                        setFactsList(list)
                    }
                    binding.pbFacts.visibility = View.GONE
                }
                ResponseStatus.ERROR -> {
                    binding.pbFacts.visibility = View.GONE
                    it.message?.let { it1 -> showMessage(it1) }
                }
                ResponseStatus.LOADING -> {
                    binding.pbFacts.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setFactsList(list: List<Fact>) {
        binding.apply {
            factAdapter = FactAdapter(list)
            rvFacts.adapter = factAdapter
        }
    }

    private fun showMessage(msg: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Cat Facts")
        builder.setMessage(msg)
        builder.setNeutralButton("Ok") { dialogInterface, _ ->
            dialogInterface.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }

}