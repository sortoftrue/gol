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
            startGrid = setupGrid();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //output grid
        for(int i=0; i<startGrid.length;i++){
            for(int j=0;j<startGrid[0].length;j++){
                System.out.print("[" + startGrid[i][j] + "]");
            }
            System.out.println();
        }

    }

    public static char[][] evalNext(char[][] currentGrid){
        char[][] nextGrid;
        int neighbours = 0;



        return nextGrid;
    }

    public static char[][] setupGrid() throws IOException{
        char[][] startgrid=null;

        //readfile
        File golfile = new File("golfiles/blinker.gol");
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