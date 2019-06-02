package com.example.basketballscore.database.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "basketMatch_table")
data class basketMatch(
    var teamNameA: String,
    var teamNameB: String,
    var scoreTeamA: Int,
    var scoreTeamB: Int,
    var date: String,
    var time: String,
    var winner: String
): Parcelable
{
    @PrimaryKey(autoGenerate = true) var id : Int = 0

    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
        id = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(teamNameA)
        parcel.writeString(teamNameB)
        parcel.writeInt(scoreTeamA)
        parcel.writeInt(scoreTeamB)
        parcel.writeString(date)
        parcel.writeString(time)
        parcel.writeString(winner)
        parcel.writeInt(id)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<basketMatch> {
        override fun createFromParcel(parcel: Parcel): basketMatch {
            return basketMatch(parcel)
        }

        override fun newArray(size: Int): Array<basketMatch?> {
            return arrayOfNulls(size)
        }
    }
}