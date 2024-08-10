package com.ivoxavier.convidados.repository

import android.content.Context
import com.ivoxavier.convidados.model.GuestModel


class GuestRepository(context: Context){

    private val guestDataBase = GuestDataBase.getDataBase(context).guestDAO()

    // repository acessa a DataBase

    //Singleton, usado para controlar o acesso as instancias da nossa classe
    companion object {
        private lateinit var repository : GuestRepository

        fun getInstance(context: Context): GuestRepository {
            if(!Companion::repository.isInitialized) {
                repository = GuestRepository(context)
            }
            return repository
        }
    }

    fun insert(guest: GuestModel): Boolean{
       /*return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.COLUMNS.NAME,guest.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.Guest.TABLE_NAME,null, values)
            true
        }catch (e: Exception){
            false
        }*/

        return guestDataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean{
       return guestDataBase.update(guest) > 0
    }

    fun delete(id: Int){
        val guest = get(id)
         guestDataBase.delete(guest)
    }

    fun getAll(): List<GuestModel>{
        return guestDataBase.getAll()
    }


    fun get(id: Int): GuestModel{
       return guestDataBase.get(id)
    }


    fun getPresent(): List<GuestModel>{

        /*val list = mutableListOf<GuestModel>()
        try{
            val db = guestDataBase.readableDatabase

//            val projection = arrayOf(
//                DataBaseConstants.Guest.COLUMNS.ID
//                ,DataBaseConstants.Guest.COLUMNS.NAME
//                ,DataBaseConstants.Guest.COLUMNS.PRESENCE)


//            val selection = DataBaseConstants.Guest.COLUMNS.PRESENCE + " = ?"
//
//            val args = arrayOf("1")

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 1",null)

//            val cursor =  db.query(DataBaseConstants.Guest.TABLE_NAME,
//                projection,
//                selection,
//                args,
//                null,
//                null,
//                null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                    list.add(GuestModel(id,name,presence == 1))
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return list
        }
        return list*/
        return guestDataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel>{
        return guestDataBase.getAbsent()
    }

}