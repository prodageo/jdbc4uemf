// wrapper to log system, namely Heroku, ...
package libinsa;

public class insaLogger
{

    private static volatile insaLogger instance = null;
    private static String className = null ;
     
    // other fields / standard constructors / getters
  
    private insaLogger(Class myClass)
    {
        // this.className = myClass.getName() ;
    }   

    public static insaLogger getLogger(Class theClass) {
        return new insaLogger(theClass);
    }

    
    /*
    public static insaLogger getLogger(Class theClass) {
        if (instance == null) {
            synchronized (insaLogger.class) {
                if (instance == null) {
                    instance = new insaLogger(theClass);
                }
            }
        }
        return instance;
    }
*/
    
  public static void debug( String logMessage )
  {
    // https://devcenter.heroku.com/articles/logging
    // System.err.println( this.className + ":" + logMessage );
    System.err.println( logMessage );
  }


}
