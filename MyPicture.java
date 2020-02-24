import greenfoot.*;
import java.lang.Object.*;
import java.awt.*;

/**This class contains image processing filters.
 * 
 * @authors (Ahmed Elomrani,Jeeyong Yoon,Ramiz Unudov,Arvin Guna serkaranc) 
 * @version (V1, 1-4-2014)
 */
public class MyPicture extends Picture
{

    /** 
     * Constructor that takes no arguments 
     */
    public MyPicture()

    {
        super();
    }

    /**
     * Constructor that takes the filename to read the picture
     * data from
     * @param filename the filename to read the picture data from
     */
    public MyPicture(String filename)
    {
        super(filename);
    }

    /** 
     * This method claculate the difference in color between the pixels specified in the picture with the color brown.
     * If that different satisfies the condition, then change the color of the pixel to something else.
     * 
     * @param startX is the startingX point from which loop should start in the horizontal direction.
     * @param endX is the ending x point from which the loop should end in the horixontal direction.
     * @param startY is startingY point from which the loop should start in the vertical direction.
     * @param endY is endingY point from which the loop should end in the vertical direction.
     * @param num referes the channel of the switch statement which decides which statement will be executed.
     * @param chosenCol referes to the color that you want to change the color of the pixel to if matched a particular condition.
     */
    public void ColorDifference(int startX, int endX, int startY, int endY, int num,Color chosenCol)
    {

        Pixel pixel = null;
        double distance = 110;   

        // loop through the pixels values
        for (int x=startX; x < endX; x++)
        {
            for (int y=startY; y < endY; y++)
            {
                int redRand = Greenfoot.getRandomNumber(100);  
                Color Reed = new Color(redRand/3,redRand/2,70);
                // get the current pixel
                pixel = this.getPixel(x,y);

                /* check if in distance to brown is less than
                 * the passed distance and if so change the color of the pixel
                 */  
                switch (num) {
                    case 1:  
                    if (pixel.colorDistance(Color.BROWN) <distance){//120
                        pixel.setColor(new Color(Reed));
                    }
                    break;
                    case 2: 
                    if (pixel.colorDistance(Color.BROWN) < distance){
                        pixel.setColor(new Color(chosenCol));
                    }
                    break;
                }
            }
        }
    }

    /** this method takes the RGB color value of a pixel and draws a circle with that color with the size specified
     * from the pointillize value.
     * 
     * @param pointillize refers to the diameter of the circle that is going to be drawn.
     * @param num1 refers to the channel in the switch statement for which the program should execute.
     */
    public void pointilliz (int pointillize,int num1)
    { 
        GreenfootImage img = this.getImage();//defining the current loaded picture as Greenfoot object to be accessed by the draw/fill line method.
        Pixel pixelObj = null;   
        int count = 0;
        String yeah =  "Pixel Hackers";
        //loop through the pixels in distance equivalent to the diameter of the circle
        for(int x = 0  ;     x  <  this.getWidth() ; x+=pointillize )  
        {
            for (int y = 0;    y  < this.getHeight();    y+=pointillize){

                // get the location and colour of the current pixels
                pixelObj = this.getPixel(x,y);
                int r = pixelObj.getRed();
                int g = pixelObj.getGreen();
                int b = pixelObj.getBlue();

                switch(num1)
                {
                    case 1:
                    img.fillOval(x,y-((pointillize)),pointillize,pointillize);
                    img.setColor(new Color(r,g,b));
                    count ++;
                    break;
                    default:
                    img.drawOval(x,y-((pointillize)),pointillize,pointillize);
                    img.setColor(new Color(r,g,b));
                    count ++;
                    break;
                }
            }   

            System.out.println("The picture is made of "  + count +  " circles of pixels");  
            img.drawString(yeah,10,10);  
        }
    }

    /** This method blurrs the image by the taking the average color of the surrounding pixels depending on the neighboring pixels that
     * get involved. The bigger the radius(number of pixels) used the intense the effect of blurring becomes.
     * 
     * @param numPixels indicates the size of the radius or the numbers layers of surrounding pixels that would be color averaged.
     */
    public void blur(int numPixels)
    {
        Pixel pixel = null;
        Pixel samplePixel = null;
        int redValue = 0;
        int greenValue = 0;
        int blueValue = 0;
        int count = 0;
        // loop through the pixels
        for (int x=0; x < this.getWidth(); x++) {
            for (int y=0; y < this.getHeight(); y++) {

                // get the current pixel
                pixel = this.getPixel(x,y);

                // reset the count and red, green, and blue values
                count = 0;
                redValue = greenValue = blueValue = 0;

                // loop through pixel numPixels before x to numPixels after x
                for (int xSample = x - numPixels;      xSample <= x + numPixels;    xSample++) {

                    for    (int ySample = y - numPixels;   ySample <= y + numPixels;   ySample++) {

                        // check that we are in the range of acceptable pixels
                        if (xSample >= 0 && xSample < this.getWidth() &&
                        ySample >= 0 && ySample < this.getHeight()) {
                            samplePixel = this.getPixel(xSample,ySample);
                            redValue = redValue + samplePixel.getRed();
                            greenValue = greenValue + samplePixel.getGreen();
                            blueValue = blueValue + samplePixel.getBlue();
                            count = count + 1;
                        }
                    }
                }
                // use average color of surrounding pixels
                Color newColor = new Color(redValue / count,
                        greenValue / count,
                        blueValue / count);
                pixel.setColor(newColor);
            }
        }
    }

