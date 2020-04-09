package e.www.writeroomtest

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ThisDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "string") val string: String?
)