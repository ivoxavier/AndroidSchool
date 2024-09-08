package com.ivoxavier.tasks.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Priority")
class PriorityModel {

    /*
    * "Id":1,
    * "Description":"Baixa"
    * */

    @SerializedName("Id")
    @ColumnInfo(name = "id")
    @PrimaryKey // o id ja vem gerado automaticamente
    var id: Int = 0


    @SerializedName("Description")
    @ColumnInfo(name = "description")
    var description: String = ""
}
