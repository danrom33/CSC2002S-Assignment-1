package MonteCarloMini;

import java.util.concurrent.RecursiveTask;

import MonteCarloMini.TerrainArea.Direction;


public class SearchParallel extends RecursiveTask<int[]>{

   // private Search searchArray[];
    private TerrainArea terrain;
    private SearchInner[] searches;
    private int high;
    private int low;
    private int minHeight = Integer.MAX_VALUE;

    private static int SEQUENTIAL_CUTOFF = 1;

//    public SearchParallel(Search[] searchArray, int low, int high){
//         this.searchArray = searchArray;
//         this.high = high;
//         this.low = low;
//    }

    public SearchParallel(SearchInner[] searches, int low, int high){
        this.searches = searches;
        this.low = low;
        this.high = high;
    }

    public SearchParallel(){
        
    }
    

    protected int[] compute(){
        int[] results = new int[2];
        if ((high - low) <= SEQUENTIAL_CUTOFF) {
            int local_min = Integer.MAX_VALUE;
            int finder = -1;
    
            for (int i = low; i < high; i++) {
                
                int currentLocalMin = searches[i].find_valleys();
                if (currentLocalMin < local_min && !searches[i].isStopped()) {
                    local_min = currentLocalMin;
                    finder = i;
                    //System.out.println(i+ ": " + local_min + "\t" + xMin + "\t" + yMin + "\n");
                }
            }
    
            results[0] = local_min;
            results[1] = finder;
    
            return results;
        }
        else{
            //System.out.print("High:" + high + "\tLow:" + low);
            SearchParallel left = new SearchParallel(searches,low, (high+low)/2);
            SearchParallel right = new SearchParallel(searches, (high+low)/2, high);
            left.fork();
            int[] rightHeight = right.compute();
            int[] leftHeight = left.join();
            int[] result;
            if(rightHeight[0] < leftHeight[0])
                result = rightHeight;
            else
                result = leftHeight;
            return result;
        }

        //return minHeight;
    }

    public class SearchInner{

        private int id;				// Searcher identifier
        private int pos_row, pos_col;	// Position in the grid
        private int steps; //number of steps to end of search
        private boolean stopped;			// Did the search hit a previous trail?
        
        private TerrainArea terrain;

        public SearchInner(int id, int pos_row, int pos_col, TerrainArea terrain) {
            this.id = id;
            this.pos_row = pos_row; //randomly allocated
            this.pos_col = pos_col; //randomly allocated
            this.terrain = terrain;
            this.stopped = false;
        }
        
        public int find_valleys() {	
            int height=Integer.MAX_VALUE;
            Direction next = Direction.STAY_HERE;
            while(terrain.visited(pos_row, pos_col)==0) { // stop when hit existing path
                height=terrain.get_height(pos_row, pos_col);
                //System.out.println("Height: " + height);
                terrain.mark_visited(pos_row, pos_col, id); //mark current position as visited
                steps++;
                next = terrain.next_step(pos_row, pos_col);
                switch(next) {
                    case STAY_HERE: return height; //found local valley
                    case LEFT: 
                        //System.out.println("LEFT");
                        pos_row--;
                        break;
                    case RIGHT:
                        //System.out.println("RIGHT");
                        pos_row=pos_row+1;
                        break;
                    case UP: 
                        //System.out.println("UP");
                        pos_col=pos_col-1;
                        break;
                    case DOWN: 
                        //System.out.println("DOWN");
                        pos_col=pos_col+1;
                        break;
                }
            }
            stopped=true;
            //System.out.println("VALLEY (" + height + ")FOUND AT: " + pos_row + ", " + pos_col + "\n\n");
            return height;
        }

        public int getID() {
            return id;
        }

        public int getPos_row() {
            return pos_row;
        }

        public int getPos_col() {
            return pos_col;
        }

        public int getSteps() {
            return steps;
        }
        public boolean isStopped() {
            return stopped;
        }

    }

}
