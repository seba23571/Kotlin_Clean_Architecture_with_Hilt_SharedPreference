package com.supraweb.users.allusers_json.presentation.userdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.users.allusers_json.domain.model.UserModelDetail
import com.supraweb.users.allusers_json.domain.model.UserModelDomain
import com.supraweb.users.core.util.Resource
import com.supraweb.users.databinding.FragmentPublishBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PublishFragment : Fragment() {
    private var _binding: FragmentPublishBinding? = null
    private val binding get() = _binding!!
    private val userViewModelDetails: UserDetailViewModel by viewModels()

    private val args by navArgs<PublishFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPublishBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        //  return inflater.inflate(R.layout.fragment_publish, container, false)
        binding.idListasDePublicaciones.text =
            "Listas de las Publicaciones con ID : " + args.userModel.id
        binding.idUserNamePublis.text = args.userModel.username //id_userNamePublis
        binding.idMailNamePublis.text = args.userModel.email            //id_mailNamePublis
        binding.idPhonePublish.text = args.userModel.phone       //id_mailNamePublis
        binding.apply {
            //items_publicaciones
            itemsPublicaciones.apply {
                layoutManager = LinearLayoutManager(requireContext())
            }
            userViewModelDetails.getDetails(args.userModel.id)
                .observe(viewLifecycleOwner, Observer { result ->
                    when (result) {
                        is Resource.Success -> {
                            listaAdapter(result.data)
                        }
                        is Resource.Error -> {
                            idTextViewError.isVisible = true
                            idTextViewError.text = result.error
                            progressfBar.isVisible = false
                            Toast.makeText(context, "dentro del el error ", Toast.LENGTH_LONG)
                                .show()

                        }
                        is Resource.Loading -> {
                            progressfBar.isVisible = false
                        }

                    }


                })
        }




        return binding.root
    }

    private fun listaAdapter(data: List<UserModelDetail>?) {
        val linearLayoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        val decoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        val userAdapter = PublishAdapte(data)
        binding.itemsPublicaciones.adapter = userAdapter            //items_publicaciones
        // reciclerPublish. items_publicaciones.addItemDecoration(decoration)
        binding.itemsPublicaciones.addItemDecoration(decoration)
        binding.itemsPublicaciones.setHasFixedSize(true)

    }


}