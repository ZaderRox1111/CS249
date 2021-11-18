package p9_package;

/**
 * Driver class for testing TwoThreeTreeClass
 * 
 * @author MichaelL
 *
 */
public class TwoThreeTreeMainClass
   {

    /**
     * Main/Driver method for testing components of TwoThreeTreeClass
     * 
     * @param args String arguments not used
     */
    public static void main(String[] args)
      {
       File_Input_Class fileOps = new File_Input_Class();
       TwoThreeTreeClass tttc = new TwoThreeTreeClass();
       int nameCount, numNames = 8;
       String newName;
       
       fileOps.openInputFile( "inData.txt" );
       
       for( nameCount = 0; nameCount < numNames; nameCount++ )
          {
           newName = fileOps.getString();
           
           System.out.println( "\nUploaded name: " + newName );
           
           tttc.addItem( newName );
           
           System.out.println( tttc.inOrderTraversal() );
          }
      }

   }