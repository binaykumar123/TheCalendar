package com.example.thecalendar.calendar.ui.uiModels

import android.os.Parcel
import android.os.Parcelable
import com.google.api.client.util.DateTime

class ParcelableDateTime(val dateTime: DateTime) : Parcelable {

    constructor(parcel: Parcel) : this(
        DateTime(parcel.readLong())
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(dateTime.value)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelableDateTime> {
        override fun createFromParcel(parcel: Parcel): ParcelableDateTime {
            return ParcelableDateTime(parcel)
        }

        override fun newArray(size: Int): Array<ParcelableDateTime?> {
            return arrayOfNulls(size)
        }
    }
}
