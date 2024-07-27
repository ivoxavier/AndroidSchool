package com.ivoxavier.convidados.view.viewholder

import android.content.DialogInterface
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.ivoxavier.convidados.R
import com.ivoxavier.convidados.databinding.RowGuestBinding
import com.ivoxavier.convidados.model.GuestModel
import com.ivoxavier.convidados.view.listener.OnGuestListener

class GuestViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) : RecyclerView.ViewHolder(bind.root) {
    fun bind(guest: GuestModel){
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }
        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context).setTitle("Remoção de convidado")
                .setMessage("Tem a certeza que deseja remover")
                .setPositiveButton("Sim"
                ) { dialog, which ->
                    listener.onDelete(guest.id)
                }
                .setNegativeButton("Não",null)
                .setNeutralButton("Neutro"
                ) { dialog, which ->
                    val s = ""
                }
                .create().show()


            true
        }

    }
}