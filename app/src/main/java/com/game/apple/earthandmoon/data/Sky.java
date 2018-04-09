package com.game.apple.earthandmoon.data;
import com.game.apple.earthandmoon.program.SkyShaderProgram;

import static android.opengl.GLES20.*;
/**
 * Created by apple on 2016/3/19.
 */
public class Sky {
    private VertexArrary vertexArrary;
    private float[] vertex ;
    int vCount=0;
    float yAngle;
    float scale;
    public Sky(float scale,float yAngle,int vCount) {
        this.scale=scale;
        this.yAngle=yAngle;
        this.vCount=vCount;
        initVertexData();
        vertexArrary = new VertexArrary(vertex);
    }

    private void initVertexData(){
        vertex=new float[vCount*3];
        for (int i = 0; i < vCount; i++) {
            double angleTempJD=Math.PI*2*Math.random();
            double angleTempWD=Math.PI*(Math.random()-0.5f);

            vertex[i*3]=(float)(10.0f*Math.cos(angleTempWD)*Math.sin(angleTempJD));
            vertex[i*3+1]=(float)(10.0f*Math.sin(angleTempWD));
            vertex[i*3+2]=(float)(10.0f*Math.cos(angleTempWD)*Math.cos(angleTempJD));
        }
    }
    public void bindData(SkyShaderProgram skyShaderProgram){
        vertexArrary.setData(0,skyShaderProgram.getPositionLocation(),3,3*4);
    }
   public void draw(){
       glDrawArrays(GL_POINTS,0,vCount);
   }
}
