package h1z1.game;


public class Maze {



        public static final int WIDTH = 24;
        public static final int HEIGHT = 24;
        public static final int SIZE = 20;
        public static final int OFFSET = 100;

        boolean[][] maze = new boolean[24][];

        private boolean[] raw = new boolean[]{
                true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,
                        true,true,true,true,true,true,false,true,true,true,true,true,true,true,true,true,true,true,false,true,true,true,true,true,
                        true,true,false,false,false,true,false,false,false,true,false,false,false,true,false,false,false,true,false,false,false,false,true,true,
                        true,false,false,true,false,true,true,true,false,true,false,true,false,false,false,true,false,true,false,true,true,false,true,true,
                        true,true,false,true,false,false,false,false,false,true,true,false,false,true,true,true,false,true,false,true,false,false,true,true,
                        true,true,true,true,false,false,true,false,false,false,true,true,false,false,false,true,false,true,false,false,false,true,true,true,
                        true,true,false,false,false,true,true,false,true,false,false,false,false,true,true,true,false,false,false,true,false,true,true,true,
                        true,true,false,false,true,true,true,false,false,false,true,false,false,false,false,true,true,true,true,true,false,false,true,true,
                        true,true,false,false,false,false,false,true,false,true,false,false,true,false,false,false,true,false,false,false,false,true,true,true,
                        true,true,true,true,false,true,false,true,true,false,false,true,true,true,false,false,true,false,true,true,true,true,true,true,
                        true,false,false,false,false,false,false,true,false,false,false,false,true,false,false,false,false,false,false,true,false,true,true,true,
                        true,true,false,true,true,false,false,true,true,false,true,false,false,false,true,false,false,false,false,false,false,false,false,true,
                        true,true,false,true,true,true,false,false,true,false,false,true,false,true,false,false,false,true,false,true,true,true,true,true,
                        true,true,false,false,false,false,true,false,false,false,false,false,true,false,false,true,false,true,false,false,false,false,true,true,
                        true,true,false,true,false,false,false,false,true,false,false,true,false,true,false,false,false,false,true,false,true,false,true,true,
                        true,true,false,true,true,true,true,false,false,false,true,false,false,false,true,false,false,true,false,false,false,false,true,true,
                        true,true,false,true,false,false,false,false,false,false,false,false,true,false,false,false,true,false,true,true,true,false,true,true,
                        true,true,true,false,true,false,false,true,true,true,false,true,true,true,false,false,true,false,false,false,false,false,true,true,
                        true,true,false,false,true,true,false,false,true,true,false,false,true,false,false,true,true,false,true,true,true,false,true,true,
                        true,true,false,true,true,true,true,false,false,true,false,false,false,false,false,true,true,false,false,false,true,false,true,true,
                        true,false,false,false,false,false,true,false,false,true,false,true,true,true,false,false,true,false,true,true,true,false,true,true,
                        true,true,true,false,true,false,false,false,false,false,false,false,true,false,false,false,true,false,false,false,false,false,true,true,
                        true,true,true,false,true,true,true,true,true,true,true,false,false,false,true,false,false,false,true,true,true,false,true,true,
                        true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true
        };

        public boolean getValue(int x,int y){

                int position = y * WIDTH + x;

                return raw [position];
        }

}
