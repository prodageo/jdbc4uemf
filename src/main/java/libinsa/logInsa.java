// wrapper to log system, namely Heroku, ...
package libinsa;

public class logInsa 
{

  public static String log( String logMessage )
  {
    // https://devcenter.heroku.com/articles/logging
    System.err.println( logMessage );
  }


}
