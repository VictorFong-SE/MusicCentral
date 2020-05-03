// myAIDL.aidl
package com.vfong3.musiccentral;

// Declare any non-default types here with import statements

interface myAIDL
{
    void retrieveAllInfo();

    void retrieveSongInfo(int songNumber);

    void retrieveURL(int songNumber);
}
