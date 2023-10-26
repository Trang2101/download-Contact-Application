package com.example.advanceassignment1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandle(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Contact"
        private const val TABLE_NAME = "Contacts"
        private const val KEY_ID = "_id"
        private const val KEY_NAME = "name"
        private const val KEY_PHONE = "phone"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery =
            ("CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER PRIMARY KEY, $KEY_NAME TEXT, $KEY_PHONE TEXT)")
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addContact(name: String, phone: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_NAME, name)
        values.put(KEY_PHONE, phone)
        val id = db.insert(TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun getAllContact(): ArrayList<Contact> {
        val contactList = ArrayList<Contact>()
        val selectQuery = "SELECT * FROM $TABLE_NAME ORDER BY $KEY_NAME ASC"
        val db = this.readableDatabase
        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var name: String
        var phone: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NAME))
                phone = cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                val contact = Contact(id, name, phone)
                contactList.add(contact)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return contactList
    }
}