package com.game.apple.earthandmoon.program;

import android.content.Context;

import static android.opengl.GLES20.*;

/**
 * Created by apple on 2016/3/19.
 */
public class SkyShaderProgram extends ShaderProgram {
    private int positionLocation;
    private int matrixLocation;
    private int pointSizeLocation;

    public SkyShaderProgram(Context context, int vertexRawID, int fragmentRawID) {
        super(context, vertexRawID, fragmentRawID);
        positionLocation = glGetAttribLocation(program, "Position");
        matrixLocation = glGetUniformLocation(program, "Matrix");
        pointSizeLocation = glGetUniformLocation(program, "PointSize");
    }

    public void setUniform(float[] matrix, float size) {
        glUniformMatrix4fv(matrixLocation, 1, false, matrix, 0);
        glUniform1f(pointSizeLocation, size);
    }

    public int getPositionLocation() {
        return positionLocation;
    }
}
