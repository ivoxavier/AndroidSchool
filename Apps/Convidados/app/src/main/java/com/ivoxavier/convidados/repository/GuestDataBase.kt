package com.ivoxavier.convidados.repository

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ivoxavier.convidados.constants.DataBaseConstants
import com.ivoxavier.convidados.model.GuestModel
import com.ivoxavier.convidados.repository.GuestRepository.Companion

//caso use uma constructor class em vez do ROOM
/*
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
}*/
@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase: RoomDatabase() {

    abstract fun guestDAO(): GuestDAO


    companion object{
        private lateinit var INSTANCE: GuestDataBase

        fun getDataBase(context: Context): GuestDataBase{
            if(!::INSTANCE.isInitialized) {
                //garante que so executa uma instancia por vez
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context, GuestDataBase::class.java, "guestdb"
                    ).addMigrations(MIGRATION_1_2)
                        .allowMainThreadQueries()
                        .build()
                }
            }
                return INSTANCE
            }

            private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
                override fun migrate(db: SupportSQLiteDatabase) {
                   // database.execSQL("DELETE FROM Guest")
                }
            }
    }
}
