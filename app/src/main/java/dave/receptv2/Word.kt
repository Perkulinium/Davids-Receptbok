package dave.receptv2

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "word_table")
data class Word(@PrimaryKey @ColumnInfo(name = "title") val title: String,
                val recept: String,
                val category: String,
                val ingredienser: String,
                val picture: String?,
                val info: String,
                val ID : Int
                )

