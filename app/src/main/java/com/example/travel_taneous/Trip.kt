package com.example.travel_taneous

import android.os.Parcel
import android.os.Parcelable

class Trip constructor(var estL:String,
                       var estT:String,
                       var actL:String,
                       var actT:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(estL)
        parcel.writeString(estT)
        parcel.writeString(actL)
        parcel.writeString(actT)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Trip> {
        override fun createFromParcel(parcel: Parcel): Trip {
            return Trip(parcel)
        }

        override fun newArray(size: Int): Array<Trip?> {
            return arrayOfNulls(size)
        }
    }

}