package com.dodgeball.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.dodgeball.game.objects.*;
import com.dodgeball.game.screens.GameScreen;

/**
 * Created by samuel on 6/11/16.
 */
public class World {

    public ShipAI shipOne;
    public ShipAI shipTwo;


    public Array<Bullet> shipOneBullets;
    public Array<Bullet> shipTwoBullets;

    public int generation = 0;
    public int group = 0;
    public Array<String> genomes;
    public Array<Integer> fitnesses;

    public Crosshair crosshair;
    public MathUtils mathUtils;
    public GameScreen gameScreen;

    int random;

    public int gameTimer = 0;
    public int speed = 1;

    public World(GameScreen gameScreen){
        generateGenomes();
        generateShips();

        shipOneBullets = new Array<Bullet>();
        shipTwoBullets = new Array<Bullet>();

        crosshair = new Crosshair(100,100, this);

        mathUtils = new MathUtils();
        this.gameScreen = gameScreen;
    }

    public void update(float deltaTime){
        gameTimer++;
        changeSpeed();
        updateCharacters(deltaTime);
        updateBullets(shipOneBullets,shipTwo,deltaTime);
        updateBullets(shipTwoBullets,shipOne,deltaTime);
        updateShips(deltaTime);
        updateInterface(deltaTime);
        repeatCycle();
    }
    public void changeSpeed(){
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            if(speed==1){
                speed = 10;
                gameTimer=gameTimer/speed;
            }else {
                speed = 1;
                gameTimer=gameTimer*speed;
            }
        }
    }
    public void generateGenomes(){

        genomes = new Array<String>();
        fitnesses = new Array<Integer>();
        for(int i =0;i<16;i++){
            genomes.add("");
            fitnesses.add(0);
            for(int j=0;j<32;j++){
                genomes.set(i,genomes.get(i)+String.valueOf(MathUtils.random(0,10   )));
            }
        }
    }
    public void repeatCycle(){
        if(gameTimer==1000/speed&&group!=8){
            fitnesses.set(group*2,shipOne.fitness);
            fitnesses.set(group*2+1,shipTwo.fitness);
            group++;
            gameTimer = 0;
            shipOneBullets.clear();
            shipTwoBullets.clear();
            if(group == 8){
                sort();
                System.out.println("Highest fitness of generation "+generation+": "+fitnesses.get(0));
                System.out.println("Genome: "+genomes.get(0));
                generation++;
                group = 0;
                geneticAlgorithm();
            }
            shipOne = new ShipAI(500, 540, genomes.get(group * 2), shipTwo, this);
            shipTwo = new ShipAI(1420, 540, genomes.get(group * 2 + 1), shipOne, this);
            shipOne = new ShipAI(500, 540, genomes.get(group * 2), shipTwo, this);

        }
    }
    public void geneticAlgorithm(){
        for (int i =1;i<genomes.size;i++){
            genomes.set(i,genomes.get(0));
            for(int j=0;j<10;j++){
                random = MathUtils.random(0,31);
                genomes.set(i,genomes.get(i).substring(0,random)+mathUtils.random(0,10)+genomes.get(i).substring(random+1,32));
            }
        }
        for (int i =0;i<fitnesses.size;i++){
            fitnesses.set(i,0);
        }
        genomes.shuffle();

    }
    public void sort(){
        for (int i = fitnesses.size; i>=0;i--){
            for (int j = 0; j<fitnesses.size-1;j++){
                if(fitnesses.get(j)<fitnesses.get(j+1)){
                    String tempGenome = genomes.get(j);
                    genomes.set(j,genomes.get(j+1));
                    genomes.set(j+1,tempGenome);

                    int tempFitness = fitnesses.get(j);
                    fitnesses.set(j,fitnesses.get(j+1));
                    fitnesses.set(j+1,tempFitness);
                }
            }
        }
    }
    public void generateShips(){
        shipOne = new ShipAI(500,540,genomes.get(0),shipTwo,this);
        shipTwo = new ShipAI(1420,540,genomes.get(1),shipOne,this);
        shipOne = new ShipAI(500,540,genomes.get(0),shipTwo,this);
    }
    public void updateCharacters(float deltaTime){
       // playerOne.update(deltaTime);
    }

    public void updateShips(float deltaTime){
        shipOne.update(deltaTime);
        shipTwo.update(deltaTime);
    }

    public void updateBullets(Array<Bullet> bullets,ShipAI target, float deltaTime){
        for(int i = 0;i<bullets.size;i++){
            bullets.get(i).update(deltaTime);

            if(bullets.get(i).timer==300||bullets.get(i).isColliding(target.bounds)){
                bullets.removeIndex(i);
                target.getHit();
                if(target==shipOne){
                    shipTwo.fitness=shipTwo.fitness+3;
                }else if(target==shipTwo){
                    shipOne.fitness=shipOne.fitness+3;
                }
            }
        }
    }

    public void updateInterface(float deltaTime){
        crosshair.update(deltaTime);
    }
}