    /** This method draws horizontal and vertical lines by changing the pixels of the image in a stright line. The number of lines 
     * is defined by the number of rows and columnes.
     * 
     *@param row is the number of horizontal lines to be drawn.
     *@param col is the number of vertical lines to be drawn.
     *@param optional the color of the lines to be drawn.
     */
    public void Lines(int row,int col,Color optional)
    {
        int value1 = 0;
        int value2 = 0;

        if (row == 0)
        {  

            value1 = 0;

        }else
        {
            value1 =  this.getWidth()/row;

        }

        for (Pixel pix : getPixels())
        {if (value1 == 0)
            {
                value1 = 0;} 
            else{
                if (pix.getX() % value1 == 0)
                    pix.setColor(optional);
            }
        }

        if(col==0)
        {
            value2 = 0;

        }else
        { 
            value2 = this.getHeight()/col;
        }

        for (Pixel pix : getPixels())
        { if (value2 == 0)
            {
                value2 = 0;} 
            else
            {
                if (pix.getY()% value2 == 0)
                    pix.setColor(optional);
            }

        }
    }

    /**
     * Method to posterize (reduce the number of colors) in 
     * the picture.  The number of reds, greens, and blues will be 4
     */
    public void posterize()
    {
        Pixel pixel = null;
        int redValue = 0;
        int greenValue = 0;
        int blueValue = 0;

        // loop through the pixels
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {

                // get the current pixel and colors
                pixel = this.getPixel(x,y);
                redValue = pixel.getRed();
                greenValue = pixel.getGreen();
                blueValue = pixel.getBlue();

                // check for red range and change color
                if (redValue < 64)
                    redValue = 31;
                else if (redValue < 128)
                    redValue = 95;
                else if (redValue < 192)
                    redValue = 159;
                else
                    redValue = 223;

                // check for green range
                if (greenValue < 64)
                    greenValue = 31;
                else if (greenValue < 128)
                    greenValue = 95;
                else if (greenValue < 192)
                    greenValue = 159;
                else
                    greenValue = 223;

                // check for blue range
                if (blueValue < 64)
                    blueValue = 31;
                else if (blueValue < 128)
                    blueValue = 95;
                else if (blueValue < 192)
                    blueValue = 159;
                else
                    blueValue = 223;

                // set the colors
                pixel.setRed(redValue);
                pixel.setGreen(greenValue);
                pixel.setBlue(blueValue);
            }
        }
    }

    /**This method creates a vertical mirror by reflecting the pixels on the left side of the mirror point ot those on the right side of the mirrir point.
     * 
     *@param ySTART the upper vertical edge of the mirror.
     *@param yEND the lower vertical edge of the mirror.
     *@param xSTART the rightmost edge of the mirror.
     *@param xEND the centre of the mirror.
     */
    public void mirrorSpecifier(int ySTART, int yEND, int xSTART, int mirrorPoint )
    {
        GreenfootImage img1 = this.getImage();

        Pixel leftPixel = null;
        Pixel rightPixel = null;
        int count = 0;
        //int position = greenfoot.MouseInfo.getX();
        //Handlerclass handler = new Handlerclass();
        //int xStart =  img1.addMouseListener(handler); 

        // loop through the rows
        for (int y = ySTART; y < yEND; y++)
        {
            // loop from 13 to just before the mirror point
            for (int x = xSTART; x < mirrorPoint; x++)
            {
                System.out.println("Copying color from " +
                    x + " to " +
                    (mirrorPoint + (mirrorPoint - x)));
                leftPixel = getPixel(x, y);       
                rightPixel = getPixel(mirrorPoint + (mirrorPoint - x), y);
                rightPixel.setColor(leftPixel.getColor());
                count = count + 1;
            }
        }
        System.out.println("We copied " + count + " pixels");
    }

    /**This method produces tv noise image that produces a random gray scale pixels that produce no definite shape, just tv noise.
     * 
     *@param howLong is the length for which this method should keep going.
     */
    public void tvNoise(int howLong)
    {
        Pixel pix = null;
        int count = 0;
        for(int i =0; i<howLong; i++)
        {
            while(count == 0)
            {

                for(int x = 0; x< this.getWidth() ; x++)
                {
                    for(int y=0; y<this.getHeight()  ; y++){

                        double rand22 = Greenfoot.getRandomNumber(66);
                        pix = this.getPixel(x,y);
                        pix.setColor(new Color(rand22,rand22,rand22));

                    }

                }

            }
        }
    }

    /**This method produces a kind of sketch looking picture by comapring the color of each pixel with the one below and changes the color 
     * if satisfies a particular condition.
     * 
     *@param amount is the threshold value. 
     */
    public void drawStyle(double amount) {
        Pixel topPixel = null;
        Pixel bottomPixel = null;
        double topAverage = 0.9;
        double bottomAverage = 0.9;
        int endY = this.getHeight() - 1;

        /* loop through y values from 0 to height - 1 
         * (since compare to below pixel) */
        for (int y = 0; y < this.getHeight()-1; y++) {

            // loop through the x values from 0 to width
            for (int x = 0; x < this.getWidth()-1; x++) {

                // get the top and bottom pixels
                topPixel = this.getPixel(x,y);
                bottomPixel = this.getPixel(x,y+1);

                // get the color averages for the two pixels
                topAverage = topPixel.getAverage();
                bottomAverage = bottomPixel.getAverage();

                /* check if the absolute value of the difference 
                 * is less than the amount */
                if (Math.abs(topAverage - bottomAverage) < amount) {
                    topPixel.setColor(Color.LIGHT_GRAY);

                    // else set the color to black
                } else {
                    topPixel.setColor(Color.DARK_GRAY);
                }
            }
        }
    }

}
