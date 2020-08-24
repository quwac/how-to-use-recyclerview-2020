package io.github.quwac.how_to_use_recyclerview_2020.ui.main

import androidx.lifecycle.MutableLiveData

data class User(
    val id: Long,
    val name: String,
    val isChecked: MutableLiveData<Boolean> = MutableLiveData(false)
) {
    override fun toString(): String {
        return """{"id": $id, "name": $name, "isChecked.value": ${isChecked.value}}"""
    }
}
