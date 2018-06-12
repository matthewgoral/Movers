import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Movers extends PApplet {

Mover[] movers;
int count = 35;
int min = 20;
int max = 70;
int offset = 500;
int interval = 15;

public void setup() {
  
  movers = new Mover[count];
  for (int i = 0; i < count; i++) {
    movers[i] = new Mover();
  }
}

public void draw() {
  background(240);
  stroke(225);
  for(int i = 0; i < width + offset; i += interval) {
    line(i, 0, i - offset, height);
  }
  for (Mover mover : movers) {
    mover.display();
    mover.move();
    mover.grab();
  }
}

class Mover {
  int c;
  float x;
  float y;
  float h;
  float w;
  
  Mover() {
    c = color(random(100, 255),random(100, 255),random(100, 255));
    x = random(width/4, width-width/4);
    y = random(height/4, height-height/4);
    h = random(min, max);
    w = random(min, max);
  }
  
  public void display() {
    noStroke();
    rectMode(CENTER);
    fill(0, 50);
    rect(x+4, y+4, w, h);
    fill(c);
    rect(x, y, w, h);
  }
  
  public void move() {
    x += randomGaussian();
    y += randomGaussian();
    h += randomGaussian();
    w += randomGaussian();
    if(h<10){h=10;}
    if(w<10){w=10;}
    if(h>100){h=100;}
    if(w>100){w=100;}
  }
  
  public void grab() {
    if( mousePressed &&
        mouseX > x - w/2 &&
        mouseY > y - h/2 &&
        mouseX < x + w/2 &&
        mouseY < y + h/2) {
        
       x = mouseX;
       y = mouseY;
    }
  }
  
}
  public void settings() {  size(1280, 720); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Movers" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
