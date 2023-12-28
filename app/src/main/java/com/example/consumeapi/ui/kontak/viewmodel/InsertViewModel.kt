package com.example.consumeapi.ui.kontak.viewmodel


import androidx.lifecycle.ViewModel
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.repository.KontakRepository



class InsertViewModel(private val kontakRepository: KontakRepository) : ViewModel(){

}

data class InsertUiEvent(
    val id: Int = 0,
    val nama: String = "",
    val alamat: String = "",
    val nohp: String = "",
)

fun InsertUiEvent.toKontak() : Kontak = Kontak(
    id = id,
    nama = nama,
    alamat = alamat,
    nohp = nohp,
)

data class InsertUiState(
    val insertUiEvent: InsertUiEvent = InsertUiEvent()
)
