package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Map extends Board {  
    public int [][] levelMap() {
        int [][] level1 = new int[][] {
        	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1},
            {1, 2, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 5, 0, 0, 2, 0, 2, 0, 2, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 2, 0, 2, 0, 0, 0, 1},
            {1, 2, 1, 0, 1, 0, 1, 0, 1, 0, 1, 2, 1, 2, 1},
            {1, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        scene = new int[level1.length][level1[0].length];
        for(int i = 0;i< level1.length;i++)
        {
            for(int j = 0;j < level1[0].length;j++)
            {
                scene[i][j]=level1[i][j];
            }

        }
        
        
        //set vi tri  gan nhan vat de player co the di 
        scene[1][1] = 0;
        scene[2][1] = 0;
        scene[1][2] = 0;
        /*
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                    System.out.print(scene[i][j] + " ");
            }
            System.out.println();
        }
        */
        return scene;
    }
    
    
    public int [][] levelMap2() {
        int [][] level1 = new int[][] {
        	{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 1},
            {1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1},
            {1, 0, 0, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 1},
            {1, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 2, 1, 2, 1, 0, 1, 0, 1, 2, 1, 2, 1, 2, 1},
            {1, 0, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
            {1, 0, 0, 2, 0, 0, 2, 1, 2, 2, 2, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1, 2, 1, 0, 1, 2, 0, 0, 1},
            {1, 2, 0, 0, 2, 0, 2, 0, 0, 0, 0, 2, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };

        scene = new int[level1.length][level1[0].length];
        for(int i = 0;i< level1.length;i++)
        {
            for(int j = 0;j < level1[0].length;j++)
            {
                scene[i][j]=level1[i][j];
            }

        }
        
        
        //set vi tri  gan nhan vat de player co the di 
        scene[1][1] = 0;
        scene[2][1] = 0;
        scene[1][2] = 0;
        /*
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                    System.out.print(scene[i][j] + " ");
            }
            System.out.println();
        }
        */
        return scene;
    }
}