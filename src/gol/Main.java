package gol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        
        char[][] startGrid = null;

        try {
            startGrid = setupGrid("glider.gol");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //output startgrid
        for(int i=0; i<startGrid.length;i++){
            for(int j=0;j<startGrid[0].length;j++){
                System.out.print("[" + startGrid[i][j] + "]");
            }
            System.out.println();
        }

        for(int k=1;k<=100;k++){
            char[][] nextGrid = evalNext(startGrid);
        
            //output nextGrid
            System.out.printf("Run: %d\n",k);
            for(int i=0; i<nextGrid.length;i++){
                for(int j=0;j<nextGrid[0].length;j++){
                    System.out.print("[" + nextGrid[i][j] + "]");
                }
                System.out.println();
            }
            startGrid = nextGrid;
            System.out.println();
        }
        

    }

    public static char[][] evalNext(char[][] currentGrid){
        
        int ySize = currentGrid.length;
        int xSize = currentGrid[0].length;
        char[][] nextGrid = new char[ySize][xSize];
        int neighbours = 0;

        for(int i=0;i<ySize;i++){
            for (int j=0;j<xSize;j++){
                try{
                    if(currentGrid[i-1][j-1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i-1][j]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i-1][j+1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i][j-1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i][j+1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i+1][j-1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i+1][j]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                try{
                    if(currentGrid[i+1][j+1]=='*'){
                    neighbours++;
                    }
                } catch (Exception e){}
                
                if (neighbours <=1){
                    nextGrid[i][j] = ' ';
                } else if(neighbours == 2){
                    if (currentGrid[i][j]=='*'){
                        nextGrid[i][j] = '*';
                    } else nextGrid[i][j] = ' ';
                } else if(neighbours ==3){
                    nextGrid[i][j] = '*';
                } else if(neighbours >=3){
                    nextGrid[i][j] = ' ';
                }

                //nextGrid[i][j]=(char)(neighbours+'0');
                neighbours =0;


            }
        }
        


        return nextGrid;
    }

    public static char[][] setupGrid(String filename) throws IOException{
        char[][] startgrid=null;

        //readfile
        File golfile = new File("golfiles/%s".formatted(filename));
        BufferedReader reader = new BufferedReader(new FileReader(golfile));
        String readLine = null;
        int xSize;
        int ySize;
        int xStart = 0;
        int yStart;
        int currentY = 0;
        int currentX = 0;
        Scanner scan;

        while((readLine = reader.readLine()) !=null){
            
            scan = new Scanner(readLine);
            String first = scan.next();

            switch (first){
                case "#":{
                    break;
                }

                case "GRID":{
                    xSize = scan.nextInt();
                    ySize = scan.nextInt();
                    System.out.printf("%d %d\n",xSize,ySize);

                    //initialise blank grid.
                    startgrid = new char[ySize][xSize];

                    for(int i=0; i<ySize;i++){
                        for(int j=0;j<xSize;j++){
                            startgrid[i][j] = ' ';
                        }
                    }
                    break;
                }

                case "START":{
                    xStart = scan.nextInt();
                    yStart = scan.nextInt();
                    System.out.printf("%d %d\n",xStart,yStart);

                    currentY = yStart;
                    currentX = xStart;
                    break;
                }

                case "DATA":{
                    while((readLine = reader.readLine()) !=null){
                        char[] readChars = readLine.toCharArray();

                        for(char icon:readChars){
                            startgrid[currentY][currentX]=icon;
                            System.out.print("[" + icon + "]");
                            currentX++;
                        }

                        System.out.println();

                        currentX=xStart;
                        currentY++;
                    }
                    break;
                }

            }
            
        }

        System.out.println("Setup Complete");
        return startgrid;
    }
}