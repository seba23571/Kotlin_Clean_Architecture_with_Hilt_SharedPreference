package com.supraweb.users.allusers_json.presentation.userlist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.supraweb.users.allusers_json.domain.model.UserModelDomain
import com.supraweb.users.databinding.FragmentUserPresentationBinding
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UserPresentationFragmen"

@AndroidEntryPoint
class UserPresentationFragment : Fragment(), UserListener, SearchView.OnQueryTextListener {
    private var _binding: FragmentUserPresentationBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModelPresentation by viewModels()
    private lateinit var searchView: SearchView
    private var listFilter = ArrayList<UserModelDomain>()
    private  var listUser: List<UserModelDomain>?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding?.progressfBar?.isVisible   =true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserPresentationBinding.inflate(inflater, container, false)
        searchView = binding!!.idSearchVIEW
        searchView.setOnQueryTextListener(this)
        searchView.setFocusable(false)

        binding.apply {
            itemsRecyclerview.apply {
                // adapter =userAdapter
                layoutManager = LinearLayoutManager(requireContext())
                progressfBar.isVisible = true

            }

            userViewModel.uiState.observe(viewLifecycleOwner, Observer { resultState ->
                when (resultState) {
                    is UiState.Success -> myRecyclerView(resultState.data)




                      is UiState.Error -> doError(resultState.message)

                    is UiState.Loading -> loadBar(true)


                }

            })



        }




        return binding.root
    }

    private fun loadBar(valor : Boolean) {
       binding. progressfBar.isVisible = valor
    }

    private fun doError(message: String) {

     binding. idTextViewError.isVisible = true
        binding. idTextViewError.text = message ?: "An unexpected error occured"
         binding.    idTextViewError.postDelayed(
            {  binding. idTextViewError.isVisible = false
                binding. progressfBar.isVisible = false


            }, 5000
        )



    }

    private fun myRecyclerView(success: List<UserModelDomain>) {



        listaAdapter(success ?: emptyList())


      binding.progressfBar.isVisible = !success.isNotEmpty()?:false


    }

    private fun listaAdapter(data: List<UserModelDomain>?) {

        listUser = data!!
        val userAdapter = UserAdapterPresentation(this)
        binding.itemsRecyclerview.adapter = userAdapter
        userAdapter.submitList(data)


    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {


        } else {


        }

        return true

    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText!!.isNotEmpty()) {

            listFilter.clear()
            // if (::repository.isInitialized) {}
            if (listUser.isNullOrEmpty()) {
                listUser = ArrayList()
            }
            listUser!!.forEach { listuser ->
                if (listuser.name.contains(newText)) { //.toLowerCase().trim()
                    listFilter.add(listuser)
                }
            }


        } else {
            listFilter.clear()

            if (listUser == null) {
                listUser = ArrayList<UserModelDomain>()
            }
            listFilter.addAll(listUser!!)
        }
        ListSearch(listFilter)
        return true
    }

    private fun ListSearch(listFilter: ArrayList<UserModelDomain>) {
        val userAdapter = UserAdapterPresentation(this)
        binding.itemsRecyclerview.adapter = userAdapter
        userAdapter.submitList(listFilter)
        userAdapter.notifyDataSetChanged()

    }

    override fun doNavegation(currentUser: UserModelDomain) {

        val action: NavDirections =
            UserPresentationFragmentDirections.actionUserPresentationFragmentToPublishFragment(
                currentUser
            )
        findNavController().navigate(action)

    }


}