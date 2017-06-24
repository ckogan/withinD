/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trove.withind;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Clark
 */
public class withind {
    int[] id_refs;
    int[] id;
    
    public withind(int[] id, int[] id_refs) {
        this.id_refs = id_refs;
        this.id = id;
    }
    
    public static void main(String args[]) {
        double[] x = { -0.138,2.314,0.349,1.217,0.596,2.222,0.829,-1.718,0.986,-0.062,2.471,0.576,-0.565,-1.226,0.278,-0.757,1.194,2.346,-0.41,-1.524,0.274,0.709,-0.579,-2.062,-0.506,-0.78,-1.343,-0.015,0.166,-0.949,0.534,0.327,-0.429,-0.751,0.974,0.708,0.626,-2.025,-0.601,-0.535,-0.742,1.005,-1.494,1.361,1.29,-0.847,-0.149,2.096,-0.307,-0.479,1.179,-0.683,0.986,0.54,-0.357,-0.855,-1.816,-1.152,0.369,0.215,0.485,0.318,-0.141,1.026,-0.589,0.73,-0.346,1.464,-0.867,0.52,-0.815,1.153,-1.585,-0.369,-1.094,-1.51,-0.65,0.531,0.068,-0.963,0.966,1.041,-1.286,0.1,0.356,-0.213,0.01,0.404,-0.836,-0.436,-0.211,0.329,-0.545,-0.701,-0.655,0.016,-1.338,0.696,0.806,-1.864 };
        double[] y = { -0.073,0.578,0.137,-0.526,0.122,-0.252,1.902,-0.639,-1.403,-1.178,-0.681,-0.043,1.271,0.434,-0.875,-0.026,-0.642,0.293,0.763,0.207,-0.885,-1.06,-1.871,-1.148,-2.879,1.575,1.163,-1.596,-0.769,0.575,-0.633,-0.095,-0.162,0.905,-0.285,1.199,-1.113,0.973,1.413,-0.548,0.699,-0.258,-0.148,-0.615,1.384,-0.302,1.292,-1.921,0.216,-1.166,-0.695,-0.884,1.262,0.05,0.336,0.346,-1.683,0.679,1.585,-1.357,-1.372,2.32,-0.669,-0.604,0.099,-0.275,-2.345,0.191,-0.189,0.859,0.291,-1.533,1.592,0.804,1.482,2.131,-0.748,0.5,-0.189,-1.995,-0.267,0.639,0.221,0.202,0.865,-0.34,-1.215,-0.571,-0.487,-0.903,-0.501,-0.193,-0.126,1.087,0.458,1.336,-1.942,-0.109,0.125,0.365 };
        int[] id = { 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100 };
    }
    
    public static int[] within_d_of_point(int[] id, ArrayList<double[]> data, double x_ref, double y_ref, double max_dist) {
        int n = id.length;
        double d = max_dist;
        double big_left = x_ref - d;
        double big_right = x_ref + d;
        double big_bottom = y_ref - d;
        double big_top = y_ref +d;
        double dsq = d / Math.sqrt(2);
        double small_left = x_ref - dsq;
        double small_right = x_ref + dsq;
        double small_bottom = y_ref - dsq;
        double small_top = y_ref + dsq;
        
        LinkedList<Integer> inside_circle = new LinkedList<Integer>();
        int i = 0;
        for(double[] row : data){
            double xi = row[0];
            double yi = row[0];
            if(!(xi > big_right | xi < big_left | yi < big_bottom | yi > big_top)) {
                if(!(xi < small_right & xi > small_left & yi > small_bottom & yi < small_top)) {
                    double rx = xi - x_ref;
                    double ry = yi - y_ref;
                    if(rx*rx+ ry*ry < d*d) {
                        inside_circle.add(i);
                    } else {
                        inside_circle.add(i);
                    } 
                }    
            }
            i++;
        }
        return inside_circle.stream().mapToInt(k->k).toArray();
    }
}
