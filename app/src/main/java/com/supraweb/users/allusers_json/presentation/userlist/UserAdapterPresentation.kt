package com.supraweb.users.allusers_json.presentation.userlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
 import androidx.recyclerview.widget.RecyclerView
import com.supraweb.users.allusers_json.domain.model.UserModelDomain
import com.supraweb.users.databinding.ListasUsuariosBinding
import androidx.recyclerview.widget.ListAdapter


class UserAdapterPresentation(
    private val userListener: UserListener
) :ListAdapter<UserModelDomain, UserAdapterPresentation.UserViewHolder>(        UserComparator()    )  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ListasUsuariosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val currentUser = getItem(position)

        if(currentUser != null){
            holder.bind(currentUser,userListener)
        }


    }


    class UserViewHolder(private val binding: ListasUsuariosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentUser: UserModelDomain, userListener: UserListener) {
            binding.apply {

                binding.idTiemsNombreItems.text = currentUser.name
                binding.idPhoneNumberText.text = currentUser.phone
                binding.idMailText.text = currentUser.email
                binding.idVerPublicacion.text = "publicacion num : " + currentUser.id
                binding.idVerPublicacion.setOnClickListener {
                    userListener.doNavegation(currentUser)
                }

            }

        }


    }


    class UserComparator : DiffUtil.ItemCallback<UserModelDomain>() {
        override fun areItemsTheSame(oldItem: UserModelDomain, newItem: UserModelDomain) =
            oldItem.id == newItem.id


        override fun areContentsTheSame(oldItem: UserModelDomain, newItem: UserModelDomain) =
            oldItem == newItem


    }


}