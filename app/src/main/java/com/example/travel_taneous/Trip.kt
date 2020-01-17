package com.example.travel_taneous

import android.os.Parcel
import android.os.Parcelable

class Trip constructor(var estL:String,
                       var estT:String,
                       var estM:String,
                       var estE:String,
                       var estU:String,
                       var actL:String,
                       var actT:String,
                       var actM:String,
                       var actE:String,
                       var actU:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(estL)
        parcel.writeString(estT)
        parcel.writeString(estM)
        parcel.writeString(estE)
        parcel.writeString(estU)
        parcel.writeString(actL)
        parcel.writeString(actT)
        parcel.writeString(actM)
        parcel.writeString(actE)
        parcel.writeString(actU)
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