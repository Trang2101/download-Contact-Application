package com.example.advanceassignment1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ContactViewModel(application: Application) : AndroidViewModel(application) {
    private val databaseHandle: DatabaseHandle

    private var _isVisible = MutableLiveData(false)
    val isVisible: LiveData<Boolean> = _isVisible

    private var _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>> = _contacts

    private var _progressNum = MutableLiveData(0)
    val progressNum: LiveData<Int> = _progressNum

    private val coroutineScope: CoroutineScope = MainScope()

    init {
        val context = application.applicationContext
        databaseHandle = DatabaseHandle(context)
        addContacts()
    }

    private fun addContacts() {
        for (i in 0..10) {
            val randomInt = Random.nextInt(975485204, 975842548)
            databaseHandle.addContact("person ${i+1}","0$randomInt")
        }
    }

    fun downloadContacts(){
        _isVisible.value = true
        coroutineScope.launch {
            for (i in 0 until 20) {
                delay(200)
                _progressNum.value = _progressNum.value?.plus(10)
            }
            _isVisible.value = false
            _contacts.value = databaseHandle.getAllContact()
        }
    }
}