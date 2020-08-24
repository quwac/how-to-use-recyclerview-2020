package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import androidx.lifecycle.*

class MainViewModel : ViewModel() {

    private val usersRaw = mutableListOf<User>()

    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users.distinctUntilChanged()
    val usersText = MediatorLiveData<String>().apply {
        addSource(users) {
            updateUsersText()
        }
    }

    private fun updateUsersText() {
        usersText.value =
            "users=${usersRaw.joinToString(separator = ",", prefix = "[", postfix = "]")}"
    }

    private val _toastRequest = MutableLiveDataEvent<String>()
    val toastRequest: LiveDataEvent<String> = _toastRequest

    private var index = 0L

    fun addElement() {
        val p = (index % 26).toInt()
        val name = ('A' + p).toString()

        val user = User(index, name).apply {
            usersText.addSource(isChecked) {
                updateUsersText()
            }
        }
        usersRaw.add(user)
        _users.value = ArrayList(usersRaw)

        index += 1
    }

    fun checkAll() {
        usersRaw.forEach {
            it.isChecked.value = true
        }
    }

    fun onClickItem(item: User) {
        _toastRequest.setValue(item.toString())
    }
}