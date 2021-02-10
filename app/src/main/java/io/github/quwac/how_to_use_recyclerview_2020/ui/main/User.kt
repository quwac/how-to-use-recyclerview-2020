package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import androidx.lifecycle.MutableLiveData

enum class UserType {
    USER_SWITCHABLE,
    USER_UNSWITCHABLE,
}

data class User(
    val id: Long,
    val name: String,
    val userType: UserType,
    val isChecked: MutableLiveData<Boolean> = MutableLiveData(false)
) {
    override fun toString(): String {
        return """{"id": $id, "name": $name, "userType": ${userType}, "isChecked.value": ${isChecked.value}}"""
    }
}
