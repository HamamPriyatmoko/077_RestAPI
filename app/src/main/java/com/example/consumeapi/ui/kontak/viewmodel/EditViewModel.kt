package com.example.consumeapi.ui.kontak.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.consumeapi.repository.KontakRepository
import com.example.consumeapi.ui.kontak.screen.EditDestination
import kotlinx.coroutines.launch

class EditViewModel (
    savedStateHandle: SavedStateHandle,
    private val kontakRepository: KontakRepository
) : ViewModel(){
    var editKontakState by mutableStateOf(InsertUiState())
        private set
    val kontakId: Int = checkNotNull(savedStateHandle[EditDestination.kontakId])

    init {
        viewModelScope.launch {
            editKontakState = kontakRepository.getkontakById(kontakId).toUiStateKontak()
        }
    }

    fun updateInsertKontakState(insertUiEvent: InsertUiEvent){
        editKontakState = InsertUiState(insertUiEvent = insertUiEvent)
    }

    suspend fun updateKontak(){
        viewModelScope.launch {
            try {
                kontakRepository.updatekontak(kontakId, editKontakState.insertUiEvent.toKontak())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}