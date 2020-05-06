// myAIDL.aidl
package com.vfong3.musiccommon;


interface myAIDL
{
    void retrieveAllInfo(out List<String> titles, out List<String> artists, out List<Bitmap> covers, List<String> urls);

    void retrieveSongInfo(int songNumber,out String title, out String artist, out Bitmap cover, out String url);

    String retrieveURL(int songNumber);
}