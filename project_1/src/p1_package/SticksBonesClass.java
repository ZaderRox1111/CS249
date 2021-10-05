package p1_package;

/**
 * Class manages two dimensional array of sticks or bones; 
 * includes one dimensional array of sticks
 * and one dimensional array of bones
 * <p>
 * The limit on number of sticks is 25 per cent of array height
 * and the limit on number of bones is 25 per cent of array width
 * 
 * @author Michael Leverington
 *
 */
public class SticksBonesClass
   {
    /**
     * Constant, default two dimensional array sides
     */
    private static final int TWO_DIM_ARRAY_SIDE = 50;
       
    /**
     * constant, stick and bone percentage of side length
     */
    private static final double STICK_BONE_PERCENTAGE = 0.25;

    /**
     * constant, horizontal line character
     */
    private static final char HORIZ_LINE = '-';
    
    /**
     * constant, vertical line character
     */
    private static final char VERTICAL_LINE = '|';
    
    /**
     * Master array of sticks and bones
     */
    private CellClass[][] fieldArray;
    
    /**
     * Array of sticks
     */
    private ArtifactClass[] stickArray;
    
    /**
     * height of cell array
     */
    private int fieldArrayHeight;
    
    /**
     * width of cell array
     */
    private int fieldArrayWidth;
    
    /**
     * Number of sticks
     */
    private int numSticks;
    
    /**
     * Array of bones
     */
    private ArtifactClass[] boneArray;
    
    /**
     * Number of bones
     */
    private int numBones;
    
    /**
     * Default constructor
     * <p>
     * Initializes class with random sticks and bones in square array;
     * arrays use default capacities
     */
    public SticksBonesClass()
       {
        this( TWO_DIM_ARRAY_SIDE, TWO_DIM_ARRAY_SIDE );
       }
    
    /**
     * Initialization constructor
     * <p>
     * Initializes class with random sticks and bones;
     * arrays use parameter capacities
     * 
     * @param height integer number of rows in two dimensional array
     * 
     * @param width integer number of columns in two dimensional array
     */
    public SticksBonesClass( int height, int width )
       {
        fieldArrayHeight = height;
        
        fieldArrayWidth = width;
        
        // generate random array
        createRandomStickBoneArray( height, width );
        
        // find sticks, store in array
        findAllSticks();
        
        // find bones, store in array
        findAllBones();
       }
    
    /**
     * Creates a random stick/bone array
     * <p>
     * Random x, y location, with random length of stick or bone
     * between 3 and 6
     * 
     * @param height integer value representing number of rows in array
     * 
     * @param width integer value representing number of columns in array
     */
    // create random stick/bone array
    private void createRandomStickBoneArray( int height, int width )
       {
        int rowIndex, colIndex, locIndex, index = 0;
        int yLoc, xLoc, startLoc = 0;
        int itemLength;
        int minItemLength = 3;
        int maxItemLength = 6;
        int stickCount, boneCount;
        
        fieldArrayHeight = height;
        fieldArrayWidth = width;
        
        // create array of cells
        fieldArray = new CellClass[ fieldArrayHeight ][ fieldArrayWidth ];
        
        for( rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++ )
           {
            for( colIndex = 0; colIndex < fieldArrayWidth; colIndex++ )
               {
                fieldArray[ rowIndex ][ colIndex ] = new CellClass();
               }
           }
        
        // generate between 1/3 of height and full height sticks and bones
        stickCount = generateRandBetween( height / 3, height );
        boneCount = generateRandBetween( height / 3, height );

        boolean createItemSuccess;
        
        while( index < stickCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );

            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( yLoc + locIndex >= height
                      || xLoc >= width
                           || fieldArray[ yLoc + locIndex ][ xLoc ].isUsed() )
                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc + locIndex ][ xLoc ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }
        
        index = 0;
        
        while( index < boneCount )
           {
            yLoc = generateRandBetween( startLoc, height - 1 );
            xLoc = generateRandBetween( startLoc, width - 1 );
            itemLength = generateRandBetween( minItemLength, maxItemLength );
            
            createItemSuccess = true;
            
            for( locIndex = 0; 
                  locIndex < itemLength && createItemSuccess; locIndex++ )
               {
                if( xLoc + locIndex >= width
                    || yLoc >= height
                          || fieldArray[ yLoc ][ xLoc + locIndex ].isUsed() )

                   {
                    createItemSuccess = false;
                   }
               }
            
            if( createItemSuccess )
               {
                for( locIndex = 0; locIndex < itemLength; locIndex++ )
                   {
                    fieldArray[ yLoc ][ xLoc + locIndex ]
                                .setCellCharacter( CellClass.STICK_BONE_CHAR );
                   }

                index++;
               }
           }

       }
    
    /**
     * Finds all bones, assigns to bone array
     * <p>
     * May find adjacent crossing stick and count as bone
     */
    public void findAllBones()
       {
        // Todo: implement this method
    	int rowIndex, colIndex, boneIndex = 0;
    	int inColIndex = 0;
        int yLoc, xLoc, boneLength = 0;
        ArtifactClass newBone;
        int bone = ArtifactClass.BONE;
        boneArray = new ArtifactClass[TWO_DIM_ARRAY_SIDE];
        
        for (rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++)
        {
        	for (colIndex = 0; colIndex < fieldArrayWidth; colIndex++)
        	{
        		// Check if there is an asterisk
        		if (fieldArray[rowIndex][colIndex].isUsed())
        		{
        			inColIndex = colIndex;
        			// Loop until reach the end of bone
        			while(inColIndex < fieldArrayWidth && fieldArray[rowIndex][inColIndex].isUsed())
        			{
        				boneLength++;
        				inColIndex++;
        			}
        			
        			// Create bone and add to array
        			if (boneLength > 1)
        			{
        				yLoc = rowIndex;
        				xLoc = colIndex;
        				
        				newBone = new ArtifactClass(xLoc, yLoc, bone, boneLength);
        				boneArray[boneIndex] = newBone;
        				numBones++;
        				
        				boneIndex++;
        			}
        			
        			colIndex = inColIndex;
        			boneLength = 0;
        			
        		}
        	}
        }
       }
    
    /**
     * Finds all sticks, assigns to sticks array, 
     * <p>
     * May find adjacent crossing bone and count as stick
     */
    public void findAllSticks()
       {
        // Todo: implement this method
    	int rowIndex, colIndex, stickIndex = 0;
    	int inRowIndex = 0;
        int yLoc, xLoc, stickLength = 0;
        ArtifactClass newStick;
        int stick = ArtifactClass.STICK;
        stickArray = new ArtifactClass[TWO_DIM_ARRAY_SIDE];
        
        for (colIndex = 0; colIndex < fieldArrayWidth; colIndex++)
        {
        	for (rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++)
        	{
        		// Check if there is an asterisk
        		if (fieldArray[rowIndex][colIndex].isUsed())
        		{
        			inRowIndex = rowIndex;
        			// Loop until reach the end of bone
        			
        			while(inRowIndex < fieldArrayHeight && fieldArray[inRowIndex][colIndex].isUsed())
            		{
            			stickLength++;
            			inRowIndex++;
            		}
        			
        			// Create bone and add to array
        			if (stickLength > 1)
        			{
        				yLoc = rowIndex;
        				xLoc = colIndex;
        				
        				newStick = new ArtifactClass(xLoc, yLoc, stick, stickLength);
        				stickArray[stickIndex] = newStick;
        				numSticks++;
        				
        				stickIndex++;
        			}
        			
        			rowIndex = inRowIndex;
        			stickLength = 0;
        			
        		}
        	}
        }
       }

    /**
     * Generates random values between given low, high, inclusive
     * 
     * @param low integer value representing low end of random output
     * 
     * @param high integer value representing high end of random output
     * 
     * @return integer random value returned between low and high
     * parameters, inclusive
     */
    public int generateRandBetween( int low, int high )
       {
        int range = high - low + 1;
        
        return (int)( Math.random() * range ) + low;          
       }    

    /**
     * Shows field of sticks and bones, has frame around perimeter
     */
    public void showField()
       {
        // Todo: implement this method
    	int rowIndex, colIndex, index;
    	
    	// Display top bar
    	for (index = 0; index < (fieldArrayWidth + 2); index++)
    	{
    		System.out.print(HORIZ_LINE);
    	}
    	System.out.println();
    	
    	// Display field array
    	for (rowIndex = 0; rowIndex < fieldArrayHeight; rowIndex++) 
    	{
    		System.out.print(VERTICAL_LINE);
    		
    		for (colIndex = 0; colIndex < fieldArrayWidth; colIndex++)
    		{
    			System.out.print(fieldArray[rowIndex][colIndex].getCellCharacter());
    		}
    		
    		System.out.print(VERTICAL_LINE);
    		System.out.println();
    	}
    	
    	// Display bottom bar
    	for (index = 0; index < (fieldArrayWidth + 2); index++)
    	{
    		System.out.print(HORIZ_LINE);
    	}
    	System.out.println();
       }
    
    /**
     * Shows bone list, provides results in formatted, aligned output
     * Hint: Google System.out.format
     *  
     */
    public void showBoneList()
       {
        // Todo: implement this method
    	int index;
    	
    	System.out.println();
    	System.out.format("Found %d bones:", numBones);
    	System.out.println();
    	
    	for (index = 0; index < numBones; index++)
    	{
    		System.out.format("Bone at ( %2d, %2d ), length is: %2d", boneArray[index].xPos, boneArray[index].yPos, boneArray[index].length);
    		System.out.println();
    	}
    	
    	System.out.println();
       }

    /**
     * Shows list of sticks, provides results in formatted, aligned output
     * Hint: Google System.out.format 
     *    
     */
    public void showStickList()
       {
        // Todo: implement this method
    	int index;
    	
    	System.out.println();
    	System.out.format("Found %d sticks:", numSticks);
    	System.out.println();
    	
    	for (index = 0; index < numSticks; index++)
    	{
    		System.out.format("Stick at ( %2d, %2d ), length is: %2d", stickArray[index].xPos, stickArray[index].yPos, stickArray[index].length);
    		System.out.println();
    	}
    	
    	System.out.println();
       }
    
   }
