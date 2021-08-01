package ru.agafonovilya.pressurepocketbook.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.agafonovilya.pressurepocketbook.databinding.FragmentMainBinding
import ru.agafonovilya.pressurepocketbook.entities.Record
import ru.agafonovilya.pressurepocketbook.repository.RecordsRepositoryFirestore
import ru.agafonovilya.pressurepocketbook.ui.BaseFragment
import ru.agafonovilya.pressurepocketbook.vm.MainViewModel
import ru.agafonovilya.pressurepocketbook.vm.MainViewModelFactory

class MainFragment : BaseFragment<FragmentMainBinding>() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(
            this, MainViewModelFactory(
                RecordsRepositoryFirestore() // RecordsRepositoryStub()
            )
        ).get(MainViewModel::class.java)
    }
    private var adapter: MainRecyclerAdapter? = null
    private var fabListener: FabListener? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MainRecyclerAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel
                .stateFlow
                .collectLatest {
                    adapter?.populate(it)
                }
        }

        fabListener = context as? FabListener
        binding.fab.setOnClickListener { fabListener?.fabClicked() }
    }

    fun saveRecord(record: Record) {
        viewModel.saveRecord(record)
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentMainBinding.inflate(inflater, container, false)
}