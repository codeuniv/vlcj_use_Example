package ru.codeuniverse;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.jna.Native; // NOT java.lang...
import com.sun.jna.NativeLibrary;

import uk.co.caprica.vlcj.binding.LibVlc;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

public class Demo {
	
public static void main(String[] args) {
   JFrame f=new JFrame();
   f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   f.setVisible(true);
   // 0,0 = wnd top left corner, 1800=width, 1200=height	
   f.setBounds(100,100,1800,1200);
   Canvas c=new Canvas();
   c.setBackground(Color.black);
 
   JPanel p=new JPanel();
   p.setLayout(new BorderLayout());
   p.add(c);
   f.add(p);
   
   // указали путь к плееру VLC (имя папки, внутри к-й файлы 
   // libvlc.dll, libvlccore.dll и папка plugins).
   // Нужны только они (не все). Можешь добавить их к проекту просто.
   NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "R:\\del_win64");
   Native.loadLibrary(RuntimeUtil.getLibVlcLibraryName(), LibVlc.class);
   MediaPlayerFactory mpf= new MediaPlayerFactory();
   EmbeddedMediaPlayer emp= mpf.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(f));
   emp.setVideoSurface(mpf.newVideoSurface(c));
   emp.setEnableMouseInputHandling(false);
   emp.setEnableKeyInputHandling(false);
   String file="K:\\\\Чувства животных_Видео\\\\Реальный мир. Животные. Чувства животных.avi";       
   emp.prepareMedia(file);
   emp.play();
 }
}