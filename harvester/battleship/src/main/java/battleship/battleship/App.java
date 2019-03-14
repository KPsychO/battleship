package battleship.battleship;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {
        
    	String url = "http://es.battleship-game.org/";
    	DataHarvester dt = new DataHarvester(url);
    	ArrayList<boolean[][]> battlefields = new ArrayList<boolean[][]>();
    	int[][] score = new int[10][10];
    	double[][] matrix = new double[10][10];
    	
    	int n_btfs = Integer.valueOf(args[0]);
    	
		battlefields = dt.getData(n_btfs);
		
		System.out.print("\n");
		System.out.print("\n");
		
		for(boolean[][] btf : battlefields) {
    		
    		for(int i = 0; i < 10; i++) {
    			
    			for(int j = 0; j < 10; j++) {
    				
    				if(btf[i][j] == true)
    					score[i][j]++;

    			}

    		}
    		
    	}
		
		for(int i = 0; i < 10; i++) {
			
			for(int j = 0; j < 10; j++) {
				
				System.out.print(score[i][j]);
				matrix[i][j] += ((float)score[i][j])/ n_btfs * 255;
				if(j != 9) System.out.print(" ");
				
			}
			
			System.out.print("\n");
		}
		System.out.print("\n");
		System.out.print("\n");
		
		System.out.print('{');
		for(int i = 0; i < 10; i++) {
			
			System.out.print('{');
			for(int j = 0; j < 10; j++) {
				System.out.print(matrix[i][j]);
				if(j != 9) System.out.print(", ");
				
			}
			System.out.print('}');
			if(i != 9) System.out.print(", ");

		}
		
		System.out.print("};");
    	
		System.out.print("\n");
		System.out.print("\n");
		
    }
}
