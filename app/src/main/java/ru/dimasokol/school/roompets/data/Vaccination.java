package ru.dimasokol.school.roompets.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "vaccinations", foreignKeys =
@ForeignKey(entity = Dog.class,
        parentColumns = "id",
        childColumns = "dog_id",
        onDelete = ForeignKey.CASCADE))
public class Vaccination {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long mId;

    @ColumnInfo(name = "vaccine")
    private String mVaccineName;

    @ColumnInfo(name = "date", index = true)
    private Date mVaccinationDate;

    @ColumnInfo(name = "dog_id")
    private long mDogId;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getVaccineName() {
        return mVaccineName;
    }

    public void setVaccineName(String vaccineName) {
        mVaccineName = vaccineName;
    }

    public Date getVaccinationDate() {
        return mVaccinationDate;
    }

    public void setVaccinationDate(Date vaccinationDate) {
        mVaccinationDate = vaccinationDate;
    }

    public long getDogId() {
        return mDogId;
    }

    public void setDogId(long dogId) {
        mDogId = dogId;
    }
}
