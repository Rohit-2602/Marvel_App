package com.example.marvelapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.marvelapp.data.CharacterResult

@Database(entities = [CharacterResult::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {

    abstract fun getCharacterDao(): CharacterDao

}