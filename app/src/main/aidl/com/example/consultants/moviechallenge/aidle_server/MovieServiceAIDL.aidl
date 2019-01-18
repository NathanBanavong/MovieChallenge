// MovieServiceAIDL.aidl
package com.example.consultants.moviechallenge;
import com.example.movieservice.data.Movies;
// Declare any non-default types here with import statements

interface MovieServiceAIDL {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    String ping();

    Bundle search(String aQuery, int pageNum);
}
