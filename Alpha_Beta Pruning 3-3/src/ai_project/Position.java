
package ai_project;
import java.util.LinkedList;
import java.util.List;


public class Position {
    
    public char[] game_board;
    public char turn;
    public int dim=3;
    public static Integer[]  best_choice=new Integer[2];
        
    
    public Position() {
        this.game_board="         ".toCharArray();
        this.turn='x';
    }
    
    public Position(char[] board , char turn){
        this.game_board=board;
        this.turn=turn;
    }
    
    public Position(String str){
        this.game_board = str.toCharArray();
        this.turn='x';
    }
    
    public Position(String str,char turn){
        this.game_board=str.toCharArray();
        this.turn=turn;
        
    }

   public Position move(int idx) {
       char[] newBoard= game_board.clone();
       newBoard[idx]=turn;
        return new Position(newBoard , turn=='x' ? 'o' : 'x');
    }

   public Integer[] possibleMoves() {
       List<Integer> list=new LinkedList<Integer>();
       for (int i=0; i<game_board.length; i++){
           if(game_board[i]==' '){
               list.add(i);
           }
       }
       Integer[] array=new Integer[list.size()];
       list.toArray(array); 
        return array;
    }
   
   public boolean win_condition(char turn , int start , int step){
       for(int i=0;i<3;i++){
           if(game_board[start + step*i] != turn){
               return false;    
           }
       }
       return true;
   }
   
   public boolean win(char turn) {
       for (int i=0; i<dim; i++){// examine rows and column
           if(win_condition(turn,i*dim,1) || win_condition(turn,i,dim)){
               return true;    
           }  
       }
       if(win_condition(turn,dim-1,dim-1) || win_condition(turn,0,dim+1)){
           return true;
       }
        return false;
    }

   public int exist_value( int targetValue){
       
       for(int i=0;i<possibleMoves().length;i++){
		if(possibleMoves()[i]==targetValue)
			return 1;
	}
	return 0;
       
       
   }
    public int alpha_beta(double fatherAlpha,double fatherBeta) {
       double beta=Double.POSITIVE_INFINITY;
       double alpha=Double.NEGATIVE_INFINITY;
    
       if(win('x')){return 100;}
       if(win('o')){return -100;}
       if(possibleMoves().length==0){return 0;}
       for(Integer idx: possibleMoves()){
           Integer value=move(idx).alpha_beta(alpha,beta);
           
           if( turn=='x' && alpha<value){
                 alpha=value; 
                 
           }
           if(turn=='o' && beta>value){
               beta=value; 
                 
           }
           if(turn=='x' && alpha>=fatherBeta){
               break;
           }
            if(turn=='o' && beta<=fatherAlpha){
               break;
           }
            
       }
       int result=0;
       if(turn=='x' ){
             result=(int)alpha;
           }
            if(turn=='o' ){
               result=(int)beta;
           }
            
      
       return result + (turn=='x' ? -1 : 1);
    }

    public int bestMove() {
        
        double beta=Double.POSITIVE_INFINITY;
        double alpha=Double.NEGATIVE_INFINITY;
        int best=-1;
        
       for(Integer idx: possibleMoves()){
          
           Integer value=move(idx).alpha_beta(alpha,beta);
           if( turn=='x' && alpha<value){
                 alpha=value; 
                 best=idx;
           }
           if(turn=='o' && beta>value){
               beta=value; 
                 best=idx;
           }           
       }
       return best;
    }
    public boolean gameEnd() {
        return win('x') || win('o') || possibleMoves().length==0;
    }
    
}
