package com.vfong3.musiccentral;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable
{
    public String title;
    public String artist;
    public Bitmap cover;
    public String url;

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>()
    {
        public Song createFromParcel(Parcel in)
        {
            return new Song(in);
        }
        public Song[] newArray(int size)
        {
            return new Song[size];
        }
    };



    public Song(String title, String artist, Bitmap cover, String url)
    {
        this.title = title;
        this.artist = artist;
        this.cover = cover;
        this.url = url;
    }


    public Song(){}


    private Song(Parcel in)
    {
        this.title = in.readString();
        this.artist = in.readString();
        this.cover = in.readParcelable(Bitmap.class.getClassLoader());
        this.url = in.readString();
    }


    public int describeContents()
    {
        return 0;
    }


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getArtist()
    {
        return artist;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public Bitmap getCover()
    {
        return cover;
    }

    public void setCover(Bitmap cover)
    {
        this.cover = cover;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(this.title);
        dest.writeString(this.artist);
        dest.writeParcelable(this.cover, flags);
        dest.writeString(this.url);
    }

    public void readFromParcel(Parcel in)
    {
        this.title = in.readString();
        this.artist = in.readString();
        this.cover = in.readParcelable(Bitmap.class.getClassLoader());
        this.url = in.readString();
    }
}
