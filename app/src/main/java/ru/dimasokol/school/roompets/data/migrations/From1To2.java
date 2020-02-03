package ru.dimasokol.school.roompets.data.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class From1To2 extends Migration {
    public From1To2() {
        super(1, 2);
    }

    @Override
    public void migrate(@NonNull SupportSQLiteDatabase database) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `vaccinations` (`id` INTEGER NOT NULL, `vaccine` TEXT, `date` INTEGER, PRIMARY KEY(`id`))");
    }
}
