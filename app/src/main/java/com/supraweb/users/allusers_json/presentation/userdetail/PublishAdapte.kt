package com.supraweb.users.allusers_json.presentation.userdetail
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.supraweb.users.R
import com.supraweb.users.allusers_json.domain.model.UserModelDetail

 import com.supraweb.users.databinding.ListasPublicacionesBinding


class PublishAdapte(

    private val detailPublish: List<UserModelDetail>?

)        :
    RecyclerView.Adapter<PublicacionViewHolder>()                    {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PublicacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return   PublicacionViewHolder(layoutInflater.inflate(R.layout.listas_publicaciones, parent, false))

       //  PublicacionViewHolder(layoutInflater.inflate(binding.idListas, parent, false))


    }

    override fun onBindViewHolder(holder: PublicacionViewHolder, position: Int) {
        val itemPublish = detailPublish?.get(position)

        holder.render(itemPublish!!)

    }

    override fun getItemCount(): Int {
        return detailPublish!!.size
    }


}


class PublicacionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ListasPublicacionesBinding.bind(view)

    fun render(itemPublish: UserModelDetail) {

        binding.idTituloPublicacion .text = itemPublish.title
        binding.idNombrePublicacion.text = itemPublish.body

    }


}