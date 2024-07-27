package com.ivoxavier.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.ivoxavier.convidados.constants.DataBaseConstants

class GuestDataBase(context: Context?) : SQLiteOpenHelper(context, NAME, null, VERSION) {

    companion object {
        private const val NAME = "guestdb"
        private const val VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //cria a tabela
        db?.execSQL("CREATE TABLE" + DataBaseConstants.Guest.TABLE_NAME +
                " (" + DataBaseConstants.Guest.COLUMNS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DataBaseConstants.Guest.COLUMNS.NAME + " TEXT, " +
                DataBaseConstants.Guest.COLUMNS.PRESENCE + " INTEGER);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if(newVersion > oldVersion){
            //atualiza a tabela
        }
    }

}