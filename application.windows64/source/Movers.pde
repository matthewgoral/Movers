Mover[] movers;
int count = 35;
int min = 20;
int max = 70;
int offset = 500;
int interval = 15;

void setup() {
  size(1280, 720);
  movers = new Mover[count];
  for (int i = 0; i < count; i++) {
    movers[i] = new Mover();
  }
}

void draw() {
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
  color c;
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
  
  void display() {
    noStroke();
    rectMode(CENTER);
    fill(0, 50);
    rect(x+4, y+4, w, h);
    fill(c);
    rect(x, y, w, h);
  }
  
  void move() {
    x += randomGaussian();
    y += randomGaussian();
    h += randomGaussian();
    w += randomGaussian();
    if(h<10){h=10;}
    if(w<10){w=10;}
    if(h>100){h=100;}
    if(w>100){w=100;}
  }
  
  void grab() {
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