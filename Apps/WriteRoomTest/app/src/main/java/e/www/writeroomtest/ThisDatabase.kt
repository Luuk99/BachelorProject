package e.www.writeroomtest

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ThisDatabaseEntity::class], version = 1, exportSchema = false)
abstract class ThisDatabase : RoomDatabase() {

    abstract fun thisDatabaseInterface(): ThisDatabaseInterface
}