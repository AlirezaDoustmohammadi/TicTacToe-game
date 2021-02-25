
package ai_project;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;



public class Game {
 Position position=new Position();   
 public static void main(String[] args) {
     SwingUtilities.invokeLater(new Runnable() {
         public void run() {
             // a graphical windows
             JFrame frame=new JFrame("AI Project");
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setLayout(new GridLayout(3,3));
             final Game game=new Game();
             final JButton[] buttons=new JButton[9];
             background_music("Start");             
             for(int i=0;i<9;i++){
                 final int idx=i;
                 final JButton button=new JButton();
                 buttons[i]=button;
                 button.setPreferredSize(new Dimension(100,100));                 
                 button.setFont(new Font(null,Font.PLAIN, 100));
                 button.addMouseListener(new MouseListener() {
                     public void mouseReleased(MouseEvent e) {}
                     public void mousePressed(MouseEvent e) {}
                     public void mouseExited(MouseEvent e) {}
                     public void mouseEntered(MouseEvent e) {}
                     
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        
                         if(game.position.exist_value(idx)==1){
                             button.setText("" +game.position.turn);
                             game.move(idx);
                             if(!game.position.gameEnd()){
                                 int best=game.position.bestMove();
                                 buttons[best].setText("" + game.position.turn);
                                 game.move(best);
                             }
                             if(game.position.gameEnd()){
                                 if(game.position.win('x')){
                                     JOptionPane.showMessageDialog(null,"Congradulation!");
                                 }else if(game.position.win('o')){
                                     JOptionPane.showMessageDialog(null, "Game Over!");
                                 }else{
                                     JOptionPane.showMessageDialog(null,"No one Won!");
                                 }                             
                             }
                         } 

                     }
                 });
                 frame.add(button);
             }
             frame.pack();
             frame.setVisible(true);             
         }
     }); 
  }

    protected void move(int idx) {
       position=position.move(idx);
    }
    
    
public static void background_music(String Str){
        
        AudioPlayer Play_music = AudioPlayer.player;
        AudioStream audio_stream ;
        AudioData audio_data;
        ContinuousAudioDataStream loop = null;
        try {
                InputStream test = new FileInputStream("./Music/game-background.wav");                      
                audio_stream = new AudioStream(test);
                audio_data = audio_stream.getData();
                AudioPlayer.player.start(audio_stream);
                loop = new ContinuousAudioDataStream(audio_data); 

        } catch (IOException error) {
                System.out.print(error.toString());
        }
        if(Str=="Start"){
            Play_music.start(loop);            
        }
    }    
}

