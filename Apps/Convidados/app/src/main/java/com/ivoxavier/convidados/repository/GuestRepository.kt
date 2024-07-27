package com.ivoxavier.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.ivoxavier.convidados.constants.DataBaseConstants
import com.ivoxavier.convidados.model.GuestModel

//private constructor para a classe nao ser instanciada
class GuestRepository private constructor(context: Context){

    private val guestDataBase = GuestDataBase(context)

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

    fun insert(guest: GuestModel): Boolean {
       return try {
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.COLUMNS.NAME,guest.name)
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)

            db.insert(DataBaseConstants.Guest.TABLE_NAME,null, values)
            true
        }catch (e: Exception){
            false
        }
    }

    fun update(guest: GuestModel): Boolean{
       return try{
            val db = guestDataBase.writableDatabase

            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.Guest.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.Guest.COLUMNS.NAME,guest.name)

            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"

            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.Guest.TABLE_NAME,values, selection, args)
           true
        } catch (e: Exception){
            false
        }

    }

    fun delete(id: Int): Boolean{
        return try{
            val db = guestDataBase.writableDatabase

            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"

            val args = arrayOf(id.toString())

            db.delete(DataBaseConstants.Guest.TABLE_NAME, selection, args)
            true
        } catch (e: Exception){
            false
        }

    }

    fun getAll(): List<GuestModel>{

        val list = mutableListOf<GuestModel>()
        try{
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID
                ,DataBaseConstants.Guest.COLUMNS.NAME
                ,DataBaseConstants.Guest.COLUMNS.PRESENCE)

            val cursor =  db.query(DataBaseConstants.Guest.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null)

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
        return list
    }


    fun get(id: Int): GuestModel?{

        var guest : GuestModel? = null
        try{
            val db = guestDataBase.readableDatabase

            val projection = arrayOf(
                DataBaseConstants.Guest.COLUMNS.ID
                ,DataBaseConstants.Guest.COLUMNS.NAME
                ,DataBaseConstants.Guest.COLUMNS.PRESENCE)

            val selection = DataBaseConstants.Guest.COLUMNS.ID + " = ?"

            val args = arrayOf(id.toString())

            val cursor =  db.query(DataBaseConstants.Guest.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                null)

            if(cursor != null && cursor.count > 0){
                while(cursor.moveToNext()){
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.Guest.COLUMNS.PRESENCE))

                   guest = GuestModel(id,name,presence == 1)
                }
            }

            cursor.close()
        }
        catch (e: Exception){
            return guest
        }
        return guest
    }


    fun getPresent(): List<GuestModel>{

        val list = mutableListOf<GuestModel>()
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
        return list
    }

    fun getAbsent(): List<GuestModel>{

        val list = mutableListOf<GuestModel>()
        try{
            val db = guestDataBase.readableDatabase

//            val projection = arrayOf(
//                DataBaseConstants.Guest.COLUMNS.ID
//                ,DataBaseConstants.Guest.COLUMNS.NAME
//                ,DataBaseConstants.Guest.COLUMNS.PRESENCE)


//            val selection = DataBaseConstants.Guest.COLUMNS.PRESENCE + " = ?"
//
//            val args = arrayOf("1")

            val cursor = db.rawQuery("SELECT id, name, presence FROM Guest WHERE presence = 0",null)

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
        return list
    }

}