//HauntedHouse
//James Nelson
//September 8, 2021
//Computer Programming 1

/******************
Create a project with a house/maze/building etc with at least:
 -10 Rooms
 -1 Sound Files
 -Several images using piskel
 -GUI based, text, or a hybrid of the two
******************/

import javax.swing.*;
import java.io.File;
import javax.sound.sampled.*;
import java.util.*;

public class HauntedHouse {
   public static void main(String args[]) {
      // arrayList for the inventory
      ArrayList<String> inventory = new ArrayList<String>();
      
      System.out.println("");

      // starting the game
      HauntedHouse game = new HauntedHouse();
      game.intro(game, inventory);

   }

   // GUI to show the player if they want to pick an item up or leave it
   public void item(HauntedHouse game, ArrayList<String> inventory) {
      Object itemObject[] = { "take knife", "leave it" };

      ImageIcon knife = new ImageIcon("./img/knife.png");
      String itemOption = (String) JOptionPane.showInputDialog(null,
            "You turn around the corner and find a knife on the floor. \n You wonder what left this here \n Pick an option: take knife, leave it",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, knife, itemObject, itemObject);
      if (itemOption.toLowerCase().equals("take knife")) {
         inventory.add("knife");
         JOptionPane.showMessageDialog(null,
               "You pick up the item and go back to the intersection from where you came from", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
         game.success(game, inventory);
      } else if (itemOption.toLowerCase().equals("leave it")) {
         inventory.add("no items");
      }
   }

   // success sound when choosing a correct direction
   public void success(HauntedHouse game, ArrayList<String> inventory) {
      try {
         AudioInputStream input = AudioSystem.getAudioInputStream(new File("audio/success.wav"));
         Clip clip = AudioSystem.getClip();
         Thread.sleep(10); // 1000ms
         clip.open(input);
         clip.loop(0);
         Thread.sleep(1000);
      } catch (Exception sound) {
      }
   }

   // death sound when choosing an incorrect direction
   public void died(HauntedHouse game, ArrayList<String> inventory) {
      try {
         AudioInputStream input = AudioSystem.getAudioInputStream(new File("audio/died.wav"));
         Clip clip = AudioSystem.getClip();
         Thread.sleep(10); // 1000ms
         clip.open(input);
         clip.loop(0);
         Thread.sleep(1000);
      } catch (Exception sound) {
      }
   }

   // intro sequence with opening lore and map
   public void intro(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon maze = new ImageIcon("./img/startMaze.png");
      Object introObject[] = { "start", "exit" };

      String introOption = (String) JOptionPane.showInputDialog(null,
            "HauntedHouse Maze Project \n You have arrived at a House that appears to be Haunted. \n The door is locked so you decide to go to the back. \n You enter the back in which the walls are vines. \n As you enter, the entrance behind you closes and now you are in a maze that you must escape. \n There may be unknown creatures here as well. \n There is a map on your left. \n Light green is the start\n Light red is the exit to the stairs \n Dark red are enemies \n Light blue are items \n Good Luck \n Select an option: start, exit",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, maze, introObject, introObject[0]);

      if (introOption.toLowerCase().equals("start")) {
         game.success(game, inventory);
         game.start(game, inventory);
      } else if (introOption.toLowerCase().equals("exit")) {
         System.exit(0);
      }
   }

   // invalid screen if a selection is invalid
   public void invalid(HauntedHouse game, ArrayList<String> inventory) {
      JOptionPane.showMessageDialog(null, "Invalid Selection!", "HauntedHouse", JOptionPane.ERROR_MESSAGE, null);
   }

   // start screen with the first intersection and choice
   public void start(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon start = new ImageIcon("./img/start.png");
      String option1 = (String) JOptionPane.showInputDialog(null,
            "You have entered the maze and you arrive at an intersection. Enter a selection: straight, left",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, start, null, null);

      if (option1.toLowerCase().equals("straight")) {
         game.success(game, inventory);
         game.s1(game, inventory);
      } else if (option1.toLowerCase().equals("left")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.start(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.start(game, inventory);
      }
   }

   // death screen with an option to restart or exit
   public void death(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon dead = new ImageIcon("./img/dead.png");
      ImageIcon enemy = new ImageIcon("./img/s4Image.png");
      Object deathObject[] = { "start over", "exit" };

      String fightOption = (String) JOptionPane.showInputDialog(null,
            "As you turn the corner, a monster appears and comes towards you \n Choose an option: fight, run",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, enemy, null, null);

      if (fightOption.toLowerCase().equals("fight") && !inventory.contains("knife")) {
         game.died(game, inventory);
         String deathOption = (String) JOptionPane.showInputDialog(null,
               "You tried to kill the monster with your hands but it didn't work and the monster has killed you \n Choose an option: start over, exit",
               "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, dead, deathObject, deathObject);

         if (deathOption.toLowerCase().equals("start over")) {
            game.success(game, inventory);
            game.intro(game, inventory);
         } else if (deathOption.toLowerCase().equals("exit")) {
            System.exit(0);
         } else {
            game.invalid(game, inventory);
            game.death(game, inventory);
         }
      } else if (fightOption.toLowerCase().equals("fight") && inventory.contains("knife")) {
         JOptionPane.showMessageDialog(null,
               "You successfully killed the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);

      } else if (fightOption.toLowerCase().equals("run")) {
         JOptionPane.showMessageDialog(null,
               "You successfully ran away from the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);
      } else {
         game.invalid(game, inventory);
         game.death(game, inventory);
      }
   }

   // first option screen with an intersection
   public void s1(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s1Image = new ImageIcon("./img/s1Image.png");
      String option2 = (String) JOptionPane.showInputDialog(null,
            "You move through the maze and you encountered an intersection \n Enter a direction: left, right, back right",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, s1Image, null, null);

      if (option2.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.i1(game, inventory);
      } else if (option2.toLowerCase().equals("right")) {
         game.success(game, inventory);
         game.item(game, inventory);
         game.s1(game, inventory);
      } else if (option2.toLowerCase().equals("back right")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.s1(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s1(game, inventory);
      }

   }

   // interlude screen through a hallway
   public void i1(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon i1Image = new ImageIcon("./img/i1Image.png");
      String hallway1 = (String) JOptionPane.showInputDialog(null,
            "As you walk through the corridor, you wonder who lived here, and what creatures are in here. \n You hear something behind you in the distance in the maze \n Enter a direction: straight, backwards",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, i1Image, null, null);
      if (hallway1.toLowerCase().equals("straight")) {
         game.success(game, inventory);
         game.s2(game, inventory);
      } else if (hallway1.toLowerCase().equals("backwards")) {
         game.success(game, inventory);
         JOptionPane.showMessageDialog(null, "You go back to the previous intersection", "HauntedHouse",
               JOptionPane.INFORMATION_MESSAGE, null);
         game.s1(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.i1(game, inventory);
      }
   }

   // second option screen with another intersection
   public void s2(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s2Image = new ImageIcon("./img/s2Image.png");
      String option3 = (String) JOptionPane.showInputDialog(null,
            "You walk past the corridor and the noises from behind you, You have encounted another intersection. \n Enter a direction: right, left",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, s2Image, null, null);
      if (option3.toLowerCase().equals("right")) {
         game.success(game, inventory);
         game.s3(game, inventory);
      } else if (option3.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.item(game, inventory);
         game.s2(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s2(game, inventory);
      }
   }

   // third option with another intersection
   public void s3(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s3Image = new ImageIcon("./img/s3Image.png");
      String option4 = (String) JOptionPane.showInputDialog(null,
            "The end seems to be approaching, but the maze continues and you reach another intersection. There appear to be noises all around you \n Enter a direction: right, left",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, s3Image, null, null);
      if (option4.toLowerCase().equals("left")) {
         game.success(game, inventory);
         game.s4(game, inventory);
      } else if (option4.toLowerCase().equals("right")) {
         game.died(game, inventory);
         game.death(game, inventory);
         game.s3(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s3(game, inventory);
      }
   }

   // fourth option with monster approaching you and you having to fight it
   public void s4(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon s4Image = new ImageIcon("./img/s4Image.png");
      ImageIcon dead = new ImageIcon("./img/dead.png");
      Object deathObject[] = { "start over", "exit" };
      String option5 = (String) JOptionPane.showInputDialog(null,
            "As you turn left, you see the exit, but a monster from the left sees you and comes towards you from the corridor. \n Choose an option: fight, run",
            "HauntedHouse", JOptionPane.PLAIN_MESSAGE, s4Image, null, null);
      if (option5.toLowerCase().equals("fight") && !inventory.contains("knife")) {
         String deathOption = (String) JOptionPane.showInputDialog(null,
               "You tried to kill the monster with your hands but it didn't work and the monster has killed you \n Choose an option: start over, exit",
               "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, dead, deathObject, deathObject);

         if (deathOption.toLowerCase().equals("start over")) {
            game.success(game, inventory);
            game.intro(game, inventory);
         } else if (deathOption.toLowerCase().equals("exit")) {
            System.exit(0);
         } else {
            game.invalid(game, inventory);
            game.death(game, inventory);
         }
      } else if (option5.toLowerCase().equals("fight") && inventory.contains("knife")) {
         game.success(game, inventory);
         game.mazeEnd(game, inventory);

      } else if (option5.toLowerCase().equals("run")) {
         JOptionPane.showMessageDialog(null,
               "You successfully ran away from the monster and you go back to the intersection from where you came",
               "HauntedHouse", JOptionPane.INFORMATION_MESSAGE, null);
         game.s3(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.s4(game, inventory);
      }
   }

   // win screen with an option to exit or restart
   public void mazeEnd(HauntedHouse game, ArrayList<String> inventory) {
      ImageIcon win = new ImageIcon("./img/win.png");
      Object mazeEndObject[] = { "exit", "start over" };

      String option6 = (String) JOptionPane.showInputDialog(null,
            "You fought the monster and you successfully killed the monster with your knife. \n You escaped the maze and leave the premises of the HauntedHouse \n For now... \n Thanks for Playing!",
            "HauntedHouse", JOptionPane.YES_NO_CANCEL_OPTION, win, mazeEndObject, mazeEndObject[0]);

      if (option6.toLowerCase().equals("exit")) {
         System.exit(0);
      } else if (option6.toLowerCase().equals("start over")) {
         game.success(game, inventory);
         game.start(game, inventory);
      } else {
         game.invalid(game, inventory);
         game.mazeEnd(game, inventory);
      }
   }
}