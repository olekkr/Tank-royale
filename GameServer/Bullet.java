import processing.core.*;

// Bullet class representerer et skud i spillet
class Bullet extends Entity  {
  int dmg;
  Player owner;
  int ownerID;

  Bullet(PApplet a, Player player, PVector position, PVector velocity) {
    bind(a);
    pos = position;
    vel = velocity;
    owner = player;
    ownerID = player.ID;
    world = player.world;
    size = world.BulletSize;
    dmg = world.BulletDmg;
  }

  void Render() {
    g.noStroke();
    g.fill(000000);
    float[] relpos = world.relPos(pos.x, pos.y);
    g.circle(relpos[0], relpos[1], size);
  }

  // collide-funktion med Entity 
  void Collide(Entity e) {
    if (e instanceof Player && ((Player) e).alive) {
      Kill();
    }
  }
  //generisk collide-funktion med gameobject
  void Collide(GameObject g) {
    if (g instanceof GameObject) {
      Kill();
    }
  }

  //void CheckCollision(Entity e) {
  //  if(e instanceof Player){
  //    if(pos.dist(e.pos) < e.size + size){
  //      Collide(e);
  //      e.Collide(this);
  //    }
  //  }
  //}

  //void CheckCollision(GameObject g) {
  //  if (g instanceof Wall) {
  //    
  //  }
  //}

  void CheckCollision(World w) {
    if (pos.x < 0 || pos.x > w.MapSize[0] || pos.y < 0 || pos.y > w.MapSize[1]) {
      Kill();
    }
  }

  void CheckCollisions() {
    CheckCollision(world);
  }
}
