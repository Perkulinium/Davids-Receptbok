package dave.receptv2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "word") val word: String, val recept: String)

data class Forrat(@PrimaryKey @ColumnInfo(name = "forrat") val word: String, val recept: String)