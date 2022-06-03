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
import com.supraweb.users.core.util.Resource
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "UserPresentationFragmen"

@AndroidEntryPoint
class UserPresentationFragment : Fragment(), UserListener, SearchView.OnQueryTextListener {
    private var _binding: FragmentUserPresentationBinding? = null
    private val binding get() = _binding!!
    private val userViewModel: UserViewModelPresentation by viewModels()
    private lateinit var searchView: SearchView
    private var listFilter = ArrayList<UserModelDomain>()
    private lateinit var listUser: List<UserModelDomain>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUserPresentationBinding.inflate(inflater, container, false)
        searchView = binding!!.idSearchVIEW
        searchView.setOnQueryTextListener(this)
        searchView.setFocusable(false)
       // val state = userViewModel.uiState.value
        binding.apply {
            itemsRecyclerview.apply {
                // adapter =userAdapter
                layoutManager = LinearLayoutManager(requireContext())
                progressfBar.isVisible = true

            }
            userViewModel.uiState.observe(viewLifecycleOwner, Observer { resultState ->
                when (resultState) {
                    is UiState.Success -> doSomething(resultState.data)
                    is UiState.Error -> doError(resultState.message)
                    is UiState.Loading ->loadBar()

                    /*
                        listaAdapter(       result.data ?: emptyList()       )
                        progressfBar.isVisible = false
                     */


                    //   UserState(resultState.Success             ) -> doSomething(resultState.Success )
                    /*
                    is UiState.Error -> showError(it.message)
                UiState.Loading -> loading()
                is UiState.Success -> dataLoaded(it.data)
                     */
                }

            })
//            userViewModel.userList.observe(viewLifecycleOwner) { result ->
//                when (result) {
//                    is Resource.Success -> {
//                        //  progressfBar.isVisible = true
//
//
//                        // _state.value = CoinListState(coins = result.data ?: emptyList()    )
//                        listaAdapter(result.data ?: emptyList())
//                        progressfBar.isVisible = false
//                    }
//                    is Resource.Error -> {
//
//                        idTextViewError.isVisible = true
//                        idTextViewError.text = result.error ?: "An unexpected error occured"
//                        progressfBar.isVisible = false
//                        idTextViewError.postDelayed(
//                            { idTextViewError.isVisible = false }, 5000
//                        )
//
//
//                        return@observe
//                    }
//                    is Resource.Loading -> {
//                        progressfBar.isVisible = true
//                    }
//                }
//
//
//            }


        }


//        met(Handler().postDelayed({         abstract fun met(postDelayed: Boolean)
//            binding.idTextViewError.isVisible = false
//        },5000))

        return binding.root
    }

    private fun loadBar() {
       binding. progressfBar.isVisible = true
    }

    private fun doError(message: String) {

       binding. idTextViewError.isVisible = true
        binding. idTextViewError.text = message ?: "An unexpected error occured"
        binding.   progressfBar.isVisible = false
        binding.    idTextViewError.postDelayed(
            {  binding. idTextViewError.isVisible = false }, 5000
        )


        return

    }

    private fun doSomething(success: List<UserModelDomain>) {
        Log.d(TAG, "doSomething:  " + success.forEach {

            println("........   " + it.toString())

        })
        listaAdapter(success ?: emptyList())
        binding.progressfBar.isVisible = false


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
            if (!::listUser.isInitialized) {
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
        //  Toast.makeText(context, "from adapter valores son \n "+currentUser.toString(), Toast.LENGTH_LONG).show()

        val action: NavDirections =
            UserPresentationFragmentDirections.actionUserPresentationFragmentToPublishFragment(
                currentUser
            )
        findNavController().navigate(action)

    }


}