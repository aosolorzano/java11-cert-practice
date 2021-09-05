module com.hiperium.java.cert.prep.chapter.eleven.bo { // module name should avoid terminal digits
    exports com.hiperium.java.cert.prep.chapter._11.bo to com.hiperium.java.cert.prep.chapter.eleven.api;
    requires com.hiperium.java.cert.prep.chapter.eleven.dao;
}