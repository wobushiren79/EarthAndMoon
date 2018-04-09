package com.game.apple.earthandmoon.data;

import android.util.Log;

import com.game.apple.earthandmoon.program.EarthShaderProgram;
import com.game.apple.earthandmoon.program.SkyShaderProgram;

import java.util.ArrayList;
import java.util.List;

import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by apple on 2016/3/19.
 */
public class Earth {
    private VertexArrary vertexArrary;
    private VertexArrary vertexArraryTexture;
    private VertexArrary vertexArraryNormal;
    private List<Float> listVertex = new ArrayList<>();
    private float[] vertex;
    float r = 1f;
    float UNIT_SIZE = 1f;


    public Earth() {
        final float angleSpan=10f;//将球进行单位切分的角度
        for(float vAngle=90;vAngle>-90;vAngle=vAngle-angleSpan){//垂直方向angleSpan度一份
            for(float hAngle=360;hAngle>0;hAngle=hAngle-angleSpan){//水平方向angleSpan度一份
                //纵向横向各到一个角度后计算对应的此点在球面上的坐标
                double xozLength=r*UNIT_SIZE*Math.cos(Math.toRadians(vAngle));
                float x1=(float)(xozLength*Math.cos(Math.toRadians(hAngle)));
                float z1=(float)(xozLength*Math.sin(Math.toRadians(hAngle)));
                float y1=(float)(r*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));
                xozLength=r*UNIT_SIZE*Math.cos(Math.toRadians(vAngle-angleSpan));
                float x2=(float)(xozLength*Math.cos(Math.toRadians(hAngle)));
                float z2=(float)(xozLength*Math.sin(Math.toRadians(hAngle)));
                float y2=(float)(r*UNIT_SIZE*Math.sin(Math.toRadians(vAngle - angleSpan)));
                xozLength=r*UNIT_SIZE*Math.cos(Math.toRadians(vAngle-angleSpan));
                float x3=(float)(xozLength*Math.cos(Math.toRadians(hAngle-angleSpan)));
                float z3=(float)(xozLength*Math.sin(Math.toRadians(hAngle-angleSpan)));
                float y3=(float)(r*UNIT_SIZE*Math.sin(Math.toRadians(vAngle - angleSpan)));
                xozLength=r*UNIT_SIZE*Math.cos(Math.toRadians(vAngle));
                float x4=(float)(xozLength*Math.cos(Math.toRadians(hAngle-angleSpan)));
                float z4=(float)(xozLength*Math.sin(Math.toRadians(hAngle-angleSpan)));
                float y4=(float)(r*UNIT_SIZE*Math.sin(Math.toRadians(vAngle)));
                //构建第一三角形
                listVertex.add(x1);listVertex.add(y1);listVertex.add(z1);
                listVertex.add(x2);listVertex.add(y2);listVertex.add(z2);
                listVertex.add(x4);listVertex.add(y4);listVertex.add(z4);
                //构建第二三角形
                listVertex.add(x4);listVertex.add(y4);listVertex.add(z4);
                listVertex.add(x2);listVertex.add(y2);listVertex.add(z2);
                listVertex.add(x3);listVertex.add(y3);listVertex.add(z3);

            }}
        vertex = new float[listVertex.size()];
        for (int i = 0; i < listVertex.size(); i++) {
            vertex[i] = listVertex.get(i);
        }
        float[] textureVertex = generateTexCoor(
                (int) (360 / angleSpan),
                (int) (180 / angleSpan));
        vertexArrary = new VertexArrary(vertex);
        vertexArraryTexture = new VertexArrary(textureVertex);
        vertexArraryNormal = new VertexArrary(vertex);
    }

    public void bindData(EarthShaderProgram earthShaderProgram) {
        vertexArrary.setData(0, earthShaderProgram.getPositionLocation(), 3, 3 * 4);
        vertexArraryTexture.setData(0, earthShaderProgram.getTextureLocation(), 2, 2 * 4);
        vertexArraryNormal.setData(0, earthShaderProgram.getNormalLocation(), 3, 3 * 4);
    }

    public void draw() {
        glDrawArrays(GL_TRIANGLES, 0, listVertex.size() / 3);
    }

    public float[] generateTexCoor(int bw, int bh) {
        float[] result = new float[bw * bh * 6 * 2];
        float sizew = 1.0f / bw;//列数
        float sizeh = 1.0f / bh;//行数
        int c = 0;
        for (int i = 0; i < bh; i++) {
            for (int j = 0; j < bw; j++) {
                //每行列一个矩形，由两个三角形构成，共六个点，12个纹理坐标
                float s = j * sizew;
                float t = i * sizeh;

                result[c++] = s;
                result[c++] = t;

                result[c++] = s;
                result[c++] = t + sizeh;

                result[c++] = s + sizew;
                result[c++] = t;

                result[c++] = s + sizew;
                result[c++] = t;

                result[c++] = s;
                result[c++] = t + sizeh;

                result[c++] = s + sizew;
                result[c++] = t + sizeh;
            }
        }
        return result;
    }
}
