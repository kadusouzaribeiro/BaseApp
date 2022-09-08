package br.com.android.baseapp.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import br.com.android.baseapp.data.remote.dto.Fact
//import br.com.android.baseapp.databinding.ItemFactBinding
//
///**
// * Created by Carlos Souza on 21,junho,2022
// */
//class FactAdapter(private val listFact: List<Fact>): RecyclerView.Adapter<FactAdapter.FactViewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
//        val binding = ItemFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return FactViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
//        listFact[position].let {
//            with(holder) {
//                binding.apply {
//                    tvFact.text = it.text
//                }
//            }
//        }
//    }
//
//    override fun getItemCount() = listFact.size
//
//    inner class FactViewHolder(val binding: ItemFactBinding): RecyclerView.ViewHolder(binding.root)
//}