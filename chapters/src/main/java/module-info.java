module com.hiperium.java.cert.prep.chapters { // module name should avoid terminal digits
    requires com.hiperium.java.cert.prep.chapter.eleven.api;
    uses com.hiperium.java.cert.prep.chapter._11.api.Chapter11API;  // Must be required by Service Loader.
}