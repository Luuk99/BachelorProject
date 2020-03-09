package e.www.writeroomtest

import androidx.room.*

@Dao
interface ThisDatabaseInterface {

    @Update
    fun editEntry(entry: ThisDatabaseEntity)

    @Delete
    fun deleteEntry(entry: ThisDatabaseEntity)

    @Insert
    fun addEntry(entry: ThisDatabaseEntity)

    @Query("DELETE * FROM thisdatabaseentity")
    fun deleteAll()
}