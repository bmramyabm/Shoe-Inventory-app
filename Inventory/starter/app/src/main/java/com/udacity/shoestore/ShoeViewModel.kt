package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    var currentShoe: Shoe? = null

    private val _shoes = MutableLiveData<List<Shoe>>()
    val shoes: LiveData<List<Shoe>>
        get() = _shoes

    private val _eventCloseScreen = MutableLiveData<Boolean?>()
    val eventCloseScreen: MutableLiveData<Boolean?>
        get() = _eventCloseScreen

    init {
        _shoes.value = createSampleShoes()

    }

    fun createSampleShoes(): List<Shoe> {
        val tempShoes = mutableListOf<Shoe>()
        tempShoes.add(Shoe("Free Fly", 5.0, "Nike", "Fly like a runner"))
        return tempShoes
    }

    fun createNewShoe() {
        currentShoe = Shoe("", 0.0, "", "")
    }

    //To add the list of shoes in the inventory
    fun save() {
        val tempShoes = mutableListOf<Shoe>()
        _shoes.value?.let {
            tempShoes.addAll(it)
        }
        currentShoe?.let {
            tempShoes.add(it)
        }
        _shoes.value = tempShoes
        _eventCloseScreen.value = true
    }

    fun onEventCloseComplete() {
        _eventCloseScreen.value = null
    }

}
