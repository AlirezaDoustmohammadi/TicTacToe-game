package ai_project;
import org.junit.Test;
import static org.junit.Assert.*;

public class PositionTest {
    
//in this step we create 9 position and the default position is 'x' and
//we convert position to string(compare with 'x' and 'o')    
    @Test
    public void testNew(){
        Position position = new Position();
        assertEquals("         ", position.toString());
        assertEquals('x', position.turn);   
    }
    
    @Test
    public void testMove(){
        Position position= new Position().move(1);
        assertEquals(" x       ", position.toString());
        assertEquals('o', position.turn);
    }
    
    @Test
    public void testPossibleMoves(){
        Position position= new Position().move(1).move(3).move(4);
        assertArrayEquals(new Integer[] {0,2,5,6,7,8}, position.possibleMoves());   
    }
    
    @Test
    public void testWin(){
        assertFalse(new Position().win('x'));
        assertTrue(new Position("xxx      ").win('x'));
        assertTrue(new Position("   ooo   ").win('o'));
        assertTrue(new Position("x  x  x  ").win('x'));
        assertTrue(new Position("  x x x  ").win('x'));   
        assertTrue(new Position("x   x   x").win('x'));   
    }
    
    @Test
    public void testMiniMax(){
        assertEquals( 100, new Position("xxx      ").minimax());
        assertEquals(-100, new Position("ooo      ").minimax());        
        assertEquals(   0, new Position("xoxoxooxo").minimax()); 
        assertEquals(  99, new Position(" xx      ").minimax());
        assertEquals( -99, new Position(" oo      ",'o').minimax()); 
    }
    
    @Test
    public void testBestMove(){
        assertEquals(0, new Position(" xx      ").bestMove());
        assertEquals(1, new Position("o o      ",'o').bestMove()); 
    }
    
    @Test
    public void testGameEnd(){
        assertFalse(new Position().gameEnd());
        assertTrue(new Position("xxx      ").gameEnd());
        
    }
  
}
